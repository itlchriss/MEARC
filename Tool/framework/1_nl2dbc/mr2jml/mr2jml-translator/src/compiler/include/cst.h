#ifndef CST_H
#define CST_H

#include "util.h"

// node of compile-time symbol table
struct cstsymbol {
    // name of the symbol
    char *symbol;
    // value of the symbol stored in the symbol table
    char *data;
    // reference list. denoting the pointers that referenced the symbol
    struct queue *refs;
    /* scope opening. default 0. the value 1 indicates the scope is closed and no more references can be added */
    int scope;
};


// add a new symbol to the compile-time symbol table
// the symbol is the symbol that is going to be added to the table
// the qsymbol is the symbol of the quantifier
// void addcstsymbol(struct queue* cst, struct token *symbol, struct token *qsymbol);
void addcstsymbol(struct queue *cst, char *symbol);
int addcstref(struct queue *cst, char *symbol, void *pt);
// struct cstsymbol* updatecstsymbol(struct queue* cst, char *symbol, char *data);
struct cstsymbol* updatecstsymbol(struct queue *, char *, void *);
void showcstsymbol(void *);
void syncsymbol(struct cstsymbol *);
struct cstsymbol* searchsymbolbyref(struct queue *, void *);
struct cstsymbol* searchcst(struct queue *, char *);
void closecstscope(struct queue *cst, char *symbol);

void removecstref(struct queue *, char *, void *);
void setvalue2cstsymbol(struct cstsymbol *cstsym, char *data);
void renamesymbols(struct queue *cst);
void deallocatecstsymbol(void *);
int getavailablerefs(struct cstsymbol *);
#endif
