package com.springboot.exception;

import com.springboot.Domain.member.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(value = MemberNotExistException.class)
    public ResponseEntity<Object> handleMemberNotExistException(MemberNotExistException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiException apiException = ApiException.builder()
                .code(ExceptionCode.MEMBER_NOT_FOUND_EXCEPTION)
                .localDateTime(LocalDateTime.now())
                .build();
        apiException.setDetail(e.getMessage());

        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = PostsNotExistException.class)
    public ResponseEntity<Object> handlePostsNotExistException(PostsNotExistException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiException apiException = ApiException.builder()
                .code(ExceptionCode.POSTS_NOT_FOUND_EXCEPTION)
                .localDateTime(LocalDateTime.now())
                .build();
        apiException.setDetail(e.getMessage());

        return new ResponseEntity<>(apiException, httpStatus);
    }
}
