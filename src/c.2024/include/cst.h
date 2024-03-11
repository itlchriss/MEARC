#ifndef CST_H
#define CST_H

#include "util.h"

#ifndef UNDEFINED
#define UNDEFINED -1
#endif
#ifndef ANY
#define ANY -2
#endif
#ifndef RELTYPE
#define RELTYPE -9
#endif
#ifndef ALLOWED
#define ALLOWED 1
#endif
#ifndef NOTALLOWED
#define NOTALLOWED 0
#endif

enum primitive_datatype {
    AnyPrimitiveType = -2,
    Boolean = 0,
    Byte = 1,
    Char = 2,
    Short = 3,
    Integer = 4,
    Long = 5,
    Float = 6,
    Double = 7
};

// Class, Interface are treated as Object
enum reference_datatype {
    AnyRefType = -2,
    Array = 0,
    String = 1,
    Object = 2
};

// type stores the type of class and interface
struct datatype {
    enum primitive_datatype p;
    enum reference_datatype r;
    /* 
        special keywords to resolve runtime elements, currently at most 1 keyword can be used
        __REF__type: the type stored in the types of an entity
    */
    char *lazy_resolve;
    /* all elements in this queue must be C-strings. each C-string represents a type name */
    struct queue *types;
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
    /* 
        This is a flag distinguishing if the current datalist can contain multiple data 
        The reason why we need this is to check if the MR semantics is correct.
        If there is a term, which is defined as plural, then the entity it accepts as argument should allow multiple data.
        Otherwise, this entity should allow single data.        
    */
    // TODO BE ADDED
    // int allow_multi_data;

    // enum explicit_datatype datatype;
    struct datatype *datatype;
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
