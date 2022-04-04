package com.switchfully.digibuggy.members;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailNotProvidedException extends RuntimeException{
    EmailNotProvidedException(){
        super("The email was not provided");
    }
}
