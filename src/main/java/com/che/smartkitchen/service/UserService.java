package com.che.smartkitchen.service;

import com.che.smartkitchen.dto.UserCreateDto;
import com.che.smartkitchen.dto.UserDto;
import com.che.smartkitchen.entity.User;
import com.che.smartkitchen.vo.UserVo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;


public interface UserService extends UserDetailsService {
    List<UserDto> list();

    UserDto create(UserCreateDto userCreateDto);

    @Override
    User loadUserByUsername(String username);
}
