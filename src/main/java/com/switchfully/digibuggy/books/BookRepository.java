package com.switchfully.digibuggy.books;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class BookRepository {

    BookDatabase bookDatabase;

    public BookRepository(BookDatabase bookDatabase) {
        this.bookDatabase = bookDatabase;
    }

    public void saveBook(Book book) {
        bookDatabase.saveBook(book);
    }

    public Book getBookByIsbn(String isbn) {
        return bookDatabase.getBookByIsbn(isbn);
    }

    public Map<String, Book> getAllBooks() {
        return bookDatabase.getAllBooks();
    }

    public void lendBook(LendABook lendABook) {
        bookDatabase.saveLentBook(lendABook);
    }

    public Optional<LendABook> getLentBookByIsbn(String isbn) {
        return bookDatabase.getLentBookByIsbn(isbn);
    }
}
