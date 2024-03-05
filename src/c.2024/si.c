/*
    This is an implementation of semantic interpretation analysis and synthesis specially for the neo-davidsonian event semantics
*/

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "util.h"
#include "si.h"
#include "ast.h"
#include "event.h"
#include "alias.h"
#include "error.h"

#ifndef TRUE
#define TRUE 1
#endif
#ifndef FALSE
#define FALSE 0
#endif



extern struct astnode *root;
extern struct queue *predicates, *operators, *silist, *events, *alias;
extern struct astnode *root;
struct queue *cst;    

int search_syntax(struct si*, enum ptbsyntax);
int __simatcher(void *, void *);
int __eventsimatcher(void *, void *);
int __sisymbol_duplicated(void *, void *);
int __preposition_argtype_simatcher(void *, void *);
int __match_interpretation_and_get_type(void *, void *);
int __is_Rel_dependent__(struct cstsymbol *);
void generate_param_si(char *s);

int selfSI[] = { 1, 0, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1, 1};
// char *javadatatype_name[] = { "PRIMITIVE", "ARRAY", "COLLECTION", "JML_EXPRESSION_RESULT", "JML_EXPRESSION_TEMPLATE", "OTHERS" };

char *gram_string[] = { "Subj", "Acc", "AccI", "AccE", "Dat", "Gen", "Abl", "Rel", "Voc" };
char *gram_name[] = { "SubjectOf", "AccusationOf", "IntentionalAccusationOf", "ExtentionalAccusationOf", "Dative", "Genitive", "Ablative", "Relative", "Vocative" };

char *gramtype2string(enum gramtype type) {
    return gram_string[type];
}

int __is_noun_predicate__(struct astnode *node) {
    return node->syntax == NN || node->syntax == NNP || node->syntax == NNS || node->syntax == NNPS;
}

int __is_event_variable__(struct astnode *node) {
    return node->token->symbol[0] == 'e';
}



/*
    Match an SI with argument data types, where both arguments from the event have data type
    there will not have another order of matching because the arguments need to be declared using grammar components,
    such as Subj, Acc
*/
int __match_event_si_with_2_arg_datatype__(void *_si, void *_astnode) {
    struct astnode *node = (struct astnode *)_astnode;
    struct event *event = (struct event *)__searchevent(getastchild(node, 0)->cstptr);
    struct si *si = (struct si *)_si;
    /* a predicate not accepting 2 arguments can be filtered out */
    if (si->args->count != 2) return FALSE;
    struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0), *arg2 = (struct si_arg *)gqueue(si->args, 1);
    struct entity *en1 = (struct entity *)gqueue(event->entities, 0), *en2 = (struct entity *)gqueue(event->entities, 1);
    if (arg1->datatype == en1->cstptr->datatype && 
        arg2->datatype == en2->cstptr->datatype &&
        strcmp(gramtype2string(en1->type), arg1->symbol) == 0 &&
        strcmp(gramtype2string(en2->type), arg2->symbol) == 0
        ) return TRUE;
    else
        return FALSE;
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
int __match_event_si_with_1_datatype_and_1_asterisk__(void *_si, void *_astnode) {
    struct astnode *node = (struct astnode *)_astnode;
    struct event *event = (struct event *)__searchevent(getastchild(node, 0)->cstptr);
    struct si *si = (struct si *)_si;
    /* a predicate not accepting 2 arguments can be filtered out */
    if (si->args->count != 2) return FALSE;
    struct entity *en1 = (struct entity *)gqueue(event->entities, 0), *en2 = (struct entity *)gqueue(event->entities, 1);
    struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0), *arg2 = (struct si_arg *)gqueue(si->args, 1);
    if (
        (has_datatype(en1->cstptr) && en1->cstptr->datatype == arg1->datatype) ||
        (has_datatype(en2->cstptr) && en2->cstptr->datatype == arg2->datatype)
    ) return TRUE;
    else return FALSE;
}

/*
    Match an SI with argument data types, where both arguments without using event
*/
int __match_si_with_2_arg_datatype__(void *_si, void *_astnode) {
    struct astnode *node = (struct astnode *)_astnode;    
    struct si *si = (struct si *)_si;
    /* a predicate not accepting 2 arguments can be filtered out */
    if (si->args->count != 2) return FALSE;
    struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0), *arg2 = (struct si_arg *)gqueue(si->args, 1);
    struct astnode *child1 = (struct astnode *)getastchild(node, 0), *child2 = (struct astnode *)getastchild(node, 1);
    if ((arg1->datatype == child1->cstptr->datatype && arg2->datatype == child2->cstptr->datatype) ||
        (arg1->datatype == child2->cstptr->datatype && arg2->datatype == child1->cstptr->datatype)) return TRUE;
    else
        return FALSE;
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
    Match an SI with one argument data type
*/
int __match_si_with_input_arg_datatype__(void *_si, void *_astnode) {
    struct astnode *node = (struct astnode *)_astnode;
    struct si *si = (struct si *)_si;
    /* a predicate accepts more than 1 argument can be filtered out */
    if (si->args->count != 1) return FALSE;
    struct si_arg *arg = (struct si_arg *)gqueue(si->args, 0);
    if (arg->datatype == node->cstptr->datatype) return TRUE;
    else return FALSE;
}

/*
    Match an SI with one event data type
*/
int __match_event_si_with_1_arg_datatype__(void *_si, void *_astnode) {
    struct astnode *node = (struct astnode *)_astnode;
    struct event *event = (struct event *)__searchevent(getastchild(node, 0)->cstptr);
    struct si *si = (struct si *)_si;
    /* a predicate not accepting only 1 argument can be filtered out */
    if (si->args->count != 1) return FALSE;
    struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0);
    struct entity *en1 = (struct entity *)gqueue(event->entities, 0);
    if (arg1->datatype == en1->cstptr->datatype &&
        strcmp(gramtype2string(en1->type), arg1->symbol) == 0
        ) return TRUE;
    else
        return FALSE;
}


/*
    Match an event SI specifically for prepositions (IN)
    because preposition predicates always accept 2 arguments, one is an event and another is a valid entity
    we have to check the datatype of the entity inside the event, as well as the datatype of the valid entity in the prepositions' arguments
*/
int __match_event_si_for_prepositions_1_datatype_1_asterisk__(void *_si, void *_astnode) {
    struct astnode *node = (struct astnode *)_astnode;
    struct event *event = (struct event *)__searchevent(getastchild(node, 0)->cstptr);
    struct cstsymbol *var_cstptr = ((struct astnode *)getastchild(node, 1))->cstptr;
    struct cstsymbol *en_cstptr = ((struct entity *)gqueue(event->entities, 0))->cstptr;
    struct si *si = (struct si *)_si;
    /* a predicate not accepting only 1 argument can be filtered out */
    if (si->args->count != 2) return FALSE;
    struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0), *arg2 = (struct si_arg *)gqueue(si->args, 1);
    if (
        (has_datatype(en_cstptr) && arg1->datatype == en_cstptr->datatype) ||
        (has_datatype(var_cstptr) && arg2->datatype == var_cstptr->datatype)
        ) return TRUE;
    else
        return FALSE;
}

/*
    Match an SI with the symbol only. this is used in finding REL SI
*/
int __match_si_with_symbol_only__(void *_si, void *_symbol) {
    struct si *si = (struct si *)_si;
    char *symbol = (char *)_symbol;
    if (strcmp(si->symbol, symbol) == 0) return TRUE;
    else return FALSE;
}


void __replace_si_at_parent__(struct astnode *node, enum astnodetype type, char *si) {        
    free(node->token->symbol);
    node->token->symbol = (char*) strdup(si);
    node->type = type;
}


/*
    obtaining SI from feeding x and y to si's argument
    the order of feeding is x to the 1st argument and y to the 2nd argument
    there can be cases that a variable is being accepted to two different direct semantic predicates
    therefore, a data list is returned. each data is computed by feeding the cst's data to the si
*/
struct queue *__obtain_si_(struct astnode *x, struct astnode *y, struct si *si) {    
    struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0), *arg2 = (struct si_arg *)gqueue(si->args, 1);
    struct queue *result = initqueue();
    for (int i = 0; i < x->cstptr->datalist->count; ++i) {
        for (int j = 0; j < y->cstptr->datalist->count; ++j) {
            char *s = (char *)strdup(si->interpretation);
            char *xdata = (char *)gqueue(x->cstptr->datalist, i), *ydata = (char *)gqueue(y->cstptr->datalist, j);
            char *tmp = strrep(s, arg1->symbol, xdata);
            free(s);
            s = tmp;
            tmp = strrep(s, arg2->symbol, ydata);            
            free(s);
            enqueue(result, (void *)tmp);
        }
    }
    return result;
}

struct queue *__obtain_si_with_cstptr_(struct cstsymbol *x, struct cstsymbol *y, struct queue *siq) {    
    struct queue *result = initqueue();
    for (int k = 0; k < siq->count; ++k) {
        struct si *si = (struct si *)gqueue(siq, k);
        struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0), *arg2 = (struct si_arg *)gqueue(si->args, 1);
        for (int i = 0; i < x->datalist->count; ++i) {
            for (int j = 0; j < y->datalist->count; ++j) {
                char *s = (char *)strdup(si->interpretation);
                char *xdata = (char *)gqueue(x->datalist, i), *ydata = (char *)gqueue(y->datalist, j);
                char *tmp = strrep(s, arg1->symbol, xdata);
                free(s);
                s = tmp;
                tmp = strrep(s, arg2->symbol, ydata);            
                free(s);
                enqueue(result, (void *)tmp);
            }
        }
    }
    return result;
}

/*
    obtaining SI from feeding x to si's argument   
    there can be cases that a variable is being accepted to two different direct semantic predicates
    therefore, a data list is returned. each data is computed by feeding the cst's data to the si
*/
struct queue *__enhance_si__(struct astnode *x, struct si *si) {
    struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0);
    struct queue *result = initqueue();
    for (int i = 0; i < x->cstptr->datalist->count; ++i) {
        char *s = (char *)strdup(si->interpretation);
        char *xdata = (char *)gqueue(x->cstptr->datalist, i);
        char *tmp = strrep(s, arg1->symbol, xdata);
        free(s);
        enqueue(result, (void *)tmp);
    }
    return result;
}


/* enhancing the functionality with a checking in the parent, if the parent is a event predicate */
/* obtaining semantic interpretation from the template of parent node and synthesising it with its event entities */
struct queue *__obtain_si_from_subtree_with_precise_datatypes__(struct astnode *parent, struct si* si) {
    struct queue *result = initqueue();    
    struct event *event = (struct event *)__searchevent(getastchild(parent, 0)->cstptr);
    // if (countastchildren(parent) == 1) {
    if (event->entities->count == 1) {
        /* the case of only one child. this is applied to those that should be an adjective or adverb */
        // struct astnode *child1 = (struct astnode *)getastchild(parent, 0);
        /* this case only consider that the child has a data type, and it is matched with the argument's datatype */
        /* child1 matches arg1 */
        
        // result = __enhance_si__(child1, si);
    } else {
        /* the case of having two children. this is a bit complicated because we have to do combination of data types */
        /* in this function we only consider both arguments have specified data types in the natural language requirements */
        // struct astnode *child1 = (struct astnode *)getastchild(parent, 0), *child2 = (struct astnode *)getastchild(parent, 1);
        // struct si_arg *arg1 = (struct si_arg *) gqueue(si->args, 0), *arg2 = (struct si_arg *) gqueue(si->args, 1);
        // if (child1->cstptr->datatype == arg1->datatype && child2->cstptr->datatype == arg2->datatype) {
        //     /* child1 matches arg1 and child2 matches arg2 */
        //     result = __obtain_si_(child1, child2, si);
        // } else {
        //     /* child2 matches arg1 and child1 matches arg2 */
        //     result = __obtain_si_(child2, child1, si);
        // }
    }    
    /* NOTE: this needs to be fixed after finishing all the cases */
    return result;
}


/* at least one of the children DO NOT HAVE a datatype. such that we loop and acquire all possible subtree sythesised outcome by obtaining semantic interpretation from the template of parent node and synthesising it with its children */
struct queue *__obtain_si_from_subtree_without_precise_datatypes__(struct astnode *parent, struct queue* siq) {
    struct queue *result = initqueue();
    int child_count = countastchildren(parent);
    struct queue *tmp = NULL;
    struct si *_si = NULL;
    if (child_count == 1) {
        /* the case of only one child. this is applied to those that should be an adjective or adverb */
        struct astnode *child1 = (struct astnode *)getastchild(parent, 0);
        /* this case only consider that the child has a data type, and it is matched with the argument's datatype */
        /* child1 matches arg1 */
        for (int i = 0; i < siq->count; ++i) {
            _si = (struct si *)gqueue(siq, i);
            tmp = __enhance_si__(child1, _si);
            while (!isempty(tmp)) {
                enqueue(result, dequeue(tmp));
            }         
            deallocatequeue(tmp, NULL);
        }
    } else {
        /* the case of having two children. this is a bit complicated because we have to do combination of data types */
        struct astnode *child1 = (struct astnode *)getastchild(parent, 0), *child2 = (struct astnode *)getastchild(parent, 1);
        for (int i = 0; i < siq->count; ++i) {
            _si = (struct si *)gqueue(siq, i);
            tmp = __obtain_si_(child1, child2, _si);
            while (!isempty(tmp)) {
                enqueue(result, dequeue(tmp));
            }         
            deallocatequeue(tmp, NULL);
        }
    }    
    return result;
}

int __synthesis_predicate_at_root__(struct si *si) {
    // same as if and only if, we have to do operator resolution, simplification before synthesising the root predicate
    if (predicates->count > 0) {
        // we have to ensure all other predicates are synthesised
        return 1;
    }
    free(root->token->symbol);
    root->token->symbol = (char*)strdup(si->interpretation);
    if (strcmp(root->token->symbol, "==>") == 0) {
        root->conntype = Op_Imply;
    } else if (strcmp(root->token->symbol, "&&") == 0) {
        root->conntype = Op_And;
    } else if (strcmp(root->token->symbol, "||") == 0) {
        root->conntype = Op_Or;
    } else if (strcmp(root->token->symbol, "<==>") == 0) {
        root->conntype = Op_Equivalent;
    } else {
        fprintf(stderr, "Unknown semantics %s is not allowed to be the root node semantic\n", root->token->symbol);
        exit(-16);
    }
    root->type = Connective;
    return 0;
}



void __post_operation_si_subtree_synthesis__(struct astnode *node) {
    node->type = Synthesised; 
    deleteastchildren(node);
}

void subtree_si_synthesis(struct astnode *node, struct si *si) {
    node->si_q = __obtain_si_from_subtree_with_precise_datatypes__(node, si);
    __post_operation_si_subtree_synthesis__(node);
}

void __combinatorial_subtree_si_synthesis__(struct astnode *node, struct queue *siq) {
    node->si_q = __obtain_si_from_subtree_without_precise_datatypes__(node, siq);
    __post_operation_si_subtree_synthesis__(node);
}



/*
    operations of
    1. putting the semantics synthesised SI into the targetnode's cstptr datalist
    2. removing the cst references
    3. deleting the subtree rooted at subtree_root
*/
void __subtree_with_direct_syntax_operation__(struct astnode *subtree_root, struct astnode *targetnode, char *interpretation) {
    struct cstsymbol *cstptr = targetnode->cstptr;
    if (interpretation != NULL && strlen(interpretation) > 0) {
        enqueue(cstptr->datalist, (char *)strdup(interpretation));
    }
    cstptr->status = Assigned;
    cstptr->ref_count--;
    root = deleteastnodeandedge(subtree_root, root);
}

int __direct_syntax_synthesis__(struct astnode *node) {
    struct astnode *child = (struct astnode *) getastchild(node, 0);
    struct si *targetsi = (struct si *)gqueue(node->si_q, 0);
    // if (targetsi->synthesised_datatype != None && child->cstptr->datatype == None) child->cstptr->datatype = targetsi->synthesised_datatype;
    if (targetsi->synthesised_datatype != NULL) 
        child->cstptr->datatype = targetsi->synthesised_datatype;
    __subtree_with_direct_syntax_operation__(node, child, targetsi->interpretation);
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
        // /* Both arguments have data type */
        // siq = q_searchqueue(node->si_q, node, __match_si_with_2_arg_datatype__);
        // if (siq->count == 0) sinotfound_error(node->token->symbol);
        // else if (siq->count > 1) siconflict_error(node->token->symbol);
        // else subtree_si_synthesis(node, (struct si *)gqueue(siq, 0));        
        // deallocatequeue(siq, NULL);
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
        else subtree_si_synthesis(node, (struct si *)gqueue(siq, 0));           
        deallocatequeue(siq, NULL);
    } else {

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

int Vseries_code_synthesis(struct astnode *node) {
    return 0;
}


int Nseries_code_synthesis(struct astnode *node) {
    return __direct_syntax_synthesis__(node);
}



int CC_code_synthesis(struct astnode *node) { return 0; }
/*
    The predicate is a cardinal number. Therefore, the synthesised semantics must be an integer/long. We assume it as integer first.
*/
int CD_code_synthesis(struct astnode *node) { 
    ((struct astnode *)getastchild(node, 0))->cstptr->datatype->p = Integer;
    return __direct_syntax_synthesis__(node);
}
int DT_code_synthesis(struct astnode *node) { return 0; }
int EX_code_synthesis(struct astnode *node) { return 0; }
int FW_code_synthesis(struct astnode *node) { return 0; }
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


    if (has_datatype(en->cstptr) && has_datatype(varnode->cstptr)) {
        /* TO BE DONE HERE */
    } else if (has_datatype(en->cstptr) || has_datatype(varnode->cstptr)) {
        node->si_q = q_searchqueue(node->si_q, node, __match_event_si_for_prepositions_1_datatype_1_asterisk__);
        if (node->si_q->count == 0) sinotfound_error(node->token->symbol);
        else {
            node->si_q = __obtain_si_with_cstptr_(en->cstptr, varnode->cstptr, node->si_q);
        }
    } else {
        if (__is_Rel_dependent__(en->cstptr)) {
            char *rel_symbol = (char *)gqueue(en->cstptr->datalist, 0);
            struct queue *relq = q_searchqueue(silist, rel_symbol, __match_si_with_symbol_only__);
            if (relq->count == 0) sinotfound_error(rel_symbol);
            deallocatequeue(en->cstptr->datalist, deallocatedata);
            en->cstptr->datalist = initqueue();
            while (!isempty(relq)) {
                struct si *_rel_si = (struct si *)dequeue(relq);
                enqueue(en->cstptr->datalist, (void *)strdup(_rel_si->interpretation));
            }
        }
        node->si_q = __obtain_si_with_cstptr_(en->cstptr, varnode->cstptr, node->si_q);
    }
    deallocatequeue(en->cstptr->datalist, deallocatedata);
    en->cstptr->datalist = initqueue();
    while (!isempty(node->si_q)) enqueue(en->cstptr->datalist, dequeue(node->si_q));
    root = deleteastnodeandedge(node, root);
    return 0;
}
int JJ_code_synthesis(struct astnode *node) { return Jseries_code_synthesis(node); }
int JJR_code_synthesis(struct astnode *node) { return 0; }
int JJS_code_synthesis(struct astnode *node) { return 0; }
int LS_code_synthesis(struct astnode *node) { return 0; }
int MD_code_synthesis(struct astnode *node) { return 0; }
int NN_code_synthesis(struct astnode *node) { return Nseries_code_synthesis(node); }
int NNS_code_synthesis(struct astnode *node) { return Nseries_code_synthesis(node); }
int NNP_code_synthesis(struct astnode *node) { return Nseries_code_synthesis(node); }
int NNPS_code_synthesis(struct astnode *node) { return 0; }
int PDT_code_synthesis(struct astnode *node) { return 0; }
int POS_code_synthesis(struct astnode *node) { return 0; }
int PRP_code_synthesis(struct astnode *node) { return 0; }
int PRP_POS_code_synthesis(struct astnode *node) { return 0; }
int RB_code_synthesis(struct astnode *node) {

    // subtree_si_synthesis(node);
    return 0;
}
int RBR_code_synthesis(struct astnode *node) { return 0; }
int RBS_code_synthesis(struct astnode *node) { return 0; }
int RP_code_synthesis(struct astnode *node) { return 0; }
int SYM_code_synthesis(struct astnode *node) { return 0; }
int TO_code_synthesis(struct astnode *node) { return 0; }
int UH_code_synthesis(struct astnode *node) { return 0; }
int VB_code_synthesis(struct astnode *node) { 
    return Vseries_code_synthesis(node); 
}
int VBD_code_synthesis(struct astnode *node) {  
    return Vseries_code_synthesis(node);
}
int VBG_code_synthesis(struct astnode *node) { 
    return Vseries_code_synthesis(node);
}
int VBN_code_synthesis(struct astnode *node) { 
    return Vseries_code_synthesis(node);
}
int VBP_code_synthesis(struct astnode *node) { return 0; }
int VBZ_code_synthesis(struct astnode *node) {
    return Vseries_code_synthesis(node);
}
int WDT_code_synthesis(struct astnode *node) { return 0; }
int WP_code_synthesis(struct astnode *node) { return 0; }
int WP_POS_code_synthesis(struct astnode *node) { return 0; }
int WRB_code_synthesis(struct astnode *node) { return 0; }

/*
    combinatorially forming all possible SI synthesis for 2 event entities
    the reason why it is a combinatorial problem is that,
    1. there are 2 event entities, and each of them may have multiple SIs
        i. let x and y be the number of SIs for the 2 event entities
    2. there can be multiple SIs, let the number of SIs be n
    3. therefore, at most we have  x * y * n SIs
*/
struct queue *__2_event_entities_combinatorial_subtree_si_synthesis__(struct event *event, struct queue *siq) {
    struct queue *result = initqueue();
    struct entity *en1 = (struct entity *)gqueue(event->entities, 0), *en2 = (struct entity *)gqueue(event->entities, 1);
    char *t1 = __combine_3_strings__("(", gramtype2string(en1->type), ")"), *t2 = __combine_3_strings__("(", gramtype2string(en2->type), ")");
    for (int i = 0; i < siq->count; ++i) {
        struct si *si = (struct si *)gqueue(siq, i);        
        for (int j = 0; j < en1->cstptr->datalist->count; ++j) {
            char *d1 = (char *)gqueue(en1->cstptr->datalist, j);
            for (int k = 0; k < en2->cstptr->datalist->count; ++k) {
                char *d2 = (char *)gqueue(en2->cstptr->datalist, k), *s = (char *)strdup(si->interpretation);                
                char *tmp = strrep(s, t1, d1);
                free(s);
                s = tmp;
                tmp = strrep(s, t2, d2);
                free(s);
                enqueue(result, (void *)tmp);
            }            
        }
    }
    free(t1);
    free(t2);
    return result;
}

/*
    combinatorially forming all possible SI synthesis for 1 event entity
    the reason why it is a combinatorial problem is that,
    1. there are 1 event entity, and it may have multiple SIs
        i. let x be the number of SIs for the 2 event entities
    2. there can be multiple SIs, let the number of SIs be n
    3. therefore, at most we have  x * n SIs
*/
struct queue *__1_event_entities_combinatorial_subtree_si_synthesis__(struct event *event, struct queue *siq) {
    struct queue *result = initqueue();
    struct entity *en1 = (struct entity *)gqueue(event->entities, 0);
    char *t1 = __combine_3_strings__("(", gramtype2string(en1->type), ")");
    for (int i = 0; i < siq->count; ++i) {
        struct si *si = (struct si *)gqueue(siq, i);        
        for (int j = 0; j < en1->cstptr->datalist->count; ++j) {
            char *d1 = (char *)gqueue(en1->cstptr->datalist, j), *s = (char *)strdup(si->interpretation);                
            char *tmp = strrep(s, t1, d1);
            free(s);
            enqueue(result, (void *)tmp);       
        }
    }
    free(t1);
    return result;
}


int event_synthesis(struct astnode *node) {
    struct event *e = __searchevent(getastchild(node, 0)->cstptr);
    struct queue *siq = NULL;
    
    if (e->entities->count == 1) {
        /* cases that predicates only have Subj */
        struct entity *en1 = (struct entity *)gqueue(e->entities, 0);
        if (has_datatype(en1->cstptr)) {
            siq = q_searchqueue(node->si_q, node, __match_event_si_with_1_arg_datatype__);
            if (siq->count == 0) sinotfound_error(node->token->symbol);
            else if (siq->count > 1) siconflict_error(node->token->symbol);            
        } else {

        }
        node->si_q = __1_event_entities_combinatorial_subtree_si_synthesis__(e, siq);
        en1->cstptr->ref_count--;       
    } else {
        /* cases that predicates have two components */        
        struct entity *en1 = (struct entity *)gqueue(e->entities, 0), *en2 = (struct entity *)gqueue(e->entities, 1);
        if (has_datatype(en1->cstptr) && has_datatype(en2->cstptr)) {
            /* both components have data types */
            siq = q_searchqueue(node->si_q, node, __match_event_si_with_2_arg_datatype__);
            if (siq->count == 0) sinotfound_error(node->token->symbol);
            else if (siq->count > 1) siconflict_error(node->token->symbol);
        } else if (has_datatype(en1->cstptr) || has_datatype(en2->cstptr)) {
            siq = q_searchqueue(node->si_q, node, __match_event_si_with_1_datatype_and_1_asterisk__);
            if (siq->count == 0) sinotfound_error(node->token->symbol);            
        } else {

        }        
        /* combinatorially forming all possible SI synthesis from 2 entities */
        node->si_q = __2_event_entities_combinatorial_subtree_si_synthesis__(e, siq);        
        en1->cstptr->ref_count--;
        en2->cstptr->ref_count--;
    }    
    e->cstptr->ref_count--;
    /* since the SIs are only for this synthesis, it should have no effect on the overall SI list. we should deallocate ASAP */
    deallocatequeue(siq, NULL);
    /* the resulting operations */
    __post_operation_si_subtree_synthesis__(node);    
    return 0;
}

/*
    a helper function checking the input cst symbol's SI is starting with '__Rel__'.
    If so, c is said to be a dependent value and the result is true, otherwise, the result is false.
    Besides, the one with '__Rel__' SI should have only one SI and this SI starts with '__Rel__', otherwise, a semantic declaration error is thrown
*/
int __is_Rel_dependent__(struct cstsymbol *c) {
    if (c->datalist->count > 1) return FALSE;
    char *data = (char *)gqueue(c->datalist, 0);
    int occur[strlen(data)/7 + 1];
    if (strsearch(data, "__REL__", occur) != 0) return TRUE;        
    else return FALSE;
}



/*
    synthesising Rel predicates
    the Rel predicates must accept 2 SI-Assigned arguments
    One of the arguments must have Assigned SI starting with '__Rel__', let it be d
    d is treated as a dependent value, such that its final SI is decided by another argument's (call this x) datatype
    If x does not have a datatype (aka, there are many SIs), then the number of synthesised SIs is equal to the number of SIs that x has multiplied by the number of SIs that d has
*/
int Gram_Rel_synthesis(struct astnode *node) {
    struct astnode *child1 = getastchild(node, 0), *child2 = getastchild(node, 1), *x, *d;
    if (__is_Rel_dependent__(child1->cstptr)) {
        d = child1;
        x = child2;
    } else {
        d = child2;
        x = child1;
    }

    /* 
        Although it looks like the normal relationship synthesis, but it is in fact different 
        the SI is not searched from the parent node, instead, it is searched from d's si_q
    */
    char *rel_symbol = (char *)gqueue(d->cstptr->datalist, 0);
    d->si_q = q_searchqueue(silist, (void *)rel_symbol, __match_si_with_symbol_only__);
    if (d->si_q->count == 0) sinotfound_error(rel_symbol);
    // enum explicit_datatype synthesised_datatype = ((struct si *)gqueue(d->si_q, 0))->synthesised_datatype;
    struct datatype *synthesised_datatype = ((struct si *)gqueue(d->si_q, 0))->synthesised_datatype;
    if (has_datatype(x->cstptr)) {
        // struct queue *siq = q_searchqueue(d->si_q, x, __match_si_with_1_arg_datatype__);
        struct queue *siq = q_searchqueue(d->si_q, x, __match_si_with_input_arg_datatype__);
        if (siq->count == 0) sinotfound_error(rel_symbol);
        else if (siq->count > 1) siconflict_error(rel_symbol);
        else {
            struct si *si = (struct si*)gqueue(siq, 0);
            struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0);
            char *xdata = (char *)gqueue(x->cstptr->datalist, 0), *s = (char *)strdup(si->interpretation);
            char *tmp = strrep(s, arg1->symbol, xdata);
            free(s);
            s = tmp;
            /* 
                because d has a data type, therefore, there is only 1 SI left 
                we have to pop all the existing SIs in d's data
                WHY d? the subject of a sentence with a term "x's d" representing a posessive relationship is d, and not x. this is based on observation in the HOL.
            */
            deallocatequeue(d->cstptr->datalist, deallocatedata);
            d->cstptr->datalist = initqueue();
            __subtree_with_direct_syntax_operation__(node, d, s);
        }
        deallocatequeue(siq, NULL);        
    } else {
        struct queue *results = initqueue();
        for (int i = 0; i < x->cstptr->datalist->count; ++i) {
            for (int j = 0; j < d->si_q->count; ++j) {
                struct si *si = (struct si *)gqueue(d->si_q, j);
                struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0);
                char *xdata = (char *)gqueue(x->cstptr->datalist, i), *s = (char *)strdup(si->interpretation);
                char *tmp = strrep(s, arg1->symbol, xdata);
                free(s);
                s = tmp;                    
                enqueue(results, (void *)s);
            }
        }
        /*
            no matter what is in d's datalist, they have to be popped out because the data is synthesised.
            WHY d? the subject of a sentence with a term "x's d" representing a posessive relationship is d, and not x. this is based on observation in the HOL.
        */
        deallocatequeue(d->cstptr->datalist, deallocatedata);
        d->cstptr->datalist = initqueue();
        for (int i = 0; i < results->count; ++i) {
            enqueue(d->cstptr->datalist, gqueue(results, i));
        }
        deallocatequeue(results, NULL);
        __subtree_with_direct_syntax_operation__(node, d, NULL);
    }
    /* the synthesised datatype must be defined in the SI */
    /* TODO: we should perform a checking in the very beginning to acknowledge the users the possible errors in the SI template */
    d->cstptr->datatype = synthesised_datatype;
    return 0;
}

/* Reserve for future usage */
int Gram_Prog_synthesis(struct astnode *node) {
    return 0;
}

int (*code_syntheses[])(struct astnode *) = {CC_code_synthesis, CD_code_synthesis, DT_code_synthesis, EX_code_synthesis, FW_code_synthesis, IN_code_synthesis, JJ_code_synthesis, JJR_code_synthesis, JJS_code_synthesis, LS_code_synthesis, MD_code_synthesis, NN_code_synthesis, NNS_code_synthesis, NNP_code_synthesis, NNPS_code_synthesis, PDT_code_synthesis, POS_code_synthesis, PRP_code_synthesis, PRP_POS_code_synthesis, RB_code_synthesis, RBR_code_synthesis, RBS_code_synthesis, RP_code_synthesis, SYM_code_synthesis, TO_code_synthesis, UH_code_synthesis, VB_code_synthesis, VBD_code_synthesis, VBG_code_synthesis, VBN_code_synthesis, VBP_code_synthesis, VBZ_code_synthesis, WDT_code_synthesis, WP_code_synthesis, WP_POS_code_synthesis, WRB_code_synthesis, Gram_Prog_synthesis, Gram_Rel_synthesis};


struct si* __add_runtime_si(char *term, enum ptbsyntax syntax, char *interpretation) {
    struct si *new = (struct si*) malloc (sizeof(struct si));
    new->symbol = (char*)strdup(term);
    new->args = initqueue();
    struct si_arg *arg = (struct si_arg *)malloc(sizeof(struct si_arg));
    arg->symbol = (char*) strdup("(*)");
    arg->datatype = (struct datatype *)malloc(sizeof(struct datatype));
    arg->datatype->p = UNDEFINED;
    arg->datatype->r = UNDEFINED;
    arg->datatype->types = NULL;
    new->synthesised_datatype = (struct datatype *)malloc(sizeof(struct datatype));
    new->synthesised_datatype->p = Integer;
    new->synthesised_datatype->r = UNDEFINED;
    new->synthesised_datatype->types = NULL;
    enqueue(new->args, (void*)arg);
    new->interpretation = (char*)strdup(interpretation);
    new->syntax = initqueue();
    enqueue(new->syntax, (void *)syntax);
    enqueue(silist, (void*)new);
    return new;    
}

struct si* __generate_runtime_si__(struct astnode *node) {
    struct si *dup = searchqueue(silist, node->token->symbol, __sisymbol_duplicated);
    if (dup == NULL) {
        return __add_runtime_si(node->token->symbol, node->syntax, node->token->symbol);
    } else {
        return dup;
    }
}

/*
    this is added when LLM helps to identify the parameter, and we trust the specification that there is such a parameter in the context
*/
void generate_param_si(char *s) {
    __add_runtime_si(s, NN, s);
}

/* 
    20230522 in Portugal
    Words used as meaning like nouns but not tagged as nouns
    For instance, A is true, where true is tagged as adjective(JJ)
    This case, this true has direct semantics as a literal.
    Another point is that, the si must be a symbol from the contextual information
*/
int __is_nonnounsyntax_using_as_noun__(struct astnode *node, struct si *si) {
    // if (countastchildren(node) == 1 && si->source == CONTEXTUTAL)
    if (countastchildren(node) == 1)
        return 0;
    else 
        return 1;
}



/* because the event predicate has only one child. and such event requires multiple synthesised predicates to finish the synthesis. therefore, it is a special case to the current practice of si matching, which is designed for usual higher-order logic. */
int __is_event_predicate__(struct astnode *node) {
    if (countastchildren(node) > 1) {
        return FALSE;
    } else {
        struct astnode *child = getastchild(node, 0);
        if (child->type != Variable || child->token->symbol[0] != 'e') {
            return FALSE;
        } else {
            return TRUE;
        }
    }
}

int __search_visited_variables__(void *_child_cstptr, void *_input_cstptr) {
    struct cstsymbol *child = (struct cstsymbol *)_child_cstptr;
    struct cstsymbol *input = (struct cstsymbol *)_input_cstptr;
    if (child == input) return TRUE;
    else return FALSE;
}


int satisfy(struct astnode *node, struct queue *visited_variables) {
    if (countastchildren(node) == 1) {
        struct event *e = __searchevent(getastchild(node, 0)->cstptr);
        if (e->entities->count == 1) {
            struct entity *en = (struct entity *)gqueue(e->entities, 0);
            /*
                used in function satisfy only 
                if an entity's cstptr's ref_count == 1, this means that the cstptr is only referenced by this entity only
                then, we replace this entity's pointer by its aliased cstptr
            */
            if (en->cstptr->ref_count == 1) {
                struct cstsymbol *_aliased_cstptr = searchalias(en->cstptr);
                if (_aliased_cstptr == NULL) internal_error("Please check with si.c -> satisfy function. There is an entity that does not have an alias, and its cstptr is only referenced by itself.");
                _aliased_cstptr->datatype = en->cstptr->datatype;
                en->cstptr = _aliased_cstptr;
                en->cstptr->ref_count++;
            }
            if (!searchqueue(visited_variables, en->cstptr, __search_visited_variables__)) return 0;
            else return 1;
        } else {
            for (int i = 0; i < e->entities->count; ++i) {
                struct entity *en = (struct entity *)gqueue(e->entities, i);
                if (en->cstptr->ref_count == 1) {
                    struct cstsymbol *_aliased_cstptr = searchalias(en->cstptr);
                    if (_aliased_cstptr == NULL) internal_error("Please check with si.c -> satisfy function. There is an entity that does not have an alias, and its cstptr is only referenced by itself.");
                    en->cstptr = _aliased_cstptr;
                }
                if (!searchqueue(visited_variables, en->cstptr, __search_visited_variables__)) return 0;
            }       
            /* make it to the last one to be resolved */
            return 2;
        } 
    } else {
        int hasevent = FALSE;
        for (int i = 0; i < countastchildren(node); ++i) {
            if (getastchild(node, i)->cstptr->symbol[0] == 'e') { hasevent = TRUE; continue; }
            if (!searchqueue(visited_variables, getastchild(node, i)->cstptr, __search_visited_variables__)) return 0;
        }
        if (hasevent) 
            return 1;
        else
            return 2;
    }
}

void check_validity(struct astnode *node) {
    if (node->si_q->count == 0) {
        sinotfound_error(node->token->symbol);
    }
}


void sianalysis() {
    struct astnode *node = NULL;
    struct queue *visited_variables = initqueue(), *target = initqueue(), *last = initqueue();
    int check = -1;
    // showqueue(cst, showcstsymbol);
    while (!isempty(predicates)) {
        node = (struct astnode *)dequeue(predicates);
        node->si_q = initqueue();
        switch(node->syntax) {
            case CD:
                enqueue(node->si_q, (void*)__generate_runtime_si__(node));
                check_validity(node);
                enqueue(visited_variables, (void *)getastchild(node, 0)->cstptr);
                enqueue(target, (void *)node);
                break;
            case NN:
            case NNS:
            case NNP:
            case NNPS:
                node->si_q = q_searchqueue(silist, node, __simatcher);
                check_validity(node);
                enqueue(visited_variables, (void *)getastchild(node, 0)->cstptr);
                enqueue(target, (void *)node);
                break;
            case Gram_Rel:
                if (satisfy(node, visited_variables)) enqueue(target, (void *)node);
                else {
                    struct astnode *tmp = dequeue(predicates);
                    push(predicates, node);
                    push(predicates, tmp);
                }
                break;
            default:
                check = satisfy(node, visited_variables);
                if (!check) {
                    struct astnode *tmp = dequeue(predicates);
                    push(predicates, node);
                    push(predicates, tmp);
                } else {
                    node->si_q = q_searchqueue(silist, node, __simatcher);
                    check_validity(node);
                    if (check == 1) 
                        enqueue(target, (void *)node);
                    else
                        enqueue(last, (void *)node);
                }
                break;
        }
    }
    while (!isempty(last)) enqueue(target, dequeue(last));
    #if SIDEBUG
    printf("Analysed predicate sequence:\n");
    for (int i = 0; i < target->count; ++i) {
        node = (struct astnode *)gqueue(target, i);
        printf("predicate %s\n", node->token->symbol);
    }
    #endif
    deallocatequeue(last, NULL);
    deallocatequeue(predicates, NULL);
    deallocatequeue(visited_variables, NULL);
    predicates = target;
}

/* 
    semantic interpretation synthesis 
    this is a process to identify as many si as possible presenting in the meaning representation
    - parameter descriptions
        predicates  : a queue holding pointers of predicates present in an abstract syntax tree parsed from meaning representation
        silist      : a queue holding semantic interpretations parsed from standard semantic interpretation database
        cst         : a queue holding the compile time symbols, aka the identitiers in the meaning representation
*/
void sisynthesis() {
    struct astnode *node;
    #if SIDEBUG
    printf("si synthesis: after sorting, there are %d predicates in the queue.\n", predicates->count);
    for (int i = 0; i < predicates->count; ++i) {
        node = (struct astnode*)gqueue(predicates, i);
        printf("%d. %s(%s) %d\n", i + 1, node->token->symbol, ptbsyntax2string(node->syntax), node->si_q->count);
    }
    #endif

    // int count = predicates->count;
    // int check = 0;
    while (!isempty(predicates)) {    
        node = (struct astnode*)dequeue(predicates);
        #if SIDEBUG
        printf("si synthesis: processing predicate %s(%s) with %d SIs available.\n", node->token->symbol, ptbsyntax2string(node->syntax), node->si_q->count);
        #endif

        /* 
            Rigorously checking the child status
            1. If there is only one child, then
                i. if the child is an event variable, all the event components must be Assigned
                ii. if the child is not an event variable, the node must be a noun or a cardinal number predicate
                iii. else, semantic error is thrown
            2. If there are two or more children, then all children must be Assigned
        */
        int child_count = countastchildren(node);
        /* NOTE: remember to update the event variable making its status to Assigned when all the event components are Assigned */
        if (child_count == 1) { 
            struct astnode *child = (struct astnode *) getastchild(node, 0);
            if (__is_event_variable__(child)) {
                /* the child is an event variable, and it is marked with Assigned which indicates all event components (related variables) are marked Assigned */
                if (child->cstptr->status != Assigned) {
                    semantic_error("Synthesis is stopped because a event predicate(%s) has not completed component synthesis.", node->token->symbol);
                } else {
                    event_synthesis(node);
                }

                /* Experimental: past participles have no individual meaning unless it is applied to an entity which is synthesised with a noun semantics. therefore, the semantics of past participles applying an entity should become part of the semantics of such entity. concluding this idea, after past pariticiple synthesis, the node should be deleted because its semantics is already applied to the entity */
                if (node->syntax == VBN) {
                    root = deleteastnodeandedge(node, root);
                }
            } else if (child->cstptr->status != Assigned && 
                !__is_noun_predicate__(node) && 
                node->syntax != CD) {
                /* there can be a case that the preposition comes before the adjectives. we have to think of retry */
                semantic_error("Synthesis is stopped because a predicate(%s) has non-noun and non-CD syntax and its argument has not been assigned.", node->token->symbol); 
            } else {
                /* do the synthesis according to the syntax of predicate */
                (*code_syntheses[node->syntax])(node);       
            }
        } else {
            /* checking all children, if one of them is not assigned with semantics, the synthesis cannot be done */            
            for (int i = 0; i < child_count; ++i) {
                struct astnode *tmp = (struct astnode *) getastchild(node, i);
                if (tmp->cstptr->status != Assigned) {
                    semantic_error("Synthesis is stopped because a predicate(%s) has children that are not Assigned.", node->token->symbol);
                }
            }
            (*code_syntheses[node->syntax])(node);       
        }
        /* ================================================================================================ */
        #if ASTDEBUG
        showast(root, 0);
        showqueue(cst, showcstsymbol);
        fflush(stdout);
        #endif

        /*
            if a variable is a component of a event, such as Subj(e) = x, then x is a subject of event e,
            then after assigning value to x, we have to check if all components of e are assigned.
            if so, then we update e as Assigned
        */
        update_events();
    }
    #if SIDEBUG
    printf("si synthesis finished\n");
    #endif
}

// according to the semantics of the higher order logic, the equal operator represents an alias relationship between two variables.
// therefore, we build an alias table to remark these relationships.
// once the relationship is built, the subtree with operator as tree root can be pruned.
void opresolution() {
    struct astnode *node, *left, *right;    
    while (operators->count > 0) {
        node = (struct astnode*)dequeue(operators);
        left = getastchild(node, 0);
        right = getastchild(node, 1);
        // addalias(alias, left->cstptr, right->cstptr);   
        addalias(left->cstptr, right->cstptr);   
        if (has_datatype(left->cstptr) && !has_datatype(right->cstptr)) {
            right->cstptr->datatype = left->cstptr->datatype;
        } else if  (!has_datatype(left->cstptr) && has_datatype(right->cstptr)) {
            left->cstptr->datatype = right->cstptr->datatype;
        }
        /*
            because we are going to remove these two nodes
            decreasing the count before deletion or the pointer is not found
        */
        left->cstptr->ref_count--;
        right->cstptr->ref_count--;
        root = deleteastnodeandedge(node, root);
        #ifdef SIDEBUG
        showast(root, 0);
        #endif
    }
}

int __sisymbol_duplicated(void *_si, void *_symbol) {
    char *symbol = (char*)_symbol;
    struct si* si = (struct si*)_si;
    if (strcmp(symbol, si->symbol) == 0) return TRUE;
    else return FALSE;
}



int __simatcher(void *_si, void *_astnode) {
    struct si* si = (struct si*)_si;
    struct astnode *node = (struct astnode*)_astnode;
    // if (strcmp(node->token->symbol, si->symbol) == 0 &&
    //             search_syntax(si, node->syntax) == 0 && 
    //             si->arg_count == countastchildren(node)) 
    /* 20230522 in portugal. 
        We want to tackle a problem of using a word tagged as not-a-noun at the end of a sentence, or an declarative sentence, where this word should be a symbol.
        Adding a condition that if there is only one child, it is okay to skip the search_syntax. Because this is to say that this symbol must be a symbol in the contextual information. 
        This can be wrong if there is an SI in the SI library conflicting with the contextual information. 
        TODO: we can put a flag in the SI to distinguish between contextual SI and std SI, such that this will not be a problem.
    */
    int child_count = countastchildren(node);
    if (strcmp(node->token->symbol, si->symbol) == 0 &&
                search_syntax(si, node->syntax) == TRUE && 
                (si->args->count == child_count || 
                    (
                        getastchild(node, 0)->cstptr->symbol[0] == 'e' && 
                        si->args->count == __searchevent(getastchild(node, 0)->cstptr)->entities->count
                    )
                )
        ) 
        return TRUE;
    else
        return FALSE;
}

/* 
    this is an SI matcher that is specifically for the event predicates.
    event predicates have only one child that is the event variable, which is not useful in the SI matching with SIs should have specified the number of arguments required for synthesis.
    however, we have recorded the entities that have grammar relationships with this event variable.
    such that, we can check the number of arguments required for the SI against the number of entities of the event variables.
*/
int __eventsimatcher(void *_si, void *_astnode) {
    struct si* si = (struct si*)_si;
    struct astnode *node = (struct astnode*)_astnode, *child = getastchild(node, 0);
    struct event *event = __searchevent(child->cstptr);    
    if (strcmp(si->symbol, node->token->symbol) == 0 &&
        search_syntax(si, node->syntax) == TRUE &&
        si->args->count == event->entities->count
        ) 
    {
        return TRUE;
    } else {
        return FALSE;
    }
}

int __preposition_argtype_simatcher(void *_si, void *_datanode) {
    // struct si* si = (struct si*)_si;
    // struct astnode *node = (struct astnode*)_datanode;
    // if (si->arg_count == 1) {
    //     struct cstsymbol *c = searchsymbolbyref(node);    
    //     if (si->arg_types[0] == c->type) return 0;
    // }
    return 1;
}

int __match_interpretation_and_get_type(void *_si, void *_s) {
    struct si* si = (struct si*)_si;
    char *s = (char*)_s;
    if (strcmp(si->symbol, s) == 0) return TRUE;
    else return FALSE;
}

int search_syntax(struct si* si, enum ptbsyntax ptb) {
    for (int i = 0; i < si->syntax->count; ++i) {
        if ((enum ptbsyntax)gqueue(si->syntax, i) == ptb) {
            return TRUE;
        }
    }
    return FALSE;
}

void showsi(void *_si) {
    struct si *si = (struct si*)_si;
    printf("==========================Semantic interpretations: =========================\n");
    printf("Symbol          Syntactic Category       Arguments    Interpretation\n");
    printf("Symbol: %s   Syntactic Category: ", si->symbol);        
    for (int j = 0; j < si->syntax->count; ++j) {
        printf("%s ", ptbsyntax2string((enum ptbsyntax)gqueue(si->syntax, j)));
    }
    printf("  Arguments: ");
    for (int j = 0; j < si->args->count; ++j) {
        struct si_arg *arg = (struct si_arg*)gqueue(si->args, j);
        printf("%s(p:%d, r:%d)", arg->symbol, arg->datatype->p, arg->datatype->r);
    }
    printf("     Synthesised datatype: p:%d, r:%d   ", si->synthesised_datatype->p, si->synthesised_datatype->r);
    printf("   %s\n", si->interpretation);
    printf("============================================================================\n");
}

void deallocatesi_arg(void *tmp) {
    struct si_arg *arg = (struct si_arg*)tmp;
    if (arg->symbol)
        free(arg->symbol);    
}

void deallocatesi(void *tmp) {    
    struct si *si = (struct si*)tmp;
    if (si->syntax)
        deallocatequeue(si->syntax, NULL);
    if (si->args)
        deallocatequeue(si->args, deallocatesi_arg);
    if (si->interpretation)
        free(si->interpretation);        
    free(si->symbol);
    free(si);
}



