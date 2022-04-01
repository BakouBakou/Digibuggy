package com.switchfully.digibuggy.members;

import com.switchfully.digibuggy.members.dtos.RegisterMemberDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class MemberControllerUnitTest {

    @LocalServerPort
    private int port;

    @Test
    void GivenANewMemberWhenINSSIsNullBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto()
                .setEmailAddress("bugs@bunny.com")
                .setFirstName("bugs")
                .setLastName("bunny")
                .setStreetName("Looney tunes street")
                .setStreetNumber("1")
                .setPostalCode("1000")
                .setCityName("Looney tunes city");


                RestAssured
                        .given()
                        .body(registerMemberDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/members")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.BAD_REQUEST.value());


    }

}