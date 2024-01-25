#!/bin/bash

# SRC_PATHS=("./java_api/java.lang/String" "./java_api/java.util/Map" "./java_api/java.util/Set" "./java_api/java.util/Queue" "./java_api/java.util/Collection" "./java_api/java.util.logging/Logger" "./java_api/java.lang/Integer" "./java_api/java.lang/Boolean" "./java_api/java.lang/Number" "./java_api/java.lang/Object")
# SRC_PATHS=("./java_api/java.lang/String" "./java_api/java.util/HashSet" "./java_api/java.util/ArrayList" "./java_api/java.util/Stack" "./java_api/java.util/Collections" "./java_api/java.util.logging/Logger" "./java_api/java.lang/Integer" "./java_api/java.lang/Boolean" "./java_api/java.lang/Number" "./java_api/java.lang/Object")
SRC_PATHS=("./java_api/java.lang/String" "./java_api/java.util/HashSet" "./java_api/java.util/ArrayList" "./java_api/java.util/Stack" "./java_api/java.util/Collections" "./java_api/java.security/MessageDigest" "./java_api/java.lang/Integer" "./java_api/java.lang/Boolean" "./java_api/java.lang/Number" "./java_api/java.lang/Object" "./java_api/java.io/ByteArrayInputStream")

ORG_OK_COUNT=0
# tmp=$(grep -r "Failure" ./java_api/java.lang/String/*/*/*.org.result | wc -l | awk '{print $1}')
ORG_COUNT=0
P1_COUNT=0
TOTAL=0

for p in ${SRC_PATHS[@]};
do
    tmp=$(grep -r "Failure" $p/src/*/*/*.org.result | wc -l | awk '{print $1}')
    ORG_COUNT=$(($ORG_COUNT+$tmp))
    tmp=$(grep -r "Success" $p/src/*/*/*.org.result | wc -l | awk '{print $1}')
    ORG_OK_COUNT=$(($ORG_OK_COUNT+$tmp))
    p1ok=$(grep -r "Success" $p/src/*/*/*.p1.result | wc -l | awk '{print $1}')
    # p1ok=$(($tmp-$p1tmp))
    P1_COUNT=$(($P1_COUNT+$p1ok))
    P1_FAILED=$(grep -r "Failure" $p/src/*/*/*.org.result | wc -l | awk '{print $1}')
    echo $p ' ORIGINALLY failed in ' $P1_FAILED ' cases, of which phase 1 FIXED ' $p1ok ' cases.'
done

FIX_RATE=$(bc -l <<< "(${P1_COUNT}/${ORG_COUNT}) * 100")
echo 'Altogether ' $ORG_COUNT ' cases and ' $P1_COUNT ' cases are fixed.'
echo 'Fix rate is ' $FIX_RATE '%.'

echo 'Totally we have ' $(($ORG_COUNT + $ORG_OK_COUNT)) ' cases'
echo 'Originally ' $ORG_OK_COUNT ' cases can be translated'
ORG_OK_RATE=$(bc -l <<< "(${ORG_OK_COUNT}/(${ORG_COUNT} + ${ORG_OK_COUNT})) * 100")
P1_OK_RATE=$(bc -l <<< "((${ORG_OK_COUNT} + ${P1_COUNT})/(${ORG_COUNT} + ${ORG_OK_COUNT})) * 100")
echo 'MR translation rate is improved from ' $ORG_OK_RATE '% to ' $P1_OK_RATE '%.'

# ./report.sh ./java_api/java.lang/String
# org_count=$(grep -r "Failure" $SRC_PATH/*/*/*.org.result | wc -l | awk '{print $1}')
# ./report.sh
# ./report.sh
# ./report.sh
# ./report.sh
# ./report.sh
# ./report.sh
