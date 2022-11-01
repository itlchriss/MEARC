#include "jml.h"

char * conv_operator(operator op) {
    int idx = (int)op;
    return j_opers[idx];
}