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
    if (has_Abstract_SI(node->si_q)) {
        /* we use the abstract noun SI only */
        /* pop the current SI then find and enqueue the abstract noun SI */
        struct si *tmp = (struct si *)gqueue(node->si_q, 0);
        struct queue *tmpq = q_searchqueue(silist, tmp->interpretation, __match_si_with_symbol_only__);
        if (tmpq->count == 0) sinotfound_error(tmp->interpretation);
        dequeue(node->si_q);
        enqueue(node->si_q, (void *)gqueue(tmpq, 0)); 
        deallocatequeue(tmpq, NULL);
        ((struct astnode *)getastchild(node, 0))->cstptr->si_q = initqueue();
        for (int i = 0; i < node->si_q->count; ++i) {
             enqueue(((struct astnode *)getastchild(node, 0))->cstptr->si_q, (void *)gqueue(node->si_q, i));
        }
        return __direct_syntax_synthesis__(node);
    }
    /* Rel SI always needs typed entity. therefore, the first condition is not work to exclude them */
    else if (check_need_assigned_entity(node) && !has_Rel_SI(node->si_q)) {
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


