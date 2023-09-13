package com.liby20.qacommunity.service;

import com.liby20.qacommunity.entity.Reply;
import com.liby20.qacommunity.vo.ReplyVo;

import java.util.List;


public interface ReplyService {

    void addReply(Integer answerId, ReplyVo replyVo);

    void addReply(Integer answerId, Reply reply);

    List<Reply> getPagedRepliesByAnswerId(Integer answerId, Integer page, Integer size);

    int getRepliesCountByAnswerId(Integer answerId);

    boolean isValidPage(Integer answerId, Integer page, Integer size);

    boolean isLastPage(Integer answerId, Integer page, Integer size);
}
