package com.switchfully.digibuggy.books;

import com.switchfully.digibuggy.books.dtos.BookDto;
import com.switchfully.digibuggy.books.dtos.BookOverviewDto;
import com.switchfully.digibuggy.books.dtos.LendABookDto;
import com.switchfully.digibuggy.books.exceptions.BookAlreadyLentException;
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
        logger.info("getBookByIsbn method is called with isbn " + isbn);
        if (bookRepository.getBookByIsbn(isbn) == null) {
            logger.error(new ISBNNotFoundException(isbn).getMessage());
            throw new ISBNNotFoundException(isbn);
        }
        logger.info("returning book with isbn " + isbn);
        return bookMapper.bookToDto(bookRepository.getBookByIsbn(isbn));
    }

    public List<BookOverviewDto> getAllBooks() {
        logger.info("getAllBooks method is called");
        return bookMapper.bookOverviewToDto(bookRepository.getAllBooks().values());
    }

    public void lendBook(LendABookDto lendABookDto) {
        logger.info("lendBook method is called by member with id " + lendABookDto.getMemberId() + " and with isbn " + lendABookDto.getISBN());

        if (memberRepository.getById(lendABookDto.getMemberId()).isEmpty()) {
            logger.error(new MemberDoesNotExistException(lendABookDto.getMemberId()).getMessage());
            throw new MemberDoesNotExistException(lendABookDto.getMemberId());
        }

        if (bookRepository.getBookByIsbn(lendABookDto.getISBN()) == null) {
            logger.error(new BookDoesNotExistException(lendABookDto.getISBN()).getMessage());
            throw new BookDoesNotExistException(lendABookDto.getISBN());
        }

        if (bookRepository.getLentBookByIsbn(lendABookDto.getISBN()).isPresent()) {
            logger.error(new BookAlreadyLentException(lendABookDto.getISBN()).getMessage());
            throw new BookAlreadyLentException(lendABookDto.getISBN());
        }

        logger.info("Book with isbn " + lendABookDto.getISBN() + " is lent by member with id " + lendABookDto.getMemberId());
        bookRepository.lendBook(bookMapper.toLendABook(lendABookDto));
    }
}
