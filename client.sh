#!/bin/bash

# Fetch variables
source ./config.sh

# Executes the client
cd bin && \
java -Djava.security.policy=rmi.policy \
-Djava.rmi.server.useCodebaseOnly=false -Djava.rmi.server.hostname=localhost \
-Djava.rmi.server.codebase=file:$PROJECT_PATH/bin Client "$1" "$2"
