package com.liby20.qacommunity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liby20.qacommunity.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReplyMapper extends BaseMapper<Reply> {

    @Select("SELECT COUNT(*) FROM reply WHERE answer_id = #{answerId}")
    int getRepliesCountByAnswerId(@Param("answerId") Integer answerId);
}
