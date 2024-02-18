#ifndef CST_H
#define CST_H

#include "util.h"


 enum explicit_datatype {
    None = -1,
    JavaInteger = 0,
    JavaShort,
    JavaLong,
    JavaFloat,
    JavaDouble,
    JavaArray,
    JavaList,
    JavaString,
    JavaChar,
    JavaBool
};

enum symbol_status {
    Empty,
    Assigned
};

// node of compile-time symbol table
struct cstsymbol {
    // name of the symbol
    char *symbol;
    // value of the symbol stored in the symbol table
    // char *data;
    // updated. there can be multiple data
    /* there can be multiple predicates that are being resolved to the same variable. this is common in compound subject, such as "A and B are not null" then both A and B are resolved to the same variable.*/
    struct queue *datalist;

    // reference list. denoting the pointers that referenced the symbol
    struct queue *refs;
    /* scope opening. default 0. the value 1 indicates the scope is closed and no more references can be added */
    int scope;
    /* this field is to save the type from si, such type indicates the datatype of the data */
    int type;
    enum explicit_datatype datatype;
    void *si_ptr;
    /* a queue holding the possible SIs if the symbol is not synthesised */
    struct queue *si_q;
    /* this field is assigned when the si_ptr is assigned */
    /* it is for counting the unresolved grammar arguments */
    int g_arg_count;    
    /*
        Refining the status of the symbol instead of changing the status of the tree node
    */
    enum symbol_status status;
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
void deallocatedata(void *);
int getavailablerefs(struct cstsymbol *);

/* 
    providing a checking on a valid datatype that is ready for synthesis
    basically, the data types that have prefix 'Java' are ready for synthesis
    an exception is 'RelDepend', which is only for predicate 'Rel'
*/
int has_datatype(struct cstsymbol *cstptr);
#endif
