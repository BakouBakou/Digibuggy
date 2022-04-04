package com.switchfully.digibuggy.members;

import com.switchfully.digibuggy.members.dtos.MemberDto;
import com.switchfully.digibuggy.members.dtos.RegisterMemberDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/members")
public class MemberController {

    public static final String INSS_CANNOT_BE_EMPTY = "The INSS cannot be empty";
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto registerMember(@RequestBody RegisterMemberDto registerMemberDto) {
            return memberService.registerMember(registerMemberDto);
        }
        catch (IllegalArgumentException e) {
            logger.error(INSS_CANNOT_BE_EMPTY);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INSS_CANNOT_BE_EMPTY);
        }

    }


}
