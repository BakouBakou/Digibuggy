package com.switchfully.digibuggy.books;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BookRepository {

    BookDatabase bookDatabase;

    public BookRepository(BookDatabase bookDatabase) {
        this.bookDatabase = bookDatabase;
    }

    public void save(Book book) {
        bookDatabase.save(book);
    }

    public Book getBookByIsbn(String isbn) {
        return bookDatabase.getBookByIsbn(isbn);
    }

    public Map<String, Book> getAllBooks() {
        return bookDatabase.getAllBooks();
    }
}
