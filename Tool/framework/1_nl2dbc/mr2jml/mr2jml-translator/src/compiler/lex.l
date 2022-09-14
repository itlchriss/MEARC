%{
    int bracket = 0, cbracket = 0, role_rel_flag = -1;
    #include "core.h"
    #include "tok.h"    
    extern YYSTYPE yylval; /* for passing value to parser */
    extern int lineNum, colNum, *error_lines, error_count; /* line number from cal.tab.c */ 
    // #define __LOG_LEVEL__ 1
    // #define DEBUG
    void print_debug(char *);
    void print_error();

    #define YY_INPUT( BUF, RESULT, MAX_SIZE ) \
    (RESULT) = lexer_get_input( (BUF), (MAX_SIZE) )

    static size_t column;     // current 0-based column
    char  *input_line;
    size_t input_line_len;

    static size_t lexer_get_input( char *buf, size_t buf_size ) {
    size_t bytes_read = 0;

    for ( ; bytes_read < buf_size; ++bytes_read ) {
        int const c = getc( yyin );
        if ( c == EOF ) {
        if ( ferror( yyin ) )
            /* complain and exit */;
        break;
        }
        buf[ bytes_read ] = (char)c;
        if ( c == '\n' )
        break;
    } // for

    if ( column == 0 && bytes_read < buf_size ) {
        static size_t input_line_capacity;
        if ( input_line_capacity < bytes_read + 1/*null*/ ) {
        input_line_capacity = bytes_read + 1/*null*/;
        input_line = (char*)realloc( input_line, input_line_capacity );
        }
        strncpy( input_line, buf, bytes_read );
        input_line_len = bytes_read;
        input_line[ input_line_len ] = '\0';
    }

    return bytes_read;
    }

    void handlecolnum (char *token) {
        colNum += strlen(token);
        // printf("Colnum: %d\n", colNum);
    }
    struct token * handletoken(char *text) {
        #if DEBUG
        printf("Token: %s\n", text);
        #endif  
        struct token *new = newtoken(text, lineNum, colNum);        
        handlecolnum(text);
        return new;
    }
%}

%option nounput noinput yylineno

LBRAC          "("
RBRAC          ")"
CURLY_LBRAC          "{"
CURLY_RBRAC          "}"
DOT             "."
COMMA           ","

/* Operators */
MINUS           "-"
ADD             "+"
MUL             "*"
DIV             "/"        
IMPLY           "->"
EQUIV           "<->"
EQ              "="
AND             "&"
OR              "|"

KEYWORD_NN          "NN"
KEYWORD_NNS         "NNS"
KEYWORD_NNP         "NNP"
KEYWORD_NNPS        "NNPS"
KEYWORD_IN          "IN"
KEYWORD_JJ          "JJ"
KEYWORD_JJR         "JJR"
KEYWORD_JJS         "JJS"
KEYWORD_VB          "VB"
KEYWORD_VBZ         "VBZ"
KEYWORD_VBN         "VBN"
KEYWORD_VBP         "VBP"
KEYWORD_DT          "DT"
KEYWORD_CC          "CC"
KEYWORD_CD          "CD"
KEYWORD_PRP         "PRP"

KEYWORD_EXISTS      "exists"
KEYWORD_FORALL      "all"
KEYWORD_NOT_EXISTS  "-exists"
KEYWORD_NOT_FORALL  "-all"

STRING          [a-z]+
ANNOT_PREDICATE      [-]?(_([a-zA-Z0-9\-\.*]+))+@\{([a-z]+),([a-z]+)\-([a-z]+)\}
PREDICATE       (_([a-zA-Z0-9\-\.*']+))+
VARIABLE               (x([0-9]+))|(e([0-9]+))
GRAM_KEYWORDS        ("Subj"|"Acc"|"AccI"|"Dat"|"Gen"|"Abl"|"Rel"|"Voc"|"AccE")

%s QSCOPE QVAR_SCOPE ARGUMENT PSCOPE COND COMPLEX_FORMULA FORMULA FORMULA_BODY WORD_CAT RSCOPE RVAR_SCOPE
%%
<INITIAL>{LBRAC} {
    BEGIN(FORMULA);
    handlecolnum(yytext);
    ++bracket;
    return LBRAC;
}
<INITIAL>{KEYWORD_EXISTS} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); return KEYWORD_EXISTS; }
<INITIAL>{KEYWORD_FORALL} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); return KEYWORD_FORALL; }
<INITIAL>{KEYWORD_NOT_EXISTS} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); return KEYWORD_NOT_EXISTS; }
<INITIAL>{KEYWORD_NOT_FORALL} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); return KEYWORD_NOT_FORALL; }
<FORMULA>{KEYWORD_EXISTS} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); return KEYWORD_EXISTS; }
<FORMULA>{KEYWORD_FORALL} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); return KEYWORD_FORALL; }
<FORMULA>{KEYWORD_NOT_EXISTS} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); return KEYWORD_NOT_EXISTS; }
<FORMULA>{KEYWORD_NOT_FORALL} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); return KEYWORD_NOT_FORALL; }
<QSCOPE>{VARIABLE} { 
    BEGIN(QVAR_SCOPE);
    yylval.t = handletoken(yytext);
    return VARIABLE;
    }
<QVAR_SCOPE>{DOT}{LBRAC}  { 
    ++bracket; 
    BEGIN(FORMULA_BODY); 
    handlecolnum(yytext);
    return LBRAC;
    }
<FORMULA_BODY>{PREDICATE} {
    yylval.t = handletoken(yytext);
    BEGIN(PSCOPE);
    return PREDICATE;
}
<PSCOPE>{CURLY_LBRAC} {
    ++cbracket;    
    handlecolnum(yytext);
    BEGIN(WORD_CAT);    
}
<WORD_CAT>{KEYWORD_NN} { yylval.t = handletoken(yytext); return KEYWORD_NN; }
<WORD_CAT>{KEYWORD_NNS} { yylval.t = handletoken(yytext); return KEYWORD_NNS; }
<WORD_CAT>{KEYWORD_NNP} { yylval.t = handletoken(yytext); return KEYWORD_NNP; }
<WORD_CAT>{KEYWORD_NNPS} { yylval.t = handletoken(yytext); return KEYWORD_NNPS; }
<WORD_CAT>{KEYWORD_IN} { yylval.t = handletoken(yytext); return KEYWORD_IN; }
<WORD_CAT>{KEYWORD_JJ} { yylval.t = handletoken(yytext); return KEYWORD_JJ; }
<WORD_CAT>{KEYWORD_JJR} { yylval.t = handletoken(yytext); return KEYWORD_JJR; }
<WORD_CAT>{KEYWORD_JJS} { yylval.t = handletoken(yytext); return KEYWORD_JJS; }
<WORD_CAT>{KEYWORD_VB} { yylval.t = handletoken(yytext); return KEYWORD_VB; }
<WORD_CAT>{KEYWORD_VBZ} { yylval.t = handletoken(yytext); return KEYWORD_VBZ; }
<WORD_CAT>{KEYWORD_VBN} { yylval.t = handletoken(yytext); return KEYWORD_VBN; }
<WORD_CAT>{KEYWORD_VBP} { yylval.t = handletoken(yytext); return KEYWORD_VBP; }
<WORD_CAT>{KEYWORD_DT} { yylval.t = handletoken(yytext); return KEYWORD_DT; }
<WORD_CAT>{KEYWORD_CC} { yylval.t = handletoken(yytext); return KEYWORD_CC; }
<WORD_CAT>{KEYWORD_CD} { yylval.t = handletoken(yytext); return KEYWORD_CD; }
<WORD_CAT>{KEYWORD_PRP} { yylval.t = handletoken(yytext); return KEYWORD_PRP; }

<WORD_CAT>{CURLY_RBRAC} {
    if (cbracket <= 0) {
        print_error();
    }
    --cbracket;
    handlecolnum(yytext);
    BEGIN(PSCOPE);
}
<PSCOPE>{LBRAC} {
    ++bracket;
    BEGIN(ARGUMENT);
    handlecolnum(yytext);
    return LBRAC;
}
<ARGUMENT>{VARIABLE}    { 
    yylval.t = handletoken(yytext);
    return VARIABLE;
    }
<ARGUMENT>{COMMA} {
    handlecolnum(yytext);
    return COMMA;
}
<ARGUMENT>{RBRAC} {
    --bracket;
    BEGIN(FORMULA_BODY);
    return RBRAC;
}
<FORMULA_BODY>{AND} {
    yylval.t = handletoken(yytext);
    return AND;
}
<FORMULA_BODY>{KEYWORD_EXISTS} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); return KEYWORD_EXISTS; }
<FORMULA_BODY>{KEYWORD_FORALL} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); return KEYWORD_FORALL; }
<FORMULA_BODY>{KEYWORD_NOT_EXISTS} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); return KEYWORD_NOT_EXISTS; }
<FORMULA_BODY>{KEYWORD_NOT_FORALL} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); return KEYWORD_NOT_FORALL; }
<FORMULA_BODY>{LBRAC} {
    ++bracket;
    handlecolnum(yytext);
    return LBRAC;
}
<FORMULA_BODY>{GRAM_KEYWORDS} {
    yylval.t = handletoken(yytext);
    BEGIN(RSCOPE);
    return GCASE;
}
<RSCOPE>{LBRAC} {
    ++bracket;
    handlecolnum(yytext);
    BEGIN(RVAR_SCOPE);
    return LBRAC;
}
<RVAR_SCOPE>{VARIABLE} {
    yylval.t = handletoken(yytext);    
    return VARIABLE;
}
<RSCOPE>{VARIABLE} {
    yylval.t = handletoken(yytext);    
    return VARIABLE;
}
<RVAR_SCOPE>{RBRAC} {
    --bracket;    
    handlecolnum(yytext);
    BEGIN(RSCOPE);
    return RBRAC;
}
<RSCOPE>{EQ} {
    handlecolnum(yytext);
    return EQUAL;
}
<RSCOPE>{COMMA} {
    handlecolnum(yytext);
    return COMMA;
}
<RSCOPE>{KEYWORD_EXISTS} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); role_rel_flag = 1; return KEYWORD_EXISTS; }
<RSCOPE>{KEYWORD_FORALL} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); role_rel_flag = 1; return KEYWORD_FORALL; }
<RSCOPE>{KEYWORD_NOT_EXISTS} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); role_rel_flag = 1; return KEYWORD_NOT_EXISTS; }
<RSCOPE>{KEYWORD_NOT_FORALL} { BEGIN(QSCOPE); yylval.t = handletoken(yytext); role_rel_flag = 1; return KEYWORD_NOT_FORALL; }
<RSCOPE>{RBRAC} {
    --bracket;
    handlecolnum(yytext);
    BEGIN(FORMULA_BODY);
    return RBRAC;
}
<FORMULA_BODY>{RBRAC} {
    --bracket;
    handlecolnum(yytext);
    if (bracket == 0) {
        BEGIN(INITIAL);
    } else if (role_rel_flag == 1) {
        BEGIN(RSCOPE);
        role_rel_flag = -1;
    }
    return RBRAC;
}
[ \t]+ { 
    handlecolnum(yytext);
    }
\n  { lineNum++; colNum = 1;  }
\r  { colNum = 1; lineNum++; }
\n\r { lineNum++; colNum = 1; }
. { 
    // printf("bad input character '%s' at line %d and column %d\n", yytext, lineNum, colNum); 
    // fprintf(stderr,"%s\n", input_line);
    // free(input_line);
    // for(int i = 0; i < colNum - 1; i++)
    //     fprintf(stderr,"_");
    // fprintf(stderr,"^\n"); 
    // exit(-1);
    print_error();
    }

%%
void print_error() {
    printf("Lexer: bad input character '%s' at line %d and column %d\n", yytext, lineNum, colNum); 
    /* fprintf(stderr,"%s\n", input_line); */
    /* error_lines[error_count] = lineNum; */
    /* free(input_line); */
    /* for(int i = 0; i < colNum - 1; i++) */
        /* fprintf(stderr,"_"); */
    /* fprintf(stderr,"^\n");  */
    /* exit(-1); */
}

int yywrap() {  /* need this to avoid link problem */ 
 return 1; 

}

