package com.switchfully.digibuggy.users.members.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailNotProvidedException extends RuntimeException {
    public EmailNotProvidedException() {
        super("The email was not provided");
    }
}
