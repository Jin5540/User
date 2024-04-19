package com.example.user.member.domain;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MemberDTO {

    @Pattern(regexp = "^[a-z]{1}[a-z0-9]{4,10}+$", message = "아이디는 영문과 숫자를 조합하여 4~10자리로 입력하여 주십시오.")
    @NotBlank(message = "아이디를 입력해주십시오")
    private String userId;

    @Pattern(regexp = "^[a-z]{1}[a-z0-9]{6,15}+$", message = "비밀번호는 영문과 숫자를 조합하여 6~15자리로 입력하여 주십시오.")
    @NotBlank(message = "비밀번호를 입력해주십시오")
    private String password;

    @Pattern(regexp = "^[ㄱ-ㅎ|가-힣]{2,8}+$", message = "닉네임은 한글만 가능하며 2~8자리로 입력하여 주십시오.")
    @NotBlank(message = "닉네임을 입력해주십시오")
    private String nickName;

    @Email(message = "이메일 형식에 어긋납니다. 다시 확인하여 주십시오.")
    @NotBlank(message = "이메일을 입력해주십시오")
    private String email;

    @Pattern(regexp = "^[ㄱ-ㅎ|가-힣]{2,8}+$", message = "성함은 한글만 가능하며 2~8자리로 입력하여 주십시오.")
    @NotBlank(message = "성함을 입력해주십시오")
    private String name;

    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "휴대폰 번호는 하이픈을 포함하여 입력하여 주십시오.")
    @NotBlank(message = "휴대폰 번호를 입력해주십시오")
    private String phoneNumber;
}
