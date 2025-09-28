package com.skh.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
public class WebClientProducerController {

    @Autowired
    private  Employee employeeRef ;

    @GetMapping(path = "/pathaVariableEg/{empName}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<Employee> pathaVariableEg(@PathVariable String empName,
                                                    @CookieValue(name = "EMPLOYEE-HEADER", required = false) String optionalCookieValue,
                                                    @RequestHeader(value = "Authorization", required = false) String authorizationHeader){

        Employee employee = employeeRef.fetchAllEmployees().get(0);
        employee.setEmpName(empName);
//        return ResponseEntity.of(Optional.of(employee));
//        return ResponseEntity.ofNullable(employee);
//        return ResponseEntity.notFound().build();

//        return ResponseEntity.status(HttpStatus.CREATED).body(employee);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header("X-Custom-Header", "custom-value")
                .header("EMPLOYEE-HEADER", optionalCookieValue)
                .body(employee);


    }

    @GetMapping(path = "/requestParamEg", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<Employee> requestParamEg(@RequestParam String empName, @RequestParam Integer empId,
                                                   @RequestHeader HttpHeaders headers){

        headers.forEach((key, value) -> {
            System.out.println(String.format("Request Header Key: %s, value: %s", key, value));
        });


        Employee employee = employeeRef.fetchAllEmployees().get(0);
        employee.setEmpName(empName);
        employee.setEmpId(empId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header("X-Custom-Header", "custom-value")
                .header("EMPLOYEE-HEADER", "2397242")
                .body(employee);


    }



    // 201 Created
    @PostMapping(path = "saveEmployee")
    public ResponseEntity<Employee> createProduct(@Validated @RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    // 204 No Content
    @DeleteMapping("deleteEmployee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

    /*
    @RequestParam("file") MultipartFile file → for Spring MVC (spring-boot-starter-web).
    @RequestPart("file") FilePart file → for Spring WebFlux (spring-boot-starter-webflux).
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestPart("file") FilePart file) {
        try {
            return ResponseEntity.ok(String.format("File uploaded Successfully..!!!!, fileName: %s, time: %s",file.filename(), LocalTime.now()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed");
        }
    }


}
