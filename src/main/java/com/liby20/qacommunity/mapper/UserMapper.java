package com.liby20.qacommunity.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liby20.qacommunity.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM user where user_name = #{userName}")
    User selectByUserName(@Param("userName") String userName);

}
