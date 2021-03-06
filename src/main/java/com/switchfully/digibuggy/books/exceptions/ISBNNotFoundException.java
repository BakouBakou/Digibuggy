package com.switchfully.digibuggy.books.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ISBNNotFoundException extends RuntimeException {
    public ISBNNotFoundException(String isbn) {
        super("No books were found with ISBN " + isbn);
    }
}
