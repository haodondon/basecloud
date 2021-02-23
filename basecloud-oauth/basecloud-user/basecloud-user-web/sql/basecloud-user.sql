/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.32 : Database - basecloud-user
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`basecloud-user` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `basecloud-user`;

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` varchar(40) NOT NULL,
  `menu_name` varchar(40) DEFAULT NULL COMMENT '菜单名称',
  `menu_identify` varchar(20) DEFAULT NULL COMMENT '菜单标识',
  `menu_parent_id` varchar(40) DEFAULT NULL COMMENT '上级菜单',
  `menu_order` int(10) DEFAULT NULL COMMENT '菜单顺序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(1) DEFAULT '0' COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `t_menu` */

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` varchar(40) NOT NULL,
  `role_name` varchar(10) DEFAULT NULL COMMENT '角色名称',
  `role_identify` varchar(20) DEFAULT NULL COMMENT '角色标识',
  `role_order` int(10) DEFAULT NULL COMMENT '角色顺序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(1) DEFAULT '0' COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`role_name`,`role_identify`,`role_order`,`create_time`,`status`) values ('1','管理员','USER_ADMIN',0,'2020-11-29 03:48:15',0);

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `id` varchar(40) NOT NULL,
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(40) DEFAULT NULL COMMENT '菜单ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

/*Data for the table `t_role_menu` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` varchar(50) NOT NULL,
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `user_account` varchar(20) DEFAULT NULL COMMENT '用户登录账号',
  `user_password` varchar(100) DEFAULT NULL COMMENT '用户登录密码',
  `user_pic` varchar(500) DEFAULT NULL COMMENT '用户头像',
  `user_frozen` int(1) DEFAULT NULL COMMENT '用户冻结状态 0-否 1-是',
  `user_phone` varchar(11) DEFAULT NULL COMMENT '用户手机号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(1) DEFAULT '0' COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`user_name`,`user_account`,`user_password`,`user_pic`,`user_frozen`,`user_phone`,`create_time`,`status`) values ('1','超级管理员','admin','$2a$10$XHdyjJgoX2Gw868XaHfFYeqEO7MuZ7jICHjpKEZhjhqR04EbUFVAy',NULL,0,NULL,'2020-11-29 08:50:49',0);

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `id` varchar(40) NOT NULL,
  `user_id` varchar(40) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`id`,`user_id`,`role_id`,`create_time`) values ('1','1','1','2020-11-29 03:48:29');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
