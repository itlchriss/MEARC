#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "util.h"
#include "si.h"
#include "ast.h"

extern struct astnode *root;

int search_syntax(struct si*, enum ptbsyntax);
int __sicomparator(void *, void *);

int selfSI[] = { 1, 0, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 };

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

int Jseries_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) {
    struct astnode *child;
    for (int i = 0; i < countastchildren(node); ++i) {
        child = getastchild(node, i);
        if (child->type != Synthesised) return 1;
    }
    char *s = __obtain_si_from_subtree__(node, si);
    if (countastchildren(node) == 1) {
        __replace_si_at_parent__(node, Template, s);
    } else {
        __replace_si_at_parent__(node, Synthesised, s);
        __remove_all_children_cst__(cst, node);
    }
    free(s);
    return 0;
}

int Vseries_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) {
    struct astnode *child;
    /* Verbs usually accepts 2 arguments (subject, object). Thus, verbs cannot be resolved before both arguments are resolved. */
    for (int i = 0; i < countastchildren(node); ++i) {
        child = getastchild(node, i);
        if (child->type != Synthesised) {
            return 1;
        }
    }
    char *s = __obtain_si_from_subtree__(node, si);
    __replace_si_at_parent__(node, Synthesised, s);
    free(s);
    return 0;
}


int Nseries_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) {
    char *s = si->interpretation;;    
    __remove_all_children_cst__(cst, node);
    struct astnode *child = getastchild(node, 0);    
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
        } else {
            /* argument does affect the semantic interpretation */
            /* therefore, the semantic interpretation of the subtree requires code synthesis */
            s = __obtain_si_from_subtree__(node, si);
        }
        struct cstsymbol *c = updatecstsymbol(cst, child->token->symbol, s);
        syncsymbol(c);
    }           
    root = deleteastnodeandedge(node, root);
    return 0;
}



int CC_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int CD_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { 
    char *s = node->token->symbol;    
    __remove_all_children_cst__(cst, node);
    struct astnode *child = getastchild(node, 0); 
    #if SIDEBUG
    printf("CD_code_synthesis: si(%s) and child token(%s)\n", s, child->token->symbol);
    #endif        
    struct cstsymbol *c = updatecstsymbol(cst, child->token->symbol, s);
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
    if (strcmp(si->interpretation, "\\sub(x)2(y)") == 0) {
        char *s;
        struct cstsymbol *c;
        if (x->type == Template) {
            s = __subsititute_synthesised_into_template__(y, x, cst);
            c = updatecstsymbol(cst, x->token->symbol, s);
        } else {
            s = __subsititute_synthesised_into_template__(x, y, cst);
            c = updatecstsymbol(cst, y->token->symbol, s);
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
int NNS_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int NNP_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int NNPS_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int PDT_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int POS_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int PRP_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int PRP_POS_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int RB_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) {
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
int VBD_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int VBG_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { 
    return Vseries_code_synthesis(node, si, cst);
}
int VBN_code_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
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
    // while (predicates->count > 0) {
    while (!isempty(predicates)) {    
        node = (struct astnode*)dequeue(predicates);
        #if SIDEBUG
        printf("si identification: processing predicate %s(%s).\n", node->token->symbol, ptbsyntax2string(node->syntax));
        #endif
        if (selfSI[node->syntax] == 1) {
            si = searchqueue(silist, node, __sicomparator);
            if (si == NULL) {
                #if SIDEBUG
                printf("si identification: no si for predicate %s(%s) is found\n", node->token->symbol, ptbsyntax2string(node->syntax));
                #endif
                node->type = NoSI;
            } else {
                int x = (*code_syntheses[node->syntax])(node, si, cst);
                if (x != 0) {
                    enqueue(predicates, (void*)node);
                }
            }  
        } else {
            (*code_syntheses[node->syntax])(node, si, cst);
        }
        #if ASTDEBUG
        showast(root, 0);
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
                si->arg_count == countastchildren(node)) 
        return 0;
    else
        return 1;
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
    printf("   %s\n", si->interpretation);
    printf("============================================================================\n");
}

void deallocatesi(void *tmp) {    
    struct si *si = (struct si*)tmp;
    for (int i = 0; i < si->arg_count; ++i) {
        free(si->args[i]);
    }
    free(si->syntax);
    free(si->interpretation);
    free(si->term);
    free(si);
}

