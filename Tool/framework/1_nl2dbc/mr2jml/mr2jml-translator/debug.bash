#!/bin/sh


debugger=gdb

make clean;
make;
$debugger -ex=r --args bin/main -f./test/test_commons-collections-exception -d./test/resources/commons-collections4.swe
