package com.bank.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SendingFailedException extends RuntimeException{
    public SendingFailedException(String message) {
        super(message);
    }
}