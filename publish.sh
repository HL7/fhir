#/bin/bash
echo "Running publication process now with args: '$@'"
ant -Dargs=\"$@\"
