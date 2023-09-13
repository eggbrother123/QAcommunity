package com.liby20.qacommunity.controller;

import com.liby20.qacommunity.common.ResponseVO;
import com.liby20.qacommunity.entity.Answer;
import com.liby20.qacommunity.service.AnswerService;
import com.liby20.qacommunity.vo.AnswerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    //在一个指定的问题下添加答案
    @PostMapping("/questionId={questionId}")
    public ResponseVO<AnswerVo> addAnswer(@PathVariable Integer questionId, @RequestBody AnswerVo answerVo) {
        answerService.addAnswer(questionId, answerVo);
        return ResponseVO.success(answerVo);
    }

    //    分页查看某一个问题下的答案列表
    @GetMapping("/questionId={questionId}/paged")
    public ResponseVO getPagedAnswersByQuestion(@PathVariable Integer questionId,
                                                @RequestParam(defaultValue = "1") Integer page,
                                                @RequestParam(defaultValue = "3") Integer size) {
        List<Answer> answerLists = answerService.getPagedAnswersByQuestionId(questionId, page, size);
        if (answerService.isLastPage(questionId, page, size))
            return ResponseVO.success(200, "当前为最后一页", answerLists);
        return ResponseVO.success(answerLists);
    }

}
