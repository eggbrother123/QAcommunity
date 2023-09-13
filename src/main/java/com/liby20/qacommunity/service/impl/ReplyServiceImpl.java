package com.liby20.qacommunity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liby20.qacommunity.entity.Reply;
import com.liby20.qacommunity.exception.ServiceException;
import com.liby20.qacommunity.exception.UserException;
import com.liby20.qacommunity.handler.ExceptionType;
import com.liby20.qacommunity.mapper.ReplyMapper;
import com.liby20.qacommunity.service.AnswerService;
import com.liby20.qacommunity.service.ReplyService;
import com.liby20.qacommunity.service.UserService;
import com.liby20.qacommunity.vo.ReplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    ReplyMapper replyMapper;

    @Autowired
    AnswerService answerService;

    @Autowired
    UserService userService;

    @Override
    public void addReply(Integer answerId, ReplyVo replyVo) {
        replyVo.setAnswerId(answerId);
        if (answerService.getAnswerById(answerId) == null)
            throw new ServiceException(ExceptionType.ANSWER_NOT_FOUND);
        if (userService.getUserById(replyVo.getUserId()) == null)
            throw new UserException(ExceptionType.USER_NOT_FOUND);
        if (replyVo.getContent().trim().isEmpty())
            throw new ServiceException(ExceptionType.REPLY_CONTENT_EMPTY);
        Reply reply = new Reply();
        reply.setUserId(replyVo.getUserId());
        reply.setContent(replyVo.getContent());
        addReply(answerId, reply);
    }

    @Override
    public void addReply(Integer answerId, Reply reply) {
        reply.setAnswerId(answerId);
        replyMapper.insert(reply);
    }

    @Override
    public List<Reply> getPagedRepliesByAnswerId(Integer answerId, Integer page, Integer size) {
        if (answerService.getAnswerById(answerId) == null)
            throw new ServiceException(ExceptionType.ANSWER_NOT_FOUND);
        if (getRepliesCountByAnswerId(answerId) == 0)
            throw new ServiceException(ExceptionType.REPLY_LIST_EMPTY);
        if (isValidPage(answerId, page, size))
            throw new ServiceException(ExceptionType.INVALID_PAGE);
        Page<Reply> replyPage = new Page<>(page, size);
        QueryWrapper<Reply> condition = new QueryWrapper<>();
        condition.eq("answer_id", answerId);
        Page<Reply> pg = replyMapper.selectPage(replyPage, condition);
        return pg.getRecords();
    }

    @Override
    public int getRepliesCountByAnswerId(Integer answerId) {
        return replyMapper.getRepliesCountByAnswerId(answerId);
    }

    @Override
    public boolean isValidPage(Integer answerId, Integer page, Integer size) {
        int total = getRepliesCountByAnswerId(answerId);
        int totalPages;
        if (total % size == 0) totalPages = total / size;
        else totalPages = total / size + 1;
        return page < 1 || page > totalPages;
    }

    @Override
    public boolean isLastPage(Integer answerId, Integer page, Integer size) {
        int total = getRepliesCountByAnswerId(answerId);
        int totalPages;
        if (total % size == 0) totalPages = total / size;
        else totalPages = total / size + 1;
        return page == totalPages;
    }
}
