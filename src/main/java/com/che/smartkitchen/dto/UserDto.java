package com.che.smartkitchen.dto;

import com.che.smartkitchen.vo.RoleVo;
import lombok.Data;

import java.util.List;
@Data
public class UserDto {
    private int id;

    private String username;

    private String nickname;

    private List<RoleVo> roles;
}
