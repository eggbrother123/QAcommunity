package com.liby20.qacommunity.handler;

import lombok.Getter;

@Getter
public enum ExceptionType {
    USER_NOT_FOUND(1000, "用户不存在"),
    USERNAME_OR_PASSWORD_EMPTY(1001, "用户名或密码为空"),
    USERNAME_DUPLICATED(1002, "用户名重复"),
    PASSWORD_DUPLICATED(1003, "密码和原来一致"),
    QUESTION_TITLE_EMPTY(1004, "问题标题为空"),
    QUESTION_NOT_FOUND(1005, "问题不存在"),
    INVALID_PAGE(1006, "查询页码范围错误"),
    ANSWER_CONTENT_EMPTY(1007, "答案内容为空"),
    ANSWER_LIST_EMPTY(1008, "答案列表为空"),
    ANSWER_NOT_FOUND(1009, "答案不存在"),
    REPLY_CONTENT_EMPTY(1010, "回复内容为空"),
    REPLY_LIST_EMPTY(1011, "回复列表为空"),
    SYSTEM_ERROR(2000, "系统异常");

    private final int Code;
    private final String Msg;

    ExceptionType(int Code, String Msg) {
        this.Code = Code;
        this.Msg = Msg;
    }

}
