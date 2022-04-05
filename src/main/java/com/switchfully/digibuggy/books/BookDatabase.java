package com.switchfully.digibuggy.books;

import com.switchfully.digibuggy.books.exceptions.BookAlreadyLentException;
import com.switchfully.digibuggy.books.exceptions.BookDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BookDatabase {

    private final ConcurrentHashMap<String, Book> bookDatabase = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, LendABook> lendBookDatabase = new ConcurrentHashMap<>();

    public Book getBookByIsbn(String isbn) {
        return bookDatabase.get(isbn);
    }

    public void saveBook(Book book) {
        bookDatabase.put(book.getIsbn(), book);
    }

    public Map<String, Book> getAllBooks() {
        return bookDatabase;
    }

    public LendABook saveLentBook(LendABook lendABook) {
        lendBookDatabase.put(lendABook.getLendingId(), lendABook);
        return lendBookDatabase.get(lendABook.getLendingId());
    }

    public Optional<LendABook> getLentBookByIsbn(String isbn) {
        return lendBookDatabase.values().stream().filter(lendABook -> lendABook.getIsbn().equals(isbn)).findFirst();
    }

    public void deleteLentBook(String lendingId) {
        lendBookDatabase.remove(lendingId);
    }
}
