%{
    #include "core.h"
    #include "util.h"
    #define YYERROR_VERBOSE 1

    void print_debug(char *);

    // From main.c
    extern int c;
    extern struct astnode **ast;  
    extern struct queue **conn_queues, **pred_queues, *reftable;

    // c is for the line counter of hols
    int lbracs = 0, rbracs = 0, lineNum = 1, colNum = 1, *error_lines, error_count = 0;
%}

%union{
    struct astnode *node;
    struct astnodelist *nodelist;
    struct token *t;
    enum ptbsyntax ptb;
    enum conntype conntype;
}

%token <t> PREDICATE VARIABLE COMMA LBRAC RBRAC GCASE EQUAL AND IMPLY EQUIV
%token <t> KEYWORD_FORALL KEYWORD_EXISTS KEYWORD_NOT_FORALL KEYWORD_NOT_EXISTS
%token <ptb> KEYWORD_NN KEYWORD_NNS KEYWORD_NNP KEYWORD_NNPS KEYWORD_IN KEYWORD_JJ KEYWORD_JJR KEYWORD_JJS KEYWORD_VB        
%token <ptb> KEYWORD_VBZ KEYWORD_VBN KEYWORD_VBP KEYWORD_DT KEYWORD_CC KEYWORD_CD KEYWORD_PRP
%left AND IMPLY EQUIV RBRAC

%type<ptb> pos_tag
%type<conntype> connective
%type<node> formula terms meaning_repr formulas argument term quantify_expr
%type<nodelist> arguments

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
        print_debug("formulas: formulas formula");
        $$ = newastnode(Connective, NULL);
        $$->conntype = $2;
        addastchild($$, $1);
        addastchild($$, $3);
    }
    | formula {
        print_debug("formulas: formula");
        $$ = $1;
    }
    ;

formula
    : quantify_expr LBRAC terms RBRAC {
        print_debug("formula: quantify_expr LBRAC terms RBRAC");
        $$ = $1;
        addastchild($$, $3);
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
    | term {
        print_debug("terms: term");
        $$ = $1;
    }
    ;

term
    : PREDICATE pos_tag LBRAC arguments RBRAC {
        print_debug("term: PREDICATE POS_TAG LBRAC arguments RBRAC");
        $$ = newastnode(Predicate, $1);    
        addastchildren($$, $4);
        $$->ptbsyntax = $2;
    }
    | formula {
        print_debug("term: formula");
        $$ = $1;
    }
    | LBRAC GCASE LBRAC argument RBRAC EQUAL argument RBRAC {
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
    : VARIABLE {
        print_debug("argument: VARIABLE");
        $$ = newastnode(Variable, $1);
    }
    | formula {
        print_debug("argument: formula");
        $$ = $1;
    }
    ;

quantify_expr
    : KEYWORD_EXISTS VARIABLE {
        $$ = newastnode(Quantifier, $2);
        $$->qtype = Quantifier_Exists;
    }
    | KEYWORD_NOT_EXISTS VARIABLE {
        $$ = newastnode(Quantifier, $2);
        $$->qtype = Quantifier_Exists;
        $$->isnegative = 1;
    }
    | KEYWORD_FORALL VARIABLE {
        $$ = newastnode(Quantifier, $2);
        $$->qtype = Quantifier_ForAll;
    }
    | KEYWORD_NOT_FORALL VARIABLE {
        $$ = newastnode(Quantifier, $2);
        $$->qtype = Quantifier_ForAll;
        $$->isnegative = 1;
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

connective
    : AND { $$ = Op_And; }
    | EQUIV { $$ = Op_Equivalent; }
    | IMPLY { $$ = Op_Imply; }
%%


void print_debug(char *s) {
    #if DEBUG
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
