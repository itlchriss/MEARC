%{
    int l = 0, r = 0, bracket = 0;
    #include "h2jc.h"
    #include "tok.h"    
    extern YYSTYPE yylval; /* for passing value to parser */
    extern int lineNum, colNum; /* line number from cal.tab.c */ 
    // #define __LOG_LEVEL__ 1
    // #define DEBUG
    void print_debug(char *);

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
        struct token *new = newtoken(text, lineNum, colNum);
        handlecolnum(text);
        return new;
    }
%}

%option nounput noinput yylineno

LBRAC          "("
RBRAC          ")"
DOT             "."
COMMA           ","
/* Quantifiers */
EXISTS          "exists"
NOTEXISTS          "-exists"
ALL             "all"
NOTALL          "-all"

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

STRING          [a-z]+
ANNOT_PREDICATE      [-]?(_([a-zA-Z0-9\-\.*]+))+@\{([a-z]+),([a-z]+)\-([a-z]+)\}
VARIABLE        ({STRING}([0-9]+)?)

%s QUANTIFIER QUANTIFIER_VAR ARGUMENT PREDICATE COND COMPLEX_FORMULA
%%
<INITIAL>{ANNOT_PREDICATE}   {
    BEGIN(COND);
    yylval.t = handletoken(yytext);
    return ANNOT_PREDICATE;
}
{NOTEXISTS}         {
    BEGIN(QUANTIFIER); 
    yylval.t = newtoken("-exists", lineNum, colNum);
    handlecolnum(yytext);
    return NOTEXISTS;
}
{EXISTS}            { 
    BEGIN(QUANTIFIER); 
    yylval.t = newtoken("exists", lineNum, colNum);
    handlecolnum(yytext);
    return EXISTS;
    }
{ALL}               { 
    BEGIN(QUANTIFIER); 
    yylval.t = newtoken("all", lineNum, colNum);
    handlecolnum(yytext);
    return ALL;
    }
<QUANTIFIER>{VARIABLE} { 
    BEGIN(QUANTIFIER_VAR);
    yylval.t = handletoken(yytext);
    return VARIABLE;
    }
<QUANTIFIER_VAR>{DOT}{LBRAC}  { 
    ++bracket; BEGIN(PREDICATE); 
    handlecolnum(yytext);
    return LBRAC;
    }

<PREDICATE>{ANNOT_PREDICATE} { 
    yylval.t = handletoken(yytext);
    return ANNOT_PREDICATE;
    }
<ARGUMENT>{VARIABLE}    { 
    char *text = (char *)strdup(yytext);
    yylval.t = newtoken(text, lineNum, colNum);
    // colNum += strlen(text);
    handlecolnum(yytext);
    return VARIABLE;
    }
<ARGUMENT>{ANNOT_PREDICATE} {
    char *text = (char *)strdup(yytext);       
    yylval.t = newtoken(text, lineNum, colNum);
    // colNum += strlen(text);
    handlecolnum(yytext);
    return ANNOT_PREDICATE;
}
<ARGUMENT>{RBRAC} { 
    --bracket; 
    if (bracket == 0) { BEGIN(INITIAL); } 
    else BEGIN(PREDICATE);
    // colNum++;
    handlecolnum(yytext);
    return RBRAC;
    }
<PREDICATE>{RBRAC} {
    --bracket;
    if (bracket == 0) { 
        BEGIN(INITIAL); 
    }
    // colNum++;
    handlecolnum(yytext);
    return RBRAC;
}
<COND>{LBRAC} |
<PREDICATE>{LBRAC} | 
<ARGUMENT>{LBRAC}  { 
    BEGIN(ARGUMENT); ++bracket; 
    // colNum++;
    handlecolnum(yytext);
    return LBRAC;
    }

<INITIAL>{LBRAC} {
    ++bracket;
    BEGIN(COMPLEX_FORMULA);
    handlecolnum(yytext);
    return LBRAC;
}
<COMPLEX_FORMULA>{ALL} {
    yylval.t = handletoken(yytext);
    return ALL;
}
<COMPLEX_FORMULA>{NOTALL} {
    yylval.t = handletoken(yytext);
    return NOTALL;
}
<COMPLEX_FORMULA>{EXISTS} {
    yylval.t = handletoken(yytext);
    return EXISTS;
}
<COMPLEX_FORMULA>{NOTEXISTS} {
    yylval.t = handletoken(yytext);
    return NOTEXISTS;
}
<COMPLEX_FORMULA>{RBRAC} {
    --bracket;
    if (bracket == 0) {
        BEGIN(INITIAL);
    }
    handlecolnum(yytext);
    return RBRAC;
}
{COMMA}             { 
    // colNum++;
    handlecolnum(yytext);
    return COMMA;
    }
{AND}               { 
    yylval.t = newtoken("&", lineNum, colNum);
    // colNum++;        
    handlecolnum(yytext);
    return AND;
    }
{OR}                { 
    yylval.t = newtoken("|", lineNum, colNum);
    // colNum++;
    handlecolnum(yytext);
    return OR;
    }
{IMPLY}             { 
    yylval.t = newtoken("->", lineNum, colNum);
    // colNum += 2;
    handlecolnum(yytext);
    return IMPLY;
    }
{EQUIV}             { 
    yylval.t = newtoken("<->", lineNum, colNum);
    // colNum += 3;
    handlecolnum(yytext);
    return EQUIV;
    }
{MINUS}             {
    yylval.t = handletoken(yytext);
    return MINUS;
}
[ \t]+ { 
    // colNum++; 
    handlecolnum(yytext);
    }
\n  { lineNum++; colNum = 1;  }
\r  { colNum = 1; lineNum++; }
\n\r { lineNum++; colNum = 1; }
. { 
    printf("bad input character '%s' at line %d and column %d\n", yytext, lineNum, colNum); 
    // ++colNum;     
    // handlecolnum(yytext);
    fprintf(stderr,"%s\n", input_line);
    free(input_line);
    for(int i = 0; i < colNum - 1; i++)
        fprintf(stderr,"_");
    fprintf(stderr,"^\n"); 
    exit(-1);
    }

%%
int yywrap() {  /* need this to avoid link problem */ 
 return 1; 

}

