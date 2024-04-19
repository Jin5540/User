package com.example.user.member.domain;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MemberUpdateDTO {

    @Pattern(regexp = "^[a-z]{1}[a-z0-9]{6,20}+$", message = "비밀번호는 영문과 숫자를 조합하여 6~20자리로 입력하여 주십시오.")
    private String password;

    @Pattern(regexp = "^[ㄱ-ㅎ|가-힣]{2,8}+$", message = "닉네임은 한글만 가능하며 2~8자리로 입력하여 주십시오.")
    private String nickName;

    @Pattern(regexp = "^[ㄱ-ㅎ|가-힣]{2,10}+$", message = "성함은 한글만 가능하며 2~10자리로 입력하여 주십시오.")
    private String name;

}
