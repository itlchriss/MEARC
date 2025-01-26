#ifndef CST_H
#define CST_H

#include "util.h"
#include "si.h"

#ifndef UNDEFINED
#define UNDEFINED -1
#endif
#ifndef ANY
#define ANY -2
#endif
#ifndef RELTYPE
#define RELTYPE -9
#endif
#ifndef EVENTTYPE
#define EVENTTYPE -10
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
    Object = 2,
    List = 3,
    String_Array = 4
};

/*
Definition must be the same as interpretation_type in si.h
*/
enum intermediate_SI_type {
    INT_SI_TYPE_UNDEFINED = -1,
    INT_SI_TYPE_DIRECT = 0,
    INT_SI_TYPE_EXPR = 1,
    INT_SI_TYPE_MODIFIER = 2,
    INT_SI_TYPE_JAVA_METHOD = 3,
    INT_SI_TYPE_JAVA_METHOD_CHAIN = 4,
    INT_SI_TYPE_JAVA_TYPE = 5,
    INT_SI_TYPE_JAVA_MEMBER_METHOD = 6,
    INT_SI_TYPE_JAVA_MEMBER_ATTR = 7,
    INT_SI_TYPE_MULTIPLE_SI = 8,

    INT_SI_TYPE_JAVA_BOOLEAN = 50,
    INT_SI_TYPE_JAVA_BYTE = 51,
    INT_SI_TYPE_JAVA_CHAR = 52,
    INT_SI_TYPE_JAVA_SHORT = 53,
    INT_SI_TYPE_JAVA_INTEGER = 54,
    INT_SI_TYPE_JAVA_LONG = 55,
    INT_SI_TYPE_JAVA_FLOAT = 56,
    INT_SI_TYPE_JAVA_DOUBLE = 57
};

// type stores the type of class and interface
struct datatype {
    enum primitive_datatype p;
    enum reference_datatype r;
    enum intermediate_SI_type i;
    /* 
        special keywords to resolve runtime elements, currently at most 1 keyword can be used
        __REF__type: the type stored in the types of an entity
    */
    char *lazy_resolve;
    /* all elements in this queue must be C-strings. each C-string represents a type name */
    struct queue *types;
    /* only use when r == 2 */
    struct datatype *element_datatype;
    /* when a compile symbol is a rel symbol, after its synthesis, we store the datatype of the symbol that decides the SI of this rel symbol*/
    struct datatype *relative_datatype;

    /*
    * Used when the datalist length is more than 1. However, it is only the case when the datalist length is incremented by 
    *  having more direct syntax. Another words, if the datalist length is incremented because of ambiguous SI, then it is not used.
    */
    struct queue *multiple_datatypes;

    /*
    * Used when REL synthesis is performed. 
    * This field holds the original variable
    * For instance, the length of X
    * then, this field holds a value X
    */
    char *relative_var;
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
    /* type of the synthesised intermediate SI type, refer to interpretation_type in si.h */
    int interpretation_type;
    /*
        Number of nodes that referenced this ptr
    */
    int ref_count;

    /*
    * The predicate(astnode) of this event cstpointer is refered to. Such as sort(e01) then e01->astptr = ptr(sort)
    */
    struct astnode *astptr;

    /*
    * Used when the datalist length is more than 1. We record the node's connective in this list to perform conjuction
    */
    struct queue *conjunction_operators;    
    /*
    * A field to record if this symbol is an argument to a predicate. If not, its alias should be check in SI analysis
    */
    int is_argument_to_predicate;
    /*
        TRUE indicates the symbol is an abstract noun SI and requires further synthesis
     */
    int abstract_synthesis_required;

    /*
        TRUE indicates the symbol has a type defined in the input
     */
    int type_assigned;
};


// add a new symbol to the compile-time symbol table
// the symbol is the symbol that is going to be added to the table
// the qsymbol is the symbol of the quantifier
struct cstsymbol *newcstsymbol(char *symbol);
void showcstsymbol(void *);


/*
* cloning a datatype structure
*/
struct datatype *copydatatype(struct datatype *);

void deallocatecstsymbol(void *);
void deallocatedata(void *);

/* 
    providing a checking on a valid datatype that is ready for synthesis
    basically, the data types that have prefix 'Java' are ready for synthesis
    an exception is 'RelDepend', which is only for predicate 'Rel'
*/
int has_datatype(struct cstsymbol *cstptr);

/*
    doing the same as the has_datatype but accepts argument with type struct datatype
*/
int datatype_is_specific(struct datatype *dt);
#endif
