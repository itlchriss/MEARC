#include <stdlib.h>
#include <string.h>

#include "util.h"
#include "ast.h"
#include "si.h"
#include "cst.h"
#include "event.h"
#include "error.h"
#include "alias.h"

#include "synthesis.h"
#include "sshare.h"


extern struct queue *predicates, *operators, *silist, *events, *alias;
extern struct astnode *root;


struct queue *__obtain_si_with_1_cstptr_(struct cstsymbol *x, struct queue *siq) {    
    struct queue *result = initqueue();
    for (int k = 0; k < siq->count; ++k) {
        struct si *si = (struct si *)gqueue(siq, k);
        struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0);
        for (int i = 0; i < x->datalist->count; ++i) {
            char *s = (char *)strdup(si->interpretation);
            char *xdata = (char *)gqueue(x->datalist, i);
            char *tmp = strrep(s, arg1->symbol, xdata);
            free(s);    
            enqueue(result, (void *)tmp);
        }
    }
    return result;
}


int Nseries_code_synthesis(struct astnode *node) {
    /* Rel SI always needs typed entity. therefore, the first condition is not work to exclude them */
    if (check_need_assigned_entity(node) && !has_Rel_SI(node->si_q)) {
        /* get the aliased ptr of the child node cstptr */
        struct cstsymbol *_aliased_cstptr = searchalias(getastchild(node, 0)->cstptr);
        /* check if the aliased ptr is assigned */
        /* return FALSE to indicate the aliased ptr is not assigned yet */
        if (_aliased_cstptr->status != Assigned) return FALSE;
        /* if assigned, use the intermediate si of the aliased ptr to do the synthesis and replace the node->si_q */
        node->si_q = __obtain_si_with_1_cstptr_(_aliased_cstptr, node->si_q);        
        free(node->token->symbol);
        node->token->symbol = (char *)strdup((char *)gqueue(node->si_q, 0));
        __post_operation_si_subtree_synthesis__(node);
        return TRUE;
    } else {
        return __direct_syntax_synthesis__(node);
    }
}

int NN_code_synthesis(struct astnode *node) { return Nseries_code_synthesis(node); }
int NNS_code_synthesis(struct astnode *node) { return Nseries_code_synthesis(node); }
int NNP_code_synthesis(struct astnode *node) { return Nseries_code_synthesis(node); }


