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


#include "sshare.h"
#include "synthesis.h"

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
int __match_interpretation_and_get_type(void *, void *);
// int __is_Rel_dependent__(struct cstsymbol *);
void generate_param_si(char *s);
int has_Rel_SI(struct queue *siq);

int selfSI[] = { 1, 0, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1, 1};


int __is_noun_predicate__(struct astnode *node) {
    return node->syntax == NN || node->syntax == NNP || node->syntax == NNS || node->syntax == NNPS;
}

int __is_event_variable__(struct astnode *node) {
    return node->token->symbol[0] == 'e';
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

void __replace_si_at_parent__(struct astnode *node, enum astnodetype type, char *si) {        
    free(node->token->symbol);
    node->token->symbol = (char*) strdup(si);
    node->type = type;
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







// /*
//     operations of
//     1. putting the semantics synthesised SI into the targetnode's cstptr datalist
//     2. removing the cst references
//     3. deleting the subtree rooted at subtree_root
// */
// void __subtree_with_direct_syntax_operation__(struct astnode *subtree_root, struct astnode *targetnode, char *interpretation) {
    // struct cstsymbol *cstptr = targetnode->cstptr;
    // if (interpretation != NULL && strlen(interpretation) > 0) {
    //     enqueue(cstptr->datalist, (char *)strdup(interpretation));
    // }
    // cstptr->status = Assigned;
    // cstptr->ref_count--;
    // root = deleteastnodeandedge(subtree_root, root);
// }

// int __direct_syntax_synthesis__(struct astnode *node) {
//     struct astnode *child = (struct astnode *) getastchild(node, 0);
//     struct si *targetsi = (struct si *)gqueue(node->si_q, 0);
//     /*
//         if both p and r are ANY, then it is not specific, so we should not inherit it to overwrite the entity datatype        
//     */
//     if (targetsi->synthesised_datatype != NULL && 
//         targetsi->synthesised_datatype->p >= 0 &&
//         targetsi->synthesised_datatype->r >= 0) { 
//         child->cstptr->datatype = targetsi->synthesised_datatype;
//     }
//     if (targetsi->type != -1) {
//         child->cstptr->interpretation_type = targetsi->type;
//         child->cstptr->datatype->i = targetsi->type;
//     }
//     // __subtree_with_direct_syntax_operation__(node, child, targetsi->interpretation);
//     if (targetsi->interpretation != NULL && strlen(targetsi->interpretation) > 0) {
//         enqueue(child->cstptr->datalist, (char *)strdup(targetsi->interpretation));
//     }
//     child->cstptr->status = Assigned;
//     child->cstptr->ref_count--;
//     root = deleteastnodeandedge(node, root);
//     return 0;
// }










int Vseries_code_synthesis(struct astnode *node) {
    return 0;
}





int CC_code_synthesis(struct astnode *node) { return 0; }

int DT_code_synthesis(struct astnode *node) { return 0; }
int EX_code_synthesis(struct astnode *node) { return 0; }
int FW_code_synthesis(struct astnode *node) { return 0; }


int LS_code_synthesis(struct astnode *node) { return 0; }
int MD_code_synthesis(struct astnode *node) { return 0; }

int NNPS_code_synthesis(struct astnode *node) { return 0; }
int PDT_code_synthesis(struct astnode *node) { return 0; }
int POS_code_synthesis(struct astnode *node) { return 0; }
int PRP_code_synthesis(struct astnode *node) { return 0; }
int PRP_POS_code_synthesis(struct astnode *node) { return 0; }
int RB_code_synthesis(struct astnode *node) {
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
    struct queue *siq = q_searchqueue(d->si_q, x->cstptr->datatype, __match_si_with_input_arg_datatype__);
    if (siq->count == 0) sinotfound_error(rel_symbol);
    __Rel_synthesis__(x->cstptr, d->cstptr, siq);
    deallocatequeue(siq, NULL);
    // __subtree_with_direct_syntax_operation__(node, d, NULL);

    d->cstptr->status = Assigned;
    d->cstptr->ref_count--;
    root = deleteastnodeandedge(node, root);
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
    arg->datatype->p = AnyPrimitiveType;
    arg->datatype->r = AnyRefType;
    arg->datatype->types = NULL;
    new->synthesised_datatype = (struct datatype *)malloc(sizeof(struct datatype));
    if (syntax == CD) {
        new->synthesised_datatype->p = Integer;
        new->synthesised_datatype->r = UNDEFINED;
    } else {
        new->synthesised_datatype->p = AnyPrimitiveType;
        new->synthesised_datatype->r = AnyRefType;
    }    
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
    #if SIANALYSIS
    printf("checking satify %s...\n", node->token->symbol);
    #endif
    if (countastchildren(node) == 1) {
        struct event *e = __searchevent(getastchild(node, 0)->cstptr);
        if (e->entities->count == 1) {
            struct entity *en = (struct entity *)gqueue(e->entities, 0);
            #if SIANALYSIS
            printf("checking entity %s\n", en->cstptr->symbol);
            #endif
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


int __match_same_child_variable__(void *_node, void *_inputnode) {
    struct astnode *current = (struct astnode *)_node;
    struct astnode *input = (struct astnode *)_inputnode;

    struct astnode *child1 = getastchild(current, 0), *child2 = getastchild(input, 0);
    if (child1->cstptr == child2->cstptr) 
        return TRUE;
    else
        return FALSE;
}

void sianalysis() {
    struct astnode *node = NULL;
    struct queue *visited_variables = initqueue(), *target = initqueue(), *last = initqueue();
    int check = -1;
    #if SIANALYSIS
    for (int i = 0; i < predicates->count; ++i) {
        printf("%s\n", ((struct astnode *)gqueue(predicates, i))->token->symbol);
    }
    #endif
    /* 
        adverbs that are restrictive such as 'only' must be resolved first.
        they are resolved by combining them to the verb predicate, 
        how do we indicate which verb to combine?
        check the event variable, they should have the same event variable
        if there is no event variable the same as an adverb, 
        the MR is considered to have semantic error
    */
    struct queue *rbqueue = initqueue();
    while (!isempty(predicates)) {
        node = (struct astnode *)dequeue(predicates);
        if (node->syntax == RB) enqueue(rbqueue, (void *)node);
        else enqueue(target, (void *)node);
    }
    while (!isempty(target)) { enqueue(predicates, dequeue(target)); }
    target = initqueue();
    while (!isempty(rbqueue)) {
        node = (struct astnode *)dequeue(rbqueue);
        /* find the predicate that accepts the same variable */
        struct astnode *r = searchqueue(predicates, node, __match_same_child_variable__);
        /* if not found, throw semantic error */
        if (r == NULL) semantic_error("Event variable matching for adverb predicate(%s) has failed.", node->token->symbol);
        /* if found, append the adverb predicate to that predicate */
        char *tmp = __combine_3_strings__(r->token->symbol, "_", node->token->symbol);
        free(r->token->symbol);
        r->token->symbol = tmp;
        /* delete the node */
        root = deleteastnodeandedge(node, root);
    }

    /* two sortings, at most n^4 */
    int count = 0, max = predicates->count * predicates->count * predicates->count * predicates->count; 
    while (!isempty(predicates)) {
        if (count > max) {
            internal_error("SI analysis has exceeded the maximum count. please check with the MR. ");
        }
        node = (struct astnode *)dequeue(predicates);
        #if SIANALYSIS
        printf("analysing %s...\n", node->token->symbol);
        #endif
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
                if (check_need_assigned_entity(node) && !has_Rel_SI(node->si_q)) {
                    /* current assumption of this case is that there must be an alias to the variable */
                    struct cstsymbol *_aliased_cstptr = searchalias(getastchild(node, 0)->cstptr);
                    if (_aliased_cstptr == NULL) {
                        semantic_error("Please check with sianalysis function for case NN. An entity for predicate(%s) that does not have an alias, and its cstptr is only referenced by itself.", node->token->symbol);
                    } else {
                        if (!searchqueue(visited_variables, _aliased_cstptr, __search_visited_variables__)) {
                            /* TODO: we should implement the node relocation, inserting the node just after the aliased node is visited */
                            internal_error("SI analysis (not yet implemented part): The aliased variable is not yet visisted\n");
                        } else {
                            enqueue(visited_variables, (void *)getastchild(node, 0)->cstptr);
                            enqueue(target, (void *)node);
                        }                        
                    }
                } else {
                    enqueue(visited_variables, (void *)getastchild(node, 0)->cstptr);
                    enqueue(target, (void *)node);
                }
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
                    struct astnode *tmp = NULL;
                    struct queue *tmpqueue = initqueue();
                    enqueue(tmpqueue, (void *)node);
                    #if SIANALYSIS
                    printf("before...\n");
                    for (int i = 0; i < predicates->count; ++i) {
                        printf("%s\n", ((struct astnode *)gqueue(predicates, i))->token->symbol);
                    }
                    #endif
                    while (!isempty(predicates)) {
                        tmp = (struct astnode *)dequeue(predicates);
                        #if SIANALYSIS
                        printf("checking %s(%d)\n", tmp->token->symbol, tmp->syntax);
                        #endif
                        if (tmp->syntax != NN && 
                            tmp->syntax != NNS && 
                            tmp->syntax != NNP && 
                            tmp->syntax != NNPS && 
                            tmp->syntax != CD) {
                            enqueue(tmpqueue, (void *)tmp);
                            #if SIANALYSIS
                            printf("enqueue %s...\n", tmp->token->symbol);
                            #endif
                            }
                        else {
                            #if SIANALYSIS
                            printf("pushing %s...\n", tmp->token->symbol);
                            #endif
                            push(tmpqueue, (void *)tmp);
                            break;
                        }
                    }
                    while (!isempty(tmpqueue)) {
                        push(predicates, (void *)pop(tmpqueue));
                    }
                    deallocatequeue(tmpqueue, NULL);
                    #if SIANALYSIS
                    for (int i = 0; i < predicates->count; ++i) {
                        printf("%s\n", ((struct astnode *)gqueue(predicates, i))->token->symbol);
                    }
                    #endif
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
        count++;
    }
    while (!isempty(last)) enqueue(target, dequeue(last));
    #if SIANALYSIS
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
            /* TODO: 
                if the return is FALSE, we should push it back to the predicates queue. 
                there is a case in NN, that the predicate depends on a variable with type.
                therefore, the variable needs to wait for its aliased variable to be assigned.                
            */
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



