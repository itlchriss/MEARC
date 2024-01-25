#!/bin/bash

# SRC_PATHS=("./java_api/java.lang/String" "./java_api/java.util/HashSet" "./java_api/java.util/ArrayList" "./java_api/java.util/Stack" "./java_api/java.util/Collections" "./java_api/java.util.logging/Logger" "./java_api/java.lang/Integer" "./java_api/java.lang/Boolean" "./java_api/java.lang/Number" "./java_api/java.lang/Object")
# we cannot use interface in experiment, because the interface methods are not returnable without implementation. Interfaces such as java.util.Set are excluded.
# SRC_PATHS=("./java_api/java.lang/String" "./java_api/java.util/HashSet" "./java_api/java.util/ArrayList" "./java_api/java.util/Stack" "./java_api/java.util/Collections" "./java_api/java.security/Key" "./java_api/java.security/MessageDigest" "./java_api/java.lang/Integer" "./java_api/java.lang/Boolean" "./java_api/java.lang/Number" "./java_api/java.lang/Object")
SRC_PATHS=("./java_api/java.lang/String" "./java_api/java.util/HashSet" "./java_api/java.util/ArrayList" "./java_api/java.util/Stack" "./java_api/java.util/Collections" "./java_api/java.security/MessageDigest" "./java_api/java.lang/Integer" "./java_api/java.lang/Boolean" "./java_api/java.lang/Number" "./java_api/java.lang/Object" "./java_api/java.io/File" "./java_api/java.io/ByteArrayInputStream")

for p in ${SRC_PATHS[@]};
do
    echo "./p1.sh $p"
    ./p1.sh $p
done
