#!/bin/bash

# argv 1: dataset main name [java_api]
# argv 2: dataset sub name [java.lang, java.text]
PYCMD=~/Documents/Phd_Studies/venv/py3/bin/python
SRC_PATH=./initial
SCRIPT_PATH=/Users/chrissleong/Documents/Phd_Studies/MEARC/datasets/bigcorpus/scripts

# echo "rm -rf $SRC_PATH; mkdir -p $SRC_PATH"
# rm -rf $SRC_PATH; mkdir -p $SRC_PATH

# echo "$PYCMD scripts/prepare.py $FILE_PATH $SRC_PATH"
# $PYCMD scripts/prepare.py $FILE_PATH $SRC_PATH

for entry in `ls $SRC_PATH`
do
  echo "Working with method number $entry..................."
  # for dir in `ls $SRC_PATH/$entry/*_descriptions`
  # for file in `find $SRC_PATH/$entry -type f -name "pre" -o -name "post"`
  for file in `find $SRC_PATH/$entry -type f -name "post.p1"`
  do
    # echo "Processing $dir.........."
    # for file in `find $dir -type f -regex ".*/p.*"`
    # do
      # echo "Calling method to translate $file to MR......."
    echo $file
    rm $SRC_PATH/$entry/*.mr
    $PYCMD $SCRIPT_PATH/transMR.py $file
    rm $SRC_PATH/$entry/*.tok
    rm $SRC_PATH/$entry/*.xml
    rm $SRC_PATH/$entry/*.log
      # echo "Done.........................................."
    # done
    # echo "Done with $dir.............................."
  done
  echo "Done with $SRC_PATH/$entry....................................."
done



