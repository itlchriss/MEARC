#ifndef CST_H
#define CST_H

#include "util.h"


 enum explicit_datatype {
    JavaInteger,
    JavaShort,
    JavaLong,
    JavaFloat,
    JavaDouble,
    JavaArray,
    JavaList
};

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
    /* this field is to save the type from si, such type indicates the datatype of the data */
    int type;
    enum explicit_datatype symbol_datatype;
    void *si_ptr;
    /* a queue holding the possible SIs if the symbol is not synthesised */
    struct queue *si_q;
    /* this field is assigned when the si_ptr is assigned */
    /* it is for counting the unresolved grammar arguments */
    int g_arg_count;
};


// add a new symbol to the compile-time symbol table
// the symbol is the symbol that is going to be added to the table
// the qsymbol is the symbol of the quantifier
// void addcstsymbol(struct queue* cst, struct token *symbol, struct token *qsymbol);
void addcstsymbol(char *symbol);
int addcstref(char *symbol, void *pt);
struct cstsymbol* updatecstsymbol(char *, void *);
void showcstsymbol(void *);
void syncsymbol(struct cstsymbol *);
struct cstsymbol* searchsymbolbyref(void *);
struct cstsymbol* searchcst(char *);
void closecstscope(char *symbol);

void removecstref(char *, void *);
void setvalue2cstsymbol(struct cstsymbol *cstsym, char *data);
void renamesymbols();
void deallocatecstsymbol(void *);
int getavailablerefs(struct cstsymbol *);
#endif
