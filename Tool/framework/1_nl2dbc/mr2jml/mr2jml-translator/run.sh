#!/bin/bash

STD_SI=./src/compiler/lib/std_si_2022.yml
 
make clean;
find ./test/* -prune -type d | while IFS= read -r input; do 
    d=`basename $input`
    if [[ -f "./test/$d/$d.conditions.output" ]]; then
        rm ./test/$d/$d.conditions.output
    fi
done
make;

if [ -f "./bin/main" ]; then

    echo "Running all the test cases in the ***test*** folder"
    echo "Using standard interpretations in $STD_SI"
    
    ERROR=0
    shopt -s dotglob
    find ./test/* -prune -type d | while IFS= read -r input; do 
        d=`basename $input`
        #echo "checking ./test/$d/$d.conditions.mr && -f ./test/$d/$d.si.yml"
        if [[ -f "./test/$d/$d.conditions.mr" && -f "./test/$d/$d.si.yml" ]]; then
            echo "===============================Running test of $d====================================="
            ./bin/main -f./test/$d/$d.conditions.mr -s./test/$d/$d.si.yml,$STD_SI > ./test/$d/$d.conditions.output
	    DIFF=$(diff ./test/$d/$d.conditions.output ./test/$d/$d.conditions.jml)
	    if ["$DIFF" != ""]; then
		    ERROR=$(expr $ERROR + 1)
	    else
	        echo "Success"	    
	    fi
            #echo "======================================================================================"
        else
            echo "======================================================================================"
            [ -f "./test/$d/$d.conditions.mr" ] && echo "Please provide the SI file at ./test/$d/$d.si.yml"
            [ -f "./test/$d/$d.si.yml" ] && echo "Please provide the MR file at ./test/$d/$d.conditions.mr"
            echo "======================================================================================"
        fi
    done
 
    if [ "$ERROR" = 0 ]; then
	echo "All tests passed!"
    else
	echo "There are $ERROR tests failed."
    fi
fi


