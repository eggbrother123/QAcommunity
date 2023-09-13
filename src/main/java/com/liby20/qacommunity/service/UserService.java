package com.liby20.qacommunity.service;

import com.liby20.qacommunity.entity.User;
import com.liby20.qacommunity.vo.UserVo;

import java.util.List;


public interface UserService {
    void addUser(User user);

    void addUser(UserVo userVo);

    UserVo getUserVoById(Integer userId);

    User getUserById(Integer userId);

    void deleteById(Integer userId);

    List<User> getAllUsers();

    User getUserByUserName(String userName);

    void updateUser(User user);

    void updateUser(Integer userId, UserVo userVo);

}
