/*
Navicat MySQL Data Transfer

Source Server         : haoyouxiaoju
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : project_test

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2023-10-14 00:35:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orderfrom
-- ----------------------------
DROP TABLE IF EXISTS `orderfrom`;
CREATE TABLE `orderfrom` (
  `oid` int NOT NULL AUTO_INCREMENT,
  `seller_phone` varchar(11) COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `seller_id` varchar(10) COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `buyer_phone` varchar(11) COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `buyer_id` varchar(10) COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `book_name` varchar(32) COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `book_unitPrice` decimal(10,2) NOT NULL,
  `book_buyNum` int NOT NULL,
  `allPrice` decimal(10,2) NOT NULL,
  `YorN` enum('已发货','未发货') COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `buyTime` datetime NOT NULL,
  `buyeraddress` varchar(50) COLLATE utf8mb3_unicode_520_ci NOT NULL,
  `deliver_goods` datetime DEFAULT NULL,
  `receive_goods` enum('未收货','已收货') COLLATE utf8mb3_unicode_520_ci NOT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_520_ci;

-- ----------------------------
-- Records of orderfrom
-- ----------------------------
INSERT INTO `orderfrom` VALUES ('11', '120', '2240129623', '17602024903', '2240129516', '思想道德与法治', '23.00', '1', '23.00', '未发货', '2023-10-13 10:59:39', '*******************', null, '未收货');
INSERT INTO `orderfrom` VALUES ('12', '120', '2240129623', '17602024903', '2240129516', '深入理解计算机系统', '80.00', '1', '80.00', '未发货', '2023-10-13 10:59:45', '*******************', null, '未收货');
INSERT INTO `orderfrom` VALUES ('13', '120', '2240129623', '17602024903', '2240129516', '中国近现代史', '34.00', '1', '34.00', '未发货', '2023-10-13 10:59:51', '*******************', null, '未收货');
INSERT INTO `orderfrom` VALUES ('14', '110', '2240129107', '17602024903', '2240129516', '高等数学', '34.00', '1', '34.00', '已发货', '2023-10-13 10:59:56', '*******************', '2023-10-14 12:16:31', '已收货');
INSERT INTO `orderfrom` VALUES ('15', '110', '2240129107', '17602024903', '2240129516', '高等数学', '34.00', '1', '34.00', '已发货', '2023-10-13 11:00:44', '*******************', '2023-10-13 11:35:27', '已收货');
INSERT INTO `orderfrom` VALUES ('16', '110', '2240129107', '17602024903', '2240129516', '计算机科学导论', '29.00', '1', '29.00', '已发货', '2023-10-13 11:01:02', '*******************', '2023-10-13 11:22:02', '已收货');
INSERT INTO `orderfrom` VALUES ('17', '110', '2240129107', '17602024903', '2240129516', '高等数学', '34.00', '1', '34.00', '已发货', '2023-10-13 11:01:07', '*******************', '2023-10-13 11:34:53', '已收货');
INSERT INTO `orderfrom` VALUES ('18', '110', '2240129107', '17602024903', '2240129516', '高等数学', '34.00', '1', '34.00', '已发货', '2023-10-13 11:15:36', '*******************', '2023-10-13 11:35:24', '已收货');
INSERT INTO `orderfrom` VALUES ('19', '110', '2240129107', '17602024903', '2240129516', '高等数学', '34.00', '1', '34.00', '未发货', '2023-10-13 11:15:39', '*******************', '2023-10-13 11:17:33', '未收货');
INSERT INTO `orderfrom` VALUES ('20', '110', '2240129107', '17602024903', '2240129516', '高等数学', '34.00', '1', '34.00', '已发货', '2023-10-13 11:15:42', '*******************', '2023-10-14 12:16:32', '已收货');
INSERT INTO `orderfrom` VALUES ('21', '110', '2240129107', '17602024903', '2240129516', '计算机科学导论', '29.00', '1', '29.00', '未发货', '2023-10-13 11:15:47', '*******************', '2023-10-13 11:18:37', '未收货');
INSERT INTO `orderfrom` VALUES ('22', '110', '2240129107', '17602024903', '2240129516', '计算机科学导论', '29.00', '1', '29.00', '未发货', '2023-10-13 11:15:50', '*******************', '2023-10-13 11:16:11', '未收货');
