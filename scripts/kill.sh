#!bin/sh
sudo kill -s SIGKILL $(ps -aux |grep core|grep -v grep|awk '{print $2}')
