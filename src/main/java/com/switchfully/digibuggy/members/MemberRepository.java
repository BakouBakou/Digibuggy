package com.switchfully.digibuggy.members;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    private final MemberDatabase memberDatabase;


    public MemberRepository(MemberDatabase memberDatabase) {
        this.memberDatabase = memberDatabase;
    }

    public Member registerMember(Member registerMember) {
        return memberDatabase.save(registerMember);
    }
}
