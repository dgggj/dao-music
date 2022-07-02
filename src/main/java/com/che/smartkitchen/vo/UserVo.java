package com.che.smartkitchen.vo;

import com.che.smartkitchen.enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class UserVo {
    private String id;

    private String username;

    private String nickname;

    private List<RoleVo> roles;
    private Gender gender;
    private Boolean locked;
    private Boolean enabled;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<RoleVo> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleVo> roles) {
        this.roles = roles;
    }
}
