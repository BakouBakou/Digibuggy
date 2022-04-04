package com.switchfully.digibuggy.books;

import com.switchfully.digibuggy.books.dtos.BookDto;
import com.switchfully.digibuggy.books.dtos.BookOverviewDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookOverviewDto> getAllBooks() {
        List<Book> booksToReturn = new ArrayList<>(List.of(
                new Book("1", "The prisoner of Azkaban", "J.K.", "Rowling", "blablabla"),
                new Book("2", "The prisoner of Azkabon", "J.K.", "Rowling", "blablabla"),
                new Book("3", "The prisoner of Azkabin", "J.K.", "Rowling", "blablabla")
        ));

        BookMapper mapper = new BookMapper();
        List<BookOverviewDto> bookOverviewToDto = new ArrayList<>();
        booksToReturn.forEach(book -> bookOverviewToDto.add(mapper.bookOverviewToDto(book)));
        return bookOverviewToDto;
    }

    @GetMapping(path = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BookDto getBook(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }
}



