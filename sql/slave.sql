/*
* 
*      数据库初始化脚本
*	数据库：mysql5.6
*   脚本版本：V1.0
*
*/

DROP DATABASE IF EXISTS `phynos_slave`;
CREATE DATABASE IF NOT EXISTS `phynos_slave` DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE `phynos_slave`;

SET FOREIGN_KEY_CHECKS = 0;

-- 用户系统
drop table if exists `sys_device_data`;
create table `sys_device_data` (
	`id` int not null auto_increment,
	`created_datetime` DateTime not null comment '数据创建时间',
	`username` varchar(20) not null comment '登录-用户名',
	`email` varchar(50) not null comment '登录-邮箱',
	`mobile_phone` varchar(20) not null comment '登录-移动电话',
	`password` varchar(20) not null comment '登录密码',
	`email_validated` bit not null comment '邮箱验证是否通过',
	`mobile_phone_validated` bit not null comment '手机验证是否通过',
	`nickname` varchar(20) not null comment '昵称',
	`enabled` bit not null comment '启用状态',
	primary key (`id`),
	Constraint `uidx_sys_user_client_1` unique(`email`),
	Constraint `uidx_sys_user_client_2` unique(`mobile_phone`)
) comment = '数据表';

SET FOREIGN_KEY_CHECKS = 1;