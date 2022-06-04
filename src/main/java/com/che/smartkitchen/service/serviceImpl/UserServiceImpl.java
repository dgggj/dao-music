package com.che.smartkitchen.service.serviceImpl;

import com.che.smartkitchen.dto.UserDto;
import com.che.smartkitchen.entity.User;
import com.che.smartkitchen.mapper.UserMapper;
import com.che.smartkitchen.repository.UserRepository;
import com.che.smartkitchen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    @Override
    public List<UserDto> list() {



        return userRepository.findAll().stream().map(a->userMapper.toDto(a)).collect(Collectors.toList());

    }



    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
