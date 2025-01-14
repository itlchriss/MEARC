#include <stdlib.h>
#include <string.h>

#include "util.h"
#include "ast.h"
#include "si.h"
#include "cst.h"
#include "event.h"
#include "error.h"

#include "synthesis.h"
#include "sshare.h"


extern struct astnode *root;
extern struct queue *predicates, *operators, *silist, *events, *alias;
extern struct astnode *root;


int TO_code_synthesis(struct astnode *node) {      
    struct astnode *eventnode = NULL, *varnode = NULL;
    if (((struct astnode *)getastchild(node, 0))->cstptr->symbol[0] == 'e') {
        eventnode = (struct astnode *)getastchild(node, 0);
        varnode = (struct astnode *)getastchild(node, 1);
    } else {
        eventnode = (struct astnode *)getastchild(node, 1);
        varnode = (struct astnode *)getastchild(node, 0);
    }

    struct entity *en = (struct entity *)gqueue(__searchevent(eventnode->cstptr)->entities, 0);    

    /*
        20250113 added support of abstract noun
        if the cstptr points to a variable contains abstract noun, aka  checks the data in the variable the same as the symbol of cstptr
        then we consider this preposition is a component of the synthesis of this abstract noun
        1. fill the SI of the varnode into the corresponding argument of the SI arg in the abstract noun
        2. prune the subtree node rooted at this preposition
     */
    if (en->cstptr->abstract_synthesis_required == TRUE) {
        /*
            check varnode dependency
            if there is another node that depends on the varnode, aka the SI synthesis of that node has not been completed yet
            we should wait for it to be completed
         */
         char *var = (char *)gqueue(varnode->cstptr->datalist, 0);
         char *target = (char *)dequeue(en->cstptr->datalist);
         char *tmp = strrep(target, __combine_3_strings__("(", node->token->symbol, ")"), var);
         free(target);
         target = (char *)strdup(tmp);
         free(tmp);
         enqueue(en->cstptr->datalist, (void *)target);
         varnode->cstptr->ref_count--;
         eventnode->cstptr->ref_count--;
         en->cstptr->ref_count--;
         if (__is_abtract_arg_done__(en->cstptr)) en->cstptr->abstract_synthesis_required = FALSE;
    }

    root = deleteastnodeandedge(node, root);
    return TRUE;
}
