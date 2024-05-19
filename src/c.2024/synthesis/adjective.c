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



void JJ_event_synthesis_post_operation(struct astnode *node, struct queue *siq) {
    if (siq->count == 1 && ((struct si *)gqueue(siq, 0))->type == SI_INT_TYPE_JAVA_TYPE) {
        root = deleteastnodeandedge(node, root);
    } else if (siq->count == 1 && ((struct si *)gqueue(siq, 0))->type == SI_INT_TYPE_MODIFIER) {
        struct entity *en1 = (struct entity *)gqueue(__searchevent(getastchild(node, 0)->cstptr)->entities, 0);
        if (node->si_q) {
            en1->cstptr->datalist = initqueue();
            for (int i = 0; i < node->si_q->count; ++i) {
                enqueue(en1->cstptr->datalist, strdup(gqueue(node->si_q, i)));
            }
        }
        root = deleteastnodeandedge(node, root);  
    } else {
        __post_operation_si_subtree_synthesis__(node);
    }
}



/*
    Match an SI with ONE argument data type, where there are two arguments need to be synthesised
    this is because only one argument has data type
    therefore, we match all SIs that accept two arguments, while these SIs should accept the same order of argument datatypes,
    for instance, denote the 1st child as x and the 2nd child as y,
    if x has a datatype, then y does not have a datatype
    such that, the node's predicate should be P(x, y) where P is the predicate symbol of the input _astnode
    the function returns true if the si accepts two arguments, the 1st argument has the same datatype as x's.
*/
int __match_si_with_1_datatype_and_1_asterisk__(void *_si, void *_astnode) {
    struct astnode *node = (struct astnode *)_astnode;
    struct si *si = (struct si *)_si;
    /* a predicate not accepting 2 arguments can be filtered out */
    if (si->args->count != 2) return FALSE;
    struct astnode *child1 = (struct astnode *)getastchild(node, 0), *child2 = (struct astnode *)getastchild(node, 1);
    struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0), *arg2 = (struct si_arg *)gqueue(si->args, 1);
    if (
        (has_datatype(child1->cstptr) && child1->cstptr->datatype == arg1->datatype) ||
        (has_datatype(child2->cstptr) && child2->cstptr->datatype == arg2->datatype)
    ) return TRUE;
    else return FALSE;
}

/*
    Match an SI with one argument data type
*/
int __match_si_with_1_arg_datatype__(void *_si, void *_astnode) {
    struct astnode *node = (struct astnode *)_astnode;
    struct si *si = (struct si *)_si;
    /* a predicate accepts more than 1 argument can be filtered out */
    if (si->args->count != 1) return FALSE;
    struct astnode *child = (struct astnode *)getastchild(node, 0);
    struct si_arg *arg = (struct si_arg *)gqueue(si->args, 0);
    if (arg->datatype == child->cstptr->datatype) return TRUE;
    else return FALSE;
}

/*
    this synthesis happens when the predicate requires a SINGLE typed argument (aka, not *),
    such that, it does not provide a relationship between two arguments.
    This predicate only enhances the semantics of the argument's entity variable.

    1. If the child has a data type, SI is searched from node->siq and check if any SIs accept an argument with the same data type
        i. If there are more than one matched, an SI conflict error is thrown.
        ii. If there is no match, an SI not found is thrown
    2. If the child has no data type, all SIs are applied to form the same number of SIs
*/
int __semantic_enhancement_synthesis__(struct astnode *node) {
    struct astnode *child = (struct astnode *)getastchild(node, 0);
    if (has_datatype(child->cstptr)) {
        /* the child has a data type */
        struct queue *siq = q_searchqueue(node->si_q, node, __match_si_with_1_arg_datatype__);
        if (siq->count == 0) sinotfound_error(node->token->symbol);
        else if (siq->count > 1) siconflict_error(node->token->symbol);
        // else subtree_si_synthesis(node, (struct si *)gqueue(siq, 0));           
        deallocatequeue(siq, NULL);
    } else {

    }
    return 0;
}


/*
    Match an SI with its interpretation. 
    This is only used for the method __get_SIs_with_unique_interpretation__
*/
int __match_si_with_interpretation__(void *_si, void *_interpretation) {
    struct si *si = (struct si *)_si;
    char *interpretation = (char *)_interpretation;
    if (strcmp(si->interpretation, interpretation) == 0) return TRUE;
    else return FALSE;
}

/* 
    returning a unique set of SIs based on the input _SIs
    Be aware that the input _SIs is deallocated before returning the new set
*/
struct queue * __get_SIs_with_unique_interpretation__(struct queue *_SIs) {
    struct queue *result = initqueue();
    struct si *_si = NULL;
    for (int i = 0; i < _SIs->count; ++i) {
        _si = (struct si *)gqueue(_SIs, i);
        if (searchqueue(result, _si->interpretation, __match_si_with_interpretation__) == NULL) {
            enqueue(result, (void *)_si);
        }
    }
    /* prevention of memory leak */
    deallocatequeue(_SIs, NULL);
    return result;
}


/*
To synthesise a predicate that is a relationship that accepts two arguments, the types of the arguments are very important.
The result is synthesised according to the types provided in the specification, or there can be no information from the specification.
Let x and y be the arguments from the predicate P, such that the relationship in HOL is denoted as P(x,y)
1. If both x and y have data type, SI is searched from the node->si_q that matches both arguments' data type. Synthesis is performed if there is a match. Only one synthesised SI is expected.
    i. If there are more than one matched, an SI conflict error is thrown.
    ii. If there is no match, an SI not found error is thrown.
2. If x has data type and y has no data type, Si is searched from the node->si_q that match x's data type. Synthesis is performed for all possible matches. There can be more than one synthesised SI.
3. If both x and y have no data types, all SI of P stored in node->si_q are used to generate all possible synthesis.
*/
int __relationship_syntax_synthesis__(struct astnode *node) {
    struct astnode *left = (struct astnode *) getastchild(node, 0);
    struct astnode *right = (struct astnode *) getastchild(node, 1);

    struct queue *siq = NULL;
    if (has_datatype(left->cstptr) && has_datatype(right->cstptr)) {

    } else if (has_datatype(left->cstptr) || has_datatype(right->cstptr)) {
        /* One of the argument has data type */
        siq = q_searchqueue(node->si_q, node, __match_si_with_1_datatype_and_1_asterisk__);
        if (siq->count == 0) sinotfound_error(node->token->symbol);
        else {
            /* 
                it is possible that SIs with the same interpretation are returned due to one of the argument has undefined datatype.
                therefore, a unique set should be found in this SIs by applying the criteria of distinct interpretation
            */
            siq = __get_SIs_with_unique_interpretation__(siq);
            /* perform a combinatorial results between x's cstptr datalist and y's cstptr datalist */
            __combinatorial_subtree_si_synthesis__(node, siq);
            deallocatequeue(siq, NULL);
        }
    } else {
        /* Both arguments have NO data type */
    }
    return 0;
}


int Jseries_code_synthesis(struct astnode *node) {
    if (countastchildren(node) == 1) {
        return __semantic_enhancement_synthesis__(node);
    } else {
        return __relationship_syntax_synthesis__(node);
    }    
}


int JJ_code_synthesis(struct astnode *node) { return Jseries_code_synthesis(node); }
int JJR_code_synthesis(struct astnode *node) { return 0; }
int JJS_code_synthesis(struct astnode *node) { return 0; }
