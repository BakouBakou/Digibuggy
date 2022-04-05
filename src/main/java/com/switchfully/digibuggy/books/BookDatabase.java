package com.switchfully.digibuggy.books;

import com.switchfully.digibuggy.books.exceptions.BookAlreadyLentException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BookDatabase {

    private final ConcurrentHashMap<String, Book> bookDatabase = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, LendABook> lendBookDatabase = new ConcurrentHashMap<>();

//    public BookDatabase() {
//    }

    public Book getBookByIsbn(String isbn) {
        return bookDatabase.get(isbn);
    }

    public void saveBook(Book book) {
        bookDatabase.put(book.getIsbn(), book);
    }

    public Map<String, Book> getAllBooks() {
        return bookDatabase;
    }

    public void saveLentBook(LendABook lendABook) {

        Optional<LendABook> lentBooksSameISBN = lendBookDatabase.values().stream()
                .filter(lentBook -> lentBook.getIsbn().equals(lendABook.getIsbn()))
                .findFirst();

        if(lentBooksSameISBN.isPresent()) {
            throw new BookAlreadyLentException();
        }

        lendBookDatabase.put(lendABook.getLendingId(), lendABook);
    }
}
