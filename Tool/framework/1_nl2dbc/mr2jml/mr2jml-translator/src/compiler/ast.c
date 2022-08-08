#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "ast.h"
#include "util.h"
#include "dst.h"

void throwasterror(char *msg, struct token *token);

// this has to agree exactly with the enum in ast.h
char *node_type_name[] = { "Exists", "NotExists", "ForAll", "NotForAll", "Predicate", "Variable", "TrivialConnective", "TrueP", "STDPF", "Resolved", "Semantic", "NonTrivialConnective" };
struct dstnode *_fdstptr = NULL;
struct queue *_paramdstptrs = NULL;

int iscomparator(char *relation) {
    if (strcmp(relation, "greater_than_or_equal_to") == 0 ||
        strcmp(relation, "less_than_or_equal_to") == 0 ||
        strcmp(relation, "equal_to") == 0 ||
        strcmp(relation, "less_than") == 0 ||
        strcmp(relation, "greater_than") == 0)

        return 0;
    return 1;
}

int isequality(char *relation) {
    return strcmp(relation, "be");
}

// int iskeyword(struct astnode *node) {
//     struct semantic *sem = getsemantic(node->semantics, 0);
//     if (sem != NULL && sem->data != NULL && sem->data[0] == '\\' && strcmp(sem->args[0], "*") == 0) return 0;
//     return 1;
// }

void appendnode(struct astnodelist *list, struct astnode *n) {
    if (list == NULL) {
        list = (struct astnodelist *) malloc (sizeof(struct astnodelist));
        list->node = n;
        list->next = NULL;
    } else {
        struct astnodelist *ptr = list;
        while (ptr->next != NULL) 
            ptr = ptr->next;
        ptr->next = (struct astnodelist *) malloc (sizeof(struct astnodelist));
        ptr->next->node = n;
        ptr->next->next = NULL;
    }
}

struct astnodelist *newastnodelist(struct astnode* n) {
    struct astnodelist *new = (struct astnodelist *) malloc (sizeof(struct astnodelist));
    new->node = n;
    new->next = NULL;
    return new;
}


struct token *newtoken(char *text, int line, int column) {
    struct token *new = malloc(sizeof(struct token));
    new->symbol = (char *)strdup(text);
    new->line = line;
    new->column = column;
    return new;
}

enum ptbsyntax _getptbsyntax(char *syntax) {
    if (strcmp(syntax, "cc") == 0) return CC;
    else if (strcmp(syntax, "cd") == 0) return CD;
    else if (strcmp(syntax, "dt") == 0) return DT;
    else if (strcmp(syntax, "ex") == 0) return EX;
    else if (strcmp(syntax, "fw") == 0) return FW;
    else if (strcmp(syntax, "in") == 0) return IN;
    else if (strcmp(syntax, "jj") == 0) return JJ;
    else if (strcmp(syntax, "jjr") == 0) return JJR;
    else if (strcmp(syntax, "jjs") == 0) return JJS;
    else if (strcmp(syntax, "ls") == 0) return LS;
    else if (strcmp(syntax, "md") == 0) return MD;
    else if (strcmp(syntax, "nn") == 0) return NN;
    else if (strcmp(syntax, "nns") == 0) return NNS;
    else if (strcmp(syntax, "nnp") == 0) return NNP;
    else if (strcmp(syntax, "nnps") == 0) return NNPS;
    else if (strcmp(syntax, "pdt") == 0) return PDT;
    else if (strcmp(syntax, "prp") == 0) return PRP;
    else if (strcmp(syntax, "pos") == 0) return POS;
    else if (strcmp(syntax, "prp_pos") == 0) return PRP_POS;
    else if (strcmp(syntax, "rb") == 0) return RB;
    else if (strcmp(syntax, "rbr") == 0) return RBR;
    else if (strcmp(syntax, "rbs") == 0) return RBS;
    else if (strcmp(syntax, "rp") == 0) return RP;
    else if (strcmp(syntax, "sym") == 0) return SYM;
    else if (strcmp(syntax, "to") == 0) return TO;
    else if (strcmp(syntax, "uh") == 0) return UH;
    else if (strcmp(syntax, "vb") == 0) return VB;
    else if (strcmp(syntax, "vbd") == 0) return VBD;
    else if (strcmp(syntax, "vbg") == 0) return VBG;
    else if (strcmp(syntax, "vbn") == 0) return VBN;
    else if (strcmp(syntax, "vbp") == 0) return VBP;
    else if (strcmp(syntax, "vbz") == 0) return VBZ;
    else if (strcmp(syntax, "wdt") == 0) return WDT;
    else if (strcmp(syntax, "wp") == 0) return WP;
    else if (strcmp(syntax, "wp_pos") == 0) return WP_POS;
    else if (strcmp(syntax, "wrb") == 0) return WRB;
    else return 0;
}

struct astnode *newastnode(enum astnodetype type, struct token *token) {
    struct astnode *new = malloc(sizeof(struct astnode));
    new->type = type;    
    new->token = token;
    new->syntax = NULL;
    new->parent = NULL;
    new->isroot = 0;
    new->isnegative = 0;
    // default priority is the last priority
    new->priority = 9;
    if (type == Predicate) {
        // Only predicate has syntax
        char *tmp = strdup(token->symbol), *pos;
        char *t = strtok_r(tmp, "@", &pos);
        if (t == NULL) {
            printf("Syntax Error: the functor syntax is incorret %s\n", token->symbol);
        }
        free(new->token->symbol);
        if (t[0] == '-') {
            new->token->symbol = (char *)strdup(t + 2);
            new->isnegative = 1;
        } else {
            new->token->symbol = (char *)strdup(t + 1);
        }
        t = strtok_r(NULL, "@", &pos);
        if (t == NULL) {
            printf("Syntax Error: the functor syntax is incorret %s\n", token->symbol);
        }
        t = (char *)strdup(t) + 1;
        t = strtok_r(t, ",", &pos);
        new->syntax = (char *)strdup(t);
        free(tmp);

        // after acquiring the functor and syntax, deliberately search the static symbol table
        
        new->ptbsyntax = _getptbsyntax(new->syntax);
    }
    new->children = malloc(sizeof(struct astnodelist));
    new->children->node = NULL;
    new->children->next = NULL;
    new->children->prev = NULL;
    new->semantics = initsemlist();
    return new;
}

void deleteastnode(struct astnode *node) {
    if (node->syntax)
        free(node->syntax);
    if (node->token) {
        free(node->token->symbol);
        free(node->token);
    }
    deallocatesemantics(node->semantics);
    free(node);
}

int deleteastchild(struct astnode *parent, struct astnode *child) {
    int pc = countastchildren(parent);
    struct astnodelist *children = parent->children;
    children = parent->children;
    while ((children = children->next) != NULL) {
        if (children->node == child) {               
            (children->prev)->next = children->next;
            deleteastnode(child);
            free(children);            
            if (countastchildren(parent) != pc -1) return 1;
            else return 0;
        }
    } 
    return 1;
}

void deleteastchildren(struct astnode *parent) {
    struct astnodelist *children = parent->children->next, *tmp;
    while (children != NULL) {
        //TODO: remove the cstptr->refs of the node
        for (int i = 0; i < children->node->cstptr->refs->count; ++i) {
            struct astnode *t = (struct astnode*)gqueue(children->node->cstptr->refs, i);
            if (t == children->node) {
                rqueue(children->node->cstptr->refs, i);
                break;
            }
        }
        deleteastnode(children->node);
        tmp = children;
        children = children->next;
        free(tmp);
    }
    parent->children->next = NULL;
    #if DEBUG
    if (countastchildren(parent) > 0) {
        printf("Error in deleteastchildren");
    }
    #endif
}

void transnodedata(struct astnode *node, char *data) {    deleteastchildren(node);
    deleteastchildren(node);
    free(node->token);
    node->token = newtoken(data, 0, 0);
}

void transresolved(struct astnode *node, char *data) {
    transnodedata(node, data);
    node->type = Resolved;
}

// void removesubtree(struct astnode *node) {
//     deleteastchildren(node);
//     deleteastchild(node->parent, node);
// }

void transrelation(struct astnode *node) {
    struct semantic *sem = getsemantic(node->semantics, 0);
    char *template = sem->data;
    struct dstnode *dstptr = NULL;
    struct astnode *child;
    struct astnode *child1 = getastchild(node, 0);
    struct astnode *child2 = getastchild(node, 1);
    
    if ((iscomparator(node->token->symbol) == 0 || isequality(node->token->symbol) == 0) &&
        child2->token->symbol[0] == '\\') {
        // if the node is a comparator, then we have to make the LHS and RHS follow a rule:
        //   if there is a child's semantic is a forall or exists, it must be located on the LHS
        template = strrep(template, sem->args[1], child1->token->symbol);
        template = strrep(template, sem->args[0], child2->token->symbol);        
    } 
    if (strcmp(template, "\\sub") == 0) {
        if (child1->type == Variable && child2->type == Semantic) {
            // Substituting semantic of child2 to all the variables used in child1 
            for (int i = 0; i < child1->cstptr->refs->count; ++i) {
                struct astnode *tmp = (struct astnode*)gqueue(child1->cstptr->refs, i);
                free(tmp->token->symbol);
                tmp->token->symbol = (char*)strdup(child2->token->symbol);
            }
            template = "";
        }
    } else {
        for (int i = 0; i < countastchildren(node); ++i) {
            child = getastchild(node, i);
            // TODO: we have to avoid the replacement doubled. such as (x,y):x == y, sub y to x: y == y, sub x to y: x == x
            char *s = strrep(template, sem->args[i], child->token->symbol);
            if (s == NULL) continue;
            else {
                template = s;
                if (child->cstptr->dstptr != NULL) dstptr = child->cstptr->dstptr;
            }
        }    
    }

    if (isequality(node->token->symbol) == 0 && !(child1->type == Semantic && child2->type == Semantic)) {        
        template = "";
        free(child2->cstptr->token->symbol);
        child2->cstptr->token->symbol = strdup(child1->token->symbol);        
        for (int i = 0; i < child2->cstptr->refs->count; ++i) {
            struct astnode *tmp = (struct astnode*)gqueue(child2->cstptr->refs, i);
            free(tmp->token->symbol);
            tmp->token->symbol = strdup(child1->token->symbol);
        }
        if (child2->cstptr->dstptr == NULL) {
            child2->cstptr->dstptr = child1->cstptr->dstptr;
        } else if (child1->cstptr->dstptr == NULL) {
            child1->cstptr->dstptr = child2->cstptr->dstptr;
        }    
    } else if (iscomparator(node->token->symbol) == 0) {
        // TODO: we apply a naive setting to assign propagate the dstptr to all other null arguments
        for (int i = 0; i < countastchildren(node); ++i) {
            child = getastchild(node, i);
            if (child->cstptr->dstptr == NULL) child->cstptr->dstptr = dstptr;
        }
        // if (child1->type == Variable && child2->type == Semantic) {
        //     // Substituting semantic of child2 to all the variables used in child1 
        //     for (int i = 0; i < child1->cstptr->refs->count; ++i) {
        //         struct astnode *tmp = (struct astnode*)gqueue(child1->cstptr->refs, i);
        //         free(tmp->token->symbol);
        //         tmp->token->symbol = (char*)strdup(child2->token->symbol);
        //     }
        //     template = "";
        //     transresolved(node, "");
        // }
    }
    transresolved(node, template);
}

// // transform constant node
void transconstant(struct astnode *node, void *ptr) {
    // the constant node should have only one child and the child must be a variable
    struct astnode *child = getastchild(node, 0);
    // TODO: for multiple semantics existing in one node, we have to implement the cst symbol point to a "semantics" structure
    struct semantic *sem = getsemantic(node->semantics, 0);

    if (sem->sstype == SemSource_Dynamic) {
        // we have to provide the dstptr to the runtime symbol
        // and transform the node
        // TODO: replacing all the symbols pointed to the child->cstptr with the node->token->symbol
        for (int i = 0; i < child->cstptr->refs->count; ++i) {
            struct astnode *tmp = (struct astnode*)gqueue(child->cstptr->refs, i);
            free(tmp->token->symbol);
            tmp->token->symbol = (char*)strdup(node->token->symbol);
            tmp->type = Semantic;
        }
        transresolved(node, "");
    } else {    
        if (strcmp(sem->data, "\\param") == 0) {
            struct queue* params = (struct queue*)ptr;        
            if (params == NULL) {
                transresolved(node, "");
                return;
            }
            if (params->count > 1) {
                printf("Warning: Please remember to specify the parameter name in the specification. There are more than one parameters for the testing predicate\n");
                printf("         The compiler will not generate the multiple instance of specifications for the current version....\n");
            } else {
                child->cstptr->dstptr = (struct dstnode*)gqueue(params, 0);
                // template = child->cstptr->dstptr->symbol;
                // TODO: replacing all the symbols pointed to the child->cstptr with the child->cstptr->dstptr->symbol
                for (int i = 0; i < child->cstptr->refs->count; ++i) {
                    struct astnode *tmp = (struct astnode*)gqueue(child->cstptr->refs, i);
                    // free(tmp->token->symbol);
                    tmp->token->symbol = (char*)strdup(child->cstptr->dstptr->symbol);
                    tmp->type = Semantic;
                }
                transresolved(node, "");
            }            
        } else {
            if (strcmp(sem->args[0], "*") == 0) { 
                // this is a keyword
                // a keyword can be a software element keyword or a jml keyword
                for (int i = 0; i < child->cstptr->refs->count; ++i) {
                    struct astnode *tmp = (struct astnode*)gqueue(child->cstptr->refs, i);
                    // free(tmp->token->symbol);
                    tmp->token->symbol = (char*)strdup(sem->data);
                    tmp->type = Semantic;
                }
                transresolved(node, "");
            } else {
                // this is a predicate with parameters
                // substitute the child symbol to the predicate parameter
                char *template;
                if (child->cstptr->of_referal != NULL) {
                    template = strrep(sem->data, sem->args[0], child->cstptr->of_referal->token->symbol);
                } else {
                    template = strrep(sem->data, sem->args[0], child->token->symbol);
                    transresolved(node, template);
                }                       
            }

            if (strcmp(sem->data, "\\result") == 0) {
                // propagate the resulting type to the variable
                child->cstptr->dstptr = ptr;
                for (int i = 0; i < child->cstptr->refs->count; ++i) {
                    struct astnode *tmp = (struct astnode*)gqueue(child->cstptr->refs, i);
                    // free(tmp->token->symbol);
                    tmp->token->symbol = (char*)strdup("\\result");
                    tmp->type = Semantic;
                }
                transresolved(node, "");
            }
        }
    }
    for (int i = 1; i < node->semantics->s->count; ++i) {
        struct semantic *tmpsem = getsemantic(node->semantics, i);
        if (tmpsem->sstype == SemSource_Dynamic) {
            child->cstptr->dstptr = tmpsem->sourceptr;
        }
    }
}

void transIN(struct astnode *node) {
    if (strcmp(node->token->symbol, "of") == 0) {
        struct astnode *first = getastchild(node, 0);
        first->cstptr->of_referal = getastchild(node, 1)->cstptr;
        transresolved(node, "");
    }
}

void transidentity(struct astnode *node) {
    struct astnodelist *children = node->children;
    while((children = children->next) != NULL) {
        addastchild(node->parent, children->node);
    }
    deleteastchildren(node);
    deleteastnode(node);
}


struct astnode *simplifyast(struct astnode *root, struct queue *pred_queue, struct queue *conn_queue, struct dstnode *fdstptr, struct queue *paramdstptrs) {
    // steps:
    // 1. transform constants from subtrees to single node containing the semantics
    // 2. if every child of an & node is resolved, remove the & node
    struct astnode *rootptr = root, *node;    
    struct semantic *sem;

    

    // the order of the predicates are in the order of the bottom most to the top most
    //   the ordering is maintained by the parser, due to the parser resolves the inner grammar rules first then to the outer rules
    // the complexity is O(number of predicates)
    while (pred_queue->count > 0) {
        node = (struct astnode *)dequeue(pred_queue);
        #if DEBUG
        printf("AST processing node: %s\n", node->token->symbol);
        #endif
        // TODO: we may have to deal with multiple semantics later. currently we always refer to the first semantic
        sem = getsemantic(node->semantics, 0);
        // if (node->ptbsyntax == IN) {
        //     transIN(node);
        // } else 
        if (sem->sstype == SemSource_Dynamic) {
            // the semantic is a symbol from the source code
            // relating the symbol information to the runtime symbol
            transconstant(node, NULL);
        } else if (strcmp(sem->data, "TrueP") == 0) {
            transresolved(node, "");
        } else {
            // the semantic is a symbol from the static symbol table, aka the standard predicate
            switch (node->ptbsyntax)
            {
            case NN: case NNP: case NNPS: case NNS:
            case CD:
                if (strcmp(sem->data, "\\result") == 0) {
                    transconstant(node, fdstptr);
                } else if (strcmp(sem->data, "\\param")  == 0) {
                    transconstant(node, paramdstptrs);
                } else {
                    transconstant(node, NULL);
                }
                break;
            case JJ: case JJR: case JJS: case VBN: case VBZ: case VB: case VBG: case RB: case VBP:
            case IN:
                transrelation(node);
                break;   
            case MD:
                // transidentity(node);
                break;     
            default:
                throwasterror("Unexpected type of syntax encountered", node->token);
                goto END;
                break;
            }
        }
        #if DEBUG
        printf("In AST Simplification\n");
        showast(rootptr, 0);
        #endif
    }

    struct astnode *child;
    while (conn_queue->count > 0) {
        node = (struct astnode*)dequeue(conn_queue);
        for (int i = 0; i < countastchildren(node); ++i) {
            child = getastchild(node, i);            
            if (child->type != Exists && 
                child->type != NotExists && 
                child->type != ForAll && 
                child->type != NotForAll && 
                child->type != Resolved) {
                printf("Incomplete simplification: a node with type %s cannot be simplified....\n", node_type_name[child->type]);
                goto END;
            } else {
                addastchild(node->parent, child);
            }
        }
        deleteastchild(node->parent, node);
        #if DEBUG
        printf("In & removal\n");
        showast(rootptr, 0);
        #endif
    }
END:
    return rootptr;
}

void addastchild(struct astnode *parent, struct astnode *child) {
    struct astnodelist *new = malloc(sizeof(struct astnodelist));
    new->node = child;
    new->next = NULL;
    child->parent = parent;
    struct astnodelist *children = parent->children;
    while(children->next != NULL)
        children = children->next;
    children->next = new;
    new->prev = children;
}

void addastchildren(struct astnode *parent, struct astnodelist *children) {
    struct astnodelist * tmp = children;
    while (tmp != NULL) {
        addastchild(parent, tmp->node);
        tmp = tmp->next;
    }
    free(children);
}

void insertastchild(struct astnode *parent, struct astnode *child, int position) {
    struct astnodelist *new = malloc(sizeof(struct astnodelist));
    new->node = child;
    struct astnodelist *children = parent->children;
    while(children->next != NULL && position-- > 0)
        children = children->next;
    new->next = children->next;
    children->next = new;
}

struct astnode *getastchild(struct astnode *parent, int position) {
    struct astnodelist *children = parent->children;
    while((children = children->next) != NULL)
        if(position-- == 0)
            return children->node;
    return NULL;
}

int countastchildren(struct astnode *node) {
    if (node->children->next == NULL) return 0;
    int i = 0;
    struct astnodelist *children = node->children;
    // while(getastchild(node, i) != NULL)
        // i++;
    while ((children = children->next) != NULL) {
        i++;
    }
    return i;
}

int getnodelistlength(struct astnodelist *list) {
    int c = 0;
    struct astnodelist *tmp = list;
    while (tmp != NULL) {
        ++c;
        tmp = tmp->next;
    }
    return c;
}

void showast(struct astnode *node, int depth) {
    int i;
    for(i = 0; i < depth; i++)
        printf("..");
    switch(node->type) {
        case Predicate:
            if (getsemantic(node->semantics, 0) != NULL) {
                printf("%s(%s) Syntax: %s.%s", node_type_name[node->type], node->token->symbol, 
                    node->syntax, getsemantic(node->semantics, 0)->data);
            } else {
                printf("%s(%s) Syntax: %s", node_type_name[node->type], node->token->symbol, 
                    node->syntax);
            }
            break;
        case Exists:
        case ForAll:
        case NotExists:
        case NotForAll:
            printf("%s %s", node_type_name[node->type], node->token->symbol);
            break;
        case Variable:
        case Connective:
        case NonTrivialConnective:
        case Semantic:
            printf("%s(%s)", node_type_name[node->type], node->token->symbol);
            break;
        case STDPF:
            printf("%s(%s) : %s", node_type_name[node->type], node->token->symbol, getsemantic(node->semantics, 0)->data);
            break;
        case TrueP:
            printf("%s", node_type_name[node->type]);
            break;
        case Resolved:
            printf("%s: %s", node_type_name[node->type], node->token->symbol);
            break;
        default:
            printf("Unknown type for: %s: %s", node->token->symbol, node_type_name[node->type]);
    }
    if (node->isnegative == 1) {
        printf(" (Negative) ");
    }
    printf("\n");
    struct astnodelist *child = node->children;
    while((child = child->next) != NULL)
        showast(child->node, depth+1);
}

void deallocateast(struct astnode *node) {
    if(node != NULL) {
        struct astnodelist *child = node->children;
        while(child != NULL) {
            deallocateast(child->node);
            struct astnodelist *tmp = child;
            child = child->next;
            free(tmp);
        }
        if(node->token != NULL) {
            free(node->token->symbol);
            free(node->token);
        }
        if(node->syntax != NULL) {
            free(node->syntax);
        }
        deallocatesemantics(node->semantics);
        free(node);
    }
}

void throwasterror(char *msg, struct token *token) {
    printf("AST Error: ");
    if (token != NULL)
        printf("%s for token %s at line %d column %d\n", msg, token->symbol, token->line, token->column);
    else
        printf("%s\n", msg);
}
