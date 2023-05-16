#!bin/sh
sudo kill -s SIGKILL $(ps -aux |grep java|grep -v grep|awk '{print $2}')
