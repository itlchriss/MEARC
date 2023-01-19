#ifndef SI_H
#define SI_H
#include "ast.h"
#include "util.h"
/*
    Semantic interpretation header
    Each semantic interpretation is UNIQUE because each interpretation has a unique composite key of (term and penn-tree bank category).
*/

enum javadatatype { PRIMITIVE = 0, NON_PRIMITIVE, NON_PRIMITIVE_WITH_DIMENSIONS };

struct si {
    char *term;
    /* penn tree bank categories of this si */
    enum ptbsyntax *syntax;
    /* number of syntax that can be accepted for synthesising this semantic */
    int syntax_count;
    /* number of argument to synthesize this semantic */
    int arg_count; 
    /* number of argument to synthesize the grammar relation semantic */
    int g_arg_count;   
    /* arguments in the interpretation */
    char **args;
    /* grammar arguments in the interpretation */
    char **g_args;
    int *arg_types;
    char *interpretation;
    enum javadatatype jtype;
};

/* 
    semantic interpretation identification 
    this is a process to identify as many si as possible presenting in the meaning representation
    - parameter descriptions
        predicates  : a queue holding pointers of predicates present in an abstract syntax tree parsed from meaning representation
        silist      : a queue holding semantic interpretations parsed from standard semantic interpretation database
        cst         : a queue holding the compile time symbols, aka the identitiers in the meaning representation
*/
void siidentification();
void opresolution();
void showsi(void *_si);
void deallocatesi(void *);
#endif
