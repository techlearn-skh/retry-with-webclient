package com.skh.controllers;


import com.skh.model.Employee;
import com.skh.services.WebClientConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
