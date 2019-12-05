

PROGRAM='/home/phynos/app'
PID=`ps -ef|grep -v grep | grep $PROGRAM | awk '${print $2}'`
kill -9 $PID

cd /home/phynos/app

rm -rf phynos-api.jar

nohup java -jar phynos-api.jar >/dev/null 2>&1 &