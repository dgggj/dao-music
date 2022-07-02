INSERT INTO `user` (id, username, nickname, password, created_time, update_time)
VALUES ('1', 'chylua', 'qiaqia', '$2a$10$47VcQQv3/qTaYjRLVdUcZ.HzT6MoTt27TzPevP9IN6VAue3X7Buqu',
        '2021-07-21 09:27:12.260000',
        '2021-07-21 09:27:12.260000');
INSERT INTO `role` (id, name, title, create_time, update_time)
VALUES ('1', 'ROLE_USER', '普通用户', '2021-07-21 09:27:12.260000', '2021-07-21 09:27:12.260000');
INSERT INTO `role` (id, name, title, create_time, update_time)
VALUES ('2', 'ROLE_ADMIN', '超级管理员', '2021-07-21 09:27:12.260000', '2021-07-21 09:27:12.260000');
INSERT INTO `user_role` (user_id, role_id)
VALUES ('1', '1');
INSERT INTO `user_role` (user_id, role_id)
VALUES ('1', '2');
