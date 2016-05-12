/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.25-log : Database - rms2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rms2` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rms2`;

/*Table structure for table `cqupt_college` */

DROP TABLE IF EXISTS `cqupt_college`;

CREATE TABLE `cqupt_college` (
  `college_id` varchar(255) NOT NULL,
  `college_name` varchar(255) NOT NULL,
  PRIMARY KEY (`college_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cqupt_college` */

insert  into `cqupt_college`(`college_id`,`college_name`) values ('1','经济管理学院');
insert  into `cqupt_college`(`college_id`,`college_name`) values ('2','传媒艺术学院');
insert  into `cqupt_college`(`college_id`,`college_name`) values ('3','通信学院');

/*Table structure for table `cqupt_role` */

DROP TABLE IF EXISTS `cqupt_role`;

CREATE TABLE `cqupt_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) NOT NULL COMMENT '角色名字',
  `role_level_id` int(11) DEFAULT NULL COMMENT '参照''role_level''的''level_id''',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述信息',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `cqupt_role` */

insert  into `cqupt_role`(`role_id`,`role_name`,`role_level_id`,`description`) values (1,'普通教师',NULL,'一般教师，可以进行自己数据录入');
insert  into `cqupt_role`(`role_id`,`role_name`,`role_level_id`,`description`) values (2,'科研管理员',NULL,'普通管理员，可以审批他人数据');
insert  into `cqupt_role`(`role_id`,`role_name`,`role_level_id`,`description`) values (3,'系统管理员',NULL,'最高权限的管理员');
insert  into `cqupt_role`(`role_id`,`role_name`,`role_level_id`,`description`) values (6,'植物学家',NULL,'一叶知秋，叶问');
insert  into `cqupt_role`(`role_id`,`role_name`,`role_level_id`,`description`) values (7,'程序员',NULL,'php是世界上最好的编程语言，好像java和c++也挺6的');
insert  into `cqupt_role`(`role_id`,`role_name`,`role_level_id`,`description`) values (8,'摄影师',NULL,'摄影是用光的艺术');

/*Table structure for table `cqupt_user` */

DROP TABLE IF EXISTS `cqupt_user`;

CREATE TABLE `cqupt_user` (
  `user_id` varchar(255) NOT NULL COMMENT '登录时的用户名',
  `college_id` varchar(255) NOT NULL COMMENT '学院id，参照''cqupt_college''的''college_id''',
  `department` varchar(255) DEFAULT NULL COMMENT '部门',
  `user_name` varchar(255) NOT NULL COMMENT '用户真实姓名',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `origin` varchar(255) DEFAULT NULL COMMENT '籍贯',
  `nationality` varchar(255) DEFAULT NULL COMMENT '国籍',
  `birthday` varchar(255) DEFAULT NULL COMMENT '出生日期',
  `political_status` varchar(255) DEFAULT NULL COMMENT '政治地位',
  `time_join_party` varchar(255) DEFAULT NULL COMMENT '入党时间',
  `time_begin_cqupt` varchar(255) DEFAULT NULL COMMENT '进入重邮时间',
  `time_begin_work` varchar(255) DEFAULT NULL COMMENT '参加工作时间',
  `first_degree` varchar(255) DEFAULT NULL COMMENT '第一学位',
  `first_professional_name` varchar(255) DEFAULT NULL COMMENT '第一专业名称',
  `first_graduate_school` varchar(255) DEFAULT NULL COMMENT '第一毕业院校',
  `last_degree` varchar(255) DEFAULT NULL COMMENT '最后学位',
  `last_professional_name` varchar(255) DEFAULT NULL COMMENT '最后专业名称',
  `last_graduate_school` varchar(255) DEFAULT NULL COMMENT '最后毕业院校',
  `last_academic` varchar(255) DEFAULT NULL COMMENT '最后学历',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cqupt_user` */

insert  into `cqupt_user`(`user_id`,`college_id`,`department`,`user_name`,`gender`,`origin`,`nationality`,`birthday`,`political_status`,`time_join_party`,`time_begin_cqupt`,`time_begin_work`,`first_degree`,`first_professional_name`,`first_graduate_school`,`last_degree`,`last_professional_name`,`last_graduate_school`,`last_academic`) values ('1','2','辅导员','张三','男','重庆','中国','','党员','','2016-05-10','2016-05-10','本科','信息管理','','','','重邮','');

/*Table structure for table `proofs` */

DROP TABLE IF EXISTS `proofs`;

CREATE TABLE `proofs` (
  `proof_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '旁证材料id',
  `record_id` varchar(255) NOT NULL COMMENT '记录id，参照''research_record''的''record_id''',
  `time_proof_upload` datetime NOT NULL COMMENT '上传时间',
  `desc_proof` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `proof_path` varchar(255) NOT NULL COMMENT '材料路径',
  `upload_proof_name` varchar(255) DEFAULT NULL COMMENT '上传材料名称',
  `upload_real_name` varchar(255) DEFAULT NULL COMMENT '保存材料的真实名称',
  `upload_content_type` varchar(255) DEFAULT NULL COMMENT '材料文本类型',
  PRIMARY KEY (`proof_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `proofs` */

insert  into `proofs`(`proof_id`,`record_id`,`time_proof_upload`,`desc_proof`,`proof_path`,`upload_proof_name`,`upload_real_name`,`upload_content_type`) values (11,'20160501213428653','2016-05-03 00:00:00',NULL,'E:/RMS旁证材料/p1605032038317193.jpg','D7200_01_kx_1920x1080.jpg','p1605032038317193.jpg','image/jpeg');
insert  into `proofs`(`proof_id`,`record_id`,`time_proof_upload`,`desc_proof`,`proof_path`,`upload_proof_name`,`upload_real_name`,`upload_content_type`) values (12,'20160501213428653','2016-05-03 00:00:00',NULL,'E:/RMS旁证材料/p1605032131178879.txt','new 1.txt','p1605032131178879.txt','text/plain');
insert  into `proofs`(`proof_id`,`record_id`,`time_proof_upload`,`desc_proof`,`proof_path`,`upload_proof_name`,`upload_real_name`,`upload_content_type`) values (17,'20160504110038872','2016-05-04 00:00:00',NULL,'E:/RMS旁证材料/p1605041100386302.torrent','李安-家庭三部曲-《推手》《喜宴》《饮食男女》.torrent','p1605041100386302.torrent','application/download');

/*Table structure for table `research_class` */

DROP TABLE IF EXISTS `research_class`;

CREATE TABLE `research_class` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '科研类别id',
  `class_name` varchar(255) NOT NULL COMMENT '类别名称',
  `class_des` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `class_remark` varchar(255) DEFAULT NULL COMMENT '提示信息',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '类别是否删除，0为未删除，1为删除',
  `order` int(11) DEFAULT NULL COMMENT '类别展示顺序',
  `submittime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '类别提交时间',
  `parent_id` int(11) NOT NULL COMMENT '类别的父类id',
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `research_class` */

insert  into `research_class`(`class_id`,`class_name`,`class_des`,`class_remark`,`is_delete`,`order`,`submittime`,`parent_id`) values (1,'教学类',NULL,NULL,0,1,'2016-04-13 15:02:55',0);
insert  into `research_class`(`class_id`,`class_name`,`class_des`,`class_remark`,`is_delete`,`order`,`submittime`,`parent_id`) values (2,'学生获奖类',NULL,NULL,0,0,'2016-04-13 15:03:28',0);
insert  into `research_class`(`class_id`,`class_name`,`class_des`,`class_remark`,`is_delete`,`order`,`submittime`,`parent_id`) values (3,'工商类修改','工商类描述信息','',0,0,'2016-04-13 16:39:13',0);
insert  into `research_class`(`class_id`,`class_name`,`class_des`,`class_remark`,`is_delete`,`order`,`submittime`,`parent_id`) values (4,'教学类1',NULL,NULL,0,1,'2016-04-14 09:29:19',1);
insert  into `research_class`(`class_id`,`class_name`,`class_des`,`class_remark`,`is_delete`,`order`,`submittime`,`parent_id`) values (5,'教学类2',NULL,NULL,0,2,'2016-04-14 09:29:49',1);
insert  into `research_class`(`class_id`,`class_name`,`class_des`,`class_remark`,`is_delete`,`order`,`submittime`,`parent_id`) values (6,'工商类1',NULL,NULL,0,1,'2016-04-14 09:30:15',3);
insert  into `research_class`(`class_id`,`class_name`,`class_des`,`class_remark`,`is_delete`,`order`,`submittime`,`parent_id`) values (12,'一级测试','',NULL,0,0,'2016-04-24 12:44:20',0);
insert  into `research_class`(`class_id`,`class_name`,`class_des`,`class_remark`,`is_delete`,`order`,`submittime`,`parent_id`) values (15,'二级科研','科研类别描述信息','科研类别备注信息',0,0,'2016-04-24 12:53:05',12);

/*Table structure for table `research_data` */

DROP TABLE IF EXISTS `research_data`;

CREATE TABLE `research_data` (
  `record_id` varchar(255) NOT NULL COMMENT '记录id，参考''research_record''的''record_id''',
  `field_id` int(11) NOT NULL COMMENT '字段id，参照''research_field''的''field_id''',
  `value` varchar(255) DEFAULT NULL COMMENT '字段的值',
  PRIMARY KEY (`record_id`,`field_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `research_data` */

insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160501141855562',1,'测试值1');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160501141855562',2,'测试值2');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160501141855562',6,'测试');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160501213428653',1,'测试值1修改');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160501213428653',2,'测试值2');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160501213428653',6,'测试值3修改');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160501213428653',7,'测试值4');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160504105416848',1,'测试值1');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160504105416848',2,'测试值2');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160504105442350',1,'测试值1');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160504105442350',2,'测试值2');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160504105541663',1,'测试值1');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160504105541663',2,'测试值2');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160504110038872',1,'测试值1');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160504110038872',2,'测试值2');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509103233205',1,'测试值1修改');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509103233205',2,'测试值2');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509103344757',1,'测试值1修改');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509103344757',2,'测试值2');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('2016050910373241',1,'测试值1修改');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('2016050910373241',2,'测试值2');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509103813923',1,'测试值1sdaf');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509103813923',2,'测试值2fdsa');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509103833689',1,'测试值1sdaf');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509103833689',2,'测试值2fdsa');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509103833689',6,'测试值3');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509103833689',7,'测试值4');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509104104568',1,'测试值1');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509104104568',2,'测试值2fdsa');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509104104568',6,'测试值3');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('2016050910411290',1,'测试值1修改');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('2016050910411290',2,'测试值2fdsa');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('2016050910411290',6,'测试值3');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509104122506',1,'测试值1修改');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509104122506',2,'测试值2fdsa');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509104122506',6,'测试值3');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509104216336',1,'测试值1');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509104216336',2,'测试值2fdsa');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509104216336',6,'测试值3修改');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509104216336',7,'测试值4');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509104226955',1,'测试值1');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509104226955',2,'测试值2');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509104226955',6,'测试值3');
insert  into `research_data`(`record_id`,`field_id`,`value`) values ('20160509104226955',7,'测试值4');

/*Table structure for table `research_field` */

DROP TABLE IF EXISTS `research_field`;

CREATE TABLE `research_field` (
  `field_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字段id',
  `class_id` int(11) DEFAULT NULL COMMENT '字段所属类别id，参考''research_class''的''class_id''',
  `field_name` varchar(255) NOT NULL COMMENT '字段在数据库中的名字',
  `field_des` varchar(255) NOT NULL COMMENT '字段在页面展示的名字',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '字段是否删除，0为未删除，1为删除',
  `submittime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '字段提交时间',
  `order` int(11) NOT NULL COMMENT '字段的展现顺序值',
  `is_null` int(11) DEFAULT '0' COMMENT '字段是否允许为null,0为不允许null，1为允许null',
  PRIMARY KEY (`field_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `research_field` */

insert  into `research_field`(`field_id`,`class_id`,`field_name`,`field_des`,`is_delete`,`submittime`,`order`,`is_null`) values (1,15,'filed_test','字段测试',1,'2016-05-12 16:30:16',0,0);
insert  into `research_field`(`field_id`,`class_id`,`field_name`,`field_des`,`is_delete`,`submittime`,`order`,`is_null`) values (2,15,'filed_test_t','字段测试2',0,'2016-05-12 16:30:16',1,0);
insert  into `research_field`(`field_id`,`class_id`,`field_name`,`field_des`,`is_delete`,`submittime`,`order`,`is_null`) values (6,15,'filed_test_f','字段测试3',0,'2016-05-12 16:30:16',2,0);
insert  into `research_field`(`field_id`,`class_id`,`field_name`,`field_des`,`is_delete`,`submittime`,`order`,`is_null`) values (7,15,'filed_test_4','字段测试4',0,'2016-05-12 16:30:16',3,1);
insert  into `research_field`(`field_id`,`class_id`,`field_name`,`field_des`,`is_delete`,`submittime`,`order`,`is_null`) values (8,15,'field_test5','字段测试5',0,'2016-05-12 16:30:16',4,1);

/*Table structure for table `research_person` */

DROP TABLE IF EXISTS `research_person`;

CREATE TABLE `research_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '逻辑主键',
  `record_id` varchar(255) DEFAULT NULL COMMENT '科研记录的id，参照''research_record''的''record_id''',
  `member_id` varchar(255) DEFAULT NULL COMMENT '成员信息的id，参照''cqupt_user''的''user_id''',
  `member_name` varchar(255) DEFAULT NULL COMMENT '成员名字',
  `orders` varchar(255) DEFAULT NULL COMMENT '排名情况',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

/*Data for the table `research_person` */

insert  into `research_person`(`id`,`record_id`,`member_id`,`member_name`,`orders`,`remarks`) values (40,'20160504110038872',NULL,'赵N','2','孙R');
insert  into `research_person`(`id`,`record_id`,`member_id`,`member_name`,`orders`,`remarks`) values (41,'20160501213428653',NULL,'赵修改','2','备注修改');
insert  into `research_person`(`id`,`record_id`,`member_id`,`member_name`,`orders`,`remarks`) values (42,'20160501213428653',NULL,'钱','1','修改');
insert  into `research_person`(`id`,`record_id`,`member_id`,`member_name`,`orders`,`remarks`) values (43,'20160501141855562',NULL,'冯迪','1','备注');
insert  into `research_person`(`id`,`record_id`,`member_id`,`member_name`,`orders`,`remarks`) values (44,'20160504105416848',NULL,'赵N','2','孙R');
insert  into `research_person`(`id`,`record_id`,`member_id`,`member_name`,`orders`,`remarks`) values (45,'20160504105442350',NULL,'赵N','2','孙R');
insert  into `research_person`(`id`,`record_id`,`member_id`,`member_name`,`orders`,`remarks`) values (46,'20160504105541663',NULL,'赵N','2','孙R');

/*Table structure for table `research_record` */

DROP TABLE IF EXISTS `research_record`;

CREATE TABLE `research_record` (
  `record_id` varchar(255) NOT NULL COMMENT '记录id',
  `class_id` int(11) NOT NULL COMMENT '记录所属类别id，参照''research_class''的''class_id''',
  `return_reason` varchar(255) DEFAULT NULL COMMENT '拒绝原因',
  `status` int(11) DEFAULT NULL COMMENT '审批状态',
  `submit_user_id` varchar(255) DEFAULT NULL COMMENT '提交者id，参照''cqupt_user''的''user_id''',
  `approved_user_id` varchar(255) DEFAULT NULL COMMENT '审批者id，参照''cqupt_user''的''user_id''',
  `submit_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `research_record` */

insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('20160501141855562',15,'测试拒绝原因',0,'1','1','2016-05-01 14:18:55');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('20160501213428653',15,NULL,2,'1','1','2016-05-01 21:34:28');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('20160504105416848',15,NULL,2,'1','1','2016-05-04 10:54:16');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('20160504105442350',15,NULL,3,'1','1','2016-05-04 10:54:42');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('20160504105541663',15,NULL,3,'1','1','2016-05-04 10:55:41');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('20160504110038872',15,NULL,0,'1',NULL,'2016-05-04 11:00:38');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('20160509103233205',15,'信息不全',3,'1','1','2016-05-09 10:32:33');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('20160509103344757',15,NULL,2,'1','1','2016-05-09 10:33:44');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('2016050910373241',15,NULL,2,'1','1','2016-05-09 10:37:32');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('20160509103813923',15,NULL,2,'1','1','2016-05-09 10:38:13');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('20160509103833689',15,NULL,2,'1','1','2016-05-09 10:38:33');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('20160509104104568',15,NULL,2,'1','1','2016-05-09 10:41:04');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('2016050910411290',15,NULL,2,'1','1','2016-05-09 10:41:12');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('20160509104122506',15,NULL,2,'1','1','2016-05-09 10:41:22');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('20160509104216336',15,NULL,2,'1','1','2016-05-09 10:42:16');
insert  into `research_record`(`record_id`,`class_id`,`return_reason`,`status`,`submit_user_id`,`approved_user_id`,`submit_time`) values ('20160509104226955',15,NULL,1,'1',NULL,'2016-05-09 10:42:26');

/*Table structure for table `resource_info` */

DROP TABLE IF EXISTS `resource_info`;

CREATE TABLE `resource_info` (
  `resourceId` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `resourceName` varchar(50) NOT NULL COMMENT '资源名称',
  `resourceUrl` longtext COMMENT '资源路径',
  `resourceRemark` longtext COMMENT '说明信息',
  `parentId` int(11) DEFAULT NULL COMMENT '资源父类id，参照''resource_info''的''resourceId''',
  PRIMARY KEY (`resourceId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `resource_info` */

insert  into `resource_info`(`resourceId`,`resourceName`,`resourceUrl`,`resourceRemark`,`parentId`) values (1,'管理个人信息',NULL,NULL,0);
insert  into `resource_info`(`resourceId`,`resourceName`,`resourceUrl`,`resourceRemark`,`parentId`) values (2,'管理个人基本信息','../common/manageuserinfo.html',NULL,1);
insert  into `resource_info`(`resourceId`,`resourceName`,`resourceUrl`,`resourceRemark`,`parentId`) values (3,'修改密码','../common/modifypw.html',NULL,1);
insert  into `resource_info`(`resourceId`,`resourceName`,`resourceUrl`,`resourceRemark`,`parentId`) values (4,'系统管理员功能',NULL,NULL,0);
insert  into `resource_info`(`resourceId`,`resourceName`,`resourceUrl`,`resourceRemark`,`parentId`) values (5,'角色权限管理','../system/roleandauthority.html',NULL,4);
insert  into `resource_info`(`resourceId`,`resourceName`,`resourceUrl`,`resourceRemark`,`parentId`) values (6,'用户角色管理','../system/manageuserrole.html',NULL,4);
insert  into `resource_info`(`resourceId`,`resourceName`,`resourceUrl`,`resourceRemark`,`parentId`) values (7,'管理科研类别','../system/manageresearchclass.html',NULL,4);
insert  into `resource_info`(`resourceId`,`resourceName`,`resourceUrl`,`resourceRemark`,`parentId`) values (8,'管理科研动态字段','../system/managedynamicfield.html',NULL,4);

/*Table structure for table `role_college` */

DROP TABLE IF EXISTS `role_college`;

CREATE TABLE `role_college` (
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `collegeId` varchar(255) NOT NULL COMMENT '学院id',
  PRIMARY KEY (`roleId`,`collegeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_college` */

insert  into `role_college`(`roleId`,`collegeId`) values (1,'1');

/*Table structure for table `role_level` */

DROP TABLE IF EXISTS `role_level`;

CREATE TABLE `role_level` (
  `level_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '等级id',
  `role_level_name` varchar(255) DEFAULT NULL COMMENT '角色等级名称',
  `role_level_description` varchar(255) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`level_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `role_level` */

insert  into `role_level`(`level_id`,`role_level_name`,`role_level_description`) values (1,'普通教师',NULL);
insert  into `role_level`(`level_id`,`role_level_name`,`role_level_description`) values (2,'学院管理员',NULL);
insert  into `role_level`(`level_id`,`role_level_name`,`role_level_description`) values (3,'学校管理员',NULL);
insert  into `role_level`(`level_id`,`role_level_name`,`role_level_description`) values (4,'系统管理员',NULL);

/*Table structure for table `role_purview_dynamic` */

DROP TABLE IF EXISTS `role_purview_dynamic`;

CREATE TABLE `role_purview_dynamic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '逻辑主键',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `class_id` int(11) NOT NULL COMMENT '类别id',
  `input` tinyint(1) DEFAULT '0' COMMENT '录入权限',
  `manage` tinyint(1) DEFAULT '0' COMMENT '管理个人科研权限',
  `approve` tinyint(1) DEFAULT '0' COMMENT '审批权限',
  `statistics` tinyint(1) DEFAULT '0' COMMENT '统计权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `role_purview_dynamic` */

insert  into `role_purview_dynamic`(`id`,`role_id`,`class_id`,`input`,`manage`,`approve`,`statistics`) values (1,1,4,0,0,0,0);
insert  into `role_purview_dynamic`(`id`,`role_id`,`class_id`,`input`,`manage`,`approve`,`statistics`) values (2,1,5,0,0,0,0);
insert  into `role_purview_dynamic`(`id`,`role_id`,`class_id`,`input`,`manage`,`approve`,`statistics`) values (3,1,6,0,0,0,0);
insert  into `role_purview_dynamic`(`id`,`role_id`,`class_id`,`input`,`manage`,`approve`,`statistics`) values (4,1,15,1,1,1,1);

/*Table structure for table `role_purview_fixed` */

DROP TABLE IF EXISTS `role_purview_fixed`;

CREATE TABLE `role_purview_fixed` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '逻辑主键',
  `roleId` int(11) NOT NULL COMMENT '角色id，参照''cqupt_role''',
  `purviewId` int(11) NOT NULL COMMENT '权限id，参照''resource_info''的''resource_id''',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `role_purview_fixed` */

insert  into `role_purview_fixed`(`id`,`roleId`,`purviewId`) values (3,1,1);
insert  into `role_purview_fixed`(`id`,`roleId`,`purviewId`) values (4,1,4);
insert  into `role_purview_fixed`(`id`,`roleId`,`purviewId`) values (13,1,5);
insert  into `role_purview_fixed`(`id`,`roleId`,`purviewId`) values (18,1,2);
insert  into `role_purview_fixed`(`id`,`roleId`,`purviewId`) values (19,1,3);
insert  into `role_purview_fixed`(`id`,`roleId`,`purviewId`) values (20,1,6);
insert  into `role_purview_fixed`(`id`,`roleId`,`purviewId`) values (21,1,7);
insert  into `role_purview_fixed`(`id`,`roleId`,`purviewId`) values (22,1,8);

/*Table structure for table `user_login` */

DROP TABLE IF EXISTS `user_login`;

CREATE TABLE `user_login` (
  `user_id` varchar(255) NOT NULL COMMENT '用户id',
  `user_pw` varchar(255) NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_login` */

insert  into `user_login`(`user_id`,`user_pw`) values ('1','76A68763D367BAFD');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '逻辑主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id，参考''cqupt_user''',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id,参考''cqupt_role''',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`) values (1,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
