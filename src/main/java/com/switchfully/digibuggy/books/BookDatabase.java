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
    private final Logger logger = LoggerFactory.getLogger(BookController.class);

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
            logger.error(new BookAlreadyLentException().getMessage());
            throw new BookAlreadyLentException();
        }

        Optional<Book> bookSameISBN = bookDatabase.values().stream()
                .filter(book -> book.getIsbn().equals(lendABook.getIsbn()))
                .findFirst();

        if(bookSameISBN.isEmpty()){
            logger.error(new BookDoesNotExistException().getMessage());
            throw new BookDoesNotExistException();
        }

        lendBookDatabase.put(lendABook.getLendingId(), lendABook);
    }
}
