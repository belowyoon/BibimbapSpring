package com.springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberNotExistException extends RuntimeException {
    public MemberNotExistException(String message) {
        super(message);
    }
}
