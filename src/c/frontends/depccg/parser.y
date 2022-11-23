%{
    #include "core.h"
    #include "util.h"
    #include "cst.h"
    #define YYERROR_VERBOSE 1
    #if EVENT_SEMANTICS
    #include "event.h"
    #endif

    void print_debug(char *);

    // From main.c
    extern int c;
    extern struct astnode **ast;  
    extern struct queue **csts, **predicates, **operators;
    #if EVENT_SEMANTICS
    extern struct queue **events;
    #endif

    // c is for the line counter of hols
    int lbracs = 0, rbracs = 0, lineNum = 1, colNum = 1, *error_lines, error_count = 0;
%}

%union{
    struct astnode *node;
    struct astnodelist *nodelist;
    struct token *t;
    enum ptbsyntax ptb;
    enum conntype conntype;
    enum grammartype gtype;
}

%token <t> PREDICATE IDENTIFIER KEYWORD_TRUEP DOT MINUS
%token <t> COMMA LBRAC RBRAC GCASE EQUAL AND OR IMPLY EQUIV CURLY_LBRAC CURLY_RBRAC
%token <t> KEYWORD_FORALL KEYWORD_EXISTS
%token <t> KEYWORD_PROG
%token <ptb> KEYWORD_NN KEYWORD_NNS KEYWORD_NNP KEYWORD_NNPS KEYWORD_IN KEYWORD_JJ KEYWORD_JJR KEYWORD_JJS 
%token <ptb> KEYWORD_VB KEYWORD_VBG KEYWORD_VBZ KEYWORD_VBN KEYWORD_VBP KEYWORD_VBD
%token <ptb> KEYWORD_DT KEYWORD_CC KEYWORD_CD KEYWORD_PRP KEYWORD_MD
%token <ptb> KEYWORD_RB
%left AND IMPLY EQUIV RBRAC

%type<ptb> pos_tag
%type<conntype> connective
%type<node> formula terms meaning_repr formulas argument term quantify_expr
%type<nodelist> arguments
%type<gtype> grammar_term
%start meaning_reprs

%%

meaning_reprs
    : meaning_reprs meaning_repr
    {
        print_debug("meaning_reprs: meaning_reprs meaning_repr");
        ast[c] = $2;
        $2->isroot = 1;
        ++c;      
        lineNum++;
        colNum = 1;
    }
    | meaning_repr 
    {
        print_debug("meaning_reprs: meaning_repr");
        ast[c] = $1;
        $1->isroot = 1;
        ++c;  
        lineNum++;
        colNum = 1;      
    }
    ;

meaning_repr
    : 
    LBRAC formulas RBRAC {
        print_debug("meaning_repr: formula");
        $$ = $2;
    }
    | formulas {
        print_debug("meaning_repr: formula");
        $$ = $1;
    }
    ;

formulas
    : LBRAC formulas RBRAC connective formula {
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
    | formulas connective LBRAC formula RBRAC {

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
    : quantify_expr DOT LBRAC terms RBRAC {
        print_debug("formula: quantify_expr LBRAC terms RBRAC");
        $$ = $1;
        addastchild($$, $4);
        closecstscope(csts[c], $1->token->symbol);
    }
    | LBRAC quantify_expr DOT LBRAC terms RBRAC RBRAC {
        print_debug("formula: LBRAC quantify_expr DOT LBRAC terms RBRAC RBRAC");
        $$ = $2;
        addastchild($$, $5);
        closecstscope(csts[c], $2->token->symbol);
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
    | terms connective LBRAC term RBRAC {
        print_debug("terms: terms connective LBRAC term RBRAC");
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
    | terms connective MINUS LBRAC term RBRAC {
        print_debug("terms: terms term");
        // $$ = newastnode(Connective, $2);
        $$ = newastnode(Connective, NULL);
        $5->isnegative = 1;
        $$->conntype = $2;
        addastchild($$, $1);
        addastchild($$, $5);
    }
    | LBRAC term RBRAC {
        
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
    | MINUS LBRAC term RBRAC {
        print_debug("term: MINUS term");
        $$ = $3;
        $$->isnegative = 1;
    }
    ;

term
    : PREDICATE CURLY_LBRAC pos_tag CURLY_RBRAC LBRAC arguments RBRAC {
        print_debug("term: PREDICATE CURLY_LBRAC pos_tag CURLY_RBRAC LBRAC arguments RBRAC");
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
                    enqueue(predicates[c], (void*)$$);
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
            enqueue(predicates[c], (void*)$$);
        }        
    }
    | PREDICATE CURLY_LBRAC pos_tag CURLY_RBRAC LBRAC LBRAC terms RBRAC RBRAC {
        print_debug("term: PREDICATE(Modal)) CURLY_LBRAC pos_tag CURLY_RBRAC LBRAC LBRAC terms RBRAC RBRAC");
        $$ = $7;
    }
    | KEYWORD_TRUEP {
        print_debug("term: PREDICATE(TrueP)");
        $$ = NULL;
    }
    | grammar_term LBRAC PREDICATE CURLY_LBRAC pos_tag CURLY_RBRAC LBRAC arguments RBRAC RBRAC {
        print_debug("term: grammar_term LBRAC PREDICATE CURLY_LBRAC pos_tag CURLY_RBRAC LBRAC arguments RBRAC RBRAC");
        $$ = newastnode(Predicate, $3);
        if ($$->token->symbol[0] == '_') {
            /* removing the underscore */
            popchar($$->token->symbol);
        }
        $$->syntax = $5;
        $$->gtype = $1;
        addastchildren($$, $8);
        /* predicate node is marked in a queue and si identification is processed later  */
        enqueue(predicates[c], (void*)$$);
    }
    | formula {
        print_debug("term: formula");
        $$ = $1;
    }
    | LBRAC formula connective formula RBRAC {
        print_debug("term: LBRAC formula connective formula RBRAC");
        $$ = newastnode(Connective, NULL);
        $$->conntype = $3;
        addastchild($$, $2);
        addastchild($$, $4);
    }
    /* | GCASE LBRAC arguments RBRAC EQUAL arguments { */
    | GCASE LBRAC arguments RBRAC EQUAL arguments {
        print_debug("term: LBRAC GCASE LBRAC VARIABLE RBRAC EQUAL VARIABLE RBRAC");
        #if EVENT_SEMANTICS
        addevententity(newevent(events[c], $3->node->token->symbol), $6->node->token->symbol, $1->symbol);
        #endif
        $$ = NULL;
        //CAUTION: there is a memory leak here. the two arguments are in structure of astnode. therefore, they are not freed in the later operations.
    }
    | GCASE LBRAC arguments RBRAC {
        print_debug("term: LBRAC GCASE LBRAC arguments RBRAC RBRAC");
        $$ = NULL;
    }
    | IDENTIFIER EQUAL IDENTIFIER {
        print_debug("term: LBRAC IDENTIFIER EQUAL IDENTIFIER RBRAC");
        $$ = newastnode(Operator, $2);
        struct astnode *left = newastnode(Variable, $1);
        struct astnode *right = newastnode(Variable, $3);
        if (addcstref(csts[c], $1->symbol, left) != 0) {
            addcstsymbol(csts[c], $1->symbol);
            addcstref(csts[c], $1->symbol, left);
        }
        if (addcstref(csts[c], $3->symbol, right) != 0) {
            addcstsymbol(csts[c], $3->symbol);
            addcstref(csts[c], $3->symbol, right);
        }
        addastchild($$, left);
        addastchild($$, right);
        enqueue(operators[c], (void*)$$);
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
        if (addcstref(csts[c], $1->symbol, $$) != 0) {
            addcstsymbol(csts[c], $1->symbol);
            addcstref(csts[c], $1->symbol, $$);
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
        if (addcstref(csts[c], $2->symbol, $$) != 0) {
            addcstsymbol(csts[c], $2->symbol);
            addcstref(csts[c], $2->symbol, $$);
        }
    }
    | KEYWORD_FORALL IDENTIFIER {
        print_debug("quantify_expr: KEYWORD_FORALL IDENTIFIER");
        $$ = newastnode(Quantifier, $2);
        $$->qtype = Quantifier_ForAll;
        if (addcstref(csts[c], $2->symbol, $$) != 0) {
            addcstsymbol(csts[c], $2->symbol);
            addcstref(csts[c], $2->symbol, $$);
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
    error_lines[error_count++] = lineNum;
    free(input_line);
    for(int i = 0; i < colNum - 1; i++)
        fprintf(stderr,"_");
    fprintf(stderr,"^\n"); 
    /* exit(-1); */    
}
