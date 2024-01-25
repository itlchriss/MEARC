#!/bin/bash

# argv 1: dataset path
SRC_PATH=$1/src


total=`ls $1/src | wc -l`
echo "Number of tests:" $total
org_count=$(grep -r "Failure" $SRC_PATH/*/*/*.org.result | wc -l | awk '{print $1}')
org_rate=$(bc -l <<< "((${total}-${org_count})/${total}) * 100")
org_rate=$(printf %.2f "$org_rate")

p1_count=$(grep -r "Failure" $SRC_PATH/*/*/*.p1.result | wc -l | awk '{print $1}')
# p1_count=$(grep -r "Failure" $SRC_PATH/*/*/*.sw.result | wc -l | awk '{print $1}')



echo "Original failure count:" $org_count
echo "Phase 1 failure count:" $p1_count
p1_fix_count=$(($org_count - $p1_count))

fix_rate=$(bc -l <<< "(${p1_fix_count}/${org_count}) * 100")
fix_rate=$(printf %.2f "$fix_rate")
p1_rate=$(bc -l <<< "((${p1_fix_count}+(${total}-${org_count}))/${total}) * 100")
p1_rate=$(printf %.2f "$p1_rate")

count=$(find $SRC_PATH -type f -regex ".*.p1" | wc -l)
# count=$(find $SRC_PATH -type f -regex ".*.sw" | wc -l)

echo "P1 processed $count"
echo "Fixes $fix_rate%  of the sentences which were not getting a correct MR."
echo "From $org_rate% to $p1_rate%"

echo "Failed cases:"

for entry in `grep -lr "Failure" ./java_api/java.lang/String/src/*/*/*.p1.result`:
do
    filename=`echo $entry | sed 's/.*\///'`
    index=`echo $filename | sed 's/\..*//'`
    folder=`echo $entry | sed "s/\/$filename//"`
    case_folder=`echo $folder | sed 's/.*\///'`
    target_folder=`echo $folder | sed "s/\/$case_folder//"`
    file="$folder/$index.p1"
    echo $entry
    cat $file
done


