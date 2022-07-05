package com.che.smartkitchen.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MusicUpdateRequest {
    @NotBlank(message = "音乐名不能为空")
    private String name;


    private String description;
}
