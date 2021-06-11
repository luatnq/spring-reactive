package com.example.categoryservice.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String name){
        super("Product: " + name +"is not found");
    }
}
