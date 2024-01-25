#!/bin/bash

# argv 1: dataset main name [java_api]
# argv 2: dataset sub name [java.lang, java.text]
PYCMD=~/Documents/Phd_Studies/venv/py3/bin/python

SRC_PATH=$1/src

rm $SRC_PATH/*/*/*.p1*
rm $SRC_PATH/*/*/*.mr

for entry in `find $SRC_PATH -type f -regex ".*.org.failed_sentence"`
do
    echo "Working with $entry..................."
    echo "Calling LLM text restriction method........"
    $PYCMD scripts/p1.py $entry
    echo "Done......................................."

    filename=`echo $entry | sed 's/.*\///'`
    index=`echo $filename | sed 's/\..*//'`
    folder=`echo $entry | sed "s/\/$filename//"`
    p1_file="$folder/$index.p1"
    sw_file="$folder/$index.sw"
    
    echo "Calling method to translate $p1_file to MR......."
    $PYCMD scripts/transMR.py $p1_file; rm $folder/*.tok; rm $folder/*.xml; rm $folder/*.log
    echo "Done.........................................."

    # echo "Calling method to translate $sw_file to MR......."
    # $PYCMD scripts/transMR.py $sw_file; rm $folder/*.tok; rm $folder/*.xml; rm $folder/*.log
    # echo "Done.........................................."
    # echo "Done with $entry....................................."
done


