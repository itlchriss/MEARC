#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "cst.h"
#include "ast.h"

extern struct queue *cst;

void showcstsymbol(void *_symbol) {
    struct cstsymbol *c = (struct cstsymbol*)_symbol;
    printf("=============================Compile time symbol===================================\n");
    printf("Symbol: %s  (datatype: p(%d) r(%d) i(%d))    Number of Refs: %d\n", c->symbol, c->datatype->p, c->datatype->r, c->datatype->i, c->ref_count);
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
    if ((int)c->datatype->i == (int)SI_INT_TYPE_MULTIPLE_SI) {
        if (c->datatype->multiple_datatypes->count > 0) {
            printf("\nMutliple datatypes:");    
            for (int i = 0; i < c->datatype->multiple_datatypes->count; ++i) {
                printf("(datatype %d: p(%d) r(%d))  ", 
                    i,
                    ((struct datatype *)gqueue(c->datatype->multiple_datatypes, i))->p,
                    ((struct datatype *)gqueue(c->datatype->multiple_datatypes, i))->r
                );
            }
        }
    }
    if (c->conjunction_operators->count > 0) {
        printf("\nConjunctions:");    
        for (int i = 0; i < c->conjunction_operators->count; ++i) {
            printf("%s  ", (char *)gqueue(c->conjunction_operators, i));
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
    new->interpretation_type = -1;
    new->datatype->lazy_resolve = NULL;
    new->datatype->types = initqueue();
    new->datatype->element_datatype = NULL;
    new->datatype->relative_datatype = NULL;
    new->datatype->multiple_datatypes = initqueue();
    new->astptr = NULL;
    new->status = Empty;
    new->datalist = initqueue();
    new->ref_count = 0;
    new->conjunction_operators = initqueue();
    enqueue(cst, (void*)new);
    return new;
}


struct datatype *copydatatype(struct datatype *input) {
    struct datatype *new = (struct datatype *)malloc(sizeof(struct datatype));
    new->p = input->p;
    new->r = input->r;
    new->i = input->i;
    // TODO: the object class names should be copied as well
    new->types = NULL;
    new->relative_datatype = new->element_datatype = NULL;
    new->lazy_resolve = NULL;
    new->multiple_datatypes = NULL;
    return new;
}


int datatype_is_specific(struct datatype *dt) {
    if (dt->p >= 0 || dt->r >= 0) return TRUE;
    else return FALSE;
}


/* 
    providing a checking on a valid datatype that is ready for synthesis
    basically, the data types that have prefix 'Java' are ready for synthesis
    an exception is 'RelDepend', which is only for predicate 'Rel'
*/
int has_datatype(struct cstsymbol *cstptr) {
    // if (cstptr->datatype->p >= 0 || cstptr->datatype->r >= 0) return TRUE;
    // else return FALSE;
    return datatype_is_specific(cstptr->datatype);
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
    if (c->datatype->element_datatype) {
        deallocatequeue(c->datatype->element_datatype->types, deallocatedata);
    }
    if (c->conjunction_operators && c->conjunction_operators->count > 0) {
        deallocatequeue(c->conjunction_operators, deallocatedata);
    }
    /* we never allocate the type_names in the relative datatype */
}

