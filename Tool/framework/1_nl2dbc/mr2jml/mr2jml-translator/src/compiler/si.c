#include <string.h>
#include <stdlib.h>
#include "si.h"
#include "ast.h"

void setargs(struct si *si, char *data) {
    
}

/* 
    semantic interpretation identification 
    this is a process to identify as many si as possible presenting in the mr
*/
void siidentification(struct astnode *root, struct queue *silist) {

}

void deallocatesilist(struct queue *silist) {
    struct si *si = NULL;
    while (silist->count > 0) {
        si = (struct si*)dequeue(silist);
        free(si);
    }
}

void deallocatesi(struct si *si) {
    for (int i = 0; i < si->arg_count; ++i) {
        free(si->args[i]);
    }
    free(si->interpretation);
    free(si->symbol);
    free(si);
}

