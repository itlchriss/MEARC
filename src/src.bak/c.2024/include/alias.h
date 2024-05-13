#ifndef _ALIAS_H
#define _ALIAS_H

#include "util.h"
#include "cst.h"

struct alias {
    struct cstsymbol *a;
    struct cstsymbol *b;
};

// void addalias(struct queue *, struct cstsymbol *, struct cstsymbol *);
// struct cstsymbol *searchalias(struct queue *, struct cstsymbol *);

void addalias(struct cstsymbol *, struct cstsymbol *);
struct cstsymbol *searchalias(struct cstsymbol *);



#endif 
