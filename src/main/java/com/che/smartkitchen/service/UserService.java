package com.che.smartkitchen.service;

import com.che.smartkitchen.dto.TokenCreateRequest;
import com.che.smartkitchen.dto.UserCreateRequest;
import com.che.smartkitchen.dto.UserDto;
import com.che.smartkitchen.dto.UserUpdateRequest;
import com.che.smartkitchen.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
//    List<UserDto> list();

    UserDto create(UserCreateRequest userCreateDto);

    @Override
    User loadUserByUsername(String username);

    UserDto get(String id);

    UserDto update(String id, UserUpdateRequest userUpdateRequest);

    void delete(String id);

    Page<UserDto> search(Pageable pageable);

    String createToken(TokenCreateRequest tokenCreateRequest);

    UserDto getCurrentUser();
}
