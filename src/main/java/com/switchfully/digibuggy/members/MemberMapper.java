package com.switchfully.digibuggy.members;

import com.switchfully.digibuggy.members.dtos.MemberDto;
import com.switchfully.digibuggy.members.dtos.RegisterMemberDto;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member toMember(RegisterMemberDto registerMemberDto) {
        return new Member(
                registerMemberDto.getInss(),
                registerMemberDto.getEmailAddress(),
                registerMemberDto.getFirstName(),
                registerMemberDto.getLastName(),
                registerMemberDto.getStreetName(),
                registerMemberDto.getStreetNumber(),
                registerMemberDto.getPostalCode(),
                registerMemberDto.getCityName()
        );
    }

    public MemberDto toDto(Member memberToRegister) {
        return new MemberDto()
                .setId(memberToRegister.getId())
                .setInss(memberToRegister.getInss())
                .setEmailAddress(memberToRegister.getEmailAddress())
                .setFirstName(memberToRegister.getFirstName())
                .setLastName(memberToRegister.getLastName())
                .setStreetName(memberToRegister.getStreetName())
                .setStreetNumber(memberToRegister.getStreetNumber())
                .setPostalCode(memberToRegister.getPostalCode())
                .setCityName(memberToRegister.getCityName());
    }
}
