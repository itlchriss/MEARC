%{
    int bracket = 0, cbracket = 0;
    #include "core.h"
    #include "tok.h"    
    #include "util.h"
    extern YYSTYPE yylval; /* for passing value to parser */
    extern int lineNum, colNum; /* line number from cal.tab.c */ 
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
    void *denormalise_text(char *text, char *norm, char *denorm) {
        char *tmp = strrep(text, norm, denorm);
        if (tmp != NULL) { 
            return (char *)strdup(tmp);
        } else {
            return text;
        }
    }
    struct token * handletoken(char *text) {
        #if LEXDEBUG
        printf("Token: %s\n", text);
        #endif  
        text = denormalise_text(text, "dash_", "-");
        text = denormalise_text(text, "_DOT", ".");

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
NEG           "-"
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
KEYWORD_TO          "TO"

KEYWORD_EXISTS      "exists"
KEYWORD_FORALL      "all"

KEYWORD_PROG        "Prog"
KEYWORD_REL         "Rel"

KEYWORD_TRUEP      "TrueP"
KEYWORD_TRUE       "True"

KEYWORD_SUBJ        "Subj"
KEYWORD_ACC         "Acc"
KEYWORD_ACCI         "AccI"
KEYWORD_DAT         "Dat"

PREDICATE           (_([a-zA-Z0-9\-\.*'\^\+]+))+
SPECIAL_PREDICATE   (_from_[0-9]+_[0-9]+_[a-z]+_sequence_)
FIX_SEQUENCE_PREDICATE (_fixed_integer_sequence_)
CHECK_CHAR_PREDICATE (_checking_character_sequence_)
CHECK_STRING_PREDICATE (_checking_string_sequence_)
EXPR                (_expr[0-9]+_)
KEYWORD_TYPE         _type[_a-z]+_
KEYWORD_PARAM        _param[_a-zA-Z0-9]+_
VARIABLE               ([a-z]([0-9]+)?)
%%
"(" {
    yylval.t = handletoken(yytext);
    ++bracket;
    return '(';
}
")" {
    yylval.t = handletoken(yytext);
    --bracket;
    return ')';
}
{NEG} { yylval.t = handletoken(yytext); return NEG; }
{KEYWORD_EXISTS} { yylval.t = handletoken(yytext); return KEYWORD_QUANTIFIER; }
{KEYWORD_FORALL} { yylval.t = handletoken(yytext); return KEYWORD_QUANTIFIER; }

{VARIABLE} { yylval.t = handletoken(yytext); return IDENTIFIER; }
{KEYWORD_PARAM} { yylval.t = handletoken(yytext); return KEYWORD_PARAM; }
{KEYWORD_TYPE} { yylval.t = handletoken(yytext); return KEYWORD_TYPE; }
{PREDICATE} { yylval.t = handletoken(yytext); return PREDICATE; }
{SPECIAL_PREDICATE} { yylval.t = handletoken(yytext); return PREDICATE; }
{CHECK_CHAR_PREDICATE} { yylval.t = handletoken(yytext); return PREDICATE; }
{CHECK_STRING_PREDICATE} { yylval.t = handletoken(yytext); return PREDICATE; }
{FIX_SEQUENCE_PREDICATE} { yylval.t = handletoken(yytext); return PREDICATE; }
{EXPR} { yylval.t = handletoken(yytext); return PREDICATE; }
"." { handlecolnum(yytext); return '.'; }
{KEYWORD_TRUEP} { yylval.t = handletoken(yytext); return KEYWORD_TRUEP; }
{KEYWORD_TRUE} { yylval.t = handletoken(yytext); return KEYWORD_TRUEP; }
"{" {
    ++cbracket;    
    yylval.t = handletoken(yytext);
    return '{';
}

{KEYWORD_PROG} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_REL} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_NN} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_NNS} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_NNP} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_NNPS} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_IN} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_JJ} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_JJR} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_JJS} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_VB} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_VBZ} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_VBN} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_VBP} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_DT} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_CC} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_CD} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_PRP} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_MD} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_VBG} { yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_RB}    {yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_TO}    {yylval.t = handletoken(yytext); return TAG; }
{KEYWORD_VBD}    {yylval.t = handletoken(yytext); return TAG; }

{KEYWORD_SUBJ}  {yylval.t = handletoken(yytext); return EVENT;}
{KEYWORD_ACC}  {yylval.t = handletoken(yytext); return EVENT;}
{KEYWORD_ACCI}  {yylval.t = handletoken(yytext); return EVENT;}
{KEYWORD_DAT}   {yylval.t = handletoken(yytext); return EVENT;}

"}" {
    if (cbracket <= 0) {
        print_error();
    }
    --cbracket;
    yylval.t = handletoken(yytext);
    return '}';
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
{EQUIV} {
    yylval.t = handletoken(yytext);
    return EQUIV;
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
    free(input_line);
    for(int i = 0; i < colNum - 1; i++)
        fprintf(stderr,"_");
    fprintf(stderr,"^\n"); 
    exit(-1);
}

int yywrap() {  /* need this to avoid link problem */ 
 return 1; 

}

