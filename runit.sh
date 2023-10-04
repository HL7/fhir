#!/usr/bin/env bash
# runs gradle on core build and captures sysout and syserr output in file with timestamp in front
DATEFILE='%Y-%m-%d-%H%m'
FILENAME=$(date +$DATEFILE)-build.log
echo $FILENAME
./gradlew publish --info 2>1 |tee $FILENAME

