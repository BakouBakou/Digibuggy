package com.switchfully.digibuggy.members;

import com.switchfully.digibuggy.members.dtos.MemberDto;
import com.switchfully.digibuggy.members.dtos.RegisterMemberDto;
import com.switchfully.digibuggy.members.exceptions.*;
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

        logger.info("Registering a new member");

        checkForNullBlankOrEmptyExceptions(registerMemberDto.getInss(), new INSSNotProvidedException());

        if (memberRepository.getByInss(registerMemberDto.getInss()).isPresent()) {
            logger.error(new INSSAlreadyExistsException().getMessage());
            throw new INSSAlreadyExistsException();
        }

        checkForNullBlankOrEmptyExceptions(registerMemberDto.getEmailAddress(), new EmailNotProvidedException());

        if (memberRepository.getByEmail(registerMemberDto.getEmailAddress()).isPresent()) {
            logger.error(new EmailAlreadyExistsException().getMessage());
            throw new EmailAlreadyExistsException();
        }

        if (!registerMemberDto.getEmailAddress().matches("^(\\S+)@(\\S+)\\.([a-zA-Z]+)$")) {
            logger.error(new WrongEmailFormatException().getMessage());
            throw new WrongEmailFormatException();
        }

        checkForNullBlankOrEmptyExceptions(registerMemberDto.getLastName(), new LastNameNotProvidedException());

        checkForNullBlankOrEmptyExceptions(registerMemberDto.getCityName(), new CityNameNotProvidedException());

        Member memberToRegister = memberMapper.toMember(registerMemberDto);
        Member registeredMember = memberRepository.registerMember(memberToRegister);

        logger.info("New member has been registered");
        return memberMapper.toDto(registeredMember);
    }

    private boolean isNullBlankOrEmpty(String stringToCheck) {
        return stringToCheck == null || stringToCheck.isEmpty() || stringToCheck.isBlank();
    }

    private void checkForNullBlankOrEmptyExceptions(String stringToCheck, RuntimeException ex) {
        if (isNullBlankOrEmpty(stringToCheck)) {
            logger.error(ex.getMessage());
            throw ex;
        }
    }
}
