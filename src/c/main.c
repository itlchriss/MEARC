#include <unistd.h>
#include <yaml.h>
#include "core.h"
#include "cg.h"
#include "si.h"
#if EVENT_SEMANTICS
#include "event.h"
#endif

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

#if EVENT_SEMANTICS
struct queue **events;
struct queue *_events;
#endif

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
    #if EVENT_SEMANTICS
    events = (struct queue **) malloc (sizeof(struct queue *) * lines);
    #endif
    for (int i = 0; i < lines; ++i) {
        csts[i] = initqueue();
        predicates[i] = initqueue();
        operators[i] = initqueue();
        events[i] = initqueue();
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
        connectentitysymbol(csts[i], events[i]);
        root = ast[i];
        #if INFO
        showprocessinfo("Start semantic interpretation identification");
        #endif
        #if EVENT_SEMANTICS
        _events = events[i];
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
        /*
        intended to do simplification TWICE.
        because there can be case that a skew tree exists before simplification and the root is a connective.
        such that, all internal nodes may be removed (the case that all internal nodes are quantifiers),
        but the root is preserved. 
        this case will make the semantics weird in the code generation phase.
        running the simplification twice can deal with this situation. 
        however, it is ugly. we should improve the algorithm.
        */
        ast[i] = astsimplification(ast[i]);
        ast[i] = astsimplification(ast[i]);
        #if INFO
        showprocessinfo("Finished AST simplification");
        #endif
        #if ASTDEBUG
        showast(ast[i], 0);
        #endif        
        #if EVENTDEBUG
        showqueue(events[i], showevent);
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

    /* free resources */
    for (int i = 0; i < c; ++i) {
        if (ast[i])
            deallocateast(ast[i]);
        if (csts[i])
            deallocatequeue(csts[i], deallocatecstsymbol);
        if (operators[i])
            deallocatequeue(operators[i], NULL);
        #if EVENT_SEMANTICS
            deallocatequeue(events[i], deallocateevent);
        #endif
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

enum mearc_config_key_type { 
    TERM,
    SYNTAX,
    ARITY,
    ARGUMENTS,
    ARGUMENTS_SECTION,
    ARGUMENT_NAME,
    ARGUMENT_TYPE,
    INTERPRETATION,
    TYPE
};


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
        
        yaml_token_t  token;   /* new variable */
        yaml_token_type_t token_type;
        enum mearc_config_key_type key_type;
        struct si *si = NULL;
        struct siarg *siarg = NULL;
         do {
            yaml_parser_scan(&parser, &token);
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
                puts("(Key token)   "); 
                #endif
                token_type = YAML_KEY_TOKEN;
                break;
            case YAML_VALUE_TOKEN: 
                #if SIDEBUG
                puts("(Value token) "); 
                #endif
                token_type = YAML_VALUE_TOKEN;
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
                    si = newsi();
                }
                switch (key_type)
                {
                case ARGUMENTS:
                    key_type = ARGUMENTS_SECTION;
                    siarg = (struct siarg*)malloc(sizeof(struct siarg));
                    break;
                case ARGUMENTS_SECTION:
                    key_type = ARGUMENT_NAME;
                    break;
                case ARGUMENT_NAME:
                    key_type = ARGUMENT_TYPE;
                    break;
                default:
                    break;
                }
                break;
            case YAML_BLOCK_END_TOKEN:            
                #if SIDEBUG
                puts("<b>End block</b>");
                #endif
                if (key_type == ARGUMENT_TYPE) { 
                    enqueue(si->_args, (void*)siarg);
                    siarg = NULL;
                    key_type = ARGUMENTS;
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
                printf("YAML_SCALAR_TOKEN: %s(%s) \n", token.data.scalar.value, yaml_token_type_name[token_type]); 
                #endif
                if (token_type == YAML_KEY_TOKEN) {
                    if (strcmp((char*)token.data.scalar.value, "term") == 0) {
                        key_type = TERM;
                    } else if (strcmp((char*)token.data.scalar.value, "syntax") == 0) {
                        key_type = SYNTAX;
                    } else if (strcmp((char*)token.data.scalar.value, "arguments") == 0) {
                        key_type = ARGUMENTS;
                    } else if (strcmp((char*)token.data.scalar.value, "arity") == 0) {
                        key_type = ARITY;
                    } else if (strcmp((char*)token.data.scalar.value, "interpretation") == 0) {
                        key_type = INTERPRETATION;
                    } else if (strcmp((char*)token.data.scalar.value, "type") == 0) {
                        key_type = TYPE;
                    } else {
                        fprintf(stderr, "Unknown config key encountered: %s\n", token.data.scalar.value);
                        exit(-1);
                    }
                }
                else if (token_type == YAML_VALUE_TOKEN) {
                    switch (key_type)
                    {
                    case TERM:
                        si = newsi();
                        si->term = (char*)strdup((char*)token.data.scalar.value);
                        break;
                    case SYNTAX:
                        enqueue(si->_syntax, (void*)((char*)strdup((char*)token.data.scalar.value)));
                        break;
                    case ARITY:
                        si->arg_count = atoi((char*)token.data.scalar.value);
                        break;
                    case ARGUMENT_NAME:
                        siarg->data = (char*)strdup((char*)token.data.scalar.value);
                        break;
                    case ARGUMENT_TYPE:
                        siarg->type = str2argtype((char*)token.data.scalar.value);
                        break;
                    case TYPE:
                        si->type = str2argtype((char*)token.data.scalar.value);
                        break;
                    case INTERPRETATION:
                        si->interpretation = (char*)strdup((char*)token.data.scalar.value);
                        enqueue(new, (void*)si);
                        si = NULL;
                        break;
                    default:
                        break;
                    }
                } else {
                    fprintf(stderr, "Unknown token type encountered in reading scalar token: %s\n", yaml_token_type_name[token_type]);
                    exit(-1);
                }
                break;
            /* Others */
            default:
                #if SIDEBUG
                printf("Got unknown token of type %d\n", token.type);
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


