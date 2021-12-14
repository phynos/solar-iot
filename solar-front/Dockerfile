# 基于的镜像
FROM adoptopenjdk/openjdk11
# 设置环境变量
ENV TZ=Asia/Shanghai
# 执行命令，为了防止多层镜像，这里只执行一次（docker build时候运行）
RUN mkdir -p /iot \
    && echo "${TZ}" > /etc/timezone
# 指定工作目录
WORKDIR /iot
#复制到容器指定目录
COPY ./target/phynos-solar-mqtt.jar /iot/phynos-solar-mqtt.jar
# 启动后执行命令
ENTRYPOINT [ "java", "-jar", "/iot/phynos-solar-mqtt.jar" ]