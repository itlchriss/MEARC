#include <stdio.h>
#include <unistd.h>
#include <yaml.h>
#include "core.h"
#include "cg.h"
#include "si.h"
#include "event.h"
#include "alias.h"
#include "error.h"

extern FILE *yyin;

struct queue* readSI(char *);

// data structures
//  abstract syntax tree(s)
struct astnode *ast;  
//  compile-time symbol table
struct queue *cst;     
//  predicate stack, marking the positions of the predicate nodes
struct queue *predicates;

struct queue *events;

struct queue *operators;

struct queue *silist;

// a table holding the relationship of alias, such as (x = y) in the higher order logic
struct queue *alias;

struct astnode *root;

// for parsing only
struct queue *_events, *_datarefs;

#if INFO
void showprocessinfo(char *msg) {
    printf("======================================%s=========================================\n", msg);
}
#endif

int get_datatype(char *s) {
    if (strcmp(s, "*") == 0) return ANY;
    else if (strcmp(s, "R") == 0) return RELTYPE;
    else if (strcmp(s, "-1") == 0) return UNDEFINED;
    else return atoi(s);
}


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
    silist = readSI(dstfiles);


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
    events = initqueue();
    alias = initqueue();
    /* for parsing only */
    _datarefs = initqueue();
    _events = initqueue();    
    ///////////////////////
    yyin = fp;
    yyparse();
    fclose(fp);
    #if CSTDEBUG 
    showqueue(cst, showcstsymbol);
    #endif
    #if CSTDEBUG
    showqueue(cst, showcstsymbol);
    #endif
    #if EVENTDEBUG
    showqueue(events, showevent);
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
    opresolution(operators, cst);        
    sianalysis();
    sisynthesis();
    ast = root;
    #if ASTDEBUG
    showast(ast, 0);
    #endif
    ast = astsimplification(ast);
    ast = astsimplification(ast);
    #if ASTDEBUG
    showast(ast, 0);
    #endif 
    deallocatequeue(silist, deallocatesi);
    output(ast);

    /* free resources */
    if (ast) {
        deallocateast(ast);
    }
    if (events) {
        deallocatequeue(events, deallocateevent);
    }
    if (cst) {
        deallocatequeue(cst, deallocatecstsymbol);
    }
    if (operators) {
        deallocatequeue(operators, NULL);
    }
    if (alias) {
        deallocatequeue(alias, NULL);
    }

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
        fp = fopen(filepath, "r");        
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
        printf("Reading SI from file at %s\n", filepath);
        #endif
        int token_flag = -1;
        yaml_token_t  token;   /* new variable */
        struct si *si = NULL;
        char *key, *value;
        struct si_arg *arg = NULL;
         do {
            START:
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
                    si->synthesised_datatype = (struct datatype *)malloc(sizeof(struct datatype));
                    si->synthesised_datatype->p = UNDEFINED;
                    si->synthesised_datatype->r = UNDEFINED;
                    si->synthesised_datatype->types = initqueue();
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
                        si->args = initqueue();            
                        do { yaml_parser_scan(&parser, &token); } while (token.type != YAML_BLOCK_ENTRY_TOKEN);
                        while (token.type == YAML_BLOCK_ENTRY_TOKEN) {
                            while (token.type != YAML_BLOCK_END_TOKEN) {
                                switch (token.type) {
                                    case YAML_BLOCK_MAPPING_START_TOKEN:
                                        arg = (struct si_arg*)malloc (sizeof(struct si_arg));
                                        arg->datatype = (struct datatype *)malloc(sizeof(struct datatype));
                                        arg->datatype->p = UNDEFINED;
                                        arg->datatype->r = UNDEFINED;
                                        arg->datatype->types = NULL;
                                        break;
                                    case YAML_KEY_TOKEN:
                                        yaml_parser_scan(&parser, &token);
                                        #if SIDEBUG
                                        printf("(Value token) YAML_SCALAR_TOKEN: %s\n", (char*)token.data.scalar.value);
                                        #endif
                                        if (strcmp((char*)token.data.scalar.value, "symbol") == 0) {
                                            yaml_parser_scan(&parser, &token);     
                                            yaml_parser_scan(&parser, &token);                                        
                                            arg->symbol = (char *)strdup((char*)token.data.scalar.value);
                                            #if SIDEBUG
                                            printf("(Value token) YAML_SCALAR_TOKEN: %s\n", (char*)token.data.scalar.value);
                                            #endif
                                        } else if (strcmp((char*)token.data.scalar.value, "primitive_type") == 0) {
                                            yaml_parser_scan(&parser, &token);
                                            yaml_parser_scan(&parser, &token);     
                                            arg->datatype->p = (enum primitive_datatype)get_datatype((char *)token.data.scalar.value);
                                            #if SIDEBUG
                                            printf("(Value token) YAML_SCALAR_TOKEN: %s\n", (char*)token.data.scalar.value);
                                            #endif                                            
                                        } else if (strcmp((char*)token.data.scalar.value, "reference_type") == 0) {
                                            yaml_parser_scan(&parser, &token);
                                            yaml_parser_scan(&parser, &token);     
                                            arg->datatype->r = (enum reference_datatype)get_datatype((char *)token.data.scalar.value);
                                            #if SIDEBUG
                                            printf("(Value token) YAML_SCALAR_TOKEN: %s\n", (char*)token.data.scalar.value);
                                            #endif                                            
                                        } else if(strcmp((char *)token.data.scalar.value, "lazy_resolve") == 0) {
                                            yaml_parser_scan(&parser, &token);
                                            yaml_parser_scan(&parser, &token);     
                                            arg->datatype->lazy_resolve = (char *)strdup((char *)token.data.scalar.value);
                                            #if SIDEBUG
                                            printf("(Value token) YAML_SCALAR_TOKEN: %s\n", (char*)token.data.scalar.value);
                                            #endif  
                                        } else if (strcmp((char*)token.data.scalar.value, "type_names") == 0) {
                                            arg->datatype->types = initqueue();
                                            yaml_parser_scan(&parser, &token);
                                            while (token.type != YAML_KEY_TOKEN) {
                                                if (token.type == YAML_SCALAR_TOKEN) {
                                                    #if SIDEBUG
                                                    printf("(Value token) YAML_SCALAR_TOKEN: %s \n", token.data.scalar.value); 
                                                    #endif                                                                             
                                                    enqueue(arg->datatype->types, (void *)strdup((char *)token.data.scalar.value));                                                        
                                                }                  
                                                yaml_parser_scan(&parser, &token);
                                            }
                                            enqueue(si->args, (void *)arg);
                                            goto SWITCH;                                                                                                                  
                                        } else {
                                            sisyntax_error(filepath, si->symbol, "arguments");
                                        }                                    
                                        break;
                                    default:
                                        break;
                                }
                                yaml_parser_scan(&parser, &token);                            
                            }
                            enqueue(si->args, (void *)arg);
                            yaml_parser_scan(&parser, &token);       
                        }
                        // yaml_parser_scan(&parser, &token); 
                        goto START;
                    } else if (strcmp(key, "syntax") == 0) {
                        si->syntax = initqueue();
                        /* skip the YAML_VALUE_TOKEN */
                        yaml_parser_scan(&parser, &token);
                        /* read the YAML_BLOCK_ENTRY_TOKEN */
                        yaml_parser_scan(&parser, &token);
                        while (token.type == YAML_BLOCK_ENTRY_TOKEN) {                               
                            yaml_parser_scan(&parser, &token);          
                            #if SIDEBUG
                            printf("YAML_SCALAR_TOKEN: %s \n", token.data.scalar.value); 
                            #endif                                                                             
                            enqueue(si->syntax, (void *)string2ptbsyntax((char*)token.data.scalar.value));
                            yaml_parser_scan(&parser, &token);                      
                        }                                                                         
                        goto SWITCH;
                    } else if (strcmp(key, "synthesised_datatype") == 0) {
                        do { yaml_parser_scan(&parser, &token); } while (token.type != YAML_SCALAR_TOKEN);
                        while (token.type != YAML_SCALAR_TOKEN) yaml_parser_scan(&parser, &token);
                        #if SIDEBUG
                        printf("(SYNTHESISED DATATYPE) YAML_SCALAR_TOKEN: %s \n", token.data.scalar.value); 
                        #endif  
                        do { yaml_parser_scan(&parser, &token); } while (token.type != YAML_SCALAR_TOKEN);
                        #if SIDEBUG
                        printf("(SYNTHESISED DATATYPE) YAML_SCALAR_TOKEN: %s \n", token.data.scalar.value); 
                        #endif                                 
                        char *tmp = (char *)strdup((char *)token.data.scalar.value);
                        si->synthesised_datatype->p = (enum primitive_datatype)get_datatype(tmp);
                        free(tmp);
                        do { yaml_parser_scan(&parser, &token); } while (token.type != YAML_SCALAR_TOKEN);
                        #if SIDEBUG
                        printf("(SYNTHESISED DATATYPE) YAML_SCALAR_TOKEN: %s \n", token.data.scalar.value); 
                        #endif                               
                        do { yaml_parser_scan(&parser, &token); } while (token.type != YAML_SCALAR_TOKEN);
                        #if SIDEBUG
                        printf("(SYNTHESISED DATATYPE) YAML_SCALAR_TOKEN: %s \n", token.data.scalar.value); 
                        #endif                             
                        tmp = (char *)strdup((char *)token.data.scalar.value);
                        si->synthesised_datatype->r = (enum reference_datatype)get_datatype(tmp);
                        free(tmp);
                        do { yaml_parser_scan(&parser, &token); } while (token.type != YAML_KEY_TOKEN);
                        goto SWITCH;
                    }
                } else if (token_flag == 2) {
                    value = (char*) strdup((char*)token.data.scalar.value);
                    if (key && value) {
                        if (strcmp(key, "term") == 0) {
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
                        } else {
                            sisyntax_error(filepath, key, value);
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

    return new;
}


