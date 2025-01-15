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
         if (__is_abtract_arg_done__(en->cstptr)) {
            en->cstptr->abstract_synthesis_required = FALSE; 
            struct si *si = (struct si *)gqueue(en->cstptr->si_q, 0);
            en->cstptr->datatype->p = si->synthesised_datatype->p;
            en->cstptr->datatype->r = si->synthesised_datatype->r;           
         }
    }
    else if (__is_Rel_dependent__(en->cstptr)) {
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
            /*
                check if any other node depends on the entity
                if the reference count of the entity is greater than 1, then we should wait for the count to be 1
                which means, the current synthesis is the last one to be resolved
            */
            // showqueue(events, showevent);
            // if (en->cstptr->ref_count > 1 || varnode->cstptr->ref_count > 1) return FALSE;
            // if (varnode->cstptr->abstract_synthesis_required) return FALSE;
            node->si_q = q_searchqueue(node->si_q, node, __match_event_si_for_prepositions__);            
            if (node->si_q->count == 0) sinotfound_error(node->token->symbol);        
            if (has_Abstract_SI(node->si_q) && varnode->cstptr->abstract_synthesis_required) return FALSE;
            
            if (varnode->cstptr->si_q != NULL && __has_abstract_ex_arg__(varnode->cstptr)) {
                struct si *si = (struct si *)gqueue(varnode->cstptr->si_q, 0);
                char *var = (char *)dequeue(varnode->cstptr->datalist);
                char *target = (char *)gqueue(en->cstptr->datalist, 0);
                char *tmp = strrep(var, __combine_3_strings__("(", si->exarg->symbol, ")"), target);
                free(var);
                var = (char *)strdup(tmp);
                free(tmp);
                enqueue(varnode->cstptr->datalist, (void *)var);
                varnode->cstptr->ref_count--;
                eventnode->cstptr->ref_count--;
                en->cstptr->ref_count--;
                node->si_q = initqueue();
                enqueue(node->si_q, (void *)strdup(var));
                /* EXPERIMENTAL */
                deleteastchildren(node);
                node->type = Synthesised;                
                return TRUE;
            } else {
                node->si_q = __obtain_si_with_cstptr_(en->cstptr, varnode->cstptr, node->si_q);
            }
        }        
        deallocatequeue(en->cstptr->datalist, deallocatedata);
        en->cstptr->datalist = initqueue();
        for (int i = 0; i < node->si_q->count; ++i) enqueue(en->cstptr->datalist, (char *)strdup((char *)gqueue(node->si_q, i)));
    }

    root = deleteastnodeandedge(node, root);
    return TRUE;
}
