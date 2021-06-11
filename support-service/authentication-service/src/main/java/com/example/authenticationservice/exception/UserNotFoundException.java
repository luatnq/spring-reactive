package com.example.authenticationservice.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String name){
        super("User: " + name +"is not found");
    }
}
