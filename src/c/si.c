#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "util.h"
#include "si.h"
#include "ast.h"

extern struct astnode *root;
extern struct queue *predicates, *operators, *silist;
extern struct astnode *root;
struct queue *cst;    

int search_syntax(struct si*, enum ptbsyntax);
int __sicomparator(void *, void *);
int __argtype_si_comparator(void *, void *);

int selfSI[] = { 1, 0, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 0, 0};
char *javadatatype_name[] = { "PRIMITIVE", "NON_PRIMITIVE", "NON_PRIMITIVE_WITH_DIMENSIONS" };

void __replace_si_at_parent__(struct astnode *node, enum astnodetype type, char *si) {    
    deleteastchildren(node);
    free(node->token->symbol);
    node->token->symbol = (char*) strdup(si);
    node->type = type;
}

void __remove_all_children_cst__(struct astnode *node) {
    struct astnode *child;
    for (int i = 0; i < countastchildren(node); ++i) {
        child = getastchild(node, i);
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

/* obtaining semantic interpretation from the template of parent node and synthesising it with its children */
char* __obtain_si_from_subtree__(struct astnode *parent, struct si* si) {
    char *s = (char*)strdup(si->interpretation), *tmp = NULL;
    struct astnode *child = NULL;
    for (int i = 0; i < countastchildren(parent); ++i) {
        child = getastchild(parent, i);
        if (child->type == Synthesised) {
            /* child semantic interpretation has been resolved. use it in code synthesis */
            tmp = strrep(s, si->args[i], child->token->symbol);
        } else {
            /* child semantic interpretation has NOT been resolved. leave the name of the argument as the argument of the semantic interpretation for possible code synthesis to be happened in operator resolution */
            char *arg = __combine_3_strings__("(", child->token->symbol, ")");
            tmp = strrep(s, si->args[i], arg);
            free(arg);
        }
        free(s);
        s = tmp;
    }
    return s;
}

int __synthesis_predicate_at_root__(struct si *si) {
    // same as if and only if, we have to do operator resolution, simplification before synthesising the root predicate
    if (predicates->count > 0) {
        // we have to ensure all other predicates are synthesised
        return 1;
    }
    opresolution();
    root = astsimplification(root);
    // TODO: we will remove this once we revise the simplification algorithm
    root = astsimplification(root);
    char *s = __obtain_si_from_subtree__(root, si);
    __replace_si_at_parent__(root, Synthesised, s);
    __remove_all_children_cst__(root);
    free(s);
    return 0;
}

void __update_cstsymbol_data__(struct cstsymbol *c, char *data, int datatype) {
    if (c->data) {
        free(c->data);
    }
    c->data = (char*)strdup(data);
    c->type = datatype;
}

int Jseries_code_synthesis(struct astnode *node, struct si *si) {
    struct astnode *child;
    for (int i = 0; i < countastchildren(node); ++i) {
        child = getastchild(node, i);
        if (child->type != Synthesised) return 1;
    }
    char *s = __obtain_si_from_subtree__(node, si);
    if (countastchildren(node) == 1) {
        child = getastchild(node, 0);
        struct cstsymbol *c = searchsymbolbyref(child);
        __remove_all_children_cst__(node);
        __update_cstsymbol_data__(c, s, si->jtype);
        syncsymbol(c);
        if (getavailablerefs(c) == 0 && node->parent->type == Quantifier) {
            /* a special case for the sentences that the predicate is a single adjective */
            __replace_si_at_parent__(node, Synthesised, s);
        } else {
            root = deleteastnodeandedge(node, root);
        }
    } else {
        __remove_all_children_cst__(node);
        __replace_si_at_parent__(node, Synthesised, s);
        // __remove_all_children_cst__(node);
    }
    free(s);
    return 0;
}

int Vseries_code_synthesis(struct astnode *node, struct si *si) {
    struct astnode *child;
    /* Verbs usually accepts 2 arguments (subject, object). Thus, verbs cannot be resolved before both arguments are resolved. */
    for (int i = 0; i < countastchildren(node); ++i) {
        child = getastchild(node, i);
        if (child->type != Synthesised) {
            return 1;
        }
    }
    char *s = __obtain_si_from_subtree__(node, si);
    __remove_all_children_cst__(node);
    __replace_si_at_parent__(node, Synthesised, s);
    free(s);
    return 0;
}


int Nseries_code_synthesis(struct astnode *node, struct si *si) {
    struct astnode *child = getastchild(node, 0);    
    char *s = (char*)strdup(si->interpretation);
    struct cstsymbol *c = searchsymbolbyref(child);
    __remove_all_children_cst__(node);    
    #if SIDEBUG
    printf("Nseries_code_synthesis: si(%s) and child token(%s)\n", s, child->token->symbol);
    #endif         
    if (strcmp(s, "\\param") == 0) {
        /* indicating the next predicate with the same indentifier must be a parameter name */
        /* potential research problem. can we infer or guess the parameter name if the word used in the sentence is not the exact parameter name? */
    } else {
        if (strcmp(si->args[0], "(*)") == 0) {
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
            }
        } else {
            /* argument does affect the semantic interpretation */
            /* therefore, the semantic interpretation of the subtree requires code synthesis */
            s = __obtain_si_from_subtree__(node, si);
        }
        __update_cstsymbol_data__(c, s, si->jtype);
        syncsymbol(c);
        c->si_ptr = si;
    }               
    root = deleteastnodeandedge(node, root);
    return 0;
}



int CC_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int CD_code_synthesis(struct astnode *node, struct si *si) { 
    char *s = node->token->symbol;    
    struct astnode *child = getastchild(node, 0); 
    struct cstsymbol *c = searchsymbolbyref(child);
    __remove_all_children_cst__(node);    
    #if SIDEBUG
    printf("CD_code_synthesis: si(%s) and child token(%s)\n", s, child->token->symbol);
    #endif        
    __update_cstsymbol_data__(c, s, 0);
    syncsymbol(c);    
    // because CS is self defined SI that is dynamically generated
    c->si_ptr = NULL;     
    root = deleteastnodeandedge(node, root);
    return 0;
}
int DT_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int EX_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int FW_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int IN_code_synthesis(struct astnode *node, struct si *si) {      
    if (countastchildren(node) == 1) {
        fprintf(stderr, "single argument preposition is not supported.\n");
        exit(-5);
    }
    // a special case for root with preposition predicate
    if (node->isroot == 1) {
        return __synthesis_predicate_at_root__(si);
    } else {
        struct astnode *x;
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
                s = __subsititute_synthesised_into_template__(y, x);
                c = updatecstsymbol(s, x);
            } else {
                s = __subsititute_synthesised_into_template__(x, y);
                c = updatecstsymbol(s, y);
            }        
            __remove_all_children_cst__(node);
            syncsymbol(c);        
            root = deleteastnodeandedge(node, root);
        } else if (strcmp(si->interpretation, "\\sub(y)2(x)") == 0) {
            if (x->type == Template) {
                s = __subsititute_synthesised_into_template__(y, x);
                c = updatecstsymbol(s, x);
            } else {
                s = __subsititute_synthesised_into_template__(x, y);
                c = updatecstsymbol(s, y);
            }        
            __remove_all_children_cst__(node);
            syncsymbol(c);        
            root = deleteastnodeandedge(node, root);
        } else {
            // #if SIDEBUG
            // fprintf(stderr, "the si(%s) is not implemented.\n", si->interpretation);
            // #endif
            char *s = __obtain_si_from_subtree__(node, si);
            __remove_all_children_cst__(node);
            __replace_si_at_parent__(node, Synthesised, s);
            free(s);
        }
    }
    return 0;
}
int JJ_code_synthesis(struct astnode *node, struct si *si) { return Jseries_code_synthesis(node, si); }
int JJR_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int JJS_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int LS_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int MD_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int NN_code_synthesis(struct astnode *node, struct si *si) { return Nseries_code_synthesis(node, si); }
int NNS_code_synthesis(struct astnode *node, struct si *si) { return Nseries_code_synthesis(node, si); }
int NNP_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int NNPS_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int PDT_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int POS_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int PRP_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int PRP_POS_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int RB_code_synthesis(struct astnode *node, struct si *si) {
    if (node->isroot == 1) {
        //because the node is a root node, if we want to do the synthesis, the tree must be simplified first
        node = astsimplification(node);
        #if ASTDEBUG
        showast(root, 0);
        fflush(stdout);
        #endif
    }
    for (int i = 0; i < countastchildren(node); ++i) {
        if (getastchild(node, i)->type != Synthesised) return 1;
    }
    char *s = __obtain_si_from_subtree__(node, si);
    __replace_si_at_parent__(node, Synthesised, s);
    __remove_all_children_cst__(node);
    free(s);
    return 0;
}
int RBR_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int RBS_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int RP_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int SYM_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int TO_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int UH_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int VB_code_synthesis(struct astnode *node, struct si *si) { 
    return Vseries_code_synthesis(node, si); 
}
int VBD_code_synthesis(struct astnode *node, struct si *si) {  
    return Vseries_code_synthesis(node, si);
}
int VBG_code_synthesis(struct astnode *node, struct si *si) { 
    return Vseries_code_synthesis(node, si);
}
int VBN_code_synthesis(struct astnode *node, struct si *si) { 
    return Vseries_code_synthesis(node, si);
}
int VBP_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int VBZ_code_synthesis(struct astnode *node, struct si *si) {
    return Vseries_code_synthesis(node, si);
}
int WDT_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int WP_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int WP_POS_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int WRB_code_synthesis(struct astnode *node, struct si *si) { return 0; }

int Gram_Rel_synthesis(struct astnode *node, struct si *si) {
    for (int i = 0; i < countastchildren(node); ++i) {
        if (getastchild(node, i)->type != Synthesised) return 1;
    }
    struct astnode *x = getastchild(node, 0), *y = getastchild(node, 1);
    struct cstsymbol *c = searchsymbolbyref(x);
    struct si *_si = (struct si *)c->si_ptr;
    if (_si->g_arg_count == 0) {
        printf("Error: Predicate(%s) must have grammar arguments defined\n", _si->term);
        exit(-14);
    }
    char *s = strrep(x->token->symbol, _si->g_args[0], y->token->symbol);
    c = updatecstsymbol(s, x);      
    __remove_all_children_cst__(node);
    syncsymbol(c);        
    root = deleteastnodeandedge(node, root);
    c->g_arg_count++;
    return 0;
}

/* Reserve for future usage */
int Gram_Prog_synthesis(struct astnode *node, struct si *si) {
    return 0;
}

int (*code_syntheses[])(struct astnode *, struct si *) = {CC_code_synthesis, CD_code_synthesis, DT_code_synthesis, EX_code_synthesis, FW_code_synthesis, IN_code_synthesis, JJ_code_synthesis, JJR_code_synthesis, JJS_code_synthesis, LS_code_synthesis, MD_code_synthesis, NN_code_synthesis, NNS_code_synthesis, NNP_code_synthesis, NNPS_code_synthesis, PDT_code_synthesis, POS_code_synthesis, PRP_code_synthesis, PRP_POS_code_synthesis, RB_code_synthesis, RBR_code_synthesis, RBS_code_synthesis, RP_code_synthesis, SYM_code_synthesis, TO_code_synthesis, UH_code_synthesis, VB_code_synthesis, VBD_code_synthesis, VBG_code_synthesis, VBN_code_synthesis, VBP_code_synthesis, VBZ_code_synthesis, WDT_code_synthesis, WP_code_synthesis, WP_POS_code_synthesis, WRB_code_synthesis, Gram_Prog_synthesis, Gram_Rel_synthesis};

/* 
    semantic interpretation identification 
    this is a process to identify as many si as possible presenting in the meaning representation
    - parameter descriptions
        predicates  : a queue holding pointers of predicates present in an abstract syntax tree parsed from meaning representation
        silist      : a queue holding semantic interpretations parsed from standard semantic interpretation database
        cst         : a queue holding the compile time symbols, aka the identitiers in the meaning representation
*/
void siidentification() {
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
        // reset the si pointer for checking a node dequeue from the predicates queue
        si = NULL;
        if (selfSI[node->syntax] == 0) {
            push(tmp, node);
        } else {
            si = searchqueue(silist, node, __sicomparator);
            if (si == NULL) {
                if (node->isroot) {
                    fprintf(stderr, "Symbol error: Please provide the SI for predicate(%s)\n", node->token->symbol);
                    exit(-10);
                }
                __remove_all_children_cst__(node);
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
            } else if (node->syntax == NN || node->syntax >= Gram_Prog) {
                if (strcmp(si->args[0], "*") == 0) {
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

    // struc queue *childnodetypes;
    struct queue *si_q;
    while (!isempty(predicates)) {    
        node = (struct astnode*)dequeue(predicates);
        #if SIDEBUG
        printf("si identification: processing predicate %s(%s).\n", node->token->symbol, ptbsyntax2string(node->syntax));
        #endif
        if (selfSI[node->syntax] == 1) {
            si_q = q_searchqueue(silist, node, __sicomparator);
            si = NULL;
            if (si_q->count == 0) si = NULL;
            else if (si_q->count == 1) si = dequeue(si_q);
            else {
                // because there are more than one possible si
                // in order to get the most appropriate one, we filter the queue to get the one that has arg_types matching the current child node types                
                si = searchqueue(si_q, node, __argtype_si_comparator);                                
            }                 
            deallocatequeue(si_q, NULL);
            if (si == NULL) {
                #if SIDEBUG
                printf("si identification: no si for predicate %s(%s) is found\n", node->token->symbol, ptbsyntax2string(node->syntax));
                #endif
                node->type = NoSI;
            } else {
                int x = (*code_syntheses[node->syntax])(node, si);
                if (x != 0) {
                    #if SIDEBUG
                    printf("si identification: predicate %s(%s) code synthesis is not done in this loop.\n", node->token->symbol, ptbsyntax2string(node->syntax));
                    #endif
                    enqueue(predicates, (void*)node);
                }
            }  
        } else {
            int x = (*code_syntheses[node->syntax])(node, si);
            // originally, self defined SI (e.g. CD) will not have the case that cannot be synthesised
            //   however, we have added new rule for grammar relation, such relation requires all children nodes to be synthesised
            if (x != 0) {
                #if SIDEBUG
                printf("si identification: predicate %s(%s) code synthesis is not done in this loop.\n", node->token->symbol, ptbsyntax2string(node->syntax));
                #endif
                enqueue(predicates, (void*)node);
            }
        }
        #if ASTDEBUG
        showast(root, 0);
        showqueue(cst, showcstsymbol);
        fflush(stdout);
        #endif
    }
    #if SIDEBUG
    printf("si identification finished\n");
    #endif
}

void opresolution() {
    struct astnode *node, *left, *right;    
    while (operators->count > 0) {
        node = (struct astnode*)dequeue(operators);
        left = getastchild(node, 0);
        right = getastchild(node, 1);
        char *s = NULL;

        if (left->type == Synthesised && right->type == Synthesised) {
            /* there can be one of the synthesised node has unresolved grammar argument */
            struct cstsymbol *cleft = searchsymbolbyref(left), *cright = searchsymbolbyref(right);
            if (cleft->g_arg_count < ((struct si*)cleft->si_ptr)->g_arg_count && cright->g_arg_count < ((struct si*)cright->si_ptr)->g_arg_count) {
                fprintf(stderr, "The predicates' grammar arguments are not resolved\n");
                exit(-15);
            } else if (cleft->g_arg_count == ((struct si*)cleft->si_ptr)->g_arg_count && cright->g_arg_count == ((struct si*)cright->si_ptr)->g_arg_count) {
                /* both ast node semantic interpretations have been synthesised */
                /* therefore, the operator serves as a comparator */
                s = __combine_3_strings__(left->token->symbol, "==", right->token->symbol);
            } else {
                struct astnode *template, *sub;
                struct cstsymbol *ctemplate;
                if (cleft->g_arg_count < ((struct si*)cleft->si_ptr)->g_arg_count) {
                    template = left;
                    sub = right;
                    ctemplate = cleft;
                } else {
                    template = right;
                    sub = left;
                    ctemplate = cright;
                }
                struct si *_si_ptr = ctemplate->si_ptr;
                char *tmp = __combine_3_strings__("(", _si_ptr->g_args[ctemplate->g_arg_count], ")");
                s = strrep(template->token->symbol, tmp, sub->token->symbol);
                free(tmp);
                ctemplate->g_arg_count++;
                ctemplate = updatecstsymbol(s, template);      
                __remove_all_children_cst__(node);
                syncsymbol(ctemplate);        
                // root = deleteastnodeandedge(node, root);                
            }
        } else {
            /* if node A has node type == Template, the symbol of node A is aliased as the symbol of node B */
            /* such that, semantic interpretation is synthesised from substituting the semantic interpretation of node B into the template in node A */
            if (left->type == Template && right->type == Synthesised) {
                s = __subsititute_synthesised_into_template__(right, left);
            } else if (left->type == Synthesised && right->type == Template) {                
                s = __subsititute_synthesised_into_template__(left, right);
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
                si->arg_count == countastchildren(node)) 
        return 0;
    else
        return 1;
}

int __argtype_si_comparator(void *_si, void *_astnode) {
    struct si* si = (struct si*)_si;
    struct astnode *node = (struct astnode*)_astnode, *tmp;
    struct cstsymbol *c = NULL;
    for (int i = 0; i < si->arg_count; ++i) {
        tmp = getastchild(node, i);
        c = searchsymbolbyref(tmp);
        if (si->arg_types[i] != c->type) return 1;
        else continue;
    }
    return 0;
}

int search_syntax(struct si* si, enum ptbsyntax ptb) {
    for (int i = 0; i < si->syntax_count; ++i) {
        if (si->syntax[i] == ptb) {
            return 0;
        }
    }
    return 1;
}

void showsi(void *_si) {
    struct si *si = (struct si*)_si;
    printf("==========================Semantic interpretations: =========================\n");
    printf("Term          Syntactic Category       Arity     Arguments    Interpretation\n");
    printf("Term: %s   Syntactic Category: ", si->term);        
    for (int j = 0; j < si->syntax_count; ++j) {
        printf("%s ", ptbsyntax2string(si->syntax[j]));
    }
    printf("  Arity: %d   Arguments: ", si->arg_count);
    for (int j = 0; j < si->arg_count; ++j) {
        printf("%s ", si->args[j]);
    }
    printf("   %s(%p)\n", si->interpretation, (void*)si->interpretation);
    printf("============================================================================\n");
}

void deallocatesi(void *tmp) {    
    struct si *si = (struct si*)tmp;
    for (int i = 0; i < si->arg_count; ++i) {
        free(si->args[i]);
    }
    for (int i = 0; i < si->g_arg_count; ++i) {
        if (si->g_args[i])
            free(si->g_args[i]);
    }
    free(si->syntax);
    if (si->interpretation)
        free(si->interpretation);
    free(si->term);
    free(si);
}

