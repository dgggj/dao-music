package com.che.smartkitchen.controller;

import com.che.smartkitchen.mapper.UserMapper;
import com.che.smartkitchen.service.UserService;
import com.che.smartkitchen.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    UserMapper userMapper;



    @GetMapping("/")
    public List<UserVo>list(){
        return userService.list().stream().map(userMapper::toVo).collect(Collectors.toList());
    }



    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
