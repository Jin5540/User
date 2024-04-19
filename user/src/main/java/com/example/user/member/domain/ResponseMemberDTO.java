package com.example.user.member.domain;

import com.example.user.status.domain.Code;
import com.example.user.status.domain.ResponseDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseMemberDTO<T> {

    /*
    private long index;
    private String userId;
    private String nickName;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
     */
    private Integer code; // code의 정수값을 저장하기 위한 필드
    private String message; // 메시지를 저장하기 위한 필드
    private T data;

    // 생성자 추가
    public ResponseMemberDTO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
