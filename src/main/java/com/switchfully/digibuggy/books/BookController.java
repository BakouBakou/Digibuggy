package com.switchfully.digibuggy.books;

import com.switchfully.digibuggy.books.dtos.BookDto;
import com.switchfully.digibuggy.books.dtos.BookOverviewDto;
import com.switchfully.digibuggy.books.dtos.LendABookDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {


    private final BookService bookService;
    private final Logger logger = LoggerFactory.getLogger(BookController.class);
    public static final String ISBN_NOT_FOUND_IN_DATABASE = "The ISBN doesn't exist in our library";


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookOverviewDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BookDto getBook(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @PostMapping(path = "/lend", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void lendBook(@RequestBody LendABookDto lendABookDto) {
        bookService.lendBook(lendABookDto);
    }

    @DeleteMapping(path = "/return/{lendingId}")
    @ResponseStatus(HttpStatus.OK)
    public void returnBook(@PathVariable String lendingId) {
        bookService.returnBook(lendingId);
    }
}



