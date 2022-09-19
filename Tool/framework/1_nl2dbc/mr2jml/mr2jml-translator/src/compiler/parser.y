%{
    #include "core.h"
    #include "util.h"
    #define YYERROR_VERBOSE 1

    void print_debug(char *);

    // From main.c
    extern int c;
    extern struct astnode **ast;  
    extern struct queue **conn_queues, **predicates, *reftable;

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
%token <t> COMMA LBRAC RBRAC GCASE EQUAL AND IMPLY EQUIV CURLY_LBRAC CURLY_RBRAC
%token <t> KEYWORD_FORALL KEYWORD_EXISTS
%token <t> KEYWORD_PROG
%token <ptb> KEYWORD_NN KEYWORD_NNS KEYWORD_NNP KEYWORD_NNPS KEYWORD_IN KEYWORD_JJ KEYWORD_JJR KEYWORD_JJS KEYWORD_VB KEYWORD_VBG
%token <ptb> KEYWORD_VBZ KEYWORD_VBN KEYWORD_VBP KEYWORD_DT KEYWORD_CC KEYWORD_CD KEYWORD_PRP KEYWORD_MD
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
    : LBRAC formulas RBRAC {
        print_debug("meaning_repr: formula");
        $$ = $2;
    }
    | formulas {
        print_debug("meaning_repr: formula");
        $$ = $1;
    }
    ;

formulas:
    formulas connective formula {
        print_debug("formulas: formulas connective formula");
        $$ = newastnode(Connective, NULL);
        $$->conntype = $2;
        addastchild($$, $1);
        addastchild($$, $3);
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
    }
    ;

terms
    : terms AND term {
        print_debug("terms: terms term");
        if ($3 == NULL) {
            $$ = $1;
        } else {
            $$ = newastnode(Connective, $2);
            addastchild($$, $1);
            addastchild($$, $3);
        }
    }
    | terms AND MINUS term {
        print_debug("terms: terms term");
        $$ = newastnode(Connective, $2);
        $4->isnegative = 1;
        addastchild($$, $1);
        addastchild($$, $4);
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
    ;

term
    : PREDICATE CURLY_LBRAC pos_tag CURLY_RBRAC LBRAC arguments RBRAC {
        print_debug("term: PREDICATE CURLY_LBRAC pos_tag CURLY_RBRAC LBRAC arguments RBRAC");
        $$ = newastnode(Predicate, $1);    
        addastchildren($$, $6);
        $$->syntax = $3;
        /* predicate node is marked in a queue and si identification is processed later  */
        enqueue(predicates[c], (void*)$$);
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
    | LBRAC GCASE LBRAC arguments RBRAC EQUAL arguments RBRAC {
        print_debug("term: LBRAC GCASE LBRAC VARIABLE RBRAC EQUAL VARIABLE RBRAC");
        $$ = NULL;
    }
    | LBRAC GCASE LBRAC arguments RBRAC {
        print_debug("term: LBRAC GCASE LBRAC arguments RBRAC");
        $$ = NULL;
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
    }
    | formula {
        print_debug("argument: formula");
        $$ = $1;
    }
    ;

quantify_expr
    : KEYWORD_EXISTS IDENTIFIER {
        $$ = newastnode(Quantifier, $2);
        $$->qtype = Quantifier_Exists;
    }
    | KEYWORD_FORALL IDENTIFIER {
        $$ = newastnode(Quantifier, $2);
        $$->qtype = Quantifier_ForAll;
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
    ;

connective
    : AND { $$ = Op_And; }
    | EQUIV { $$ = Op_Equivalent; }
    | IMPLY { $$ = Op_Imply; }
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
