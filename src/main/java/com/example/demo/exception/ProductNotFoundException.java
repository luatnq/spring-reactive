package com.example.demo.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String name){
        super("Product: " + name +"is not found");
    }
}
