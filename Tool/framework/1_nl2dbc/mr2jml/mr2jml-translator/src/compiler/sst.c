#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "util.h"
#include "sst.h"
#include "ast.h"

// unsigned long long syn2binary(char *syntax);
// unsigned long long syn2bitmask(char *syntax);
enum ptbsyntax getptbsyntax(char *syntax);
// int *getptblist(char *syntax);
int searchsyntax(struct sstnode *node, int v);
struct sstnode* newsstnode(char *symbol, char *syntax, int arity, char *stdpredicate);

// struct arg* getarg(struct queue *args, int index) {
//     return (struct arg*)gqueue(args, index);
// }

// struct arg* newarg(char *input) {
//     struct arg *new = (struct arg*)malloc(sizeof(struct arg));
//     if (strcmp(input, "*") == 0 || strcmp(input, "f(x)") == 0) {
//         new->name = input;
//         new->syntax = NULL;
//         new->ssize = 0;
//     } else {
//         char *s = (char*)strdup(input);
//         char *pos, *sep = "_", *tmp = strtok_r(s, sep, &pos);
//         new->name = strdup(tmp);
//         tmp = strtok_r(NULL, sep, &pos);        
//         // tmp = strtok_r(NULL, sep, &pos);
//         char *_syntax = strdup(tmp);
//         ++_syntax;
//         _syntax[strlen(_syntax) - 1] = '\0';
//         int size = 1;
//         for (int i = 0; i < strlen(_syntax); ++i) {
//             if (_syntax[i] == '|') ++size;
//         }
//         new->syntax = (int*)malloc(sizeof(int) * size);
//         sep = "|";
//         tmp = strtok_r(_syntax, sep, &pos);
//         for (int i = 0; i < size; ++i) {
//             new->syntax[i] = getptbsyntax(tmp);
//             tmp = strtok_r(NULL, sep, &pos);            
//         }
//         new->ssize = size;
//     }
//     #if DEBUG
//     printf("name: %s nsyntax: %d\n", new->name, new->ssize);
//     #endif
//     return new;
// }

// struct arg* clonearg(struct arg* _arg) {
//     struct arg *new = (struct arg*)malloc(sizeof(struct arg));
//     new->name = (char*)strdup(_arg->name);
//     new->ssize = _arg->ssize;
//     new->syntax = (int*)malloc(sizeof(int) * new->ssize);
//     for (int i = 0; i < new->ssize; ++i) {
//         new->syntax[i] = _arg->syntax[i];
//     }
//     return new;
// }

struct sstnode* newsstnode(char *symbol, char *syntax, 
int arity, char *stdpredicate) {
    struct sstnode *new = (struct sstnode*) malloc (sizeof(struct sstnode));
    new->symbol = (char*)strdup(symbol);
    new->arity = arity;

    int size = 1, i;
    for (i = 0; i < strlen(syntax); ++i) {
        if (syntax[i] == ',') ++size;
    }
    new->syntax = (int*) malloc (sizeof(int) * size);
    char *pos, *sep = ",", *tmp = strtok_r(syntax, sep, &pos);
    i = 0;
    while (tmp != NULL) {
        tmp = (char*)strdup(tmp);
        new->syntax[i] = getptbsyntax(tmp);
        free(tmp);
        tmp = strtok_r(NULL, sep, &pos);
        ++i;
    }    
    new->syntaxsize = size;
    new->next = NULL;
    // new->symbolhash = sdbm(symbol);

    //separating the args and template. the separator is a colon
    //args is in format of (arg_1, args_2, ... , arg_n)
    //number of args must be equal to arity, or a syntax error is generated
    // new->stdpredicate = (char*)strdup(stdpredicate);
    sep = ":";
    char *pstr = (char*)strdup(stdpredicate);
    tmp = strtok_r(pstr, sep, &pos);
    if (tmp == NULL && tmp[0] != '(' && tmp[strlen(tmp) - 1] != ')') {
        printf("Syntax Error in sst.template while processing separation of arguments and templates for %s. The format of the std predicate is (arg_1, arg_2, ... , arg_n):predicate_template", symbol);
        return NULL;
    }
    char *args = (char*) strdup (tmp);
    args++;
    args[strlen(args) - 1] = '\0';
    tmp = strtok_r(NULL, sep, &pos);
    if (tmp == NULL) {
        printf("Syntax Error in sst.template while processing arguments for %s. The format of the std predicate is (arg_1, arg_2, ... , arg_n):predicate_template", symbol);
        return NULL;
    }
    new->template = (char*) strdup (tmp);
    size = 1;
    for (i = 0; i < strlen(args); ++i) {
        if (args[i] == ',') ++size;
    }    
    new->args = (char**) malloc (sizeof(char*) * size);
    // new->_args = initqueue();
    sep = ",";
    tmp = strtok_r(args, sep, &pos);
    for (i = 0; i < size; ++i) {
        if (tmp == NULL) {
            printf("Syntax Error in sst.template. Unexpected null in parsing the arguments of %s", new->symbol);            
        } else {
            // enqueue(new->_args, (void*)newarg(strdup(tmp)));
            new->args[i] = (char*)strdup (tmp);
            tmp = strtok_r(NULL, sep, &pos);
        }
    }
    new->argsize = size;
    if (pstr)
        free(pstr);    
    return new;
}

void addsstnode(struct sstnode *sst, struct sstnode *new) {
    struct sstnode *tmp = sst;
    while (tmp->next != NULL) {
        tmp = tmp->next;
    }
    tmp->next = new;
}

int searchsyntax(struct sstnode *node, int v) {
    //TODO: change this search to binary search    
    int size = node->syntaxsize, i = 0;
    for (i = 0; i < size; ++i) {
        if (node->syntax[i] == v) return v;
    }
    return -1;
}

struct queue *getsstsymbols(struct sstnode *sst, char *symbol, char *syntax, int arity) {
    struct queue *q = initqueue();        
    int ptbsyntax = getptbsyntax(syntax);
    // unsigned long long symbolhash = sdbm(symbol);
    struct sstnode *tmp = sst;
    while (tmp != NULL) {
        if (strcmp(tmp->symbol, symbol) == 0 && searchsyntax(tmp, ptbsyntax) > 0
            && (tmp->arity == 0 || tmp->arity == arity)) {
            enqueue(q, (void*)tmp);
        }
        tmp = tmp->next;
    }

    if (ptbsyntax == CD && q->count == 0) {
        // cardinal numbers are dynamically generated
        // we check the count is zero in case the logic uses the same number more than once
        // TODO: there can be a problem if the number has several digits
        char *template = (char*) malloc (sizeof(char) * (4 + strlen(symbol) + 1));
        strcpy(template, "(*):");
        strcat(template, symbol);
        tmp = newsstnode(symbol, syntax, 1, template);
        addsstnode(sst, tmp);
        enqueue(q, (void*)tmp);
    }
    return q;
}

struct sstnode* getsymbolfromqueue(struct queue *sstlist, int arity) {
    struct queuenode *tmp = sstlist->q;
    struct sstnode* ptr;
    while ((tmp = tmp->next) != NULL) {
        ptr = (struct sstnode*)tmp->datanode;
        if (ptr->arity == arity) return ptr;
    }
    return NULL;
}

enum ptbsyntax getptbsyntax(char *syntax) {
    if (strcmp(syntax, "cc") == 0) return CC;
    else if (strcmp(syntax, "cd") == 0) return CD;
    else if (strcmp(syntax, "dt") == 0) return DT;
    else if (strcmp(syntax, "ex") == 0) return EX;
    else if (strcmp(syntax, "fw") == 0) return FW;
    else if (strcmp(syntax, "in") == 0) return IN;
    else if (strcmp(syntax, "jj") == 0) return JJ;
    else if (strcmp(syntax, "jjr") == 0) return JJR;
    else if (strcmp(syntax, "jjs") == 0) return JJS;
    else if (strcmp(syntax, "ls") == 0) return LS;
    else if (strcmp(syntax, "md") == 0) return MD;
    else if (strcmp(syntax, "nn") == 0) return NN;
    else if (strcmp(syntax, "nns") == 0) return NNS;
    else if (strcmp(syntax, "nnp") == 0) return NNP;
    else if (strcmp(syntax, "nnps") == 0) return NNPS;
    else if (strcmp(syntax, "pdt") == 0) return PDT;
    else if (strcmp(syntax, "prp") == 0) return PRP;
    else if (strcmp(syntax, "pos") == 0) return POS;
    else if (strcmp(syntax, "prp_pos") == 0) return PRP_POS;
    else if (strcmp(syntax, "rb") == 0) return RB;
    else if (strcmp(syntax, "rbr") == 0) return RBR;
    else if (strcmp(syntax, "rbs") == 0) return RBS;
    else if (strcmp(syntax, "rp") == 0) return RP;
    else if (strcmp(syntax, "sym") == 0) return SYM;
    else if (strcmp(syntax, "to") == 0) return TO;
    else if (strcmp(syntax, "uh") == 0) return UH;
    else if (strcmp(syntax, "vb") == 0) return VB;
    else if (strcmp(syntax, "vbd") == 0) return VBD;
    else if (strcmp(syntax, "vbg") == 0) return VBG;
    else if (strcmp(syntax, "vbn") == 0) return VBN;
    else if (strcmp(syntax, "vbp") == 0) return VBP;
    else if (strcmp(syntax, "vbz") == 0) return VBZ;
    else if (strcmp(syntax, "wdt") == 0) return WDT;
    else if (strcmp(syntax, "wp") == 0) return WP;
    else if (strcmp(syntax, "wp_pos") == 0) return WP_POS;
    else if (strcmp(syntax, "wrb") == 0) return WRB;
    else return 0;
}


void showsst(struct sstnode *sst) {
    struct sstnode *tmp = sst;
    // printf("symbol, mask, arity, stdpred, symhash\n");
    // while (tmp != NULL) {
    //     printf("%s, %llu, %d, %s, %llu\n", 
    //     tmp->symbol, tmp->validsyntaxmask, tmp->arity, tmp->stdpredicate, tmp->symbolhash);
    //     tmp = tmp->next;
    // }
    int size, i;
    printf("symbol, arity, stdpred, symhash, syntax, args, template\n");
    while (tmp != NULL) {
        printf("%s, %d, %s, %llu   ", 
        tmp->symbol, tmp->arity, tmp->template, tmp->symbolhash);
        size = tmp->syntaxsize;        
        for (i = 0; i < size; ++i) {
            printf("%d", tmp->syntax[i]);
            if (i != size - 1) {
                printf(",");
            }
        }
        // for (i = 0; i < tmp->arity; ++i) {
        //     printf("%s", tmp->args[i]);
        //     if (i != tmp->arity - 1) {
        //         printf(",");
        //     }
        // }
        // for (i = 0; i < tmp->_args->count; ++i) {
        //     struct arg *arg = getarg(tmp->_args, i);
        //     printf("name: %s, n allowed syntax: %d\n", arg->name, arg->ssize);
        //     if (i != tmp->_args->count - 1) {
        //         printf(",");
        //     }
        // }
        printf("\n");
        tmp = tmp->next;
    }
}

void deallocatesst(struct sstnode *sst) {
    struct sstnode *tmp;
    // int i = 0;
    while (sst != NULL) {
        tmp = sst;
        if (sst->symbol)
            free(sst->symbol);
        if (sst->syntax)
            free(sst->syntax);
        for (int i = 0; i < sst->argsize; ++i) {
            if (sst->args[i])
                free(sst->args[i]);
        }
        if (sst->args)
            free(sst->args);
        if (sst->template)
            free(sst->template);
        // if (sst->_args) {
        //     while (sst->_args->count > 0) {
        //         struct arg *_arg = dequeue(sst->_args);
        //         if (_arg->name)
        //             free(_arg->name);
        //         if (_arg->syntax)
        //             free(_arg->syntax);
        //         free(_arg);
        //     }
        //     deallocatequeue(sst->_args);
        // }
        sst = sst->next;
        free(tmp);
    }
    if (sst)
        free(sst);
}
