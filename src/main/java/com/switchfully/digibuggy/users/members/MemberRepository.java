package com.switchfully.digibuggy.users.members;

import com.switchfully.digibuggy.users.members.Member;
import com.switchfully.digibuggy.users.members.MemberDatabase;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberRepository {

    private final MemberDatabase memberDatabase;


    public MemberRepository(MemberDatabase memberDatabase) {
        this.memberDatabase = memberDatabase;
    }

    public Member registerMember(Member registerMember) {
        return memberDatabase.save(registerMember);
    }

    public Optional<Member> getByInss(String inss) {
        return memberDatabase.getByInss(inss);
    }

    public Optional<Member> getByEmail(String emailAddress) {
        return memberDatabase.getByEmail(emailAddress);
    }
}
