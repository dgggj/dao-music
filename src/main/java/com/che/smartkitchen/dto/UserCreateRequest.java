package com.che.smartkitchen.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserCreateRequest {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 64, message = "请输入4-64字符的用户名")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码的长度应大于6小于20")
    private String password;
    private String nickname;

    private String gender;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
