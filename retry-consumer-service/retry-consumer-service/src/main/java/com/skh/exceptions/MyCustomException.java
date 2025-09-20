package com.skh.exceptions;

public class MyCustomException extends RuntimeException{
    public MyCustomException(String message){
        super(message);
    }

    public MyCustomException(String message, Throwable exception){
        super(message, exception);
    }


}
