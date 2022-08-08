#!/bin/bash
echo "Running publication process now with args: '$@'"
./gradlew publish --args=\"$@\"