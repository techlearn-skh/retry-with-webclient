package com.skh.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class ProducerController {

    @Autowired
    private Employee employee;


    @GetMapping(path = "/fetchEmployee/{retryCount}")
    public ResponseEntity<Employee> fetchEmployee(@PathVariable Integer retryCount) throws Exception {
        System.out.println("fetchEmployee() called in ProducerController.java!!!");
        Thread.sleep(1000);
        if(new Random().nextInt() % 2 == 0){
//        if(true){
//            try {
                throw new NullPointerException("Error from Producer-service..!!");
//            } catch (Exception e) {
//                return new ResponseEntity<Employee>((Employee) List.of(new Employee()), HttpStatus.INTERNAL_SERVER_ERROR);
//            }

        }
        return new ResponseEntity<>(employee.fetchAllEmployees().get(0), HttpStatus.OK);
    }

    @GetMapping(path = "/fetchAllEmployees")
    public ResponseEntity<List<Employee>> fetchAllEmployees() {
        return new ResponseEntity<>(employee.fetchAllEmployees(), HttpStatus.OK);
    }

}
