#!/bin/bash

# dependencies.sh
#
# Used in the Dockerfile to properly compile all of
# the dependencies that the project might have.
#
# @author   Allan DeBoe (allan.m.deboe@gmail.com)
# @date     March 31st, 2024

declare -a dependencies=("nodejs" "npm" "jq")

for value in "${dependencies[@]}"
do
    echo -e "/r/n( Installing $value ...)"
    until ( apt-get install $value -y )
    do 
        apt-get update --fix-missing
    done
done