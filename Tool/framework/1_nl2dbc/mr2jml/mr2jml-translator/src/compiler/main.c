#include <stdio.h>
#include <unistd.h>
#include <yaml.h>
#include "core.h"
#include "cg.h"
#include "si.h"

extern FILE *yyin;
extern int *error_lines, error_count;


int countlines(FILE *fp);
struct queue* readSI(char *);
// int readPSL(FILE *fp);

// counting the number of MR in single file
int c = 0;

// data structures
//  abstract syntax tree(s)
struct astnode **ast;  
//  compile-time symbol table
struct queue **csts;     
//  predicate stack, marking the positions of the predicate nodes
struct queue **predicates;

struct queue **operators;

struct astnode *root;

#if INFO
void showprocessinfo(char *msg) {
    printf("======================================%s=========================================\n", msg);
}
#endif


//TODO: we have to store every queue per root node.
//      because, if we use the same queue and multiple sentences, predicates will be stores in the same
//      structure, and everything messes up.
int main(int argc, char** argv) { 
    int opt;
    char *specfile, *pslfile, *dstfiles, *cpslfile, *mname;
    specfile = pslfile = dstfiles = cpslfile = mname = NULL;

    while ((opt = getopt(argc, argv, ":f:p:s:c:m:")) != -1) {
        switch(opt) {
            case 'f':
            #if INFO
            printf("input file: %s\n", optarg);
            #endif
            specfile = optarg;
            break;
            case 's':
            #if INFO
            printf("Semantic interpretation sources: %s\n", optarg);
            #endif
            dstfiles = optarg;
            break;
            // case 'm':
            // printf("method name: %s\n", optarg);
            // mname = optarg;
            // break;
            case '?':
            fprintf (stderr,
                   "Unknown option character `\\x%x'.\n",
                   optopt);
            return 1;
            default:
            printf("-f filename    specifying the filename of the spec file\n");
            printf("-s filename    specifying the filenames of the semantic interpretation library files\n");
        }
    }
    if (specfile == NULL) {
        printf("Please specify the spec file by -f\n");
        return 1;
    } 
    

    int lines = 0;
    /* A structure implemented by a queue containing semantic interpretations */  
    #if INFO      
    showprocessinfo("Start reading SI information");
    #endif
    struct queue *silist = readSI(dstfiles);
    #if INFO      
    showprocessinfo("Finished reading SI information");
    #endif

    FILE *fp = fopen(specfile, "r");
    if (!fp) {
        printf("The spec file path cannot be opened...\n");
        return 1;
    }
    lines = countlines(fp);
    if (lines <= 0) {
        printf("zero lines...do I have to do anything?\n");
        exit(1);
    }
    #if INFO
    printf("Number of MRs: %d\n", lines);    
    #endif
    // acquire resources before parsing
    ast = (struct astnode **) malloc (sizeof(struct astnode *) * lines);
    csts = (struct queue **) malloc (sizeof(struct queue *) * lines);
    operators = (struct queue **) malloc (sizeof(struct queue *) * lines);
    predicates = (struct queue **) malloc (sizeof(struct queue *) * lines);
    for (int i = 0; i < lines; ++i) {
        csts[i] = initqueue();
        predicates[i] = initqueue();
        operators[i] = initqueue();
    }

    fseek(fp, 0, SEEK_SET);
    error_lines = (int*) malloc (sizeof(int) * lines);
    #if INFO      
    showprocessinfo("Start Parsing");
    #endif
    yyin = fp;
    yyparse();
    fclose(fp);
    for (int i = 0; i < lines; ++i) {
    #if INFO
    showprocessinfo("Before rename cst symbols");
    #endif
    #if CSTDEBUG 
        showqueue(csts[i], showcstsymbol);
    #endif
        renamesymbols(csts[i]);
    #if INFO
    showprocessinfo("After rename cst symbols");
    #endif
    #if CSTDEBUG
        showqueue(csts[i], showcstsymbol);
    #endif
    }

    // only process with successful parsed lines
    lines -= error_count;

    #if INFO
    showprocessinfo("Parsing finished");
    #endif

    #if ASTDEBUG
    for (int i = 0; i < c; ++i) {
        printf("Printing Abstract syntax tree # %d.................\n", i + 1);
        showast(ast[i], 0);        
        showqueue(csts[i], showcstsymbol);
    }
    printf("================================================================================================\n");    
    #endif

    /* 
    Step: Semantic interpretation identification  
        For each abstract syntax tree, we traverse all nodes to find the nodes which are predicates, trying to map the semantic interpretations from si list
    */
     for (int i = 0; i < c; ++i) {
        root = ast[i];
        #if INFO
        showprocessinfo("Start semantic interpretation identification");
        #endif
        siidentification(predicates[i], silist, csts[i]);
        #if INFO
        showprocessinfo("Finished semantic interpretation identification");
        #endif
        #if INFO
        showprocessinfo("Start operator resolution");
        #endif
        opresolution(operators[i], csts[i]);        
        #if INFO
        showprocessinfo("Finished operator resolution");
        #endif
        ast[i] = root;
        #if ASTDEBUG
        showast(ast[i], 0);
        #endif
        #if INFO
        showprocessinfo("Start AST simplification");
        #endif
        ast[i] = astsimplification(ast[i]);
        #if INFO
        showprocessinfo("Finished AST simplification");
        #endif
        #if ASTDEBUG
        showast(ast[i], 0);
        #endif        
     }
    deallocatequeue(silist, deallocatesi);

    #if ASTDEBUG
    for (int i = 0; i < c; ++i) {
        printf("Printing resulting Abstract syntax tree # %d.................\n", i + 1);
        showast(ast[i], 0);        
        showqueue(csts[i], showcstsymbol);
    }
    printf("\n");    
    #endif

    #if INFO
    showprocessinfo("Start Code generation");
    #endif
    for (int i = 0; i < c; ++i) {
        output(ast[i]);
    }
    #if INFO
    showprocessinfo("Finished Code generation");
    #endif

    // // exit(-2);

    // // sort the priorities
    // for (int k = 0; k < c; ++k) {
    //     for (int i = 0; i < pred_queues[k]->count; ++i) {
    //         for (int j = pred_queues[k]->count - 1; j > i; --j) {
    //             if (((struct astnode *)gqueue(pred_queues[k], j))->priority < 
    //                 ((struct astnode *)gqueue(pred_queues[k], i))->priority) {
    //                 struct queuenode *tmp1 = _gqueue(pred_queues[k], j);
    //                 struct queuenode *tmp2 = _gqueue(pred_queues[k], i);
    //                 struct astnode *tmpnode = (struct astnode *)tmp1->datanode;
    //                 tmp1->datanode = tmp2->datanode;
    //                 tmp2->datanode = tmpnode;
    //             }
    //         }
    //     }
    // }

    // #if DEBUG
    // for (int k = 0; k < c; ++k) {
    //     for (int i = 0; i < pred_queues[k]->count; ++i) {
    //         printf("%s %d, ", 
    //             ((struct astnode *)gqueue(pred_queues[k], i))->token->symbol, 
    //             ((struct astnode *)gqueue(pred_queues[k], i))->priority);
    //     }
    // }
    // printf("\n");
    // #endif

    // // TODO: support other modes, such as invariant    
    // // indicating the current spec type
    // int mode;
    // //simplifying the ast and generate target code    
    // if (specfile[strlen(specfile) - 4] == 'e') {
    //     // preconditions, generate 'requires'
    //     mode = 1;
    // } else {
    //     // postconditions, generate 'ensures'
    //     mode = 2;
    // }

    // params = getparams(dst, fdstptr);
    // for (int i = 0; i < c; ++i) { 
    //     ast[i] = simplifyast(ast[i], pred_queues[i], conn_queues[i], fdstptr, params);
    //     // Code generation
    //     gencode(ast[i], mode);
    // }  
    
    // END:

    /* free resources */
    for (int i = 0; i < c; ++i) {
        if (ast[i])
            deallocateast(ast[i]);
        if (csts[i])
            deallocatequeue(csts[i], deallocatecstsymbol);
        if (predicates[i])
            deallocatequeue(predicates[i], NULL);
        if (operators[i])
            deallocatequeue(operators[i], NULL);
    }    
   
    if (error_count > 0) {
        printf("There are %d lines that cannot be processed:\n", error_count);
        for (int i = 0; i < error_count - 1; ++i) {
            printf("%d, ", error_lines[i]);
        }
        printf("%d\n", error_lines[error_count - 1]);
    }
    free(error_lines);
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

struct queue* readSI(char *dstfilepaths) {
    char *filepath, *pos;
    char *_t = strtok_r(dstfilepaths, ",", &pos);
    struct queue* new = initqueue();
    FILE *fp;
    yaml_parser_t parser;    
    while (_t != NULL) {
        filepath = (char*)strdup(_t);
        fp = fopen(filepath, "rb");        
        if (!fp) {
            fprintf(stderr, "Cannot open SI file at %s\n", filepath);
            exit(-1);
        } else {
            int c = fgetc(fp);
    	    if (c == EOF) {
                fprintf(stderr, "Empty SI file at %s\n", filepath);
                break;
            } else {
                ungetc(c, fp);
            }
        }
        yaml_parser_initialize(&parser);
        yaml_parser_set_input_file(&parser, fp);
        #if SIDEBUG
        printf("Trying to read SI from file at %s\n", filepath);
        #endif
        int token_flag = -1;
        yaml_token_t  token;   /* new variable */
        struct si *si = NULL;
        char *key, *value;
         do {
            yaml_parser_scan(&parser, &token);
            SWITCH:
            switch(token.type)
            {
            /* Stream start/end */
            case YAML_STREAM_START_TOKEN: 
                #if SIDEBUG
                puts("STREAM START");
                #endif
                break;
            case YAML_STREAM_END_TOKEN:
                #if SIDEBUG
                puts("STREAM END");
                #endif   
                break;
            /* Token types (read before actual token) */
            case YAML_KEY_TOKEN:   
                #if SIDEBUG
                printf("(Key token)   "); 
                #endif
                token_flag = 1;
                break;
            case YAML_VALUE_TOKEN: 
                #if SIDEBUG
                printf("(Value token) "); 
                #endif
                token_flag = 2;
                break;
            /* Block delimeters */
            case YAML_BLOCK_SEQUENCE_START_TOKEN: 
                #if SIDEBUG
                puts("<b>Start Block (Sequence)</b>"); 
                #endif
                break;
            case YAML_BLOCK_ENTRY_TOKEN:          
                #if SIDEBUG
                puts("<b>Start Block (Entry)</b>");    
                #endif
                if (!si) {
                    si = (struct si*) malloc (sizeof(struct si));
                }
                break;
            case YAML_BLOCK_END_TOKEN:            
                #if SIDEBUG
                puts("<b>End block</b>");
                #endif
                if (si) {
                    enqueue(new, (void*)si);
                    si = NULL;
                }
                break;
            /* Data */
            case YAML_BLOCK_MAPPING_START_TOKEN:
                #if SIDEBUG
                puts("[Block mapping]");
                #endif                            
                break;
            case YAML_SCALAR_TOKEN:  
                #if SIDEBUG
                printf("YAML_SCALAR_TOKEN: %s \n", token.data.scalar.value); 
                #endif
                if (token_flag == 1) {
                    key = (char*) strdup((char*)token.data.scalar.value);
                    if (strcmp(key, "arguments") == 0) {
                        si->args = (char**)malloc(sizeof(char*) * si->arg_count);
                        int i = 0;                            
                        while (i < si->arg_count) {
                            yaml_parser_scan(&parser, &token);
                            if (token.type == YAML_SCALAR_TOKEN) {
                                si->args[i] = (char*) strdup((char*)token.data.scalar.value);
                                ++i;
                            }
                        }                            
                    } else if (strcmp(key, "syntax") == 0) {
                        int i = 0;
                        si->syntax = NULL;
                        /* skip the YAML_VALUE_TOKEN */
                        yaml_parser_scan(&parser, &token);
                        /* read the YAML_BLOCK_ENTRY_TOKEN */
                        yaml_parser_scan(&parser, &token);
                        while (token.type == YAML_BLOCK_ENTRY_TOKEN) {                               
                            yaml_parser_scan(&parser, &token);          
                            #if SIDEBUG
                            printf("YAML_SCALAR_TOKEN: %s \n", token.data.scalar.value); 
                            #endif                                                 
                            if (i > 0) {
                                enum ptbsyntax *tmp = (enum ptbsyntax*) malloc (sizeof(enum ptbsyntax) * (++i));
                                for (int j = 0; j < i; ++j) {
                                    tmp[j] = si->syntax[j];
                                }
                                free(si->syntax);          
                                si->syntax = tmp;                      
                            } else {
                                si->syntax = (enum ptbsyntax*) malloc (sizeof(enum ptbsyntax) * (++i));
                            }
                            si->syntax[i - 1] = string2ptbsyntax((char*)token.data.scalar.value);                               
                            yaml_parser_scan(&parser, &token);                      
                        }                                                                         
                        si->syntax_count = i;
                        goto SWITCH;
                    }
                } else if (token_flag == 2) {
                    value = (char*) strdup((char*)token.data.scalar.value);
                    if (key && value) {
                        if (strcmp(key, "arity") == 0) {
                            si->arg_count = atoi(value);                            
                        } else if (strcmp(key, "term") == 0) {
                            si->term = (char*) strdup(value);
                            trim(si->term);
                            /* 
                                predicates in meaning representation use underscores to represent spaces 
                                therefore, we replace any spaces in the term with underscores
                            */                            
                            for (int i = 0; i < strlen(si->term); ++i) {
                                if (si->term[i] == ' ') {
                                    si->term[i] = '_';
                                }
                            }
                        } else if (strcmp(key, "interpretation") == 0) {
                            si->interpretation = (char*) strdup(value);
                        } else {
                            fprintf(stderr, "Syntax error in SI file %s with key %s and value %s\n", filepath, key ,value);
                            exit(-1);
                        }                        
                    }
                    free(key);
                    free(value);
                }
                break;
            /* Others */
            default:
                #if SIDEBUG
                printf("Got token of type %d\n", token.type);
                #endif
                break;
            }
            if(token.type != YAML_STREAM_END_TOKEN) {
                yaml_token_delete(&token);
            }
        } while(token.type != YAML_STREAM_END_TOKEN);
        yaml_token_delete(&token);
        /* END new code */

        /* Cleanup */
        yaml_parser_delete(&parser);   
        fclose(fp);
        _t = strtok_r(NULL, ",", &pos);
    }
    yaml_parser_delete(&parser);
    #if SIDEBUG
    showqueue(new, showsi);
    #endif
    return new;
}


