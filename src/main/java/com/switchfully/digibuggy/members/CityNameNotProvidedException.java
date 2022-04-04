package com.switchfully.digibuggy.members;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CityNameNotProvidedException extends RuntimeException {
    public CityNameNotProvidedException() {
        super("The city name was not provided");
    }
}
