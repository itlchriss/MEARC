#include "alias.h"
#include "cst.h"
#include "util.h"
#include <stdlib.h>


void addalias(struct queue *_alias, struct cstsymbol *_a, struct cstsymbol *_b) {
    struct alias *new = (struct alias*) malloc (sizeof(struct alias));
    new->a = _a;
    new->b = _b;
    enqueue(_alias, (void*)new);
}

int __aliascomparator(void *_alias, void *_target) {
    struct alias *alias = (struct alias*)_alias;
    struct cstsymbol *target = (struct cstsymbol*)_target;
    if (alias->a == target || alias->b == target) return 0;
    else return 1;
}

struct cstsymbol *searchalias(struct queue *_alias, struct cstsymbol *target) {
    struct alias *tmp = searchqueue(_alias, target, __aliascomparator);
    if (tmp == NULL) return NULL;
    else {
        if (tmp->a == target) return tmp->b;
        else return tmp->a;
    }
}
