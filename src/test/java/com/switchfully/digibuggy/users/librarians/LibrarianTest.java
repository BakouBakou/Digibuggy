package com.switchfully.digibuggy.users.librarians;

import com.switchfully.digibuggy.users.members.dtos.MemberDto;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LibrarianTest {

    @LocalServerPort
    private int port;

    @Test
    void whenCreatingLibrarian_ThenLibrarianIsAddedToDataBase() {
        RegisterLibrarianDto registerLibrarianDto = new RegisterLibrarianDto("Bugs", "Bunny", "bugs@bunny.com");

        LibrarianDto librarianDto = RestAssured
                .given()
                    .body(registerLibrarianDto)
                    .accept(JSON)
                    .contentType(JSON)
                .when()
                    .port(port)
                    .post("/admin/librarians")
                .then()
                    .assertThat()
                    .statusCode(HttpStatus.CREATED.value())
                    .extract()
                    .as(LibrarianDto.class);

        Assertions.assertThat(librarianDto.getFirstName()).isEqualTo(registerLibrarianDto.getFirstName());
        Assertions.assertThat(librarianDto.getLastName()).isEqualTo(registerLibrarianDto.getLastName());
        Assertions.assertThat(librarianDto.getEmailAddress()).isEqualTo(registerLibrarianDto.getEmailAddress());

    }


}



