#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "util.h"
#include "cst.h"

char *cst_datatype_name[] = { "Predicate", "Function" };

struct cstsymbol* searchcst(struct queue *cst, struct token *symbol);


struct queue** initcsts(int scopes) {
    struct queue **new = (struct queue **) malloc (sizeof(struct queue *) * scopes);
    for (int i = 0; i < scopes; ++i) {
        new[i] = initqueue();
    }
    return new;
}

struct cstscope* addscope(struct queue *cstscopes, int scope, struct cstsymbol *symbol) {
    struct cstscope *new = (struct cstscope*) malloc (sizeof(struct cstscope));
    new->scope = scope;
    new->symbol = symbol;
    new->refs = initqueue();
    // TODO: adding a quantifying type in the symbol scope
    enqueue(cstscopes, (void*)new);    
    return new;
}

struct cstsymbol *newcstsymbol(struct token *token, struct token *quantifier) {
    struct cstsymbol *new = (struct cstsymbol*) malloc (sizeof(struct cstsymbol));
    new->token = token;
    new->datatype = CST_STDP;
    new->args = NULL;
    new->argcount = 0;
    new->alias = NULL;
    new->scopeclosed = 0;
    return new;
}

void showcst(struct queue* cst) {
    struct queuenode *tmp = cst->q->next;
    while (tmp != NULL) {
        struct cstsymbol *node = (struct cstsymbol*)tmp->datanode;
        printf("Symbol: %s, Data: %s, Type: %s\n", node->token->symbol, node->data, cst_datatype_name[node->datatype]);
        tmp = tmp->next;
    }
}


struct cstsymbol* addcstsymbol(struct queue *cst, struct token *token, struct token *qsymbol) {
    struct cstsymbol *new = (struct cstsymbol*) malloc (sizeof(struct cstsymbol));
    new->token = newtoken(token->symbol, token->line, token->column);
    new->data = NULL;
    // new->hash = sdbm(symbol->symbol);
    if (strcmp(qsymbol->symbol, "EXISTS")) {
        new->type = CST_EXISTS;
    } else {
        new->type = CST_FORALL;
    }
    new->datatype = CST_STDP;
    new->args = NULL;
    new->argcount = 0;
    new->alias = NULL;
    new->refs = initqueue();
    new->dstptr = NULL;
    new->of_referal = NULL;
    struct cstsymbol *tmp;
    // TODO: checking if the cst already has a name for the symbol. if so, we have to rename the current one
    while ((tmp = searchcst(cst, new->token)) != NULL) {
        // TODO: currently we consider only single integer       
        if (strlen(new->token->symbol) == 1) {
            free(new->token->symbol);
            new->token->symbol = (char*)malloc(sizeof(char)*3);
            new->token->symbol[0] = tmp->token->symbol[0];
            new->token->symbol[1] = '1';
            new->token->symbol[2] = '\0';
        } else {
            new->token->symbol[1]++;
        }         
    }
    enqueue(cst, (void*)new);
    return new;
}

int setvalue2cst(struct queue *cst, struct token *symbol, char *data) {
    struct cstsymbol *tmp = searchcst(cst, symbol);
    if (tmp == NULL) return 1;
    else {
        tmp->data = (char*) strdup (data);
        return 0;
    }
}

// int addreference2cst(struct queue *cst, char *symbol, void *ptr) {
//     struct cstsymbol *tmp = searchcst(cst, symbol);
//     if (tmp == NULL) return 1;
//     else {
//         enqueue(tmp->references, ptr);
//     }
//     return 0;
// }

// char* getsymboldata(struct queue *cst, char *symbol) {
//     struct cstsymbol *tmp = searchcst(cst, symbol);
//     if (tmp == NULL) return NULL;
//     else return tmp->data;
// }

void setvalue2cstsymbol(struct cstsymbol *cstsym, char *data) {
    cstsym->data = (char*) strdup (data);
}

void setalias2cstsymbol(struct cstsymbol *cstsym, char *data) {
    cstsym->alias = (char*) strdup (data);
}

struct cstsymbol* searchcst(struct queue *cst, struct token *symbol) {
    struct queuenode *tmp = cst->q->next;
    while(tmp != NULL) {        
        if (strcmp(((struct cstsymbol*)tmp->datanode)->token->symbol, symbol->symbol) == 0) {
            return ((struct cstsymbol*)tmp->datanode);
        }
        tmp = tmp->next;
    } 
    return NULL;
}

struct cstscope *searchcstscope(struct queue *cstscopes, struct token *token) {
    struct queuenode *tmp = cstscopes->q;
    while((tmp = tmp->next) != NULL) {
        if (strcmp(((struct cstscope*)tmp->datanode)->symbol->token->symbol, token->symbol) == 0) {
            return ((struct cstscope*)tmp->datanode);
        }
    }
    return NULL;
}

// void showcstscopes(struct queue *cstscopes) {
//     int i;
//     for (i = 0; i < cstscopes->count; ++i) {
//         s = (struct cstscope*)gqueue(cstscopes, i);
//     }
// }

void deallocatecst(struct queue *cst) {
    struct cstsymbol *tmp;
    int i;
    while (!isempty(cst)) {        
        tmp = (struct cstsymbol*)dequeue(cst);
        // if (tmp->symbol)
            // free(tmp->symbol);
        if (tmp->data)
            free(tmp->data);
        if (tmp->argcount > 0) {
            for (i = 0; i < tmp->argcount; ++i) {
                free(tmp->args[i]);
            }
            free(tmp->args);
        }
        if (tmp->alias) {
            free(tmp->alias);
        }
        if (tmp->refs) {
            deallocatequeue(tmp->refs);
        }
        if (tmp->token) {
            if (tmp->token->symbol) free(tmp->token->symbol);
            free(tmp->token);
        }
        // if (tmp->references) {
        //     deallocatequeue(tmp->references);
        // }
    }
    free(cst);    
}

void deallocatecsts(struct queue **csts, int scopes) {
    for (int i = 0; i < scopes; ++i) {
        if (csts[i])
            deallocatecst(csts[i]);  
    }    
}

void deallocatecstscopes(struct queue *cstscopes) {
    struct cstscope *tmp;
    while (cstscopes->count > 0) {
        tmp = (struct cstscope*)dequeue(cstscopes);
        deallocatequeue(tmp->refs);
        free(tmp);
    }
    free(cstscopes);
}
