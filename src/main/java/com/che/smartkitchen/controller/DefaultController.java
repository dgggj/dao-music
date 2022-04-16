package com.che.smartkitchen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class DefaultController {
    @GetMapping
    public String sayHello(){
        return "智能厨房安全监控系统";
    }
}
