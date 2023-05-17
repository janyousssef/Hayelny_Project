#!/bin/sh
mvn clean package -Dspring.profiles.active=prod && rm -f nohup.out && nohup sudo java -Dspring.profiles.active=prod -jar ./target/core-0.0.1-SNAPSHOT.jar
