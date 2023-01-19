%{
    #include "core.h"
    #include "util.h"
    #include "cst.h"
    #define YYERROR_VERBOSE 1

    void print_debug(char *);

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
    enum grammartype gtype;
}

%token <t> PREDICATE IDENTIFIER KEYWORD_TRUEP '.' MINUS
%token <t> COMMA '(' ')' GCASE EQUAL AND OR IMPLY EQUIV '{' '}'
%token <t> KEYWORD_FORALL KEYWORD_EXISTS
%token <t> KEYWORD_PROG KEYWORD_REL
%token <ptb> KEYWORD_NN KEYWORD_NNS KEYWORD_NNP KEYWORD_NNPS KEYWORD_IN KEYWORD_JJ KEYWORD_JJR KEYWORD_JJS 
%token <ptb> KEYWORD_VB KEYWORD_VBG KEYWORD_VBZ KEYWORD_VBN KEYWORD_VBP KEYWORD_VBD
%token <ptb> KEYWORD_DT KEYWORD_CC KEYWORD_CD KEYWORD_PRP KEYWORD_MD
%token <ptb> KEYWORD_RB
%left AND IMPLY EQUIV ')'

%type<ptb> pos_tag
%type<conntype> connective
%type<node> formula terms formulas argument term quantify_expr
%type<nodelist> arguments
%type<gtype> grammar_term
%start meaning_repr

%%

meaning_repr
    : 
    '(' formulas ')' {
        print_debug("meaning_repr: formula");
        $2->isroot = 1;
        lineNum++;
        colNum = 1;
        ast = $2;
    }
    | formulas {
        print_debug("meaning_repr: formula");
        ast = $1;
        $1->isroot = 1;
        lineNum++;
        colNum = 1;
    }
    ;

formulas
    : '(' formulas ')' connective formula {
        print_debug("formulas: formulas connective formula");
        $$ = newastnode(Connective, NULL);
        $$->conntype = $4;
        addastchild($$, $2);
        addastchild($$, $5);
    }
    | formulas connective formula {
        print_debug("formulas: formulas connective formula");
        $$ = newastnode(Connective, NULL);
        $$->conntype = $2;
        addastchild($$, $1);
        addastchild($$, $3);
    }
    | formulas connective '(' formula ')' {

    }
    | formulas connective MINUS formula {
        print_debug("formulas: formulas connective MINUS formula");
        $$ = newastnode(Connective, NULL);
        $$->conntype = $2;
        $4->isnegative = 1;
        addastchild($$, $1);
        addastchild($$, $4);
    }
    | formula {
        print_debug("formulas: formula");
        $$ = $1;
    }
    | MINUS formula {
        print_debug("formulas: MINUS formula");
        $$ = $2;
        $$->isnegative = 1;
    }
    ;

formula
    : quantify_expr '.' '(' terms ')' {
        print_debug("formula: quantify_expr '(' terms ')'");
        $$ = $1;
        addastchild($$, $4);
        closecstscope($1->token->symbol);
    }
    | '(' quantify_expr '.' '(' terms ')' ')' {
        print_debug("formula: '(' quantify_expr '.' '(' terms ')' ')'");
        $$ = $2;
        addastchild($$, $5);
        closecstscope($2->token->symbol);
    }
    ;

terms
    : terms connective term {
        print_debug("terms: terms term");
        if ($3 == NULL) {
            $$ = $1;
        } else {
            // $$ = newastnode(Connective, $2);
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
    | terms connective MINUS term {
        print_debug("terms: terms term");
        // $$ = newastnode(Connective, $2);
        $$ = newastnode(Connective, NULL);
        $4->isnegative = 1;
        $$->conntype = $2;
        addastchild($$, $1);
        addastchild($$, $4);
    }
    | terms connective MINUS '(' term ')' {
        print_debug("terms: terms term");
        // $$ = newastnode(Connective, $2);
        $$ = newastnode(Connective, NULL);
        $5->isnegative = 1;
        $$->conntype = $2;
        addastchild($$, $1);
        addastchild($$, $5);
    }
    | '(' term ')' {
        
    }
    | term {
        print_debug("terms: term");
        $$ = $1;
    }
    | MINUS term {
        print_debug("term: MINUS term");
        $$ = $2;
        $$->isnegative = 1;
    }
    | MINUS '(' term ')' {
        print_debug("term: MINUS term");
        $$ = $3;
        $$->isnegative = 1;
    }
    ;

term
    : PREDICATE '{' pos_tag '}' '(' arguments ')' {
        print_debug("term: PREDICATE '{' pos_tag '}' '(' arguments ')'");
        #if PARDEBUG
        printf("Predicate(%s), Syntax(%s)\n", $1->symbol, ptbsyntax2string($3));
        #endif
        if ($3 == MD) {
            /* skip the predicates with syntax is Modal, e.g. should, must */
            /* and, the prerequisite is the arguments are formula */
            if (getnodelistlength($6) == 1) {
                $$ = $6->node;
                free($6);
                if ($$->type == Predicate) {
                    enqueue(predicates, (void*)$$);
                }
            } else {
                fprintf(stderr, "This meaning representation is not supported by the current grammar.\n");
            }
        } else {
            $$ = newastnode(Predicate, $1);    
            if ($$->token->symbol[0] == '_') {
                /* removing the underscore */
                popchar($$->token->symbol);
            }
            addastchildren($$, $6);
            $$->syntax = $3;
            /* predicate node is marked in a queue and si identification is processed later  */            
            enqueue(predicates, (void*)$$);
        }        
    }
    | PREDICATE '{' pos_tag '}' '(' '(' terms ')' ')' {
        print_debug("term: PREDICATE(Modal)) '{' pos_tag '}' '(' '(' terms ')' ')'");
        $$ = $7;
    }
    | KEYWORD_TRUEP {
        print_debug("term: PREDICATE(TrueP)");
        $$ = NULL;
    }
    | grammar_term '(' PREDICATE '{' pos_tag '}' '(' arguments ')' ')' {
        print_debug("term: grammar_term '(' PREDICATE '{' pos_tag '}' '(' arguments ')' ')'");
        $$ = newastnode(Predicate, $3);
        if ($$->token->symbol[0] == '_') {
            /* removing the underscore */
            popchar($$->token->symbol);
        }
        $$->syntax = $5;
        $$->gtype = $1;
        addastchildren($$, $8);
        /* predicate node is marked in a queue and si identification is processed later  */
        enqueue(predicates, (void*)$$);
    }
    | formula {
        print_debug("term: formula");
        $$ = $1;
    }
    | '(' formula connective formula ')' {
        print_debug("term: '(' formula connective formula ')'");
        $$ = newastnode(Connective, NULL);
        $$->conntype = $3;
        addastchild($$, $2);
        addastchild($$, $4);
    }
    | GCASE '(' arguments ')' EQUAL arguments {
        print_debug("term: '(' GCASE '(' VARIABLE ')' EQUAL VARIABLE ')'");
        $$ = NULL;
    }
    | GCASE '(' arguments ')' {
        print_debug("term: '(' GCASE '(' arguments ')' ')'");
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

arguments
    : arguments COMMA argument {
        print_debug("arguments: arguments argument");
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
    | formula {
        print_debug("argument: formula");
        $$ = $1;
    }
    ;

quantify_expr
    : KEYWORD_EXISTS IDENTIFIER {
        print_debug("quantify_expr: KEYWORD_EXISTS IDENTIFIER");
        $$ = newastnode(Quantifier, $2);
        $$->qtype = Quantifier_Exists;
        if (addcstref($2->symbol, $$) != 0) {
            addcstsymbol($2->symbol);
            addcstref($2->symbol, $$);
        }
    }
    | KEYWORD_FORALL IDENTIFIER {
        print_debug("quantify_expr: KEYWORD_FORALL IDENTIFIER");
        $$ = newastnode(Quantifier, $2);
        $$->qtype = Quantifier_ForAll;
        if (addcstref($2->symbol, $$) != 0) {
            addcstsymbol($2->symbol);
            addcstref($2->symbol, $$);
        }    
    }
    ;



pos_tag
    : KEYWORD_NN { $$ = NN; }
    | KEYWORD_NNS { $$ = NNS; }
    | KEYWORD_NNP { $$ = NNP; }
    | KEYWORD_NNPS { $$ = NNPS; }
    | KEYWORD_IN { $$ = IN; }
    | KEYWORD_JJ { $$ = JJ; }
    | KEYWORD_JJR { $$ = JJR; }
    | KEYWORD_JJS { $$ = JJS; }
    | KEYWORD_VB { $$ = VB; }
    | KEYWORD_VBZ { $$ = VBZ; }
    | KEYWORD_VBN { $$ = VBN; }
    | KEYWORD_VBP { $$ = VBP; }
    | KEYWORD_DT { $$ = DT; }
    | KEYWORD_CD { $$ = CD; }
    | KEYWORD_CC { $$ = CC; }
    | KEYWORD_PRP { $$ = PRP; }
    | KEYWORD_MD { $$ = MD; }
    | KEYWORD_VBG { $$ = VBG; }
    | KEYWORD_RB  { $$ = RB; }
    | KEYWORD_VBD { $$ = VBD; }
    ;

connective
    : AND { $$ = Op_And; }
    | EQUIV { $$ = Op_Equivalent; }
    | IMPLY { $$ = Op_Imply; }
    | OR    { $$ = Op_Or; }
    ;

grammar_term
    : KEYWORD_PROG { $$ = Gram_Prog; }
    | KEYWORD_REL { $$ = Gram_Rel; }
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
