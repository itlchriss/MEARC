#include <stdio.h>
#include <stdlib.h>
#include "opt.h"
#include "util.h"
#include "dag.h"
#include "cst.h"

void cf(struct dag *dag);
void tps(struct dag *dag);
void throwopterror(char *msg);

// checking all the ampersand nodes for simplication
// the ampersand nodes are traversed from the right bottom most one to the top
int optdag(struct dag *dag) {
    cf(dag);
    tps(dag);
    if (dag->opstack->count > 0) {
        printf("optimization failed: %d internal nodes in dag cannot be simplified...\n", dag->opstack->count);
        return 1;
    }
    //TODO: we need to check if all the symbols in the cst have values
    return 0;
}

// Constant folding
void cf(struct dag *dag) {
    struct queue *conststack = dag->conststack;
    while (conststack->count > 0) {
        struct dagnode *eqnode = (struct dagnode *)pop(conststack);
        if (eqnode != NULL && eqnode->children != NULL) {
            struct dagnode *tmp, *vnode, *onode, *tnode;
            struct dagnodelist *child = eqnode->children;
            vnode = onode = NULL;
            while ((child = child->next) != NULL) {
                tmp = child->node;
                if (tmp->type == Dnt_Value) vnode = tmp;
                else if (tmp->type == Dnt_Operand) onode = tmp;
                else {
                    throwopterror("Unknown type encountered in constant folding");
                    goto END;
                }
            }
            if (vnode != NULL && onode != NULL) {
                // setting the value to the symbol table
                setvalue2cst(dag->cst, onode->data, vnode->data);
                tmp = eqnode->parent;
                if (deletedagchild(eqnode, vnode) != 0 || deletedagchild(eqnode, onode) != 0 || deletedagchild(tmp, eqnode)) {
                    throwopterror("Error in deleting nodes in constant folding");
                }
                // removing the subtree and add TrueP node to denote the constant is marked
                tnode = newdagnode("TrueP", Dnt_TrueP);
                adddagchild(tmp, tnode);                            
                push(dag->truepstack, (void*)tnode);    
            } else {
                throwopterror("Value or operand node is not found in constant folding");
                goto END;
            }
        } else {
            throwopterror("No child node exists under the constant operator");
            goto END;
        }
        --conststack->count;
        #ifdef DEBUG 
        printf("Under constant folding...\n");
        showdag(dag);
        #endif
    }
    END:
        return;
}

// TrueP simplification. The TrueP can be TrueP or NTrueP
void tps(struct dag *dag) {
    struct queue *truepstack = dag->truepstack;
    struct queue *opstack = dag->opstack;
    while (truepstack->count > 0) {
        //1. get the parent via the parent pointer
        //  1a. check if the parent is a node with type Dnt_Ampersand, if not, a DAG construction error should be raised
        //2. check the sibilings
        //  2a. if there is EXACTLY only one sibling that has type differs from Dnt_TrueP, then perform the simplification
        //  2b. else, other optimization is required
        struct dagnode *tnode, *tparent, *ntnode;
        tnode = (struct dagnode*)pop(truepstack);
        tparent = tnode->parent;
        if (tparent != NULL && tparent->type == Dnt_Ampersand) {
            struct dagnodelist *children = tparent->children;
            int count = 0;
            while ((children = children->next) != NULL) {
                if (children->node->type != Dnt_TrueP) {
                    ntnode = children->node;
                    ++count;
                }
            }
            // more than one sibling is not TrueP, further optimization is required
            if (count != 1) continue;

            // exactly one sibling is not TrueP
            // we can apply the ampersand as logical "AND" semantic
            // such that, the ampersand and TrueP nodes are removed, as well as the edge in between
            // the not TrueP sibling will be the internal node at the position where the ampersand was
            if (tparent->isroot == 1) {
                deletedagchild(tparent, tnode);          
                dag->d = ntnode;
                ntnode->isroot = 1;      
                deletedagnode(tparent);
                pop(opstack);
            } else if (tparent->parent == NULL) {
                throwopterror("The parent node is not available");
                goto END;
            } else {
                deletedagchild(tparent, tnode);
                adddagchild(tparent->parent, ntnode);
                deletedagchild(tparent->parent, tparent);     
                pop(opstack);           
            }
        } else {
            throwopterror("Either the TrueP has no Ampersand parent, or the parent is null");
            goto END;
        }
        #ifdef DEBUG 
        printf("Under TrueP simplification...\n");
        showdag(dag);
        #endif        
    }
    END:
        return;
}

void throwopterror(char *msg) {
    printf("Optimization error: %s\n", msg);
}