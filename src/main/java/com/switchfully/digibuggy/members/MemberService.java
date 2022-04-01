package com.switchfully.digibuggy.members;

import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public MemberDto registerMember(RegisterMemberDto registerMemberDto) {

        Member memberToRegister = memberMapper.toMember(registerMemberDto);
        Member registeredMember = memberRepository.registerMember(memberToRegister);

        return memberMapper.toDto(registeredMember);
    }
}
