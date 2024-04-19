package com.example.user.status.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ResponseDTO {

    private final Integer code;
    private final String message;

    @JsonCreator
    public static ResponseDTO create(@JsonProperty("code") Integer code, @JsonProperty("message") String message) {
        return new ResponseDTO(code, message);
    }

    public static ResponseDTO of(Code code) {
        return new ResponseDTO(code.getCode(), code.getMessage());
    }

    public static ResponseDTO of(Code errorCode, Exception e) {
        return new ResponseDTO(errorCode.getCode(), errorCode.getMessage(e));
    }

    public static ResponseDTO of(Code errorCode, String message) {
        return new ResponseDTO(errorCode.getCode(), errorCode.getMessage(message));
    }
}
