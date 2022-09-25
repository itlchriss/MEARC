#!/bin/bash

STD_SI=./src/compiler/lib/std_si_2022.yml
 
make clean;
make;

if [ -f "./bin/main" ]; then

    echo "Running all the test cases in the ***test*** folder"
    echo "Using standard interpretations in $STD_SI"

    shopt -s dotglob
    find ./test/* -prune -type d | while IFS= read -r input; do 
        d=`basename $input`
        echo "checking ./test/$d/$d.conditions.mr && -f ./test/$d/$d.si.yml"
        if [[ -f "./test/$d/$d.conditions.mr" && -f "./test/$d/$d.si.yml" ]]; then
            echo "===============================Running test of $d====================================="
            ./bin/main -f./test/$d/$d.conditions.mr -s./test/$d/$d.si.yml,$STD_SI
            echo "======================================================================================"
        else
            echo "======================================================================================"
            [ -f "./test/$d/$d.conditions.mr" ] && echo "Please provide the SI file at ./test/$d/$d.si.yml"
            [ -f "./test/$d/$d.si.yml" ] && echo "Please provide the MR file at ./test/$d/$d.conditions.mr"
            echo "======================================================================================"
        fi
    done
fi


