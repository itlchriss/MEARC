#ifndef CST_H
#define CST_H

#include "util.h"


 enum explicit_datatype {
    Indexing = -2,
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
    // updated. there can be multiple data
    /* there can be multiple predicates that are being resolved to the same variable. this is common in compound subject, such as "A and B are not null" then both A and B are resolved to the same variable.*/
    struct queue *datalist;

    enum explicit_datatype datatype;
    /* a queue holding the possible SIs if the symbol is not synthesised */
    struct queue *si_q;
    /*
        Refining the status of the symbol instead of changing the status of the tree node
    */
    enum symbol_status status;
    /*
        Number of nodes that referenced this ptr
    */
    int ref_count;
};


// add a new symbol to the compile-time symbol table
// the symbol is the symbol that is going to be added to the table
// the qsymbol is the symbol of the quantifier
struct cstsymbol *newcstsymbol(char *symbol);
void showcstsymbol(void *);

void deallocatecstsymbol(void *);
void deallocatedata(void *);

/* 
    providing a checking on a valid datatype that is ready for synthesis
    basically, the data types that have prefix 'Java' are ready for synthesis
    an exception is 'RelDepend', which is only for predicate 'Rel'
*/
int has_datatype(struct cstsymbol *cstptr);
#endif
