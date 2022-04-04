package com.switchfully.digibuggy.members;

import com.switchfully.digibuggy.members.dtos.RegisterMemberDto;
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
class MemberControllerUnitTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    void givenANewMemberWhenINSSIsNullBadRequestIsThrown() {
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

    @Test
    void givenANewMemberWhenINSSAlreadyExistsBadRequestIsThrown() {
        //GIVEN
        String alreadyExistingINSS = "44654564";
        RegisterMemberDto registerMemberDto = new RegisterMemberDto()
                .setInss(alreadyExistingINSS)
                .setEmailAddress("bugs@bunny.com")
                .setFirstName("bugs")
                .setLastName("bunny")
                .setStreetName("Looney tunes street")
                .setStreetNumber("1")
                .setPostalCode("1000")
                .setCityName("Looney tunes city");

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
        RegisterMemberDto registerMemberDto = new RegisterMemberDto()
                .setInss("Bugs")
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

    @Test
    void givenANewMemberWhenEmailIsEmptyBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto()
                .setInss("Bugs")
                .setEmailAddress("")
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

    @Test
    void givenANewMemberWhenEmailIsBlankBadRequestIsThrown() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto()
                .setInss("Bugs")
                .setEmailAddress("         ")
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