#!/usr/bin/env bash

#跳转到sh脚本所在目录
DIR=$(
  cd $(dirname $0)
  pwd
)
echo $DIR
cd $DIR

# 这里设置应用参数

# APP名称
APP_NAME=supervise-datax-0.0.1.jar
# 版本号
APP_VERSION=
# APP完整文件名
APP=supervise-datax-0.0.1.jar
JVM_ARGS=
SPRING_AGS="--spring.profiles.active=zwy --app.vehicle.copy=true"

function checkRoot() {
  if [ $UID -ne 0 ]; then
    echo "非root用户，正常"
  else
    echo "不能以root用户启动此脚本"
    exit 1
  fi
}

function checkAppFile() {
    if [ -f $DIR/${APP} ]; then
      echo "check file success"
    else
      echo "file not exist: $DIR/${APP}"
      exit 2
    fi
}

function killApp() {
  PID=$(ps -ef | grep ${APP_NAME} | grep -v grep | awk '{print $2}')
  if [ -z "${PID}" ]; then
    echo 'app not start'
  else
    echo "kill app, pid=$PID"
    kill -9 $PID
  fi
}

function startApp() {
  checkAppFile
  nohup java ${JVM_ARGS} -jar $DIR/${APP} ${SPRING_AGS} >/dev/null 2>&1 &
  echo "start app success"
}

function bakApp() {
  echo "bak app"
  checkAppFile
  now="$(date +"%m%d%H")"
  bak_app_name=${APP}.${now}
  cp ${APP} ${bak_app_name}
}

# 推荐使用systemd服务或者docker来管理进程，这种只能是一个临时方案
function dogRun() {
  echo "startWatchDog..."
  while true; do
    PID=$(ps -ef | grep -v grep | grep ${APP_NAME} | awk '{print $2}')
    if [ -z "${PID}" ]; then
      myLog "app not start, start app now..."
      startApp a b c
    else
      echo "app running[pid=$PID]..."
    fi
    sleep 3s
  done
}

function stopDog() {
  echo "stopWatchDog..."
}

function watchDogIsRunning() {
  echo "watchDogIsRunning..."
}

checkRoot
if [ "start" == "$1" ]; then
  startApp
elif [ "stop" == "$1" ]; then
  killApp
elif [ "restart" == "$1" ]; then
  killApp
  startApp
elif [ "bak" == "$1" ]; then
  bakApp
else
  killApp
  startApp
fi