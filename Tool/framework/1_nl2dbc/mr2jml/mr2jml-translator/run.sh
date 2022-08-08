#!/bin/bash

$LEX_OKAY=false

make clean
make

if [ -f "./build/lex.o" ]; then
    LEX_OKAY=true
else
    echo "compilation failed"
fi

if $LEX_OKAY; then
    echo "./lex.o ../test/dataset1.txt > ./results/result1.txt"
    ./build/lex.o ../test/dataset1.txt > ./results/result1.txt
    echo "./lex.o ../test/dataset2.txt > ./results/result2.txt"
    ./build/lex.o ../test/dataset2.txt > ./results/result2.txt

    if [ -f "./results/result1.txt" ]; then
        cat ./results/result1.txt | grep 'bad input character'
    fi

    if [ -f "./results/result2.txt" ]; then
        cat ./results/result2.txt | grep 'bad input character'
    fi
fi
