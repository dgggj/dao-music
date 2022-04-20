CREATE TABLE user_role(
                          user_id VARCHAR (32) NOT NULL COMMENT '用户id',
                          role_id VARCHAR (32) NOT NULL COMMENT '角色id',
                          CONSTRAINT c_user_id FOREIGN KEY (user_id) REFERENCES user(id),
                          CONSTRAINT c_role_id FOREIGN KEY (role_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '用户角色表';