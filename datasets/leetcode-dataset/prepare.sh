#!/bin/bash

SRC=../LeetCode-in-Java/src/main/java
INITIAL=./initial

rm -rf $INITIAL/*

# Set that the dataset is not completed 
skip=("s1095")

for f in `find $SRC -regex ".*/s[[:digit:]].*/.*\.java" | sort -t '\0' -n`;
do
    s=`echo $f | grep -o -E "s\d+"`
    if [[ ${skip[@]} =~ $s ]]
    then
        continue
    fi
    readme="$(dirname "${f}")"/readme.md
    # m_name=`echo $f | grep -o -E 's\d+.*/' | sed -n 's/s[0-9]*_//p' | sed 's/_//g' | sed -n 's/\///p'`
    # echo $m_name
    m=`grep -E 'public.*)' $f | sed -n 's/[{}]//p' | sed 's/^ *//g'`
    if [[ -z $m ]]; then
        echo "cannot find method signature in $f"
        exit 1
    fi
    c=`cat $readme | grep 'Given'`
    c1=`cat $readme | grep 'given'`
    r=`cat $readme | grep 'return'`
    r1=`cat $readme | grep 'Return'`
    echo $s
    mkdir -p $INITIAL/$s
    cp $f $INITIAL/$s
    echo $c > $INITIAL/$s/pre
    echo $c1 >> $INITIAL/$s/pre
    echo $r > $INITIAL/$s/post
    echo $r1 >> $INITIAL/$s/post
    echo $m > $INITIAL/$s/methodsignature
done
