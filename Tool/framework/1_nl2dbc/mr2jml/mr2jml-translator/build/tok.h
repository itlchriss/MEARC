/* A Bison parser, made by GNU Bison 3.5.1.  */

/* Bison interface for Yacc-like parsers in C

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

/* Undocumented macros, especially those whose name start with YY_,
   are private implementation details.  Do not rely on them.  */

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

#line 107 "parser.tab.h"

};
typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_PARSER_TAB_H_INCLUDED  */
