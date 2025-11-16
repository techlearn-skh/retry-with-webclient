package com.skh.models;

import java.time.Instant;

// Generic API response wrapper
public class ApiResponse<T> {
    private String status;
    private T data;
    private String message;
    private Instant timestamp;

    public ApiResponse(String status, T data) {
        this.status = status;
        this.data = data;
        this.timestamp = Instant.now();
    }

    // Constructors, getters, setters
}
