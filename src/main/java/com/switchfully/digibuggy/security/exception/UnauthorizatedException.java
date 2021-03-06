package com.switchfully.digibuggy.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizatedException extends RuntimeException {
    public UnauthorizatedException() {
        super("You have no access to this feature");
    }
}