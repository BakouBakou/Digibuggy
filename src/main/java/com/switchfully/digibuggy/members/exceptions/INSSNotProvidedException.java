package com.switchfully.digibuggy.members.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class INSSNotProvidedException extends RuntimeException {

    public INSSNotProvidedException() {
        super("The INSS was not provided");
    }
}
