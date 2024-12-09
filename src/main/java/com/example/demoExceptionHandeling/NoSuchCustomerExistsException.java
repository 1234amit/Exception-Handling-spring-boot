package com.example.demoExceptionHandeling;

public class NoSuchCustomerExistsException  extends RuntimeException{
    private String message;

    public NoSuchCustomerExistsException(){}

    public NoSuchCustomerExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
