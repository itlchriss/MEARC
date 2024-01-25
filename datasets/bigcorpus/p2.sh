#!/bin/bash

# argv 1: dataset main name [java_api]
# argv 2: dataset sub name [java.lang, java.text]
PYCMD=~/Documents/Phd_Studies/venv/py3/bin/python

SRC_PATH=$1/src

rm $SRC_PATH/*/*/*.p2*

for entry in `grep -r "Success" $SRC_PATH/*/*/*.p1.result`
do
    filename=`echo $entry | sed 's/.*\///'`
    index=`echo $filename | sed 's/\..*//'`
    folder=`echo $entry | sed "s/\/$filename//"`
    case_folder=`echo $folder | sed 's/.*\///'`
    target_folder=`echo $folder | sed "s/\/$case_folder//"`
    p1_file="$folder/$index.p1"
    echo "Working with $p1_file..................."
    echo "Calling LLM text restriction method........"
    $PYCMD scripts/p2.py $p1_file $target_folder/methodname.txt
    # $PYCMD scripts/p1.py $entry
    # echo "Done......................................."

    # filename=`echo $entry | sed 's/.*\///'`
    # index=`echo $filename | sed 's/\..*//'`
    # folder=`echo $entry | sed "s/\/$filename//"`
    # p1_file="$folder/$index.p1"

    # echo "Calling method to translate $p1_file to MR......."
    # # echo "$PYCMD scripts/transMR.py $p1_file"
    # $PYCMD scripts/transMR.py $p1_file p1
    # rm $folder/*.tok
    # rm $folder/*.xml
    # rm $folder/*.log
    # echo "Done.........................................."
    # echo "Done with $entry....................................."
done




for entry in `grep -r "Success" $SRC_PATH/*/*/*.org.result`
do
    filename=`echo $entry | sed 's/.*\///'`
    index=`echo $filename | sed 's/\..*//'`
    folder=`echo $entry | sed "s/\/$filename//"`
    case_folder=`echo $folder | sed 's/.*\///'`
    target_folder=`echo $folder | sed "s/\/$case_folder//"`
    org_file="$folder/$index.org"
    echo "Working with $org_file..................."
    echo "Calling LLM text restriction method........"
    $PYCMD scripts/p2.py $org_file $target_folder/methodname.txt
done