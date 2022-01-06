-- 创建用户
CREATE USER iot_data WITH PASSWORD '*****';

-- 用户-数据库授权
GRANT ALL PRIVILEGES ON DATABASE iotdb TO iot_data;
-- 用户-单表授权
-- GRANT SELECT ON TABLE mytable TO iot;

-- 创建scheme（将 用户名 和 scheme名称设置设置相同）
-- 如果不指定用户，则scheme归属用户为当前用户
create schema if not exists iot_data authorization iot_data;

-- 将用户的search_path设置为$user，这样，任何当某个用户连接上来后，会默认将查找或者定义的对象都定位到与之同名的模式中
ALTER USER iot_data SET search_path = 'iot_data';

-- 1.创建父表
create table t_device_data
(
    age  int     not null,
    city varchar not null
) partition by list (city);

-- 2.创建分区表
CREATE TABLE t_device_data_20220106 PARTITION OF t_device_data FOR VALUES IN
(
    '20220106'
);
CREATE TABLE t_device_data_20220107 PARTITION OF t_device_data FOR VALUES IN
(
    '20220107'
);
CREATE TABLE t_device_data_history PARTITION OF t_device_data DEFAULT;

insert into t_device_data(age, city)
VALUES (1, 'GZ');
insert into t_device_data(age, city)
VALUES (2, 'SZ');
insert into t_device_data(age, city)
VALUES (21, 'SZ');
insert into t_device_data(age, city)
VALUES (13, 'BJ');
insert into t_device_data(age, city)
VALUES (43, 'SH');
insert into t_device_data(age, city)
VALUES (28, 'HK');
insert into t_device_data(age, city)
VALUES (28, 'GZ');

-- 3.在分区上创建索引

-- 4.查看分区表