/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.35-log : Database - zb_server
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zb_server` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zb_server`;

/*Table structure for table `basic_config` */

DROP TABLE IF EXISTS `basic_config`;

CREATE TABLE `basic_config` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `config_name` varchar(50) NOT NULL COMMENT '标识项目是否处于管理员调试、修改状态，用户登录之后会有相应的弹窗提醒',
  `config_value` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='全局性配置';

/*Data for the table `basic_config` */

insert  into `basic_config`(`id`,`config_name`,`config_value`) values (1,'project_debugging_tip','0');

/*Table structure for table `basic_source_download` */

DROP TABLE IF EXISTS `basic_source_download`;

CREATE TABLE `basic_source_download` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '资源名称',
  `down_url` varchar(150) NOT NULL COMMENT '下载地址',
  `icon_name` varchar(50) NOT NULL DEFAULT 'icon-download-alt' COMMENT '图标',
  `sort_num` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `basic_source_download` */

insert  into `basic_source_download`(`id`,`name`,`down_url`,`icon_name`,`sort_num`,`create_time`,`update_time`) values (1,'ActiveMQ消息监听处理入门Demo(Spring简单集成).zip','http://pan.baidu.com/s/1hrH9DnY','icon-download-alt',0,'2016-11-03 15:17:37','2016-11-03 15:17:39'),(2,'apache tomcat集群环境搭建图文教程.zip','http://pan.baidu.com/s/1o7DtCsq','icon-download-alt',0,'2016-11-03 15:18:34','2016-11-03 15:18:36'),(3,'chatpush-在线聊天室(可选指定用户聊天).zip','http://pan.baidu.com/s/1eR521IM','icon-download-alt',0,'2016-11-03 15:19:33','2016-11-03 15:19:35'),(4,'crm后台系统源码(bootstrap).zip','http://pan.baidu.com/s/1i4TcT29','icon-download-alt',0,'2016-11-03 15:19:56','2016-11-03 15:19:57'),(5,'DateTimePicker_JQuery日期和时间插件.zip','http://pan.baidu.com/s/1o8eyaF4','icon-download-alt',0,'2016-11-03 15:20:20','2016-11-03 15:20:22'),(6,'dwrcomet-推送消息给指定用户Demo.zip','http://pan.baidu.com/s/1kVAslEn','icon-download-alt',0,'2016-11-03 15:20:42','2016-11-03 15:20:44'),(7,'DwrPush-模拟消息推送至所有当前在线的用户.zip','http://pan.baidu.com/s/1i5BclKh','icon-download-alt',0,'2016-11-03 15:21:03','2016-11-03 15:21:05'),(8,'dwz富客户端 springmvc ibatis简单Demo.zip','http://pan.baidu.com/s/1boRTofX','icon-download-alt',0,'2016-11-03 15:21:24','2016-11-03 15:21:26'),(9,'easyui完整Demo-各种功能_布局.zip','http://pan.baidu.com/s/1nvJb8G5','icon-download-alt',0,'2016-11-03 15:21:52','2016-11-03 15:21:54'),(10,'erp项目源码--bootstrap 选项卡切换.zip','http://pan.baidu.com/s/1boLhDLl','icon-download-alt',0,'2016-11-03 15:22:13','2016-11-03 15:22:15'),(11,'Highcharts简单实例Demo.zip','http://pan.baidu.com/s/1dFt91nF','icon-download-alt',0,'2016-11-03 15:22:37','2016-11-03 15:22:38'),(12,'HTML5 CSS3鼠标悬停图片特效.zip','http://pan.baidu.com/s/1eSLXIee','icon-download-alt',0,'2016-11-03 15:23:01','2016-11-03 15:23:03'),(13,'Java web项目：mybatis、springmvc、seajs、freemarker、分布式session、自定义taglib等.zip','http://pan.baidu.com/s/1qYzsFxU','icon-download-alt',0,'2016-11-03 15:23:21','2016-11-03 15:23:23'),(14,'Java后台系统-不同框架优点集成-bootstrap布局-权限角色管理(含sql)----1.0.0版本.zip','http://pan.baidu.com/s/1i4GlFql','icon-download-alt',0,'2016-11-03 15:23:43','2016-11-03 15:23:44'),(15,'java线程异步：异步发送email、回调接口定义、其他异步线程demo.zip','http://pan.baidu.com/s/1jIbOunO','icon-download-alt',0,'2016-11-03 15:24:06','2016-11-03 15:24:08'),(16,'JAVA之23种设计模式的完整实例代码.zip','http://pan.baidu.com/s/1o8NJsL0','icon-download-alt',0,'2016-11-03 15:24:28','2016-11-03 15:24:29'),(17,'jd-gui反编译jar_复制源码为java文件(自己写的工具).rar','http://pan.baidu.com/s/1dE8mrap','icon-download-alt',0,'2016-11-03 15:24:47','2016-11-03 15:24:50'),(18,'jquery点击图片选中特效.rar','http://pan.baidu.com/s/1jIJNEq6','icon-download-alt',0,'2016-11-03 15:25:12','2016-11-03 15:25:13'),(19,'jQuery轻量级圆形进度指示器插件.zip','http://pan.baidu.com/s/1o8Lbz4m','icon-download-alt',0,'2016-11-03 15:27:51','2016-11-03 15:27:53'),(20,'lucene基于本地磁盘的文件索引Demo.zip','http://pan.baidu.com/s/1jI8suqy','icon-download-alt',0,'2016-11-03 15:28:11','2016-11-03 15:28:13'),(21,'lucene基于内存的检索Demo.zip','http://pan.baidu.com/s/1qXEB2DA','icon-download-alt',0,'2016-11-03 15:28:31','2016-11-03 15:28:32'),(22,'lucene索引——增删改查.zip','http://pan.baidu.com/s/1bo2kKCf','icon-download-alt',0,'2016-11-03 15:28:49','2016-11-03 15:28:50'),(23,'MongoDB环境搭建与实例代码测试.rar','http://pan.baidu.com/s/1o7OiXMM','icon-download-alt',0,'2016-11-03 15:29:09','2016-11-03 15:29:10'),(24,'orm框架 微信框架 自定义拦截器 自定义注解 数据源切换等.zip','http://pan.baidu.com/s/1jHMVoiU','icon-download-alt',0,'2016-11-03 15:29:29','2016-11-03 15:29:31'),(25,'pdf文件打印(freemarker作为pdf模板，通过参数替换、遍历显示效果)（java版 spring）.zip','http://pan.baidu.com/s/1kUWw8Vd','icon-download-alt',0,'2016-11-03 15:29:47','2016-11-03 15:29:49'),(26,'pluplaod文件分割上传Demo.zip','http://pan.baidu.com/s/1kV8Wpy3','icon-download-alt',0,'2016-11-03 15:30:05','2016-11-03 15:30:07'),(27,'SpringActiveMQ消息发送与接收(WEB版)-入门实例.zip','http://pan.baidu.com/s/1hsqL6vE','icon-download-alt',0,'2016-11-03 15:30:24','2016-11-03 15:30:26'),(28,'springbatch批处理(完整Demo).zip','http://pan.baidu.com/s/1hsgCgVq','icon-download-alt',0,'2016-11-03 15:30:47','2016-11-03 15:30:49'),(29,'springmvc mybatis集成—干净框架(直接使用).zip','http://pan.baidu.com/s/1i4XgFjR','icon-download-alt',0,'2016-11-03 15:31:09','2016-11-03 15:31:10'),(30,'springsecurity权限控制Demo.zip','http://pan.baidu.com/s/1i4MXsnr','icon-download-alt',0,'2016-11-03 15:31:30','2016-11-03 15:31:31'),(31,'spring_activemq消息发送接收(最终版-非WEB访问).zip','http://pan.baidu.com/s/1jIDcfII','icon-download-alt',0,'2016-11-03 15:31:47','2016-11-03 15:31:49'),(32,'spring_security3_详细配置(摘自网络).zip','http://pan.baidu.com/s/1kUAbWjl','icon-download-alt',0,'2016-11-03 15:32:09','2016-11-03 15:32:10'),(33,'SSO之CAS单点登录详细图文教程.zip','http://pan.baidu.com/s/1hrY4BBm','icon-download-alt',0,'2016-11-03 15:32:29','2016-11-03 15:32:30'),(34,'WebSocket消息聊天室Demo.zip','http://pan.baidu.com/s/1jI7CzAY','icon-download-alt',0,'2016-11-03 15:32:51','2016-11-03 15:32:52'),(35,'读取Excel工具类(包含流读取、文件读取).zip','http://pan.baidu.com/s/1skE92ET','icon-download-alt',0,'2016-11-03 15:33:09','2016-11-03 15:33:11'),(36,'断点 多线程下载实例.zip','http://pan.baidu.com/s/1miuHeIG','icon-download-alt',0,'2016-11-03 15:33:31','2016-11-03 15:33:32'),(37,'基于dwr消息推送Demo.zip','http://pan.baidu.com/s/1boRTogJ','icon-download-alt',0,'2016-11-03 15:33:47','2016-11-03 15:33:48'),(38,'手机_日期时间控件.zip','http://pan.baidu.com/s/1hs7G2HY','icon-download-alt',0,'2016-11-03 15:34:05','2016-11-03 15:34:07'),(39,'手机时间控件.zip','http://pan.baidu.com/s/1qXYou7M','icon-download-alt',0,'2016-11-03 15:34:25','2016-11-03 15:34:27'),(40,'数据采集—基于百度文库的抓取Demo.zip','http://pan.baidu.com/s/1cpBMeM','icon-download-alt',0,'2016-11-03 15:34:46','2016-11-03 15:34:47'),(41,'网页加载进度条-实例Demo.zip','http://pan.baidu.com/s/1pLViL9x','icon-download-alt',0,'2016-11-03 15:35:07','2016-11-03 15:35:08'),(42,'微信JSAPI支付完整Demo.zip','http://pan.baidu.com/s/1nvdehnj','icon-download-alt',0,'2016-11-03 15:35:29','2016-11-03 15:35:30'),(43,'微信JSAPI支付完整Demo第二版(新增关闭订单、查询订单、查询退款、下载对账单、申请退款).zip','http://pan.baidu.com/s/1i4VKKNN','icon-download-alt',0,'2016-11-03 15:35:47','2016-11-03 15:35:48'),(44,'异步发送email之demo.zip','http://pan.baidu.com/s/1o7IJv1S','icon-download-alt',0,'2016-11-03 15:36:05','2016-11-03 15:36:07'),(45,'银联PC网关支付demo(退款、对账文件、查询、撤销).zip','http://pan.baidu.com/s/1pKXAz0Z','icon-download-alt',0,'2016-11-03 15:36:25','2016-11-03 15:36:27'),(46,'支付宝PC及时到账支付Demo-JAVA.zip','http://pan.baidu.com/s/1pLyEKsz','icon-download-alt',0,'2016-11-03 15:36:44','2016-11-03 15:36:46'),(47,'支付宝PC支付完整Demo.zip','http://pan.baidu.com/s/1kUAbWjH','icon-download-alt',0,'2016-11-03 15:37:03','2016-11-03 15:37:05');

/*Table structure for table `leave_apply` */

DROP TABLE IF EXISTS `leave_apply`;

CREATE TABLE `leave_apply` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `process_instance_id` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `type` int(1) NOT NULL COMMENT '请假类型',
  `reason` varchar(100) NOT NULL COMMENT '请假事由',
  `user_id` bigint(11) NOT NULL COMMENT '申请人id',
  `start_date` datetime NOT NULL COMMENT '申请日期',
  `end_date` datetime NOT NULL COMMENT '结束日期',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='OA请假申请表';

/*Data for the table `leave_apply` */



/*Table structure for table `order_info` */

DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) NOT NULL,
  `total_fee` bigint(11) NOT NULL,
  `good_name` varchar(50) NOT NULL,
  `order_source` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order_info` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `sort` varchar(100) DEFAULT NULL,
  `icon` varchar(30) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `permission_code` varchar(50) NOT NULL COMMENT '所需权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`url`,`parent_id`,`sort`,`icon`,`type`,`permission_code`) values (1,'系统管理','#',0,'1','&#xe61d;','1','sys:mgt'),(2,'用户管理','/user/toUserListView',1,'2',NULL,'1','user:mgt'),(4,'角色管理','/role/toRoleListView',1,'4',NULL,'1','role:mgt'),(5,'权限管理','/permission/toListView',1,'3',NULL,'1','permission:mgt'),(6,'微信开发','#',0,'1','&#xe694;','1','wechat:mgt'),(7,'模板消息','/wechat/template/toTemplateMsgView',6,'2',NULL,'1','template:msg:send'),(9,'实时监控','#',0,'1','&#xe695;','1','monitor:mgt'),(10,'tomcat日志实时监控','/socket/tomcatlog',9,'2',NULL,'1','monitor:tomcat:log'),(11,'消息推送','#',0,'1','&#xe6ce;','1','socket:push:mgt'),(12,'在线聊天','#',0,'1','&#xe622;','1','chat:mgt'),(13,'netty实现在线聊天','/chat/toChatView',12,'2','icon-rss','1','chat:netty:service'),(14,'activiti工作流','#',0,'1','&#xe637;','1','activiti:mgt'),(15,'OA请假申请','/oa/toOaApplyView',14,'3','icon-rss','1','activiti:oa:apply'),(16,'已部署工作流程','/activiti/toProcessListView',14,'2','icon-rss','1','activiti:process:list'),(17,'我发起的请假流程','/oa/toMyLeaveApplyView',14,'4','icon-rss','1','activiti:myprocess:leaveapply'),(18,'部门领导审批','/oa/toDeptleaderAuditView',14,'5','icon-rss','1','activiti:deptleader:audit'),(19,'请假调整申请','/oa/toMyLeaveApplyTurndownView',14,'6','icon-rss','1','activiti:myleaveapply:modify'),(20,'我的请假历史','/oa/toMyLeaveApplyHistoryView',14,'7','icon-rss','1','activiti:myleaveapplyed:history'),(21,'人事审批','/oa/toHrAuditView',14,'8','icon-rss','1','activiti:hr:audit'),(22,'推送消息给当前用户','/socket/userpush',11,'2',NULL,'1','socket:user:msgpush'),(23,'推送消息给所有用户','/socket/userpush/all',11,'3',NULL,'1','socket:alluser:msgpush');

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '权限名称',
  `code` varchar(50) NOT NULL COMMENT '权限代码',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态，0：可用，1：不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`name`,`code`,`description`,`parent_id`,`status`) values (1,'系统管理','sys:mgt','对系统模块的管理',-1,0),(2,'人员管理','user:mgt','对人员进行管理',1,0),(3,'角色管理','role:mgt','对角色进行管理',1,0),(4,'权限管理','permission:mgt','对权限进行管理',1,0),(9,'查看用户列表','user:list','可以查看用户列表',2,0),(10,'编辑用户信息','user:edit','可以编辑用户信息',9,0),(11,'删除用户','user:delete','可以删除用户',9,0),(12,'查看角色列表','role:list','可以查看角色列表',3,0),(13,'查看权限列表','permission:list',NULL,4,0),(14,'新增角色','role:add','新增角色信息',12,0),(15,'编辑角色','role:edit','编辑角色信息',12,0),(16,'删除角色','role:delete','可以删除角色',12,0),(17,'新增权限','permission:add','可以添加新权限',13,0),(18,'编辑权限','permission:edit','修改权限信息',13,0),(19,'删除权限','permission:delete','可以删除权限数据',13,0),(20,'添加用户','user:add','可以添加新的用户',9,0),(21,'微信开发','wechat:mgt','微信相关功能开发演示',-1,0),(22,'微信模板消息','template:msg:send','测试发送微信模板消息',21,0),(23,'监控管理','monitor:mgt','监控管理',-1,0),(24,'tomcat日志监控','monitor:tomcat:log','可以查看服务器上tomcat的实时打印日志',23,0),(25,'服务器消息推送','socket:push:mgt','服务器消息推送',-1,0),(26,'在线聊天','chat:mgt','在线聊天',-1,0),(27,'netty实现在线聊天','chat:netty:service','netty实现在线聊天',26,0),(28,'activiti工作流','activiti:mgt','activiti工作流相关技术',-1,0),(29,'OA请假流程','activiti:oa:apply','基于工作流实现OA请假',28,0),(30,'已部署的工作流','activiti:process:list','已部署的工作流',28,0),(31,'我发起的请假流程','activiti:myprocess:leaveapply','我发起的请假流程',28,0),(32,'部门领导审批','activiti:deptleader:audit','部门领导审批',28,0),(33,'请假调整申请','activiti:myleaveapply:modify','重新修改被驳回的请假申请',28,0),(34,'我的请假历史','activiti:myleaveapplyed:history','我的请假历史',28,0),(35,'人事审批请假申请','activiti:hr:audit','人事审批请假申请',28,0),(39,'推送消息给自己','socket:user:msgpush','推送消息给自己',25,0),(40,'推送消息给所有人','socket:alluser:msgpush','推送消息给所有人',25,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(24) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`description`,`status`,`update_time`,`create_time`) values (1,'超级管理员','最高级别权限管理员',0,'2016-08-01 15:17:52','2016-08-01 15:17:53'),(2,'员工','基层员工',0,'2016-08-01 15:18:21','2016-08-01 15:18:23'),(3,'财务','财务部角色',0,'2016-08-03 13:21:56','2016-08-03 13:21:58'),(4,'人事','人事',0,'2017-03-21 13:19:34','2017-03-21 13:19:34'),(5,'部门经理','部门经理',0,'2017-03-21 13:20:47','2017-03-21 13:20:47'),(6,'出纳员','出纳员',0,'2017-03-21 13:22:06','2017-03-21 13:22:06'),(7,'采购经理','采购经理',0,'2017-03-21 13:22:23','2017-03-21 13:22:23'),(8,'总经理','总经理',0,'2017-03-21 13:23:03','2017-03-21 13:23:03');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1119 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`id`,`role_id`,`permission_id`) values (226,3,-1),(227,3,1),(228,3,2),(688,5,-1),(689,5,28),(690,5,30),(691,5,32),(830,4,-1),(831,4,28),(832,4,30),(833,4,35),(870,2,-1),(871,2,1),(872,2,2),(873,2,28),(874,2,29),(875,2,30),(876,2,31),(877,2,33),(878,2,34),(1085,1,-1),(1086,1,1),(1087,1,2),(1088,1,9),(1089,1,10),(1090,1,11),(1091,1,20),(1092,1,3),(1093,1,12),(1094,1,14),(1095,1,15),(1096,1,16),(1097,1,4),(1098,1,13),(1099,1,17),(1100,1,18),(1101,1,19),(1102,1,21),(1103,1,22),(1104,1,23),(1105,1,24),(1106,1,25),(1107,1,39),(1108,1,40),(1109,1,26),(1110,1,27),(1111,1,28),(1112,1,29),(1113,1,30),(1114,1,31),(1115,1,32),(1116,1,33),(1117,1,34),(1118,1,35);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL COMMENT '登录用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '账号状态',
  `open_id` varchar(50) DEFAULT NULL COMMENT '微信openid',
  `from_system` tinyint(4) NOT NULL DEFAULT '0' COMMENT '来源哪个系统。(后期会进行与第三方系统做用户数据对接，默认为当前系统用户中心)',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`password`,`real_name`,`status`,`open_id`,`from_system`,`create_time`,`update_time`) values (1,'admin','e10adc3949ba59abbe56e057f20f883e','超级管理员',0,NULL,0,'2016-08-03 13:22:46','2017-07-20 18:53:13'),(3,'bumenjingli','e10adc3949ba59abbe56e057f20f883e','部门经理',0,NULL,0,'2017-04-01 11:36:59','2017-04-01 11:36:59'),(5,'renshi','e10adc3949ba59abbe56e057f20f883e','小欣',0,NULL,0,'2017-05-19 14:34:32','2017-07-11 16:40:40'),(6,'renshi02','e10adc3949ba59abbe56e057f20f883e','小花',2,NULL,0,'2017-05-19 15:20:50','2017-07-11 17:15:53'),(31,'yuangong01','e10adc3949ba59abbe56e057f20f883e','张三',1,NULL,0,'2017-07-11 16:33:26','2017-07-11 17:16:00'),(34,'chunayuan','e10adc3949ba59abbe56e057f20f883e','李斯',0,NULL,0,'2017-07-11 16:41:36','2017-07-11 16:41:36'),(36,'caiwu','e10adc3949ba59abbe56e057f20f883e','张华',0,NULL,0,'2017-07-11 16:42:17','2017-07-11 16:42:17'),(38,'caigoujingli','e10adc3949ba59abbe56e057f20f883e','李天',0,NULL,0,'2017-07-11 16:42:56','2017-07-11 16:42:56'),(39,'zongjingli','e10adc3949ba59abbe56e057f20f883e','王化',2,NULL,0,'2017-07-11 16:43:18','2017-07-11 17:15:41');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values (68,3,5),(115,5,4),(117,34,6),(119,36,3),(121,38,7),(127,39,8),(128,6,4),(129,31,2),(131,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
