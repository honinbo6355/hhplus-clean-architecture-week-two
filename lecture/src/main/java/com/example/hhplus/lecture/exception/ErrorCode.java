package com.example.hhplus.lecture.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    ALREADY_REQUEST(HttpStatus.ALREADY_REPORTED, "100", "이미 요청 중입니다."),
    ALREADY_RESERVED_USER(HttpStatus.BAD_REQUEST, "101", "이미 신청된 유저입니다."),
    ALREADY_MAX_RESERVED_SIZE(HttpStatus.BAD_REQUEST, "102", "이미 정원이 마감되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
