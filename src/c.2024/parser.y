%{
    #include "core.h"
    #include "util.h"
    #include "cst.h"
    #include "si.h"
    #include "event.h"
    #include "regex.h"
    #include "error.h"
    #include "tok.h"
    #define YYERROR_VERBOSE 1

    void print_debug(char *);
    void print_semantic_error(char *);

    // From main.c
    // extern int c;
    extern struct astnode *ast;  
    extern struct queue *predicates, *operators;
    extern struct queue *events, *silist;


    /* for parsing only */
    extern struct queue *_events, *_datarefs;

    struct _dataref {
        struct astnode *node;
        /* datatype of the node, if there is a type_term before the symbol is declared */
        // enum explicit_datatype datatype;
        struct datatype *datatype;
    };

    struct _event {
        struct astnode *_subtree_root;
        struct astnode *_eventnode;
        struct astnode *_entitynode;
    };

    int __tmp_event_match_entity_variable__(void *_e, void *_entity_variable) {
        struct _event *e = (struct _event *)_e;
        char *entity_var = (char *)_entity_variable;
        if (strcmp(e->_entitynode->token->symbol, entity_var) == 0) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

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

    // struct _dataref *newtmpdataref(struct astnode *_node, enum explicit_datatype datatype) {
    struct _dataref *newtmpdataref(struct astnode *_node, struct datatype *_datatype) {
        struct _dataref *new = (struct _dataref *)malloc(sizeof(struct _dataref));
        new->node = _node;
        new->datatype = _datatype;
        return new;
    }

    void pruneeventsubtrees() {
        while (!isempty(_events)) {
            struct _event *_e = (struct _event*)dequeue(_events);
            struct event *event = newevent(_e->_eventnode->cstptr);
            enqueue(event->entities, (void *)newentity(_e->_entitynode->cstptr, __string2gramtype(_e->_subtree_root->token->symbol)));

            deleteastnodeandedge(_e->_subtree_root, ast);
        }
    }

    int __search_syntax(struct si* si, enum ptbsyntax ptb) {
        for (int i = 0; i < si->syntax->count; ++i) {
            if ((enum ptbsyntax)gqueue(si->syntax, i) == ptb) {
                return TRUE;
            }
        }
        return FALSE;
    }

    int __javatype_simatcher(void *_si, void *_astnode) {
        struct si* si = (struct si*)_si;
        struct astnode *node = (struct astnode *)_astnode;
        if (strcmp(node->token->symbol, si->symbol) == 0 &&
                    __search_syntax(si, node->syntax) == TRUE) 
            return TRUE;
        else
            return FALSE;
    }

    struct queue *search_symbols(struct astnode *subtree_root, char *symbol) {
        struct queue *queue = initqueue();
        struct queue *cstptrs = initqueue();
        for (int i = 0; i < countastchildren(subtree_root); ++i) {
            enqueue(queue, (void *)getastchild(subtree_root, i));
        }
        while (!isempty(queue)) {
            struct astnode *node = (struct astnode *)dequeue(queue);            
            if ((node->type == Variable || node->type == EventEntity || node->type == EventVariable) && strcmp(node->token->symbol, symbol) == 0 && node->cstptr == NULL) {
                enqueue(cstptrs, (void *)node);
            }
            for (int i = 0; i < countastchildren(node); ++i) {
                enqueue(queue, (void *)getastchild(node, i));
            }
        }        
        deallocatequeue(queue, NULL);
        return cstptrs;
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
%type<node> terms argument term quantified_term predicate_term grammar_term type_term param_term event_term pronoun_term
%type<nodelist> arguments
/* %type<gtype> grammar_relation */
%start formula

%%

formula
    // : quantified_term {
    //     print_debug("formula: terms");
    //     ast = $1;
    //     $1->isroot = 1;
    //     pruneeventsubtrees();
    //     deallocatequeue(_events, NULL);
    //     deallocatequeue(_datarefs, NULL);
    // }
    // | NEG quantified_term {
    //     print_debug("formula: NEG terms");
    //     ast = $2;
    //     $2->isnegative = 1;
    //     $2->isroot = 1;
    //     pruneeventsubtrees();
    //     deallocatequeue(_events, NULL);
    //     deallocatequeue(_datarefs, NULL);
    // }
    // | terms IMPLY terms {

    // }    
    // ;
    : terms {
        ast = $1;
        $1->isroot = 1;
        pruneeventsubtrees();
        deallocatequeue(_events, NULL);
        deallocatequeue(_datarefs, NULL);
    }
    // | NEG terms {
    //     ast = $2;
    //     $2->isroot = 1;
    //     $2->isnegative = 1;
    //     pruneeventsubtrees();
    //     deallocatequeue(_events, NULL);
    //     deallocatequeue(_datarefs, NULL);
    // }
    ;


terms
    :  NEG term connective terms {
        print_debug("(top rule) terms: NEG terms connective term");
        $2->isnegative = 1;
        if ($4 == NULL) {
            /* case of terms connective TrueP */
            $$ = $2;
        } else {
            $$ = newastnode(Connective, NULL);
            $$->conntype = $3;
            addastchild($$, $2);
            addastchild($$, $4);
        }
    }
    | term connective terms {
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
    | '(' terms ')' connective term  {
        print_debug("terms: terms connective '(' term ')'");
        if ($2 == NULL) {
            $$ = $5;
        } else if ($5 == NULL) {
            $$ = $2;
        } else {
            $$ = newastnode(Connective, NULL);
            $$->conntype = $4;
            addastchild($$, $2);
            addastchild($$, $5);
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
    // | NEG '(' term ')' {
    //     print_debug("term: NEG term");
    //     $$ = $3;
    //     $$->isnegative = 1;
    // }
    | NEG '(' terms ')' {
        print_debug("NEG '(' terms ')'");
        $$ = $3;
        $$->isnegative = 1;
    }
    ;


term
    : predicate_term {
        $$ = $1;
    }
    | type_term {
        $$ = $1;
    }
    | param_term {
        $$ = $1;
    }
    | quantified_term {
        $$ = $1;
    }
    | event_term {
        // $$ = NULL;
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
        addastchild($$, left);
        addastchild($$, right);
        enqueue(operators, (void*)$$);
    }
    | pronoun_term {
        $$ = $1;
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

pronoun_term
    : '(' IDENTIFIER EQUAL PREDICATE '{' TAG '}' ')' {
        print_debug("pronoun_term: IDENTIFIER EQUAL PREDICATE '{' TAG '}'");
        $$ = newastnode(Pronoun, $4);
        if ($$->token->symbol[0] == '_') {
            /* removing the underscore */
            popchar($$->token->symbol);
        }
        $$->syntax = string2ptbsyntax($6->symbol);
        addastchild($$, newastnode(Variable, $2));
        enqueue(predicates, (void*)$$);
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
        popchar($1->symbol);
        $$ = newastnode(TypePredicate, $1);
        addastchildren($$, $6);
        $$->syntax = string2ptbsyntax($3->symbol);
        struct queue *siq = q_searchqueue(silist, $$, __javatype_simatcher);
        if (siq->count == 0) {
            syntax_error("SI for type predicate(%s) is not found.", $1->symbol);
        } 
        struct si *si = (struct si *)gqueue(siq, 0);
        enqueue(_datarefs, (void *)newtmpdataref($$, si->synthesised_datatype));                       
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
        $$->token->symbol[strlen($$->token->symbol) - 1] = '\0';
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
    }
    | terms {
        print_debug("argument: terms");
        $$ = $1;
    }
    ;

event_term
    : '(' EVENT '(' IDENTIFIER ')' EQUAL IDENTIFIER ')' {
        print_debug("event_term: '(' EVENT '(' IDENTIFIER ')' EQUAL IDENTIFIER ')'"); 
        // struct astnode *subtree_root = newastnode(GrammarNotation, $2);
        $$ = newastnode(GrammarNotation, $2);
        struct astnode *eventnode = newastnode(EventVariable, $4);
        struct astnode *entitynode = newastnode(EventEntity, $7);
        addastchild($$, eventnode);
        addastchild($$, entitynode);
        enqueue(_events, (void *)newtmpevent($$, eventnode, entitynode));
    }
    ;

quantified_term
    : KEYWORD_QUANTIFIER IDENTIFIER '.' '(' terms ')' {
        print_debug("quantify_expr: KEYWORD_QUANTIFIER IDENTIFIER");
        #if PARDEBUG
        printf("quantified variable: %s\n", $2->symbol);
        #endif
        $$ = newastnode(Quantifier, $2);
        if (strcmp($1->symbol, "exists") == 0) {
            $$->qtype = Quantifier_Exists;
            $$->quantified_ranges = NULL;
        } else {
            $$->qtype = Quantifier_ForAll;
            $$->quantified_ranges = initqueue();
        }
        struct queue *in_scope_symbol_nodes = search_symbols($5, $2->symbol);
        if (in_scope_symbol_nodes->count == 0) {
            printf("Precaution: there are no entity(%s) references in the quantified scope.\n", $2->symbol);
        }
        addastchild($$, $5);  
        $$->cstptr = newcstsymbol($2->symbol);

        $$->cstptr->ref_count += in_scope_symbol_nodes->count;
        /* finding all the variable nodes that have the same symbol as the quantified variable, and find if there is a node has TYPE resolved */
        for (int i = 0; i < in_scope_symbol_nodes->count; ++i) {
            struct astnode *_node = (struct astnode *)gqueue(in_scope_symbol_nodes, i);
            if (_node->parent->type == TypePredicate) {
                struct _dataref *ref = NULL;
                for (int j = 0; j < _datarefs->count; ++j) {
                    ref = (struct _dataref *)gqueue(_datarefs, j);
                    if (ref->node == _node->parent) {
                        break;
                    }
                }
                /* TO BE DONE: needed refinement. incoming type can be a primitive type, and the stored type is a reference type */
                if (has_datatype($$->cstptr)) {
                    /* 
                        Try to merge datatype 
                        two datatypes can be merged if * in a type and another is not
                        for instance, datatype X has primitive type as * and Y has primitive type as 0,
                        however, if both their primitive types have valid types, we consider that the entity may have two or more datatypes
                            being specified, providing combinatorial results are considered as future work.
                    */
                    if (($$->cstptr->datatype->p >= 0 && ref->datatype->p >= 0) || ($$->cstptr->datatype->r == ref->datatype->r)) {                        
                        semantic_error("The entity(%s) has two or more datatypes found. Please solve this conflict.", $2->symbol);
                    } else if (($$->cstptr->datatype->r == String || ref->datatype->r == String) && $$->cstptr->datatype->r != ref->datatype->r) {
                        /* we can only use whatever we want when the parsed datatype is an array or a list */
                        /* if the parsed datatype is an array, then the incoming datatype can only be a primitive type */
                        /* if the parsed datatype is a list, then the incoming datatype can be any type */
                        /* TODO: custom types are not supported currently */
                        $$->cstptr->datatype->element_datatype = (struct datatype *)malloc(sizeof(struct datatype));
                        $$->cstptr->datatype->element_datatype->types = initqueue();
                        $$->cstptr->datatype->element_datatype->r = String;
                        $$->cstptr->datatype->element_datatype->p = AnyPrimitiveType;     
                        if ($$->cstptr->datatype->r == String) {
                            $$->cstptr->datatype->p = ref->datatype->p;
                            $$->cstptr->datatype->r = ref->datatype->r;
                        }
                    } else {
                        if (ref->datatype->p >= 0) $$->cstptr->datatype->p = ref->datatype->p;
                        if (ref->datatype->r >= 0) $$->cstptr->datatype->r = ref->datatype->r;
                        if (ref->datatype->r == 2) {
                            while(!isempty(ref->datatype->types)) enqueue($$->cstptr->datatype->types, (void *)dequeue(ref->datatype->types));
                        }
                    }
                } else {
                    $$->cstptr->datatype = ref->datatype;
                }
                /* the variable is being removed. deduct the reference count */
                $$->cstptr->ref_count--;
                $$->cstptr->type_assigned = TRUE;
                deleteastchild(ref->node->parent, ref->node);
            } else {
                _node->cstptr = $$->cstptr;
            }
        }
        deallocatequeue(in_scope_symbol_nodes, NULL);
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

extern
void yyerror(const char *s) {
    extern char* input_line;
    fprintf(stderr,"Parser: parse error!\n  Message: %s in line %d, column %d\n", s, lineNum, colNum);
    fprintf(stderr,"%s\n", input_line);
    free(input_line);
    for(int i = 0; i < colNum - 1; i++)
        fprintf(stderr,"_");
    fprintf(stderr,"^\n"); 
    exit(-1); 
}
