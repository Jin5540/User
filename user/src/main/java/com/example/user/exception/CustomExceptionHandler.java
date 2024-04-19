package com.example.user.exception;

import com.example.user.status.domain.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // 유효성 검사 오류의 필드 에러 목록을 가져옵니다.
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        // 오류 메시지를 생성합니다.
        String errorMessage ="";
        if (!fieldErrors.isEmpty()) {
            FieldError firstError = fieldErrors.get(0);
            errorMessage =firstError.getDefaultMessage();
        }

        // 에러 응답 객체를 생성합니다.
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setMessage(errorMessage);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
