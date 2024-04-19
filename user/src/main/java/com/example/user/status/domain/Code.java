package com.example.user.status.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum Code {

    SUCCESS_JOIN(201, HttpStatus.OK, "회원가입 성공하였습니다."),
    FAIL_JOIN(401, HttpStatus.BAD_REQUEST, "회원가입 실패하였습니다. 다시 시도하여 주십시오."),
    SUCCESS_GET_MEMBER_LIST(200, HttpStatus.OK, "멤버 리스트를 가져왔습니다."),
    FAIL_GET_MEMBER_LIST(400, HttpStatus.BAD_REQUEST, "멤버 리스트를 가져오지 못했습니다."),
    SUCCESS_UPDATE_MEMBER(202, HttpStatus.OK, "멤버 정보를 업데이트하였습니다."),
    FAIL_UPDATE_MEMBER(402, HttpStatus.BAD_REQUEST, "멤버 정보를 업데이트하지 못하였습니다."),
    EXIST_USER(405, HttpStatus.BAD_REQUEST, "존재하는 멤버입니다."),
    NON_EXIST_USER(204, HttpStatus.OK, "존재하지 않는 멤버입니다."),
    BAD_REQUEST(403, HttpStatus.BAD_REQUEST,"bad request"),
    INTERNAL_ERROR(404, HttpStatus.INTERNAL_SERVER_ERROR, "Internal error"),
    OK(203, HttpStatus.OK, "Ok");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;

    public String getMessage(Throwable e) {
        return this.getMessage(this.getMessage() + " - " + e.getMessage());
    }

    public String getMessage(String message) {
        return Optional.ofNullable(message)
                .filter(m -> !m.isEmpty())
                .orElse(this.getMessage());
    }

    public static Code valueOf(HttpStatus httpStatus) {
        if (httpStatus == null) {
            throw new GeneralException("HttpStatus is null.");
        }

        return Arrays.stream(values())
                .filter(errorCode -> errorCode.getHttpStatus() == httpStatus)
                .findFirst()
                .orElseGet(() -> {
                    if (httpStatus.is4xxClientError()) {
                        return Code.BAD_REQUEST;
                    } else if (httpStatus.is5xxServerError()) {
                        return Code.INTERNAL_ERROR;
                    } else {
                        return Code.OK;
                    }
                });
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", this.name(), this.getCode());
    }
}
