package com.switchfully.digibuggy.members;

import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public MemberDto registerMember(RegisterMemberDto registerMemberDto) {



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
