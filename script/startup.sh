#!/usr/bin/env bash

DIR=$(cd $(dirname "$0"); pwd)
echo "当前目录：$DIR"
cd "$DIR" || exit

APP_CALL="java -jar "
JVM_ARGS=""
APP_NAME=solar-api.jar
APP_ARGS="--spring.profiles.active=zwy"

for app in "${apps[@]}"
do
  PID=`ps -ef|grep -v grep | grep "$APP_NAME"| awk '{print $2}'`
  if [ -z "${PID}" ];then
    echo 'app: '$app',pid: is empty'
  else
    echo 'app: '$app',pid:'$PID
    kill -9 $PID
  fi
done

function killApp() {
    PID=$(ps -ef|grep ${APP_NAME}|grep -v grep|awk '{print $2}')
    if [ -z "${PID}" ]; then
      echo 'app not start'
    else
      echo "kill app, pid=$PID"
      kill -9 "$PID"
    fi
}

function startApp() {
    nohup ${APP_CALL} ${JVM_ARGS} "${DIR}"/${APP_NAME} ${APP_ARGS} >/dev/null 2>&1 &
    echo "start app success"
}

if [ "start" = "$1" ]
then
  echo "target: $1"
  startApp
else
  echo "target: all"
  killApp
  startApp
fi
