package com.che.smartkitchen.dto;

import lombok.Data;

//Cos要传的参数
@Data
public class FileUpdateDto {
    private String secreteId;

    private String secretKey;

    private String token;

    private String bucket;

    private String region;

    private String key;

}
