#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "cst.h"
#include "ast.h"

struct cstsymbol* searchcst(struct queue *cst, char *symbol);
struct cstsymbol* __searchcst(struct queue *cst, char *symbol);

void showcstsymbol(void *_symbol) {
    struct cstsymbol *c = (struct cstsymbol*)_symbol;
    printf("=============================Compile time symbol===================================\n");
    printf("Symbol: %s   Data: %s   No. of Refs: %d\n", c->symbol, c->data, c->refs->count);
    printf("===================================================================================\n");
}

void applysyncsymbols(void *_astptr, void *_symbol) {
    struct astnode *node = (struct astnode *)_astptr;
    char *s = (char*)_symbol;
    free(node->token->symbol);
    node->token->symbol = (char*)strdup(s);
}

void renamesymbols(struct queue *cst) {
    char *tmp;
    struct cstsymbol *_curptr, *_vicptr;
    for (int i = 0; i < cst->count; ++i) {
        _curptr = (struct cstsymbol*)gqueue(cst, i);
        for (int j = i + 1; j < cst->count; ++j) {
            _vicptr = (struct cstsymbol*)gqueue(cst, j);
            if (strcmp(_vicptr->symbol, _curptr->symbol) == 0) {
                /* rename the symbols of _vicptr */
                if (strlen(_vicptr->symbol) == 1) {
                    tmp = (char*) malloc (sizeof(char) * 3);
                    tmp[0] = _vicptr->symbol[0];
                    tmp[1] = '1';
                    tmp[2] = '\0';
                } else {
                    /* overflow when the symbol length is greater than 2 */
                    char *s = (char*)malloc(sizeof(char) * 2);
                    s[0] = _vicptr->symbol[1];
                    s[1] = '\0';
                    int n = atoi(s);
                    ++n;
                    sprintf(s, "%d", n);
                    tmp = (char*) malloc(sizeof(char) * 3);
                    tmp[0] = _vicptr->symbol[0];
                    tmp[1] = s[0];
                    tmp[2] = '\0';
                    free(s);
                }
                free(_vicptr->symbol);
                _vicptr->symbol = (char*)strdup(tmp);
                applyqueue(_vicptr->refs, _vicptr->symbol, applysyncsymbols);
            }
        }
    }
}

void addcstsymbol(struct queue *cst, char *symbol) {
    struct cstsymbol *tmp = searchcst(cst, symbol);
    if (tmp != NULL && tmp->scope == 0) {
        printf("Error in building compiler symbol table. Adding new symbol(%s) when there is a symbol(%s) with open scope existed.\n", symbol, tmp->symbol);
    } else {
        struct cstsymbol *new = (struct cstsymbol*) malloc (sizeof(struct cstsymbol));
        new->symbol = (char*)strdup(symbol);
        // if (tmp != NULL) {
        //     /* TODO: the current renaming strategy is naive */
        //     if (strlen(symbol) == 1) {
        //         new->symbol = (char*) malloc (sizeof(char) * 3);
        //         new->symbol[0] = symbol[0];
        //         new->symbol[1] = '1';
        //         new->symbol[2] = '\0';
        //     } else {
        //         /* overflow when the symbol length is greater than 2 */
        //         char *s = (char*)malloc(sizeof(char) * 2);
        //         s[0] = symbol[1];
        //         s[1] = '\0';
        //         int n = atoi(s);
        //         ++n;
        //         sprintf(s, "%d", n);
        //         new->symbol = (char*) malloc(sizeof(char) * 3);
        //         new->symbol[0] = symbol[0];
        //         new->symbol[1] = s[0];
        //         new->symbol[2] = '\0';
        //     }   
        // } else {
        //     new->symbol = (char*)strdup(symbol);
        // }
        new->data = NULL;
        new->refs = initqueue();
        new->scope = 0;            
        enqueue(cst, (void*)new);
    }            
}

int addcstref(struct queue *cst, char *symbol, void *pt) {
    struct cstsymbol *c = __searchcst(cst, symbol);
    if (c == NULL) {
        #if CSTDEBUG
        printf("Compile time symbol(%s) with scope opened(scope == 0) not found\n", symbol);
        #endif
        return 1;
    } else if (c->scope == 0) {
        enqueue(c->refs, pt);
        return 0;
    } else {
        #if CSTDEBUG
        printf("Trying to add identifier(%s) to the references but the scope is closed.\n", symbol);
        printf("Attempting to add new compile time symbol with number appended or incremented.\n");
        #endif
        return 2;
    }
}

void removecstref(struct queue *cst, char *symbol, void *pt) {
    struct cstsymbol *c = searchcst(cst, symbol);
    for (int i = 0; i < c->refs->count; ++i) {
        if (gqueue(c->refs, i) == pt) {
            rqueue(c->refs, i);
        }
    }
}

void closecstscope(struct queue *cst, char *symbol) {
    struct cstsymbol *c = searchcst(cst, symbol);
    if (c != NULL) {
        c->scope = 1;
    }
}

struct cstsymbol* updatecstsymbol(struct queue *cst, char *symbol, char *data) {
    struct cstsymbol *c = searchcst(cst, symbol);
    if (c->data) {
        free(c->data);
    }
    c->data = (char*)strdup(data);
    return c;
}

void syncsymbol(struct cstsymbol *c) {
    char *tmp = __combine_3_strings__("(", c->symbol, ")");
    int check = strsearch(c->data, tmp, NULL);
    free(tmp);
    for (int i = 0; i < c->refs->count; ++i) {
        struct astnode *node = (struct astnode*)gqueue(c->refs, i);
        if (node->type != Quantifier) {
            if (check > 0)  {
                node->type = Template;
            } else {
                node->type = Synthesised;
            }
        }            
        if (node->token->symbol)
            free(node->token->symbol);
        node->token->symbol = (char*)strdup(c->data);
    }
}

void setvalue2cstsymbol(struct cstsymbol *cstsym, char *data) {
    cstsym->data = (char*) strdup (data);
}

int __cstsymbolcomparator(void *_pt, void *_symbol) {
    struct cstsymbol* c = (struct cstsymbol*)_pt;
    char *symbol = (char*)_symbol;
    return strcmp(c->symbol, symbol);
}

int __cstsymbolandscopecomparator(void *_pt, void *_symbol) {
    struct cstsymbol* c = (struct cstsymbol*)_pt;
    char *symbol = (char*)_symbol;
    if (strcmp(c->symbol, symbol) == 0 && c->scope == 0) return 0;
    else return 1;
}

int __ptrcomparator(void *_aptr, void *_baptr) {
    if (_aptr == _baptr) {
        return 0;
    } else {
        return 1;
    }
}

int __cstrefcomparator(void *_csymptr, void *_astptr) {
    struct cstsymbol *c = (struct cstsymbol*)_csymptr;
    if (searchqueue(c->refs, _astptr, __ptrcomparator) == NULL) {
        return 1;
    } else {
        return 0;
    }
}

struct cstsymbol* searchcst(struct queue *cst, char *symbol) {
    return (struct cstsymbol*)searchqueue(cst, symbol, __cstsymbolcomparator);
}

struct cstsymbol* __searchcst(struct queue *cst, char *symbol) {
    return (struct cstsymbol*)searchqueue(cst, symbol, __cstsymbolandscopecomparator);
}

struct cstsymbol* searchsymbolbyref(struct queue *cst, void *_astptr) {
    return (struct cstsymbol*)searchqueue(cst, _astptr, __cstrefcomparator);
}

void deallocatecstsymbol(void *_cstsymbol) {
    struct cstsymbol *c = (struct cstsymbol *)_cstsymbol;
    free(c->symbol);
    free(c->data);
    deallocatequeue(c->refs, NULL);
}

