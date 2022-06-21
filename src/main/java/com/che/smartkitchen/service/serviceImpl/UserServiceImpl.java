package com.che.smartkitchen.service.serviceImpl;

import com.che.smartkitchen.dto.UserCreateDto;
import com.che.smartkitchen.dto.UserDto;
import com.che.smartkitchen.entity.User;
import com.che.smartkitchen.exception.BizException;
import com.che.smartkitchen.exception.ExceptionType;
import com.che.smartkitchen.mapper.UserMapper;
import com.che.smartkitchen.repository.UserRepository;
import com.che.smartkitchen.service.UserService;
import com.che.smartkitchen.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> list() {
        System.out.println(userRepository.findAll());



        return userRepository.findAll().stream().map(a->userMapper.toDto(a)).collect(Collectors.toList());

    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        System.out.println(userCreateDto.getUsername());
        System.out.println(userCreateDto.getPassword());
        checkUsername(userCreateDto.getUsername());
        User entity = userMapper.createEntity(userCreateDto);
        String encode = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encode);
        return userMapper.toDto(userRepository.save(entity));

    }

    @Override
    public User loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user.get();
    }

    private void checkUsername(String username){
        Optional<User> user=userRepository.findByUsername(username);
        if(user.isPresent()){
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
    }


}
