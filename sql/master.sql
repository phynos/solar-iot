/*
* 
*      数据库初始化脚本
*	数据库：mysql5.6
*   脚本版本：V1.0
*
*/

DROP DATABASE IF EXISTS `phynos`;
CREATE DATABASE IF NOT EXISTS `phynos` DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE `phynos`;

SET FOREIGN_KEY_CHECKS = 0;

-- 用户系统
drop table if exists `sys_user_client`;
create table `sys_user_client` (
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
) comment = '终端用户表';
create unique index idx_sys_user_client_1 on `sys_user_client` (`username`);
-- 用户登录日志表
drop table if exists `sys_user_client_login_log`;
create table `sys_user_client_login_log` (
	`id` int not null auto_increment,
	`user_client_id` int not null comment '用户id',
	`login_datetime` DateTime not null comment '登录时间',
	`platform` int not null comment '登录平台',
	Constraint `fk_user_client_login_log_uid` Foreign Key(`user_client_id`) References `sys_user_client`(`Id`),
	primary key (`id`)
) comment = '用户登录日志表';
create index idx_sys_user_client_login_log_1 on `sys_user_client_login_log` (`user_client_id`);


-- 管理系统
drop table if exists `sys_user_admin`;
create table `sys_user_admin` (
	`id` int not null auto_increment,
	`created_datetime` DateTime not null comment '数据创建时间',
	`username` varchar(20) not null comment '登录-用户名',
	`sex` varchar(50) default '' comment '性别，绑定字典',
	`email` varchar(50) not null comment '登录-邮箱',
	`mobile_phone` varchar(20) not null comment '登录-移动电话',	
	`password` varchar(20) not null comment '登录密码',
	`email_validated` bit not null comment '邮箱验证是否通过',
	`mobile_phone_validated` bit not null comment '手机验证是否通过',	
	`nickname` varchar(20) not null comment '昵称',
	`enabled` bit not null comment '启用状态',
	primary key (`id`)
) comment = '管理用户表格';

insert into `sys_user_admin` values(1,'2018-07-16 16:30:00','admin','','phynos@126.com','1111111','admin',true,true,'admin',true);

-- 表：登录日志
drop table if exists `sys_user_admin_login_log`;
create table `sys_user_admin_login_log` (
	`id` int not null auto_increment,
	`user_admin_id` int not null comment '用户id',
	`login_datetime` DateTime not null comment '登录时间',
	`platform` int not null comment '登录平台',
	Constraint `fk_sys_user_admin_login_log_uid` Foreign Key(`user_admin_id`) References `sys_user_admin`(`Id`),
	primary key (`id`)
) comment = '管理用户登录日志表';
create index idx_sys_user_admin_login_log_1 on `sys_user_admin_login_log` (`user_admin_id`);

-- 权限系统  

-- 表：角色
drop table if exists `sys_role`;
create table `sys_role` (
	`id` int not null auto_increment,
	`role_name` varchar(20) not null comment '角色名称',
	`role_key` varchar(100) not null comment '角色字符',
	`role_type` int not null comment '角色类型', 
	`remark` varchar(200) null comment '描述信息',
	primary key (`id`),
	unique (role_key)
) comment = '角色';
-- 表：用户角色绑定（多对多）
drop table if exists `sys_user_admin_role`;
create table `sys_user_admin_role` (
	`id` int not null auto_increment,
	`role_id` int not null comment '角色id',
	`user_admin_id` int not null comment '管理员用户id', 
	primary key (`id`),
	Constraint `fk_sys_user_admin_role_rid` Foreign Key(`role_id`) References `sys_role`(`Id`),
	Constraint `fk_sys_user_admin_role_uaid` Foreign Key(`user_admin_id`) References `sys_user_admin`(`Id`)
) comment = '用户角色绑定';

-- 表：权限表
drop table if exists `sys_action`;
create table `sys_action` (
	`id` int not null auto_increment,
	`remark` varchar(200) null comment '描述信息',
	primary key (`id`)
) comment = '权限表';
-- 表：角色权限绑定（多对多）
drop table if exists `sys_role_action`;
create table `sys_role_action` (
	`id` int not null auto_increment,
	`role_id` int not null comment '角色id',
	`action_id` int not null comment '权限id',
	primary key (`id`),
	Constraint `fk_sys_role_action_rid` Foreign Key(`role_id`) References `sys_role`(`Id`),
	Constraint `fk_sys_role_action_aid` Foreign Key(`action_id`) References `sys_action`(`Id`)
) comment = '角色权限绑定';
-- 表：区域
drop table if exists `sys_area`;
create table `sys_area` (
	`id` int not null auto_increment,
	`areaName` varchar(20) not null comment '区域名称',
	`parentId` int default 0 comment '父区域id',
	`sorted_number` int not null comment '排序编号',
	`areaCode` varchar(50) null comment '区域编码',
	`created_user_id` int not null comment '创建用户id',
	`created_datetime` DateTime not null comment '数据创建时间',
	`update_user_id` int not null comment '更新用户id',
	`update_datetime` DateTime not null comment '更新时间',
	`remark` varchar(200) null comment '描述信息',
	`removed` bit not null comment '是否被移除',
	primary key (`id`),
	Constraint `fk_sys_area_cuid` Foreign Key(`created_user_id`) References `sys_user_admin`(`Id`),
	Constraint `fk_sys_area_uuid` Foreign Key(`update_user_id`) References `sys_user_admin`(`Id`)
) comment = '区域表格';

-- 表：菜单
drop table if exists `sys_menu`;
create table `sys_menu` (
	`id` int not null auto_increment,
	`menu_text` varchar(20) not null comment '菜单名称',	
	`sort_number` int not null comment '排序编号',
	`menu_parent_id` int default 0 comment '父菜单id',
	`menu_type` int not null comment '菜单类型：0=目录；1=菜单;2=按键',
	`menu_URL` varchar(255) not null comment '菜单URL',
	`menu_icon` varchar(100) not null comment '菜单图标',
	`visible` bit default 1 comment '是否可见',
	`perms` varchar(100) default '' comment '权限字符',
	`remark` varchar(200) null comment '描述信息',
	primary key (`id`)
) comment = '菜单';
-- 表：角色菜单
drop table if exists `sys_role_menu`;
create table `sys_role_menu` (
	`id` int not null auto_increment,
	`role_id` int not null comment '角色id',
	`menu_id` int not null comment '菜单id',
	primary key (`id`),
	Constraint `fk_sys_role_menu_rid` Foreign Key(`role_id`) References `sys_role`(`Id`),
	Constraint `fk_sys_role_menu_mid` Foreign Key(`menu_id`) References `sys_menu`(`Id`)
) comment = '角色菜单表';


-- 表：字典类型
drop table if exists `sys_dict_type`;
create table `sys_dict_type` (
	`id` int not null auto_increment,
	`dict_type_name` varchar(50) not null comment '字典类型名称',
	`dict_type_key` varchar(30) not null comment '字典类型唯一键',
	`status` bit default 1 comment '状态:0=禁用，1=启用',
	`created_datetime` DateTime not null comment '数据创建时间',
	`remark` varchar(200) null comment '描述信息',
	primary key (`id`),
	unique (dict_type_key)
) comment = '字典类型';
-- 表：字典数据
drop table if exists `sys_dict`;
create table `sys_dict` (
	`id` int not null auto_increment,
	`sort_number` int not null comment '排序编号',
	`dict_label` varchar(50) not null comment '字典标签',
	`dict_value` varchar(50) not null comment '字典值',
	`dict_type_id` int not null comment '字典类型id',
	`is_default` bit default 1 comment '是否默认：0=否，=是',
	`status` bit default 1 comment '状态:0=禁用，1=启用',
	`created_datetime` DateTime not null comment '数据创建时间',
	`remark` varchar(200) null comment '描述信息',	
	primary key (`id`),
	Constraint `fk_sys_dict_dtid` Foreign Key(`dict_type_id`) References `sys_dict_type`(`Id`)
) comment = '字典数据';

-- 表：系统参数
drop table if exists `sys_parameter`;
create table `sys_parameter` (
	`id` int not null auto_increment,
	`para_label` varchar(50) not null comment '参数标签',
	`para_key` varchar(50) not null comment '参数键名',
	`para_value` varchar(50) not null comment '参数键值',
	`para_type` int not null comment '参数类型：0=系统内置（不可删），1=用户新增',
	`created_datetime` DateTime not null comment '数据创建时间',
	`created_user_id` int not null comment '创建用户id',
	`update_datetime` DateTime not null comment '更新时间',
	`update_user_id` int not null comment '更新用户id',
	`remark` varchar(200) null comment '描述信息',
	primary key (`id`),
	Constraint `fk_sys_parameter_cuid` Foreign Key(`created_user_id`) References `sys_user_admin`(`Id`),
	Constraint `fk_sys_parameter_uuid` Foreign Key(`update_user_id`) References `sys_user_admin`(`Id`)
) comment = '系统参数表';

-- ----------------------------
-- 操作记录表
-- ----------------------------
drop table if exists `sys_operation_log`;
create table `sys_operation_log` (
	`id` int not null auto_increment,
	`module_title` varchar(50) default '' comment '模块标题',
	`action_name` varchar(100) default '' comment '功能名称',
	`method_name` varchar(100) default '' comment '方法名称',
	`method_params` varchar(250) default '' comment '请求参数',
	`status` bit(1) default b'0' comment '操作状态：0=正常 1=异常）',
	`oper_user_id` int not null comment '操作用户id',
	`oper_URL` varchar(255) default '' comment '请求URL',
	`oper_IP` varchar(30) default '' comment '主机地址',
	`oper_location` varchar(255) default '' comment '操作地点',		
	`error_msg` varchar(2000) default '' comment '错误消息',
	`operation_datetime` DateTime not null comment '操作时间',
	primary key (`id`),
	Constraint `fk_sys_operation_log_ouid` Foreign Key(`oper_user_id`) References `sys_user_admin`(`Id`)
) comment = '操作记录表';
create index idx_sys_operation_log_1 on `sys_operation_log` (`operation_datetime`);
create index idx_sys_operation_log_2 on `sys_operation_log` (`oper_user_id`);

-- -- 数据系统-设备
drop table if exists `sys_device`;
create table `sys_device` (
	`id` int not null auto_increment,
	`created_datetime` DateTime not null comment '数据创建时间',
	`sn` varchar(20) not null comment '序列号',
	`remark` varchar(200) null comment '描述信息',
	`removed` bit(1) not null DEFAULT b'0' COMMENT '是否已删除',
  	`removed_time` datetime DEFAULT null COMMENT '移除时间',
	`enabled` bit not null comment '启用状态',
	primary key (`id`)
) comment = '设备表格';

-- 设备升级日志
drop table if exists `sys_device_upgrade_log`;
create table `sys_device_upgrade_log`  (
	`id` int not null auto_increment,
	`created_datetime` DateTime not null comment '数据创建时间',
	`device_id` int not null comment '设备id',
	`stutus_0` int comment '状态0',
	`stutus_1` int comment '状态1',
	`stutus_2` int comment '状态2',
	`stutus_3` int comment '状态3',
	primary key (`id`),
	Constraint `fk_sys_device_upgrade_log_did` Foreign Key(`device_id`) References `sys_device`(`Id`)
) comment = '设备升级日志';

-- 设备地理变化信息
drop table if exists `sys_device_geo_record`;
create table `sys_device_geo_record`  (
	`id` int not null auto_increment,
	`created_datetime` DateTime not null comment '数据创建时间',
	`device_id` int not null comment '设备id',
	`coordinate_type` int comment '坐标系',
	`lng` int comment '经度',
	`lat` int comment '纬度',
	primary key (`id`),
	Constraint `fk_sys_device_geo_record_did` Foreign Key(`device_id`) References `sys_device`(`Id`)
) comment = '设备实时地理信息记录';
create index idx_sys_device_geo_record_did on `sys_device_geo_record` (`device_id`);

-- 设备用户绑定关系表
drop table if exists `sys_user_client_device`;
create table `sys_user_client_device` (
	`id` int not null auto_increment,
	`created_datetime` DateTime not null comment '数据创建时间',
	`user_client_id` int not null comment '终端用户id',
	`device_id` int not null comment '设备id',
	primary key (`id`),
	Constraint `fk_sys_user_client_device_ucid` Foreign Key(`user_client_id`) References `sys_user_client`(`Id`),
	Constraint `fk_sys_user_client_device_did` Foreign Key(`device_id`) References `sys_device`(`Id`)
) comment = "设备用户绑定关系表";
create index idx_sys_user_client_device_ucid on `sys_user_client_device` (`user_client_id`);

-- 分组系统

-- 订阅系统
drop table if exists `sys_subscribe`;
create table `sys_subscribe` (
	`id` int not null auto_increment,
	primary key (`id`)	
) comment = '订阅记录';

drop table if exists `sys_subscribe_log`;
create table `sys_subscribe_log` (
	`id` int not null auto_increment,
	`sys_subscribeId` int not null comment '订阅表id',
	primary key (`id`)
) comment = '订阅日志表';
alter table `sys_subscribe_log` add Foreign Key(`sys_subscribeId`) References `sys_subscribe`(`id`);


SET FOREIGN_KEY_CHECKS = 1;