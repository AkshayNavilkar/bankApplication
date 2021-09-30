package com.bank.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ValidationFailedException extends RuntimeException{

    public ValidationFailedException(String message) {
        super(message);
    }
}