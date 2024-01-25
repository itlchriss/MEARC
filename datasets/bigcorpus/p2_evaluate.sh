#!/bin/bash

SRC_PATHS=("./java_api/java.lang/String" "./java_api/java.util/Map" "./java_api/java.util/Set" "./java_api/java.util/Queue" "./java_api/java.util/Collection" "./java_api/java.util.logging/Logger" "./java_api/java.lang/Integer" "./java_api/java.lang/Boolean" "./java_api/java.lang/Number" "./java_api/java.lang/Object")


for p in ${SRC_PATHS[@]};
do
    echo "./p2.sh $p"
    ./p2.sh $p
done
