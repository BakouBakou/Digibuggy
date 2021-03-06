package com.switchfully.digibuggy.users.members.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LastNameNotProvidedException extends RuntimeException {
    public LastNameNotProvidedException() {
        super("The lastname was not provided");
    }
}
