package com.switchfully.digibuggy.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("Incorrect password provided");
    }
}
