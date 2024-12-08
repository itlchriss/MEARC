#!/bin/bash
OPENJML="../../openjml-0.17-a15/openjml "
ESC_FLAG="-esc"
#BIGINT_FLAGS="--spec-math bigint --code-math bigint"
BIGINT_FLAGS="--spec-math bigint --code-math bigint --nullable-by-default"
TIMEOUT_FLAG="--timeout 300"

dotest () {
    #type=$1
    #problem=$2
    #for entry in `find ./$type/*/mutants -type f -name 'Solution.java' | sort`
    #do
        #folder=$(dirname "$entry")
        #echo $entry
        #src=$(dirname $(dirname "$folder"))
        #entry=`find ./$1/$2 -type f -name 'Solution.java'`
        #src=$(dirname "$entry")
        src=./$1/$2
        echo $src
        #$OPENJML $ESC_FLAG $src/Solution.java $BIGINT_FLAGS $TIMEOUT_FLAG > $src/org_result.txt
        rm -rf ./$1/$2/mutants
        mkdir -p ./$1/$2/mutants
        python ../scripts/createmutants.py $src
        for entry in `find $src/mutants -type f -name 'Solution.java'`
        do
            echo $entry
            folder=$(dirname "$entry")
            $OPENJML $ESC_FLAG $entry  $BIGINT_FLAGS $TIMEOUT_FLAG > $folder/result.txt
        done
    #done
}

#dotest 'gpt35' $1
dotest 'hart' $1
