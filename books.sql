/*
Navicat MySQL Data Transfer

Source Server         : haoyouxiaoju
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : project_test

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2023-10-05 22:07:51
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
  `BookState` enum('上架中','下架') COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `NowRepertory` int NOT NULL,
  `BookPrice` decimal(10,2) NOT NULL,
  PRIMARY KEY (`BookID`)
) ENGINE=InnoDB AUTO_INCREMENT=1027 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_520_ci;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('1001', '5', '数据结构与原理', '下架', '1', '32.00');
INSERT INTO `books` VALUES ('1011', '12', '马克思基本原理', '下架', '1', '20.00');
INSERT INTO `books` VALUES ('1012', 'wyjyh', '高等数学', '上架中', '12', '34.00');
INSERT INTO `books` VALUES ('1016', '12', '计算机网络', '下架', '1', '39.00');
INSERT INTO `books` VALUES ('1017', '12', '计算机组成原理', '下架', '1', '38.00');
INSERT INTO `books` VALUES ('1020', 'wyjyh', '思想道德与法治', '上架中', '11', '23.00');
INSERT INTO `books` VALUES ('1021', 'wyjyh', 'Linux系统入门与实战', '上架中', '12', '34.00');
INSERT INTO `books` VALUES ('1022', 'wyjyh', '深入理解计算机系统', '上架中', '122', '80.00');
INSERT INTO `books` VALUES ('1023', 'wyjyh', '离散数学', '上架中', '25', '34.00');
INSERT INTO `books` VALUES ('1024', 'wyjyh', '线性代数', '上架中', '12', '12.00');
INSERT INTO `books` VALUES ('1025', 'wyjyh', 'C语言程序设计', '上架中', '41', '66.00');
INSERT INTO `books` VALUES ('1026', 'wyjyh', '中国近现代史', '上架中', '12', '34.00');
