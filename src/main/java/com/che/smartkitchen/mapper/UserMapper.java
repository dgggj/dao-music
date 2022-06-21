package com.che.smartkitchen.mapper;

import com.che.smartkitchen.dto.UserCreateDto;
import com.che.smartkitchen.dto.UserDto;
import com.che.smartkitchen.entity.User;
import com.che.smartkitchen.vo.UserVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserVo toVo(UserDto userDto);
    User createEntity(UserCreateDto userCreateDto);
    UserDto toDto(User user);







}
