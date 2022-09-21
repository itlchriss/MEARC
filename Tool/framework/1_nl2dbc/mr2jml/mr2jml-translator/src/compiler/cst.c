#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "cst.h"
#include "ast.h"

struct cstsymbol* searchcst(struct queue *cst, char *symbol);

void showcstsymbol(void *_symbol) {
    struct cstsymbol *c = (struct cstsymbol*)_symbol;
    printf("=============================Compile time symbol===================================\n");
    printf("Symbol: %s   Data: %s   No. of Refs: %d\n", c->symbol, c->data, c->refs->count);
    printf("===================================================================================\n");
}

void addcstsymbol(struct queue *cst, char *symbol) {
    struct cstsymbol *tmp = searchcst(cst, symbol);
    if (tmp != NULL && tmp->scope == 0) {
        printf("Error in building compiler symbol table. Adding new symbol(%s) when there is a symbol(%s) with open scope existed.\n", symbol, tmp->symbol);
    } else {
        struct cstsymbol *new = (struct cstsymbol*) malloc (sizeof(struct cstsymbol));
        if (tmp != NULL) {
            /* TODO: the current renaming strategy is naive */
            if (strlen(symbol) == 1) {
                new->symbol = (char*) malloc (sizeof(char) * 3);
                new->symbol[0] = symbol[0];
                new->symbol[1] = '1';
                new->symbol[2] = '\0';
            } else {
                /* overflow when the symbol length is greater than 2 */
                char *s = (char*)malloc(sizeof(char) * 2);
                s[0] = symbol[1];
                s[1] = '\0';
                int n = atoi(s);
                ++n;
                sprintf(s, "%d", n);
                new->symbol = (char*) malloc(sizeof(char) * 3);
                new->symbol[0] = symbol[0];
                new->symbol[1] = s[0];
                new->symbol[2] = '\0';
            }   
        } else {
            new->symbol = (char*)strdup(symbol);
        }
        new->data = NULL;
        new->refs = initqueue();
        new->scope = 0;            
        enqueue(cst, (void*)new);
    }            
}

int addcstref(struct queue *cst, char *symbol, void *pt) {
    struct cstsymbol *c = searchcst(cst, symbol);
    if (c == NULL) {
        #if CSTDEBUG
        printf("Compile time symbol(%s) not found\n", symbol);
        #endif
        return 1;
    } else if (c->scope == 0) {
        enqueue(c->refs, pt);
        return 0;
    } else {
        #if CSTDEBUG
        printf("Error in building compiler symbol table. Trying to add identifier(%s) to the references but the scope is closed.\n", symbol);
        #endif
        exit(-2);
    }
}

void closecstscope(struct queue *cst, char *symbol) {
    struct cstsymbol *c = searchcst(cst, symbol);
    if (c != NULL) {
        c->scope = 1;
    }
}

// int setvalue2cst(struct queue *cst, struct token *symbol, char *data) {
//     struct cstsymbol *tmp = searchcst(cst, symbol);
//     if (tmp == NULL) return 1;
//     else {
//         tmp->data = (char*) strdup (data);
//         return 0;
//     }
// }

struct cstsymbol* updatecstsymbol(struct queue* cst, char *symbol, char *data) {
    struct cstsymbol *c = searchcst(cst, symbol);
    if (c->data) {
        free(c->data);
    }
    c->data = (char*)strdup(data);
    return c;
}

void syncsymbol(struct cstsymbol *c) {
    for (int i = 0; i < c->refs->count; ++i) {
        struct astnode *node = (struct astnode*)gqueue(c->refs, i);
        free(node->token->symbol);
        node->token->symbol = (char*)strdup(c->data);
    }
}

void setvalue2cstsymbol(struct cstsymbol *cstsym, char *data) {
    cstsym->data = (char*) strdup (data);
}

int cstsymbolcomparator(void *_pt, void *_symbol) {
    struct cstsymbol* c = (struct cstsymbol*)_pt;
    char *symbol = (char*)_symbol;
    return strcmp(c->symbol, symbol);
}

struct cstsymbol* searchcst(struct queue *cst, char *symbol) {
    return (struct cstsymbol*)searchqueue(cst, symbol, cstsymbolcomparator);
}

void deallocatecstsymbol(void *_cstsymbol) {
    struct cstsymbol *c = (struct cstsymbol *)_cstsymbol;
    free(c->symbol);
    free(c->data);
    deallocatequeue(c->refs, NULL);
}

