#ifndef CORE_H
#define CORE_H
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ast.h"

extern int yylex(void);
extern int yyparse(void);
extern void yyerror(const char*);

// extern struct astnode *newformula (struct token *quantifier, struct token *variable, struct astnode *terms);
// extern struct astnode *newcomplexformula(struct astnode *cfnode, struct astnode *connnode, struct astnode *fnode);
#endif

