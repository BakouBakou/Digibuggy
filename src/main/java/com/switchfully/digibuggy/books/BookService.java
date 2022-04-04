package com.switchfully.digibuggy.books;

import com.switchfully.digibuggy.books.dtos.BookDto;
import com.switchfully.digibuggy.members.INSSNotProvidedException;
import com.switchfully.digibuggy.members.MemberController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
}
