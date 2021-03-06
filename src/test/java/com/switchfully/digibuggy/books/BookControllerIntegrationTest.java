package com.switchfully.digibuggy.books;

import com.switchfully.digibuggy.books.dtos.BookDto;
import com.switchfully.digibuggy.books.dtos.BookOverviewDto;
import com.switchfully.digibuggy.books.dtos.LendABookDto;
import com.switchfully.digibuggy.books.dtos.LentBookDto;
import com.switchfully.digibuggy.users.members.Member;
import com.switchfully.digibuggy.users.members.MemberRepository;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookMapper mapper;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void givenISBN_whenGetBookIsCalled_thenBookIsReturned() {
        Book thePrisonerOfAzkaban = new Book("123456789132", "The prisoner of Azkaban", "J.K.", "Rowling", "blablabla");
        bookRepository.saveBook(thePrisonerOfAzkaban);

        BookDto expectedResult = mapper.bookToDto(thePrisonerOfAzkaban);

        BookDto result = RestAssured
                .given()
                .accept(JSON)
                .when()
                .port(port)
                .get("/books/" + thePrisonerOfAzkaban.getIsbn())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(BookDto.class);

        Assertions.assertThat(result).isEqualTo(expectedResult);

    }

    @Test
    void whenGetBooks_ThenGetAllBooks() {
        List<Book> bookList = new ArrayList<>(List.of(
                new Book("1", "The prisoner of Azkaban", "J.K.", "Rowling", "blablabla"),
                new Book("2", "The prisoner of Azkabon", "J.K.", "Rowling", "blablabla"),
                new Book("3", "The prisoner of Azkabin", "J.K.", "Rowling", "blablabla")
        ));

        bookRepository.saveBook(bookList.get(0));
        bookRepository.saveBook(bookList.get(1));
        bookRepository.saveBook(bookList.get(2));

        List<BookOverviewDto> expectedDtoList = new ArrayList<>();

        bookList.forEach(book -> expectedDtoList.add(mapper.bookOverviewToDto(book)));

        BookOverviewDto[] result = RestAssured
                .given()
                .accept(JSON)
                .when()
                .port(port)
                .get("/books")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(BookOverviewDto[].class);

        Assertions.assertThat(result).hasSameElementsAs(expectedDtoList);

    }

    @Test
    void givenMembersIDAndBookISBN_WhenLendBook_ThenAddBookToLentBooks() {
        Book thePrisonerOfAzkaban = new Book("123456789132", "The prisoner of Azkaban", "J.K.", "Rowling", "blablabla");
        bookRepository.saveBook(thePrisonerOfAzkaban);
        Member member = new Member("8787643", "member@mail.test", "some", "member", "some street", "10", "5000", "Namur");
        memberRepository.registerMember(member);
        String memberId = member.getId();
        String isbn = thePrisonerOfAzkaban.getIsbn();

        LendABookDto toLend = new LendABookDto(memberId, isbn);

        LentBookDto actualLendBookDto = RestAssured
                .given()
                .body(toLend)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/books/lend")
                .then()
                .assertThat()
                .statusCode(HttpStatus.ACCEPTED.value())
                .extract()
                .as(LentBookDto.class);

        Assertions.assertThat(actualLendBookDto.getLendingId()).isNotBlank().isNotEmpty().isNotNull();
        Assertions.assertThat(actualLendBookDto.getDueDate()).isNotNull();
        Assertions.assertThat(actualLendBookDto.getIsbn()).isEqualTo(toLend.getISBN());
        Assertions.assertThat(actualLendBookDto.getMemberId()).isEqualTo(toLend.getMemberId());
    }

    @Test
    void givenLendingId_WhenReturnBook_ThenBookIsDeletedFromLendBookDatabase(){
        Book thePrisonerOfAzkaban = new Book("123456789132", "The prisoner of Azkaban", "J.K.", "Rowling", "blablabla");
        bookRepository.saveBook(thePrisonerOfAzkaban);
        Member member = new Member("8787643", "member@mail.test", "some", "member", "some street", "10", "5000", "Namur");
        memberRepository.registerMember(member);
        LendABook lentBook = bookRepository.lendBook(new LendABook(member.getId(), thePrisonerOfAzkaban.getIsbn()));
        String lendingId = lentBook.getLendingId();

        RestAssured
                .given()
                .contentType(JSON)
                .when()
                .port(port)
                .delete("/books/return/" + lendingId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

                 org.junit.jupiter.api.Assertions.assertTrue(bookRepository.getLentBookByIsbn(lentBook.getIsbn()).isEmpty());
    }
}