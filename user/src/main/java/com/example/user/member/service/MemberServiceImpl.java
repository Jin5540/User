package com.example.user.member.service;

import com.example.user.member.domain.ListDTO;
import com.example.user.member.domain.MemberDTO;
import com.example.user.member.domain.MemberUpdateDTO;
import com.example.user.member.domain.ResponseMemberDTO;
import com.example.user.member.domain.entity.Member;
import com.example.user.member.repository.MemberRepository;
import com.example.user.status.domain.Code;
import com.example.user.status.domain.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import com.example.user.member.domain.SortCheck;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseDTO join(MemberDTO memberDTO) {
        ResponseDTO result = checkUser(memberDTO);

        if(result.getCode().equals(ResponseDTO.of(Code.NON_EXIST_USER).getCode())){
            Member member = new Member();
            member.setUserId(memberDTO.getUserId());
            member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
            member.setNickName(memberDTO.getNickName());
            member.setName(memberDTO.getName());
            member.setEmail(memberDTO.getEmail());
            member.setPhoneNumber(memberDTO.getPhoneNumber());

            memberRepository.save(member);

            return ResponseDTO.of(Code.SUCCESS_JOIN);
        }
        return result;
    }

    @Override
    public ResponseDTO checkUser(MemberDTO memberDTO) {
        String checkUserId = memberDTO.getUserId();
        boolean resultUserId = memberRepository.existsByUserId(checkUserId);
        if(resultUserId){
            return ResponseDTO.of(Code.EXIST_USER, "중복된 아이디입니다. 다시 입력하여 주십시오.");
        }

        String checkEmail = memberDTO.getEmail();
        boolean resultEmail = memberRepository.existsByEmail(checkEmail);
        if(resultEmail){
            return ResponseDTO.of(Code.EXIST_USER, "중복된 이메일입니다. 다시 확인하여 주십시오.");
        }

        String checkPhoneNumber = memberDTO.getPhoneNumber();
        boolean resultPhoneNumber = memberRepository.existsByPhoneNumber(checkPhoneNumber);
        if(resultPhoneNumber){
            return ResponseDTO.of(Code.EXIST_USER, "중복된 휴대폰 번호입니다. 다시 확인하여 주십시오.");
        }

        return ResponseDTO.of(Code.NON_EXIST_USER);
    }

    @Override
    public Page<ResponseMemberDTO<Member>> list(ListDTO listDTO) {
        Page<ResponseMemberDTO<Member>> responsePage;

        if(listDTO.getSort().getSymbol().equals("CREATED_AT")||listDTO.getSort().getSymbol().equals("NAME"))
        {
            Sort sort = Sort.by(Sort.Direction.ASC, listDTO.getSort().getSymbol());
            PageRequest pageable = PageRequest.of(listDTO.getPage(), listDTO.getPageSize(), sort);

            Page<Member> memberPage = memberRepository.findAll(pageable);

            Page<ResponseMemberDTO<Member>> responseMemberPage = memberPage.map(member -> {
                ResponseMemberDTO<Member> responseMemberDTO = new ResponseMemberDTO<>(null, null,null);
                responseMemberDTO.setCode(Code.SUCCESS_GET_MEMBER_LIST.getCode());
                responseMemberDTO.setMessage(Code.SUCCESS_GET_MEMBER_LIST.getMessage());
                responseMemberDTO.setData(member);
                return responseMemberDTO;
            });

            responsePage = responseMemberPage;
        }else {
            ResponseMemberDTO<Member> responseMemberDTO = new ResponseMemberDTO<>(Code.FAIL_GET_MEMBER_LIST.getCode(), Code.FAIL_GET_MEMBER_LIST.getMessage(), null);
            List<ResponseMemberDTO<Member>> contentList = Collections.singletonList(responseMemberDTO);
            responsePage = new PageImpl<>(contentList);
        }
        return responsePage;
    }

    @Override
    public ResponseDTO updateUser(String userId, MemberUpdateDTO memberUpdateDTO) {
        Member existingMember = memberRepository.findByUserId(userId);
        if(existingMember == null){
            return ResponseDTO.of(Code.NON_EXIST_USER,"업데이트 할 유저가 존재하지 않습니다");
        }

        if(memberUpdateDTO.getPassword() != null){
            existingMember.setPassword(passwordEncoder.encode(memberUpdateDTO.getPassword()));
        }

        if(memberUpdateDTO.getNickName() != null){
            existingMember.setNickName(memberUpdateDTO.getNickName());
        }

        if(memberUpdateDTO.getName() != null){
            existingMember.setName(memberUpdateDTO.getName());
        }
        existingMember.setUpdateAt(LocalDateTime.now());

        memberRepository.save(existingMember);

        return ResponseDTO.of(Code.SUCCESS_UPDATE_MEMBER);
    }


}
