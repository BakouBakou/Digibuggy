package com.switchfully.digibuggy.users.members.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongEmailFormatException extends RuntimeException {
    public WrongEmailFormatException () {
        super("Email format is wrong, expected: example@domain.com");
    }
}
