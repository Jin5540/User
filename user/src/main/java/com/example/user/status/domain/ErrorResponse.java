package com.example.user.status.domain;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String message;
    private String error;
}

