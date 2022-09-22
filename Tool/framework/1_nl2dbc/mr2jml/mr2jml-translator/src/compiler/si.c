#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "util.h"
#include "si.h"
#include "ast.h"

int search_syntax(struct si*, enum ptbsyntax);
int sicomparator(void *, void *);

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

int Vseries_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) {
    struct astnode *child;
    /* Verbs usually accepts 2 arguments (subject, object). Thus, verbs cannot be resolved before both arguments are resolved. */
    for (int i = 0; i < countastchildren(node); ++i) {
        child = getastchild(node, i);
        if (child->type != Resolved) {
            return 1;
        }
    }
    char *arg;
    char *s = (char*) strdup(si->interpretation), *tmp;
    for (int i = 0; i < countastchildren(node); ++i) {
        child = getastchild(node, i);
        arg = si->args[i];
        tmp = strrep(s, arg, child->token->symbol);
        free(s);
        s = tmp;
    }
    __replace_si_at_parent__(node, Resolved, s);
    return 0;
}


int Nseries_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) {
    char *s = si->interpretation;    
    __remove_all_children_cst__(cst, node);
    if (strcmp(s, "\\param") == 0) {
        /* indicating the next predicate with the same indentifier must be a parameter name */
        /* potential research problem. can we infer or guess the parameter name if the word used in the sentence is not the exact parameter name? */
    } else {
        if (strcmp(si->args[0], "(*)") == 0) {
            /* arguments do no effects to the semantic interpretation */
            /* therefore, directly replacing the semantic interpretation to all the places of identifiers */
            struct astnode *child = getastchild(node, 0); 
            struct cstsymbol *c = updatecstsymbol(cst, child->token->symbol, s);
            syncsymbol(c);
        }
    }           
    deleteastchild(node->parent, node);
    return 0;
}



int CC_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int CD_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int DT_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int EX_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int FW_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int IN_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int JJ_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int JJR_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int JJS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int LS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int MD_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int NN_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return Nseries_semantic_synthesis(node, si, cst); }
int NNS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int NNP_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int NNPS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int PDT_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int POS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int PRP_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int PRP_POS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int RB_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int RBR_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int RBS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int RP_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int SYM_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int TO_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int UH_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int VB_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return Vseries_semantic_synthesis(node, si, cst); }
int VBD_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int VBG_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int VBN_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int VBP_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int VBZ_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int WDT_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int WP_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int WP_POS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int WRB_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { return 0; }
int (*semantic_syntheses[])(struct astnode *, struct si *, struct queue *) = {CC_semantic_synthesis, CD_semantic_synthesis, DT_semantic_synthesis, EX_semantic_synthesis, FW_semantic_synthesis, IN_semantic_synthesis, JJ_semantic_synthesis, JJR_semantic_synthesis, JJS_semantic_synthesis, LS_semantic_synthesis, MD_semantic_synthesis, NN_semantic_synthesis, NNS_semantic_synthesis, NNP_semantic_synthesis, NNPS_semantic_synthesis, PDT_semantic_synthesis, POS_semantic_synthesis, PRP_semantic_synthesis, PRP_POS_semantic_synthesis, RB_semantic_synthesis, RBR_semantic_synthesis, RBS_semantic_synthesis, RP_semantic_synthesis, SYM_semantic_synthesis, TO_semantic_synthesis, UH_semantic_synthesis, VB_semantic_synthesis, VBD_semantic_synthesis, VBG_semantic_synthesis, VBN_semantic_synthesis, VBP_semantic_synthesis, VBZ_semantic_synthesis, WDT_semantic_synthesis, WP_semantic_synthesis, WP_POS_semantic_synthesis, WRB_semantic_synthesis};

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
    while (predicates->count > 0) {
        node = (struct astnode*)dequeue(predicates);
        #if SIDEBUG
        printf("si identification: processing predicate %s(%s).\n", node->token->symbol, ptbsyntax2string(node->syntax));
        #endif
        si = searchqueue(silist, node, sicomparator);
        if (si == NULL) {
            #if SIDEBUG
            printf("si identification: no si for predicate %s(%s) is found\n", node->token->symbol, ptbsyntax2string(node->syntax));
            #endif
            node->type = NoSI;
        } else {
            int x = (*semantic_syntheses[node->syntax])(node, si, cst);
            if (x != 0) {
                enqueue(predicates, (void*)node);
            }
        }  
    }
}

void opresolution(struct queue *operators, struct queue *cst) {
    struct astnode *node, *left, *right;    
    while (operators->count > 0) {
        node = (struct astnode*)dequeue(operators);
        left = getastchild(node, 0);
        right = getastchild(node, 1);
        char *s;
        if (left->type == Resolved && right->type == Resolved) {
            int _size = strlen(left->token->symbol) + strlen(right->token->symbol) + 2 + 1;
            s = (char*) malloc(sizeof(char) * _size);
            memcpy(s, left->token->symbol, strlen(left->token->symbol) + 1);
            append(s, "==");
            append(s, right->token->symbol);
            s[_size - 1] = '\0';
        } else {

        }
        __replace_si_at_parent__(node, Resolved, s);
    }    
}



int sicomparator(void *_si, void *_astnode) {
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

