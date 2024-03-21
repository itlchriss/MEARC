#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "cst.h"
#include "ast.h"

extern struct queue *cst;

void showcstsymbol(void *_symbol) {
    struct cstsymbol *c = (struct cstsymbol*)_symbol;
    printf("=============================Compile time symbol===================================\n");
    printf("Symbol: %s  (datatype: p(%d) r(%d))    Number of Refs: %d\n", c->symbol, c->datatype->p, c->datatype->r, c->ref_count);
    printf("Data: ");
    for (int i = 0; i < c->datalist->count; ++i) {
        char *data = (char *)gqueue(c->datalist, i);
        printf("%s  ", data);
    }
    if (c->datatype->types->count > 0) {
        printf("    Types");
        for (int i = 0; i < c->datatype->types->count; ++i) {
            printf("%s  ", (char *)gqueue(c->datatype->types, i));
        }
    }
    printf("\n===================================================================================\n");
}


struct cstsymbol *newcstsymbol(char *symbol) {
    struct cstsymbol *new = (struct cstsymbol*) malloc (sizeof(struct cstsymbol));
    new->symbol = (char*)strdup(symbol);     
    new->si_q = NULL;
    new->datatype = (struct datatype *)malloc(sizeof(struct datatype));
    new->datatype->p = UNDEFINED;
    new->datatype->r = UNDEFINED;
    new->datatype->lazy_resolve = NULL;
    new->datatype->types = initqueue();
    new->status = Empty;
    new->datalist = initqueue();
    new->ref_count = 0;
    enqueue(cst, (void*)new);
    return new;
}


/* 
    providing a checking on a valid datatype that is ready for synthesis
    basically, the data types that have prefix 'Java' are ready for synthesis
    an exception is 'RelDepend', which is only for predicate 'Rel'
*/
int has_datatype(struct cstsymbol *cstptr) {
    if (cstptr->datatype->p >= 0 || cstptr->datatype->r >= 0) return TRUE;
    else return FALSE;
}


int __cstsymbolcomparator(void *_pt, void *_symbol) {
    struct cstsymbol* c = (struct cstsymbol*)_pt;
    char *symbol = (char*)_symbol;
    if (strcmp(c->symbol, symbol) == 0) return TRUE;
    else return FALSE;
}

int __ptrcomparator(void *_aptr, void *_baptr) {
    if (_aptr == _baptr) {
        return TRUE;
    } else {
        return FALSE;
    }
}





void deallocatedata(void *_data) {
    char *data = (char *)_data;
    
    if (data)
        free(data);
}

void deallocatecstsymbol(void *_cstsymbol) {
    struct cstsymbol *c = (struct cstsymbol *)_cstsymbol;
    if (c->symbol)
        free(c->symbol);
    deallocatequeue(c->datalist, deallocatedata);
    if (c->datatype->types && c->datatype->types->count > 0) {
        deallocatequeue(c->datatype->types, deallocatedata);
    }
}

