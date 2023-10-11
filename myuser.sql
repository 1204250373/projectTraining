/*
Navicat MySQL Data Transfer

Source Server         : haoyouxiaoju
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : project_test

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2023-10-11 10:49:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for myuser
-- ----------------------------
DROP TABLE IF EXISTS `myuser`;
CREATE TABLE `myuser` (
  `uid` int NOT NULL,
  `phone` varchar(11) COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `sid` varchar(10) COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `userType` enum('用户','管理员','商家') COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `userName` varchar(20) COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `userBalance` int NOT NULL,
  PRIMARY KEY (`uid`,`phone`,`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_520_ci;

-- ----------------------------
-- Records of myuser
-- ----------------------------
