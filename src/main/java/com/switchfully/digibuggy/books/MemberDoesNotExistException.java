package com.switchfully.digibuggy.books;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MemberDoesNotExistException extends RuntimeException {
    public MemberDoesNotExistException(String memberId) {
        super("No subscription found for member with ID " + memberId);
    }
}
