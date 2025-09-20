package com.skh.controllers;

import com.fasterxml.jackson.databind.JsonNode;

public class MyCustomException extends RuntimeException{
    public MyCustomException(String message){
        super(message);
    }

    public MyCustomException(String message, Throwable exception){
        super(message, exception);
    }


}
