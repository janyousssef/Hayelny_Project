#!bin/sh
rm -f nohup.out && nohup sudo java -Dspring.profiles.active=prod -jar target/core-0.0.1-SNAPSHOT.jar
