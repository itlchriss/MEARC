#ifndef SYN_H
#define SYN_H

#include "ast.h"
#include "cst.h"

struct queue *__COMP__cmd_synthesis__(struct cstsymbol *, struct cstsymbol *);

int event_synthesis(struct astnode *);
int IN_code_synthesis(struct astnode *);
int JJ_code_synthesis(struct astnode *);
int JJR_code_synthesis(struct astnode *);
int JJS_code_synthesis(struct astnode *);
int NN_code_synthesis(struct astnode *);
int NNS_code_synthesis(struct astnode *);
int NNP_code_synthesis(struct astnode *);
int CD_code_synthesis(struct astnode *);
int RB_code_synthesis(struct astnode *);
int TO_code_synthesis(struct astnode *);

int __Rel_synthesis__(
    struct cstsymbol *, struct cstsymbol *, struct queue *);


void JJ_event_synthesis_post_operation(struct astnode *, struct queue *);

#endif
