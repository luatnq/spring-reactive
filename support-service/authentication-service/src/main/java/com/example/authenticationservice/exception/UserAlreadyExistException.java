package com.example.authenticationservice.exception;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String name){
        super("User: " + name +"already exist");
    }
}
