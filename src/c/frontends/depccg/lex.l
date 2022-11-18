%{
    int bracket = 0, cbracket = 0;
    #if DEBUG
    int lineNum, colNum, *error_lines, error_count;
    #else
    #include "core.h"
    #include "tok.h"    
    extern YYSTYPE yylval; /* for passing value to parser */
    extern int lineNum, colNum, *error_lines, error_count; /* line number from cal.tab.c */     
    #endif
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
        // #if LEXDEBUG
        // printf("Token: %s\n", text);
        // #endif  
        #if DEBUG
        handlecolnum(text);
        return NULL;
        #else
        struct token *new = newtoken(text, lineNum, colNum);        
        return new;
        #endif                
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
KEYWORD_VBD         "VBD"
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
    ++bracket;
    #if DEBUG
    printf("LBRAC\n");
    #else
    yylval.t = handletoken(yytext);
    return LBRAC;    
    #endif
}
{RBRAC} {    
    --bracket;    
    #if DEBUG
    printf("RBRAC\n");
    #else
    yylval.t = handletoken(yytext);
    return RBRAC;
    #endif
}
{MINUS} {     
    #if DEBUG
    printf("MINUS\n");
    #else
    yylval.t = handletoken(yytext); 
    return MINUS; 
    #endif
    }
{KEYWORD_EXISTS} { 
    #if DEBUG
    printf("KEYWORD_EXISTS\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_EXISTS;     
    #endif
    }
{KEYWORD_FORALL} { 
    #if DEBUG
    printf("KEYWORD_FORALL\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_FORALL;     
    #endif
    }

{VARIABLE} { 
    #if DEBUG
    printf("IDENTIFIER\n");
    #else
    yylval.t = handletoken(yytext); 
    return IDENTIFIER; 
    #endif    
    }
{PREDICATE} { 
    #if DEBUG
    printf("PREDICATE\n");
    #else
    yylval.t = handletoken(yytext); 
    return PREDICATE;     
    #endif
    }
{DOT} { 
    #if DEBUG
    printf("DOT\n");
    #else
    handlecolnum(yytext); 
    return DOT;     
    #endif
    }
{KEYWORD_TRUEP} { 
    #if DEBUG
    printf("KEYWORD_TRUEP\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_TRUEP; 
    #endif
    }
{CURLY_LBRAC} {
    ++cbracket;    
    #if DEBUG
    printf("CURLY_LBRAC\n");
    #else
    yylval.t = handletoken(yytext);
    return CURLY_LBRAC;    
    #endif
}
{KEYWORD_PROG} { 
    #if DEBUG
    printf("KEYWORD_PROG\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_PROG;     
    #endif
    }
{KEYWORD_NN} { 
    #if DEBUG
    printf("KEYWORD_NN\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_NN; 
    #endif    
    }
{KEYWORD_NNS} { 
    #if DEBUG
    printf("KEYWORD_NNS\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_NNS; 
    #endif    
    }
{KEYWORD_NNP} { 
    #if DEBUG
    printf("KEYWORD_NNP\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_NNP; 
    #endif    
    }
{KEYWORD_NNPS} { 
    #if DEBUG
    printf("KEYWORD_NNPS\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_NNPS; 
    #endif
    }
{KEYWORD_IN} {
    #if DEBUG
    printf("KEYWORD_IN\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_IN; 
    #endif
    }
{KEYWORD_JJ} { 
    #if DEBUG
    printf("KEYWORD_JJ\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_JJ; 
    #endif
    }
{KEYWORD_JJR} { 
    #if DEBUG
    printf("KEYWORD_JJR\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_JJR;
    #endif
    }
{KEYWORD_JJS} { 
    #if DEBUG
    printf("KEYWORD_JJS\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_JJS; 
    #endif
    }
{KEYWORD_VB} { 
    #if DEBUG
    printf("KEYWORD_VB\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_VB; 
    #endif
    }
{KEYWORD_VBZ} { 
    #if DEBUG
    printf("KEYWORD_VBZ\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_VBZ; 
    #endif
    }
{KEYWORD_VBN} { 
    #if DEBUG
    printf("KEYWORD_VBN\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_VBN; 
    #endif
    }
{KEYWORD_VBP} { 
    #if DEBUG
    printf("KEYWORD_VBP\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_VBP; 
    #endif
    }
{KEYWORD_DT} { 
    #if DEBUG
    printf("KEYWORD_DT\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_DT; 
    #endif
    }
{KEYWORD_CC} { 
    #if DEBUG
    printf("KEYWORD_CC\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_CC; 
    #endif
    }
{KEYWORD_CD} { 
    #if DEBUG
    printf("KEYWORD_CD\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_CD; 
    #endif
    }
{KEYWORD_PRP} { 
    #if DEBUG
    printf("KEYWORD_PRP\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_PRP; 
    #endif
    }
{KEYWORD_MD} { 
    #if DEBUG
    printf("KEYWORD_MD\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_MD; 
    #endif
    }
{KEYWORD_VBG} { 
    #if DEBUG
    printf("KEYWORD_VBG\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_VBG; 
    #endif
    }
{KEYWORD_RB}    {
    #if DEBUG
    printf("KEYWORD_RB\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_RB; 
    #endif
    }
{KEYWORD_VBD}    {
    #if DEBUG
    printf("KEYWORD_VBD\n");
    #else
    yylval.t = handletoken(yytext); 
    return KEYWORD_VBD; 
    #endif    
    }

{CURLY_RBRAC} {
    if (cbracket <= 0) {
        print_error();
    }
    --cbracket;
    #if DEBUG
    printf("CURLY_RBRAC\n");
    #else
    yylval.t = handletoken(yytext);
    return CURLY_RBRAC;
    #endif    
}
{COMMA} {
    #if DEBUG
    printf("COMMA\n");
    #else
    yylval.t = handletoken(yytext);
    return COMMA;
    #endif
}
{AND} {
    #if DEBUG
    printf("AND\n");
    #else
    yylval.t = handletoken(yytext);
    return AND;
    #endif
}
{IMPLY} {
    #if DEBUG
    printf("IMPLY\n");
    #else
    yylval.t = handletoken(yytext);
    return IMPLY;    
    #endif
}
{GRAM_KEYWORDS} {
    #if DEBUG
    printf("GCASE\n");
    #else
    yylval.t = handletoken(yytext);
    return GCASE;    
    #endif
}
{EQ} {
    #if DEBUG
    printf("EQUAL\n");
    #else
    yylval.t = handletoken(yytext);
    return EQUAL;
    #endif
}

{OR} {
    #if DEBUG
    printf("OR\n");
    #else
    yylval.t = handletoken(yytext);
    return OR;    
    #endif
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

#if DEBUG
int main(int argc, char **argv) {
    yyin = fopen(argv[1], "r");
    yylex();
    fclose(yyin);
}
#endif