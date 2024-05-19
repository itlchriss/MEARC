#include "util.h"
#include "cst.h"

struct queue *__COMP__cmd_synthesis__(struct cstsymbol *expr_ptr, struct cstsymbol *mod_or_direct_ptr) {    
    struct queue *result = initqueue();
    char *mod_or_direct_semantics = (char *)gqueue(mod_or_direct_ptr->datalist, 0);
    for (int k = 0; k < expr_ptr->datalist->count; ++k) {
        char *int_si = (char *)gqueue(expr_ptr->datalist, k);
        /* TODO: there should be only one mod_or_direct semantics. however, users can claim that there are numerous of them 
                we currently only support one unique mod_or_direct semantics in this synthesis
        */
        char *tmp = strrep(int_si, "__COMP__", mod_or_direct_semantics);
        enqueue(result, (void *)tmp);
    }
    return result;
}
