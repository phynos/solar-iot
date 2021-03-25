# Phynos-SpringBoot
web单体式应用、Netty应用、Mqtt应用的代码总结

## 模块说明（/src目录）
```lua

solar
├── solar-api -- web-api管理接口
├── solar-data -- 数据解析服务  
└── solar-module -- 模块  
     ├── solar-device -- 设备模型  
     ├── solar-ruler-* -- 规则引擎  
     └── solar-* -- 预留   

```

## 功能
```lua
功能
└── 基础功能  
     ├── 用户管理  
     ├── 角色管理  
     ├── 菜单管理  
     ├── 部门管理  
     ├── 字典管理  
     ├── 权限管理  
     ├── 操作日志 -- 基于注解和aop自动处理  
     ├── 文档生成 -- 基于Swagger2  
     ├── 数据库代码生成  --基于mybatis官方插件  
     ├── 自定义数据库接口  --基于生成代码拓展  
     ├── 分页插件    
     └── 统一登录  
└── 应用示例  
     ├── 前置机Netty --整合Netty和SpringBoot，组合zookeeper和Redis实现分布式集群  
     └── 管理后台 -- 当前主流的管理后台实现  

```


### 启动jar包时设置spring.profiles.active
<pre>
java -jar xxx.jar --spring.profiles.active=test
</pre>
### maven打包时候设置环境(设置 prod 环境)
<pre>
clean package -DskipTests -Pprod
</pre>