%{
    #include "core.h"
    #include "util.h"
    #include "cst.h"
    #include "si.h"
    #include "regex.h"
    #define YYERROR_VERBOSE 1

    void print_debug(char *);
    void print_semantic_error(char *);
    enum java_datatype string2javadatatype(char *);

    // From main.c
    // extern int c;
    extern struct astnode *ast;  
    extern struct queue *predicates, *operators;

    // c is for the line counter of hols
    int lbracs = 0, rbracs = 0, lineNum = 1, colNum = 1, error_count = 0;
%}

%union{
    struct astnode *node;
    struct astnodelist *nodelist;
    struct token *t;
    enum ptbsyntax ptb;
    enum conntype conntype;
    // enum grammartype gtype;
}

%token <t> PREDICATE IDENTIFIER KEYWORD_TRUEP '.' NEG 
%token <t> COMMA '(' ')' EQUAL AND OR IMPLY EQUIV '{' '}'
%token <t> KEYWORD_QUANTIFIER KEYWORD_TYPE KEYWORD_PARAM
%token <t> TAG
/* %token <t> KEYWORD_PROG KEYWORD_REL TAG */
/* %token <ptb> KEYWORD_NN KEYWORD_NNS KEYWORD_NNP KEYWORD_NNPS KEYWORD_IN KEYWORD_JJ KEYWORD_JJR KEYWORD_JJS 
%token <ptb> KEYWORD_VB KEYWORD_VBG KEYWORD_VBZ KEYWORD_VBN KEYWORD_VBP KEYWORD_VBD
%token <ptb> KEYWORD_DT KEYWORD_CC KEYWORD_CD KEYWORD_PRP KEYWORD_MD
%token <ptb> KEYWORD_RB */
%left AND IMPLY EQUIV ')'

/* %type<ptb> pos_tag */
%type<conntype> connective
/* %type<node> terms argument term quantified_term predicate_term grammar_term grammar_tag */
%type<node> terms argument term quantified_term predicate_term grammar_term type_term param_term
%type<nodelist> arguments
/* %type<gtype> grammar_relation */
%start formula

%%

formula
    : terms {
        print_debug("formula: terms");
        ast = $1;
        $1->isroot = 1;
    }
    ;

terms
    : terms connective term {
        print_debug("terms: terms term");
        if ($3 == NULL) {
            $$ = $1;
        } else {
            $$ = newastnode(Connective, NULL);
            $$->conntype = $2;
            addastchild($$, $1);
            addastchild($$, $3);
        }
    }
    | terms connective '(' term ')' {
        print_debug("terms: terms connective '(' term ')'");
        if ($4 == NULL) {
            $$ = $1;
        } else {
            $$ = newastnode(Connective, NULL);
            $$->conntype = $2;
            addastchild($$, $1);
            addastchild($$, $4);
        }
    }
    | terms connective NEG term {
        print_debug("terms: terms term");
        $$ = newastnode(Connective, NULL);
        $4->isnegative = 1;
        $$->conntype = $2;
        addastchild($$, $1);
        addastchild($$, $4);
    }
    | terms connective NEG '(' term ')' {
        print_debug("terms: terms term");
        $$ = newastnode(Connective, NULL);
        $5->isnegative = 1;
        $$->conntype = $2;
        addastchild($$, $1);
        addastchild($$, $5);
    }
    | term {
        print_debug("terms: term");
        $$ = $1;
    }
    | NEG term {
        print_debug("term: NEG term");
        $$ = $2;
        $$->isnegative = 1;
    }
    | NEG '(' term ')' {
        print_debug("term: NEG term");
        $$ = $3;
        $$->isnegative = 1;
    }
    ;

term
    : predicate_term {
        $$ = $1;
    }
    | type_term {
        $$ = NULL;
    }
    | param_term {
        $$ = $1;
    }
    | quantified_term {
        $$ = $1;
    }
    | grammar_term {
        $$ = $1;
    }
    | KEYWORD_TRUEP {
        print_debug("term: PREDICATE(TrueP)");
        $$ = NULL;
    }
    | IDENTIFIER EQUAL IDENTIFIER {
        print_debug("term: '(' IDENTIFIER EQUAL IDENTIFIER ')'");
        $$ = newastnode(Operator, $2);
        struct astnode *left = newastnode(Variable, $1);
        struct astnode *right = newastnode(Variable, $3);
        if (addcstref($1->symbol, left) != 0) {
            addcstsymbol($1->symbol);
            addcstref($1->symbol, left);
        }
        if (addcstref($3->symbol, right) != 0) {
            addcstsymbol($3->symbol);
            addcstref($3->symbol, right);
        }
        addastchild($$, left);
        addastchild($$, right);
        enqueue(operators, (void*)$$);
    }    
    ;

connective
    : AND { $$ = Op_And; }
    | EQUIV { $$ = Op_Equivalent; }
    | IMPLY { $$ = Op_Imply; }
    | OR    { $$ = Op_Or; }
    ;

grammar_term
    : TAG '(' arguments ')' {
        print_debug("grammar_term: TAG '(' arguments ')'");
        if (string2ptbsyntax($1->symbol) == Gram_Prog) {
            $$ = $3->node;
            free($3);
        } else {
            $$ = newastnode(GrammarNotation, $1); 
            $$->syntax = string2ptbsyntax($1->symbol);
            addastchildren($$, $3);
            // we treat the grammar_tag as a predicate
            enqueue(predicates, (void*)$$);
        }
    }
    ;

/*
    if argument is more than one, semantic error
    search argument in symbol table        
        if symbol exists, add type to the symbol
        else add the symbol with the type
    the type predicate only specifies the type of the symbol, therefore, both the symbol and the type predicate do not need to be added in the AST
*/
type_term
    : KEYWORD_TYPE '{' TAG '}' '(' arguments ')' {
        //print_debug("TYPE(%s), Syntax(%s)\n", $1->symbol, $3->symbol);
        if (getnodelistlength($6) > 1) {
            print_semantic_error("A type predicate can only have one argument.");
        }
        struct cstsymbol* cst = searchcst($6->node->token->symbol);
        for (int i = 0; i < 6; ++i) {
            popchar($1->symbol);
        }
        cst->jtype = string2javadatatype($1->symbol);
        if (cst->jtype == -1) {
            printf("A type predicate(%s) is being used that is not currently supported.", $1->symbol);
            exit(-1);
        }
    }
    ;

/*
    if arguments is more than one, semantic error is thrown
    if the syntax is neither NN, nor NNS, nor NNP, nor NNPS, semantic error is thrown 
*/
param_term
    : KEYWORD_PARAM '{' TAG '}' '(' arguments ')' {
        //print_debug("PARAM(%s), Syntax(%s)\n", $1->symbol, $3->symbol); 
        if (getnodelistlength($6) > 1) {
            print_semantic_error("A type predicate can only have one argument.");
        }
        $$ = newastnode(Predicate, $1);
        for (int i = 0; i < 7; ++i) {
            popchar($$->token->symbol);
        }
        addastchildren($$, $6);
        $$->syntax = string2ptbsyntax($3->symbol);
        if ($$->syntax != NN && $$->syntax != NNS && $$->syntax != NNP && $$->syntax != NNPS) {
            print_semantic_error("A parameter must be regconised as a noun.");
        }
        $$->syntax = NN;
        /*
            this is added after using LLM. we no longer include the program context here
        */
        generate_param_si($$->token->symbol);
        enqueue(predicates, (void*)$$);        
    }
    ;

predicate_term
    : PREDICATE '{' TAG '}' '(' arguments ')' {
        print_debug("term: PREDICATE '{' TAG '}' '(' arguments ')'");
        #if PARDEBUG
        printf("Predicate(%s), Syntax(%s)\n", $1->symbol, $3->symbol);
        #endif        
        $$ = newastnode(Predicate, $1);    
        if ($$->token->symbol[0] == '_') {
            /* removing the underscore */
            popchar($$->token->symbol);
        }
        addastchildren($$, $6);
        $$->syntax = string2ptbsyntax($3->symbol);
        /* predicate node is marked in a queue and si identification is processed later  */            
        enqueue(predicates, (void*)$$);
    }
    | PREDICATE '{' TAG '}' '(' '(' terms ')' ')' {
        print_debug("term: PREDICATE(Modal)) '{' TAG '}' '(' '(' terms ')' ')'");
        $$ = $7;
    }
    ;

arguments
    : arguments COMMA argument {
        print_debug("arguments: arguments IDENTIFIER");
        appendnode($1, $3);
        $$ = $1;
    }
    | argument {
        print_debug("arguments: argument");
        $$ = newastnodelist($1);
    }
    ;

argument
    : IDENTIFIER {
        print_debug("argument: IDENTIFIER");        
        $$ = newastnode(Variable, $1);
        if (addcstref($1->symbol, $$) != 0) {
            addcstsymbol($1->symbol);
            addcstref($1->symbol, $$);
        }
    }
    | terms {
        print_debug("argument: terms");
        $$ = $1;
    }
    ;

quantified_term
    : KEYWORD_QUANTIFIER IDENTIFIER '.' '(' terms ')' {
        print_debug("quantify_expr: KEYWORD_EXISTS IDENTIFIER");
        $$ = newastnode(Quantifier, $2);
        if (strcmp($1->symbol, "exists") == 0) {
            $$->qtype = Quantifier_Exists;
        } else {
            $$->qtype = Quantifier_ForAll;
        }
        if (addcstref($2->symbol, $$) != 0) {
            addcstsymbol($2->symbol);
            addcstref($2->symbol, $$);
        }
        addastchild($$, $5);        
        closecstscope($2->symbol);
    }
    ;
%%

void print_debug(char *s) {
    #if PARDEBUG
    printf("%s\n", s);
    #endif  
}

void print_semantic_error(char *s) {
    printf("Semantic representation semantic error. %s\n", s);
    exit(-1);
}

enum java_datatype string2javadatatype(char *s) {
    if (strcmp(s, "array") == 0)  return JavaArray;
    else if (strcmp(s, "list") == 0) return JavaList;
    else if (strcmp(s, "integer") == 0) return JavaInteger;
    else if (strcmp(s, "short") == 0) return JavaShort;
    else if (strcmp(s, "long") == 0) return JavaLong;
    else if (strcmp(s, "float") == 0) return JavaFloat;
    else if (strcmp(s, "double") == 0) return JavaDouble;
    else return -1;
}

extern
void yyerror(const char *s) {
    extern char* input_line;
    fprintf(stderr,"Parser: parse error!\n  Message: %s in line %d, column %d\n", s, lineNum, colNum);
    fprintf(stderr,"%s\n", input_line);
    free(input_line);
    for(int i = 0; i < colNum - 1; i++)
        fprintf(stderr,"_");
    fprintf(stderr,"^\n"); 
    /* exit(-1); */    
}
