package com.switchfully.digibuggy.books;

import com.switchfully.digibuggy.books.dtos.BookDto;
import com.switchfully.digibuggy.books.dtos.BookOverviewDto;
import com.switchfully.digibuggy.books.dtos.LendABookDto;
import com.switchfully.digibuggy.books.exceptions.BookDoesNotExistException;
import com.switchfully.digibuggy.books.exceptions.ISBNNotFoundException;
import com.switchfully.digibuggy.users.members.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookMapper bookMapper;
    BookRepository bookRepository;
    MemberRepository memberRepository;
    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookService(BookMapper bookMapper, BookRepository bookRepository, MemberRepository memberRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public BookDto getBookByIsbn(String isbn) {
        logger.info("getBookByIsbn method is called");
        if (bookRepository.getBookByIsbn(isbn) == null) {
            logger.error(new ISBNNotFoundException().getMessage());
            throw new ISBNNotFoundException();
        }
        logger.info("returning book");
        return bookMapper.bookToDto(bookRepository.getBookByIsbn(isbn));
    }

    public List<BookOverviewDto> getAllBooks() {
        logger.info("getAllBooks method is called");
        return bookMapper.bookOverviewToDto(bookRepository.getAllBooks().values());
    }

    public void lendBook(LendABookDto lendABookDto) {
        logger.info("lendBook method is called");
        if (memberRepository.getById(lendABookDto.getMemberId()).isEmpty()) {
            logger.error(new MemberDoesNotExistException().getMessage());
            throw new MemberDoesNotExistException();
        }
        logger.info("Book is lent");
        bookRepository.lendBook(bookMapper.toLendABook(lendABookDto));
    }
}
