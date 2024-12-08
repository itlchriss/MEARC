#!/bin/bash
OPENJML="../../openjml-0.17-a15/openjml "
ESC_FLAG="-esc"
BIGINT_FLAGS="--spec-math bigint --code-math bigint"
TIMEOUT_FLAG="--timeout 60"

dotest () {
    type=$1
    for entry in `find ./$type/*/mutants -type f -name 'Solution.java' | sort`
    do
        folder=$(dirname "$entry")
        echo $entry
        src=$(dirname $(dirname "$folder"))
        $OPENJML $ESC_FLAG $src/Solution.java $BIGINT_FLAGS $TIMEOUT_FLAG > $src/org_result.txt
        $OPENJML $ESC_FLAG $entry $BIGINT_FLAGS $TIMEOUT_FLAG > $folder/result.txt
    done
}

#dotest 'gpt35'
dotest 'hart'
