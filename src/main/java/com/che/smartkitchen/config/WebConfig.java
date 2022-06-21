package com.che.smartkitchen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class WebConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
//        return new Pbkdf2PasswordEncoder();

        return new BCryptPasswordEncoder();
    }
}
