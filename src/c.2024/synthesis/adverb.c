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


extern struct astnode *root;
extern struct queue *predicates, *operators, *silist, *events, *alias;


int RB_code_synthesis(struct astnode *node) {
    event_synthesis(node);
    return 0;
}

