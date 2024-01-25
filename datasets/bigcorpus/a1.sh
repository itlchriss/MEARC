#!/bin/bash

# argv 1: dataset main name [java_api]
# argv 2: dataset sub name [java.lang, java.text]
PYCMD=~/Documents/Phd_Studies/venv/py3/bin/python
# This is only for java api dataset
FILE_PATH=$1
ROOT_PATH=$2
SRC_PATH=$ROOT_PATH/src

echo "rm -rf $SRC_PATH; mkdir -p $SRC_PATH"
rm -rf $SRC_PATH; mkdir -p $SRC_PATH

echo "$PYCMD scripts/prepare.py $FILE_PATH $SRC_PATH"
$PYCMD scripts/prepare.py $FILE_PATH $SRC_PATH

for entry in `ls $SRC_PATH`
do
  echo "Working with method number $entry..................."
  # for dir in `ls $SRC_PATH/$entry/*_descriptions`
  for dir in `find $SRC_PATH/$entry -type d -regex ".*_descriptions"`
  do
    echo "Processing $dir.........."
    for file in `find $dir -type f -regex ".*.org"`
    do
      # echo "Calling method to translate $file to MR......."
      $PYCMD scripts/transMR.py $file
      rm $dir/*.tok
      rm $dir/*.xml
      rm $dir/*.log
      # echo "Done.........................................."
    done
    echo "Done with $dir.............................."
  done
  echo "Done with $SRC_PATH/$entry....................................."
done



