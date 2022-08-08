%{
    // 2: debug rules. 3: ast
    #define __LOG_LEVEL__ 3
    #include "h2jc.h"
    // #include <unistd.h>
    #include "util.h"
    #include "sst.h"
    #include "dst.h"    
    // #include "dag.h"
    #include "cst.h"
    #include "sem.h"
    // #include "cg.h"
    #define YYERROR_VERBOSE 1

    void print_debug(char *);

    // From h2jc.c
    extern int c;
    extern struct astnode **ast;  
    extern struct queue **conn_queues, **pred_queues, *reftable;

    // c is for the line counter of hols
    int lbracs = 0, rbracs = 0, lineNum = 1, colNum = 1;
%}

%union{
    struct functor *f_val;
    struct variable *v_val;
    struct astnode *node;
    struct astnodelist *nodelist;
    struct token *t;
}

%token <t> ANNOT_PREDICATE VARIABLE EXISTS NOTEXISTS ALL NOTALL COMMA LBRAC RBRAC 
%left AND OR IMPLY EQUIV RBRAC MINUS

%type<node> formula predicate terms MR connective complex_formula predicates
%type<nodelist> varlist
%type<t> AND OR IMPLY EQUIV quantifier MINUS

%start MRs

%%

MRs
    : MRs MR
    {
        print_debug("MRs -> MRs MR");
        ast[c] = $2;
        $2->isroot = 1;
        ++c;      
    }
    | MR 
    {
        print_debug("MRs -> MR");
        ast[c] = $1;
        $1->isroot = 1;
        ++c;        
    }
    ;

MR
    : ALL VARIABLE LBRAC predicate IMPLY LBRAC LBRAC complex_formula RBRAC IMPLY LBRAC predicates RBRAC RBRAC RBRAC
    {
        print_debug("MR -> ALL LBRAC predicate IMPLY LBRAC complex_formula RBRAC IMPLY formula RBRAC");
    }
    | formula IMPLY formula
    {
        print_debug("MR -> formula IMPLY formula");
        $$ = newastnode(NonTrivialConnective, newtoken("==>", $2->line, $2->column));
        addastchild($$, $1);
        addastchild($$, $3);
    }
    | formula EQUIV formula
    {
        print_debug("MR -> formula EQUIV formula");
        $$ = newastnode(NonTrivialConnective, newtoken("<==>", $2->line, $2->column));
        addastchild($$, $1);
        addastchild($$, $3);
    }
    | formula AND formula
    {
        print_debug("MR -> formula AND formula");
        $$ = newastnode(NonTrivialConnective, newtoken("&&", $2->line, $2->column));
        addastchild($$, $1);
        addastchild($$, $3);
    }
    | LBRAC complex_formula RBRAC IMPLY formula
    {
        print_debug("LBRAC formula RBRAC IMPLY formula");
        $$ = newastnode(NonTrivialConnective, newtoken("==>", $4->line, $4->column));
        addastchild($$, $2);
        addastchild($$, $5);
    }
    | formula IMPLY LBRAC complex_formula RBRAC
    {
        print_debug("formula IMPLY LBRAC formula RBRAC");
        $$ = newastnode(NonTrivialConnective, newtoken("==>", $2->line, $2->column));
        addastchild($$, $1);
        addastchild($$, $4);
    }
    | LBRAC complex_formula RBRAC IMPLY LBRAC complex_formula RBRAC
    {
        print_debug("LBRAC formula RBRAC IMPLY LBRAC formula RBRAC");
        $$ = newastnode(NonTrivialConnective, newtoken("==>", $4->line, $4->column));
        addastchild($$, $2);
        addastchild($$, $6);
    }
    | ANNOT_PREDICATE LBRAC varlist RBRAC
    {
        // The case of if statement, which has a clause followed by an if-clause.
        // such as, A, if B.
        print_debug("ANNOT_PREDICATE LBRAC varlist RBRAC");
        $$ = newastnode(NonTrivialConnective, newtoken("==>", $1->line, $1->column));
        addastchildren($$, $3);
    }
    | formula 
    {
        $$ = $1;
    }
    ;

complex_formula
    : complex_formula connective formula
    {
        $$ = newcomplexformula($1, $2, $3);
        // $$ = $2;
        // $$->type = NonTrivialConnective;
        // if (strcmp($$->token->symbol, "&") == 0) {
        //     free($$->token->symbol);
        //     $$->token->symbol = (char*)strdup("&&");
        // } else if (strcmp($$->token->symbol, "|") == 0) {
        //     free($$->token->symbol);
        //     $$->token->symbol = (char*)strdup("||");
        // } else {
        //     #if DEBUG
        //     fprintf(stderr, "Issue (%s)\n", $$->token->symbol);
        //     #endif
        //     yyerror("Unknown connective encountered between formulas");
        // }
        // addastchild($$, $1);
        // addastchild($$, $3);
    }
    | LBRAC complex_formula RBRAC connective formula
    {
        $$ = newcomplexformula($2, $4, $5);
    }
    | LBRAC complex_formula RBRAC connective LBRAC formula RBRAC
    {
        $$ = newcomplexformula($2, $4, $6);
    }
    | complex_formula connective LBRAC formula RBRAC
    {
        $$ = newcomplexformula($1, $2, $4);
    }
    | formula
    {
        $$ = $1;
    }
    ;

formula
    : quantifier VARIABLE LBRAC terms RBRAC
    {
        print_debug("formula -> quantifier VARIABLE LBRAC terms RBRAC");
        #if DEBUG
        fprintf(stderr, "quantifying variable: %s\n", $2->symbol);
        #endif
        $$ = newformula($1, $2, $4);   
    }
    ;

terms
    : terms connective predicate
    {
        print_debug("terms -> terms connective predicate");
        $$ = $2;
        enqueue(conn_queues[c], (void*)$$);
        addastchild($$, $1);
        addastchild($$, $3);
    }
    | terms connective LBRAC terms RBRAC
    {
        print_debug("terms -> terms connective LBRAC terms RBRAC");
        $$ = $2;
        enqueue(conn_queues[c], (void*)$$);
        addastchild($$, $1);
        addastchild($$, $4);
    }
    | terms connective formula
    {
        print_debug("terms -> terms connective quantifier VARIABLE LBRAC terms RBRAC");
        $$ = $2;
        enqueue(conn_queues[c], (void*)$$);
        addastchild($$, $1);
        // addastchild($$, newqexpr($3, $4, $6));  
        addastchild($$, $3);
    }
    | terms connective MINUS LBRAC terms RBRAC
    {
        print_debug("terms connective MINUS LBRAC terms RBRAC");
        $$ = $2;
        enqueue(conn_queues[c], (void*)$$);
        addastchild($$, $1);
        addastchild($$, $5);
        $5->isnegative = 1;      
    }
    | LBRAC formula RBRAC
    {
        print_debug("terms -> LBRAC formula RBRAC");
        $$ = $2;
    }
    | predicate
    {
        print_debug("terms -> predicate");
        $$ = $1;
    }
    ;

connective
    : AND 
    {
        $$ = newastnode(Connective, $1);
    }
    | OR 
    {
        $$ = newastnode(Connective, $1);
    }
    | IMPLY
    {
        print_debug("connective -> IMPLY");
        $$ = newastnode(Connective, $1);
    }
    | EQUIV
    {
        $$ = newastnode(Connective, $1);
    }
    ;

predicates
    : predicates connective predicate
    {
        $$ = $2;
        addastchild($$, $1);
        addastchild($$, $3);
    }
    | predicate
    {
        $$ = $1;
    }
    ;

predicate
    : ANNOT_PREDICATE LBRAC varlist RBRAC
    {
        print_debug("predicate -> ANNOT_PREDICATE LBRAC varlist RBRAC");
        #if DEBUG
        fprintf(stderr, "predicate -> ANNOT_PREDICATE(%s) LBRAC varlist RBRAC\n", $1->symbol);
        #endif        
        $$ = newpred($1, $3);
    }
    ;

varlist
    : varlist COMMA VARIABLE
    {
        #if DEBUG
        fprintf(stderr, "varlist -> varlist COMMA VARIABLE(%s)\n", $3->symbol);
        #endif
        struct astnode *v = newastnode(Variable, $3);
        enqueue(reftable, (void*)v);
        appendnode($$, v);
    }
    | varlist COMMA formula
    {
        #if DEBUG
        fprintf(stderr, "varlist -> varlist COMMA formula\n");
        #endif
        appendnode($$, $3);
    }
    | formula
    {
        $$ = newastnodelist($1);
    }
    | VARIABLE
    {
        #if DEBUG
        fprintf(stderr, "varlist -> VARIABLE(%s)\n", $1->symbol);
        #endif
        struct astnode *v = newastnode(Variable, $1);
        enqueue(reftable, (void*)v);
        $$ = newastnodelist(v);
    }
    ;

quantifier
    : EXISTS
    {
        print_debug("quantifier -> EXISTS");
        $$ = $1;
    }
    | NOTEXISTS
    {
        print_debug("quantifier -> NOT EXISTS");
        $$ = $1;
    }
    | ALL
    {
        print_debug("quantifier -> ALL");
        $$ = $1;
    }
    | NOTALL
    {
        print_debug("quantifier -> NOTALL");
        $$ = $1;
    }
    ;
%%


void print_debug(char *s) {
    #if DEBUG
    printf("%s\n", s);
    #endif  
}

extern
void yyerror(const char *s) {
   /* extern char* yytext;
  printf("EEK, parse error!  Message: %s at token %s at line %d column %d\n", s, yytext, lineNum, colNum); */
    extern char* input_line;
    fprintf(stderr,"EEK, parse error!  Message: %s in line %d, column %d\n", s, lineNum, colNum);
    fprintf(stderr,"%s\n", input_line);
    free(input_line);
    for(int i = 0; i < colNum - 1; i++)
        fprintf(stderr,"_");
    fprintf(stderr,"^\n"); 
    exit(-1);
}
