package com.switchfully.digibuggy.books;

import com.switchfully.digibuggy.books.dtos.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDto bookToDto(Book book) {
        return new BookDto(book.getIsbn(), book.getTitle(), book.getAuthorFirstname(), book.getAuthorLastname(), book.getSummary());
    }
}
