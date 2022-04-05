package com.switchfully.digibuggy.users.members;

import com.switchfully.digibuggy.users.members.Member;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemberDatabase {

    private final ConcurrentHashMap<String, Member> membersById;

    public MemberDatabase() {
        this.membersById = new ConcurrentHashMap<>();
    }

    public Member save(Member member) {
        membersById.put(member.getId(), member);
        return membersById.get(member.getId());
    }

    public Optional<Member> getByInss(String inss) {

        return membersById.values()
                .stream()
                .filter(member -> member.getInss().equals(inss))
                .findAny();
    }

    public Optional<Member> getByEmail(String emailAddress) {
        return membersById.values()
                .stream()
                .filter(member -> member.getEmailAddress().equals(emailAddress))
                .findAny();
    }
}
