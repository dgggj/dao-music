package com.che.smartkitchen.repository;

import com.che.smartkitchen.entity.User;
import com.che.smartkitchen.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository repository;

    @Test
    void findByUsername() {
        User user = new User();
        User save = repository.save(user);
        user.setUsername("qia");
        user.setNickname("chexu");
//        user.setEnabled(true);
        user.setGender(Gender.FEMALE);
//        user.setLocked(false);
        user.setPassword("dhs");
        user.setLastLoginIp("127.0.0.2");
        user.setLastLoginTime(new Date());

        System.out.println("创建的ksuid为"+save.getId());
        User che = repository.getByUsername("che");

        System.out.println(save.toString());

    }
}
