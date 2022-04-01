package com.switchfully.digibuggy.books;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    public void save(Book thePrisonerOfAzkaban) {
    }

    public Book getBookByIsbn(String isbn) {
        return bookDatabase.getBookByIsbn(isbn);
    }
}
