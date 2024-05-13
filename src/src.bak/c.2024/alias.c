#include "alias.h"
#include "cst.h"
#include "util.h"
#include <stdlib.h>

extern struct queue *alias;


// void addalias(struct queue *_alias, struct cstsymbol *_a, struct cstsymbol *_b) {
void addalias(struct cstsymbol *_a, struct cstsymbol *_b) {
    struct alias *new = (struct alias*) malloc (sizeof(struct alias));
    new->a = _a;
    new->b = _b;
    enqueue(alias, (void*)new);
}

int __aliascomparator(void *_a, void *_target) {
    struct alias *_alias = (struct alias*)_a;
    struct cstsymbol *target = (struct cstsymbol*)_target;
    if (_alias->a == target || _alias->b == target) return TRUE;
    else return FALSE;
}

// struct cstsymbol *searchalias(struct queue *_alias, struct cstsymbol *target) {
struct cstsymbol *searchalias(struct cstsymbol *target) {
    struct alias *tmp = searchqueue(alias, target, __aliascomparator);
    if (tmp == NULL) return NULL;
    else {
        if (tmp->a == target) return tmp->b;
        else return tmp->a;
    }
}
