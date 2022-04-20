package com.che.smartkitchen.repository;

import com.che.smartkitchen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface UserRepository extends JpaRepository<User,String> {//这两个参数一个为实体类，另一个是实体类主键的类型
    User getByUsername(String name);
}
