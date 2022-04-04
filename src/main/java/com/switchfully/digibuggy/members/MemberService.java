package com.switchfully.digibuggy.members;

import com.switchfully.digibuggy.members.dtos.MemberDto;
import com.switchfully.digibuggy.members.dtos.RegisterMemberDto;
import com.switchfully.digibuggy.members.exceptions.EmailNotProvidedException;
import com.switchfully.digibuggy.members.exceptions.INSSAlreadyExistsException;
import com.switchfully.digibuggy.members.exceptions.INSSNotProvidedException;
import com.switchfully.digibuggy.members.exceptions.WrongEmailFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public MemberDto registerMember(RegisterMemberDto registerMemberDto) {

        if (registerMemberDto.getInss() == null || registerMemberDto.getInss().isEmpty() || registerMemberDto.getInss().isBlank()) {
            logger.error(new INSSNotProvidedException().getMessage());
            throw new INSSNotProvidedException();
        }

        if (memberRepository.getByInss(registerMemberDto.getInss()).isPresent()) {
            logger.error(new INSSAlreadyExistsException().getMessage());
            throw new INSSAlreadyExistsException();
        }

        if (registerMemberDto.getEmailAddress() == null || registerMemberDto.getEmailAddress().isEmpty() || registerMemberDto.getEmailAddress().isBlank()) {
            logger.error(new EmailNotProvidedException().getMessage());
            throw new EmailNotProvidedException();
        }

        Member memberToRegister = memberMapper.toMember(registerMemberDto);
        Member registeredMember = memberRepository.registerMember(memberToRegister);

        return memberMapper.toDto(registeredMember);
    }
}
