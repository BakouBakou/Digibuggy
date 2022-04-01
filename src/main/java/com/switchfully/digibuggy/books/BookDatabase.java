package com.switchfully.digibuggy.books;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class BookDatabase {

    private ConcurrentHashMap<String, Book> bookDatabase= new ConcurrentHashMap<>();

    public BookDatabase() {
        bookDatabase.put("1234567891234", new Book("1234567891234", "The prisoner of Azkaban", "J.K.", "Rowling", "blablabla"));
    }

    public Book getBookByIsbn(String isbn) {
        return bookDatabase.get(isbn);
    }

    public void save(Book book) {
        bookDatabase.put(book.getIsbn(), book);
    }
}
