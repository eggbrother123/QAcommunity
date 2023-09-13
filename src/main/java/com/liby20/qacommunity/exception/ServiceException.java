package com.liby20.qacommunity.exception;

import com.liby20.qacommunity.handler.ExceptionType;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private final int code;
    private final String message;

    public ServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException(ExceptionType exceptionType) {
        this(exceptionType.getCode(), exceptionType.getMsg());
    }

}
