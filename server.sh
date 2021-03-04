#!/bin/bash

# Fetch variables
source ./config.sh

# Creates bin folder if it does not exist
mkdir -p ./bin

# Compile every class and places it on bin
for file in $(ls ./src/)
do
  javac ./src/$file -d ./bin
done

# Copy rmi policy and execute the Server
cp ./rmi.policy ./bin
cd bin && pwd && java -Djava.security.policy=rmi.policy -Djava.rmi.server.useCodebaseOnly=false -Djava.rmi.server.hostname=localhost -Djava.rmi.server.codebase=file:$BIN_PATH Server
