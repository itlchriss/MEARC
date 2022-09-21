#ifndef SI_H
#define SI_H
#include "ast.h"
#include "util.h"
/*
    Semantic interpretation header
    Each semantic interpretation is UNIQUE because each interpretation has a unique composite key of (term and penn-tree bank category).
*/

struct si {
    char *term;
    /* penn tree bank categories of this si */
    enum ptbsyntax *syntax;
    /* number of syntax that can be accepted for synthesising this semantic */
    int syntax_count;
    /* number of argument to synthesize this semantic */
    int arg_count;    
    /* arguments in the interpretation */
    // struct queue *args;
    char **args;
    char *interpretation;
};

/* 
    semantic interpretation identification 
    this is a process to identify as many si as possible presenting in the meaning representation
    - parameter descriptions
        predicates  : a queue holding pointers of predicates present in an abstract syntax tree parsed from meaning representation
        silist      : a queue holding semantic interpretations parsed from standard semantic interpretation database
        cst         : a queue holding the compile time symbols, aka the identitiers in the meaning representation
*/
void siidentification(struct queue*, struct queue *, struct queue *);
void showsi(void *_si);
void deallocatesi(void *);
#endif
