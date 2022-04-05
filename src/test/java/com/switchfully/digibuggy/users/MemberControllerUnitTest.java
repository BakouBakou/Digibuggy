package com.switchfully.digibuggy.users;

import com.switchfully.digibuggy.users.members.Member;
import com.switchfully.digibuggy.users.members.MemberRepository;
import com.switchfully.digibuggy.users.members.dtos.RegisterMemberDto;
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

    @Nested
    @DisplayName("INSS tests")
    class INSSTests {
        @Test
        void givenANewMember_WhenINSSIsNull_ThenBadRequestIsThrown() {
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
        void givenANewMember_WhenINSSIsEmpty_ThenBadRequestIsThrown() {
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
        void givenANewMember_WhenINSSIsBlank_ThenBadRequestIsThrown() {
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
        void givenANewMember_WhenINSSAlreadyExists_ThenBadRequestIsThrown() {
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
    }

    @Nested
    @DisplayName("Empty email tests")
    class EmailTest {
        @Test
        void givenANewMember_WhenEmailIsNull_ThenBadRequestIsThrown() {
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
        void givenANewMember_WhenEmailIsEmpty_ThenBadRequestIsThrown() {
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
        void givenANewMember_WhenEmailIsBlank_ThenBadRequestIsThrown() {
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
    }

    @Nested
    @DisplayName("Email format tests")
    class EmailFormatTests {
        @Test
        void givenANewMember_WhenEmailFormatIsCorrect_ThenMemberIsCreated() {
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
        void givenANewMember_WhenEmailFormatIsWrongSpecialCharacter_ThenBadRequestIsThrown() {
            RegisterMemberDto registerMemberDto = new RegisterMemberDto( "Bugs",
                    "gfsssf@dg.d_s",
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
        void givenANewMember_WhenEmailFormatIsWrongNoAtNoDot_ThenBadRequestIsThrown() {
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
        void givenANewMember_WhenEmailFormatIsWrongNoAt_ThenBadRequestIsThrown() {
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
        void givenANewMember_WhenEmailFormatIsWrongNoDot_ThenBadRequestIsThrown() {
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
        void givenANewMember_WhenEmailFormatIsWrongDotBeforeAt_ThenBadRequestIsThrown() {
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
    }

    @Nested
    @DisplayName("Empty city name tests")
    class CityTests {
        @Test
        void givenANewMember_WhenCityIsNull_ThenBadRequestIsThrown() {
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
        void givenANewMember_WhenCityIsEmpty_ThenBadRequestIsThrown() {
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
        void givenANewMember_WhenCityIsBlank_ThenBadRequestIsThrown() {
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
    }

    @Nested
    @DisplayName("Empty last name tests")
    class LastNameTests {
        @Test
        void givenANewMember_WhenLastNameIsNull_ThenBadRequestIsThrown() {
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
        void givenANewMember_WhenLastNameIsEmpty_ThenBadRequestIsThrown() {
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
        void givenANewMember_WhenLastNameIsBlank_ThenBadRequestIsThrown() {
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

}