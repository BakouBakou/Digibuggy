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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    BookRepository bookRepository;

    @Test
    void GivenIDWhenGetBookIsCalledThenBookIsReturned() {
        Book thePrisonerOfAzkaban = new Book("123456789132", "The prisoner of Azkaban", "J.K.", "Rowling", "blablabla");
        bookRepository.save(thePrisonerOfAzkaban);

        BookMapper mapper = new BookMapper();
        BookDto expectedResult = mapper.bookToDto(thePrisonerOfAzkaban);


        BookDto result = RestAssured
                .given()
                    .accept(ContentType.JSON)
                .when()
                    .port(port)
                    .get("/books/" + thePrisonerOfAzkaban.getISBN())
                .then()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value())
                    .extract()
                    .as(BookDto.class);

        Assertions.assertThat(result).isEqualTo(expectedResult);

    }
}