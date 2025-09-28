package com.skh.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MultiStatusException.class)
    public ResponseEntity<Map<String, Object>> handleMultiStatus(MultiStatusException ex) {
        Map<String, Object> body = Map.of("results", ex.getResults());
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(body);
    }
}
