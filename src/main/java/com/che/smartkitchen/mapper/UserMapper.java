package com.che.smartkitchen.mapper;

import com.che.smartkitchen.dto.UserCreateRequest;
import com.che.smartkitchen.dto.UserDto;
import com.che.smartkitchen.dto.UserUpdateRequest;
import com.che.smartkitchen.entity.User;
import com.che.smartkitchen.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {


    User createEntity(UserCreateRequest userCreateDto);

    UserVo toVo(UserDto userDto);

    User updateEntity(@MappingTarget User user, UserUpdateRequest userUpdateRequest);

    UserDto toDto(User user);


}
