package com.che.smartkitchen.controller;

import com.che.smartkitchen.dto.UserCreateDto;
import com.che.smartkitchen.mapper.UserMapper;
import com.che.smartkitchen.service.UserService;
import com.che.smartkitchen.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    UserMapper userMapper;



    @GetMapping("/")
    public List<UserVo>list(){
        System.out.println(userService.list());
        return userService.list().stream().map(userMapper::toVo).collect(Collectors.toList());
    }
    @PostMapping("/")
    UserVo create(@RequestBody UserCreateDto userCreateDto){


        return userMapper.toVo(userService.create(userCreateDto));

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
