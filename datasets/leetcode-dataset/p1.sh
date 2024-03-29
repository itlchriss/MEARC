#!/bin/bash

# argv 1: dataset main name [java_api]
# argv 2: dataset sub name [java.lang, java.text]
PYCMD=~/Documents/Phd_Studies/venv/py3/bin/python
SRC_PATH=./initial
SCRIPT_PATH=/Users/chrissleong/Documents/Phd_Studies/MEARC/datasets/bigcorpus/scripts

rm $SRC_PATH/*/*.p1*

for entry in `find $SRC_PATH -type f -regex ".*.post.failed_sentence"`
do
    echo "Working with $entry..................."
    echo "Calling LLM text restriction method........"
    $PYCMD $SCRIPT_PATH/p1.py $entry
    echo "Done......................................."

    filename=`echo $entry | sed 's/.*\///'`
    index=`echo $filename | sed 's/\..*//'`
    folder=`echo $entry | sed "s/\/$filename//"`
    p1_file="$folder/$index.p1"

    #echo "Calling method to translate $p1_file to MR......."
    #$PYCMD $SCRIPT_PATH/transMR.py $p1_file; rm $folder/*.tok; rm $folder/*.xml; rm $folder/*.log
    #echo "Done.........................................."

done


