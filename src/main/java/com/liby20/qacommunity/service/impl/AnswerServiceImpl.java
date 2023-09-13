package com.liby20.qacommunity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liby20.qacommunity.entity.Answer;
import com.liby20.qacommunity.exception.ServiceException;
import com.liby20.qacommunity.exception.UserException;
import com.liby20.qacommunity.handler.ExceptionType;
import com.liby20.qacommunity.mapper.AnswerMapper;
import com.liby20.qacommunity.service.AnswerService;
import com.liby20.qacommunity.service.QuestionService;
import com.liby20.qacommunity.service.UserService;
import com.liby20.qacommunity.vo.AnswerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @Override
    public void addAnswer(Integer questionId, AnswerVo answerVo) {
        answerVo.setQuestionId(questionId);
        if (questionService.getQuestionById(questionId) == null)
            throw new ServiceException(ExceptionType.QUESTION_NOT_FOUND);
        if (userService.getUserById(answerVo.getUserId()) == null)
            throw new UserException(ExceptionType.USER_NOT_FOUND);
        if (answerVo.getContent().trim().isEmpty())
            throw new ServiceException(ExceptionType.ANSWER_CONTENT_EMPTY);
        Answer answer = new Answer();
        answer.setUserId(answerVo.getUserId());
        answer.setContent(answerVo.getContent());
        addAnswer(questionId, answer);
    }

    @Override
    public void addAnswer(Integer questionId, Answer answer) {
        answer.setQuestionId(questionId);
        answerMapper.insert(answer);
    }

    @Override
    public List<Answer> getPagedAnswersByQuestionId(Integer questionId, Integer page, Integer size) {
        if (questionService.getQuestionById(questionId) == null)
            throw new ServiceException(ExceptionType.QUESTION_NOT_FOUND);
        if (getAnswersCountByQuestionId(questionId) == 0)
            throw new ServiceException(ExceptionType.ANSWER_LIST_EMPTY);
        if (isValidPage(questionId, page, size))
            throw new ServiceException(ExceptionType.INVALID_PAGE);
        Page<Answer> answerPage = new Page<>(page, size);
        QueryWrapper<Answer> condition = new QueryWrapper<>();
        condition.eq("question_id", questionId);
        IPage<Answer> pg = answerMapper.selectPage(answerPage, condition);
        return pg.getRecords();
    }

    @Override
    public long getAnswersCountByQuestionId(Integer questionId) {
        return answerMapper.getAnswersCountByQuestionId(questionId);
    }

    public boolean isValidPage(Integer questionId, Integer page, Integer size) {
        long total = getAnswersCountByQuestionId(questionId);
        long totalPages;
        if (total % size == 0) totalPages = total / size;
        else totalPages = total / size + 1;
        return page > totalPages || page < 1;
    }

    public boolean isLastPage(Integer questionId, Integer page, Integer size) {
        long total = getAnswersCountByQuestionId(questionId);
        long totalPages;
        if (total % size == 0) totalPages = total / size;
        else totalPages = total / size + 1;
        return page == totalPages;
    }

    @Override
    public Answer getAnswerById(Integer answerId) {
        return answerMapper.selectById(answerId);
    }
}

