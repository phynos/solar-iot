
APP_NAME=phynos-front-raw.jar

function startApp() {
   echo '$*:'$*
   echo '$#:'$#
   echo '$@:'$@
   echo start app: $1

   if [ -e /home/hongdian ];then
      echo dir exist
   fi
   echo -e "\n"
   
   nohup java -jar ./$APP_NAME >/dev/null 2>&1 &

}

ak=abcdefg
echo -e "length=${#ak},substring=${ak:0:3}\n"


while  true
do
   PID=`ps -ef|grep -v grep|grep ${APP_NAME}|awk '{print $2}'`
   if [ -z "${PID}" ]
   then
       echo "app not start."
       echo "start app now:"
       startApp a b c
   else
       echo "app running[pid=$PID]..."
   fi
   sleep 3s
done