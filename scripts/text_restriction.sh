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
    echo "Calling LLM text restriction method........"
    $OPENAIPYCMD $SCRIPT_PATH/text_restriction.py $entry
    # mv $folder/rnl-gpt-4o.txt $folder/gpt4/rnl.txt
    # mv $folder/rnl-starchat.txt $folder/starchat/rnl.txt
    

    # filename=`echo $entry | sed 's/.*\///'`
    # index=`echo $filename | sed 's/\..*//'`
    # folder=`echo $entry | sed "s/\/$filename//"`
    # p1_file="$folder/p1"

    #echo "Calling method to translate $p1_file to MR......."
    #$PYCMD $SCRIPT_PATH/transMR.py $p1_file; rm $folder/*.tok; rm $folder/*.xml; rm $folder/*.log
    #echo "Done.........................................."
    #break
done


