# Phynos-SpringBoot
物联网快速开发框架，基于SpringBoot的单体应用，旨在快速开发，更完善的系统请使用微服务项目。

## 模块说明（/src目录）
```lua

phynos
└── phynos-3rd -- 第三方模块  
     ├── phynos-activiti -- 工作流引擎  
     └── phynos-quartz -- 分布式定时器  
└── phynos-common -- 系统公共模块  
     ├── phynos-core -- 业务核心包  
     ├── phynos-dao -- 数据连接层  
     ├── phynos-generator -- 代码生成器  
     ├── phynos-redis -- redis代码  
     └── phynos-push-rabbitmq -- 基于rabbitmq的push代码        
└── phynos-front -- 通讯前置机  
     ├── phynos-front-mqtt -- mqtt前置机  
     ├── phynos-front-raw -- netty前置机  
     └── phynos-front-websocket -- websocket前置机  
└── phynos-simulator -- 设备模拟器 
     ├── phynos-simulator-mqtt -- mqtt设备端模拟器  
     └── phynos-simulator-raw -- 原始TCP/IP协议通讯模拟器  
└── phynos-web -- WEB应用层 
     ├── phynos-api -- WEB-API接口层  
     └── phynos-monitor -- actuator监视器  

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
     ├── 分页插件 --基于pagehelper  
     └── 统一登录  
└── 拓展功能  
     ├── 流程引擎 --基于activiti5.22  
     ├── 文档管理 --使用word编辑模板并用freemaker替换占位符再生成pdf预览  
     └── 运行监控 -- 基于springboot  
└── 应用示例  
     ├── 前置机Netty --完美整合Netty和SpringBoot，组合zookeeper和Redis实现分布式集群  
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