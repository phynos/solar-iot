

-- 修改schema属主
alter schema Myschema onwer to 新Owner;

-- 重命名schema
alter schema Myschema rename to 新Schema;

-- 查询当前scheme
select current_schema();

-- 查看当前schema模式搜索路径
SHOW search_path;


-- 查询当前库下的所有schema
select * from pg_catalog.pg_namespace order by nspname;
-- 查询当前库下的所有schema(排除系统存在的schema)
select * from pg_catalog.pg_namespace where nspname not like 'pg_%' order by nspname;