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

/* for the eventnode has intermediate SI, we need to match with the intermediate SI type */
int __match_event_int_si_for_prepositions__(void *_si, void *_astnode) {
    struct astnode *node = (struct astnode *)_astnode;
    struct event *event = (struct event *)__searchevent(getastchild(node, 0)->cstptr);
    struct cstsymbol *var_cstptr = ((struct astnode *)getastchild(node, 1))->cstptr;
    struct cstsymbol *en_cstptr = event->cstptr;
    struct si *si = (struct si *)_si;
    /* a predicate not accepting only 1 argument can be filtered out */
    if (si->args->count != 2) return FALSE;
    struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0), *arg2 = (struct si_arg *)gqueue(si->args, 1);
    if (
        (en_cstptr->interpretation_type == arg1->datatype->i && var_cstptr->interpretation_type == arg2->datatype->i) ||
        (en_cstptr->interpretation_type == arg2->datatype->i && var_cstptr->interpretation_type == arg1->datatype->i)
        ) return TRUE;
    else
        return FALSE;
}

/*
    Match an event SI specifically for prepositions (IN)
    because preposition predicates always accept 2 arguments, one is an event and another is a valid entity
    we have to check the datatype of the entity inside the event, as well as the datatype of the valid entity in the prepositions' arguments
*/
int __match_event_si_for_prepositions__(void *_si, void *_astnode) {
    struct astnode *node = (struct astnode *)_astnode;
    struct event *event = (struct event *)__searchevent(getastchild(node, 0)->cstptr);
    struct cstsymbol *var_cstptr = ((struct astnode *)getastchild(node, 1))->cstptr;
    struct cstsymbol *en_cstptr = ((struct entity *)gqueue(event->entities, 0))->cstptr;
    struct si *si = (struct si *)_si;
    /* a predicate not accepting only 1 argument can be filtered out */
    if (si->args->count != 2) return FALSE;
    struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0), *arg2 = (struct si_arg *)gqueue(si->args, 1);
    if (
        __compare_datatype__(arg1->datatype, en_cstptr->datatype) && __compare_datatype__(arg2->datatype, var_cstptr->datatype)
        ) return TRUE;
    else
        return FALSE;
}

int IN_code_synthesis(struct astnode *node) {      
    struct astnode *eventnode = NULL, *varnode = NULL;
    if (((struct astnode *)getastchild(node, 0))->cstptr->symbol[0] == 'e') {
        eventnode = (struct astnode *)getastchild(node, 0);
        varnode = (struct astnode *)getastchild(node, 1);
    } else {
        eventnode = (struct astnode *)getastchild(node, 1);
        varnode = (struct astnode *)getastchild(node, 0);
    }

    struct entity *en = (struct entity *)gqueue(__searchevent(eventnode->cstptr)->entities, 0);    
    

    if (__is_Rel_dependent__(en->cstptr)) {
        char *rel_symbol = (char *)gqueue(en->cstptr->datalist, 0);
        struct queue *relq = q_searchqueue(silist, rel_symbol, __match_si_with_symbol_only__);
        if (relq->count == 0) sinotfound_error(rel_symbol);
        /*
            do a filtering of the rel_siq by the datatype of xptr
        */
        struct queue *siq = q_searchqueue(relq, varnode->cstptr->datatype, __match_si_with_input_arg_datatype__);
        if (siq->count == 0) sinotfound_error(rel_symbol);
        deallocatequeue(relq, NULL);
        deallocatequeue(en->cstptr->datalist, deallocatedata);
        en->cstptr->datalist = initqueue();
        __Rel_synthesis__(varnode->cstptr, en->cstptr, siq);
        for (int i = 0; i < varnode->cstptr->datalist->count; ++i) 
            enqueue(en->cstptr->datatype->types, (char*)strdup(gqueue(varnode->cstptr->datalist, i)));
    } else {
        if (eventnode->cstptr->datalist->count > 0) {
            node->si_q = q_searchqueue(node->si_q, node, __match_event_int_si_for_prepositions__);
            if (node->si_q->count == 0) sinotfound_error(node->token->symbol);        
            /* perform synthesis using the intermediate SI stored in eventnode */
            node->si_q = __COMP__cmd_synthesis__(eventnode->cstptr, varnode->cstptr);
            /* write back the data to the event node */
            deallocatequeue(eventnode->cstptr->datalist, deallocatedata);
            eventnode->cstptr->datalist = initqueue();
            for (int i = 0; i < node->si_q->count; ++i) enqueue(eventnode->cstptr->datalist, (char *)strdup((char *)gqueue(node->si_q, i)));
            /* 
                write back the data to the predicate (astnode) that the event is pointed to. because preposition here doing COMP is in fact  compensating or complementing the semantics of this astnode 
                eventnode->cstptr is the cst ptr of the event, eventnode is the ast node of the event that this preposition's child
            */
            deallocatequeue(eventnode->cstptr->astptr->si_q, NULL);
            eventnode->cstptr->astptr->si_q = initqueue();
            /* TODO: there can be a problem if there are multiple SI */
            enqueue(eventnode->cstptr->astptr->si_q, (void *)strdup((char *)gqueue(node->si_q, 0)));
        } else {
            node->si_q = q_searchqueue(node->si_q, node, __match_event_si_for_prepositions__);
            if (node->si_q->count == 0) sinotfound_error(node->token->symbol);        
            node->si_q = __obtain_si_with_cstptr_(en->cstptr, varnode->cstptr, node->si_q);
        }        
        deallocatequeue(en->cstptr->datalist, deallocatedata);
        en->cstptr->datalist = initqueue();
        for (int i = 0; i < node->si_q->count; ++i) enqueue(en->cstptr->datalist, (char *)strdup((char *)gqueue(node->si_q, i)));
    }

    root = deleteastnodeandedge(node, root);
    return 0;
}