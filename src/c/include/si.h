#ifndef SI_H
#define SI_H
#include "ast.h"
#include "util.h"
/*
    Semantic interpretation header
    Each semantic interpretation is UNIQUE because each interpretation has a unique composite key of (term and penn-tree bank category).
*/

enum javadatatype { PRIMITIVE = 0, NON_PRIMITIVE, NON_PRIMITIVE_WITH_DIMENSIONS };
enum argtype { EXP = 0, KEYWORD, LITERAL };

#ifdef SIDEBUG
static char *yaml_token_type_name[] = {
    "YAML_NO_TOKEN",
	"YAML_STREAM_START_TOKEN",
	"YAML_STREAM_END_TOKEN",
	"YAML_VERSION_DIRECTIVE_TOKEN",
	"YAML_TAG_DIRECTIVE_TOKEN",
	"YAML_DOCUMENT_START_TOKEN",
	"YAML_DOCUMENT_END_TOKEN",
	"YAML_BLOCK_SEQUENCE_START_TOKEN",
	"YAML_BLOCK_MAPPING_START_TOKEN",
	"YAML_BLOCK_END_TOKEN",
	"YAML_FLOW_SEQUENCE_START_TOKEN",
	"YAML_FLOW_SEQUENCE_END_TOKEN",
	"YAML_FLOW_MAPPING_START_TOKEN",
	"YAML_FLOW_MAPPING_END_TOKEN",
	"YAML_BLOCK_ENTRY_TOKEN",
	"YAML_FLOW_ENTRY_TOKEN",
	"YAML_KEY_TOKEN",
	"YAML_VALUE_TOKEN",
	"YAML_ALIAS_TOKEN",
	"YAML_ANCHOR_TOKEN",
	"YAML_TAG_TOKEN",
	"YAML_SCALAR_TOKEN"
};
#endif

struct siarg {
    enum argtype type;
    char *data;
};

struct si {
    char *term;
    /* penn tree bank categories of this si */
    // enum ptbsyntax *syntax;
    /* number of syntax that can be accepted for synthesising this semantic */
    // int syntax_count;
    /* syntax of this si in cstrings*/
    struct queue *_syntax;
    /* number of argument to synthesize this semantic */
    int arg_count;    
    /* arguments in the interpretation */
    struct queue *_args;
    // enum javadatatype jtype;
    /* type of the SI */
    enum argtype type;
    
    char *interpretation;
};

/* 
    semantic interpretation identification 
    this is a process to identify as many si as possible presenting in the meaning representation
    - parameter descriptions
        predicates  : a queue holding pointers of predicates present in an abstract syntax tree parsed from meaning representation
        silist      : a queue holding semantic interpretations parsed from standard semantic interpretation database
        cst         : a queue holding the compile time symbols, aka the identitiers in the meaning representation
*/
void siidentification(struct queue*, struct queue *, struct queue *);
void opresolution(struct queue*, struct queue *);
void showsi(void *_si);
enum argtype str2argtype(char *str);
void deallocatesi(void *);
struct si* newsi();
#endif
