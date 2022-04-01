package com.switchfully.digibuggy.members;

import com.switchfully.digibuggy.members.dtos.MemberDto;
import com.switchfully.digibuggy.members.dtos.RegisterMemberDto;
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
class MemberControllerIntegrationTest<port> {

    @LocalServerPort
     private int port;

    @Test
    void GivenANewMemberWhenYouRegisterAMemberTheMemberIsAddedInTheDatabaseClass() {
        RegisterMemberDto registerMemberDto = new RegisterMemberDto()
                .setInss("9876543216541")
                .setEmailAddress("bugs@bunny.com")
                .setFirstName("bugs")
                .setLastName("bunny")
                .setStreetName("Looney tunes street")
                .setStreetNumber("1")
                .setPostalCode("1000")
                .setCityName("Looney tunes city");

        MemberDto memberDto =
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
                            .statusCode(HttpStatus.CREATED.value())
                            .extract()
                            .as(MemberDto.class);

        Assertions.assertThat(memberDto.getId()).isNotBlank().isNotEmpty().isNotNull();
        Assertions.assertThat(memberDto.getInss()).isEqualTo(registerMemberDto.getInss());
        Assertions.assertThat(memberDto.getEmailAddress()).isEqualTo(registerMemberDto.getEmailAddress());
        Assertions.assertThat(memberDto.getFirstName()).isEqualTo(registerMemberDto.getFirstName());
        Assertions.assertThat(memberDto.getLastName()).isEqualTo(registerMemberDto.getLastName());
        Assertions.assertThat(memberDto.getStreetName()).isEqualTo(registerMemberDto.getStreetName());
        Assertions.assertThat(memberDto.getStreetNumber()).isEqualTo(registerMemberDto.getStreetNumber());
        Assertions.assertThat(memberDto.getPostalCode()).isEqualTo(registerMemberDto.getPostalCode());
        Assertions.assertThat(memberDto.getCityName()).isEqualTo(registerMemberDto.getCityName());

    }
}