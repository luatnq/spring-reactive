package com.example.orderservice.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String name){
        super("Order: " + name +"is not found");
    }
}
