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
void generate_param_si(char *s);

int selfSI[] = { 1, 0, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1, 1};
char *javadatatype_name[] = { "PRIMITIVE", "ARRAY", "COLLECTION", "JML_EXPRESSION_RESULT", "JML_EXPRESSION_TEMPLATE", "OTHERS" };

int __is_noun_predicate__(struct astnode *node) {
    return node->syntax == NN || node->syntax == NNP || node->syntax == NNS || node->syntax == NNPS;
}

int __is_event_variable__(struct astnode *node) {
    return node->token->symbol[0] == 'e';
}



/*
    Match an SI with argument data types, where both argument have data type
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
    node: the node must be a predicate node
*/
void __remove_all_children_cst__(struct astnode *node) {
    struct astnode *child;
    for (int i = 0; i < countastchildren(node); ++i) {
        child = getastchild(node, i);
        /* we do not have to deal with the events here. because the structure is independent to the ast tree as they do not have pointers pointing to each other. we will free the events structure at the end of the program. */
        removecstref(child->token->symbol, child);
    }
}

/*
    substituting semantic interpretation from a synthesised node into the template from another node
    _snode: an ast node with type Synthesised, the symbol contains the semantic interpretation
    _tnode: an ast node with type Template, the symbol contains the template
*/
char* __subsititute_synthesised_into_template__(struct astnode *_snode, struct astnode *_tnode) {
    struct cstsymbol *c = searchsymbolbyref(_tnode);
    char *tmp = __combine_3_strings__("(", c->symbol, ")");
    char *new = strrep(_tnode->token->symbol, tmp, _snode->token->symbol);
    free(tmp);
    return new;
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

/* obtaining semantic interpretation from the template of parent node and synthesising it with its children */
struct queue *__obtain_si_from_subtree_with_precise_datatypes__(struct astnode *parent, struct si* si) {
    int child_count = countastchildren(parent);
    if (child_count == 1) {
        /* the case of only one child. this is applied to those that should be an adjective or adverb */
        struct astnode *child1 = (struct astnode *)getastchild(parent, 0);
        /* this case only consider that the child has a data type, and it is matched with the argument's datatype */
        /* child1 matches arg1 */
        return __enhance_si__(child1, si);
    } else {
        /* the case of having two children. this is a bit complicated because we have to do combination of data types */
        /* in this function we only consider both arguments have specified data types in the natural language requirements */
        struct astnode *child1 = (struct astnode *)getastchild(parent, 0), *child2 = (struct astnode *)getastchild(parent, 1);
        struct si_arg *arg1 = (struct si_arg *) gqueue(si->args, 0), *arg2 = (struct si_arg *) gqueue(si->args, 1);
        if (child1->cstptr->datatype == arg1->datatype && child2->cstptr->datatype == arg2->datatype) {
            /* child1 matches arg1 and child2 matches arg2 */
            return __obtain_si_(child1, child2, si);
        } else {
            /* child2 matches arg1 and child1 matches arg2 */
            return __obtain_si_(child2, child1, si);
        }
    }    
    /* NOTE: this needs to be fixed after finishing all the cases */
    return NULL;
}


/* at least one of the children DO NOT HAVE a datatype. such that we loop and acquire all possible subtree sythesised outcome by obtaining semantic interpretation from the template of parent node and synthesising it with its children */
struct queue *__obtain_si_from_subtree_without_precise_datatypes__(struct astnode *parent, struct queue* siq) {
    int child_count = countastchildren(parent);
    struct queue *result = initqueue(), *tmp = NULL;
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

// void __update_cstsymbol_data__(struct cstsymbol *c, char *data, int datatype) {
//     if (c->data) {
//         free(c->data);
//     }
//     c->data = (char*)strdup(data);
//     c->type = datatype;
// }

void si_alias(struct astnode *node, struct si *si) {
    // struct astnode *child = getastchild(node, 0); 
    // struct cstsymbol *c = searchsymbolbyref(child);
    // __remove_all_children_cst__(node);        
    // // TODO: not thoroughly checked this line. removed this line if problem raises    
    // if (si == NULL) {
    //     if (c->si_q) {
    //         deallocatequeue(c->si_q, NULL);
    //     }
    //     c->si_q = copyqueue(node->si_q);
    //     /* inidicate this symbol is not resolved due to multiple SIs */
    //     c->type = -9;
    // } else {
    //     c->si_ptr = si;  
    //     __update_cstsymbol_data__(c, si->interpretation, si->jtype);
    //     if (c->si_q) {
    //         deallocatequeue(c->si_q, NULL);
    //     }   
    // }
    // syncsymbol(c);    
    // root = deleteastnodeandedge(node, root);
}

void __post_operation_si_subtree_synthesis__(struct astnode *node) {
    __remove_all_children_cst__(node);        
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

void si_substitution(struct astnode *dest, struct astnode *sinode, struct astnode *datanode, struct si *si) {
    // struct cstsymbol *c = searchsymbolbyref(sinode);
    // char *s = strrep(si->interpretation, si->args[0], datanode->token->symbol);
    // __update_cstsymbol_data__(c, s, 0);
    // /* checking this sentence */
    // __remove_all_children_cst__(dest);
    // syncsymbol(c);
    // root = deleteastnodeandedge(dest, root);
}

/*
    Using the SI in the datanode to substitute in the argument of sinode
*/
void sibling_si_synthesis(struct astnode *node, struct astnode *sinode, struct astnode *datanode) {
    // char *s = __subsititute_synthesised_into_template__(datanode, sinode);
    // struct cstsymbol *c = updatecstsymbol(s, sinode);     
    // __remove_all_children_cst__(node);
    // syncsymbol(c);        
    // root = deleteastnodeandedge(node, root);
}

/*
    operations of
    1. putting the semantics synthesised SI into the targetnode's cstptr datalist
    2. removing the cst references
    3. deleting the subtree rooted at subtree_root
*/
void __subtree_with_direct_syntax_operation__(struct astnode *subtree_root, struct astnode *targetnode, char *interpretation) {
    struct cstsymbol *cstptr = targetnode->cstptr;
    if (interpretation != NULL) {
        enqueue(cstptr->datalist, (char *)strdup(interpretation));
    }
    cstptr->status = Assigned;
    for (int i = 0; i < countastchildren(subtree_root); ++i) {
        struct astnode *child = (struct astnode *)getastchild(subtree_root, i);
        removecstref(child->cstptr->symbol, child);
    }
    // removecstref(cstptr->symbol, targetnode);
    root = deleteastnodeandedge(subtree_root, root);
}

int __direct_syntax_synthesis__(struct astnode *node) {
    struct astnode *child = (struct astnode *) getastchild(node, 0);
    // struct cstsymbol *cstptr = child->cstptr;
    struct si *targetsi = (struct si *)gqueue(node->si_q, 0);
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
        /* Both arguments have data type */
        siq = q_searchqueue(node->si_q, node, __match_si_with_2_arg_datatype__);
        if (siq->count == 0) sinotfound_error(node->token->symbol);
        else if (siq->count > 1) siconflict_error(node->token->symbol);
        else subtree_si_synthesis(node, (struct si *)gqueue(siq, 0));        
        deallocatequeue(siq, NULL);
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
    // struct astnode *child;
    // /* Verbs usually accepts 2 arguments (subject, object). Thus, verbs cannot be resolved before both arguments are resolved. */
    // for (int i = 0; i < countastchildren(node); ++i) {
    //     child = getastchild(node, i);
    //     if (child->type != Synthesised) {
    //         return 1;
    //     }
    // }
    // subtree_si_synthesis(node, si);
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
    ((struct astnode *)getastchild(node, 0))->cstptr->datatype = JavaInteger;
    return __direct_syntax_synthesis__(node);
}
int DT_code_synthesis(struct astnode *node) { return 0; }
int EX_code_synthesis(struct astnode *node) { return 0; }
int FW_code_synthesis(struct astnode *node) { return 0; }
int IN_code_synthesis(struct astnode *node) {      

    // if (getastchild(node, 0)->type == MultipleSIs && getastchild(node, 1)->type == Synthesised) {
    //     struct astnode *sinode = getastchild(node, 0), *datanode = getastchild(node, 1);
    //     struct queue *si_q = searchqueue(sinode->si_q, datanode, __preposition_argtype_simatcher);
    //     if (si == NULL) {
    //         fprintf(stderr, "No SI matched for predicate(%s) with syntax (IN)\n", node->token->symbol);
    //         exit(-100);
    //     }
    //     si_substitution(node, sinode, datanode, si);
    // } else {
    //     struct astnode *x = getastchild(node, 0), *y = getastchild(node, 1);
    //     if (strcmp(si->interpretation, "\\sub(x)2(y)") == 0) {
    //         sibling_si_synthesis(node, y, x);
    //     } else if (strcmp(si->interpretation, "\\sub(y)2(x)") == 0) {
    //         sibling_si_synthesis(node, x, y);
    //     } else {
    //         subtree_si_synthesis(node, si);
    //     }
    // }
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

int event_synthesis(struct astnode *node) {
//     struct event *e = searchevent(getastchild(node, 0)->token->symbol);
//     char *s = NULL;
//     struct entity *en = NULL;
//     if (si->interpretation[0] == '_') {
//         struct cstsymbol *subj_ptr, *acc_ptr;
//         en = (struct entity*)gqueue((void*)e->entities, 0);
//         if (en->type == SubjectOf) {
//             subj_ptr = en->ptr;
//             acc_ptr = (struct cstsymbol*)((struct entity*)gqueue((void*)e->entities, 1))->ptr;
//         } else {
//             acc_ptr = en->ptr;
//             subj_ptr = (struct cstsymbol*)((struct entity*)gqueue((void*)e->entities, 1))->ptr;
//         }
//         /* keywords. the synthesis process is up to what the keywords have specified. */
//         if (strcmp(si->interpretation, "_sub(Subj)2(Acc)") == 0) {
//             /* 
//                 The synthesised SI of the entity with Subj is substituted to that of the entity with Acc.
//                 The synthesised SI of the Acc entity must have the type of JML_expression_template.
//                 After the substitution, both the Subj and Acc entity cst pointer's data field has the synthesised SI.
//             */
//             struct si *_si = (struct si *)acc_ptr->si_ptr;
//             s = strrep(_si->interpretation, _si->args[0], subj_ptr->data); 
//             acc_ptr->type = si->jtype;                 
//         } else if (strcmp(si->interpretation, "_event_sub(Subj)2(Acc)") == 0) {
//             /*
//                 This case is substituting the SI based on the event arguments. 
//                 The previous case is for substitution based on the accusation's arguments
//             */
//             // notice here we use (Subj) directly, because we follow the command directly
//             s = strrep(acc_ptr->data, "(Subj)", subj_ptr->data); 
//             acc_ptr->type = si->jtype;     
//         }
//     } else {
//         s = (char *)strdup(si->interpretation);
//         for (int i = 0; i < e->entities->count; ++i) {
//             /* 
//                 get the compile symbol table pointer, and the get the data.
//                 the argument of SI is named using the grammar relationship string, such as Subj
//                 substitution is done replacing the SI synthesised in the data field of CST symbol to the grammar relationship string
//             */
//             en = (struct entity*)gqueue((void*)e->entities, i);
//             /* 
//                     because the entity variables should have been synthesised before coming to this point. 
//                     we should not be able to search the symbol by the reference as the references are removed before synthesising the SI.
//                     therefore, we should search the symbol by the symbol name.
//                     there is no risk by using the symbol name because all symbol names are made unique.
//             */
//             struct cstsymbol *c = searchcst(en->var);
//             if (c->data == NULL) {
//                 /*
//                     there can be no predicates accepting the symbol.
//                     because some sentences can have an aliased variable as such argument, this is common in some clauses, as well as using corpora verb like 'be'
//                     in this case, we have to search the alias table
//                 */
//                struct cstsymbol *tmp = searchalias(alias, c);
//                if (tmp == NULL || tmp->data == NULL) {
//                     /*
//                     this means something wrong in the MR. we should not proceed with it, a semantic check should be done in the MR.
//                     */
//                    fprintf(stderr, "Semantic error. No meaning for the variable %s and no alias too. Please check with the MR.\n", c->symbol);
//                    exit(-1);
//                }
//                c = tmp;
//             }
//             char *arg = __combine_3_strings__("(", gramtype2string(en->type), ")");
//             char *tmp = strrep(s, arg, c->data);
//             free(arg);
//             free(s);
//             s = tmp;
//             c->type = si->jtype;
//         }
//     }
//     /* 
//     s contains the synthesised SI 
//     */
//    __remove_all_children_cst__(node);
//     __replace_si_at_parent__(node, Synthesised, s);
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
    enum explicit_datatype synthesised_datatype = ((struct si *)gqueue(d->si_q, 0))->synthesised_datatype;
    if (has_datatype(x->cstptr)) {
        struct queue *siq = q_searchqueue(d->si_q, x, __match_si_with_1_arg_datatype__);
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
    // new->args = (char**)malloc(sizeof(char*) * new->arg_count);
    // new->args[0] = (char*)malloc(sizeof(char) * 4);
    new->args = initqueue();
    struct si_arg *arg = (struct si_arg *)malloc(sizeof(struct si_arg));
    arg->symbol = (char*) strdup("(*)");
    if (syntax == CD) {
        arg->datatype = JavaInteger;
    } else {
        arg->datatype = -1;
    }
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
        return 1;
    } else {
        struct astnode *child = getastchild(node, 0);
        if (child->type != Variable || child->token->symbol[0] != 'e') {
            return 1;
        } else {
            return 0;
        }
    }
}

void sianalysis() {
    struct astnode *node;
    // struct si *si;
    struct queue *si_q;
    #if SIDEBUG
    printf("si analysis: there are %d predicates in the queue.\n", predicates->count);
    #endif
    /* 
        sorting the predicates according to the semantic interpretation found 
        1st priority: all predicates with 1-argument si, and the argument is '*'. these predicates can be synthesised without any affects from other predicates
        2nd priority: all predicates with 1-argument si, and the argument is not '*'
        3rd priority: all other predicates which syntax are not preposition (IN).
        4th priority: predicates which syntax are prepositions. IN predicates require two arguments which are both synthesised, otherwise the code synthesis will be very difficult to compose, such as, Template in synthesis, variable in synthesis, variable in synthesis to template, etc.
    */
    struct queue *tmp = initqueue(), *two_args_preds = initqueue(), *in_preds = initqueue();
    while (!isempty(predicates)) {
        node = (struct astnode*)dequeue(predicates);
        // reset the si pointer for checking a node dequeue from the predicates queue
        si_q = NULL;
        if (selfSI[node->syntax] == 0) {
            // generate runtime SI
            node->si_q = initqueue();
            enqueue(node->si_q, (void*)__generate_runtime_si__(node));
            push(tmp, node);
        } else if (__is_event_predicate__(node) == 0) { 
            si_q = q_searchqueue(silist, node, __eventsimatcher);
            if (si_q == NULL || si_q->count == 0) {
                /* 
                    there can be a possibility that all the relevant entities are not going to be synthesised. in another words, the relevant entities do not have predicates matched with SI. if this is the case, this event is useless.
                */
                fprintf(stderr, "Symbol error(si analysis): Please provide the SI for predicate(%s)\n", node->token->symbol);
            } else {
                node->si_q = si_q;
                enqueue(two_args_preds, node);
            }
        } else {
            si_q = q_searchqueue(silist, node, __simatcher);
            if (si_q == NULL || si_q->count == 0) {
                if (node->isroot) {
                    fprintf(stderr, "Symbol error(si analysis): Please provide the SI for predicate(%s)\n", node->token->symbol);
                    exit(-10);
                }
                __remove_all_children_cst__(node);
                if (node->parent->type != Connective) {
                    fprintf(stderr, "Symbol error(si analysis): Please provide the SI for predicate(%s)\n", node->token->symbol);
                    exit(-10);
                } else if (
                    node->parent->conntype != Op_And &&
                    node->parent->conntype != Op_Or) {
                    fprintf(stderr, "Symbol error(si analysis): Please provide the SI for predicate(%s)\n", node->token->symbol);
                    exit(-11);                
                } else {
                    root = deleteastnodeandedge(node, root);
                }
                continue;
            } else { 
                node->si_q = si_q;
                if (node->syntax == IN) {
                    enqueue(in_preds, node);
                } else if (node->syntax == NN) {
                    struct si *si = (struct si*)gqueue(si_q, 0);
                    struct si_arg *arg = (struct si_arg*)gqueue(si->args, 0);
                    if (si_q->count == 1 && strcmp(arg->symbol, "*") == 0) {
                        push(tmp, node);
                    } else {
                        enqueue(tmp, node);
                    }
                } else {
                    enqueue(two_args_preds, node);
                }
            }
        }
    }
    while (!isempty(two_args_preds)) {
        enqueue(tmp, dequeue(two_args_preds));
    }
    while (!isempty(in_preds)) {
        enqueue(tmp, dequeue(in_preds));
    }
    deallocatequeue(two_args_preds, NULL);
    deallocatequeue(predicates, NULL);
    deallocatequeue(in_preds, NULL);
    predicates = tmp;
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
            if (child->cstptr->status != Assigned && 
                !__is_noun_predicate__(node) && 
                node->syntax != CD) {
                /* there can be a case that the preposition comes before the adjectives. we have to think of retry */
                semantic_error("Synthesis is stopped because a predicate(%s) has non-noun and non-CD syntax and its argument has not been assigned.", node->token->symbol); 
            } else if (__is_event_variable__(child)) {
                /* the child is an event variable, and it is marked with Assigned which indicates all event components (related variables) are marked Assigned */
                event_synthesis(node);
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

        // int x = (*code_syntheses[node->syntax])(node);       
        // if (check != 0) {
        //     #if SIDEBUG
        //     printf("si synthesis: predicate %s(%s) code synthesis is not done in this loop.\n", node->token->symbol, ptbsyntax2string(node->syntax));
        //     #endif
        //     enqueue(predicates, (void*)node);
        // } else {
        //     count = predicates->count;
        // }
        #if ASTDEBUG
        showast(root, 0);
        showqueue(cst, showcstsymbol);
        fflush(stdout);
        #endif
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
        addalias(alias, left->cstptr, right->cstptr);        
        removecstref(left->token->symbol, left);
        removecstref(right->token->symbol, right);
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
                si->args->count == child_count) 
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
    struct event *event = searchevent(child->token->symbol);
    if (strcmp(si->symbol, node->token->symbol) == 0 &&
        search_syntax(si, node->syntax) == 0 &&
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
    printf("Symbol          Syntactic Category       Arity     Arguments    Interpretation\n");
    printf("Symbol: %s   Syntactic Category: ", si->symbol);        
    for (int j = 0; j < si->syntax->count; ++j) {
        printf("%s ", ptbsyntax2string((enum ptbsyntax)gqueue(si->syntax, j)));
    }
    printf("  Arity: %d   Arguments: ", si->args->count);
    for (int j = 0; j < si->args->count; ++j) {
        struct si_arg *arg = (struct si_arg*)gqueue(si->args, j);
        printf("%s(%d)", arg->symbol, arg->datatype);
    }
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



