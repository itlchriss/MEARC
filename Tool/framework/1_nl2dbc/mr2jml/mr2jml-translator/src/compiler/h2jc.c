#include <stdio.h>
#include <unistd.h>
#include "h2jc.h"
#include "cg.h"

extern FILE *yyin;


int countlines(FILE *fp);
int readDST(FILE *fp);
int readPSL(FILE *fp);

// counting the number of MR in single file
int c = 0;

// data structures
//  abstract syntax tree(s)
struct astnode **ast;  
//  predicate semantic library
struct sstnode *psl;    
//  dynamic symbol table
struct dstnode *dst;     
//  compile-time symbol table
struct queue **csts;     
//  predicate stack, marking the positions of the predicate nodes
struct queue **pred_queues;   
//  connective stack, marking the positions of the connective nodes
struct queue **conn_queues;  
//  temporary unreferenced table
//      this table is to mark the symbols in a single scope (subtree)
//      the aim is to check if the symbol name is used
//      if so, the symbol name is renamed and all the symbols using the same name stored in this table is renamed
struct queue *reftable; 
//  the method that a single runtime is going to check
//  this points to a dynamic symbol table that has the same method name as the input method name
struct dstnode *fdstptr = NULL;


//TODO: we have to store every queue per root node.
//      because, if we use the same queue and multiple sentences, predicates will be stores in the same
//      structure, and everything messes up.
int main(int argc, char** argv) { 
    int opt;
    char *specfile, *pslfile, *dstfile, *cpslfile, *mname;
    specfile = pslfile = dstfile = cpslfile = mname = NULL;
    while ((opt = getopt(argc, argv, ":f:p:d:c:m:")) != -1) {
        switch(opt) {
            case 'f':
            printf("input file: %s\n", optarg);
            specfile = optarg;
            break;
            case 'p':
            printf("predicate semantic library: %s\n", optarg);
            pslfile = optarg;
            break;
            case 'd':
            printf("dynamic symbol table: %s\n", optarg);
            dstfile = optarg;
            break;
            case 'c':
            printf("custom predicate semantics: %s\n", optarg);
            cpslfile = optarg;
            break;
            case 'm':
            printf("method name: %s\n", optarg);
            mname = optarg;
            break;
            case '?':
            fprintf (stderr,
                   "Unknown option character `\\x%x'.\n",
                   optopt);
            return 1;
            default:
            printf("-f filename    specifying the filename of the spec file\n");
            printf("-p filename    specifying the filename of the predicate semantic library\n");
            printf("-d filename    specifying the filename of the dynamic symbol table\n");
            printf("-c filename    specifying the filename of the custom predicate semantic from developer's annotations\n");
            printf("-m methodname  specifying the name of the method that the spec describes\n");
        }
    }
    if (specfile == NULL) {
        printf("Please specify the spec file by -f\n");
        return 1;
    } else if (dstfile == NULL) {
        printf("Please specify the dynamic symbol table by -d\n");
        return 1;
    } else if (pslfile == NULL) {
        printf("Please specify the predicate semantic library by -p\n");
        return 1;
    } else if (mname == NULL) {
        printf("Please specify the method name by -m\n");
        return 1;
    } 

    int done = 0, lines = 0;
    // parameters of the method
    //  symbols come from dynamic symbol table
    struct queue *params = NULL;
    FILE *fp = fopen(pslfile, "r");
    if (!fp) {
        printf("The predicate semantic library file cannot be opened...\n");
        goto END;
    }
    printf("Reading predicate semantic library from %s...\n", pslfile);
    done = readPSL(fp);
    fclose(fp);
    if (done == -1) {
        goto END;
    }
    if (cpslfile != NULL) {
        printf("Reading custom predicate semantics from %s...\n", cpslfile);
        fp = fopen(cpslfile, "r");
        if (!fp) {
            printf("The specified custom predicate semantic file cannot be opened...\n");
            goto END;
        }
        done = readPSL(fp);
        fclose(fp);
        if (done == -1) {
            goto END;
        }
    }    
    fp = fopen(dstfile, "r");
    if (!fp) {
        printf("The dynamic symbol table file cannot be opened...\n");
        goto END;
    }
    printf("Reading dynamic symbol table from %s...\n", dstfile);
    done = readDST(fp);
    fclose(fp);
    if (done == -1) {
        goto END;
    }

    fp = fopen(specfile, "r");
    if (!fp) {
        printf("The spec file path cannot be opened...\n");
        return 1;
    }
    lines = countlines(fp);
    if (lines <= 0) {
        printf("zero lines...do I have to do anything?\n");
        exit(1);
    }
    #if DEBUG
    printf("Printing predicate semantic library...\n");
    showsst(psl);
    printf("Printing dynamic symbol table...\n");
    showdst(dst);
    #endif
    printf("Number of MRs: %d\n", lines);    

    fdstptr = getdstsymbol(dst, mname);
    if (fdstptr == NULL || fdstptr->stype != Dst_SYM_Function) {
        printf("The method name is not found in the dynamic symbol table...\n");
        goto END;
    }    
    // acquire resources before parsing
    ast = (struct astnode **) malloc (sizeof(struct astnode *) * lines);
    // TODO: declaring structures according to the number lines
    /* cst = initqueue();     */
    // csts = (struct queue **) malloc (sizeof(struct queue *) * lines);
    csts = initcsts(lines);
    pred_queues = (struct queue **) malloc (sizeof(struct queue *) * lines);
    conn_queues = (struct queue **) malloc (sizeof(struct queue *) * lines);
    for (int i = 0; i < lines; ++i) {
        // csts[i] = initqueue();
        pred_queues[i] = initqueue();
        conn_queues[i] = initqueue();
    }
    /* pred_queue = initqueue(); */
    /* conn_queue = initqueue(); */
    reftable = initqueue();
    //
    fseek(fp, 0, SEEK_SET);
    yyin = fp;
    yyparse();
    fclose(fp);

    if (reftable->count > 0) {
        fprintf(stderr, "Symbols not referenced incorrect...Stopping...Please check with the MR...\n");        
        while (reftable->count > 0) {
            struct astnode *tmp = (struct astnode*)dequeue(reftable);
            fprintf(stderr, "Variable: %s\n", tmp->token->symbol);
        }
        goto END;
    }

    printf("Parsing completed...\n");
    // clear all the stdout buffer
    fflush(stdout);

    #if DEBUG
    for (int i = 0; i < c; ++i) {
        printf("Printing Abstract syntax tree # %d.................\n", i + 1);
        showast(ast[i], 0);
        printf("Printing Compile-time symbol table # %d............\n", i + 1);
        showcst(csts[i]);
        printf("Printing Predicate queue information of AST # %d...\n", i + 1);
        for (int j = 0; j < pred_queues[i]->count; ++j) {
            printf("%s %d, ", 
                ((struct astnode *)gqueue(pred_queues[i], j))->token->symbol, 
                ((struct astnode *)gqueue(pred_queues[i], j))->priority);
        }
    }
    printf("\n");
    #endif

    // exit(-2);

    // sort the priorities
    for (int k = 0; k < c; ++k) {
        for (int i = 0; i < pred_queues[k]->count; ++i) {
            for (int j = pred_queues[k]->count - 1; j > i; --j) {
                if (((struct astnode *)gqueue(pred_queues[k], j))->priority < 
                    ((struct astnode *)gqueue(pred_queues[k], i))->priority) {
                    struct queuenode *tmp1 = _gqueue(pred_queues[k], j);
                    struct queuenode *tmp2 = _gqueue(pred_queues[k], i);
                    struct astnode *tmpnode = (struct astnode *)tmp1->datanode;
                    tmp1->datanode = tmp2->datanode;
                    tmp2->datanode = tmpnode;
                }
            }
        }
    }

    #if DEBUG
    for (int k = 0; k < c; ++k) {
        for (int i = 0; i < pred_queues[k]->count; ++i) {
            printf("%s %d, ", 
                ((struct astnode *)gqueue(pred_queues[k], i))->token->symbol, 
                ((struct astnode *)gqueue(pred_queues[k], i))->priority);
        }
    }
    printf("\n");
    #endif

    // TODO: support other modes, such as invariant    
    // indicating the current spec type
    int mode;
    //simplifying the ast and generate target code    
    if (specfile[strlen(specfile) - 4] == 'e') {
        // preconditions, generate 'requires'
        mode = 1;
    } else {
        // postconditions, generate 'ensures'
        mode = 2;
    }

    params = getparams(dst, fdstptr);
    for (int i = 0; i < c; ++i) { 
        ast[i] = simplifyast(ast[i], pred_queues[i], conn_queues[i], fdstptr, params);
        // Code generation
        gencode(ast[i], mode);
    }  
    
    END:

    // free resources
    /* deallocatedag(dag); */
    if (psl)
        deallocatesst(psl);
    if (dst)
        deallocatedst(dst);
    /* if (cst) */
        /* deallocatecst(cst); */
    if (csts) {
        deallocatecsts(csts, lines);
    }
    for (int i = 0; i < c; ++i) {
        if (ast[i])
            deallocateast(ast[i]);
    }
    /* if (pred_queue)
        deallocatequeue(pred_queue);
    if (conn_queue)
        deallocatequeue(conn_queue); */
    if (pred_queues) {
        for (int i = 0; i < c; ++i) {
            if (pred_queues[i]) {
                deallocatequeue(pred_queues[i]);
            }
        }
    }
    if (conn_queues) {
        for (int i = 0; i < c; ++i) {
            if (conn_queues[i]) {
                deallocatequeue(conn_queues[i]);
            }
        }
    }
    if (reftable)
        deallocatequeue(reftable);
    if (params)
        deallocatequeue(params);
    return 0; 
}


int countlines(FILE *fp) {
    int lines = 0;
    int c = '\0', pc = '\n';
    while (c = fgetc(fp), c != EOF) {
        if (c == '\n' && pc != '\n') {
            lines++;
        }
        pc = c;
    }
    if (pc != '\n') {
        lines++;
    }    
    return lines;
}

int readPSL(FILE *fp) {
    char line[1001] = "", *pos;
    struct sstnode *tmp;
    int done = 0;
    while (fgets(line, sizeof(line), fp)) {
        char *symbol, *syntax, *arity, *stdp, *sep = ";";
        symbol = (char*)strdup(strtok_r(line, sep, &pos));
        syntax = (char*)strdup(strtok_r(NULL, sep, &pos));
        arity = (char*)strdup(strtok_r(NULL, sep, &pos));
        stdp = (char*)strdup(strtok_r(NULL, "", &pos));
        trim(symbol);
        trim(syntax);
        trim(arity);
        trim(stdp);
        tmp = newsstnode(symbol, syntax, atoi(arity), stdp);
        if (tmp == NULL) {
            printf("Error in parsing predicate semantic library...");
            done = -1;
            break;
        }
        if (!psl) {
            psl = tmp;
        } else {
            addsstnode(psl, tmp);
        }
        free(symbol);
        free(syntax);
        free(arity);
        free(stdp);
    }
    return done;
}

int readDST(FILE *fp) {
    char line[1001] = "", *pos;
    while (fgets(line, sizeof(line), fp)) {
        char *symbol, *datatype, *symtype, *scope, *sep = ";";
        symbol = (char*)strdup(strtok_r(line, sep, &pos));
        datatype = (char*)strdup(strtok_r(NULL, sep, &pos));
        symtype = (char*)strdup(strtok_r(NULL, sep, &pos));
        scope = (char*)strdup(strtok_r(NULL, sep, &pos));
        trim(symbol);
        trim(datatype);
        trim(symtype);
        trim(scope);
        if (!dst) {
            dst = newdstnode(symbol, datatype, symtype, scope);
        } else {
            adddstnode(dst, newdstnode(symbol, datatype, symtype, scope));
        }
        free(symbol);
        free(datatype);
        free(symtype);
        free(scope);
    }
    return 0;
}

/* 
* ==================================================================================================================
* helper functions
* ==================================================================================================================
*/
// new astnode for a quantification expression 
struct astnode *newqexpr (struct token *quantifier, struct token *variable, struct astnode *terms) {
    struct astnode *new;
    if (strcmp(quantifier->symbol, "exists") == 0) {
        new = newastnode(Exists, variable);
    } else if (strcmp(quantifier->symbol, "-exists") == 0) {
        new = newastnode(NotExists, variable);
    } else if (strcmp(quantifier->symbol, "-all") == 0) {
        new = newastnode(NotForAll, variable);
    } else {
        new = newastnode(ForAll, variable);
    }
    new->cstptr = addcstsymbol(csts[c], variable, quantifier);
    addastchild(new, terms);
    return new;
}

// new astnode for a formula expression
struct astnode *newformula (struct token *quantifier, struct token *variable, struct astnode *terms) {
    #if DEBUG
    fprintf(stderr, "Original quantifying variable: %s\n", variable->symbol);
    #endif
    struct astnode *new = newqexpr(quantifier, variable, terms);
    #if DEBUG
    fprintf(stderr, "After adding cstsymbol quantifying variable: %s\n", variable->symbol);
    fprintf(stderr, "new quantifying variable: %s\n", new->cstptr->token->symbol);
    #endif
    struct astnode *tmp;
    struct cstsymbol *s;
    //TODO: do the renaming of variable procedure here. 
    //      In case that two subtrees use the same variable name.

    // a temporary queue holding the not found variables
    struct queue *_reftable = initqueue();
    while (reftable->count > 0) {
        tmp = (struct astnode *)dequeue(reftable);
        if (strcmp(tmp->token->symbol, variable->symbol) == 0 && 
            strcmp(tmp->token->symbol, new->cstptr->token->symbol) != 0) {
            free(tmp->token->symbol);
            tmp->token->symbol = (char*)strdup(new->cstptr->token->symbol);
            s = searchcst(csts[c], tmp->token);
            tmp->cstptr = s;
            enqueue(s->refs, tmp);
        } else if ((s = searchcst(csts[c], tmp->token)) != NULL && s->scopeclosed != 1) {
            tmp->cstptr = s;
            enqueue(s->refs, tmp);
        } else {
            #if DEBUG
            fprintf(stderr, "Variable %s in reftable not fit current formula variable\n", tmp->token->symbol);
            #endif
            enqueue(_reftable, (void*)tmp);
        }
        // if ((s = searchcst(csts[c], tmp->token)) != NULL) {
        //     tmp->cstptr = s;
        //     enqueue(s->refs, tmp);
        // } 
        // else {
        //     #if DEBUG
        //     printf("Syntax error: Symbol not found for symbol %s at line %d column %d\n", 
        //     tmp->token->symbol, tmp->token->line, tmp->token->column);
        //     #endif
        //     yyerror("Syntax error: Symbol not found");
        // }
    }
    while (_reftable->count > 0) {
        enqueue(reftable, (void*)dequeue(_reftable));
    }
    if (strcmp(new->cstptr->token->symbol, variable->symbol) != 0) {
        free(new->token->symbol);
        new->token->symbol = (char*)strdup(new->cstptr->token->symbol);
    }
    deallocatequeue(_reftable);
    new->cstptr->scopeclosed = 1;
    return new;
}

struct astnode* newcomplexformula(struct astnode *cfnode, struct astnode *connnode, struct astnode *fnode) {
    struct astnode *new = connnode;
    new->type = NonTrivialConnective;
    if (strcmp(new->token->symbol, "&") == 0) {
        free(new->token->symbol);
        new->token->symbol = (char*)strdup("&&");
    } else if (strcmp(new->token->symbol, "|") == 0) {
        free(new->token->symbol);
        new->token->symbol = (char*)strdup("||");
    } else {
        #if DEBUG
        fprintf(stderr, "Issue (%s)\n", new->token->symbol);
        #endif
        yyerror("Unknown connective encountered between formulas");
    }
    addastchild(new, cfnode);
    addastchild(new, fnode);
    return new;
}

struct astnode* newpred(struct token *t, struct astnodelist *terms) {
    struct astnode *new = newastnode(Predicate, t);
    // TODO: we know exactly how many arguments that we have
    //       so, we can,
    //       1. choosing the static symbol by the count of varlist
    //       2. finding the dynamic symbol


    int arity = getnodelistlength(terms);

    struct queue *pslptr = getsstsymbols(psl, new->token->symbol, new->syntax, arity);
    struct dstnode *dstptr = getdstsymbol(dst, new->token->symbol);

    struct sstnode *s;
    while (pslptr->count > 0) {
        s = (struct sstnode*)dequeue(pslptr);
        addsemantic(new->semantics, newsemantic(SemSource_Static, s));
    }
    deallocatequeue(pslptr);
    if (dstptr != NULL) {
        addsemantic(new->semantics, newsemantic(SemSource_Dynamic, dstptr));
    }

    if (countsemantics(new->semantics) == 0 && new->ptbsyntax != IN) {
        fprintf(stderr, "predicate(%s) issue:\n", new->token->symbol);
        yyerror("symbol not found for a predicate with the syntax, please check the predicate semantics library/dynamic symbol table");
    } else {
        //assume we have only one semantic
        struct semantic *sem = getsemantic(new->semantics, 0);
        
        if (sem->data != NULL && strcmp(sem->data, "I") == 0 && sem->arity == 1) {
            //Identity function. we cannot ignore the predicate
            deleteastnode(new);
            new = terms->node;
        } else {            
            // if (sem->semtype == Sem_Keyword || 
            //     sem->semtype == Sem_SymbolName ||
            //     new->ptbsyntax == NN ||
            //     new->ptbsyntax == NNS) new->priority = 1;
            if (sem->semtype == Sem_Keyword || 
                sem->semtype == Sem_SymbolName) new->priority = 1;
            else if (strcmp(sem->data, "\\sub") == 0 || sem->semtype == IN) new->priority = 2;
            else if (iscomparator(new->token->symbol) == 0 || isequality(new->token->symbol) == 0) new->priority = 3;
            else new->priority = 4;
            addastchildren(new, terms);
            enqueue(pred_queues[c], (void*)new);
        }
    }
    return new;
}




