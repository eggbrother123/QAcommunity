package com.liby20.qacommunity.service.impl;

import com.liby20.qacommunity.entity.User;
import com.liby20.qacommunity.exception.UserException;
import com.liby20.qacommunity.handler.ExceptionType;
import com.liby20.qacommunity.mapper.UserMapper;
import com.liby20.qacommunity.service.UserService;
import com.liby20.qacommunity.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void addUser(UserVo userVo) {
        if (userVo.getUserName().trim().isEmpty() || userVo.getPassWord().trim().isEmpty())
            throw new UserException(ExceptionType.USERNAME_OR_PASSWORD_EMPTY);
        if (getUserByUserName(userVo.getUserName()) != null)
            throw new UserException(ExceptionType.USERNAME_DUPLICATED);
        User user = new User();
        user.setUserName(userVo.getUserName());
        user.setPassWord(userVo.getPassWord());
        addUser(user);
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public UserVo getUserVoById(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user == null)
            throw new UserException(ExceptionType.USER_NOT_FOUND);
        UserVo userVo = new UserVo();
        userVo.setUserName(user.getUserName());
        userVo.setPassWord(user.getPassWord());
        return userVo;
    }

    @Override
    public void deleteById(Integer userId) {
        if (getUserById(userId) == null)
            throw new UserException(ExceptionType.USER_NOT_FOUND);
        userMapper.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userMapper.selectList(null);
        if (userList.isEmpty())
            throw new UserException(ExceptionType.USER_NOT_FOUND);
        return userList;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void updateUser(Integer userId, UserVo userVo) {
        User user = getUserById(userId);
        if (user == null)
            throw new UserException(ExceptionType.USER_NOT_FOUND);
        if (getUserByUserName(userVo.getUserName()) != null)
            throw new UserException(ExceptionType.USERNAME_DUPLICATED);
        if (userVo.getUserName().trim().isEmpty() || userVo.getPassWord().trim().isEmpty())
            throw new UserException(ExceptionType.USERNAME_OR_PASSWORD_EMPTY);
        if (user.getPassWord().equals(userVo.getPassWord()))
            throw new UserException(ExceptionType.PASSWORD_DUPLICATED);
        user.setUserName(userVo.getUserName());
        user.setPassWord(userVo.getPassWord());
        updateUser(user);
    }


}
