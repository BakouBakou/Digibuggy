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
        return new MemberDto(memberToRegister.getId(),
                memberToRegister.getInss(),
                memberToRegister.getEmailAddress(),
                memberToRegister.getFirstName(),
                memberToRegister.getLastName(),
                memberToRegister.getStreetName(),
                memberToRegister.getStreetNumber(),
                memberToRegister.getPostalCode(),
                memberToRegister.getCityName());
    }
}
