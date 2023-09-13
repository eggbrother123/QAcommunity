package com.liby20.qacommunity.service;

import com.liby20.qacommunity.entity.Answer;
import com.liby20.qacommunity.vo.AnswerVo;

import java.util.List;


public interface AnswerService {
    void addAnswer(Integer questionId, AnswerVo answerVo);

    void addAnswer(Integer questionId, Answer answer);

    List<Answer> getPagedAnswersByQuestionId(Integer questionId, Integer page, Integer size);

    long getAnswersCountByQuestionId(Integer questionId);

    boolean isValidPage(Integer questionId, Integer page, Integer size);

    boolean isLastPage(Integer questionId, Integer page, Integer size);

    Answer getAnswerById(Integer answerId);

}
