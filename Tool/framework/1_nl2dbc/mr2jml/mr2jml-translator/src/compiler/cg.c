#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "cg.h"
#include "dst.h"
#include "ast.h"
#include "util.h"
#include "sem.h"

void throwcgerror(char *msg);
void donodecg(struct astnode *node);

void walksubtree(struct astnode *subtreeroot) {    
    struct queue *nodequeue = initqueue();

    struct astnode *node;
    enqueue(nodequeue, subtreeroot);

    int print = 1, not = 0;
    fprintf(stderr, "(");
    while (!isempty(nodequeue)) {
        node = (struct astnode*) dequeue(nodequeue);
        print = 1;
        switch(node->type) {
            // TODO: distinguishing negative sign
            // case Exists:                        
            // case ForAll:
            // print = 1;
            // break;            
            // case NotExists:
            // case NotForAll:
            // not = 1;
            // break;
            case Resolved:
            if (strlen(node->token->symbol) > 0) {
                if (node->isnegative) {
                    fprintf(stderr, "!(%s)", node->token->symbol);
                    not = 1;
                } else {                
                    fprintf(stderr, "%s", node->token->symbol);
                }
                fflush(stderr);
                print = 0;
            }
            break;
            default:
            fprintf(stderr, "Unknown type of ast node (%s) with type (%d) encountered in code generation...\n", node->token->symbol, node->type);
        }        
        struct astnode *child;
        for (int i = 0; i < countastchildren(node); ++i) {
            child = getastchild(node, i);
            if (not == 1 && strlen(child->token->symbol) > 0) {
                prepend(child->token->symbol, "!(");
                append(child->token->symbol, ")");
            }
            enqueue(nodequeue, (void*)child);
        }
        not = 0;
        if (!isempty(nodequeue) && strlen(node->token->symbol) > 0 && print == 0) fprintf(stderr, ";");
    }
    fprintf(stderr, ")");
    fflush(stderr);
    deallocatequeue(nodequeue);
}

void printree(struct astnode *node) {
    if (node->type != Connective &&
        node->type != NonTrivialConnective) {
        walksubtree(node);        
    } else {
        fprintf(stderr, "(");
        printree((struct astnode *)getastchild(node, 0));
        fprintf(stderr, " %s ", node->token->symbol);
        printree((struct astnode *)getastchild(node, 1));
        fprintf(stderr, ")");
    }
}

// for traversing the tree
void gencode(struct astnode *root, int mode) {
    if (mode == 1) {
        fprintf(stderr, "requires ");
    } else {
        fprintf(stderr, "ensures ");
    }

    printree(root);
    fprintf(stderr, ";\n");
    fflush(stdout);
}


void throwcgerror(char *msg) {
    fprintf(stderr, "Code generation error: %s\n", msg);
}


