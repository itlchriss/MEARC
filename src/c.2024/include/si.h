#ifndef SI_H
#define SI_H
#include "ast.h"
#include "cst.h"
#include "util.h"
/*
    Semantic interpretation header
    Each semantic interpretation is UNIQUE because each interpretation has a unique composite key of (term and penn-tree bank category).
*/

/*
    Type for the interpretation
    If the type is a modifier, then the intermediate SI is stored in CST only, the subtree is removed
    If the type is an expression, the intermediate SI is stored in the subtree root
*/
enum interpretation_type {
    SI_INT_TYPE_UNDEFINED = -1,
    SI_INT_TYPE_DIRECT = 0,
    SI_INT_TYPE_EXPR = 1,
    SI_INT_TYPE_MODIFIER = 2,    
    SI_INT_TYPE_JAVA_METHOD_CHAIN = 4,
    SI_INT_TYPE_JAVA_TYPE = 5,
    SI_INT_TYPE_JAVA_MEMBER_METHOD = 6,
    SI_INT_TYPE_JAVA_MEMBER_ATTR = 7,
    SI_INT_TYPE_MULTIPLE_SI = 8,
    SI_INT_TYPE_FUNCTION = 9,
    
    SI_INT_TYPE_EXPR_REQ_PARAM = 100,
    SI_INT_TYPE_TEMPLATE = 101,
    SI_INT_TYPE_CONSTRUCT = 102,
    SI_INT_TYPE_JAVA_METHOD = 103
};

struct si_arg {
    // enum explicit_datatype datatype;
    struct datatype *datatype;
    char *symbol;
};


struct si {
    /* the term/predicate that is can be matched in the HOL */
    char *symbol;
    /* penn tree bank categories of this si */
    struct queue *syntax;
    /* the interpretation of this SI */
    char *interpretation;
    /* external argument */
    /* this is used when a predicate needs external SI to complete its SI */
    /* for instance, the range of a value requires the SI of the value */
    struct si_arg *exarg;
    /* type of this SI */
    enum interpretation_type type;
    /* arguments accepted by this SI */
    struct queue *args;
    /* the data type of this SI after synthesis */
    // enum explicit_datatype synthesised_datatype;
    struct datatype *synthesised_datatype;
    /* 
        only used when the interpretation is a quantify expression 
        according to the JML spec,
        the last part of the quantify expression is called spec-initializer
        this is a type to specify such spec-initializer in the interpretation as a template.
        for instance, 
        \forall int i; 0 <= i < x.length(); k
        k is a spec-initializer which can be a function of x, or it can be anything
        spec_init_type is the type of k
        this integer should be consistent with the declaration of enum primitive_datatype in cst.h
    */
    int spec_init_type;
    /*
        TRUE indicates the symbol is an abstract noun SI and requires further synthesis
     */
    int abstract_synthesis_required;
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
int search_syntax(struct si* si, enum ptbsyntax ptb);
#endif
