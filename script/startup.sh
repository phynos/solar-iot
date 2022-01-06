

PROGRAM='/home/phynos/app'
apps=(solar-api solar-netty solar-actuator solar-push)

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

cd $PROGRAM

rm -rf *.jar
tar -xzvf phynos.tar.gz

for app in "${apps[@]}"
do
	echo 'build: '$app
	nohup java -jar $app.jar >/dev/null 2>&1 &
done
