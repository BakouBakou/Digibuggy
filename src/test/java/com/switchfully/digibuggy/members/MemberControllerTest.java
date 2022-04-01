package com.switchfully.digibuggy.members;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MemberControllerTest<port> {

    @LocalServerPort
     private int port;

    @Test
    void GivenANewMemberWhenYouRegisterAMemberTheMemberIsAddedInTheDatabaseClass() {
        RegisterMemberDto newMember = new RegisterMemberDto()
                .setINSS("9876543216541")
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
                            .body(newMember)
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
        Assertions.assertThat(memberDto.getINSS()).isEqualTo(newMember.getINSS());
        Assertions.assertThat(memberDto.getEmailAddress()).isEqualTo(newMember.getEmailAddress());
        Assertions.assertThat(memberDto.getFirstName()).isEqualTo(newMember.getFirstName());
        Assertions.assertThat(memberDto.getLastName()).isEqualTo(newMember.getLastName());
        Assertions.assertThat(memberDto.getStreetName()).isEqualTo(newMember.getStreetName());
        Assertions.assertThat(memberDto.getStreetNumber()).isEqualTo(newMember.getStreetNumber());
        Assertions.assertThat(memberDto.getPostalCode()).isEqualTo(newMember.getPostalCode());
        Assertions.assertThat(memberDto.getCityName()).isEqualTo(newMember.getCityName());

    }
}