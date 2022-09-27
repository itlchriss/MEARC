%{
    int bracket = 0, cbracket = 0;
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
        #if LEXDEBUG
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
KEYWORD_VBG         "VBG"
KEYWORD_DT          "DT"
KEYWORD_CC          "CC"
KEYWORD_CD          "CD"
KEYWORD_PRP         "PRP"
KEYWORD_MD          "MD"
KEYWORD_RB          "RB"

KEYWORD_EXISTS      "exists"
KEYWORD_FORALL      "all"

KEYWORD_PROG        "Prog"

KEYWORD_TRUEP      "TrueP"
PREDICATE           (_([a-zA-Z0-9\-\.*'\^]+))+
VARIABLE               ([a-z]([0-9]+)?)
GRAM_KEYWORDS        ("Subj"|"Acc"|"AccI"|"Dat"|"Gen"|"Abl"|"Rel"|"Voc"|"AccE")
%%
{LBRAC} {
    yylval.t = handletoken(yytext);
    ++bracket;
    return LBRAC;
}
{RBRAC} {
    yylval.t = handletoken(yytext);
    --bracket;
    return RBRAC;
}
{MINUS} { yylval.t = handletoken(yytext); return MINUS; }
{KEYWORD_EXISTS} { yylval.t = handletoken(yytext); return KEYWORD_EXISTS; }
{KEYWORD_FORALL} { yylval.t = handletoken(yytext); return KEYWORD_FORALL; }

{VARIABLE} { yylval.t = handletoken(yytext); return IDENTIFIER; }
{PREDICATE} { yylval.t = handletoken(yytext); return PREDICATE; }
{DOT} { handlecolnum(yytext); return DOT; }
{KEYWORD_TRUEP} { yylval.t = handletoken(yytext); return KEYWORD_TRUEP; }
{CURLY_LBRAC} {
    ++cbracket;    
    yylval.t = handletoken(yytext);
    return CURLY_LBRAC;
}
{KEYWORD_PROG} { yylval.t = handletoken(yytext); return KEYWORD_PROG; }
{KEYWORD_NN} { yylval.t = handletoken(yytext); return KEYWORD_NN; }
{KEYWORD_NNS} { yylval.t = handletoken(yytext); return KEYWORD_NNS; }
{KEYWORD_NNP} { yylval.t = handletoken(yytext); return KEYWORD_NNP; }
{KEYWORD_NNPS} { yylval.t = handletoken(yytext); return KEYWORD_NNPS; }
{KEYWORD_IN} { yylval.t = handletoken(yytext); return KEYWORD_IN; }
{KEYWORD_JJ} { yylval.t = handletoken(yytext); return KEYWORD_JJ; }
{KEYWORD_JJR} { yylval.t = handletoken(yytext); return KEYWORD_JJR; }
{KEYWORD_JJS} { yylval.t = handletoken(yytext); return KEYWORD_JJS; }
{KEYWORD_VB} { yylval.t = handletoken(yytext); return KEYWORD_VB; }
{KEYWORD_VBZ} { yylval.t = handletoken(yytext); return KEYWORD_VBZ; }
{KEYWORD_VBN} { yylval.t = handletoken(yytext); return KEYWORD_VBN; }
{KEYWORD_VBP} { yylval.t = handletoken(yytext); return KEYWORD_VBP; }
{KEYWORD_DT} { yylval.t = handletoken(yytext); return KEYWORD_DT; }
{KEYWORD_CC} { yylval.t = handletoken(yytext); return KEYWORD_CC; }
{KEYWORD_CD} { yylval.t = handletoken(yytext); return KEYWORD_CD; }
{KEYWORD_PRP} { yylval.t = handletoken(yytext); return KEYWORD_PRP; }
{KEYWORD_MD} { yylval.t = handletoken(yytext); return KEYWORD_MD; }
{KEYWORD_VBG} { yylval.t = handletoken(yytext); return KEYWORD_VBG; }
{KEYWORD_RB}    {yylval.t = handletoken(yytext); return KEYWORD_RB; }

{CURLY_RBRAC} {
    if (cbracket <= 0) {
        print_error();
    }
    --cbracket;
    yylval.t = handletoken(yytext);
    return CURLY_RBRAC;
}
{COMMA} {
    yylval.t = handletoken(yytext);
    return COMMA;
}
{AND} {
    yylval.t = handletoken(yytext);
    return AND;
}
{IMPLY} {
    yylval.t = handletoken(yytext);
    return IMPLY;
}
{GRAM_KEYWORDS} {
    yylval.t = handletoken(yytext);
    return GCASE;
}
{EQ} {
    yylval.t = handletoken(yytext);
    return EQUAL;
}

{OR} {
    yylval.t = handletoken(yytext);
    return OR;
}
[ \t]+ { 
    handlecolnum(yytext);
    }
\n  { lineNum++; colNum = 1;  }
\r  { colNum = 1; lineNum++; }
\n\r { lineNum++; colNum = 1; }
. { 
    print_error();
    }

%%
void print_error() {
    printf("Lexer: bad input character '%s' at line %d and column %d\n", yytext, lineNum, colNum); 
    fprintf(stderr,"%s\n", input_line);
    error_lines[error_count++] = lineNum;
    free(input_line);
    for(int i = 0; i < colNum - 1; i++)
        fprintf(stderr,"_");
    fprintf(stderr,"^\n"); 
    exit(-1);
}

int yywrap() {  /* need this to avoid link problem */ 
 return 1; 

}

