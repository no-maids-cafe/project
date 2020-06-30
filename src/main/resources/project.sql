/*
 Navicat MySQL Data Transfer

 Source Server         : cafe
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : cafe_house

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 30/06/2020 09:47:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for deal
-- ----------------------------
DROP TABLE IF EXISTS `deal`;
CREATE TABLE `deal`  (
  `Deal_ID` int NOT NULL COMMENT '订单编号',
  `Deal_Time` datetime(0) NOT NULL COMMENT '下单时间',
  `
Call_Number` int NOT NULL COMMENT '叫餐号',
  PRIMARY KEY (`Deal_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for deal_content
-- ----------------------------
DROP TABLE IF EXISTS `deal_content`;
CREATE TABLE `deal_content`  (
  `Deal_ID` int NOT NULL COMMENT '订单编号',
  `Item_ID` int NOT NULL COMMENT '所点餐品',
  `Item_Number` int NOT NULL COMMENT '餐品数量',
  PRIMARY KEY (`Deal_ID`, `Item_ID`) USING BTREE,
  INDEX `Item_ID`(`Item_ID`) USING BTREE,
  CONSTRAINT `Deal_ID0` FOREIGN KEY (`Deal_ID`) REFERENCES `deal` (`Deal_ID`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `Item_ID` FOREIGN KEY (`Item_ID`) REFERENCES `item` (`Item_ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `Item_ID` int NOT NULL COMMENT '餐点编号',
  `Item_Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '餐点名称',
  `Item_Type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '餐点类型',
  `Item_Remain` int NOT NULL COMMENT '剩余份数',
  `Item_Price` decimal(3, 2) NOT NULL COMMENT '单价',
  `Item_Desp` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `Item_Sold` int NULL DEFAULT NULL COMMENT '月售',
  PRIMARY KEY (`Item_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `User_ID` int NOT NULL COMMENT '用户编号',
  `User_Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `Email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `Phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户电话',
  `Password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`User_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_deal
-- ----------------------------
DROP TABLE IF EXISTS `user_deal`;
CREATE TABLE `user_deal`  (
  `User_ID` int NOT NULL,
  `Deal_ID` int NOT NULL,
  PRIMARY KEY (`User_ID`, `Deal_ID`) USING BTREE,
  INDEX `Deal_ID`(`Deal_ID`) USING BTREE,
  CONSTRAINT `Deal_ID` FOREIGN KEY (`Deal_ID`) REFERENCES `deal` (`Deal_ID`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `User_ID` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
