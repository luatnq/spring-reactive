package com.example.reviewservice.exception;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(String name){
        super("Product: " + name +"is not found");
    }
}
