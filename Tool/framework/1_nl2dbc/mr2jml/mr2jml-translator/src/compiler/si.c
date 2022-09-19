#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "util.h"
#include "si.h"
#include "ast.h"


/* 
    semantic interpretation identification 
    this is a process to identify as many si as possible presenting in the meaning representation
    - parameter descriptions
        predicates: a queue holding pointers of predicates present in an abstract syntax tree parsed from meaning representatin
        silist:     a queue holding semantic interpretations parsed from standard semantic interpretation database
*/
void siidentification(struct queue *predicates, struct queue *silist) {
    struct astnode *node;
    struct si *si;
    for (int i = 0; i < predicates->count; ++i) {
        node = (struct astnode*)gqueue(predicates, i);
        for (int j = 0; j < silist->count; ++j) {
            si = (struct si*)gqueue(silist, j);
            if (
                strcmp(node->token->symbol, si->term) == 0 &&
                node->syntax == si->syntax
            ) {
                /* semantic interpretation identified, process semantic synthesis */

            }
        }
    }
}

void showsilist(struct queue *silist) {
    struct si* si;
    printf("===========Semantic interpretations in the runtime are: ====================\n");
    printf("Term          Syntactic Category       Arity     Arguments    Interpretation\n");
    for (int i = 0; i < silist->count; ++i) {
        si = (struct si*)gqueue(silist, i);
        printf("%s        %d                       %d       ", si->term, si->syntax, si->arg_count);
        for (int j = 0; j < si->arg_count; ++j) {
            printf("%s ", si->args[j]);
        }
        printf("    %s\n", si->interpretation);
    }
    printf("============================================================================\n");
}

void deallocatesilist(struct queue *silist) {
    struct si *si = NULL;
    while (silist->count > 0) {
        si = (struct si*)dequeue(silist);
        free(si);
    }
    free(silist);
}

void deallocatesi(struct si *si) {
    for (int i = 0; i < si->arg_count; ++i) {
        free(si->args[i]);
    }
    free(si->interpretation);
    free(si->term);
    free(si);
}

