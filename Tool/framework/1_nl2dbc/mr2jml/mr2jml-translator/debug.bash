#!/bin/sh

debugger=gdb

make clean;
make;
$debugger -ex=r --args bin/h2jc -f../test/mr/prime/prime_true.mr -p./sst.template -d./dst.template -misPrime
$debugger -ex=r --args bin/h2jc -f../test/mr/sort/sort_aes.mr -p./sst.template -d./dst.template -msort
$debugger -ex=r --args bin/h2jc -f../test/mr/simple/simple.mr -p./sst.template -d./dst.template -mSum
$debugger -ex=r --args bin/h2jc -f../test/mr/sort/sort_pre.mr -p./sst.template -d./dst.template -msort

