#ifndef _AST_H
#define _AST_H

#include "util.h"
#include "cst.h"

// this has to agree exactly with the array in ast.c
enum astnodetype { Quantifier, Predicate, Variable, Connective, Synthesised, Template, NoSI, Operator };
enum grammartype { Gram_Prog };
enum astsemtype { Sem_Static, Sem_Dynamic };
enum quantifiertype { Quantifier_Exists, Quantifier_ForAll };
enum conntype { Op_And, Op_Or,  Op_Equivalent, Op_Imply };
// Penn tree bank syntax
enum ptbsyntax {
    CC, CD,DT,EX,FW,IN,JJ,JJR,JJS,LS,MD,NN,NNS,NNP,NNPS,PDT,POS,PRP,PRP_POS,RB,RBR,RBS,RP,SYM,TO,UH,VB,VBD,VBG,VBN,VBP,VBZ,WDT,WP,WP_POS,WRB};
enum contextualtype { Cont_Class, Cont_Interface, Cont_Method, Cont_Parameter };

struct token {
    char *symbol;    
    int line, column;
};

struct astnode {
    enum astnodetype type;        
    struct token *token;    
    struct astnode *parent;
    struct astnodelist *children;
    /* type of contextual element */
    enum contextualtype ctype;
    /* type of quantifier if the type == Quantifier */
    enum quantifiertype qtype;
    /* type of the node with extra grammar information */
    enum grammartype gtype;
    /* type of connective if the type == Connective */
    enum conntype conntype;
    /* type of word, according to penn-tree bank */
    enum ptbsyntax syntax;    
    int priority;
    struct cstsymbol *cstptr;  
    /* default 0 for non-root nodes. 1 indicates this node is a root node */
    int isroot;
    /* default 0 for positive. 1 for negative */
    int isnegative;

    // enum javadatatype jtype;
};

struct astnodelist {
    struct astnode *node;
    struct astnodelist *next;
    struct astnodelist *prev;
};

int getnodelistlength(struct astnodelist *list);
void appendnode(struct astnodelist *list, struct astnode *n);
struct astnodelist *newastnodelist(struct astnode* n);
struct token *newtoken(char *text, int line, int column);
struct astnode *newastnode(enum astnodetype type, struct token *token);
void addastchild(struct astnode *parent, struct astnode *child);
void deleteastnode(struct astnode *node);
int deleteastchild(struct astnode *parent, struct astnode *child);
void deleteastchildren(struct astnode *parent);
void addastchildren(struct astnode *parent, struct astnodelist *children);
void insertastchild(struct astnode *parent, struct astnode *child, int position);
struct astnode *getastchild(struct astnode *parent, int position);
int countastchildren(struct astnode *node);
struct astnode * deleteastnodeandedge(struct astnode *, struct astnode *);
void showast(struct astnode *root, int depth);
void deallocateast(struct astnode *root);
enum ptbsyntax string2ptbsyntax(char *input);
char *ptbsyntax2string(enum ptbsyntax ptb);

/* 
    simplifying the tree after semantic identification and operator resolution 
    this simplification may update the pointer of root node
    return an astnode pointer pointing at root
*/
struct astnode *astsimplification(struct astnode *);

// struct astnode *simplifyast(struct astnode *root, struct queue *pred_queue, struct queue *conn_queue, struct dstnode *fdstptr, struct queue *paramdstptrs);


int iscomparator(char *relation);
int isequality(char *relation);
#endif
