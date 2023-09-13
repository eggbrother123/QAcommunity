package com.liby20.qacommunity.controller;

import com.liby20.qacommunity.common.ResponseStatusEnum;
import com.liby20.qacommunity.common.ResponseVO;
import com.liby20.qacommunity.entity.Reply;
import com.liby20.qacommunity.service.AnswerService;
import com.liby20.qacommunity.service.ReplyService;
import com.liby20.qacommunity.service.UserService;
import com.liby20.qacommunity.vo.ReplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies")
public class ReplyController {
    @Autowired
    ReplyService replyService;

    @Autowired
    Reply reply;

    @Autowired
    UserService userService;

    @Autowired
    AnswerService answerService;


    //    在一个指定的答案下添加回复
    @PostMapping("/answerId={answerId}")
    public ResponseVO<ReplyVo> addReply(@PathVariable Integer answerId, @RequestBody ReplyVo replyVo) {
        replyService.addReply(answerId, replyVo);
        return ResponseVO.success(ResponseStatusEnum.SUCCESS, replyVo);
    }

    //    分页查看某一个答案下的回复列表
    @GetMapping("answerId={answerId}/paged")
    public ResponseVO<List<Reply>> getPagedRepliesByAnswer(@PathVariable Integer answerId,
                                                           @RequestParam(defaultValue = "1") Integer page,
                                                           @RequestParam(defaultValue = "2") Integer size) {
        List<Reply> replyLists = replyService.getPagedRepliesByAnswerId(answerId, page, size);
        if (replyService.isLastPage(answerId, page, size))
            return ResponseVO.success(200, "当前为最后一页", replyLists);
        return ResponseVO.success(replyLists);
    }
}
