/*
Navicat MySQL Data Transfer

Source Server         : zwj
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : sharedppx

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2018-01-15 22:48:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `phonenum` varchar(50) COLLATE utf8_bin NOT NULL,
  `pwd` varchar(100) COLLATE utf8_bin NOT NULL,
  `nickname` varchar(50) COLLATE utf8_bin NOT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `birthday` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `enabled` int(2) NOT NULL DEFAULT '0',
  `image` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `balance` float unsigned zerofill NOT NULL,
  `points` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '17680519905', 'admin', '小猫咪', '喵~@yahoo.com', '男', '1996年10月11日', '0', '/resources/userImage/2016092011555080522.bmp', '2016-11-22 19:11:17', '2016-10-21 11:35:43', '000000099999', null);
INSERT INTO `user` VALUES ('2', 'qqq', '123', 'HO!', '123@qq.com', '女', '2017年10月13日', '0', '/resources/userImage/2016092011555080522.bmp', null, null, '7.83716e+006', null);
INSERT INTO `user` VALUES ('3', '111', '111', 'admin', 'admin@139.com', '男', '2017年9月23日', '0', '/resources/userImage/2016092011555080522.bmp', null, null, '000000000000', null);

-- ----------------------------
-- Table structure for `userecord`
-- ----------------------------
DROP TABLE IF EXISTS `userecord`;
CREATE TABLE `userecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `startSite` varchar(255) DEFAULT NULL,
  `stopSite` varchar(255) DEFAULT NULL,
  `startTime` varchar(255) DEFAULT NULL,
  `stopTime` varchar(255) DEFAULT NULL,
  `duration` varchar(11) DEFAULT NULL,
  `totalMoney` double(11,0) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `x_id` int(11) DEFAULT NULL,
  `state` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `u_id` (`u_id`),
  KEY `x_id` (`x_id`),
  CONSTRAINT `u_id` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`),
  CONSTRAINT `x_id` FOREIGN KEY (`x_id`) REFERENCES `xia` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userecord
-- ----------------------------
INSERT INTO `userecord` VALUES ('1', '31.25784,120.749346', '31.259507,120.74953', '2017-10-06 15:22:10', '2017-10-06 15:22:47', '00:00:36', '0', '1', '5121001', '1');
INSERT INTO `userecord` VALUES ('2', '31.25784,120.749346', '31.259517,120.749505', '2017-10-06 17:44:33', '2017-10-06 17:50:22', '00:05:48', '270', '2', '5121001', '1');
INSERT INTO `userecord` VALUES ('3', '31.259517,120.749505', '31.259515,120.749507', '2017-10-06 17:51:54', '2017-10-06 17:52:10', '00:00:15', '0', '3', '5121001', '1');
INSERT INTO `userecord` VALUES ('4', '31.259515,120.749507', '31.259561,120.749052', '2017-10-09 09:29:19', '2017-10-09 16:15:58', '6:46:33', '21924', '2', '5121001', '1');
INSERT INTO `userecord` VALUES ('5', '31.259561,120.749052', '31.259583,120.749129', '2017-10-09 16:24:35', '2017-10-09 16:24:55', '00:00:06', '0', '2', '5121001', '1');
INSERT INTO `userecord` VALUES ('6', '31.259561,120.749052', '31.259369,120.749652', '2017-10-09 16:24:45', '2017-10-10 09:47:42', '17:22:53', '56268', '2', '5121001', '1');
INSERT INTO `userecord` VALUES ('7', '31.257829,120.749382', '31.259532,120.749098', '2017-10-09 16:26:42', '2017-10-09 16:27:35', '00:00:13', '0', '2', '5121002', '1');
INSERT INTO `userecord` VALUES ('8', '31.259369,120.749652', '31.259495,120.749531', '2017-10-11 13:45:00', '2017-10-11 13:46:03', '00:01:01', '54', '2', '5121001', '1');
INSERT INTO `userecord` VALUES ('9', '31.259495,120.749531', '31.259496,120.749549', '2017-10-11 15:10:44', '2017-10-11 15:11:50', '00:01:04', '54', '2', '5121001', '1');
INSERT INTO `userecord` VALUES ('14', '31.257865,120.749559', '31.259483,120.749544', '2017-10-11 17:00:47', '2017-10-11 17:06:46', '00:05:56', '270', '2', '5121003', '1');
INSERT INTO `userecord` VALUES ('15', '31.259483,120.749544', '31.259488,120.749541', '2017-10-11 17:09:00', '2017-10-11 17:09:13', '00:00:12', '0', '2', '5121003', '1');
INSERT INTO `userecord` VALUES ('16', '31.259318,120.748229', '31.259494,120.749533', '2017-10-12 10:13:38', '2017-10-13 22:17:01', '36:03:19', '2160837', '2', '5121005', '1');
INSERT INTO `userecord` VALUES ('17', '31.259496,120.749549', '31.259492,120.749535', '2017-10-13 22:19:21', '2017-10-13 22:20:26', '00:01:04', '54', '2', '5121001', '1');
INSERT INTO `userecord` VALUES ('18', '31.259492,120.749535', '31.259495,120.749534', '2017-10-13 22:23:23', '2017-10-13 22:58:50', '00:34:13', '1836', '2', '5121001', '1');
INSERT INTO `userecord` VALUES ('19', '31.259495,120.749534', '31.259497,120.749539', '2017-10-13 23:02:18', '2017-10-13 23:04:35', '00:02:15', '108', '2', '5121001', '1');
INSERT INTO `userecord` VALUES ('20', '31.260365,120.750211', '31.259535,120.749508', '2017-10-13 23:51:44', '2017-10-13 23:51:50', '00:00:05', '0', '2', '5121005', '1');

-- ----------------------------
-- Table structure for `xia`
-- ----------------------------
DROP TABLE IF EXISTS `xia`;
CREATE TABLE `xia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `latitude` double(11,6) DEFAULT NULL,
  `longitude` double(11,6) DEFAULT NULL,
  `price` float(11,2) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5121006 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xia
-- ----------------------------
INSERT INTO `xia` VALUES ('5121001', '31.259425', '120.748218', '54.00', '0', '0');
INSERT INTO `xia` VALUES ('5121002', '31.258036', '120.749737', '54.00', '0', '0');
INSERT INTO `xia` VALUES ('5121003', '31.257031', '120.749812', '54.00', '0', '0');
INSERT INTO `xia` VALUES ('5121004', '31.259443', '120.747596', '108.00', '1', '0');
INSERT INTO `xia` VALUES ('5121005', '31.259535', '120.749508', '999.00', '2', '0');
