package com.che.smartkitchen.vo;
import lombok.Data;

import java.util.*;
@Data
public class UserVo {
    private int id;

    private String username;

    private String nickname;

    private List<RoleVo>roles;
}
