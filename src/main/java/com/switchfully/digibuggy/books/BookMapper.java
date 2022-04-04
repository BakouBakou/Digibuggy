package com.switchfully.digibuggy.books;

import com.switchfully.digibuggy.books.dtos.BookDto;
import com.switchfully.digibuggy.books.dtos.BookOverviewDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDto bookToDto(Book book) {
        return new BookDto(book.getIsbn(), book.getTitle(), book.getAuthorFirstname(), book.getAuthorLastname(), book.getSummary());
    }

    public BookOverviewDto bookOverviewToDto(Book book) {
        return new BookOverviewDto(book.getIsbn(), book.getTitle(), book.getAuthorFirstname(), book.getAuthorLastname());
    }
}

//    public List<BookOverviewDto>
//}
