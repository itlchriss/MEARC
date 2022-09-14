#ifndef SEM_H
#define SEM_H

#include "util.h"
#include "sst.h"

enum semantictype {
    // indicates the semantic require arguments
    Sem_Template,
    // indicates the semantic does not affect by the arguments
    Sem_Keyword,
    // indicates the semantic is exactly the name used in the software
    Sem_SymbolName,
    // indicates the semantic is an identity function, there is no argument
    Sem_Identity
};

enum semanticsourcetype {
    SemSource_Static,
    SemSource_Dynamic
};

enum swltype {
    Swl_Param,
    Swl_Function,
    Swl_Class,
    Swl_Interface
};

struct semanticlist {
    // the software element type. this type can be indicated by static/dynamic symbol
    enum swltype swltype;
    // there can be lots of semantic for a single symbol under a syntax
    struct queue *s;
};

struct semantic {
    enum semanticsourcetype sstype;
    // the semantic data
    char *data;
    // the type of the semantic. a keyword will not be affected by the arguments
    enum semantictype semtype;
    // the arity may not be equal to the argsize. the argument size depends on the template requirement    
    int arity, argsize;
    char **args;
    // either dstpointer or sstpointer
    void *sourceptr;
    // struct queue *args;
};

struct semanticlist* initsemlist();
// if the arguments are from sst, both template and args should not be null
// else, the arguments are from dst, args should be null
// struct semantic* newsemantic(char *template, struct sstnode *sstptr);
struct semantic* newsemantic(enum semanticsourcetype sstype, void *sourceptr);
int countsemantics(struct semanticlist *list);
void addsemantic(struct semanticlist *list, struct semantic *s);
struct semantic* getsemantic(struct semanticlist *list, int position);
void deallocatesemantics(struct semanticlist *list);
struct arg* clonearg(struct arg* _arg);


// struct semantic* newsemanticdetail(enum semantictype type, char *template);
#endif
