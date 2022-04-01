package com.switchfully.digibuggy.books;

import com.switchfully.digibuggy.books.dtos.BookDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
class BookControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    BookRepository bookRepository;

    @Test
    void givenISBN_whenGetBookIsCalled_thenBookIsReturned() {
        Book thePrisonerOfAzkaban = new Book("123456789132", "The prisoner of Azkaban", "J.K.", "Rowling", "blablabla");
        bookRepository.save(thePrisonerOfAzkaban);

        BookMapper mapper = new BookMapper();
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

        bookRepository.save(bookList.get(0));
        bookRepository.save(bookList.get(1));
        bookRepository.save(bookList.get(2));

        BookMapper mapper = new BookMapper();

        List<BookDto> expectedDtoList = new ArrayList<>();

        bookList.forEach(book -> expectedDtoList.add(mapper.bookToDto(book)));

        BookDto[] result = RestAssured
                .given()
                .accept(JSON)
                .when()
                .port(port)
                .get("/books")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(BookDto[].class);

        Assertions.assertThat(result).hasSameElementsAs(expectedDtoList);

    }
}