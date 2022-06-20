# 基于的镜像
FROM adoptopenjdk/openjdk11
# 设置环境变量
ENV TZ=Asia/Shanghai PROFILE=prod DOCKER_HOST=1
# 执行命令，为了防止多层镜像，这里只执行一次（docker build时候运行）
RUN mkdir -p /app \
    && echo "${TZ}" > /etc/timezone
# 指定工作目录
WORKDIR /app
# 声明暴露端口
EXPOSE 7001

#调试的时候可以导入
#COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas

#复制到容器指定目录
COPY ./target/solar-api.jar /app/solar-api.jar
# 启动后执行命令
ENTRYPOINT [ "java", "-jar", "/app/solar-api.jar", "--spring.profiles.active=${PROFILE}" ]