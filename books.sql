/*
Navicat MySQL Data Transfer

Source Server         : haoyouxiaoju
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : project_test

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2023-10-03 22:42:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `BookID` int NOT NULL AUTO_INCREMENT,
  `Vendor` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `BookName` varchar(32) COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `BookState` int NOT NULL,
  `NowRepertory` int NOT NULL,
  `BookPrice` decimal(10,2) NOT NULL,
  PRIMARY KEY (`BookID`)
) ENGINE=InnoDB AUTO_INCREMENT=1019 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_520_ci;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('1001', '5', '数据结构与原理', '1', '0', '32.00');
INSERT INTO `books` VALUES ('1011', '12', '马克思基本原理', '1', '1', '20.00');
INSERT INTO `books` VALUES ('1016', '12', '计算机网络', '1', '1', '39.00');
INSERT INTO `books` VALUES ('1017', '12', '计算机组成原理', '1', '1', '38.00');
