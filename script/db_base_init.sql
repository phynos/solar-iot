-- 创建数据库
CREATE DATABASE iotdb;

-- 创建用户
CREATE USER iot WITH PASSWORD '*****';

-- 用户-数据库授权
GRANT ALL PRIVILEGES ON DATABASE iotdb TO iot;
-- 用户-单表授权
-- GRANT SELECT ON TABLE mytable TO iot;

-- 创建scheme（将 用户名 和 scheme名称设置设置相同）
create schema if not exists iot authorization iot;

-- 将用户的search_path设置为$user，这样，任何当某个用户连接上来后，会默认将查找或者定义的对象都定位到与之同名的模式中
SHOW search_path;
ALTER USER iot SET search_path = 'iot';
