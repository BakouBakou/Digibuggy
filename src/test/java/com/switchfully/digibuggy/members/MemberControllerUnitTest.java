package com.switchfully.digibuggy.members;

import com.switchfully.digibuggy.members.dtos.RegisterMemberDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MemberControllerUnitTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MemberRepository memberRepository;




    @Test
    void givenANewMemberWhenINSSIsNullBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( null,
                "bugs@bunny.com",
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "1000",
                "Looney tunes city");

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

    @Test
    void givenANewMemberWhenINSSIsEmptyBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "",
                "bugs@bunny.com",
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "1000",
                "Looney tunes city");

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

    @Test
    void givenANewMemberWhenINSSIsBlankBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "    ",
                "bugs@bunny.com",
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "1000",
                "Looney tunes city");

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

    @Test
    void givenANewMemberWhenINSSAlreadyExistsBadRequestIsThrown() {
        //GIVEN
        String alreadyExistingINSS = "44654564";
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( alreadyExistingINSS,
                "bugs@bunny.com",
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "1000",
                "Looney tunes city");

        memberRepository.registerMember(new Member(
                alreadyExistingINSS,
                "daffy@duck.com",
                "daffy",
                "duck",
                "ducks street",
                "7",
                "5000",
                "ducks city"
        ));

        //THEN
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

    @Test
    void givenANewMemberWhenEmailIsNullBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "bugs",
                null,
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "1000",
                "Looney tunes city");

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

    @Test
    void givenANewMemberWhenEmailIsEmptyBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "Bugs",
                "",
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "1000",
                "Looney tunes city");

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

    @Test
    void givenANewMemberWhenEmailIsBlankBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "Bugs",
                "        ",
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "1000",
                "Looney tunes city");

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

    @Test
    void givenANewMemberWhenEmailFormatIsCorrectMemberIsCreated() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "Bugs",
                "bugs@bunny.com",
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "1000",
                "Looney tunes city");

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
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void givenANewMemberWhenEmailFormatIsWrongNoAtNoDotBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "Bugs",
                "gfsfdgds",
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "1000",
                "Looney tunes city");

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

    @Test
    void givenANewMemberWhenEmailFormatIsWrongNoAtBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "Bugs",
                "gfsf.dgds",
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "1000",
                "Looney tunes city");

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

    @Test
    void givenANewMemberWhenEmailFormatIsWrongNoDotBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "Bugs",
                "gfsf@dgds",
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "1000",
                "Looney tunes city");

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

    @Test
    void givenANewMemberWhenEmailFormatIsWrongDotBeforeAtBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "Bugs",
                "gf.sf@dgds",
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "1000",
                "Looney tunes city");

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

    @Test
    void givenANewMemberWhenCityIsNullBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "Bugs",
                "bugs@bunny.com",
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "45",
                null);

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

    @Test
    void givenANewMemberWhenCityIsEmptyBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "Bugs",
                "bugs@bunny.com",
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "45",
                "");

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

    @Test
    void givenANewMemberWhenCityIsBlankBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "Bugs",
                "bugs@bunny.com",
                "bugs",
                "bunny",
                "Looney tunes street",
                "1",
                "45",
                "    ");

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

    @Test
    void givenANewMemberWhenLastNameIsNullBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "Bugs",
                "bugs@bunny.com",
                "bugs",
                null,
                "Looney tunes street",
                "1",
                "45",
                null);

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

    @Test
    void givenANewMemberWhenLastNameIsEmptyBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "Bugs",
                "bugs@bunny.com",
                "bugs",
                "",
                "Looney tunes street",
                "1",
                "45",
                "Looney tunes city");

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

    @Test
    void givenANewMemberWhenLastNameIsBlankBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto( "Bugs",
                "bugs@bunny.com",
                "bugs",
                "    ",
                "Looney tunes street",
                "1",
                "45",
                "Looney tunes city");

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