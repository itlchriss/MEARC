#!/bin/bash

# argv 1: dataset main name [java_api]
# argv 2: dataset sub name [java.lang, java.text]
PYCMD=~/Documents/Phd_Studies/venv/py3/bin/python
OPENAIPYCMD=~/Documents/Phd_Studies/venv/openai/bin/python
# SRC_PATH=/Users/chrissleong/Documents/Phd_Studies/MEARC/datasets/selected-leetcode-set
SRC_PATH=/Users/chrissleong/Documents/Phd_Studies/MEARC/test
APP_PATH=/Users/chrissleong/Documents/Phd_Studies/MEARC/src/python
SCRIPT_PATH=/Users/chrissleong/Documents/Phd_Studies/MEARC/scripts


# rm $SRC_PATH/*/gpt-results/*

for entry in `find $SRC_PATH -type f -name "readme.md" | sort`
do
    folder=""$(dirname $entry)""
    echo "Working with $entry..................."
    echo "Asking LLMs to generate JML........"
    $OPENAIPYCMD $SCRIPT_PATH/p2.py $entry
done


