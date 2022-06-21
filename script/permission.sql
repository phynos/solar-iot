


-- postgresql 权限


-- 1、授权表格访问权限（必须先授权模式使用权）
grant select on all tables in schema iot to east;
grant usage on schema iot to east;

-- 2、授权部分表访问权限（必须先授权模式使用权）
grant select, update on iot.sys_log_login to east;

-- 3、撤销权限
revoke update on iot.sys_log_login from east;