package com.che.smartkitchen.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TokenCreateRequest {
    @NotBlank
    @Size(min = 4, max = 64, message = "请输入4-64字符的用户名")
    private String username;

    @NotBlank
    @Size(min = 6, max = 20, message = "密码的长度应大于6小于20")
    private String password;

}
