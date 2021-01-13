/*
* 
*      数据库初始化脚本
*	数据库：mysql5.6
*   脚本版本：V1.0
*
*/

DROP DATABASE IF EXISTS `phynos_slave`;
CREATE DATABASE IF NOT EXISTS `phynos_slave` DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `phynos_slave`;

SET FOREIGN_KEY_CHECKS = 0;

-- 用户系统
drop table if exists `sys_device_data`;
create table `sys_device_data` (
	`id` int not null auto_increment,
	`created_datetime` DateTime not null comment '数据创建时间',
	`sn` varchar(20) not null comment '序列号',
	`dyear` int not null comment '数据年',
	`dmonth` int not null comment '数据月',
	`dday` int not null comment '数据日',
	`data_name` varchar(20) not null comment '数据名称',
	`data_value` varchar(20) not null comment '数据值',
	`data_expr` varchar(20) not null comment '数据表达式',
	`enabled` bit not null comment '启用状态',
	primary key (`id`),
	Constraint `uidx_sys_device_data_1` unique(`email`),
	Constraint `uidx_sys_device_data_2` unique(`mobile_phone`)
) comment = '数据表';

SET FOREIGN_KEY_CHECKS = 1;