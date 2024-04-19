package com.example.user.member.controller;

import com.example.user.member.domain.ListDTO;
import com.example.user.member.domain.MemberDTO;
import com.example.user.member.domain.MemberUpdateDTO;
import com.example.user.member.domain.ResponseMemberDTO;
import com.example.user.member.domain.entity.Member;
import com.example.user.member.service.MemberService;
import com.example.user.status.domain.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/join")
    public ResponseDTO join(@Valid @RequestBody MemberDTO memberDTO){
        return memberService.join(memberDTO);
    }

    @GetMapping("/list")
    public Page<ResponseMemberDTO<Member>> list(@Valid @RequestBody ListDTO listDTO){
        return memberService.list(listDTO);
    }

    @PatchMapping("/{userId}")
    public ResponseDTO update(@PathVariable String userId, @Valid @RequestBody MemberUpdateDTO memberUpdateDTO){
        return memberService.updateUser(userId, memberUpdateDTO);
    }
}
