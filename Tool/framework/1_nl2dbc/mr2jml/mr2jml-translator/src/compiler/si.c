#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "util.h"
#include "si.h"
#include "ast.h"

int search_syntax(struct si*, enum ptbsyntax);
int sicomparator(void *, void *);
void Nseries_semantic_synthesis(struct astnode *, struct si *, struct queue *);
void __synthesis_post_process__(struct astnode *, enum astnodetype, char *);



void CC_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void CD_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void DT_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void EX_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void FW_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void IN_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void JJ_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void JJR_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void JJS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void LS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void MD_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void NN_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { Nseries_semantic_synthesis(node, si, cst); }
void NNS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void NNP_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void NNPS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void PDT_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void POS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void PRP_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void PRP_POS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void RB_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void RBR_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void RBS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void RP_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void SYM_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void TO_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void UH_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void VB_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void VBD_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void VBG_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void VBN_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void VBP_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void VBZ_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void WDT_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void WP_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void WP_POS_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void WRB_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) { }
void (*semantic_syntheses[])(struct astnode *, struct si *, struct queue *) = {CC_semantic_synthesis, CD_semantic_synthesis, DT_semantic_synthesis, EX_semantic_synthesis, FW_semantic_synthesis, IN_semantic_synthesis, JJ_semantic_synthesis, JJR_semantic_synthesis, JJS_semantic_synthesis, LS_semantic_synthesis, MD_semantic_synthesis, NN_semantic_synthesis, NNS_semantic_synthesis, NNP_semantic_synthesis, NNPS_semantic_synthesis, PDT_semantic_synthesis, POS_semantic_synthesis, PRP_semantic_synthesis, PRP_POS_semantic_synthesis, RB_semantic_synthesis, RBR_semantic_synthesis, RBS_semantic_synthesis, RP_semantic_synthesis, SYM_semantic_synthesis, TO_semantic_synthesis, UH_semantic_synthesis, VB_semantic_synthesis, VBD_semantic_synthesis, VBG_semantic_synthesis, VBN_semantic_synthesis, VBP_semantic_synthesis, VBZ_semantic_synthesis, WDT_semantic_synthesis, WP_semantic_synthesis, WP_POS_semantic_synthesis, WRB_semantic_synthesis};


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
    for (int i = 0; i < predicates->count; ++i) {
        node = (struct astnode*)gqueue(predicates, i);
        si = searchqueue(silist, node, sicomparator);
        if (si == NULL) {
            #if DSIDEBUG
            printf("si identification: no si for predicate %s is found\n", node->token->symbol);
            #endif
            node->type = NoSI;
        } else {
            (*semantic_syntheses[node->syntax])(node, si, cst);
            __synthesis_post_process__(node, Resolved, si->interpretation);
        }  
    }
}

void __synthesis_post_process__(struct astnode *node, enum astnodetype type, char *si) {    
    deleteastchildren(node);
    free(node->token->symbol);
    node->token->symbol = (char*) strdup(si);
    node->type = type;
}

// char* __semantic_synthesis_process__(struct astnodelist *children, struct si *si) {
//     char *semantic;

//     return semantic;
// }

void Nseries_semantic_synthesis(struct astnode *node, struct si *si, struct queue *cst) {
    char *s = si->interpretation;    
    if (strcmp(s, "\\param") == 0) {
        /* indicating the next predicate with the same indentifier must be a parameter name */
        /* potential research problem. can we infer or guess the parameter name if the word used in the sentence is not the exact parameter name? */
    } else {
        struct astnode *child = getastchild(node, 0); 
        struct cstsymbol *c = updatecstsymbol(cst, child->token->symbol, s);
        syncsymbol(c);
    }           
}

int sicomparator(void *_si, void *_astnode) {
    struct si* si = (struct si*)_si;
    struct astnode *node = (struct astnode*)_astnode;
    if (strcmp(node->token->symbol, si->term) == 0 &&
                search_syntax(si, node->syntax) == 0) 
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

