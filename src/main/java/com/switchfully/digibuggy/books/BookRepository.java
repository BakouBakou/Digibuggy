package com.switchfully.digibuggy.books;

import org.springframework.stereotype.Repository;

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
}
