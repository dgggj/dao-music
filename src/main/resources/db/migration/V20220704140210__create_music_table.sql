CREATE TABLE IF NOT EXISTS `music`
(
    `id`          VARCHAR(32)                 NOT NULL PRIMARY KEY COMMENT '歌曲id',
    `name`        VARCHAR(64)                 NOT NULL COMMENT '歌曲名',
    `nickname`    VARCHAR(64)                 NULL COMMENT '用户昵称',
    `description` VARCHAR(256)                NOT NULL COMMENT '歌曲简介',
    `status`      VARCHAR(16) default 'DRAFT' NOT NULL COMMENT '歌曲上架状态，DRAFT-草稿，PUBLISHED-上架，CLOSED-下架',
    created_time  datetime(6)                 NOT NULL COMMENT '创建时间',
    update_time   datetime(6)                 NOT NULL COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT '歌曲表';
