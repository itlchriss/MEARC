#ifndef CG_H
#define CG_H

#include "ast.h"

// Code generation header

static char *connective_code[] = { "&&", "||", "<==>", "==>" };

// void gencode(struct astnode *, int);
void output(struct astnode *);

#endif
