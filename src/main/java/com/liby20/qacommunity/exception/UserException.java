package com.liby20.qacommunity.exception;

import com.liby20.qacommunity.handler.ExceptionType;
import lombok.Getter;


@Getter
public class UserException extends RuntimeException {
    private final int code;
    private final String message;

    public UserException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public UserException(ExceptionType exceptionType) {
        this(exceptionType.getCode(), exceptionType.getMsg());
    }
}
