package com.switchfully.digibuggy.books;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BookDatabase {

    private final ConcurrentHashMap<String, Book> bookDatabase= new ConcurrentHashMap<>();

    public BookDatabase() {
    }

    public Book getBookByIsbn(String isbn) {
        return bookDatabase.get(isbn);
    }

    public void save(Book book) {
        bookDatabase.put(book.getIsbn(), book);
    }

    public Map<String, Book> getAllBooks() {
        return bookDatabase;
    }
}
