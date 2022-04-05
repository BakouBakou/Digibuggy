package com.switchfully.digibuggy.books.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookAlreadyLentException extends RuntimeException{
    public BookAlreadyLentException(String isbn) {
        super("The book with isbn " + isbn + " is already lent");
    }
}
