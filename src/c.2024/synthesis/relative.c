#include <stdlib.h>
#include <string.h>

#include "cst.h"
#include "util.h"


int __Rel_synthesis__(
    struct cstsymbol *xptr, struct cstsymbol *relptr, struct queue *rel_siq) {    
    struct queue *results = initqueue();
    for (int i = 0; i < xptr->datalist->count; ++i) {
        for (int j = 0; j < rel_siq->count; ++j) {
            struct si *si = (struct si *)gqueue(rel_siq, j);
            struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0);
            char *s = (char *)strdup(si->interpretation), *symbol = __combine_3_strings__("(", arg1->symbol, ")");
            char *tmp = strrep(s, symbol, (char *)gqueue(xptr->datalist, i));
            free(symbol);
            free(s);
            s = tmp;                    
            enqueue(results, (void *)s);
        }
    }
    /*
        no matter what is in d's datalist, they have to be popped out because the data is synthesised.
        WHY d? the subject of a sentence with a term "x's d" representing a posessive relationship is d, and not x. this is based on observation in the HOL.
    */
    deallocatequeue(relptr->datalist, deallocatedata);
    relptr->datalist = initqueue();
    relptr->datatype->relative_var = strdup((char *)gqueue(xptr->datalist, 0));
    for (int i = 0; i < results->count; ++i) {
        enqueue(relptr->datalist, gqueue(results, i));
    }
    /* 
        saving the relative datatype for the case of ForAll
        such that we can use it in the code generation to decide the type 
        in the range
    */
    relptr->datatype->relative_datatype = (struct datatype *)malloc(sizeof(struct datatype));
    relptr->datatype->relative_datatype->p = xptr->datatype->p;
    relptr->datatype->relative_datatype->r = xptr->datatype->r;
    ////////
    deallocatequeue(results, NULL);    
    /* the synthesised datatype must be defined in the SI */
    /* TODO: we should perform a checking in the very beginning to acknowledge the users the possible errors in the SI template */
    struct si *relsi = (struct si *)gqueue(rel_siq, 0);
    struct datatype *dt = ((struct si *)gqueue(rel_siq, 0))->synthesised_datatype;
    relptr->datatype->p = dt->p;
    relptr->datatype->r = dt->r;
    if (relptr->datatype->p == ANY) {
        // A usual case that the rel SI is value or element, then the INT SI has type of the primitive type of the collection
        relptr->datatype->p = xptr->datatype->p;
    }
    relptr->datatype->types = initqueue();
    // relptr->interpretation_type = ((struct si *)gqueue(rel_siq, 0))->type;
    return 0;
}
