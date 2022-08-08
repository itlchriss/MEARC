#include <stdlib.h>
#include <string.h>
#include "sem.h"
#include "util.h"
#include "ast.h"

char *swl_reserved_semantics[] = { "param", "function", "class", "interface" };

void deallocatesemantic(struct semantic *s);

struct semanticlist* initsemlist() {
    struct semanticlist *new = (struct semanticlist*) malloc(sizeof(struct semanticlist));
    new->swltype = -1;
    new->s = initqueue();
    return new;
}

struct semantic *newsemantic(enum semanticsourcetype sstype, void *sourceptr) {
    struct semantic *new = (struct semantic*) malloc (sizeof(struct semantic));
    new->sstype = sstype;            
    new->sourceptr = sourceptr;
    if (sstype == SemSource_Dynamic) {
        new->data = NULL;
        new->semtype = Sem_SymbolName;
        new->args = NULL;
        new->argsize = 0;        
        new->arity = 0;
    } else {
        struct sstnode *sstptr = (struct sstnode*)sourceptr;
        new->data = (char*)strdup(sstptr->template);
        new->arity = sstptr->arity;
        if (sstptr->arity == 0) {
            new->semtype = Sem_Identity;
        } else if (strcmp(sstptr->args[0], "*") == 0 && sstptr->arity == 1) { 
            new->semtype = Sem_Keyword;
        } else {
            new->semtype = Sem_Template;
        }
        new->argsize = sstptr->argsize;    
        new->args = (char**) malloc (sizeof(char*) * new->argsize);    
        int i;
        for (i = 0; i < new->argsize; ++i) {
            new->args[i] = (char*)strdup(sstptr->args[i]);        
        }
    }
    return new;
}

// struct semantic* newsemantic(char *template, struct sstnode *sstptr) {
//     struct semantic *new = (struct semantic*) malloc (sizeof(struct semantic));            
//     if (sstptr == NULL) {
//         new->data = (char*)strdup(template);
//         new->semtype = Sem_SymbolName;
//         new->args = NULL;
//         new->argsize = 0;        
//         new->arity = 0;
//     } else {
//         new->data = (char*)strdup(sstptr->template);
//         new->arity = sstptr->arity;
//         if (sstptr->arity == 0) {
//             new->semtype = Sem_Identity;
//         } else if (strcmp(sstptr->args[0], "*") == 0 && sstptr->arity == 1) { 
//             new->semtype = Sem_Keyword;
//         } else {
//             new->semtype = Sem_Template;
//         }
//         new->argsize = sstptr->argsize;    
//         new->args = (char**) malloc (sizeof(char*) * new->argsize);    
//         int i;
//         for (i = 0; i < new->argsize; ++i) {
//             new->args[i] = (char*)strdup(sstptr->args[i]);        
//         }
//     }
//     return new;
// }





void addsemantic(struct semanticlist *list, struct semantic *s) {
    //TODO: more reserved words can be added
    // int i = 0;    
    // for (i = 0; i < 4; ++i) {
    //     if (strcmp(s->data, swl_reserved_semantics[i]) == 0) {    
    //         list->swltype = i;
    //         deallocatesemantic(s);
    //         goto END;
    //     }
    // }
    enqueue(list->s, (void*)s);
    // END:
        return;
}

struct semantic* getsemantic(struct semanticlist *list, int position) {
    return (struct semantic*)gqueue(list->s, position);
}

int countsemantics(struct semanticlist *list) {
    return list->s->count;
}

void deallocatesemantic(struct semantic *s) {
    if (s->data) 
        free(s->data);
    if (s->argsize > 0) {
        int i;
        for (i = 0; i < s->argsize; ++i) {
            free(s->args[i]);
            // struct arg *arg = getarg(s->args, i);            
            // free(arg->name);
            // free(arg->syntax);
            // free(arg);
        }
        free(s->args);
    }
    if (s)
        free(s);
}

void deallocatesemantics(struct semanticlist *list) {
    struct semantic *tmp;
    while (countsemantics(list) > 0) {
        tmp = (struct semantic *)dequeue(list->s);
        deallocatesemantic(tmp);
    }
}
