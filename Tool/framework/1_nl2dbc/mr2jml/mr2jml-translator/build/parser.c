/* A Bison parser, made by GNU Bison 3.5.1.  */

/* Bison implementation for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015, 2018-2020 Free Software Foundation,
   Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Undocumented macros, especially those whose name start with YY_,
   are private implementation details.  Do not rely on them.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "3.5.1"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1




/* First part of user prologue.  */
#line 1 "./src/compiler/parser.y"

    #include "core.h"
    #include "util.h"
    #include "cst.h"
    #define YYERROR_VERBOSE 1

    void print_debug(char *);

    // From main.c
    extern int c;
    extern struct astnode **ast;  
    extern struct queue **csts, **predicates, **operators;

    // c is for the line counter of hols
    int lbracs = 0, rbracs = 0, lineNum = 1, colNum = 1, *error_lines, error_count = 0;

#line 87 "parser.tab.c"

# ifndef YY_CAST
#  ifdef __cplusplus
#   define YY_CAST(Type, Val) static_cast<Type> (Val)
#   define YY_REINTERPRET_CAST(Type, Val) reinterpret_cast<Type> (Val)
#  else
#   define YY_CAST(Type, Val) ((Type) (Val))
#   define YY_REINTERPRET_CAST(Type, Val) ((Type) (Val))
#  endif
# endif
# ifndef YY_NULLPTR
#  if defined __cplusplus
#   if 201103L <= __cplusplus
#    define YY_NULLPTR nullptr
#   else
#    define YY_NULLPTR 0
#   endif
#  else
#   define YY_NULLPTR ((void*)0)
#  endif
# endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* Use api.header.include to #include this header
   instead of duplicating it here.  */
#ifndef YY_YY_PARSER_TAB_H_INCLUDED
# define YY_YY_PARSER_TAB_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    PREDICATE = 258,
    IDENTIFIER = 259,
    KEYWORD_TRUEP = 260,
    DOT = 261,
    MINUS = 262,
    COMMA = 263,
    LBRAC = 264,
    RBRAC = 265,
    GCASE = 266,
    EQUAL = 267,
    AND = 268,
    OR = 269,
    IMPLY = 270,
    EQUIV = 271,
    CURLY_LBRAC = 272,
    CURLY_RBRAC = 273,
    KEYWORD_FORALL = 274,
    KEYWORD_EXISTS = 275,
    KEYWORD_PROG = 276,
    KEYWORD_NN = 277,
    KEYWORD_NNS = 278,
    KEYWORD_NNP = 279,
    KEYWORD_NNPS = 280,
    KEYWORD_IN = 281,
    KEYWORD_JJ = 282,
    KEYWORD_JJR = 283,
    KEYWORD_JJS = 284,
    KEYWORD_VB = 285,
    KEYWORD_VBG = 286,
    KEYWORD_VBZ = 287,
    KEYWORD_VBN = 288,
    KEYWORD_VBP = 289,
    KEYWORD_DT = 290,
    KEYWORD_CC = 291,
    KEYWORD_CD = 292,
    KEYWORD_PRP = 293,
    KEYWORD_MD = 294,
    KEYWORD_RB = 295
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
union YYSTYPE
{
#line 18 "./src/compiler/parser.y"

    struct astnode *node;
    struct astnodelist *nodelist;
    struct token *t;
    enum ptbsyntax ptb;
    enum conntype conntype;
    enum grammartype gtype;

#line 189 "parser.tab.c"

};
typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_PARSER_TAB_H_INCLUDED  */



#ifdef short
# undef short
#endif

/* On compilers that do not define __PTRDIFF_MAX__ etc., make sure
   <limits.h> and (if available) <stdint.h> are included
   so that the code can choose integer types of a good width.  */

#ifndef __PTRDIFF_MAX__
# include <limits.h> /* INFRINGES ON USER NAME SPACE */
# if defined __STDC_VERSION__ && 199901 <= __STDC_VERSION__
#  include <stdint.h> /* INFRINGES ON USER NAME SPACE */
#  define YY_STDINT_H
# endif
#endif

/* Narrow types that promote to a signed type and that can represent a
   signed or unsigned integer of at least N bits.  In tables they can
   save space and decrease cache pressure.  Promoting to a signed type
   helps avoid bugs in integer arithmetic.  */

#ifdef __INT_LEAST8_MAX__
typedef __INT_LEAST8_TYPE__ yytype_int8;
#elif defined YY_STDINT_H
typedef int_least8_t yytype_int8;
#else
typedef signed char yytype_int8;
#endif

#ifdef __INT_LEAST16_MAX__
typedef __INT_LEAST16_TYPE__ yytype_int16;
#elif defined YY_STDINT_H
typedef int_least16_t yytype_int16;
#else
typedef short yytype_int16;
#endif

#if defined __UINT_LEAST8_MAX__ && __UINT_LEAST8_MAX__ <= __INT_MAX__
typedef __UINT_LEAST8_TYPE__ yytype_uint8;
#elif (!defined __UINT_LEAST8_MAX__ && defined YY_STDINT_H \
       && UINT_LEAST8_MAX <= INT_MAX)
typedef uint_least8_t yytype_uint8;
#elif !defined __UINT_LEAST8_MAX__ && UCHAR_MAX <= INT_MAX
typedef unsigned char yytype_uint8;
#else
typedef short yytype_uint8;
#endif

#if defined __UINT_LEAST16_MAX__ && __UINT_LEAST16_MAX__ <= __INT_MAX__
typedef __UINT_LEAST16_TYPE__ yytype_uint16;
#elif (!defined __UINT_LEAST16_MAX__ && defined YY_STDINT_H \
       && UINT_LEAST16_MAX <= INT_MAX)
typedef uint_least16_t yytype_uint16;
#elif !defined __UINT_LEAST16_MAX__ && USHRT_MAX <= INT_MAX
typedef unsigned short yytype_uint16;
#else
typedef int yytype_uint16;
#endif

#ifndef YYPTRDIFF_T
# if defined __PTRDIFF_TYPE__ && defined __PTRDIFF_MAX__
#  define YYPTRDIFF_T __PTRDIFF_TYPE__
#  define YYPTRDIFF_MAXIMUM __PTRDIFF_MAX__
# elif defined PTRDIFF_MAX
#  ifndef ptrdiff_t
#   include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  endif
#  define YYPTRDIFF_T ptrdiff_t
#  define YYPTRDIFF_MAXIMUM PTRDIFF_MAX
# else
#  define YYPTRDIFF_T long
#  define YYPTRDIFF_MAXIMUM LONG_MAX
# endif
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif defined __STDC_VERSION__ && 199901 <= __STDC_VERSION__
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned
# endif
#endif

#define YYSIZE_MAXIMUM                                  \
  YY_CAST (YYPTRDIFF_T,                                 \
           (YYPTRDIFF_MAXIMUM < YY_CAST (YYSIZE_T, -1)  \
            ? YYPTRDIFF_MAXIMUM                         \
            : YY_CAST (YYSIZE_T, -1)))

#define YYSIZEOF(X) YY_CAST (YYPTRDIFF_T, sizeof (X))

/* Stored state numbers (used for stacks). */
typedef yytype_uint8 yy_state_t;

/* State numbers in computations.  */
typedef int yy_state_fast_t;

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(Msgid) dgettext ("bison-runtime", Msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(Msgid) Msgid
# endif
#endif

#ifndef YY_ATTRIBUTE_PURE
# if defined __GNUC__ && 2 < __GNUC__ + (96 <= __GNUC_MINOR__)
#  define YY_ATTRIBUTE_PURE __attribute__ ((__pure__))
# else
#  define YY_ATTRIBUTE_PURE
# endif
#endif

#ifndef YY_ATTRIBUTE_UNUSED
# if defined __GNUC__ && 2 < __GNUC__ + (7 <= __GNUC_MINOR__)
#  define YY_ATTRIBUTE_UNUSED __attribute__ ((__unused__))
# else
#  define YY_ATTRIBUTE_UNUSED
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(E) ((void) (E))
#else
# define YYUSE(E) /* empty */
#endif

#if defined __GNUC__ && ! defined __ICC && 407 <= __GNUC__ * 100 + __GNUC_MINOR__
/* Suppress an incorrect diagnostic about yylval being uninitialized.  */
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN                            \
    _Pragma ("GCC diagnostic push")                                     \
    _Pragma ("GCC diagnostic ignored \"-Wuninitialized\"")              \
    _Pragma ("GCC diagnostic ignored \"-Wmaybe-uninitialized\"")
# define YY_IGNORE_MAYBE_UNINITIALIZED_END      \
    _Pragma ("GCC diagnostic pop")
#else
# define YY_INITIAL_VALUE(Value) Value
#endif
#ifndef YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_END
#endif
#ifndef YY_INITIAL_VALUE
# define YY_INITIAL_VALUE(Value) /* Nothing. */
#endif

#if defined __cplusplus && defined __GNUC__ && ! defined __ICC && 6 <= __GNUC__
# define YY_IGNORE_USELESS_CAST_BEGIN                          \
    _Pragma ("GCC diagnostic push")                            \
    _Pragma ("GCC diagnostic ignored \"-Wuseless-cast\"")
# define YY_IGNORE_USELESS_CAST_END            \
    _Pragma ("GCC diagnostic pop")
#endif
#ifndef YY_IGNORE_USELESS_CAST_BEGIN
# define YY_IGNORE_USELESS_CAST_BEGIN
# define YY_IGNORE_USELESS_CAST_END
#endif


#define YY_ASSERT(E) ((void) (0 && (E)))

#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined EXIT_SUCCESS
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
      /* Use EXIT_SUCCESS as a witness for stdlib.h.  */
#     ifndef EXIT_SUCCESS
#      define EXIT_SUCCESS 0
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's 'empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (0)
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined EXIT_SUCCESS \
       && ! ((defined YYMALLOC || defined malloc) \
             && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef EXIT_SUCCESS
#    define EXIT_SUCCESS 0
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined EXIT_SUCCESS
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined EXIT_SUCCESS
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
         || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yy_state_t yyss_alloc;
  YYSTYPE yyvs_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (YYSIZEOF (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (YYSIZEOF (yy_state_t) + YYSIZEOF (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

# define YYCOPY_NEEDED 1

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)                           \
    do                                                                  \
      {                                                                 \
        YYPTRDIFF_T yynewbytes;                                         \
        YYCOPY (&yyptr->Stack_alloc, Stack, yysize);                    \
        Stack = &yyptr->Stack_alloc;                                    \
        yynewbytes = yystacksize * YYSIZEOF (*Stack) + YYSTACK_GAP_MAXIMUM; \
        yyptr += yynewbytes / YYSIZEOF (*yyptr);                        \
      }                                                                 \
    while (0)

#endif

#if defined YYCOPY_NEEDED && YYCOPY_NEEDED
/* Copy COUNT objects from SRC to DST.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(Dst, Src, Count) \
      __builtin_memcpy (Dst, Src, YY_CAST (YYSIZE_T, (Count)) * sizeof (*(Src)))
#  else
#   define YYCOPY(Dst, Src, Count)              \
      do                                        \
        {                                       \
          YYPTRDIFF_T yyi;                      \
          for (yyi = 0; yyi < (Count); yyi++)   \
            (Dst)[yyi] = (Src)[yyi];            \
        }                                       \
      while (0)
#  endif
# endif
#endif /* !YYCOPY_NEEDED */

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  17
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   217

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  41
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  13
/* YYNRULES -- Number of rules.  */
#define YYNRULES  60
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  131

#define YYUNDEFTOK  2
#define YYMAXUTOK   295


/* YYTRANSLATE(TOKEN-NUM) -- Symbol number corresponding to TOKEN-NUM
   as returned by yylex, with out-of-bounds checking.  */
#define YYTRANSLATE(YYX)                                                \
  (0 <= (YYX) && (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[TOKEN-NUM] -- Symbol number corresponding to TOKEN-NUM
   as returned by yylex.  */
static const yytype_int8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40
};

#if YYDEBUG
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_int16 yyrline[] =
{
       0,    46,    46,    55,    68,    72,    79,    86,    93,    96,
     104,   108,   116,   122,   131,   143,   154,   163,   172,   175,
     179,   184,   192,   221,   225,   229,   242,   246,   253,   257,
     261,   281,   286,   293,   301,   308,   317,   331,   332,   333,
     334,   335,   336,   337,   338,   339,   340,   341,   342,   343,
     344,   345,   346,   347,   348,   349,   353,   354,   355,   356,
     360
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || 0
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "PREDICATE", "IDENTIFIER",
  "KEYWORD_TRUEP", "DOT", "MINUS", "COMMA", "LBRAC", "RBRAC", "GCASE",
  "EQUAL", "AND", "OR", "IMPLY", "EQUIV", "CURLY_LBRAC", "CURLY_RBRAC",
  "KEYWORD_FORALL", "KEYWORD_EXISTS", "KEYWORD_PROG", "KEYWORD_NN",
  "KEYWORD_NNS", "KEYWORD_NNP", "KEYWORD_NNPS", "KEYWORD_IN", "KEYWORD_JJ",
  "KEYWORD_JJR", "KEYWORD_JJS", "KEYWORD_VB", "KEYWORD_VBG", "KEYWORD_VBZ",
  "KEYWORD_VBN", "KEYWORD_VBP", "KEYWORD_DT", "KEYWORD_CC", "KEYWORD_CD",
  "KEYWORD_PRP", "KEYWORD_MD", "KEYWORD_RB", "$accept", "meaning_reprs",
  "meaning_repr", "formulas", "formula", "terms", "term", "arguments",
  "argument", "quantify_expr", "pos_tag", "connective", "grammar_term", YY_NULLPTR
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[NUM] -- (External) token number corresponding to the
   (internal) symbol number NUM (which must be that of a token).  */
static const yytype_int16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295
};
# endif

#define YYPACT_NINF (-73)

#define yypact_value_is_default(Yyn) \
  ((Yyn) == YYPACT_NINF)

#define YYTABLE_NINF (-1)

#define yytable_value_is_error(Yyn) \
  0

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
static const yytype_int16 yypact[] =
{
      84,    68,    93,     6,    15,     8,   -73,   201,   -73,    16,
      75,   -73,    93,   107,    26,   -73,   -73,   -73,   -73,   -73,
     -73,   -73,   -73,   151,    31,    49,   166,   201,    51,    68,
      68,   -73,    43,    63,   201,    68,    43,   -73,    72,    82,
      80,   -73,   142,   145,    92,   -73,   -73,   176,   -73,    97,
      43,   -73,   183,   -73,   104,   105,   145,   -73,    68,   201,
     100,    17,   -73,    64,   112,   190,   -73,   -73,   -73,   -73,
     -73,   -73,   -73,   -73,   -73,   -73,   -73,   -73,   -73,   -73,
     -73,   -73,   -73,   -73,   -73,   -73,    98,   -73,   108,   201,
      68,   -73,   -73,   -73,    -1,   -73,   164,   145,   -73,   135,
     114,   -73,   146,   -73,   147,    17,   160,   145,   -73,   149,
     104,    61,   -73,   -73,    17,   167,   -73,   156,    43,    41,
     170,   -73,   178,   197,   -73,    17,   184,    66,   -73,   185,
     -73
};

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_int8 yydefact[] =
{
       0,     0,     0,     0,     0,     0,     3,     5,    10,     0,
       0,    11,     0,     0,     0,    36,    35,     1,     2,    56,
      59,    58,    57,     0,     0,     0,     0,     4,     0,     0,
       0,     7,     0,     0,     0,     0,     0,     9,     0,     0,
       0,    24,     0,     0,     0,    60,    26,     0,    19,     0,
       0,     6,     0,     8,     0,     0,     0,    20,     0,    26,
       0,     0,    12,     0,     0,     0,    12,    37,    38,    39,
      40,    41,    42,    43,    44,    45,    54,    46,    47,    48,
      49,    51,    50,    52,    53,    55,     0,    30,     0,     0,
       0,    18,    33,    34,     0,    32,     0,     0,    14,     0,
       0,    13,     0,    21,     0,     0,    29,     0,    16,     0,
       0,     0,    27,    31,     0,     0,    15,     0,     0,     0,
      28,    17,     0,     0,    22,     0,     0,     0,    23,     0,
      25
};

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
     -73,   -73,   196,     4,     0,   -32,   -18,   -72,    83,     1,
      99,     7,   -73
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int8 yydefgoto[] =
{
      -1,     5,     6,     7,    46,    47,    48,    94,    95,     9,
      86,    63,    49
};

  /* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule whose
     number is the opposite.  If YYTABLE_NINF, syntax error.  */
static const yytype_uint8 yytable[] =
{
       8,    11,     8,    14,    52,     8,    13,   105,    17,   106,
      15,    25,     8,    14,    23,     1,    26,     2,    65,    16,
      23,    92,    24,    31,    57,    60,    10,     3,     4,    37,
      38,    14,    28,    23,    35,    51,     3,     4,    88,   119,
      32,    35,   120,    59,    14,    98,    39,    40,    41,   105,
      42,   124,    43,   127,    44,    33,    59,    14,    89,    14,
      36,    93,     3,     4,    45,    92,    90,    39,    40,    41,
     118,    96,    50,    97,   105,    44,   129,    10,   108,   109,
       3,     4,    53,     3,     4,    45,   123,     3,     4,   115,
     104,     1,    55,     2,     3,     4,    90,    59,    14,    54,
       1,    61,    12,     3,     4,    93,    64,    59,    14,    87,
      91,    93,     3,     4,    93,    99,   102,    27,   103,    14,
      19,    20,    21,    22,   101,    93,    67,    68,    69,    70,
      71,    72,    73,    74,    75,    76,    77,    78,    79,    80,
      81,    82,    83,    84,    85,    39,    40,    41,    39,    40,
      41,    56,   110,    44,    58,   111,    44,   112,    29,   116,
      30,     3,     4,    45,     3,     4,    45,    39,    40,    41,
       3,     4,   114,   107,   122,    44,    34,   121,   105,    19,
      20,    21,    22,     3,     4,    45,    62,   125,   113,    19,
      20,    21,    22,    66,   128,   130,    19,    20,    21,    22,
     100,    18,     0,    19,    20,    21,    22,   126,     0,   117,
      19,    20,    21,    22,    19,    20,    21,    22
};

static const yytype_int8 yycheck[] =
{
       0,     1,     2,     2,    36,     5,     2,     8,     0,    10,
       4,    10,    12,    12,     7,     7,    12,     9,    50,     4,
      13,     4,     6,    23,    42,    43,     9,    19,    20,    29,
      30,    30,     6,    26,    27,    35,    19,    20,    56,   111,
       9,    34,   114,    43,    43,    63,     3,     4,     5,     8,
       7,    10,     9,   125,    11,     6,    56,    56,    58,    58,
       9,    61,    19,    20,    21,     4,    59,     3,     4,     5,
       9,     7,     9,     9,     8,    11,    10,     9,    96,    97,
      19,    20,    10,    19,    20,    21,   118,    19,    20,   107,
      90,     7,    12,     9,    19,    20,    89,    97,    97,    17,
       7,     9,     9,    19,    20,   105,     9,   107,   107,     4,
      10,   111,    19,    20,   114,     3,    18,    10,    10,   118,
      13,    14,    15,    16,    10,   125,    22,    23,    24,    25,
      26,    27,    28,    29,    30,    31,    32,    33,    34,    35,
      36,    37,    38,    39,    40,     3,     4,     5,     3,     4,
       5,     9,    17,    11,     9,     9,    11,    10,     7,    10,
       9,    19,    20,    21,    19,    20,    21,     3,     4,     5,
      19,    20,    12,     9,    18,    11,    10,    10,     8,    13,
      14,    15,    16,    19,    20,    21,    10,     9,   105,    13,
      14,    15,    16,    10,    10,    10,    13,    14,    15,    16,
      10,     5,    -1,    13,    14,    15,    16,    10,    -1,   110,
      13,    14,    15,    16,    13,    14,    15,    16
};

  /* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
static const yytype_int8 yystos[] =
{
       0,     7,     9,    19,    20,    42,    43,    44,    45,    50,
       9,    45,     9,    44,    50,     4,     4,     0,    43,    13,
      14,    15,    16,    52,     6,    50,    44,    10,     6,     7,
       9,    45,     9,     6,    10,    52,     9,    45,    45,     3,
       4,     5,     7,     9,    11,    21,    45,    46,    47,    53,
       9,    45,    46,    10,    17,    12,     9,    47,     9,    45,
      47,     9,    10,    52,     9,    46,    10,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    51,     4,    47,    45,
      52,    10,     4,    45,    48,    49,     7,     9,    47,     3,
      10,    10,    18,    10,    45,     8,    10,     9,    47,    47,
      17,     9,    10,    49,    12,    47,    10,    51,     9,    48,
      48,    10,    18,    46,    10,     9,    10,    48,    10,    10,
      10
};

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_int8 yyr1[] =
{
       0,    41,    42,    42,    43,    43,    44,    44,    44,    44,
      44,    44,    45,    45,    46,    46,    46,    46,    46,    46,
      46,    46,    47,    47,    47,    47,    47,    47,    47,    47,
      47,    48,    48,    49,    49,    50,    50,    51,    51,    51,
      51,    51,    51,    51,    51,    51,    51,    51,    51,    51,
      51,    51,    51,    51,    51,    51,    52,    52,    52,    52,
      53
};

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_int8 yyr2[] =
{
       0,     2,     2,     1,     3,     1,     5,     3,     5,     4,
       1,     2,     5,     7,     3,     5,     4,     6,     3,     1,
       2,     4,     7,     9,     1,    10,     1,     5,     6,     4,
       3,     3,     1,     1,     1,     2,     2,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1
};


#define yyerrok         (yyerrstatus = 0)
#define yyclearin       (yychar = YYEMPTY)
#define YYEMPTY         (-2)
#define YYEOF           0

#define YYACCEPT        goto yyacceptlab
#define YYABORT         goto yyabortlab
#define YYERROR         goto yyerrorlab


#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)                                    \
  do                                                              \
    if (yychar == YYEMPTY)                                        \
      {                                                           \
        yychar = (Token);                                         \
        yylval = (Value);                                         \
        YYPOPSTACK (yylen);                                       \
        yystate = *yyssp;                                         \
        goto yybackup;                                            \
      }                                                           \
    else                                                          \
      {                                                           \
        yyerror (YY_("syntax error: cannot back up")); \
        YYERROR;                                                  \
      }                                                           \
  while (0)

/* Error token number */
#define YYTERROR        1
#define YYERRCODE       256



/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)                        \
do {                                            \
  if (yydebug)                                  \
    YYFPRINTF Args;                             \
} while (0)

/* This macro is provided for backward compatibility. */
#ifndef YY_LOCATION_PRINT
# define YY_LOCATION_PRINT(File, Loc) ((void) 0)
#endif


# define YY_SYMBOL_PRINT(Title, Type, Value, Location)                    \
do {                                                                      \
  if (yydebug)                                                            \
    {                                                                     \
      YYFPRINTF (stderr, "%s ", Title);                                   \
      yy_symbol_print (stderr,                                            \
                  Type, Value); \
      YYFPRINTF (stderr, "\n");                                           \
    }                                                                     \
} while (0)


/*-----------------------------------.
| Print this symbol's value on YYO.  |
`-----------------------------------*/

static void
yy_symbol_value_print (FILE *yyo, int yytype, YYSTYPE const * const yyvaluep)
{
  FILE *yyoutput = yyo;
  YYUSE (yyoutput);
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyo, yytoknum[yytype], *yyvaluep);
# endif
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  YYUSE (yytype);
  YY_IGNORE_MAYBE_UNINITIALIZED_END
}


/*---------------------------.
| Print this symbol on YYO.  |
`---------------------------*/

static void
yy_symbol_print (FILE *yyo, int yytype, YYSTYPE const * const yyvaluep)
{
  YYFPRINTF (yyo, "%s %s (",
             yytype < YYNTOKENS ? "token" : "nterm", yytname[yytype]);

  yy_symbol_value_print (yyo, yytype, yyvaluep);
  YYFPRINTF (yyo, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

static void
yy_stack_print (yy_state_t *yybottom, yy_state_t *yytop)
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)                            \
do {                                                            \
  if (yydebug)                                                  \
    yy_stack_print ((Bottom), (Top));                           \
} while (0)


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

static void
yy_reduce_print (yy_state_t *yyssp, YYSTYPE *yyvsp, int yyrule)
{
  int yylno = yyrline[yyrule];
  int yynrhs = yyr2[yyrule];
  int yyi;
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %d):\n",
             yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr,
                       yystos[+yyssp[yyi + 1 - yynrhs]],
                       &yyvsp[(yyi + 1) - (yynrhs)]
                                              );
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)          \
do {                                    \
  if (yydebug)                          \
    yy_reduce_print (yyssp, yyvsp, Rule); \
} while (0)

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif


#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen(S) (YY_CAST (YYPTRDIFF_T, strlen (S)))
#  else
/* Return the length of YYSTR.  */
static YYPTRDIFF_T
yystrlen (const char *yystr)
{
  YYPTRDIFF_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
static char *
yystpcpy (char *yydest, const char *yysrc)
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYPTRDIFF_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYPTRDIFF_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
        switch (*++yyp)
          {
          case '\'':
          case ',':
            goto do_not_strip_quotes;

          case '\\':
            if (*++yyp != '\\')
              goto do_not_strip_quotes;
            else
              goto append;

          append:
          default:
            if (yyres)
              yyres[yyn] = *yyp;
            yyn++;
            break;

          case '"':
            if (yyres)
              yyres[yyn] = '\0';
            return yyn;
          }
    do_not_strip_quotes: ;
    }

  if (yyres)
    return yystpcpy (yyres, yystr) - yyres;
  else
    return yystrlen (yystr);
}
# endif

/* Copy into *YYMSG, which is of size *YYMSG_ALLOC, an error message
   about the unexpected token YYTOKEN for the state stack whose top is
   YYSSP.

   Return 0 if *YYMSG was successfully written.  Return 1 if *YYMSG is
   not large enough to hold the message.  In that case, also set
   *YYMSG_ALLOC to the required number of bytes.  Return 2 if the
   required number of bytes is too large to store.  */
static int
yysyntax_error (YYPTRDIFF_T *yymsg_alloc, char **yymsg,
                yy_state_t *yyssp, int yytoken)
{
  enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
  /* Internationalized format string. */
  const char *yyformat = YY_NULLPTR;
  /* Arguments of yyformat: reported tokens (one for the "unexpected",
     one per "expected"). */
  char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
  /* Actual size of YYARG. */
  int yycount = 0;
  /* Cumulated lengths of YYARG.  */
  YYPTRDIFF_T yysize = 0;

  /* There are many possibilities here to consider:
     - If this state is a consistent state with a default action, then
       the only way this function was invoked is if the default action
       is an error action.  In that case, don't check for expected
       tokens because there are none.
     - The only way there can be no lookahead present (in yychar) is if
       this state is a consistent state with a default action.  Thus,
       detecting the absence of a lookahead is sufficient to determine
       that there is no unexpected or expected token to report.  In that
       case, just report a simple "syntax error".
     - Don't assume there isn't a lookahead just because this state is a
       consistent state with a default action.  There might have been a
       previous inconsistent state, consistent state with a non-default
       action, or user semantic action that manipulated yychar.
     - Of course, the expected token list depends on states to have
       correct lookahead information, and it depends on the parser not
       to perform extra reductions after fetching a lookahead from the
       scanner and before detecting a syntax error.  Thus, state merging
       (from LALR or IELR) and default reductions corrupt the expected
       token list.  However, the list is correct for canonical LR with
       one exception: it will still contain any token that will not be
       accepted due to an error action in a later state.
  */
  if (yytoken != YYEMPTY)
    {
      int yyn = yypact[+*yyssp];
      YYPTRDIFF_T yysize0 = yytnamerr (YY_NULLPTR, yytname[yytoken]);
      yysize = yysize0;
      yyarg[yycount++] = yytname[yytoken];
      if (!yypact_value_is_default (yyn))
        {
          /* Start YYX at -YYN if negative to avoid negative indexes in
             YYCHECK.  In other words, skip the first -YYN actions for
             this state because they are default actions.  */
          int yyxbegin = yyn < 0 ? -yyn : 0;
          /* Stay within bounds of both yycheck and yytname.  */
          int yychecklim = YYLAST - yyn + 1;
          int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
          int yyx;

          for (yyx = yyxbegin; yyx < yyxend; ++yyx)
            if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR
                && !yytable_value_is_error (yytable[yyx + yyn]))
              {
                if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
                  {
                    yycount = 1;
                    yysize = yysize0;
                    break;
                  }
                yyarg[yycount++] = yytname[yyx];
                {
                  YYPTRDIFF_T yysize1
                    = yysize + yytnamerr (YY_NULLPTR, yytname[yyx]);
                  if (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM)
                    yysize = yysize1;
                  else
                    return 2;
                }
              }
        }
    }

  switch (yycount)
    {
# define YYCASE_(N, S)                      \
      case N:                               \
        yyformat = S;                       \
      break
    default: /* Avoid compiler warnings. */
      YYCASE_(0, YY_("syntax error"));
      YYCASE_(1, YY_("syntax error, unexpected %s"));
      YYCASE_(2, YY_("syntax error, unexpected %s, expecting %s"));
      YYCASE_(3, YY_("syntax error, unexpected %s, expecting %s or %s"));
      YYCASE_(4, YY_("syntax error, unexpected %s, expecting %s or %s or %s"));
      YYCASE_(5, YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s"));
# undef YYCASE_
    }

  {
    /* Don't count the "%s"s in the final size, but reserve room for
       the terminator.  */
    YYPTRDIFF_T yysize1 = yysize + (yystrlen (yyformat) - 2 * yycount) + 1;
    if (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM)
      yysize = yysize1;
    else
      return 2;
  }

  if (*yymsg_alloc < yysize)
    {
      *yymsg_alloc = 2 * yysize;
      if (! (yysize <= *yymsg_alloc
             && *yymsg_alloc <= YYSTACK_ALLOC_MAXIMUM))
        *yymsg_alloc = YYSTACK_ALLOC_MAXIMUM;
      return 1;
    }

  /* Avoid sprintf, as that infringes on the user's name space.
     Don't have undefined behavior even if the translation
     produced a string with the wrong number of "%s"s.  */
  {
    char *yyp = *yymsg;
    int yyi = 0;
    while ((*yyp = *yyformat) != '\0')
      if (*yyp == '%' && yyformat[1] == 's' && yyi < yycount)
        {
          yyp += yytnamerr (yyp, yyarg[yyi++]);
          yyformat += 2;
        }
      else
        {
          ++yyp;
          ++yyformat;
        }
  }
  return 0;
}
#endif /* YYERROR_VERBOSE */

/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
{
  YYUSE (yyvaluep);
  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  YYUSE (yytype);
  YY_IGNORE_MAYBE_UNINITIALIZED_END
}




/* The lookahead symbol.  */
int yychar;

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval;
/* Number of syntax errors so far.  */
int yynerrs;


/*----------.
| yyparse.  |
`----------*/

int
yyparse (void)
{
    yy_state_fast_t yystate;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus;

    /* The stacks and their tools:
       'yyss': related to states.
       'yyvs': related to semantic values.

       Refer to the stacks through separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* The state stack.  */
    yy_state_t yyssa[YYINITDEPTH];
    yy_state_t *yyss;
    yy_state_t *yyssp;

    /* The semantic value stack.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs;
    YYSTYPE *yyvsp;

    YYPTRDIFF_T yystacksize;

  int yyn;
  int yyresult;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken = 0;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;

#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYPTRDIFF_T yymsg_alloc = sizeof yymsgbuf;
#endif

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  yyssp = yyss = yyssa;
  yyvsp = yyvs = yyvsa;
  yystacksize = YYINITDEPTH;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY; /* Cause a token to be read.  */
  goto yysetstate;


/*------------------------------------------------------------.
| yynewstate -- push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;


/*--------------------------------------------------------------------.
| yysetstate -- set current state (the top of the stack) to yystate.  |
`--------------------------------------------------------------------*/
yysetstate:
  YYDPRINTF ((stderr, "Entering state %d\n", yystate));
  YY_ASSERT (0 <= yystate && yystate < YYNSTATES);
  YY_IGNORE_USELESS_CAST_BEGIN
  *yyssp = YY_CAST (yy_state_t, yystate);
  YY_IGNORE_USELESS_CAST_END

  if (yyss + yystacksize - 1 <= yyssp)
#if !defined yyoverflow && !defined YYSTACK_RELOCATE
    goto yyexhaustedlab;
#else
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYPTRDIFF_T yysize = yyssp - yyss + 1;

# if defined yyoverflow
      {
        /* Give user a chance to reallocate the stack.  Use copies of
           these so that the &'s don't force the real ones into
           memory.  */
        yy_state_t *yyss1 = yyss;
        YYSTYPE *yyvs1 = yyvs;

        /* Each stack pointer address is followed by the size of the
           data in use in that stack, in bytes.  This used to be a
           conditional around just the two extra args, but that might
           be undefined if yyoverflow is a macro.  */
        yyoverflow (YY_("memory exhausted"),
                    &yyss1, yysize * YYSIZEOF (*yyssp),
                    &yyvs1, yysize * YYSIZEOF (*yyvsp),
                    &yystacksize);
        yyss = yyss1;
        yyvs = yyvs1;
      }
# else /* defined YYSTACK_RELOCATE */
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
        goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
        yystacksize = YYMAXDEPTH;

      {
        yy_state_t *yyss1 = yyss;
        union yyalloc *yyptr =
          YY_CAST (union yyalloc *,
                   YYSTACK_ALLOC (YY_CAST (YYSIZE_T, YYSTACK_BYTES (yystacksize))));
        if (! yyptr)
          goto yyexhaustedlab;
        YYSTACK_RELOCATE (yyss_alloc, yyss);
        YYSTACK_RELOCATE (yyvs_alloc, yyvs);
# undef YYSTACK_RELOCATE
        if (yyss1 != yyssa)
          YYSTACK_FREE (yyss1);
      }
# endif

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;

      YY_IGNORE_USELESS_CAST_BEGIN
      YYDPRINTF ((stderr, "Stack size increased to %ld\n",
                  YY_CAST (long, yystacksize)));
      YY_IGNORE_USELESS_CAST_END

      if (yyss + yystacksize - 1 <= yyssp)
        YYABORT;
    }
#endif /* !defined yyoverflow && !defined YYSTACK_RELOCATE */

  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;


/*-----------.
| yybackup.  |
`-----------*/
yybackup:
  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yypact_value_is_default (yyn))
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = yylex ();
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yytable_value_is_error (yyn))
        goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);
  yystate = yyn;
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END

  /* Discard the shifted token.  */
  yychar = YYEMPTY;
  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     '$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
  case 2:
#line 47 "./src/compiler/parser.y"
    {
        print_debug("meaning_reprs: meaning_reprs meaning_repr");
        ast[c] = (yyvsp[0].node);
        (yyvsp[0].node)->isroot = 1;
        ++c;      
        lineNum++;
        colNum = 1;
    }
#line 1475 "parser.tab.c"
    break;

  case 3:
#line 56 "./src/compiler/parser.y"
    {
        print_debug("meaning_reprs: meaning_repr");
        ast[c] = (yyvsp[0].node);
        (yyvsp[0].node)->isroot = 1;
        ++c;  
        lineNum++;
        colNum = 1;      
    }
#line 1488 "parser.tab.c"
    break;

  case 4:
#line 68 "./src/compiler/parser.y"
                         {
        print_debug("meaning_repr: formula");
        (yyval.node) = (yyvsp[-1].node);
    }
#line 1497 "parser.tab.c"
    break;

  case 5:
#line 72 "./src/compiler/parser.y"
               {
        print_debug("meaning_repr: formula");
        (yyval.node) = (yyvsp[0].node);
    }
#line 1506 "parser.tab.c"
    break;

  case 6:
#line 79 "./src/compiler/parser.y"
                                              {
        print_debug("formulas: formulas connective formula");
        (yyval.node) = newastnode(Connective, NULL);
        (yyval.node)->conntype = (yyvsp[-1].conntype);
        addastchild((yyval.node), (yyvsp[-3].node));
        addastchild((yyval.node), (yyvsp[0].node));
    }
#line 1518 "parser.tab.c"
    break;

  case 7:
#line 86 "./src/compiler/parser.y"
                                  {
        print_debug("formulas: formulas connective formula");
        (yyval.node) = newastnode(Connective, NULL);
        (yyval.node)->conntype = (yyvsp[-1].conntype);
        addastchild((yyval.node), (yyvsp[-2].node));
        addastchild((yyval.node), (yyvsp[0].node));
    }
#line 1530 "parser.tab.c"
    break;

  case 8:
#line 93 "./src/compiler/parser.y"
                                              {

    }
#line 1538 "parser.tab.c"
    break;

  case 9:
#line 96 "./src/compiler/parser.y"
                                        {
        print_debug("formulas: formulas connective MINUS formula");
        (yyval.node) = newastnode(Connective, NULL);
        (yyval.node)->conntype = (yyvsp[-2].conntype);
        (yyvsp[0].node)->isnegative = 1;
        addastchild((yyval.node), (yyvsp[-3].node));
        addastchild((yyval.node), (yyvsp[0].node));
    }
#line 1551 "parser.tab.c"
    break;

  case 10:
#line 104 "./src/compiler/parser.y"
              {
        print_debug("formulas: formula");
        (yyval.node) = (yyvsp[0].node);
    }
#line 1560 "parser.tab.c"
    break;

  case 11:
#line 108 "./src/compiler/parser.y"
                    {
        print_debug("formulas: MINUS formula");
        (yyval.node) = (yyvsp[0].node);
        (yyval.node)->isnegative = 1;
    }
#line 1570 "parser.tab.c"
    break;

  case 12:
#line 116 "./src/compiler/parser.y"
                                          {
        print_debug("formula: quantify_expr LBRAC terms RBRAC");
        (yyval.node) = (yyvsp[-4].node);
        addastchild((yyval.node), (yyvsp[-1].node));
        closecstscope(csts[c], (yyvsp[-4].node)->token->symbol);
    }
#line 1581 "parser.tab.c"
    break;

  case 13:
#line 122 "./src/compiler/parser.y"
                                                      {
        print_debug("formula: LBRAC quantify_expr DOT LBRAC terms RBRAC RBRAC");
        (yyval.node) = (yyvsp[-5].node);
        addastchild((yyval.node), (yyvsp[-2].node));
        closecstscope(csts[c], (yyvsp[-5].node)->token->symbol);
    }
#line 1592 "parser.tab.c"
    break;

  case 14:
#line 131 "./src/compiler/parser.y"
                            {
        print_debug("terms: terms term");
        if ((yyvsp[0].node) == NULL) {
            (yyval.node) = (yyvsp[-2].node);
        } else {
            // $$ = newastnode(Connective, $2);
            (yyval.node) = newastnode(Connective, NULL);
            (yyval.node)->conntype = (yyvsp[-1].conntype);
            addastchild((yyval.node), (yyvsp[-2].node));
            addastchild((yyval.node), (yyvsp[0].node));
        }
    }
#line 1609 "parser.tab.c"
    break;

  case 15:
#line 143 "./src/compiler/parser.y"
                                        {
        print_debug("terms: terms connective LBRAC term RBRAC");
        if ((yyvsp[-1].node) == NULL) {
            (yyval.node) = (yyvsp[-4].node);
        } else {
            (yyval.node) = newastnode(Connective, NULL);
            (yyval.node)->conntype = (yyvsp[-3].conntype);
            addastchild((yyval.node), (yyvsp[-4].node));
            addastchild((yyval.node), (yyvsp[-1].node));
        }
    }
#line 1625 "parser.tab.c"
    break;

  case 16:
#line 154 "./src/compiler/parser.y"
                                  {
        print_debug("terms: terms term");
        // $$ = newastnode(Connective, $2);
        (yyval.node) = newastnode(Connective, NULL);
        (yyvsp[0].node)->isnegative = 1;
        (yyval.node)->conntype = (yyvsp[-2].conntype);
        addastchild((yyval.node), (yyvsp[-3].node));
        addastchild((yyval.node), (yyvsp[0].node));
    }
#line 1639 "parser.tab.c"
    break;

  case 17:
#line 163 "./src/compiler/parser.y"
                                              {
        print_debug("terms: terms term");
        // $$ = newastnode(Connective, $2);
        (yyval.node) = newastnode(Connective, NULL);
        (yyvsp[-1].node)->isnegative = 1;
        (yyval.node)->conntype = (yyvsp[-4].conntype);
        addastchild((yyval.node), (yyvsp[-5].node));
        addastchild((yyval.node), (yyvsp[-1].node));
    }
#line 1653 "parser.tab.c"
    break;

  case 18:
#line 172 "./src/compiler/parser.y"
                       {
        
    }
#line 1661 "parser.tab.c"
    break;

  case 19:
#line 175 "./src/compiler/parser.y"
           {
        print_debug("terms: term");
        (yyval.node) = (yyvsp[0].node);
    }
#line 1670 "parser.tab.c"
    break;

  case 20:
#line 179 "./src/compiler/parser.y"
                 {
        print_debug("term: MINUS term");
        (yyval.node) = (yyvsp[0].node);
        (yyval.node)->isnegative = 1;
    }
#line 1680 "parser.tab.c"
    break;

  case 21:
#line 184 "./src/compiler/parser.y"
                             {
        print_debug("term: MINUS term");
        (yyval.node) = (yyvsp[-1].node);
        (yyval.node)->isnegative = 1;
    }
#line 1690 "parser.tab.c"
    break;

  case 22:
#line 192 "./src/compiler/parser.y"
                                                                      {
        print_debug("term: PREDICATE CURLY_LBRAC pos_tag CURLY_RBRAC LBRAC arguments RBRAC");
        #if PARDEBUG
        printf("Predicate(%s), Syntax(%s)\n", (yyvsp[-6].t)->symbol, ptbsyntax2string((yyvsp[-4].ptb)));
        #endif
        if ((yyvsp[-4].ptb) == MD) {
            /* skip the predicates with syntax is Modal, e.g. should, must */
            /* and, the prerequisite is the arguments are formula */
            if (getnodelistlength((yyvsp[-1].nodelist)) == 1) {
                (yyval.node) = (yyvsp[-1].nodelist)->node;
                free((yyvsp[-1].nodelist));
                if ((yyval.node)->type == Predicate) {
                    enqueue(predicates[c], (void*)(yyval.node));
                }
            } else {
                fprintf(stderr, "This meaning representation is not supported by the current grammar.\n");
            }
        } else {
            (yyval.node) = newastnode(Predicate, (yyvsp[-6].t));    
            if ((yyval.node)->token->symbol[0] == '_') {
                /* removing the underscore */
                popchar((yyval.node)->token->symbol);
            }
            addastchildren((yyval.node), (yyvsp[-1].nodelist));
            (yyval.node)->syntax = (yyvsp[-4].ptb);
            /* predicate node is marked in a queue and si identification is processed later  */            
            enqueue(predicates[c], (void*)(yyval.node));
        }        
    }
#line 1724 "parser.tab.c"
    break;

  case 23:
#line 221 "./src/compiler/parser.y"
                                                                              {
        print_debug("term: PREDICATE(Modal)) CURLY_LBRAC pos_tag CURLY_RBRAC LBRAC LBRAC terms RBRAC RBRAC");
        (yyval.node) = (yyvsp[-2].node);
    }
#line 1733 "parser.tab.c"
    break;

  case 24:
#line 225 "./src/compiler/parser.y"
                    {
        print_debug("term: PREDICATE(TrueP)");
        (yyval.node) = NULL;
    }
#line 1742 "parser.tab.c"
    break;

  case 25:
#line 229 "./src/compiler/parser.y"
                                                                                               {
        print_debug("term: grammar_term LBRAC PREDICATE CURLY_LBRAC pos_tag CURLY_RBRAC LBRAC arguments RBRAC RBRAC");
        (yyval.node) = newastnode(Predicate, (yyvsp[-7].t));
        if ((yyval.node)->token->symbol[0] == '_') {
            /* removing the underscore */
            popchar((yyval.node)->token->symbol);
        }
        (yyval.node)->syntax = (yyvsp[-5].ptb);
        (yyval.node)->gtype = (yyvsp[-9].gtype);
        addastchildren((yyval.node), (yyvsp[-2].nodelist));
        /* predicate node is marked in a queue and si identification is processed later  */
        enqueue(predicates[c], (void*)(yyval.node));
    }
#line 1760 "parser.tab.c"
    break;

  case 26:
#line 242 "./src/compiler/parser.y"
              {
        print_debug("term: formula");
        (yyval.node) = (yyvsp[0].node);
    }
#line 1769 "parser.tab.c"
    break;

  case 27:
#line 246 "./src/compiler/parser.y"
                                             {
        print_debug("term: LBRAC formula connective formula RBRAC");
        (yyval.node) = newastnode(Connective, NULL);
        (yyval.node)->conntype = (yyvsp[-2].conntype);
        addastchild((yyval.node), (yyvsp[-3].node));
        addastchild((yyval.node), (yyvsp[-1].node));
    }
#line 1781 "parser.tab.c"
    break;

  case 28:
#line 253 "./src/compiler/parser.y"
                                                  {
        print_debug("term: LBRAC GCASE LBRAC VARIABLE RBRAC EQUAL VARIABLE RBRAC");
        (yyval.node) = NULL;
    }
#line 1790 "parser.tab.c"
    break;

  case 29:
#line 257 "./src/compiler/parser.y"
                                  {
        print_debug("term: LBRAC GCASE LBRAC arguments RBRAC RBRAC");
        (yyval.node) = NULL;
    }
#line 1799 "parser.tab.c"
    break;

  case 30:
#line 261 "./src/compiler/parser.y"
                                  {
        print_debug("term: LBRAC IDENTIFIER EQUAL IDENTIFIER RBRAC");
        (yyval.node) = newastnode(Operator, (yyvsp[-1].t));
        struct astnode *left = newastnode(Variable, (yyvsp[-2].t));
        struct astnode *right = newastnode(Variable, (yyvsp[0].t));
        if (addcstref(csts[c], (yyvsp[-2].t)->symbol, left) != 0) {
            addcstsymbol(csts[c], (yyvsp[-2].t)->symbol);
            addcstref(csts[c], (yyvsp[-2].t)->symbol, left);
        }
        if (addcstref(csts[c], (yyvsp[0].t)->symbol, right) != 0) {
            addcstsymbol(csts[c], (yyvsp[0].t)->symbol);
            addcstref(csts[c], (yyvsp[0].t)->symbol, right);
        }
        addastchild((yyval.node), left);
        addastchild((yyval.node), right);
        enqueue(operators[c], (void*)(yyval.node));
    }
#line 1821 "parser.tab.c"
    break;

  case 31:
#line 281 "./src/compiler/parser.y"
                               {
        print_debug("arguments: arguments argument");
        appendnode((yyvsp[-2].nodelist), (yyvsp[0].node));
        (yyval.nodelist) = (yyvsp[-2].nodelist);
    }
#line 1831 "parser.tab.c"
    break;

  case 32:
#line 286 "./src/compiler/parser.y"
               {
        print_debug("arguments: argument");
        (yyval.nodelist) = newastnodelist((yyvsp[0].node));
    }
#line 1840 "parser.tab.c"
    break;

  case 33:
#line 293 "./src/compiler/parser.y"
                 {
        print_debug("argument: IDENTIFIER");        
        (yyval.node) = newastnode(Variable, (yyvsp[0].t));
        if (addcstref(csts[c], (yyvsp[0].t)->symbol, (yyval.node)) != 0) {
            addcstsymbol(csts[c], (yyvsp[0].t)->symbol);
            addcstref(csts[c], (yyvsp[0].t)->symbol, (yyval.node));
        }
    }
#line 1853 "parser.tab.c"
    break;

  case 34:
#line 301 "./src/compiler/parser.y"
              {
        print_debug("argument: formula");
        (yyval.node) = (yyvsp[0].node);
    }
#line 1862 "parser.tab.c"
    break;

  case 35:
#line 308 "./src/compiler/parser.y"
                                {
        print_debug("quantify_expr: KEYWORD_EXISTS IDENTIFIER");
        (yyval.node) = newastnode(Quantifier, (yyvsp[0].t));
        (yyval.node)->qtype = Quantifier_Exists;
        if (addcstref(csts[c], (yyvsp[0].t)->symbol, (yyval.node)) != 0) {
            addcstsymbol(csts[c], (yyvsp[0].t)->symbol);
            addcstref(csts[c], (yyvsp[0].t)->symbol, (yyval.node));
        }
    }
#line 1876 "parser.tab.c"
    break;

  case 36:
#line 317 "./src/compiler/parser.y"
                                {
        print_debug("quantify_expr: KEYWORD_FORALL IDENTIFIER");
        (yyval.node) = newastnode(Quantifier, (yyvsp[0].t));
        (yyval.node)->qtype = Quantifier_ForAll;
        if (addcstref(csts[c], (yyvsp[0].t)->symbol, (yyval.node)) != 0) {
            addcstsymbol(csts[c], (yyvsp[0].t)->symbol);
            addcstref(csts[c], (yyvsp[0].t)->symbol, (yyval.node));
        }    
    }
#line 1890 "parser.tab.c"
    break;

  case 37:
#line 331 "./src/compiler/parser.y"
                 { (yyval.ptb) = NN; }
#line 1896 "parser.tab.c"
    break;

  case 38:
#line 332 "./src/compiler/parser.y"
                  { (yyval.ptb) = NNS; }
#line 1902 "parser.tab.c"
    break;

  case 39:
#line 333 "./src/compiler/parser.y"
                  { (yyval.ptb) = NNP; }
#line 1908 "parser.tab.c"
    break;

  case 40:
#line 334 "./src/compiler/parser.y"
                   { (yyval.ptb) = NNPS; }
#line 1914 "parser.tab.c"
    break;

  case 41:
#line 335 "./src/compiler/parser.y"
                 { (yyval.ptb) = IN; }
#line 1920 "parser.tab.c"
    break;

  case 42:
#line 336 "./src/compiler/parser.y"
                 { (yyval.ptb) = JJ; }
#line 1926 "parser.tab.c"
    break;

  case 43:
#line 337 "./src/compiler/parser.y"
                  { (yyval.ptb) = JJR; }
#line 1932 "parser.tab.c"
    break;

  case 44:
#line 338 "./src/compiler/parser.y"
                  { (yyval.ptb) = JJS; }
#line 1938 "parser.tab.c"
    break;

  case 45:
#line 339 "./src/compiler/parser.y"
                 { (yyval.ptb) = VB; }
#line 1944 "parser.tab.c"
    break;

  case 46:
#line 340 "./src/compiler/parser.y"
                  { (yyval.ptb) = VBZ; }
#line 1950 "parser.tab.c"
    break;

  case 47:
#line 341 "./src/compiler/parser.y"
                  { (yyval.ptb) = VBN; }
#line 1956 "parser.tab.c"
    break;

  case 48:
#line 342 "./src/compiler/parser.y"
                  { (yyval.ptb) = VBP; }
#line 1962 "parser.tab.c"
    break;

  case 49:
#line 343 "./src/compiler/parser.y"
                 { (yyval.ptb) = DT; }
#line 1968 "parser.tab.c"
    break;

  case 50:
#line 344 "./src/compiler/parser.y"
                 { (yyval.ptb) = CD; }
#line 1974 "parser.tab.c"
    break;

  case 51:
#line 345 "./src/compiler/parser.y"
                 { (yyval.ptb) = CC; }
#line 1980 "parser.tab.c"
    break;

  case 52:
#line 346 "./src/compiler/parser.y"
                  { (yyval.ptb) = PRP; }
#line 1986 "parser.tab.c"
    break;

  case 53:
#line 347 "./src/compiler/parser.y"
                 { (yyval.ptb) = MD; }
#line 1992 "parser.tab.c"
    break;

  case 54:
#line 348 "./src/compiler/parser.y"
                  { (yyval.ptb) = VBG; }
#line 1998 "parser.tab.c"
    break;

  case 55:
#line 349 "./src/compiler/parser.y"
                  { (yyval.ptb) = RB; }
#line 2004 "parser.tab.c"
    break;

  case 56:
#line 353 "./src/compiler/parser.y"
          { (yyval.conntype) = Op_And; }
#line 2010 "parser.tab.c"
    break;

  case 57:
#line 354 "./src/compiler/parser.y"
            { (yyval.conntype) = Op_Equivalent; }
#line 2016 "parser.tab.c"
    break;

  case 58:
#line 355 "./src/compiler/parser.y"
            { (yyval.conntype) = Op_Imply; }
#line 2022 "parser.tab.c"
    break;

  case 59:
#line 356 "./src/compiler/parser.y"
            { (yyval.conntype) = Op_Or; }
#line 2028 "parser.tab.c"
    break;

  case 60:
#line 360 "./src/compiler/parser.y"
                   { (yyval.gtype) = Gram_Prog; }
#line 2034 "parser.tab.c"
    break;


#line 2038 "parser.tab.c"

      default: break;
    }
  /* User semantic actions sometimes alter yychar, and that requires
     that yytoken be updated with the new translation.  We take the
     approach of translating immediately before every use of yytoken.
     One alternative is translating here after every semantic action,
     but that translation would be missed if the semantic action invokes
     YYABORT, YYACCEPT, or YYERROR immediately after altering yychar or
     if it invokes YYBACKUP.  In the case of YYABORT or YYACCEPT, an
     incorrect destructor might then be invoked immediately.  In the
     case of YYERROR or YYBACKUP, subsequent parser actions might lead
     to an incorrect destructor call or verbose syntax error message
     before the lookahead is translated.  */
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;

  /* Now 'shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */
  {
    const int yylhs = yyr1[yyn] - YYNTOKENS;
    const int yyi = yypgoto[yylhs] + *yyssp;
    yystate = (0 <= yyi && yyi <= YYLAST && yycheck[yyi] == *yyssp
               ? yytable[yyi]
               : yydefgoto[yylhs]);
  }

  goto yynewstate;


/*--------------------------------------.
| yyerrlab -- here on detecting error.  |
`--------------------------------------*/
yyerrlab:
  /* Make sure we have latest lookahead translation.  See comments at
     user semantic actions for why this is necessary.  */
  yytoken = yychar == YYEMPTY ? YYEMPTY : YYTRANSLATE (yychar);

  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
# define YYSYNTAX_ERROR yysyntax_error (&yymsg_alloc, &yymsg, \
                                        yyssp, yytoken)
      {
        char const *yymsgp = YY_("syntax error");
        int yysyntax_error_status;
        yysyntax_error_status = YYSYNTAX_ERROR;
        if (yysyntax_error_status == 0)
          yymsgp = yymsg;
        else if (yysyntax_error_status == 1)
          {
            if (yymsg != yymsgbuf)
              YYSTACK_FREE (yymsg);
            yymsg = YY_CAST (char *, YYSTACK_ALLOC (YY_CAST (YYSIZE_T, yymsg_alloc)));
            if (!yymsg)
              {
                yymsg = yymsgbuf;
                yymsg_alloc = sizeof yymsgbuf;
                yysyntax_error_status = 2;
              }
            else
              {
                yysyntax_error_status = YYSYNTAX_ERROR;
                yymsgp = yymsg;
              }
          }
        yyerror (yymsgp);
        if (yysyntax_error_status == 2)
          goto yyexhaustedlab;
      }
# undef YYSYNTAX_ERROR
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

      if (yychar <= YYEOF)
        {
          /* Return failure if at end of input.  */
          if (yychar == YYEOF)
            YYABORT;
        }
      else
        {
          yydestruct ("Error: discarding",
                      yytoken, &yylval);
          yychar = YYEMPTY;
        }
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:
  /* Pacify compilers when the user code never invokes YYERROR and the
     label yyerrorlab therefore never appears in user code.  */
  if (0)
    YYERROR;

  /* Do not reclaim the symbols of the rule whose action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;      /* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (!yypact_value_is_default (yyn))
        {
          yyn += YYTERROR;
          if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
            {
              yyn = yytable[yyn];
              if (0 < yyn)
                break;
            }
        }

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
        YYABORT;


      yydestruct ("Error: popping",
                  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;


/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;


#if !defined yyoverflow || YYERROR_VERBOSE
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif


/*-----------------------------------------------------.
| yyreturn -- parsing is finished, return the result.  |
`-----------------------------------------------------*/
yyreturn:
  if (yychar != YYEMPTY)
    {
      /* Make sure we have latest lookahead translation.  See comments at
         user semantic actions for why this is necessary.  */
      yytoken = YYTRANSLATE (yychar);
      yydestruct ("Cleanup: discarding lookahead",
                  yytoken, &yylval);
    }
  /* Do not reclaim the symbols of the rule whose action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
                  yystos[+*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  return yyresult;
}
#line 361 "./src/compiler/parser.y"



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
