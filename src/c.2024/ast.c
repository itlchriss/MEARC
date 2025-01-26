#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "ast.h"
#include "util.h"
#if EVENT_SEMANTICS
#include "event.h"
#endif

extern struct astnode *root;

extern struct astnode *root;

void throwasterror(char *msg, struct token *token);

// this has to agree exactly with the enum in ast.h
char *node_type_name[] = { "Quantifier", "Predicate", "Variable", "Connective", "Synthesised", "Template", "Operator", "GrammarNotation", "Multiple SIs", "TypePredicate", "EventVariable", "EventEntity", "Pronoun" };
char *connective_name[] = { "And", "Or", "Equivalent", "Imply" };
char *quantifier_name[] = { "Exists", "All" };
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

struct astnode *newastnode(enum astnodetype type, struct token *token) {
    struct astnode *new = malloc(sizeof(struct astnode));
    new->type = type;    
    new->token = token;
    new->parent = NULL;
    new->isroot = 0;
    new->isnegative = 0;
    new->cstptr = NULL;
    new->children = malloc(sizeof(struct astnodelist));
    new->children->node = NULL;
    new->children->next = NULL;
    new->children->prev = NULL;
    new->si_q = NULL;
    return new;
}

void deleteastnode(struct astnode *node) {
    if (node->si_q) {
        deallocatequeue(node->si_q, NULL);
    }    
    if (node->token) {
        free(node->token->symbol);
        free(node->token);
    }
    free(node);
}

void __deleteastchild_only__(struct astnode *parent, struct astnode *child) {
    struct astnodelist *children = parent->children;
    while ((children = children->next) != NULL) {
        if (children->node == child) {               
            (children->prev)->next = children->next;
            if (children->next != NULL) {
                children->next->prev = children->prev;
            }
            deleteastnode(child);
            free(children);
        }
    }
}

int deleteastchild(struct astnode *parent, struct astnode *child) {
    int pc = countastchildren(parent);
    struct astnodelist *children = parent->children;
    while ((children = children->next) != NULL) {
        if (children->node == child) {               
            (children->prev)->next = children->next;
            if (children->next != NULL) {
                children->next->prev = children->prev;
            }
            deleteastchildren(child);
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
        deleteastnode(children->node);
        tmp = children;
        children = children->next;
        free(tmp);
    }
    parent->children->next = NULL;
    #if ASTDEBUG
    if (countastchildren(parent) > 0) {
        printf("Error in deleteastchildren");
        exit(-3);
    }
    #endif
}

struct astnode *astsimplification(struct astnode *_root) {
    struct queue *queue = initqueue();
    struct astnode *node, *root = _root, *child;
    enqueue(queue, _root);
    int count = 0;
    while (!isempty(queue)) {
        node = (struct astnode *)dequeue(queue);
        #if ASTDEBUG
        if (node->type == Synthesised) {
            printf("ast simplying node: %s\n", node->token->symbol);
        } else if (node->type == Connective) {
            printf("ast simplying node: %s\n", connective_name[node->conntype]);
        } else if (node->type == Quantifier) {
            printf("ast simplying node: %s %s\n", quantifier_name[node->qtype], node->token->symbol);
        } 
        #endif
        count = countastchildren(node);
        /* to traverse the tree, children nodes have to be added to the queue */
        for (int i = 0; i < count; ++i) {
            struct astnode *child = getastchild(node, i);
            if (child->type == Connective && countastchildren(child) == 0) {
                deleteastchild(node, child);
            } else {
                enqueue(queue, getastchild(node, i));
            }
        }
        count = countastchildren(node);
        /* 
            this is a node that has no effects to the result 
            a node has type connective (and, or, etc.) can only provide meaning when both left and right hand-side operators present
        */
        if (node->type == Synthesised && node->parent != NULL && countastchildren(node->parent) == 1) {
            if (node->parent->isroot == 1) {
                root = node;
                node->parent = NULL;
            } else {
                struct astnodelist *children = node->parent->parent->children;
                while ((children = children->next) != NULL) {
                    if (children->node == node->parent) {               
                        children->node = node;
                        node->parent = node->parent->parent;
                        break;
                    }
                }
            }
        }
        if (count == 1 && (node->type == Connective || (node->type == Quantifier && node->qtype == Quantifier_Exists))) {            
            child = getastchild(node, 0);
            /* xor operation is applied on the new isnegative property */
            child->isnegative = child->isnegative ^ node->isnegative;
            if (node->isroot == 1) {
                child->isroot = 1;
                root = child;
                child->parent = NULL;
            } else {
                struct astnodelist *children = node->parent->children;
                while ((children = children->next) != NULL) {
                    if (children->node == node) {               
                        children->node = child;
                        child->parent = node->parent;
                        break;
                    }
                }
            } 
            deleteastnode(node);
        } else if (count == 0 && (node->type == Connective || node->type == Quantifier)) {
            if (node->isroot != 1) deleteastchild(node->parent, node);
            else if (countastchildren(node) == 0) {
                node->type = Synthesised;
                
            }
        }
        #if ASTDEBUG
        showast(root, 0);
        #endif
    }
    deallocatequeue(queue, NULL);
    return root;
}

struct astnode * deleteastnodeandedge(struct astnode *node, struct astnode *_root) {
    struct astnode *parent = node->parent, *tmp, *root = _root; 
    deleteastchild(parent, node);      
    int count = countastchildren(parent);
    if (count == 0) {
        if (parent->isroot == 1)        
            return parent;
        deleteastchild(parent->parent, parent);
    } else if (count == 1) {
        tmp = getastchild(parent, 0);
        tmp->isnegative = tmp->isnegative ^ parent->isnegative;
        if (parent->isroot == 1) {
            tmp->isroot = 1;
            root = tmp;
            tmp->parent = NULL;
        } else {
            struct astnodelist *children = parent->parent->children;
            while ((children = children->next) != NULL) {
                if (children->node == parent) {               
                    children->node = tmp;
                    tmp->parent = parent->parent;
                    break;
                }
            }
        }
        deleteastnode(parent);
    }
    return root;
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

struct astnode *getlastchild(struct astnodelist *children) {
    struct astnodelist * tmp = children;
    while (tmp->next != NULL) {
        tmp = tmp->next;
    }
    return tmp->node;
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
            printf("%s(%s) Syntax: %s", node_type_name[node->type], node->token->symbol, 
                    ptbsyntax2string(node->syntax));
            break;
        case GrammarNotation:
            printf("%s(%s)", node_type_name[node->type], node->token->symbol);
            break;
        case Quantifier:
            printf("%s %s %s", node_type_name[node->type], quantifier_name[node->qtype], node->token->symbol);
            break;
        case Connective:
            printf("%s", connective_name[node->conntype]);
            break;
        case Variable:                        
        case Operator:        
            printf("%s(%s)", node_type_name[node->type], node->token->symbol);
            break; 
        case Template:
        case Synthesised:
            // printf("%s(%s)[%s]", node_type_name[node->type], node->token->symbol, javadatatype_name[node->jtype]);
            printf("%s(%s)", node_type_name[node->type], node->token->symbol);
            break;
        case MultipleSIs:
            printf("%s(%s)(%d)", node_type_name[node->type], node->token->symbol, node->si_q->count);
            break;
        case TypePredicate:
            printf("%s(%s) Syntax: %s", node_type_name[node->type], node->token->symbol, ptbsyntax2string(node->syntax));
            break;        
        default:
            printf("Unknown type for: %s(%d)", node->token->symbol, node->type);
    }
    if (node->isnegative == 1) {
        printf(" (Negative) ");
    }
    #if MEMDEBUG
    printf(" Memory(%p) ", (void*)node);
    #endif
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
            if (node->token->symbol)
                free(node->token->symbol);
            free(node->token);
        }
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

enum ptbsyntax string2ptbsyntax(char *input) {
    if (strcmp(input, "CC") == 0) return CC;
    else if (strcmp(input, "CD") == 0) return CD;
    else if (strcmp(input, "DT") == 0) return DT;
    else if (strcmp(input, "EX") == 0) return EX;
    else if (strcmp(input, "FW") == 0) return FW;
    else if (strcmp(input, "IN") == 0) return IN;
    else if (strcmp(input, "JJ") == 0) return JJ;
    else if (strcmp(input, "JJR") == 0) return JJR;
    else if (strcmp(input, "JJS") == 0) return JJS;
    else if (strcmp(input, "LS") == 0) return LS;
    else if (strcmp(input, "MD") == 0) return MD;
    else if (strcmp(input, "NN") == 0) return NN;
    else if (strcmp(input, "NNS") == 0) return NNS;
    else if (strcmp(input, "NNP") == 0) return NNP;
    else if (strcmp(input, "NNPS") == 0) return NNPS;
    else if (strcmp(input, "PDT") == 0) return PDT;
    else if (strcmp(input, "POS") == 0) return POS;
    else if (strcmp(input, "PRP") == 0) return PRP;
    else if (strcmp(input, "PRP_POS") == 0) return PRP_POS;
    else if (strcmp(input, "RB") == 0) return RB;
    else if (strcmp(input, "RBR") == 0) return RBR;
    else if (strcmp(input, "RBS") == 0) return RBS;
    else if (strcmp(input, "RP") == 0) return RP;
    else if (strcmp(input, "SYM") == 0) return SYM;
    else if (strcmp(input, "TO") == 0) return TO;
    else if (strcmp(input, "UH") == 0) return UH;
    else if (strcmp(input, "VB") == 0) return VB;
    else if (strcmp(input, "VBD") == 0) return VBD;
    else if (strcmp(input, "VBG") == 0) return VBG;
    else if (strcmp(input, "VBN") == 0) return VBN;
    else if (strcmp(input, "VBP") == 0) return VBP;
    else if (strcmp(input, "VBZ") == 0) return VBZ;
    else if (strcmp(input, "WDT") == 0) return WDT;
    else if (strcmp(input, "WP") == 0) return WP;
    else if (strcmp(input, "WP_POS") == 0) return WP_POS; 
    else if (strcmp(input, "WRB") == 0) return WRB;
    else if (strcmp(input, "Prog") == 0) return Gram_Prog;
    else if (strcmp(input, "Rel") == 0) return Gram_Rel;
    else {
        fprintf(stderr, "Unknown syntactic category %s in the SI files\n", input);
        exit(-2);
    }
}

char *ptbsyntax2string(enum ptbsyntax ptb) {
    if (ptb == CC) return "CC";
    else if (ptb == CD) return "CD";
    else if (ptb == DT) return "DT";
    else if (ptb == EX) return "EX";
    else if (ptb == FW) return "FW";
    else if (ptb == IN) return "IN";
    else if (ptb == JJ) return "JJ";
    else if (ptb == JJR) return "JJR";
    else if (ptb == JJS) return "JJS";
    else if (ptb == LS) return "LS";
    else if (ptb == MD) return "MD";
    else if (ptb == NN) return "NN";
    else if (ptb == NNS) return "NNS";
    else if (ptb == NNP) return "NNP";
    else if (ptb == NNPS) return "NNPS";
    else if (ptb == PDT) return "PDT";
    else if (ptb == POS) return "POS";
    else if (ptb == PRP) return "PRP";
    else if (ptb == PRP_POS) return "PRP_POS";
    else if (ptb == RB) return "RB";
    else if (ptb == RBR) return "RBR";
    else if (ptb == RBS) return "RBS";
    else if (ptb == RP) return "RP";
    else if (ptb == SYM) return "SYM";
    else if (ptb == TO) return "TO";
    else if (ptb == UH) return "UH";
    else if (ptb == VB) return "VB";
    else if (ptb == VBD) return "VBD";
    else if (ptb == VBG) return "VBG";
    else if (ptb == VBN) return "VBN";
    else if (ptb == VBP) return "VBP";
    else if (ptb == VBZ) return "VBZ";
    else if (ptb == WDT) return "WDT";
    else if (ptb == WP) return "WP";
    else if (ptb == WP_POS) return "WP_POS"; 
    else if (ptb == WRB) return "WRB";
    else if (ptb == Gram_Prog) return "Grammar(Progressive)";
    else if (ptb == Gram_Rel) return "Grammar(Relation)";
    else {
        fprintf(stderr, "Unknown syntactic category %d is encountered in function ptbsyntax2string\n", ptb);
        exit(-2);
    }
}

