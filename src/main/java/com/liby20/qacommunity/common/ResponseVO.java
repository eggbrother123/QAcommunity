package com.liby20.qacommunity.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class ResponseVO<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public ResponseVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseVO(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ResponseVO(ResponseStatusEnum resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMsg();
        this.data = data;
    }

    public static <T> ResponseVO<T> success() {
        return new ResponseVO<T>(ResponseStatusEnum.SUCCESS, null);
    }


    public static <T> ResponseVO<T> success(T data) {
        return new ResponseVO<T>(ResponseStatusEnum.SUCCESS, data);
    }

    public static <T> ResponseVO<T> success(ResponseStatusEnum resultStatus, T data) {
        if (resultStatus == null) {
            return success(data);
        }
        return new ResponseVO<T>(resultStatus, data);
    }

    public static <T> ResponseVO<T> success(Integer code, String msg) {
        return new ResponseVO<T>(code, msg);
    }

    public static <T> ResponseVO<T> success(Integer code, String msg, T data) {
        return new ResponseVO<T>(code, msg, data);
    }

    public static <T> ResponseVO<T> failure() {
        return new ResponseVO<>(ResponseStatusEnum.FAILURE, null);
    }

    public static <T> ResponseVO<T> failure(ResponseStatusEnum resultStatus) {
        return failure(resultStatus, null);
    }

    public static <T> ResponseVO<T> failure(ResponseStatusEnum resultStatus, T data) {
        if (resultStatus == null) {
            return new ResponseVO<T>(ResponseStatusEnum.FAILURE, null);
        }
        return new ResponseVO<T>(resultStatus, data);
    }

    public static <T> ResponseVO<T> failure(Integer code, String msg) {
        return new ResponseVO<T>(code, msg);
    }
}

