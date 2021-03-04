#!/bin/bash

# Creates bin folder if it does not exist
mkdir -p ./bin

# Compile every class and places it on bin
cd ./src
for file in $(ls .)
do
  javac $file -d ../bin
done

# Copy rmi policy and execute the Server
cp ../rmi.policy ../bin
