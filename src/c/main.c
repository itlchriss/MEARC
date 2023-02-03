#include <stdio.h>
#include <unistd.h>
#include <yaml.h>
#include "core.h"
#include "cg.h"
#include "si.h"
#if EVENT_SEMANTICS
#include "event.h"
#endif

extern FILE *yyin;

struct queue* readSI(char *);

// data structures
//  abstract syntax tree(s)
struct astnode *ast;  
//  compile-time symbol table
struct queue *cst;     
//  predicate stack, marking the positions of the predicate nodes
struct queue *predicates;

struct queue *operators;

#if EVENT_SEMANTICS
/* queue of events. for resolving MRs in neo-davidsonian event semantics */
struct queue *events;
#endif

struct queue *silist;

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
    
    /* A structure implemented by a queue containing semantic interpretations */  
    #if INFO      
    showprocessinfo("Start reading SI information");
    #endif
    silist = readSI(dstfiles);
    #if INFO      
    showprocessinfo("Finished reading SI information");
    #endif

    FILE *fp = fopen(specfile, "r");
    if (!fp) {
        printf("The spec file path cannot be opened...\n");
        return 1;
    }

    // acquire resources before parsing
    ast = (struct astnode *) malloc (sizeof(struct astnode));
    cst = initqueue();
    operators = initqueue();
    predicates = initqueue();
    #if DEPCCG
    events = initqueue();
    #endif

    #if INFO      
    showprocessinfo("Start Parsing");
    #endif
    yyin = fp;
    yyparse();
    fclose(fp);
    #if INFO
    showprocessinfo("Before rename cst symbols");
    #endif
    #if CSTDEBUG 
    showqueue(cst, showcstsymbol);
    #endif
    renamesymbols(cst);
    #if INFO
    showprocessinfo("After rename cst symbols");
    #endif
    #if EVENT_SEMANTICS
    showqueue(cst, showcstsymbol);
    #endif

    #if INFO
    showprocessinfo("Parsing finished");
    #endif

    #if ASTDEBUG
    printf("Printing Abstract syntax debug information.................\n");
    showast(ast, 0);        
    showqueue(cst, showcstsymbol);
    printf("================================================================================================\n");    
    #endif

    /* 
    Step: Semantic interpretation identification  
        For each abstract syntax tree, we traverse all nodes to find the nodes which are predicates, trying to map the semantic interpretations from si list
    */
    root = ast;
    #if INFO
    showprocessinfo("Start semantic interpretation analysis");
    #endif
    // siidentification(silist);
    sianalysis();
    #if INFO
    showprocessinfo("Finished semantic interpretation analysis");
    showprocessinfo("Start semantic interpretation synthesis");
    #endif
    sisynthesis();
    #if INFO
    showprocessinfo("Finished semantic interpretation synthesis");
    #endif
    #if INFO
    showprocessinfo("Start operator resolution");
    #endif
    opresolution(operators, cst);        
    #if INFO
    showprocessinfo("Finished operator resolution");
    #endif
    ast = root;
    #if ASTDEBUG
    showast(ast, 0);
    #endif
    #if INFO
    showprocessinfo("Start AST simplification");
    #endif
    ast = astsimplification(ast);
    ast = astsimplification(ast);
    #if INFO
    showprocessinfo("Finished AST simplification");
    #endif
    #if ASTDEBUG
    showast(ast, 0);
    #endif 
    deallocatequeue(silist, deallocatesi);

    #if ASTDEBUG
    printf("Printing debugging info.................\n");
    showast(ast, 0);        
    showqueue(cst, showcstsymbol);
    printf("\n");    
    #endif

    #if INFO
    showprocessinfo("Start Code generation");
    #endif
    output(ast);
    #if INFO
    showprocessinfo("Finished Code generation");
    #endif

    /* free resources */
    if (ast) {
        deallocateast(ast);
    }
    if (cst) {
        deallocatequeue(cst, deallocatecstsymbol);
    }
    if (operators) {
        deallocatequeue(operators, NULL);
    }
    #if EVENT_SEMANTICS
    if (events) {
        deallocatequeue(events, deallocateevent);
    }
    #endif
    return 0; 
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
                    si->g_arg_count = 0;
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
                        si->arg_types = (int*)malloc(sizeof(int) * si->arg_count);
                        for (int j = 0; j < si->arg_count; ++j) {
                            si->arg_types[j] = -1;
                        }
                        int i = 0;                            
                        while (i < si->arg_count) {
                            yaml_parser_scan(&parser, &token);
                            if (token.type == YAML_SCALAR_TOKEN) {
                                si->args[i] = (char*) strdup((char*)token.data.scalar.value);
                                ++i;
                            }
                        }                            
                    } else if (strcmp(key, "grammar_arguments") == 0) {                        
                        int i = 0;                            
                        while (i < si->g_arg_count) {
                            yaml_parser_scan(&parser, &token);
                            if (token.type == YAML_SCALAR_TOKEN) {
                                si->g_args[i] = (char*) strdup((char*)token.data.scalar.value);
                                ++i;
                            }
                        }  
                    } else if (strcmp(key, "specific_arg_types") == 0) {                        
                        int i = 0;                            
                        while (i < si->arg_count) {
                            yaml_parser_scan(&parser, &token);
                            if (token.type == YAML_SCALAR_TOKEN) {
                                si->arg_types[i] = atoi((char*)token.data.scalar.value);
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
                        } else if (strcmp(key, "g_arity") == 0) {
                            si->g_arg_count = atoi(value);
                            si->g_args = (char**) malloc (sizeof(char*) * si->g_arg_count);
                        } else if (strcmp(key, "term") == 0) {
                            si->symbol = (char*) strdup(value);
                            trim(si->symbol);
                            /* 
                                predicates in meaning representation use underscores to represent spaces 
                                therefore, we replace any spaces in the term with underscores
                            */                            
                            for (int i = 0; i < strlen(si->symbol); ++i) {
                                if (si->symbol[i] == ' ') {
                                    si->symbol[i] = '_';
                                }
                            }
                        } else if (strcmp(key, "interpretation") == 0) {
                            si->interpretation = (char*) strdup(value);
                        } else if (strcmp(key, "type") == 0) {
                            si->jtype = atoi(value);
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


