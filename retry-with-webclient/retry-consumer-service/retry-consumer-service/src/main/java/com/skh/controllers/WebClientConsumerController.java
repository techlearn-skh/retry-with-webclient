package com.skh.controllers;


import com.skh.model.Employee;
import com.skh.services.WebClientConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.File;
import java.time.LocalTime;

@RestController
public class WebClientConsumerController {

    @Autowired
    private WebClientConsumerServiceImpl consumerService;


    @GetMapping(path = "/pathaVariableEg/{empName}", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<Employee> pathaVariableEg(@PathVariable String empName) {
        return ResponseEntity.ok(consumerService.pathaVariableEg(empName));
    }

    /*
    @RequestParam("file") MultipartFile file → for Spring MVC (spring-boot-starter-web).
    @RequestPart("file") FilePart file → for Spring WebFlux (spring-boot-starter-webflux).
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<String> uploadFile(@RequestPart("file") FilePart filePart) {
        return filePart.transferTo(new File(filePart.filename()))
                .then(Mono.just("File received: " + filePart.filename()));
    }
}
