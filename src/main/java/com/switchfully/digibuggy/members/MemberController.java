package com.switchfully.digibuggy.members;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/members")
public class MemberController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto registerMember(@RequestBody RegisterMemberDto registerMemberDto) {
        return new MemberDto()
                .setINSS(registerMemberDto.getINSS())
                .setEmailAddress(registerMemberDto.getEmailAddress())
                .setFirstName(registerMemberDto.getFirstName())
                .setLastName(registerMemberDto.getLastName())
                .setStreetName(registerMemberDto.getStreetName())
                .setStreetNumber(registerMemberDto.getStreetNumber())
                .setCityName(registerMemberDto.getCityName())
                .setPostalCode(registerMemberDto.getPostalCode());
    }


}
