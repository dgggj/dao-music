package com.che.smartkitchen.service.serviceImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.che.smartkitchen.config.SecurityConfig;
import com.che.smartkitchen.dto.TokenCreateRequest;
import com.che.smartkitchen.dto.UserCreateRequest;
import com.che.smartkitchen.dto.UserDto;
import com.che.smartkitchen.dto.UserUpdateRequest;
import com.che.smartkitchen.entity.User;
import com.che.smartkitchen.exception.BizException;
import com.che.smartkitchen.exception.ExceptionType;
import com.che.smartkitchen.mapper.UserMapper;
import com.che.smartkitchen.repository.UserRepository;
import com.che.smartkitchen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

//    @Override
//    public List<UserDto> list() {
//        System.out.println("q2");
//        Optional<User> all = userRepository.findByUsername("cheche");
//        System.out.println(all.get());
//        List<User> all1 = userRepository.findAll();
//        return userRepository.findAll().stream().map(a -> userMapper.toDto(a)).collect(Collectors.toList());

//    }

    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {
        System.out.println("7");
        checkUsername(userCreateRequest.getUsername());
        User entity = userMapper.createEntity(userCreateRequest);
        String encode = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encode);
        return userMapper.toDto(userRepository.save(entity));
    }

    @Override
    public User loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user.get();
    }

    @Override
    public UserDto get(String id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return userMapper.toDto(user.get());
    }

    @Override
    public UserDto update(String id, UserUpdateRequest userUpdateRequest) {

        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return userMapper.toDto(userRepository.save(userMapper.updateEntity(user.get(), userUpdateRequest)));

    }

    //TODO: 重构
    @Override
    public void delete(String id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        userRepository.delete(user.get());
    }

    @Override
    public Page<UserDto> search(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDto);

    }

    @Override
    public String createToken(TokenCreateRequest tokenCreateRequest) {
        User user = loadUserByUsername(tokenCreateRequest.getUsername());
        if (!passwordEncoder.matches(tokenCreateRequest.getPassword(), user.getPassword())) {
            throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }

        if (!user.isEnabled()) {
            throw new BizException(ExceptionType.USER_NOT_ENABLED);
        }

        if (!user.isAccountNonLocked()) {
            throw new BizException(ExceptionType.USER_LOCKED);
        }

        String token = JWT.create().withSubject(tokenCreateRequest.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes(StandardCharsets.UTF_8)));

        return token;
    }

    //获取当前登录用户信息
    @Override
    public UserDto getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = loadUserByUsername(authentication.getName());
        return userMapper.toDto(currentUser);
    }

    private void checkUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
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
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


}
