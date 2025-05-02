package com.example.login.exceptions;
import org.apache.coyote.BadRequestException;


public class BadRequestExceptions extends BadRequestException {
    public BadRequestExceptions(String message){
        super(message);
    }
}