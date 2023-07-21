DIR=$(cd $(dirname $0); pwd)
echo "$DIR"
cd "$DIR"

#
APP_NAME=minio
APP_START="./minio server /home/sw73/minio/data --console-address :9001"

function startApp() {
  echo start app: $1
  if [ -e "$DIR" ]; then
    echo dir exist
  fi
  echo -e "\n"

  $APP_START
}

function dogRun() {
  while true; do
    PID=$(ps -ef | grep -v grep | grep ${APP_NAME} | awk '{print $2}')
    if [ -z "${PID}" ]; then
      echo "app not start."
      echo "start app now:"
      startApp a b c
    else
      echo "app running[pid=$PID]..."
    fi
    sleep 3s
  done
}

dogRun
