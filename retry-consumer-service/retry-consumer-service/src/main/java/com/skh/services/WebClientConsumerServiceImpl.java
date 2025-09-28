package com.skh.services;

import com.skh.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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



}
