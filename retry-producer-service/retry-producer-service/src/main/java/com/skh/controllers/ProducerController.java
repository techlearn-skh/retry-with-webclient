package com.skh.controllers;


import com.skh.exceptions.MultiStatusException;
import com.skh.models.ResultDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

        }
        if(retryCount == 1){
            throw new MultiStatusException(List.of( new ResultDetail("PaymentService", 502, "Bad Gateway")));
        } else if (retryCount == 2 ) {
            throw new MultiStatusException(List.of( new ResultDetail("InventoryService", 503, "Service Unavailable")));
            
        } else if ( retryCount == 3) {
            List<ResultDetail> results = List.of(
                    new ResultDetail("ShippingService", 504, "Gateway Timeout"),
                    new ResultDetail("NotificationService", 404, "Not Found")
            );
            throw new MultiStatusException(results);
        } else if ( retryCount == 4) {
            throw new NullPointerException("Error from Producer-service..!! - NullPointerException");
        }
        else if ( retryCount == 5) {
            throw new ArithmeticException("Error from Producer-service..!! - ArithmeticException");
        }


        return new ResponseEntity<>(employee.fetchAllEmployees().get(0), HttpStatus.OK);
    }

    @GetMapping(path = "/fetchAllEmployees")
    public ResponseEntity<List<Employee>> fetchAllEmployees() {
        return new ResponseEntity<>(employee.fetchAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/check207")
    public ResponseEntity<Map<String, Object>> check207() {
        List<ResultDetail> results = List.of(
                new ResultDetail("AuthService", 200, "OK"),
                new ResultDetail("PaymentService", 502, "Bad Gateway"),
                new ResultDetail("InventoryService", 503, "Service Unavailable"),
                new ResultDetail("ShippingService", 504, "Gateway Timeout"),
                new ResultDetail("NotificationService", 404, "Not Found")
        );
        throw new MultiStatusException(results);
    }



}
