#!/usr/bin/env python

from enum import IntEnum
import yaml

class JavaTypes(IntEnum):
    Primitive = 0
    Array = 1
    Collection = 2

with open("./src/c/lib/std_si_2022.yml", "r") as stream:
    try:
        data = yaml.safe_load(stream)
        for y in data:
            arg_types = '-'
            if 'specific_arg_types' in y:
                r = y['specific_arg_types']
                arg_types = ','.join([JavaTypes(int(i)).name for i in r])
            print('%s & %s & %s & %s & \\\\ \hline' % (
                y['term'].replace('_', '\_'), 
                ','.join(y['syntax']), 
                y['interpretation'].replace('\\', '$\\backslash$').replace('_', '\_').replace('&', '\&').replace('%', '\%').replace('<', '$<$').replace('>', '$>$'), 
                arg_types))
    except yaml.YAMLError as exc:
        print(exc)