#include <stdlib.h>
#include <string.h>

#include "util.h"
#include "ast.h"
#include "si.h"
#include "cst.h"
#include "event.h"
#include "error.h"

#include "synthesis.h"
#include "sshare.h"


extern struct queue *predicates, *operators, *silist, *events, *alias;
extern struct astnode *root;


/*
    The predicate is a cardinal number. Therefore, the synthesised semantics must be an integer/long. We assume it as integer first.
*/
int CD_code_synthesis(struct astnode *node) { 
    ((struct astnode *)getastchild(node, 0))->cstptr->datatype->p = Integer;
    ((struct astnode *)getastchild(node, 0))->cstptr->datatype->r = UNDEFINED;
    ((struct astnode *)getastchild(node, 0))->cstptr->datatype->i = UNDEFINED;
    return __direct_syntax_synthesis__(node);
}
