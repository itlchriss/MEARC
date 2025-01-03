#!/bin/bash

# This is a script for counting the number of JML translated with correct syntax and semantics regarding to the JML using OpenJML

folder="./test/s0001_two_sum"
app="gpt4"
OPENJML="../openjml-macos-13-0.21.0-alpha-0/openjml "
APPPATH="$folder/$app"
SCRIPTSPATH="scripts"

mkdir $APPPATH/build
rm $APPPATH/build/*

# echo "Inserting JML into program file..."
python $SCRIPTSPATH/insertjml.py $folder $app > $APPPATH/build/Solution.java
# echo "Done..."
# echo "Invoking OpenJML to check the JML inside the injected program..."
$OPENJML $APPPATH/build/Solution.java > $APPPATH/build/ss.log
# echo "Done..."
python $SCRIPTSPATH/loganalysis.py $APPPATH 



