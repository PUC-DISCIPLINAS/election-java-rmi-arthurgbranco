#!/bin/bash

# Fetch variables
source ./config.sh

cd bin && \
java -Djava.security.policy=rmi.policy \
-Djava.rmi.server.useCodebaseOnly=false -Djava.rmi.server.hostname=localhost \
-Djava.rmi.server.codebase=file:$PROJECT_PATH/bin Server
