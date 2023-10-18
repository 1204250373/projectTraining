/*
Navicat MySQL Data Transfer

Source Server         : haoyouxiaoju
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : project_test

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2023-10-18 12:03:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for douser
-- ----------------------------
DROP TABLE IF EXISTS `douser`;
CREATE TABLE `douser` (
  `count` int NOT NULL AUTO_INCREMENT,
  `admin` varchar(30) COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `type` enum('删除用户') COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `user` varchar(30) COLLATE utf8mb3_unicode_520_ci NOT NULL,
  PRIMARY KEY (`count`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_520_ci;

-- ----------------------------
-- Records of douser
-- ----------------------------
