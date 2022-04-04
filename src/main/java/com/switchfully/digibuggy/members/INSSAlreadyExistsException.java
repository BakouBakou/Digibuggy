package com.switchfully.digibuggy.members;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class INSSAlreadyExistsException extends RuntimeException {
    public INSSAlreadyExistsException() {
        super("An account with the provided INSS already exists");
    }
}
