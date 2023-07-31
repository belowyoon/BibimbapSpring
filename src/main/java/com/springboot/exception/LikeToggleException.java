package com.springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LikeToggleException extends RuntimeException{
    public LikeToggleException(String message) {
        super(message);
    }
}
