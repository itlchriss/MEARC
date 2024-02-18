#ifndef SI_H
#define SI_H
#include "ast.h"
#include "cst.h"
#include "util.h"
/*
    Semantic interpretation header
    Each semantic interpretation is UNIQUE because each interpretation has a unique composite key of (term and penn-tree bank category).
*/

 enum javadatatype { PRIMITIVE = 0, NON_PRIMITIVE, NON_PRIMITIVE_WITH_DIMENSIONS };

struct si_arg {
    enum explicit_datatype datatype;
    char *symbol;
};


struct si {
    /* the term/predicate that is can be matched in the HOL */
    char *symbol;
    /* penn tree bank categories of this si */
    struct queue *syntax;
    /* the interpretation of this SI */
    char *interpretation;
    /* arguments accepted by this SI */
    struct queue *args;
    /* the data type of this SI after synthesis */
    enum explicit_datatype synthesised_datatype;
};

/* 
    semantic interpretation identification 
    this is a process to identify as many si as possible presenting in the meaning representation
    - parameter descriptions
        predicates  : a queue holding pointers of predicates present in an abstract syntax tree parsed from meaning representation
        silist      : a queue holding semantic interpretations parsed from standard semantic interpretation database
        cst         : a queue holding the compile time symbols, aka the identitiers in the meaning representation
*/
// void siidentification();
void sianalysis();
void sisynthesis();
void opresolution();
void showsi(void *_si);
void deallocatesi(void *);
void generate_param_si(char *);
#endif
