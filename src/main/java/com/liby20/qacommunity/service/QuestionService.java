package com.liby20.qacommunity.service;


import com.liby20.qacommunity.entity.Question;
import com.liby20.qacommunity.vo.QuestionInfo;
import com.liby20.qacommunity.vo.QuestionVo;

import java.util.List;

public interface QuestionService {
    //   提出问题
    void addQuestion(Question question);

    void addQuestion(QuestionVo questionVo);

    //  获取所有问题
    List<Question> getAllQuestions();

    //    根据问题标题获取问题信息
    QuestionInfo getQuestionInfoByTitle(String title);

    // 根据问题ID获取问题信息
    QuestionInfo getQuestionInfoById(Integer questionId);

    //分页查询问题列表
    List<Question> getPagedQuestions(Integer page, Integer size);

    //   获得问题总数
    long getQuestionsCount();

    //    根据问题标题获取问题
    Question getQuestionByTitle(String title);

    //    根据问题ID获取问题
    Question getQuestionById(Integer questionId);

    //    根据问题ID删除问题
    void deleteById(Integer questionId);

    //    更新问题
    void updateQuestion(Question question);

    void updateQuestion(Integer questionId, QuestionVo questionVo);

    //    判断是否分页查询页码是否有效
    boolean isInvalidPage(Integer page, Integer size);

    //    判断是否最后一页
    boolean isLastPage(Integer page, Integer size);
}
