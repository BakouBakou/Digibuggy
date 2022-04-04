package com.switchfully.digibuggy.books;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookControllerUnitTest {

    @LocalServerPort
    private int port;

    @Autowired
    BookRepository bookRepository;

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
}

