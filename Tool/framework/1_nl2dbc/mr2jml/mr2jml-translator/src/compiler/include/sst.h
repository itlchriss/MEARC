#ifndef SST_H
#define SST_H

#include "util.h"
#include "ast.h"

// Static Symbol Table header
//TODO: change the list structure to a hash table


struct sstnode {
    //a binary mask for masking the valid syntax of a symbol, the length is 36bit
    // e.g., a symbol of coordinating junction (CC) is 0b000000000000000000000000000000000001
    // unsigned long long validsyntaxmask;
    //an integer array for storing the valid syntax of the symbol
    //each element is the value corresponding to the enum ptbsyntax
    int *syntax;
    int syntaxsize;
    int arity;
    char *symbol;
    unsigned long long symbolhash;
    //the standard predicate (aka, JML) that the symbol is mapping to
    // char *stdpredicate;
    // number of args must be equal to the arity of the sstnode
    int argsize;
    char **args;
    // the template of the standard predicate. substituting the args forming the predicate
    char *template;
    // struct queue *_args;
    struct sstnode *next;
};

// struct arg {
//     char *name;
//     int *syntax;
//     // number of syntax 
//     int ssize;
// };



struct sstnode* newsstnode(char *symbol, char *syntax, int arity, char *stdpredicate);
void addsstnode(struct sstnode *sst, struct sstnode *new);
//getting the standard predicate of a symbol with its syntax and arity
//if the symbol with its syntax exists, its standard preciate is returned
//otherwise, null is returned
// char* getstdpredicate(struct sstnode *sst, char *symbol, char *syntax, int arity);
void showsst(struct sstnode *sst);
//deallocation of the static symbol table
void deallocatesst(struct sstnode* sst);
//getting the arity of a symbol
//if the symbol is not found, return -1; the arity is returned on found symbol.
// int getssarity(struct sstnode *sst, char *symbol, char *syntax);
//getting the standard predicates of a symbol
//we may have several declarations of standard predicates due to the ambiguity of nl
// e.g., be can be 1-arity and 2-arity
//therefore, we return a queue of symbols
struct queue *getsstsymbols(struct sstnode *sst, char *symbol, char *syntax, int arity);
//getting the symbol with the given arity
//the given sstlist is the list of sst related to a symbol
// e.g. sst of be -> stdp of 1-arity and stdp of 2-arity
// return the symbol with the given arity or NULL is returned on not found such symbol under the precondition
struct sstnode* getsymbolfromqueue(struct queue *sstlist, int arity);


// struct arg* getarg(struct queue *args, int index);
#endif
