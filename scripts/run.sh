#!bin/sh
rm -f backend.out && nohup sudo java -Dspring.profiles.active=prod -jar ./target/core-0.0.1-SNAPSHOT.jar > backend.out 2>&1 &