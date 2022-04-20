package com.che.smartkitchen.mapper;

import com.che.smartkitchen.dto.UserDto;
import com.che.smartkitchen.entity.User;
import com.che.smartkitchen.vo.UserVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserDto toDto(User user);
    UserVo toVo(UserDto userDto);

}
