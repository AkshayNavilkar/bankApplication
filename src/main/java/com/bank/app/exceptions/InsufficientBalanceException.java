package com.bank.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}
