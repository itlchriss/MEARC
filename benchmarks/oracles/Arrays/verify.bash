#!/bin/bash

OJ=/home/chrissleong/openjml-17-15/openjml

find Equals* -prune -type d | while IFS= read -r d; do \
    echo "Checking... $d"; \
    cd $d; \
    $OJ -esc *.java; \
    cd ..; \
    echo "Done..."; \
done