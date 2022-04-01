#!/bin/bash
export JAVA_HOME=/usr/local/java/jdk1.8.0_231
export PATH=$PATH:$JAVA_HOME/bin
#需要启动的Java主程序（main方法类）
APP_NAME=$2
APP_MAINCLASS=$2".jar"
export APP_PATH=`cd $(dirname $0)/; pwd`'/'$2
#java虚拟机启动参数
JAVA_OPTS="-Xms256m -Xmx256m -Xss256k"
#初始化psid变量（全局）
psid=0
checkpid() {
    #javaps=`jps -l | grep $APP_MAINCLASS`
    javaps=`ps -ef|grep java|grep $APP_MAINCLASS|awk '{print $2}'`
#   javaps=`$(ps x | grep $APP_MAINCLASS | grep -v grep | awk '{print $1}')`

   if [ -n "$javaps" ]; then
      psid=`echo $javaps | awk '{print $1}'`
   else
      psid=0
   fi
}
start() {
   checkpid

   if [ $psid -ne 0 ]; then
      echo "================================"
      echo "info: $APP_MAINCLASS already started! (pid=$psid)"
      echo "================================"
   else
      echo -n "Starting $APP_MAINCLASS ..."
      cd $APP_PATH
      `nohup java $JAVA_OPTS -jar $APP_MAINCLASS > /dev/null 2>&1 &`
      checkpid
      if [ $psid -ne 0 ]; then
         echo "(pid=$psid) [OK]"
      else
         echo "[Failed]"
      fi
   fi
}
stop() {
   checkpid

   if [ $psid -ne 0 ]; then
      echo -n "Stopping $APP_MAINCLASS ...(pid=$psid) "
      `kill -9 $psid`
      if [ $? -eq 0 ]; then
         echo "[OK]"
      else
         echo "[Failed]"
      fi

      checkpid
      if [ $psid -ne 0 ]; then
         stop
      fi
   else
      echo "======================================="
      echo "[WARN]: $APP_MAINCLASS is not running"
      echo "======================================="
   fi
}
backups() {
  if [ ! -d "$APP_PATH/backups" ];then
    mkdir -p $APP_PATH/backups
    echo "[WARN] backups dir is not exist, mkdir success ========================================="
  else
    echo "[info] backups dir is exist========================================="
  fi

  if [ ! -d "$APP_PATH/jar" ];then
    mkdir -p $APP_PATH/jar
    echo "[WARN] jar dir is not exist, mkdir success ========================================="
  else
    echo "[info] jar dir is exist========================================="
  fi

  mv $APP_PATH/$APP_MAINCLASS $APP_PATH/backups/$APP_NAME$(date +%Y%m%d%H%M).jar
  cp $APP_PATH/jar/$APP_MAINCLASS $APP_PATH
  sleep 3
}
case "$1" in
   'start')
      start
      ;;
   'stop')
     stop
     ;;
   'restart')
     stop
     start
     ;;
   'backups_restart')
     stop
     backups
     start
     ;;
  *)
echo "Usage: $0 {start|stop|restart|backups_restart}"
exit 1
esac
exit 0