package com.liby20.qacommunity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    @TableId
    private int replyId;
    private int userId;
    private int answerId;
    private String content;
    private Date createTime;
}
