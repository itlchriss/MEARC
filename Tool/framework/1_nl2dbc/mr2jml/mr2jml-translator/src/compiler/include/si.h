#ifndef SI_H
#define SI_H
#include "ast.h"
#include "util.h"
/*
    Semantic interpretation header
    Each semantic interpretation is UNIQUE because each interpretation has a unique composite key of (symbol and penn-tree bank category).
*/

struct si {
    char *symbol;
    /* penn tree bank category of this si */
    enum ptbsyntax ptb;
    /* number of argument to synthesize this semantic */
    int arg_count;    
    /* arguments in the interpretation. the length of args must be equal to arg_count */
    char **args;    
    char *interpretation;
};

struct si* newsi(char *data);
#endif