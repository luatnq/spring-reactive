package com.example.orderservice.exception;

public class BillNotFoundException extends RuntimeException{
    public BillNotFoundException(String name){
        super("Bill: " + name +"is not found");
    }
}
