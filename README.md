# Phynos-SpringBoot
预定大于配置，就得知道有哪些约定，以及如何翻看代码

## 功能
- 支持集群
- 支持数据库分库

## 目录约定
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

## 基础功能列表
- 多环境配置
- 缓存机制
- 日志
- 数据库及其代码生成
- 上传文件
- 操作记录
- 权限管理
- 字典
- 参数管理
- 工作流

## springboot各个启动类
- mybatis-spring-boot-starter：
- 


