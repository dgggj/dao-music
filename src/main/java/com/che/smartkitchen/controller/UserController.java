package com.che.smartkitchen.controller;

import com.che.smartkitchen.dto.UserCreateRequest;
import com.che.smartkitchen.dto.UserUpdateRequest;
import com.che.smartkitchen.mapper.UserMapper;
import com.che.smartkitchen.service.UserService;
import com.che.smartkitchen.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Api(tags = "用户")
public class UserController {
    UserService userService;

    UserMapper userMapper;


    @GetMapping
    @ApiOperation("用户检索")
//    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<UserVo> search(@PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.ASC) Pageable pageable) {

        return userService.search(pageable).map(userMapper::toVo);
    }


    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    UserVo me() {
        return userMapper.toVo(userService.getCurrentUser());
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    UserVo create(@Validated @RequestBody UserCreateRequest userCreateRequest) {
        System.out.println("6");


        return userMapper.toVo(userService.create(userCreateRequest));

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    UserVo get(@PathVariable String id) {
        return userMapper.toVo(userService.get(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    UserVo update(@PathVariable String id, @Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        return userMapper.toVo(userService.update(id, userUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(@PathVariable String id) {
        userService.delete(id);
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
