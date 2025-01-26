#!/bin/bash

# argv 1: dataset main name [java_api]
# argv 2: dataset sub name [java.lang, java.text]
# PYCMD=~/Documents/Phd_Studies/venv/py3/bin/python
SRC_PATH=./test
APP_PATH=./src/python
SCRIPT_PATH=./scripts


# rm $SRC_PATH/*/gpt-results/*

for entry in `find $SRC_PATH -type f -name "Solution.java.no_annotation" | sort`
do
    folder=""$(dirname $entry)""
    echo "Working with $folder..................."
    python $SCRIPT_PATH/trans/insertcomment.py $folder
done


