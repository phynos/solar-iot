/*
* 
*      数据库初始化脚本
*	数据库：mysql-8.0.22
*   脚本版本：V1.0
*
*/

DROP DATABASE IF EXISTS `charger`;
CREATE DATABASE IF NOT EXISTS `charger` DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `charger`;

SET FOREIGN_KEY_CHECKS = 0;


-- 管理系统
drop table if exists `sys_user`;
create table `sys_user` (
	`id` bigint not null auto_increment,
	`created_datetime` DateTime not null comment '数据创建时间',
	`username` varchar(20) not null comment '登录-用户名',
	`sex` varchar(50) default '' comment '性别，绑定字典',
	`email` varchar(50) not null comment '登录-邮箱',
	`mobile_phone` varchar(20) not null comment '登录-移动电话',	
	`password` varchar(20) not null comment '登录密码',
	`email_validated` bit not null comment '邮箱验证是否通过',
	`mobile_phone_validated` bit not null comment '手机验证是否通过',	
	`nickname` varchar(20) default '' comment '昵称',
	`realname` varchar(20) default '' comment '真实姓名',
	`identity_card` varchar(50) default '' comment '身份证号',
	`dept_id` bigint default null comment '机构ID',	
	`enabled` bit not null comment '启用状态',	
	primary key (`id`),
	Constraint `fk_sys_user_deptid` Foreign Key(`dept_id`) References `sys_dept`(`Id`)
) comment = '管理用户表格';

insert into `sys_user` values(1,'2018-07-16 16:30:00','admin','','phynos@126.com','1111111','admin',true,true,'管理员',null,null,null,true);

-- 表：登录日志
drop table if exists `sys_user_login_log`;
create table `sys_user_login_log` (
	`id` bigint not null auto_increment,
	`user_id` bigint not null comment '用户id',
	`login_datetime` DateTime not null comment '登录时间',
	`login_ip` varchar(30) default '' comment '登录IP',
	`platform` int not null comment '登录平台',
	Constraint `fk_sys_user_login_log_uid` Foreign Key(`user_id`) References `sys_user`(`Id`),
	primary key (`id`)
) comment = '管理用户登录日志表';
create index idx_sys_user_login_log_1 on `sys_user_login_log` (`user_id`);

-- 表：区域
drop table if exists `sys_area`;
create table `sys_area` (
	`id` bigint not null auto_increment,
	`area_name` varchar(20) not null comment '区域名称',
	`parentId` bigint default 0 comment '父区域id',
	`sort` int not null comment '排序编号',
	`area_code` varchar(50) null comment '区域编码',
	`created_user_id` bigint not null comment '创建用户id',
	`created_datetime` DateTime not null comment '数据创建时间',
	`update_user_id` bigint not null comment '更新用户id',
	`update_datetime` DateTime not null comment '更新时间',
	`remark` varchar(200) null comment '描述信息',
	`removed` bit not null comment '是否被移除',
	primary key (`id`),
	Constraint `fk_sys_area_cuid` Foreign Key(`created_user_id`) References `sys_user`(`Id`),
	Constraint `fk_sys_area_uuid` Foreign Key(`update_user_id`) References `sys_user`(`Id`)
) comment = '区域表格';

-- 表：角色
drop table if exists `sys_role`;
create table `sys_role` (
	`id` bigint not null auto_increment,
	`role_name` varchar(20) not null comment '角色名称',
	`role_key` varchar(100) not null comment '角色字符',
	`role_type` int not null comment '角色类型', 
	`area_id` bigint not null comment '区域id',
	`remark` varchar(200) null comment '描述信息',
	primary key (`id`),
	Constraint `fk_sys_role_aid` Foreign Key(`area_id`) References `sys_area`(`Id`),
	unique (role_key)
) comment = '角色';
-- 表：用户角色绑定（多对多）
drop table if exists `sys_user_role`;
create table `sys_user_role` (
	`id` bigint not null auto_increment,
	`role_id` bigint not null comment '角色id',
	`user_id` bigint not null comment '管理员用户id', 
	primary key (`id`),
	Constraint `fk_sys_user_role_rid` Foreign Key(`role_id`) References `sys_role`(`Id`),
	Constraint `fk_sys_user_role_uaid` Foreign Key(`user_id`) References `sys_user`(`Id`)
) comment = '用户角色绑定';

-- 表：权限表
drop table if exists `sys_action`;
create table `sys_action` (
	`id` bigint not null auto_increment,
	`remark` varchar(200) null comment '描述信息',
	primary key (`id`)
) comment = '权限表';
-- 表：角色权限绑定（多对多）
drop table if exists `sys_role_action`;
create table `sys_role_action` (
	`id` bigint not null auto_increment,
	`role_id` bigint not null comment '角色id',
	`action_id` bigint not null comment '权限id',
	primary key (`id`),
	Constraint `fk_sys_role_action_rid` Foreign Key(`role_id`) References `sys_role`(`Id`),
	Constraint `fk_sys_role_action_aid` Foreign Key(`action_id`) References `sys_action`(`Id`)
) comment = '角色权限绑定';

-- 表：部门
drop table if exists `sys_dept`;
create table `sys_dept` (
	`id` bigint not null auto_increment,
	`name` varchar(20) not null comment '部门名称',	
	`sort_number` int not null comment '排序编号',
	`created_datetime` DateTime not null comment '数据创建时间',	
	`update_datetime` DateTime not null comment '更新时间',
	`del_flag` bit default 0 comment '删除标志',
	`parent_id` bigint default 0 comment '父id',
	primary key (`id`)
) comment = '机构表';
-- 表：角色部门关系
drop table if exists `sys_role_dept`;
create table `sys_role_dept` (
	`id` bigint not null auto_increment,
	`role_id` bigint not null comment '角色id',
	`dept_id` bigint not null comment '机构id',
	primary key (`id`),
	Constraint `fk_sys_role_dept_rid` Foreign Key(`role_id`) References `sys_role`(`Id`),
	Constraint `fk_sys_role_dept_did` Foreign Key(`dept_id`) References `sys_dept`(`Id`)
) comment = '角色机构表';

-- 表：菜单
drop table if exists `sys_menu`;
create table `sys_menu` (
	`id` bigint not null auto_increment,
	`text` varchar(20) not null comment '菜单名称',	
	`sort` int not null comment '排序编号',
	`pid` bigint default 0 comment '父菜单id',
	`type` int not null comment '菜单类型：0=目录；1=菜单;2=按键',
	`url` varchar(255) not null comment '菜单URL',
	`icon` varchar(100) not null comment '菜单图标',
	`visible` bit default 1 comment '是否可见',
	`perms` varchar(100) default '' comment '权限字符',
	`remark` varchar(200) null comment '描述信息',
	primary key (`id`)
) comment = '菜单';
-- 表：角色菜单
drop table if exists `sys_role_menu`;
create table `sys_role_menu` (
	`id` bigint not null auto_increment,
	`role_id` bigint not null comment '角色id',
	`menu_id` bigint not null comment '菜单id',
	primary key (`id`),
	Constraint `fk_sys_role_menu_rid` Foreign Key(`role_id`) References `sys_role`(`Id`),
	Constraint `fk_sys_role_menu_mid` Foreign Key(`menu_id`) References `sys_menu`(`Id`)
) comment = '角色菜单表';


-- 表：字典类型
drop table if exists `sys_dict_type`;
create table `sys_dict_type` (
	`id` bigint not null auto_increment,
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
	`id` bigint not null auto_increment,
	`sort` int not null comment '排序编号',
	`dict_label` varchar(50) not null comment '字典标签',
	`dict_value` varchar(50) not null comment '字典值',
	`dict_type_id` bigint not null comment '字典类型id',
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
	`id` bigint not null auto_increment,
	`para_label` varchar(50) not null comment '参数标签',
	`para_key` varchar(50) not null comment '参数键名',
	`para_value` varchar(50) not null comment '参数键值',
	`para_type` int not null comment '参数类型：0=系统内置（不可删），1=用户新增',
	`created_datetime` DateTime not null comment '数据创建时间',
	`created_user_id` bigint not null comment '创建用户id',
	`update_datetime` DateTime not null comment '更新时间',
	`update_user_id` bigint not null comment '更新用户id',
	`remark` varchar(200) null comment '描述信息',
	primary key (`id`),
	Constraint `fk_sys_parameter_cuid` Foreign Key(`created_user_id`) References `sys_user`(`Id`),
	Constraint `fk_sys_parameter_uuid` Foreign Key(`update_user_id`) References `sys_user`(`Id`)
) comment = '系统参数表';

-- ----------------------------
-- 操作记录表
-- ----------------------------
drop table if exists `sys_operation_log`;
create table `sys_operation_log` (
	`id` bigint not null auto_increment,
	`module_title` varchar(50) default '' comment '模块标题',
	`action_name` varchar(100) default '' comment '功能名称',
	`method_name` varchar(100) default '' comment '方法名称',
	`method_params` varchar(250) default '' comment '请求参数',
	`status` bit(1) default b'0' comment '操作状态：0=正常 1=异常）',
	`oper_user_id` bigint not null comment '操作用户id',
	`oper_url` varchar(255) default '' comment '请求URL',
	`oper_ip` varchar(30) default '' comment '主机地址',
	`oper_flatform` int not null comment '操作平台',
	`oper_location` varchar(255) default '' comment '操作地点',		
	`error_msg` varchar(2000) default '' comment '错误消息',
	`operation_datetime` DateTime not null comment '操作时间',
	primary key (`id`),
	Constraint `fk_sys_operation_log_ouid` Foreign Key(`oper_user_id`) References `sys_user`(`Id`)
) comment = '操作记录表';
create index idx_sys_operation_log_1 on `sys_operation_log` (`operation_datetime`);
create index idx_sys_operation_log_2 on `sys_operation_log` (`oper_user_id`);

-- -- 数据系统-设备
drop table if exists `sys_device`;
create table `sys_device` (
	`id` bigint not null auto_increment,
	`created_datetime` DateTime not null comment '数据创建时间',
	`sn` varchar(200) not null comment '序列号',
	`remark` varchar(1000) null comment '描述信息',
	`removed` bit(1) not null DEFAULT b'0' COMMENT '是否已删除',
  	`removed_time` datetime DEFAULT null COMMENT '移除时间',
	`enabled` bit not null comment '启用状态',
	primary key (`id`)
) comment = '设备表格';


SET FOREIGN_KEY_CHECKS = 1;