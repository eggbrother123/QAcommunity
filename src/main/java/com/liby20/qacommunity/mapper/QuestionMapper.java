package com.liby20.qacommunity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liby20.qacommunity.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    @Select("SELECT content from question WHERE title = #{title}")
    String selectByName(@Param("title") String title);

    @Select("SELECT user.user_name from user WHERE user.user_id = " +
            "(SELECT question.user_id from question WHERE question.title = #{title})")
    String selectUserNameByTitle(@Param("title") String title);

    @Select("SELECT COUNT(question_id) from question ")
    long getQuestionsCount();

    @Select("SELECT count(answer_id) from answer WHERE answer.question_id = " +
            "(SELECT question.question_id from question WHERE question.title = #{title})")
    long selectAnswerCountByTitle(@Param("title") String title);

    @Select("SELECT * from question WHERE title = #{title}")
    Question selectQuestionByTitle(@Param("title") String title);
}
