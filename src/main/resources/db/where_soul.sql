/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : where_soul

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2019-11-28 10:11:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `tag_id` int(11) DEFAULT NULL COMMENT '标签id',
  `type_id` int(11) DEFAULT NULL COMMENT '类型id',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '0 收入 1 支出',
  `money` decimal(10,0) NOT NULL COMMENT '金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账单表';

-- ----------------------------
-- Records of bill
-- ----------------------------

-- ----------------------------
-- Table structure for bill_tag
-- ----------------------------
DROP TABLE IF EXISTS `bill_tag`;
CREATE TABLE `bill_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '标签名称',
  `user_id` int(255) DEFAULT NULL COMMENT '用户id， 有为用户自定义标签',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点id',
  `structure_chain` varchar(255) DEFAULT NULL COMMENT '结构链 id_id',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0 不展示 1展示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账单标签表';

-- ----------------------------
-- Records of bill_tag
-- ----------------------------

-- ----------------------------
-- Table structure for bill_tag_merge
-- ----------------------------
DROP TABLE IF EXISTS `bill_tag_merge`;
CREATE TABLE `bill_tag_merge` (
  `bill_id` int(11) NOT NULL COMMENT '账单id',
  `bill_tag_id` int(11) NOT NULL COMMENT '账单标签id',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账单和标签关联表';

-- ----------------------------
-- Records of bill_tag_merge
-- ----------------------------

-- ----------------------------
-- Table structure for bill_type
-- ----------------------------
DROP TABLE IF EXISTS `bill_type`;
CREATE TABLE `bill_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '父类型id',
  `name` varchar(50) DEFAULT NULL COMMENT '类型名称',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `structure_chain` varchar(255) DEFAULT NULL COMMENT '结构链 id_id_...',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `bill_type_info_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账单类型表';

-- ----------------------------
-- Records of bill_type
-- ----------------------------

-- ----------------------------
-- Table structure for bill_type_info
-- ----------------------------
DROP TABLE IF EXISTS `bill_type_info`;
CREATE TABLE `bill_type_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` decimal(10,0) DEFAULT NULL COMMENT '余额',
  `repayment_date` datetime DEFAULT NULL COMMENT '还款日期',
  `reminder_date` datetime DEFAULT NULL COMMENT '提醒日期',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账单类型信息表';

-- ----------------------------
-- Records of bill_type_info
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `avatar_id` int(11) DEFAULT NULL COMMENT '头像',
  `gender` bit(11) DEFAULT NULL COMMENT '性别 0 女 1 男',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `login_name` varchar(50) NOT NULL COMMENT '登录名',
  `users_security_id` int(11) DEFAULT NULL COMMENT '安全绑定的账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of users
-- ----------------------------

-- ----------------------------
-- Table structure for users_avatar
-- ----------------------------
DROP TABLE IF EXISTS `users_avatar`;
CREATE TABLE `users_avatar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户头像表';

-- ----------------------------
-- Records of users_avatar
-- ----------------------------

-- ----------------------------
-- Table structure for users_security
-- ----------------------------
DROP TABLE IF EXISTS `users_security`;
CREATE TABLE `users_security` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户安全表';

-- ----------------------------
-- Records of users_security
-- ----------------------------
