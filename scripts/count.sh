#!/bin/bash

# This is a script for counting the number of JML translated with correct syntax and semantics regarding to the JML using OpenJML

# folder="./test/s0001_two_sum"
# app="gpt4"
models=("gpt4" "gpt35" "starchat")
# OPENJML="../openjml-macos-13-0.21.0-alpha-0/openjml "
OPENJML="../openjml-0.21-a0/openjml "
SCRIPTSPATH="scripts"
SRC_PATH="test"

for entry in `find $SRC_PATH -type f -name "readme.md" | sort`
do
    folder=""$(dirname $entry)""
    for model in "${models[@]}"
    do
        APPPATH="$folder/$model"

        mkdir $APPPATH/build
        # rm $APPPATH/build/jml/*
        # rm $APPPATH/build/tmp/*

        # echo "Inserting JML into program file..."
        python $SCRIPTSPATH/insertjml.py $folder $model > $APPPATH/build/Solution.java
        # echo "Done..."
        # echo "Invoking OpenJML to check the JML inside the injected program..."
        $OPENJML $APPPATH/build/Solution.java > $APPPATH/build/ss.log
        # echo "Done..."
        python $SCRIPTSPATH/loganalysis.py $APPPATH > $APPPATH/build/check.log
    done
done



