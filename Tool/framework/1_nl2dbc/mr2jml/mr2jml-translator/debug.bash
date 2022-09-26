#!/bin/bash

debugger=''

if [[ "$OSTYPE" == "linux-gnu"* ]]; then
  debugger='gdb -ex=r --args'
elif [[ "$OSTYPE" == "darwin"* ]]; then
  debuggger='lldb --'
fi
STD_SI=./src/compiler/lib/std_si_2022.yml

make clean;
make ASTDEBUG=1 DEBUG=1 SIDEBUG=1;

$debugger ./bin/main -f./test/$1/$1.conditions.mr -s./test/$1/$1.si.yml,$STD_SI
