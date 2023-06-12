%{
    #include "core.h"
    #include "util.h"
    #include "cst.h"
    #if EVENT_SEMANTICS
    #include "event.h"
    #endif
    #define YYERROR_VERBOSE 1

    void print_debug(char *);

    // From main.c
    // extern int c;
    extern struct astnode *ast;  
    extern struct queue *predicates, *operators;
    #if EVENT_SEMANTICS
    extern struct queue *events;
    #endif

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
%token <t> KEYWORD_QUANTIFIER
%token <t> TAG
%token <t> EVENT
%left AND IMPLY EQUIV ')'

/* %type<ptb> pos_tag */
%type<conntype> connective
/* %type<node> terms argument term quantified_term predicate_term grammar_term grammar_tag */
%type<node> terms argument term quantified_term predicate_term grammar_term 
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
    | quantified_term {
        $$ = $1;
    }
    | grammar_term {
        $$ = $1;
    }
    | event_term {
        $$ = NULL;
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

event_term
    : '(' EVENT '(' IDENTIFIER ')' EQUAL IDENTIFIER ')' {
        print_debug("event_term: '(' EVENT '(' IDENTIFIER ')' EQUAL IDENTIFIER ')'");
        #if EVENT_SEMANTICS
        addevententity(newevent($4->symbol), $7->symbol, $2->symbol);
        #endif
    }
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
            // $$->qtype = Quantifier_Exists;
            $$->qtype = 0;
        } else {
            // $$->qtype = Quantifier_ForAll;
            $$->qtype = 1;
        }
        if (addcstref($2->symbol, $$) != 0) {
            addcstsymbol($2->symbol);
            // addcstref($2->symbol, $$);
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
