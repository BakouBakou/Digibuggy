package com.switchfully.digibuggy.books;

import com.switchfully.digibuggy.books.dtos.BookDto;
import com.switchfully.digibuggy.books.dtos.BookOverviewDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookMapper bookMapper;
    BookRepository bookRepository;
    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookService(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public BookDto getBookByIsbn(String isbn) {
        return bookMapper.bookToDto(bookRepository.getBookByIsbn(isbn));
    }

//    public List<BookOverviewDto> getAllBooks() {
//        return bookMapper.bookOverviewToDto((bookRepository.getAllBooks()));
//    }
}
