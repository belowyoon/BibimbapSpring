package com.springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostsNotExistException extends RuntimeException{
    public PostsNotExistException(String message) {
        super(message);
    }
}
