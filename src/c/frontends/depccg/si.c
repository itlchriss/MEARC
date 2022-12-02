#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "util.h"
#include "si.h"
#include "ast.h"
#include "event.h"

extern struct astnode *root;
extern struct queue *_events;

int search_syntax(struct si*, enum ptbsyntax);
int __sicomparator(void *, void *);
int __eventsicomparator(void *, void *);

int selfSI[] = { 1, 0, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 };
char *javadatatype_name[] = { "PRIMITIVE", "NON_PRIMITIVE", "NON_PRIMITIVE_WITH_DIMENSIONS" };
char *argtype_name[] = { "EXP", "KEYWORD", "LITERAL" };

struct si* newsi() {
    struct si* new = (struct si*)malloc(sizeof(struct si));
    // new->args = NULL;
    new->interpretation = NULL;
    new->_args = initqueue();
    new->_syntax = initqueue();
    return new;
}


void __replace_si_at_parent__(struct astnode *node, enum astnodetype type, char *si) {    
    deleteastchildren(node);
    free(node->token->symbol);
    node->token->symbol = (char*) strdup(si);
    node->type = type;
}

void __remove_all_children_cst__(struct queue *cst, struct astnode *node) {
    struct astnode *child;
    for (int i = 0; i < countastchildren(node); ++i) {
        child = getastchild(node, i);
        removecstref(cst, child->token->symbol, child);
    }
}

/*
    substituting semantic interpretation from a synthesised node into the template from another node
    _snode: an ast node with type Synthesised, the symbol contains the semantic interpretation
    _tnode: an ast node with type Template, the symbol contains the template
*/
char* __subsititute_synthesised_into_template__(struct astnode *_snode, struct astnode *_tnode, struct queue *cst) {
    struct cstsymbol *c = searchsymbolbyref(cst, _tnode);
    char *tmp = __combine_3_strings__("(", c->symbol, ")");
    char *new = strrep(_tnode->token->symbol, tmp, _snode->token->symbol);
    free(tmp);
    return new;
}

/* obtaining semantic interpretation from the template of parent node and synthesising it with its children */
char* __obtain_si_from_subtree__(struct astnode *parent, struct si* si) {
    char *s = (char*)strdup(si->interpretation), *tmp = NULL;
    struct astnode *child = NULL;
    for (int i = 0; i < countastchildren(parent); ++i) {
        child = getastchild(parent, i);
        if (child->type == Synthesised) {
            /* child semantic interpretation has been resolved. use it in code synthesis */
            tmp = strrep(s, ((struct siarg*)gqueue(si->_args, i))->data, child->token->symbol);
        } else {
            /* child semantic interpretation has NOT been resolved. leave the name of the argument as the argument of the semantic interpretation for possible code synthesis to be happened in operator resolution */
            char *arg = __combine_3_strings__("(", child->token->symbol, ")");
            tmp = strrep(s, ((struct siarg*)gqueue(si->_args, i))->data, arg);
            free(arg);
        }
        free(s);
        s = tmp;
    }
    return s;
}

void __update_cstsymbol_data__(struct cstsymbol *c, char *data) {
    if (c->data) {
        free(c->data);
    }
    c->data = (char*)strdup(data);
}

int Jseries_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) {
    struct astnode *child;
    for (int i = 0; i < countastchildren(node); ++i) {
        child = getastchild(node, i);
        if (child->type != Synthesised) return 1;
    }
    char *s = __obtain_si_from_subtree__(node, si);
    if (countastchildren(node) == 1) {
        child = getastchild(node, 0);
        struct cstsymbol *c = searchsymbolbyref(cst, child);
        __remove_all_children_cst__(cst, node);
        __update_cstsymbol_data__(c, s);
        syncsymbol(c);
        if (getavailablerefs(c) == 0 && node->parent->type == Quantifier) {
            /* a special case for the sentences that the predicate is a single adjective */
            __replace_si_at_parent__(node, Synthesised, s);
        } else {
            root = deleteastnodeandedge(node, root);
        }
    } else {
        __replace_si_at_parent__(node, Synthesised, s);
        __remove_all_children_cst__(cst, node);
    }
    free(s);
    return 0;
}

// int Vseries_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) {
//     struct astnode *child;
//     /* Verbs usually accepts 2 arguments (subject, object). Thus, verbs cannot be resolved before both arguments are resolved. */
//     for (int i = 0; i < countastchildren(node); ++i) {
//         child = getastchild(node, i);
//         if (child->type != Synthesised) {
//             return 1;
//         }
//     }
//     char *s = __obtain_si_from_subtree__(node, si);
//     __remove_all_children_cst__(cst, node);
//     __replace_si_at_parent__(node, Synthesised, s);
//     free(s);
//     return 0;
// }

/*
This is a version for event semantics.
The child of a predicate with verb related syntactic category, must be an event variable
*/
int Vseries_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) {
    struct astnode *child;
    if (countastchildren(node) > 1) {
        printf("MR is not in event semantics. Please use another frontend instead.");
        exit(-14);
    }
    child = getastchild(node, 0);
    struct event *event = searchevent(_events, child->token->symbol);    
    struct entity *entity_a = (struct entity*)gqueue(event->entities, 0), *entity_b = (struct entity*)gqueue(event->entities, 1);
    struct cstsymbol *sym_a = searchcst(cst, entity_a->var), *sym_b = searchcst(cst, entity_b->var);
    /*
    This indicates that one of the symbol has not been synthesised yet
    */
    if (sym_a->data == NULL || sym_b->data == NULL) return 1;
    /*
    Assume Subject always comes before accusation in the MR.
    If not, the following code needs modification
    */
    char *tmp = strrep(si->interpretation, ((struct siarg*)gqueue(si->_args, 0))->data, sym_a->data);
    char *s = strrep(tmp, ((struct siarg*)gqueue(si->_args, 1))->data, sym_b->data);
    free(tmp);
    updatecstsymbol(cst, s, child);
    /*
    It is not only removing children now. It is to remove the variables involving.
    */
    // __remove_all_children_cst__(cst, node);
    /*
    removing the current child symbol, which is the one of the event variable from the compiler symbol reference table. 
    */
    removecstref(cst, event->var, child);
    // __replace_si_at_parent__(node, Synthesised, s);
    for (int i = 0; i < event->entities->count; ++i) {
        entity_a = (struct entity*)gqueue(event->entities, i);
        sym_a = entity_a->ptr;
        if (sym_a->symbol[0] == 'e') {
            /*
            if this is an event variable, we have to delete its quantifier.
            because it has been already synthesised, and its SI is being used in another event.
            */
            for (int j = 0; j < sym_a->refs->count; ++j) {
                child = (struct astnode*)gqueue(sym_a->refs, j);
                // if (child->type == Quantifier) {
                deleteastchildren(child);
                deleteastchild(child->parent, child);
                break;
                // }
            }
        } 
    }
    aliaseventsubject(_events, cst, event);
    sym_a = searchcst(cst, event->var);
    /*
    replacing the SI to the quantifier of the event variable
    */
    for (int i = 0; i < sym_a->refs->count; ++i) {
        child = (struct astnode*)gqueue(sym_a->refs, i);
        if (child->type == Quantifier) {
            free(child->token->symbol);
            child->token->symbol = (char*)strdup(s);
            child->type = Synthesised;
            deleteastchildren(child);
            break;
        } else {
            deleteastnode(child);
        }
    }
    // deallocatequeue(sym_a->refs, NULL);
    // sym_a->refs = initqueue();
    free(s);
    return 0;
}


int Nseries_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) {
    struct astnode *child = getastchild(node, 0);    
    char *s = (char*)strdup(si->interpretation);
    struct cstsymbol *c = searchsymbolbyref(cst, child);
    __remove_all_children_cst__(cst, node);    
    #if SIDEBUG
    printf("Nseries_code_synthesis: si(%s) and child token(%s)\n", s, child->token->symbol);
    #endif         
    if (strcmp(s, "\\param") == 0) {
        /* indicating the next predicate with the same indentifier must be a parameter name */
        /* potential research problem. can we infer or guess the parameter name if the word used in the sentence is not the exact parameter name? */
    } else {
        if (strcmp(((struct siarg*)gqueue(si->_args, 0))->data, "(*)") == 0) {
            /* argument does no effects to the semantic interpretation */
            /* therefore, directly replacing the semantic interpretation to all the places of identifiers */ 

            /* there can be a case that, a noun predicate is synthesised as template. such that when subtree root is trying to synthesise, it should be substituted to the child's semantic template */
            if (child->type == Template) {
                char *arg = __combine_3_strings__("(", c->symbol, ")");
                char *tmp = strrep(child->token->symbol, arg, s);
                free(s);
                s = (char*) strdup (tmp);
                free(arg);
                free(tmp);
                syncentitysitype(_events, c->symbol, EXP);
            } else {
                syncentitysitype(_events, child->token->symbol, si->type);
            }
        } else {
            /* argument does affect the semantic interpretation */
            /* therefore, the semantic interpretation of the subtree requires code synthesis */
            s = __obtain_si_from_subtree__(node, si);
            syncentitysitype(_events, child->token->symbol, si->type);
        }
        // struct cstsymbol *c = updatecstsymbol(cst, s, child);
        __update_cstsymbol_data__(c, s);
        syncsymbol(c);
    }               
    root = deleteastnodeandedge(node, root);
    return 0;
}



int CC_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int CD_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { 
    char *s = node->token->symbol;    
    struct astnode *child = getastchild(node, 0); 
    struct cstsymbol *c = searchsymbolbyref(cst, child);
    __remove_all_children_cst__(cst, node);    
    #if SIDEBUG
    printf("CD_code_synthesis: si(%s) and child token(%s)\n", s, child->token->symbol);
    #endif        
    // struct cstsymbol *c = updatecstsymbol(cst, child->token->symbol, s);
    // struct cstsymbol *c = updatecstsymbol(cst, s, child);
    __update_cstsymbol_data__(c, s);
    syncsymbol(c);         
    root = deleteastnodeandedge(node, root);
    return 0;
}
int DT_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int EX_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int FW_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int IN_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) {  
    struct astnode *x;
    if (countastchildren(node) == 1) {
        fprintf(stderr, "single argument preposition is not supported.\n");
        exit(-5);
    }
    for (int i = 0; i < countastchildren(node); ++i) {
        x = getastchild(node, i);
        if (x->type != Synthesised && x ->type != Template) return 1;
    }
    x = getastchild(node, 0);
    struct astnode *y = getastchild(node, 1);
    char *s;
    struct cstsymbol *c;
    if (strcmp(si->interpretation, "\\sub(x)2(y)") == 0) {
        if (x->type == Template) {
            s = __subsititute_synthesised_into_template__(y, x, cst);
            // c = updatecstsymbol(cst, s, y);
            c = updatecstsymbol(cst, s, x);
        } else {
            s = __subsititute_synthesised_into_template__(x, y, cst);
            // c = updatecstsymbol(cst, s, x);
            c = updatecstsymbol(cst, s, y);
        }        
        __remove_all_children_cst__(cst, node);
        syncsymbol(c);        
        root = deleteastnodeandedge(node, root);
    } else if (strcmp(si->interpretation, "\\sub(y)2(x)") == 0) {
        if (x->type == Template) {
            s = __subsititute_synthesised_into_template__(y, x, cst);
            c = updatecstsymbol(cst, s, x);
        } else {
            s = __subsititute_synthesised_into_template__(x, y, cst);
            c = updatecstsymbol(cst, s, y);
        }        
        __remove_all_children_cst__(cst, node);
        syncsymbol(c);        
        root = deleteastnodeandedge(node, root);
    } else {
        #if SIDEBUG
        fprintf(stderr, "the si(%s) is not implemented.\n", si->interpretation);
        #endif
    }
    return 0;
}
int JJ_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return Jseries_code_synthesis(node, si, cst); }
int JJR_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int JJS_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int LS_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int MD_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int NN_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return Nseries_code_synthesis(node, si, cst); }
int NNS_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return Nseries_code_synthesis(node, si, cst); }
int NNP_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return Nseries_code_synthesis(node, si, cst); }
int NNPS_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return Nseries_code_synthesis(node, si, cst); }
int PDT_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int POS_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int PRP_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int PRP_POS_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int RB_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) {
    for (int i = 0; i < countastchildren(node); ++i) {
        if (getastchild(node, i)->type != Synthesised) return 1;
    }
    char *s = __obtain_si_from_subtree__(node, si);
    __replace_si_at_parent__(node, Synthesised, s);
    __remove_all_children_cst__(cst, node);
    free(s);
    return 0;
}
int RBR_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int RBS_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int RP_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int SYM_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int TO_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int UH_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int VB_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { 
    return Vseries_code_synthesis(node, si, cst); 
}
int VBD_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) {  
    return Vseries_code_synthesis(node, si, cst);
}
int VBG_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { 
    return Vseries_code_synthesis(node, si, cst);
}
int VBN_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { 
    return Vseries_code_synthesis(node, si, cst);
}
int VBP_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int VBZ_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) {
    return Vseries_code_synthesis(node, si, cst);
}
int WDT_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int WP_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int WP_POS_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int WRB_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int (*code_syntheses[])(struct astnode *, struct si *, struct queue *) = {CC_code_synthesis, CD_code_synthesis, DT_code_synthesis, EX_code_synthesis, FW_code_synthesis, IN_code_synthesis, JJ_code_synthesis, JJR_code_synthesis, JJS_code_synthesis, LS_code_synthesis, MD_code_synthesis, NN_code_synthesis, NNS_code_synthesis, NNP_code_synthesis, NNPS_code_synthesis, PDT_code_synthesis, POS_code_synthesis, PRP_code_synthesis, PRP_POS_code_synthesis, RB_code_synthesis, RBR_code_synthesis, RBS_code_synthesis, RP_code_synthesis, SYM_code_synthesis, TO_code_synthesis, UH_code_synthesis, VB_code_synthesis, VBD_code_synthesis, VBG_code_synthesis, VBN_code_synthesis, VBP_code_synthesis, VBZ_code_synthesis, WDT_code_synthesis, WP_code_synthesis, WP_POS_code_synthesis, WRB_code_synthesis};

/* 
    semantic interpretation identification 
    this is a process to identify as many si as possible presenting in the meaning representation
    - parameter descriptions
        predicates  : a queue holding pointers of predicates present in an abstract syntax tree parsed from meaning representation
        silist      : a queue holding semantic interpretations parsed from standard semantic interpretation database
        cst         : a queue holding the compile time symbols, aka the identitiers in the meaning representation
*/
void siidentification(struct queue *predicates, struct queue *silist, struct queue *cst) {
    struct astnode *node;
    struct si *si;
    #if SIDEBUG
    printf("si identification: there are %d predicates in the queue.\n", predicates->count);
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
        if (selfSI[node->syntax] == 0) {
            push(tmp, node);
        } else {
            si = searchqueue_firstmatch(silist, node, __sicomparator);
            if (si == NULL) {
                __remove_all_children_cst__(cst, node);
                if (node->parent->type != Connective) {
                    fprintf(stderr, "Symbol error: Please provide the SI for predicate(%s)\n", node->token->symbol);
                    exit(-10);
                } else if (
                    node->parent->conntype != Op_And &&
                    node->parent->conntype != Op_Or) {
                    fprintf(stderr, "Symbol error: Please provide the SI for predicate(%s)\n", node->token->symbol);
                    exit(-11);                
                } else {
                    root = deleteastnodeandedge(node, root);
                }
                continue;
            } else if (node->syntax == IN) {
                enqueue(in_preds, node);
            } else if (node->syntax == NN) {
                enum argtype _argtype = ((struct siarg*)gqueue(si->_args, 0))->type;
                if (_argtype == KEYWORD || _argtype == LITERAL) {
                    push(tmp, node);
                } else {
                    enqueue(tmp, node);
                }
            } else {
                enqueue(two_args_preds, node);
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

    #if SIDEBUG
    printf("si identification: after sorting, there are %d predicates in the queue.\n", predicates->count);
    for (int i = 0; i < predicates->count; ++i) {
        node = (struct astnode*)gqueue(predicates, i);
        printf("%d. %s(%s)\n", i + 1, node->token->symbol, ptbsyntax2string(node->syntax));
    }
    #endif

    while (!isempty(predicates)) {    
        node = (struct astnode*)dequeue(predicates);
        #if SIDEBUG
        printf("si identification: processing predicate %s(%s).\n", node->token->symbol, ptbsyntax2string(node->syntax));
        #endif
        if (selfSI[node->syntax] == 1) {
            if (node->syntax >= 26 && node->syntax <= 31) {
                /*
                    Verb syntax. Typically we are checking the event restrictions.
                    To synthesise an event si, we have to know the synthesised si of the related entities
                */
                struct event *ev = searchevent(_events, getastchild(node, 0)->token->symbol);
                int ready = 0;
                for (int i = 0; i < ev->entities->count; ++i) {
                    if (((struct entity *)gqueue(ev->entities, i))->type == -1) {
                        ready = -1;
                        break;
                    }
                }
                if (ready == -1) {
                    #if SIDEBUG
                    printf("si identification: predicate %s(%s) code synthesis is not done in this loop.\n", node->token->symbol, ptbsyntax2string(node->syntax));
                    #endif
                    enqueue(predicates, (void*)node);
                } else {
                    // si = searchqueue(silist, ev, __eventsicomparator);
                    // TODO: 1. search all si that matches the predicate
                    //       2. filter the si with the exact same argument types
                    struct queue *si_set = searchqueue_allmatch(silist, node, __sicomparator);
                    si = searchqueue_firstmatch(si_set, ev, __eventsicomparator);
                    if (si == NULL) {
                        fprintf(stderr, "SI of %s is not found with the event component SI types.\n", node->token->symbol);
                    } else {
                        int x = (*code_syntheses[node->syntax])(node, si, cst);
                        #if SIDEBUG
                        if (x != 0) {
                            printf("si identification: predicate %s(%s) code synthesis is not done in this loop.\n", node->token->symbol, ptbsyntax2string(node->syntax));
                        }
                        #endif
                    }
                    deallocatequeue(si_set, NULL);
                }
            } else {
                si = searchqueue_firstmatch(silist, node, __sicomparator);
                if (si == NULL) {
                    #if SIDEBUG
                    printf("si identification: no si for predicate %s(%s) is found\n", node->token->symbol, ptbsyntax2string(node->syntax));
                    #endif
                    node->type = NoSI;
                } else {
                    int x = (*code_syntheses[node->syntax])(node, si, cst);
                    if (x != 0) {
                        #if SIDEBUG
                        printf("si identification: predicate %s(%s) code synthesis is not done in this loop.\n", node->token->symbol, ptbsyntax2string(node->syntax));
                        #endif
                        enqueue(predicates, (void*)node);
                    }
                }  
            }
        } else {
            (*code_syntheses[node->syntax])(node, si, cst);
        }
        #if ASTDEBUG
        showast(root, 0);
        showqueue(_events, showevent);
        fflush(stdout);
        #endif
    }
    #if SIDEBUG
    printf("si identification finished\n");
    #endif
}

void opresolution(struct queue *operators, struct queue *cst) {
    struct astnode *node, *left, *right;    
    while (operators->count > 0) {
        node = (struct astnode*)dequeue(operators);
        left = getastchild(node, 0);
        right = getastchild(node, 1);
        char *s = NULL;

        if (left->type == Synthesised && right->type == Synthesised) {
            /* both ast node semantic interpretations have been synthesised */
            /* therefore, the operator serves as a comparator */
            s = __combine_3_strings__(left->token->symbol, "==", right->token->symbol);
        } else {
            /* if node A has node type == Template, the symbol of node A is aliased as the symbol of node B */
            /* such that, semantic interpretation is synthesised from substituting the semantic interpretation of node B into the template in node A */
            if (left->type == Template && right->type == Synthesised) {
                s = __subsititute_synthesised_into_template__(right, left, cst);
            } else if (left->type == Synthesised && right->type == Template) {                
                s = __subsititute_synthesised_into_template__(left, right, cst);
            } else {
                /* missing semantic intrepretations. code synthesis failed */
                return;
            }
        }
        __replace_si_at_parent__(node, Synthesised, s);
        if (s) {
            free(s);
        }
    }    
}



int __sicomparator(void *_si, void *_astnode) {
    struct si* si = (struct si*)_si;
    struct astnode *node = (struct astnode*)_astnode;
    if (strcmp(node->token->symbol, si->term) == 0 &&
                search_syntax(si, node->syntax) == 0 && 
                (
                    si->arg_count == countastchildren(node) ||
                    si->arg_count == countevententity(_events, getastchild(node, 0)->token->symbol)
                )) 
        return 0;
    else
        return 1;
}

int __eventsicomparator(void *_si, void *_event) {
    struct si *si = (struct si*)_si;
    struct event *ev = (struct event*)_event;
    if (si->_args->count != ev->entities->count) return 1;
    struct entity *_en = NULL;
    struct siarg *_arg = NULL;
    for (int i = 0; i < si->_args->count; ++i) {
        _arg = (struct siarg*)gqueue(si->_args, i);
        _en = (struct entity*)gqueue(ev->entities, i);
        if (
            _arg->type != _en->sitype
        ) {
            return 1;
        }
    }
    return 0;
}

int search_syntax(struct si* si, enum ptbsyntax ptb) {
    for (int i = 0; i < si->_syntax->count; ++i) {
        if (string2ptbsyntax((char*)gqueue(si->_syntax, i)) == ptb) {
            return 0;
        }
    }
    return 1;
}

void showsi(void *_si) {
    struct si *si = (struct si*)_si;
    struct siarg* tmparg = NULL;
    printf("==========================Semantic interpretations: =========================\n");
    printf("Term          Syntactic Category       Arity     Arguments    Interpretation\n");
    printf("Term: %s   Syntactic Category: ", si->term);        
    for (int i = 0; i < si->_syntax->count; ++i) {
        printf("%s ", (char*)gqueue(si->_syntax, i));
    }
    printf("  Arity: %d   Arguments: ", si->arg_count);
    for (int i = 0; i < si->_args->count; ++i) {
        tmparg = (struct siarg*)gqueue(si->_args, i);
        printf("%s(%s) ", tmparg->data, argtype_name[tmparg->type]);
    }
    printf("   %s(%p)\n", si->interpretation, (void*)si->interpretation);
    printf("============================================================================\n");
}

void deallocatesiarg(void *_input) {
    struct siarg* input = (struct siarg*)_input;
    free(input->data);
    free(input);
}

void deallocatesyntax(void *_input) {
    free(_input);
}

void deallocatesi(void *tmp) {    
    struct si *si = (struct si*)tmp;
    // for (int i = 0; i < si->arg_count; ++i) {
    //     free(si->args[i]);
    // }
    deallocatequeue(si->_args, deallocatesiarg);
    deallocatequeue(si->_syntax, deallocatesyntax);
    // free(si->syntax);
    if (si->interpretation)
        free(si->interpretation);
    free(si->term);
    free(si);
}

enum argtype str2argtype(char *str) {
    if (strcmp(str, "EXP") == 0) return EXP;
    else if (strcmp(str, "KEYWORD") == 0) return KEYWORD;
    else if (strcmp(str, "LITERAL") == 0) return LITERAL;
    else return -1;
}


