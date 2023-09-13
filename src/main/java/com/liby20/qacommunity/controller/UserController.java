package com.liby20.qacommunity.controller;

import com.liby20.qacommunity.common.ResponseVO;
import com.liby20.qacommunity.entity.User;
import com.liby20.qacommunity.service.UserService;
import com.liby20.qacommunity.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestControllerAdvice
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //    添加一个用户
    @PostMapping
    public ResponseVO<UserVo> addUser(@RequestBody UserVo userVo) {
        userService.addUser(userVo);
        log.info("添加用户成功！");
        return ResponseVO.success();
    }


    //    删除一个用户
    @DeleteMapping("/{userId}")
    public ResponseVO<UserVo> deleteUser(@PathVariable Integer userId) {
        userService.deleteById(userId);
        log.info("删除用户成功！");
        return ResponseVO.success();
    }


    //    更新用户信息
    @PutMapping("/{userId}")
    public ResponseVO<UserVo> updateUser(@PathVariable Integer userId, @RequestBody UserVo userVo) {
        userService.updateUser(userId, userVo);
        log.info("用户信息修改成功!");
        return ResponseVO.success(200, "用户信息修改成功！");
    }


    //    根据用户ID查询用户信息
    @GetMapping("/{userId}")
    public ResponseVO<UserVo> getUserByUserId(@PathVariable Integer userId) {
        UserVo userVo = userService.getUserVoById(userId);
        return ResponseVO.success(userVo);
    }


    //    获取所有用户信息
    @GetMapping
    public ResponseVO<List<User>> getAllUsers() {
        List<User> userLists = userService.getAllUsers();
        return ResponseVO.success(userLists);
    }
}
