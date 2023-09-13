package com.liby20.qacommunity.controller;

import com.liby20.qacommunity.common.ResponseStatusEnum;
import com.liby20.qacommunity.common.ResponseVO;
import com.liby20.qacommunity.entity.Question;
import com.liby20.qacommunity.service.QuestionService;
import com.liby20.qacommunity.vo.QuestionInfo;
import com.liby20.qacommunity.vo.QuestionVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestControllerAdvice
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;


    //    提出一个问题
    @PostMapping
    public ResponseVO<QuestionVo> addQuestion(@RequestBody QuestionVo questionVo) {
        questionService.addQuestion(questionVo);
        log.info("发布问题成功！");
        return ResponseVO.success(ResponseStatusEnum.SUCCESS, questionVo);
    }

    //    删除一个问题
    @DeleteMapping("/{questionId}")
    public ResponseVO<QuestionVo> deleteQuestion(@PathVariable Integer questionId) {
        questionService.deleteById(questionId);
        log.info("删除问题成功！");
        return ResponseVO.success();
    }

    //   修改一个问题
    @PutMapping("/{questionId}")
    public ResponseVO<QuestionVo> updateQuestion(@PathVariable Integer questionId, @RequestBody QuestionVo questionVo) {
        questionService.updateQuestion(questionId, questionVo);
        log.info("修改问题成功！");
        return ResponseVO.success(200, "问题修改成功");
    }

    //   根据问题标题查询问题内容,提问的用户名和回答数目
    @GetMapping("/title")
    public ResponseVO<QuestionInfo> getQuestionInfoByTitle(@Param("title") String title) {
        QuestionInfo info = questionService.getQuestionInfoByTitle(title);
        return ResponseVO.success(info);
    }

    //   根据问题编号查询问题内容,提问的用户名和回答数目
    @GetMapping("/{questionId}")
    public ResponseVO<QuestionInfo> getQuestionByQuestionId(@PathVariable Integer questionId) {
        QuestionInfo info = questionService.getQuestionInfoById(questionId);
        return ResponseVO.success(info);
    }

    //   查看所有问题
    @GetMapping
    public ResponseVO<List<Question>> getAllQuestions() {
        List<Question> allQuestions = questionService.getAllQuestions();
        return ResponseVO.success(allQuestions);
    }

    //    分页查看问题列表
    @GetMapping("/paged")
    public ResponseVO<List<Question>> getPagedQuestions(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        List<Question> pagedQuestions = questionService.getPagedQuestions(page, size);
        if (questionService.isLastPage(page, size)) return ResponseVO.success(200, "当前为最后一页", pagedQuestions);
        return ResponseVO.success(pagedQuestions);
    }
}

