package com.liby20.qacommunity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liby20.qacommunity.entity.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface AnswerMapper extends BaseMapper<Answer> {

    @Select("SELECT COUNT(answer_id) FROM answer WHERE question_id = #{question_id}")
    long getAnswersCountByQuestionId(@Param("question_id") Integer questionId);

}
