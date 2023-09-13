package com.liby20.qacommunity.common;

import lombok.Getter;

@Getter
public enum ResponseStatusEnum {
    SUCCESS(200, "OK"),
    FAILURE(400, "Error");

    private final int Code;
    private final String Msg;

    ResponseStatusEnum(int Code, String Msg) {
        this.Code = Code;
        this.Msg = Msg;
    }


}
