package com.liby20.qacommunity.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.util.StringUtil;
import com.liby20.qacommunity.entity.Question;
import com.liby20.qacommunity.exception.ServiceException;
import com.liby20.qacommunity.exception.UserException;
import com.liby20.qacommunity.handler.ExceptionType;
import com.liby20.qacommunity.mapper.QuestionMapper;
import com.liby20.qacommunity.service.QuestionService;
import com.liby20.qacommunity.service.UserService;
import com.liby20.qacommunity.vo.QuestionInfo;
import com.liby20.qacommunity.vo.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserService userService;

    @Override
    public void addQuestion(Question question) {
        questionMapper.insert(question);
    }

    @Override
    public void addQuestion(QuestionVo questionVo) {
        if (StringUtil.isEmpty(questionVo.getTitle()))
            throw new ServiceException(ExceptionType.QUESTION_TITLE_EMPTY);
        if (userService.getUserById(questionVo.getUserId()) == null)
            throw new UserException(ExceptionType.USER_NOT_FOUND);
        Question question = new Question();
        question.setUserId(questionVo.getUserId());
        question.setTitle(questionVo.getTitle());
        question.setContent(questionVo.getContent());
        addQuestion(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        List<Question> questions = questionMapper.selectList(null);
        if (questions.isEmpty())
            throw new ServiceException(ExceptionType.QUESTION_NOT_FOUND);
        return questions;
    }

    @Override
    public QuestionInfo getQuestionInfoByTitle(String title) {
        if (null == getQuestionByTitle(title))
            throw new ServiceException(ExceptionType.QUESTION_NOT_FOUND);
        QuestionInfo info = new QuestionInfo();
        info.setContent(getQuestionByTitle(title).getContent());
        info.setUserName(getUserNameByTitle(title));
        info.setAnswerCount(getAnswerCountByTitle(title));
        return info;
    }

    @Override
    public QuestionInfo getQuestionInfoById(Integer questionId) {
        if (getQuestionById(questionId) == null)
            throw new ServiceException(ExceptionType.QUESTION_NOT_FOUND);
        String title = getQuestionById(questionId).getTitle();
        return getQuestionInfoByTitle(title);
    }

    private long getAnswerCountByTitle(String title) {
        return questionMapper.selectAnswerCountByTitle(title);
    }

    public Question getQuestionByTitle(String title) {
        return questionMapper.selectQuestionByTitle(title);
    }

    @Override
    public Question getQuestionById(Integer questionId) {
        return questionMapper.selectById(questionId);
    }

    @Override
    public void deleteById(Integer questionId) {
        if (getQuestionById(questionId) == null)
            throw new ServiceException(ExceptionType.QUESTION_NOT_FOUND);
        questionMapper.deleteById(questionId);
    }

    @Override
    public void updateQuestion(Question question) {
        questionMapper.updateById(question);
    }

    @Override
    public void updateQuestion(Integer questionId, QuestionVo questionVo) {
        Question question = getQuestionById(questionId);
        if (question == null)
            throw new ServiceException(ExceptionType.QUESTION_NOT_FOUND);
        if (questionVo.getTitle().trim().isEmpty())
            throw new ServiceException(ExceptionType.QUESTION_TITLE_EMPTY);
        question.setTitle(questionVo.getTitle());
        question.setContent(questionVo.getContent());
        updateQuestion(question);
    }

    @Override
    public List<Question> getPagedQuestions(Integer page, Integer size) {
        if (isInvalidPage(page, size))
            throw new ServiceException(ExceptionType.INVALID_PAGE);
        Page<Question> pg = new Page<>(page, size);
        IPage<Question> questionPage = questionMapper.selectPage(pg, null);
        List<Question> records = questionPage.getRecords();
        return records;
    }

    @Override
    public long getQuestionsCount() {
        return questionMapper.getQuestionsCount();
    }


    public String getUserNameByTitle(String title) {
        return questionMapper.selectUserNameByTitle(title);
    }

    @Override
    public boolean isLastPage(Integer page, Integer size) {
        long total = getQuestionsCount();
        long totalPages;
        if (total % size == 0) {
            totalPages = total / size;
        } else totalPages = total / size + 1;
        return page == totalPages;
    }

    @Override
    public boolean isInvalidPage(Integer page, Integer size) {
        long total = getQuestionsCount();
        long totalPages;
        if (total % size == 0) {
            totalPages = total / size;
        } else totalPages = total / size + 1;
        return page > totalPages || page < 1;
    }
}
