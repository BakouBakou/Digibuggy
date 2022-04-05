package com.switchfully.digibuggy.books.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookDoesNotExistException extends RuntimeException {
    public BookDoesNotExistException() {
        super("This book is not present in this library");
    }
}
