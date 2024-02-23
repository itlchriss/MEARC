%{
    #include "core.h"
    #include "util.h"
    #include "cst.h"
    #include "si.h"
    #include "event.h"
    #include "regex.h"
    #define YYERROR_VERBOSE 1

    void print_debug(char *);
    void print_semantic_error(char *);
    enum explicit_datatype string2javadatatype(char *);

    // From main.c
    // extern int c;
    extern struct astnode *ast;  
    extern struct queue *predicates, *operators;
    extern struct queue *events;

    extern struct queue *scopes;   

    /* for parsing only */
    extern struct queue *_events; 

    struct scope {
        /* symbol of the scope */
        char *symbol;
        /* datatype of the symbol, if there is a type_term before the symbol is declared */
        enum explicit_datatype datatype;
        struct queue *astnodes;
    };

    struct _event {
        struct astnode *_subtree_root;
        struct astnode *_eventnode;
        struct astnode *_entitynode;
    };

    enum gramtype __string2gramtype(char *input) {
        if (strcmp(input, "Subj") == 0) return SubjectOf;
        else if (strcmp(input, "Acc") == 0) return AccusationOf;
        else if (strcmp(input, "AccI") == 0) return IntentionalAccusationOf; 
        else if (strcmp(input, "Dat") == 0) return Dative;
        else if (strcmp(input, "Gen") == 0) return Genitive; 
        else if (strcmp(input, "Abl") == 0) return Ablative;
        else if (strcmp(input, "Rel") == 0) return Relative;
        else if (strcmp(input, "Voc") == 0) return Vocative; 
        else if (strcmp(input, "AccE") == 0) return ExtentionalAccusationOf;
        else {
            fprintf(stderr, "Unknown Grammar type %s in the MR\n", input);
            exit(-2);
        }
    }

    struct _event *newtmpevent(struct astnode *_subtree_root, struct astnode *_eventnode, struct astnode *_entitynode) {
        struct _event *new = (struct _event *)malloc(sizeof(struct _event));
        new->_subtree_root = _subtree_root;
        new->_eventnode = _eventnode;
        new->_entitynode = _entitynode;
        return new;
    }

    void pruneeventsubtrees() {
        while (!isempty(_events)) {
            struct _event *_e = (struct _event*)dequeue(_events);
            struct event *event = newevent(_e->_eventnode->cstptr);
            enqueue(event->entities, (void *)newentity(_e->_entitynode->cstptr, __string2gramtype(_e->_subtree_root->token->symbol)));
            deallocateast(_e->_subtree_root);
        }
    }

    struct scope *newscope(char *_symbol) {
        struct scope *new = (struct scope *)malloc(sizeof(struct scope));
        new->astnodes = initqueue();
        new->symbol = (char *)strdup(_symbol);
        new->datatype = None;
        return new;
    }

    void deallocatescope(void *_scope) {
        struct scope *scope = (struct scope *)_scope;
        free(scope->symbol);
        deallocatequeue(scope->astnodes, NULL);
        free(scope);
    }

    struct scope *searchscope(char *_symbol) {
        for (int i = 0; i < scopes->count; ++i) {
            struct scope *_scope = (struct scope *)gqueue(scopes, i);
            if (strcmp(_scope->symbol, _symbol) == 0) return _scope;
        }
        return NULL;
    }

    void refcst2nodesinscope(struct scope *_scope, struct cstsymbol *_cstptr) {
        while (!isempty(_scope->astnodes)) {
            struct astnode *node = (struct astnode *)dequeue(_scope->astnodes);
            node->cstptr = _cstptr;
        }
    }

    void popscope(struct scope *_scope) {        
        for (int i = 0; i < scopes->count; ++i) {
            struct scope *_tmp = (struct scope *)gqueue(scopes, i);
            if (_scope == _tmp) {
                deallocatescope(_scope);
                rqueue(scopes, i);
                break;
            }
        }
    }

    struct scope *assignscope(struct astnode *node) {
        struct scope *_scope = NULL;
        if ((_scope = searchscope(node->token->symbol)) == NULL) {
            _scope = newscope(node->token->symbol);
        }
        enqueue(_scope->astnodes, node);
        enqueue(scopes, (void *)_scope);    
        return _scope;
    }

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
%token <t> TAG EVENT
%left AND IMPLY EQUIV ')'

/* %type<ptb> pos_tag */
%type<conntype> connective
%type<node> terms argument term quantified_term predicate_term grammar_term type_term param_term
%type<nodelist> arguments
/* %type<gtype> grammar_relation */
%start formula

%%

formula
    : quantified_term {
        print_debug("formula: terms");
        ast = $1;
        $1->isroot = 1;
        pruneeventsubtrees();
        deallocatequeue(scopes, NULL);
        deallocatequeue(_events, NULL);
    }
    | NEG quantified_term {
        print_debug("formula: NEG terms");
        ast = $2;
        $2->isnegative = 1;
        $2->isroot = 1;
        pruneeventsubtrees();
        deallocatequeue(scopes, NULL);
        deallocatequeue(_events, NULL);
    }    
    ;


terms
    : term connective terms {
        print_debug("(top rule) terms: terms connective term");
        if ($3 == NULL) {
            /* case of terms connective TrueP */
            $$ = $1;
        } else if ($1 == NULL) {
            /* case of TrueP connective term */
            $$ = $3;
        } else {
            $$ = newastnode(Connective, NULL);
            $$->conntype = $2;
            addastchild($$, $1);
            addastchild($$, $3);
        }
    }
    | term connective '(' terms ')' {
        print_debug("terms: terms connective '(' term ')'");
        if ($4 == NULL) {
            $$ = $1;
        } else if ($1 == NULL) {
            $$ = $4;
        } else {
            $$ = newastnode(Connective, NULL);
            $$->conntype = $2;
            addastchild($$, $1);
            addastchild($$, $4);
        }
    }
    | term {
        print_debug("terms: term");
        if ($1 == NULL) {
            $$ = NULL;
        } else {
            $$ = $1;
        }
    }
    | NEG term {
        print_debug("term: NEG term");
        $$ = $2;
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
    | event_term {
        $$ = NULL;
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
        assignscope(left);
        assignscope(right);
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
        // struct cstsymbol* c = searchcst($6->node->token->symbol);
        for (int i = 0; i < 6; ++i) {
            popchar($1->symbol);
        }
        struct scope *_scope = searchscope($6->node->token->symbol);
        /* the type has already been resolved. so we no longer need this node */
        for (int i = 0; i < _scope->astnodes->count; ++i) {
            struct astnode *_node = (struct astnode *)gqueue(_scope->astnodes, i);
            if (_node == $6->node) {
                rqueue(_scope->astnodes, i);
                break;
            }
        };
        _scope->datatype = string2javadatatype($1->symbol);
        if (_scope->datatype == -1) {
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
        // printf("PARAM(%s), Syntax(%s)\n", $1->symbol, $3->symbol); 
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
        assignscope($$);
    }
    | terms {
        print_debug("argument: terms");
        $$ = $1;
    }
    ;

event_term
    : '(' EVENT '(' IDENTIFIER ')' EQUAL IDENTIFIER ')' {
        print_debug("event_term: '(' EVENT '(' IDENTIFIER ')' EQUAL IDENTIFIER ')'"); 
        struct astnode *subtree_root = newastnode(GrammarNotation, $2);
        struct astnode *eventnode = newastnode(Variable, $4);
        struct astnode *entitynode = newastnode(Variable, $7);
        addastchild(subtree_root, eventnode);
        addastchild(subtree_root, entitynode);
        assignscope(eventnode);
        assignscope(entitynode);
        enqueue(_events, (void *)newtmpevent(subtree_root, eventnode, entitynode));
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
        addastchild($$, $5);  
        struct scope *_scope = searchscope($2->symbol);
        $$->cstptr = newcstsymbol($2->symbol);
        $$->cstptr->datatype = _scope->datatype;
        $$->cstptr->ref_count = _scope->astnodes->count;
        refcst2nodesinscope(_scope, $$->cstptr);    
        popscope(_scope);
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

enum explicit_datatype string2javadatatype(char *s) {
    if (strcmp(s, "array") == 0 || strcmp(s, "arrays") == 0)  return JavaArray;
    else if (strcmp(s, "list") == 0) return JavaList;
    else if (strcmp(s, "integer") == 0) return JavaInteger;
    else if (strcmp(s, "short") == 0) return JavaShort;
    else if (strcmp(s, "long") == 0) return JavaLong;
    else if (strcmp(s, "float") == 0) return JavaFloat;
    else if (strcmp(s, "double") == 0) return JavaDouble;
    else return None;
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
