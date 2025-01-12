for entry in `find ./test/*/starchat -type f -name "rnl.txt" | sort`
do
    echo $entry
    echo `wc -l $entry`
done