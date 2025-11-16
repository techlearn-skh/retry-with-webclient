package com.skh.services;

import com.skh.model.Employee;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;

@Service
public class WebClientConsumerServiceImpl {

    WebClient webClient = WebClient.create("http://localhost:9001");

    public Employee pathaVariableEg(String empName){
        // GET request (blocking)
        Mono<Employee> response = webClient.get()
                .uri("/pathaVariableEg/{empName}", empName)
                .header("Authorization", "true")
                .cookie("EMPLOYEE-HEADER","12345")
                .retrieve()
                .bodyToMono(Employee.class);
        return response.block();
    }

    public Employee requestParamEg(String empName, Integer empId){
        // GET request (blocking)
        Mono<Employee> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/requestParamEg")
                        .queryParam("empName", empName)
                        .queryParam("empId", empId)
                        .build())
                .header("Authorization", "true")
                .cookie("EMPLOYEE-HEADER","12345")
                .retrieve()
                .bodyToMono(Employee.class);
        return response.block();
    }

    public Mono<String> fileUpload(MultipartFile file) throws IOException {

        // Save file temporarily (required to create a FileSystemResource)
        File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile);

        // Prepare multipart body to send to another service
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("file", new FileSystemResource(tempFile));
        formData.add("description", "Forwarded from UI");

        return webClient.post()
                .uri("/upload") // endpoint of another service
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(formData))
                .retrieve()
                .bodyToMono(String.class)
                .doFinally(signal -> tempFile.delete()); // clean up temp file

       /* //File file = new File("C:/uploads/myfile.txt");



        return webClient.post()
                .uri("/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", new FileSystemResource(file)))
                .retrieve()
                .bodyToMono(String.class);*/
    }



}
