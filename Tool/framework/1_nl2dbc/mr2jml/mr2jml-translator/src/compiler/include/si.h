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
    /* penn tree bank category of this si */
    enum ptbsyntax syntax;
    /* number of argument to synthesize this semantic */
    int arg_count;    
    /* arguments in the interpretation */
    // struct queue *args;
    char **args;
    char *interpretation;
};

void siidentification(struct queue* predicates, struct queue *silist);
void showsilist(struct queue *silist);
void deallocatesilist(struct queue *silist);
void deallocatesi(struct si *si);
#endif
