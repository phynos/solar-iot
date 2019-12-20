# Phynos-SpringBoot
Java物联网开发框架

## 模块说明（/src目录）
```lua
phynos-ui  -- https://github.com/phynos/Phynos-SpringCloud-Web

phynos
└── phynos-3rd -- 第三方模块  
     ├── phynos-activiti -- 工作流引擎  
     ├── phynos-lucene -- 分词器引擎       
     └── phynos-ruler -- 规则引擎  
└── phynos-common -- 系统公共模块  
     ├── phynos-core -- 业务核心包  
     ├── phynos-dao -- 数据连接层  
     ├── phynos-generator -- 代码生成器  
     └── phynos-kalin -- 预留代码包  
└── phynos-front -- 通讯前置机  
     ├── phynos-front-mqtt -- mqtt前置机  
     ├── phynos-front-raw -- netty前置机  
     └── phynos-front-websocket -- websocket前置机  
└── phynos-simulator -- 设备模拟器 
     ├── phynos-simulator-mqtt -- mqtt设备端模拟器  
     └── phynos-simulator-raw -- 原始TCP/IP协议通讯模拟器  
└── phynos-web -- web层应用 
     ├── phynos-api -- WEB-API接口层  
     ├── phynos-push -- websocket推送       
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

```

## 关于代码生成的说明

1. 首先使用mybatis-generator生成基础的dao代码
2. 使用本项目的phynos-generator生成controller、service、serviceimpl以及dao拓展代码


## SpringBoot基础
/static  
/public  
/resources  
/META-INF/resources  

## 配置文件约定
application或application-xxx命名的yml文件或者properties文件，且唯一

## 数据库约定
<pre>
spring: 
  datasource:
    driverClassName: com.mysql.jdbc.Driver
    url: jdbc:mysql://dev.yonyouccs.com:3001/icop-construct-busisubpack
    username: root
    password: root
</pre>
    
## 多环境配置
<pre>
spring: 
  profiles
    active: dev
</pre>
### 启动jar包时设置spring.profiles.active
<pre>
java -jar xxx.jar --spring.profiles.active=test
</pre>
### maven打包时候设置环境(设置 prod 环境)
<pre>
clean package -DskipTests -Pprod
</pre>