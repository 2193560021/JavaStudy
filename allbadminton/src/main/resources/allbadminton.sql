-- 羽毛球平台数据库结构 v1.0
-- 使用MySQL 8.0+版本

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                        `username` varchar(32) NOT NULL COMMENT '用户名',
                        `password` varchar(128) NOT NULL COMMENT '加密密码',
                        `phone` varchar(20) NOT NULL COMMENT '手机号',
                        `avatar` varchar(256) DEFAULT NULL COMMENT '头像URL',
                        `money` int DEFAULT NULL COMMENT '账户余额',
                        `role` tinyint NOT NULL DEFAULT '0' COMMENT '角色(0-用户 1-管理员)',
                        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_username` (`username`),
                        UNIQUE KEY `uk_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- 场地表
-- ----------------------------
DROP TABLE IF EXISTS `court`;
CREATE TABLE `court` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `name` varchar(64) NOT NULL COMMENT '场地名称',
                         `location` varchar(128) NOT NULL COMMENT '详细地址',
                         `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态(0-不可用 1-可用)',
                         `price` decimal(10,2) NOT NULL COMMENT '每小时价格',
                         `open_time` time NOT NULL COMMENT '开放时间',
                         `close_time` time NOT NULL COMMENT '关闭时间',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='羽毛球场地';

-- ----------------------------
-- 预约订单表
-- ----------------------------
DROP TABLE IF EXISTS `booking_order`;
CREATE TABLE `booking_order` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `order_no` varchar(32) NOT NULL COMMENT '订单编号',
                                 `user_id` bigint NOT NULL COMMENT '用户ID',
                                 `court_id` bigint NOT NULL COMMENT '场地ID',
                                 `date` date NOT NULL COMMENT '日期',
                                 `start_time` time NOT NULL COMMENT '开始时间',
                                 `end_time` time NOT NULL COMMENT '结束时间',
                                 `amount` decimal(10,2) NOT NULL COMMENT '支付金额',
                                 `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态(0-待支付 1-已支付 2-已取消)',
                                 `all_status` tinyint NOT NULL DEFAULT '0' COMMENT '状态(0-未完成 1-已完成)',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `uk_order_no` (`order_no`),
                                 UNIQUE KEY `uk_court_time` (`court_id`,`start_time`) COMMENT '防止重复预约',
                                 KEY `idx_user` (`user_id`),
                                 CONSTRAINT `fk_order_court` FOREIGN KEY (`court_id`) REFERENCES `court` (`id`),
                                 CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预约订单';

-- ----------------------------
-- 好物分享表
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `user_id` bigint NOT NULL COMMENT '发布者ID',
                         `title` varchar(128) NOT NULL COMMENT '商品标题',
                         `content` text NOT NULL COMMENT '商品描述',
                         `images` json DEFAULT NULL COMMENT '图片URL数组',
                         `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
                         `view_count` int DEFAULT '0' COMMENT '浏览数',
                         `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`),
                         KEY `idx_user` (`user_id`),
                         FULLTEXT KEY `ft_search` (`title`,`content`) COMMENT '全文搜索索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='好物分享';

-- ----------------------------
-- 组队表
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `leader_id` bigint NOT NULL COMMENT '队长ID',
                        `court_id` bigint NOT NULL COMMENT '场地ID',
                        `play_time` datetime NOT NULL COMMENT '活动时间',
                        `max_members` int NOT NULL DEFAULT '4' COMMENT '最大人数',
                        `current_members` int NOT NULL DEFAULT '1' COMMENT '当前人数',
                        `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态(0-招募中 1-已满员 2-已过期)',
                        PRIMARY KEY (`id`),
                        KEY `idx_court_time` (`court_id`,`play_time`),
                        CONSTRAINT `fk_team_court` FOREIGN KEY (`court_id`) REFERENCES `court` (`id`),
                        CONSTRAINT `fk_team_leader` FOREIGN KEY (`leader_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组队信息';

-- ----------------------------
-- 队伍成员表
-- ----------------------------
DROP TABLE IF EXISTS `team_member`;
CREATE TABLE `team_member` (
                               `team_id` bigint NOT NULL,
                               `user_id` bigint NOT NULL,
                               `join_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               PRIMARY KEY (`team_id`,`user_id`),
                               CONSTRAINT `fk_member_team` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
                               CONSTRAINT `fk_member_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='队伍成员关系';

-- ----------------------------
-- 消息通知表
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `user_id` bigint NOT NULL,
                                `content` varchar(512) NOT NULL COMMENT '消息内容',
                                `type` tinyint NOT NULL COMMENT '1-订单 2-组队 3-系统',
                                `is_read` tinyint NOT NULL DEFAULT '0' COMMENT '0-未读 1-已读',
                                `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`),
                                KEY `idx_user` (`user_id`),
                                CONSTRAINT `fk_notify_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息通知';

SET FOREIGN_KEY_CHECKS = 1;