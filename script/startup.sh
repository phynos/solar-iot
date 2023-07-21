#!/usr/bin/env bash

DIR=$(cd $(dirname $0); pwd)
echo "当前目录：$DIR"
cd "$DIR" || exit

for app in "${apps[@]}"
do
  PID=`ps -ef|grep -v grep | grep $app| awk '{print $2}'`
  if [ -z "${PID}" ];then
    echo 'app: '$app',pid: is empty'
  else
    echo 'app: '$app',pid:'$PID
    kill -9 $PID
  fi
done



PROGRAM='/home/phynos/app'
apps=(solar-api solar-netty solar-actuator solar-push)

function killApp() {
    PID=`ps -ef|grep iscan_assessment_hnjt_server|grep -v grep|awk '{print $2}'`
    if [ -z "${PID}" ]; then
      echo 'app not start'
    else
      echo 'kill app, pid=' $PID
      kill -9 $PID
    fi
}

function startApp() {
    nohup java -jar $DIR/${RESOURCE_NAME} --spring.profiles.active=zwy >/dev/null 2>&1 &
    echo "start app success"
}

killApp
startApp