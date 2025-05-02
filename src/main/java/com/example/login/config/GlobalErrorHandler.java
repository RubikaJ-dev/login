package com.example.login.config;

import com.example.login.exceptions.BadRequestExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(BadRequestExceptions.class)
    public ResponseEntity<String> handleBadRequest(BadRequestExceptions ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

