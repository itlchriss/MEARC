#ifndef CST_H
#define CST_H

#include "ast.h"
#include "util.h"

// Compile-time symbol table header

enum cstsymboltype { CST_EXISTS, CST_FORALL };
enum cstsymboldatatype { CST_STDP, CST_STDPF };

// data structure of a compiler symbol table for a subtree
// each subtree represents a formula scope
struct cstscope {
    // global scope is 0
    int scope;
    // the symbol declared name and information
    struct cstsymbol *symbol;
    // the nodes that reference this symbol
    struct queue *refs;
};

// node of compile-time symbol table
struct cstsymbol {
    // name of the symbol
    // char *symbol;
    int scopeclosed;
    // token information
    struct token *token;
    // value of the symbol stored in the symbol table
    char *data;
    // hash of the symbol
    unsigned long long hash;    
    // reference list. denoting the pointers that referenced the symbol
    // struct queue *references;
    // the type of the symbol affects the target code generation
    // e.g. exists x, we only need to provide the target code related to one x
    //      forall x, we have to provide the target code related to all elements in a set called x
    enum cstsymboltype type;
    enum cstsymboldatatype datatype;

    struct dstnode *dstptr;
    char **args;
    int argcount;
    // the actual symbol name in the source code
    // TODO: resolve this symbol name in the code generation
    char *alias;   
    // the nodes that reference this symbol
    struct queue *refs;
    // TODO: we may want to change this name
    // this is used in the case that when we encounter the "of", the first child of the "of" is the variable which is the one that this pointer points to
    //  and the second child is the argument being substituted in the function that referring the first variable
    struct cstsymbol *of_referal;
};


// // add a new symbol to the compile-time symbol table
// void addcstsymbol(struct queue* cst, char *symbol, enum cstsymboltype type);
// // setting the value in the input data to a symbol same as the input symbol in the compile-time symbol table
// // if the result is 1, the symbol is not found; the result is 0, the symbol is found and data is assigned to the symbol
// int setvalue2cst(struct queue* cst, char *symbol, char *data);
// void showcst(struct queue* cst);
// void deallocatecst(struct queue* cst);
// int addreference2cst(struct queue *cst, char *symbol, void *ptr);
// int setvalue2cst(struct queue *cst, char *symbol, char *data);
// char* getsymboldata(struct queue *cst, char *symbol);
// struct cstsymbol* getsymboldata(struct queue *cst, char *data);

// add a new symbol to the compile-time symbol table
// the symbol is the symbol that is going to be added to the table
// the qsymbol is the symbol of the quantifier
struct cstsymbol* addcstsymbol(struct queue* cst, struct token *token, struct token *qsymbol);
// setting the value in the input data to a symbol same as the input symbol in the compile-time symbol table
// if the result is 1, the symbol is not found; the result is 0, the symbol is found and data is assigned to the symbol
int setvalue2cst(struct queue* cst, struct token *symbol, char *data);
void showcst(struct queue* cst);
// void deallocatecst(struct queue* cst);
void deallocatecsts(struct queue** csts, int scopes);
int addreference2cst(struct queue *cst, struct token *symbol, void *ptr);
int setvalue2cst(struct queue *cst, struct token *symbol, char *data);
char* getsymboldata(struct queue *cst, struct token *symbol);
struct cstsymbol* searchcst(struct queue *cst, struct token *symbol);

void setvalue2cstsymbol(struct cstsymbol *cstsym, char *data);
void setalias2cstsymbol(struct cstsymbol *cstsym, char *data);

struct queue** initcsts(int scopes);
struct cstscope* addscope(struct queue *cstscopes, int scope, struct cstsymbol *symbol);
void deallocatecstscopes(struct queue *cstscopes);
struct cstsymbol *newcstsymbol(struct token *token, struct token *quantifier);
struct cstscope* searchcstscope(struct queue *cstscopes, struct token *token);
void showcstscopes(struct queue *cstscopes);
#endif

