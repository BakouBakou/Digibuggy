package com.switchfully.digibuggy.books;

import com.switchfully.digibuggy.books.dtos.LendABookDto;
import com.switchfully.digibuggy.users.members.Member;
import com.switchfully.digibuggy.users.members.MemberRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookControllerUnitTest {

    @LocalServerPort
    private int port;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void givenISBN_WhenISBNDoesNotExistsInDatabase_ThenBadRequestIsThrown() {
        //GIVEN
        String isbnNotInDB = "9876543219874";

        //THEN
        RestAssured
                .given()
                .accept(JSON)
                .when()
                .port(port)
                .get("/books/" + isbnNotInDB)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    void givenABooksISBN_WhenBookIsAlreadyLent_ThenBadRequestIsThrown() {
       //GIVEN
        Book thePrisonerOfAzkaban = new Book("123456789132", "The prisoner of Azkaban", "J.K.", "Rowling", "blablabla");
        bookRepository.saveBook(thePrisonerOfAzkaban);
        Member member = new Member("8787643", "member@mail.test", "some", "member", "some street", "10", "5000", "Namur");
        memberRepository.registerMember(member);
        String memberId = member.getId();
        String isbn = thePrisonerOfAzkaban.getIsbn();
        LendABookDto toLendTwice = new LendABookDto(memberId, isbn);
        bookRepository.lendBook(bookMapper.toLendABook(toLendTwice));

        //THEN
        RestAssured
                .given()
                .body(toLendTwice)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/books/lend")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    void givenABooksISBNAndMemberId_WhenLendingABookThatDoesNotExistInDatabase_ThenBadRequestIsThrown() {
        //GIVEN
        Member member = new Member("8787643", "member@mail.test", "some", "member", "some street", "10", "5000", "Namur");
        memberRepository.registerMember(member);
        String memberId = member.getId();
        String isbn = "123456789132";
        LendABookDto toLendBookThatDoesNotExist = new LendABookDto(memberId, isbn);

        //THEN
        RestAssured
                .given()
                .body(toLendBookThatDoesNotExist)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/books/lend")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenABooksISBNAndMemberId_WhenMemberDoesNotExistInDatabase_ThenBadRequestIsThrown() {
        //GIVEN
        Book thePrisonerOfAzkaban = new Book("123456789132", "The prisoner of Azkaban", "J.K.", "Rowling", "blablabla");
        bookRepository.saveBook(thePrisonerOfAzkaban);
        String memberId = "54654564654";
        String isbn = "123456789132";
        LendABookDto toLendBookThatDoesNotExist = new LendABookDto(memberId, isbn);

        //THEN
        RestAssured
                .given()
                .body(toLendBookThatDoesNotExist)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/books/lend")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}

