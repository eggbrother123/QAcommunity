package com.liby20.qacommunity.handler;

import com.liby20.qacommunity.common.ResponseVO;
import com.liby20.qacommunity.exception.ServiceException;
import com.liby20.qacommunity.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseVO handelerException(Exception e) {
        if (e instanceof UserException) {
            UserException userException = (UserException) e;
            if (userException.getCode() == ExceptionType.USER_NOT_FOUND.getCode())
                log.error("用户不存在：", e);
            else if (userException.getCode() == ExceptionType.USERNAME_DUPLICATED.getCode())
                log.error("用户名重复:", e);
            else if (userException.getCode() == ExceptionType.USERNAME_OR_PASSWORD_EMPTY.getCode())
                log.error("用户名或密码为空:", e);
            else if (userException.getCode() == ExceptionType.PASSWORD_DUPLICATED.getCode())
                log.error("密码和原来一致:", e);
            return ResponseVO.failure(userException.getCode(), userException.getMessage());
        } else if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            if (serviceException.getCode() == ExceptionType.ANSWER_NOT_FOUND.getCode())
                log.error("答案不存在 :", e);
            else if (serviceException.getCode() == ExceptionType.QUESTION_TITLE_EMPTY.getCode())
                log.error("问题标题为空 :", e);
            else if (serviceException.getCode() == ExceptionType.QUESTION_NOT_FOUND.getCode())
                log.error("问题不存在 :", e);
            else if (serviceException.getCode() == ExceptionType.INVALID_PAGE.getCode())
                log.error("查询页码无效 :", e);
            else if (serviceException.getCode() == ExceptionType.ANSWER_LIST_EMPTY.getCode())
                log.error("答案列表为空 :", e);
            else if (serviceException.getCode() == ExceptionType.REPLY_CONTENT_EMPTY.getCode())
                log.error("回复内容为空 :", e);
            else if (serviceException.getCode() == ExceptionType.REPLY_LIST_EMPTY.getCode())
                log.error("回复列表为空 :", e);
            return ResponseVO.failure(serviceException.getCode(), serviceException.getMessage());
        } else {
            log.error("【系统异常】：", e);
            return ResponseVO.failure(ExceptionType.SYSTEM_ERROR.getCode(), ExceptionType.SYSTEM_ERROR.getMsg());
        }
    }

}
