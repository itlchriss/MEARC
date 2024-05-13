#include <string.h>
#include <stdlib.h>

#include "util.h"
#include "cst.h"
#include "si.h"

// TODO: to be tidied up, should not be extern here
extern struct astnode *root;

/*
    a helper function checking the input cst symbol's SI is starting with '__Rel__'.
    If so, c is said to be a dependent value and the result is true, otherwise, the result is false.
    Besides, the one with '__Rel__' SI should have only one SI and this SI starts with '__Rel__', otherwise, a semantic declaration error is thrown
*/
int __is_Rel_dependent__(struct cstsymbol *c) {
    if (c->datalist->count > 1) return FALSE;
    char *data = (char *)gqueue(c->datalist, 0);
    int occur[strlen(data)/7 + 1];
    if (strsearch(data, "__REL__", occur) != 0) return TRUE;        
    else return FALSE;
}


/*
    checking if AT LEAST one type from either queue x or y is in another queue
    if so, the result is true
*/
int __contain_type__(struct queue *x, struct queue *y) {
    for (int i = 0; i < x->count; ++i) {
        char *sx = (char *)gqueue(x, i);
        for (int j = 0; j < y->count; ++j) {
            char *sy = (char *)gqueue(y, j);
            if (strcmp(sx, sy) == 0) return TRUE;
        }
    }
    return FALSE;
}

/*
    a datatype comparator
    if two datatypes are equal, then the result is true
    else the result is false
    NOTE: true can indicate two datatypes are conditionally equal,
        because one of the datatype may have ANY in p or r or both, this is treated as wildcard
*/
int __compare_datatype__(struct datatype *x, struct datatype *y) {
    /* 
        1st condition: ANY can be equal to any datatype. therefore, two ANYs in p and r implies two datatypes are equal 
                       one ANY and another type is explicitly equal also implies true
                       an exceptional case is when r == Object and both r is explicitly equal, 
                        then we have to check if there are any type names exist in both datatypes, for instance,
                            if x->r == y->r == Object, and both x->types and y->types have 'list', the result is true
                            if x->r == y->r == Object, and x->types have 'list', 'collection' and y->types have 'map', the result is false
    */
    if (
        ((x->p == ANY || y->p == ANY) && (x->r == ANY || y->r == ANY)) ||
        (
            (x->p == ANY || y->p == ANY) && 
            x->r == y->r && 
            (
                (x->r == Object && __contain_type__(x->types, y->types)) || 
                x->r != Object
            )
        ) ||
        ((x->r == ANY || y->r == ANY) && x->p == y->p) ||
        (x->p == y->p && x->r == y->r)
    ) 
        return TRUE;
    else
        return FALSE;
}


/*
    if a SI template has type java_method_chain, it consists at least 2 java methods in a comma separated string
    this method breaks them into a queue
*/
struct queue* __get_java_method_interpretations_from_chain__(char *interpretation) {
    struct queue *result = initqueue();
    char *s = (char *)strdup(interpretation), *pos;
    char *token = strtok_r(s, ",", &pos);
    do {
        enqueue(result, (void *)((char *)strdup(token)));
    } while ((token = strtok_r(NULL, ",", &pos)) != NULL);
    free(token);
    free(s);
    return result;
}


/*
    Match an SI with the symbol only. this is used in finding REL SI
*/
int __match_si_with_symbol_only__(void *_si, void *_symbol) {
    struct si *si = (struct si *)_si;
    char *symbol = (char *)_symbol;
    if (strcmp(si->symbol, symbol) == 0) return TRUE;
    else return FALSE;
}


/*
    Match an SI with one argument data type
*/
int __match_si_with_input_arg_datatype__(void *_si, void *_datatype) {
    struct datatype *datatype = (struct datatype *)_datatype;
    struct si *si = (struct si *)_si;
    /* a predicate accepts more than 1 argument can be filtered out */
    if (si->args->count != 1) return FALSE;
    struct si_arg *arg = (struct si_arg *)gqueue(si->args, 0);
    return __compare_datatype__(arg->datatype, datatype);
}


struct queue *__obtain_si_with_cstptr_(struct cstsymbol *x, struct cstsymbol *y, struct queue *siq) {    
    struct queue *result = initqueue();
    for (int k = 0; k < siq->count; ++k) {
        struct si *si = (struct si *)gqueue(siq, k);
        struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0), *arg2 = (struct si_arg *)gqueue(si->args, 1);
        for (int i = 0; i < x->datalist->count; ++i) {
            for (int j = 0; j < y->datalist->count; ++j) {
                char *s = (char *)strdup(si->interpretation);
                char *xdata = (char *)gqueue(x->datalist, i), *ydata = (char *)gqueue(y->datalist, j);
                char *tmp = strrep(s, arg1->symbol, xdata);
                free(s);
                s = tmp;
                tmp = strrep(s, arg2->symbol, ydata);            
                free(s);
                enqueue(result, (void *)tmp);
            }
        }
    }
    return result;
}


/*
    obtaining SI from feeding x and y to si's argument
    the order of feeding is x to the 1st argument and y to the 2nd argument
    there can be cases that a variable is being accepted to two different direct semantic predicates
    therefore, a data list is returned. each data is computed by feeding the cst's data to the si
*/
struct queue *__obtain_si_(struct astnode *x, struct astnode *y, struct si *si) {    
    struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0), *arg2 = (struct si_arg *)gqueue(si->args, 1);
    struct queue *result = initqueue();
    for (int i = 0; i < x->cstptr->datalist->count; ++i) {
        for (int j = 0; j < y->cstptr->datalist->count; ++j) {
            char *s = (char *)strdup(si->interpretation);
            char *xdata = (char *)gqueue(x->cstptr->datalist, i), *ydata = (char *)gqueue(y->cstptr->datalist, j);
            char *tmp = strrep(s, arg1->symbol, xdata);
            free(s);
            s = tmp;
            tmp = strrep(s, arg2->symbol, ydata);            
            free(s);
            enqueue(result, (void *)tmp);
        }
    }
    return result;
}


/*
    obtaining SI from feeding x to si's argument   
    there can be cases that a variable is being accepted to two different direct semantic predicates
    therefore, a data list is returned. each data is computed by feeding the cst's data to the si
*/
struct queue *__enhance_si__(struct astnode *x, struct si *si) {
    struct si_arg *arg1 = (struct si_arg *)gqueue(si->args, 0);
    struct queue *result = initqueue();
    for (int i = 0; i < x->cstptr->datalist->count; ++i) {
        char *s = (char *)strdup(si->interpretation);
        char *xdata = (char *)gqueue(x->cstptr->datalist, i);
        char *tmp = strrep(s, arg1->symbol, xdata);
        free(s);
        enqueue(result, (void *)tmp);
    }
    return result;
}



/* at least one of the children DO NOT HAVE a datatype. such that we loop and acquire all possible subtree sythesised outcome by obtaining semantic interpretation from the template of parent node and synthesising it with its children */
struct queue *__obtain_si_from_subtree_without_precise_datatypes__(struct astnode *parent, struct queue* siq) {
    struct queue *result = initqueue();
    int child_count = countastchildren(parent);
    struct queue *tmp = NULL;
    struct si *_si = NULL;
    if (child_count == 1) {
        /* the case of only one child. this is applied to those that should be an adjective or adverb */
        struct astnode *child1 = (struct astnode *)getastchild(parent, 0);
        /* this case only consider that the child has a data type, and it is matched with the argument's datatype */
        /* child1 matches arg1 */
        for (int i = 0; i < siq->count; ++i) {
            _si = (struct si *)gqueue(siq, i);
            tmp = __enhance_si__(child1, _si);
            while (!isempty(tmp)) {
                enqueue(result, dequeue(tmp));
            }         
            deallocatequeue(tmp, NULL);
        }
    } else {
        /* the case of having two children. this is a bit complicated because we have to do combination of data types */
        struct astnode *child1 = (struct astnode *)getastchild(parent, 0), *child2 = (struct astnode *)getastchild(parent, 1);
        for (int i = 0; i < siq->count; ++i) {
            _si = (struct si *)gqueue(siq, i);
            tmp = __obtain_si_(child1, child2, _si);
            while (!isempty(tmp)) {
                enqueue(result, dequeue(tmp));
            }         
            deallocatequeue(tmp, NULL);
        }
    }    
    return result;
}

int check_need_assigned_entity(struct astnode *node) {
    struct si *si = (struct si *)gqueue(node->si_q, 0);
    struct si_arg *arg = (struct si_arg *)gqueue(si->args, 0);
    if (arg->datatype != NULL && (arg->datatype->p != AnyPrimitiveType || arg->datatype->r != AnyRefType)) return TRUE;
    else return FALSE;
}

int has_Rel_SI(struct queue *siq) {
    struct si *si = (struct si *)gqueue(siq, 0);
    int occur[strlen(si->interpretation)/7 + 1];
    if (strsearch(si->interpretation, "__REL__", occur) != 0) return TRUE;        
    else return FALSE;
}


int __direct_syntax_synthesis__(struct astnode *node) {
    struct astnode *child = (struct astnode *) getastchild(node, 0);
    struct si *targetsi = (struct si *)gqueue(node->si_q, 0);
    /*
        if both p and r are ANY, then it is not specific, so we should not inherit it to overwrite the entity datatype        
    */
    if (targetsi->synthesised_datatype != NULL && 
        targetsi->synthesised_datatype->p >= 0 &&
        targetsi->synthesised_datatype->r >= 0) { 
        child->cstptr->datatype = targetsi->synthesised_datatype;
    }
    if (targetsi->type != -1) {
        child->cstptr->interpretation_type = targetsi->type;
        child->cstptr->datatype->i = targetsi->type;
    }
    // __subtree_with_direct_syntax_operation__(node, child, targetsi->interpretation);
    if (targetsi->interpretation != NULL && strlen(targetsi->interpretation) > 0) {
        enqueue(child->cstptr->datalist, (char *)strdup(targetsi->interpretation));
    }
    child->cstptr->status = Assigned;
    child->cstptr->ref_count--;
    root = deleteastnodeandedge(node, root);
    return 0;
}


void __post_operation_si_subtree_synthesis__(struct astnode *node) {
    node->type = Synthesised; 
    deleteastchildren(node);
}

// void subtree_si_synthesis(struct astnode *node, struct si *si) {
//     node->si_q = __obtain_si_from_subtree_with_precise_datatypes__(node, si);
//     __post_operation_si_subtree_synthesis__(node);
// }

void __combinatorial_subtree_si_synthesis__(struct astnode *node, struct queue *siq) {
    node->si_q = __obtain_si_from_subtree_without_precise_datatypes__(node, siq);
    __post_operation_si_subtree_synthesis__(node);
}