/*
Navicat MySQL Data Transfer

Source Server         : haoyouxiaoju
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : project_test

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2023-10-14 00:35:45
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
  `BookPrice` decimal(10,2) NOT NULL,
  `BookState` enum('上架中','下架') COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `NowRepertory` int NOT NULL,
  `MinRepertory` int NOT NULL,
  PRIMARY KEY (`BookID`)
) ENGINE=InnoDB AUTO_INCREMENT=1032 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_520_ci;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('1001', '2240129623', '数据结构与原理', '32.00', '下架', '0', '0');
INSERT INTO `books` VALUES ('1011', '2240129623', '马克思基本原理', '20.00', '下架', '0', '0');
INSERT INTO `books` VALUES ('1012', '2240129107', '高等数学', '34.00', '上架中', '53', '7');
INSERT INTO `books` VALUES ('1016', '2240129107', '计算机网络', '39.00', '下架', '0', '0');
INSERT INTO `books` VALUES ('1017', '2240129107', '计算机组成原理', '38.00', '下架', '0', '0');
INSERT INTO `books` VALUES ('1020', '2240129623', '思想道德与法治', '23.00', '上架中', '5', '0');
INSERT INTO `books` VALUES ('1021', '2240129107', 'Linux系统入门与实战', '34.00', '上架中', '9', '0');
INSERT INTO `books` VALUES ('1022', '2240129623', '深入理解计算机系统', '80.00', '上架中', '118', '0');
INSERT INTO `books` VALUES ('1023', '2240129107', '离散数学', '34.00', '上架中', '23', '0');
INSERT INTO `books` VALUES ('1025', '2240129107', 'C语言程序设计', '66.00', '上架中', '37', '0');
INSERT INTO `books` VALUES ('1026', '2240129623', '中国近现代史', '34.00', '上架中', '14', '0');
INSERT INTO `books` VALUES ('1027', '2240129623', '挪威的森林', '31.00', '下架', '21', '25');
INSERT INTO `books` VALUES ('1028', '2240129107', '计算机科学导论', '29.00', '上架中', '18', '5');
INSERT INTO `books` VALUES ('1029', '2240129623', '形势与政策', '20.00', '下架', '4', '5');
INSERT INTO `books` VALUES ('1031', '2240129107', '马克思理论', '10.00', '下架', '0', '0');
