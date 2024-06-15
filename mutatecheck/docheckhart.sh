#!/bin/bash
OPENJML="../../openjml-0.17-a15/openjml "
ESC_FLAG="-esc"
BIGINT_FLAGS="--spec-math bigint --code-math bigint"
TIMEOUT_FLAG="--timeout 60"

dotest () {
        src=./$1/$2
        echo $src
        $OPENJML $ESC_FLAG $src/Solution.java $BIGINT_FLAGS $TIMEOUT_FLAG > $src/org_result.txt
        mkdir -p ./$1/$2/mutants
        for entry in `find $src/mutants -type f -name 'Solution.java'`
        do
            echo $entry
            folder=$(dirname "$entry")
            $OPENJML $ESC_FLAG $entry  $BIGINT_FLAGS $TIMEOUT_FLAG > $folder/result.txt
        done
    #done
}

dotest 'hart' $1
