#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ast.h"
#include "util.h"
#include "cg.h"

static char *connective_code[] = { "&&", "||", "<==>", "==>" };

void walktree(struct astnode *root, FILE *s, int *haserror) {
    struct queue *queue = initqueue();
    struct astnode *node;
    enqueue(queue, root);
    while (!isempty(queue)) {
        node = (struct astnode*) dequeue(queue);
        switch(node->type) {
            case Synthesised:
                if (node->isnegative == 1) {
                    fprintf(s, "!(%s)", node->token->symbol);
                } else {
                    fprintf(s, "%s", node->token->symbol);
                }
                break;
            default:
                #if CGDEBUG
                fprintf(stderr, "walktree: Unknown type(%s) encountered for symbol(%s).\n");
                #endif
                (*haserror)++;
                goto END;
        }        
    }
END:
    deallocatequeue(queue, NULL);
}

void printree(struct astnode *node, FILE *s, int *haserror) {
    if (*haserror > 0) {
        return;
    } else {
        if (node == NULL) {
            (*haserror)++;
            return;
        }
        if (node->type != Connective) {
            walktree(node, s, haserror);        
        } else {
            fprintf(s, "(");
            printree((struct astnode *)getastchild(node, 0), s, haserror);
            fprintf(s, ")");
            fprintf(s, " %s ", connective_code[node->conntype]);
            fprintf(s, "(");
            printree((struct astnode *)getastchild(node, 1), s, haserror);
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
    printree(root, stream, &haserror);
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


