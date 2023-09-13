package com.liby20.qacommunity.vo;

import lombok.Data;

@Data
public class ReplyVo {
    private int userId;
    private int answerId;
    private String content;
}
