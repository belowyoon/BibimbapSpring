package com.springboot.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
    MEMBER_NOT_FOUND_EXCEPTION(404, "C001", "해당 멤버가 없습니다."),
    POSTS_NOT_FOUND_EXCEPTION(404, "C002", "해당 게시물이 없습니다."),

    LIKE_NOT_FOUND_EXCEPTION(400,"C003","해당 좋아요 없습니다.");

    private int status;
    private String code;
    private String message;
    private String detail;

    ExceptionCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
