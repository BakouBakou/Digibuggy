package com.switchfully.digibuggy.books;

import com.switchfully.digibuggy.books.dtos.BookDto;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    BookMapper bookMapper;
    BookRepository bookRepository;

    public BookService(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public BookDto getBookByIsbn(String isbn) {
        return bookMapper.bookToDto(bookRepository.getBookByIsbn(isbn));
    }
}
