package com.example.demoExceptionHandeling;

public class CustomerAlreadyExistsException extends RuntimeException{
    private String message;


    //create constructor
    public CustomerAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }


}


