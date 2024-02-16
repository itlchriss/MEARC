#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ast.h"
#include "util.h"
#include "cg.h"

static char *connective_code[] = { "&&", "||", "<==>", "==>" };

void printree(struct astnode *node, FILE *s, int *haserror) {
    char *formatstr = "%s\n";
    if (node->isnegative == 1) {
        formatstr = "!(%s)\n";
    }
    switch(node->type) {
        case Synthesised:      
            // if (node->cstptr->datatype != None) {
            //     fprintf(s, "%s\n", "GLR results");
            // }
            for (int i = 0; i < node->si_q->count; ++i) {                
                fprintf(s, formatstr, (char *)gqueue(node->si_q, i));             
            }            
            break;
        default:
            #if CGDEBUG
            fprintf(stderr, "walktree: Unknown type(%s) encountered for symbol(%s).\n");
            #endif
            (*haserror)++;
    }        
}

void walktree(struct astnode *node, FILE *s, int *haserror) {
    if (*haserror > 0) {
        return;
    } else {
        if (node == NULL) {
            (*haserror)++;
            return;
        }
        if (node->type != Connective) {
            printree(node, s, haserror);        
        } else {
            fprintf(s, "(");
            walktree((struct astnode *)getastchild(node, 0), s, haserror);
            fprintf(s, ")");
            fprintf(s, " %s ", connective_code[node->conntype]);
            fprintf(s, "(");
            walktree((struct astnode *)getastchild(node, 1), s, haserror);
            fprintf(s, ")");
        }
    }
}

void output(struct astnode *root) {
    char *buffer;
    size_t size;
    FILE *stream = open_memstream(&buffer, &size);
    /* 0 indicates no error */
    int haserror = 0;
    walktree(root, stream, &haserror);
    if (haserror == 0) {
        fflush(stream);
        /* TODO: add configuration of ensures and requires, and open bracket */
        printf("%s\n", buffer);
        /* TODO: add close bracket and colon (;) */
    } else {
        fprintf(stderr, "Failed\n");
    }
    fclose(stream);
}


