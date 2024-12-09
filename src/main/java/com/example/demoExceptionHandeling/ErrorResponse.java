package com.example.demoExceptionHandeling;

public class ErrorResponse {
    private int statusCode;
    private String message;

    public ErrorResponse( String message) {
        super();
        this.message = message;
    }
}
