/*
Navicat MySQL Data Transfer

Source Server         : haoyouxiaoju
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : project_test

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2023-10-14 00:35:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for myuser
-- ----------------------------
DROP TABLE IF EXISTS `myuser`;
CREATE TABLE `myuser` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `sid` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_520_ci DEFAULT NULL,
  `password` varchar(16) COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `userType` enum('用户','管理员','商家') COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `userBalance` double NOT NULL,
  PRIMARY KEY (`uid`,`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_520_ci;

-- ----------------------------
-- Records of myuser
-- ----------------------------
INSERT INTO `myuser` VALUES ('1', '2240129516', '17602024903', '123456', '用户', '0');
INSERT INTO `myuser` VALUES ('2', '1010101010', null, '123456', '用户', '0');
INSERT INTO `myuser` VALUES ('3', '2240129107', '110', '123456', '管理员', '0');
INSERT INTO `myuser` VALUES ('4', '2240129623', '120', '123456', '管理员', '0');
INSERT INTO `myuser` VALUES ('7', '2240129515', null, '123', '用户', '0');
