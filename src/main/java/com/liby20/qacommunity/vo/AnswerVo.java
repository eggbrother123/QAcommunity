package com.liby20.qacommunity.vo;

import lombok.Data;

@Data
public class AnswerVo {
    private int UserId;
    private int QuestionId;
    private String content;
}
