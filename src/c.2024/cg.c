#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ast.h"
#include "util.h"
#include "cg.h"

static char *connective_code[] = { "&&", "||", "<==>", "==>" };

static int quantify_variable = 105;

/* 
    a function deciding the length representation in java
    by default we consider it is a collection, which uses size() to access the size
    because array is a sequence that is not extendable,
        and String is an immutable reference, so it is also non-extendable
    TO BE DONE: we can also make it a bit further to support custom class/interface, 
        such that if such class/interface has a function to access its size, 
        developer can write an SI to support it
*/
char *get_length_str(enum reference_datatype r) {
    switch (r)
    {
    case Array:
        return "length";        
    case String:
        return "length()";
    default:
        return "size()";
    }
}


void printree(struct astnode *node, FILE *s, int *haserror) {
    char *formatstr = "%s\n";
    if (node->isnegative == 1) {
        formatstr = "!(%s)\n";
    }
    switch(node->type) {
        case Synthesised:      
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
        switch (node->type) {
            case Connective:
                fprintf(s, "(");
                walktree((struct astnode *)getastchild(node, 0), s, haserror);
                fprintf(s, ")");
                fprintf(s, " %s ", connective_code[node->conntype]);
                fprintf(s, "(");
                walktree((struct astnode *)getastchild(node, 1), s, haserror);
                fprintf(s, ")");
                break;
            case Synthesised:
                printree(node, s, haserror);        
                break;
            case Quantifier:
                if (node->qtype != Quantifier_ForAll) (*haserror)++;
                else {
                    if (node->quantified_ranges->count == 0) {
                        fprintf(s, "\\ forall int %c; 0 <= %c < %s.%s; ",
                            (char)quantify_variable, 
                            (char)quantify_variable, 
                            node->token->symbol,
                            get_length_str(node->cstptr->datatype->r));
                    } else {
                        /* to be done for specified or multiple ranges */
                        
                    }
                }
                fprintf(s, "(");
                for (int i = 0; i < countastchildren(node); ++i) {
                    walktree((struct astnode *)getastchild(node, 0), s, haserror);
                    if (i == 1 && countastchildren(node) == 2) {
                        fprintf(s, " && ");
                    }
                }
                fprintf(s, ")");
                break;
            default:
                (*haserror)++;
                break;
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


