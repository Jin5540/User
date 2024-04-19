package com.example.user.member.service;

import com.example.user.member.domain.ListDTO;
import com.example.user.member.domain.MemberDTO;
import com.example.user.member.domain.MemberUpdateDTO;
import com.example.user.member.domain.ResponseMemberDTO;
import com.example.user.member.domain.entity.Member;
import com.example.user.status.domain.ResponseDTO;
import org.springframework.data.domain.Page;

public interface MemberService {

    public ResponseDTO join(MemberDTO memberDTO);
    public ResponseDTO checkUser(MemberDTO memberDTO);
    public Page<ResponseMemberDTO<Member>> list(ListDTO listDTO);
    public ResponseDTO updateUser(String userId, MemberUpdateDTO memberUpdateDTO);
}
