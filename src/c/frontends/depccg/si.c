/*
    This is an implementation of semantic interpretation analysis and synthesis specially for the neo-davidsonian event semantics
*/

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "util.h"
#include "si.h"
#include "ast.h"
#include "event.h"

extern struct astnode *root;
extern struct queue *predicates, *operators, *silist, *events;
extern struct astnode *root;
struct queue *cst;    

int search_syntax(struct si*, enum ptbsyntax);
int __simatcher(void *, void *);
int __eventsimatcher(void *, void *);
int __argtype_simatcher(void *, void *);
int __event_argtype_simatcher(void *, void *);
int __sisymbol_duplicated(void *, void *);
int __preposition_argtype_simatcher(void *, void *);
int __match_interpretation_and_get_type(void *, void *);

int selfSI[] = { 1, 0, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1, 1};
char *javadatatype_name[] = { "PRIMITIVE", "ARRAY", "COLLECTION", "JML_EXPRESSION_RESULT", "JML_EXPRESSION_TEMPLATE", "OTHERS" };

void __replace_si_at_parent__(struct astnode *node, enum astnodetype type, char *si) {    
    deleteastchildren(node);
    free(node->token->symbol);
    node->token->symbol = (char*) strdup(si);
    node->type = type;
}

/*
    node: the node must be a predicate node
*/
void __remove_all_children_cst__(struct astnode *node) {
    struct astnode *child;
    for (int i = 0; i < countastchildren(node); ++i) {
        child = getastchild(node, i);
        /* we do not have to deal with the events here. because the structure is independent to the ast tree as they do not have pointers pointing to each other. we will free the events structure at the end of the program. */
        removecstref(child->token->symbol, child);
    }
}

/*
    substituting semantic interpretation from a synthesised node into the template from another node
    _snode: an ast node with type Synthesised, the symbol contains the semantic interpretation
    _tnode: an ast node with type Template, the symbol contains the template
*/
char* __subsititute_synthesised_into_template__(struct astnode *_snode, struct astnode *_tnode) {
    struct cstsymbol *c = searchsymbolbyref(_tnode);
    char *tmp = __combine_3_strings__("(", c->symbol, ")");
    char *new = strrep(_tnode->token->symbol, tmp, _snode->token->symbol);
    free(tmp);
    return new;
}

/* obtaining semantic interpretation from the template of parent node and synthesising it with its children */
char* __obtain_si_from_subtree__(struct astnode *parent, struct si* si) {
    char *s = (char*)strdup(si->interpretation), *tmp = NULL;
    struct astnode *child = NULL;
    for (int i = 0; i < countastchildren(parent); ++i) {
        child = getastchild(parent, i);
        // char *arg = __combine_3_strings__("(", child->token->symbol, ")");
        // tmp = strrep(s, si->args[i], arg);
        if (child->type == Synthesised) {
            /* child semantic interpretation has been resolved. use it in code synthesis */
            tmp = strrep(s, si->args[i], child->token->symbol);
            free(s);
            s = tmp;
        } else {
            /* child semantic interpretation has NOT been resolved. leave the name of the argument as the argument of the semantic interpretation for possible code synthesis to be happened in operator resolution */
            // char *arg = __combine_3_strings__("(", child->token->symbol, ")");
            // tmp = strrep(s, si->args[i], arg);
            // free(arg);
        }
        // free(s);
        // s = tmp;
    }
    return s;
}

int __synthesis_predicate_at_root__(struct si *si) {
    // same as if and only if, we have to do operator resolution, simplification before synthesising the root predicate
    if (predicates->count > 0) {
        // we have to ensure all other predicates are synthesised
        return 1;
    }
    free(root->token->symbol);
    root->token->symbol = (char*)strdup(si->interpretation);
    if (strcmp(root->token->symbol, "==>") == 0) {
        root->conntype = Op_Imply;
    } else if (strcmp(root->token->symbol, "&&") == 0) {
        root->conntype = Op_And;
    } else if (strcmp(root->token->symbol, "||") == 0) {
        root->conntype = Op_Or;
    } else if (strcmp(root->token->symbol, "<==>") == 0) {
        root->conntype = Op_Equivalent;
    } else {
        fprintf(stderr, "Unknown semantics %s is not allowed to be the root node semantic\n", root->token->symbol);
        exit(-16);
    }
    root->type = Connective;
    return 0;
}

void __update_cstsymbol_data__(struct cstsymbol *c, char *data, int datatype) {
    if (c->data) {
        free(c->data);
    }
    c->data = (char*)strdup(data);
    c->type = datatype;
}

void si_alias(struct astnode *node, struct si *si) {
    struct astnode *child = getastchild(node, 0); 
    struct cstsymbol *c = searchsymbolbyref(child);
    __remove_all_children_cst__(node);        
    // TODO: not thoroughly checked this line. removed this line if problem raises    
    if (si == NULL) {
        if (c->si_q) {
            deallocatequeue(c->si_q, NULL);
        }
        c->si_q = copyqueue(node->si_q);
        /* inidicate this symbol is not resolved due to multiple SIs */
        c->type = -9;
    } else {
        c->si_ptr = si;  
        __update_cstsymbol_data__(c, si->interpretation, si->jtype);
        if (c->si_q) {
            deallocatequeue(c->si_q, NULL);
        }   
    }
    syncsymbol(c);    
    root = deleteastnodeandedge(node, root);
}

void subtree_si_synthesis(struct astnode *node, struct si *si) {
    char *s = __obtain_si_from_subtree__(node, si);
    __remove_all_children_cst__(node);
    __replace_si_at_parent__(node, Synthesised, s);
    free(s);
}

void si_substitution(struct astnode *dest, struct astnode *sinode, struct astnode *datanode, struct si *si) {
    struct cstsymbol *c = searchsymbolbyref(sinode);
    char *s = strrep(si->interpretation, si->args[0], datanode->token->symbol);
    __update_cstsymbol_data__(c, s, 0);
    /* checking this sentence */
    __remove_all_children_cst__(dest);
    syncsymbol(c);
    root = deleteastnodeandedge(dest, root);
}

/*
    Using the SI in the datanode to substitute in the argument of sinode
*/
void sibling_si_synthesis(struct astnode *node, struct astnode *sinode, struct astnode *datanode) {
    char *s = __subsititute_synthesised_into_template__(datanode, sinode);
    struct cstsymbol *c = updatecstsymbol(s, sinode);     
    __remove_all_children_cst__(node);
    syncsymbol(c);        
    root = deleteastnodeandedge(node, root);
}

int Jseries_code_synthesis(struct astnode *node, struct si *si) {
    struct astnode *child;
    if (si->source == CONTEXTUTAL && (child = getastchild(node, 0))->type == Synthesised) {
        /* 
            20230523 in Portugal 
            As an adjective, the entity receiving the meaning of this adjective must be a noun. Therefore, if the adjective has a contextual meaning from a symbol, the sentence should result in a meaning of the contextual meaning of the adjective is equal to the meaning of the child, because both of them refer to the same entity
        */
       char *s = __combine_3_strings__(child->token->symbol, " == ", si->interpretation);
       __remove_all_children_cst__(node);
       __replace_si_at_parent__(node, Synthesised, s);
       free(s);
    } else {
        char *s = __obtain_si_from_subtree__(node, si);
        if (countastchildren(node) == 1) {
            child = getastchild(node, 0);
            struct cstsymbol *c = searchsymbolbyref(child);
            __remove_all_children_cst__(node);
            __update_cstsymbol_data__(c, s, si->jtype);
            syncsymbol(c);
            // TODO: check this. this is not thoroughly checked. if this ruins other cases, remove this line.
            c->si_ptr = si;
            c->g_arg_count = 0;
            //
            if (getavailablerefs(c) == 0 && node->parent->type == Quantifier) {
                /* a special case for the sentences that the predicate is a single adjective */
                __replace_si_at_parent__(node, Synthesised, s);
            } else {
                root = deleteastnodeandedge(node, root);
            }
        } else {
            __remove_all_children_cst__(node);
            __replace_si_at_parent__(node, Synthesised, s);
        }
        free(s);
    }
    return 0;
}

int Vseries_code_synthesis(struct astnode *node, struct si *si) {
    struct astnode *child;
    /* Verbs usually accepts 2 arguments (subject, object). Thus, verbs cannot be resolved before both arguments are resolved. */
    for (int i = 0; i < countastchildren(node); ++i) {
        child = getastchild(node, i);
        if (child->type != Synthesised) {
            return 1;
        }
    }
    subtree_si_synthesis(node, si);
    return 0;
}


int Nseries_code_synthesis(struct astnode *node, struct si *si) {
    struct astnode *child = getastchild(node, 0);    
    char *s = (char*)strdup(si->interpretation);
    struct cstsymbol *c = searchsymbolbyref(child);   
    // __remove_all_children_cst__(node);          
    if (strcmp(si->args[0], "(*)") != 0) {
        /* argument does affect the semantic interpretation */
        /* therefore, the semantic interpretation of the subtree requires code synthesis */
        s = __obtain_si_from_subtree__(node, si);
    }
    __remove_all_children_cst__(node);    
    struct si *tmp = searchqueue(silist, s, __match_interpretation_and_get_type);
    if (tmp != NULL) {
        __update_cstsymbol_data__(c, s, tmp->jtype);
    } else {
        __update_cstsymbol_data__(c, s, si->jtype);
    }    
    syncsymbol(c);
    c->si_ptr = si;
    c->type = si->jtype;            
    root = deleteastnodeandedge(node, root);
    return 0;
}



int CC_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int CD_code_synthesis(struct astnode *node, struct si *si) { 
    si_alias(node, si);
    return 0;
}
int DT_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int EX_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int FW_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int IN_code_synthesis(struct astnode *node, struct si *si) {      
    // if (countastchildren(node) == 1) {
    //     fprintf(stderr, "single argument preposition is not supported.\n");
    //     exit(-5);
    // }
    // a special case for root with preposition predicate
    // if (node->isroot == 1) {
    //     return __synthesis_predicate_at_root__(si);
    // } else 

    if (getastchild(node, 0)->type == MultipleSIs && getastchild(node, 1)->type == Synthesised) {
        struct astnode *sinode = getastchild(node, 0), *datanode = getastchild(node, 1);
        struct si *si = searchqueue(sinode->si_q, datanode, __preposition_argtype_simatcher);
        if (si == NULL) {
            fprintf(stderr, "No SI matched for predicate(%s) with syntax (IN)\n", node->token->symbol);
            exit(-100);
        }
        si_substitution(node, sinode, datanode, si);
    } else {
        struct astnode *x = getastchild(node, 0), *y = getastchild(node, 1);
        if (strcmp(si->interpretation, "\\sub(x)2(y)") == 0) {
            sibling_si_synthesis(node, y, x);
        } else if (strcmp(si->interpretation, "\\sub(y)2(x)") == 0) {
            sibling_si_synthesis(node, x, y);
        } else {
            subtree_si_synthesis(node, si);
        }
    }
    return 0;
}
int JJ_code_synthesis(struct astnode *node, struct si *si) { return Jseries_code_synthesis(node, si); }
int JJR_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int JJS_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int LS_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int MD_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int NN_code_synthesis(struct astnode *node, struct si *si) { return Nseries_code_synthesis(node, si); }
int NNS_code_synthesis(struct astnode *node, struct si *si) { return Nseries_code_synthesis(node, si); }
int NNP_code_synthesis(struct astnode *node, struct si *si) { return Nseries_code_synthesis(node, si); }
int NNPS_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int PDT_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int POS_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int PRP_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int PRP_POS_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int RB_code_synthesis(struct astnode *node, struct si *si) {
    // if (node->isroot == 1) {
    //     return __synthesis_predicate_at_root__(si);
    // } else {
    //     for (int i = 0; i < countastchildren(node); ++i) {
    //         if (getastchild(node, i)->type != Synthesised) return 1;
    //     }
    //     subtree_si_synthesis(node, si);
    //     return 0;
    // }
    // for (int i = 0; i < countastchildren(node); ++i) {
    //     if (getastchild(node, i)->type != Synthesised) return 1;
    // }
    subtree_si_synthesis(node, si);
    return 0;
}
int RBR_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int RBS_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int RP_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int SYM_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int TO_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int UH_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int VB_code_synthesis(struct astnode *node, struct si *si) { 
    return Vseries_code_synthesis(node, si); 
}
int VBD_code_synthesis(struct astnode *node, struct si *si) {  
    return Vseries_code_synthesis(node, si);
}
int VBG_code_synthesis(struct astnode *node, struct si *si) { 
    return Vseries_code_synthesis(node, si);
}
int VBN_code_synthesis(struct astnode *node, struct si *si) { 
    return Vseries_code_synthesis(node, si);
}
int VBP_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int VBZ_code_synthesis(struct astnode *node, struct si *si) {
    return Vseries_code_synthesis(node, si);
}
int WDT_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int WP_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int WP_POS_code_synthesis(struct astnode *node, struct si *si) { return 0; }
int WRB_code_synthesis(struct astnode *node, struct si *si) { return 0; }

int event_synthesis(struct astnode *node, struct si *si) {
    struct event *e = searchevent(getastchild(node, 0)->token->symbol);
    char *s = NULL;
    struct entity *en = NULL;
    if (si->interpretation[0] == '_') {
        struct cstsymbol *subj_ptr, *acc_ptr;
        en = (struct entity*)gqueue((void*)e->entities, 0);
        if (en->type == SubjectOf) {
            subj_ptr = en->ptr;
            acc_ptr = (struct cstsymbol*)((struct entity*)gqueue((void*)e->entities, 1))->ptr;
        } else {
            acc_ptr = en->ptr;
            subj_ptr = (struct cstsymbol*)((struct entity*)gqueue((void*)e->entities, 1))->ptr;
        }
        /* keywords. the synthesis process is up to what the keywords have specified. */
        if (strcmp(si->interpretation, "_sub(Subj)2(Acc)") == 0) {
            /* 
                The synthesised SI of the entity with Subj is substituted to that of the entity with Acc.
                The synthesised SI of the Acc entity must have the type of JML_expression_template.
                After the substitution, both the Subj and Acc entity cst pointer's data field has the synthesised SI.
            */
            struct si *_si = (struct si *)acc_ptr->si_ptr;
            s = strrep(_si->interpretation, _si->args[0], subj_ptr->data); 
            acc_ptr->type = si->jtype;                 
        } else if (strcmp(si->interpretation, "_event_sub(Subj)2(Acc)") == 0) {
            /*
                This case is substituting the SI based on the event arguments. 
                The previous case is for substitution based on the accusation's arguments
            */
            // notice here we use (Subj) directly, because we follow the command directly
            s = strrep(acc_ptr->data, "(Subj)", subj_ptr->data); 
            acc_ptr->type = si->jtype;     
        }
    } else {
        s = (char *)strdup(si->interpretation);
        for (int i = 0; i < e->entities->count; ++i) {
            /* 
                get the compile symbol table pointer, and the get the data.
                the argument of SI is named using the grammar relationship string, such as Subj
                substitution is done replacing the SI synthesised in the data field of CST symbol to the grammar relationship string
            */
            en = (struct entity*)gqueue((void*)e->entities, i);
            /* 
                    because the entity variables should have been synthesised before coming to this point. 
                    we should not be able to search the symbol by the reference as the references are removed before synthesising the SI.
                    therefore, we should search the symbol by the symbol name.
                    there is no risk by using the symbol name because all symbol names are made unique.
            */
            struct cstsymbol *c = searchcst(en->var);
            char *arg = __combine_3_strings__("(", gramtype2string(en->type), ")");
            char *tmp = strrep(s, arg, c->data);
            free(arg);
            free(s);
            s = tmp;
            c->type = si->jtype;
        }
    }
    /* 
    s contains the synthesised SI 
    */
   __remove_all_children_cst__(node);
    __replace_si_at_parent__(node, Synthesised, s);
    return 0;
}

int Gram_Rel_synthesis(struct astnode *node, struct si *si) {
    // sibling_si_synthesis
    // for (int i = 0; i < countastchildren(node); ++i) {
    //     if (getastchild(node, i)->type != Synthesised) return 1;
    // }
    struct astnode *sinode = getastchild(node, 0), *y = getastchild(node, 1);
    struct cstsymbol *c = searchsymbolbyref(sinode);
    struct si *_si = (struct si *)c->si_ptr;
    if (_si->g_arg_count == 0) {
        printf("Error: Predicate(%s) must have grammar arguments defined\n", _si->symbol);
        exit(-14);
    }
    char *s = strrep(sinode->token->symbol, _si->g_args[0], y->token->symbol);
    c = updatecstsymbol(s, sinode);      
    __remove_all_children_cst__(node);
    syncsymbol(c);        
    root = deleteastnodeandedge(node, root);
    c->g_arg_count++;
    return 0;
}

/* Reserve for future usage */
int Gram_Prog_synthesis(struct astnode *node, struct si *si) {
    return 0;
}

int (*code_syntheses[])(struct astnode *, struct si *) = {CC_code_synthesis, CD_code_synthesis, DT_code_synthesis, EX_code_synthesis, FW_code_synthesis, IN_code_synthesis, JJ_code_synthesis, JJR_code_synthesis, JJS_code_synthesis, LS_code_synthesis, MD_code_synthesis, NN_code_synthesis, NNS_code_synthesis, NNP_code_synthesis, NNPS_code_synthesis, PDT_code_synthesis, POS_code_synthesis, PRP_code_synthesis, PRP_POS_code_synthesis, RB_code_synthesis, RBR_code_synthesis, RBS_code_synthesis, RP_code_synthesis, SYM_code_synthesis, TO_code_synthesis, UH_code_synthesis, VB_code_synthesis, VBD_code_synthesis, VBG_code_synthesis, VBN_code_synthesis, VBP_code_synthesis, VBZ_code_synthesis, WDT_code_synthesis, WP_code_synthesis, WP_POS_code_synthesis, WRB_code_synthesis, Gram_Prog_synthesis, Gram_Rel_synthesis};


struct si* __generate_runtime_si__(struct astnode *node) {
    struct si *dup = searchqueue(silist, node->token->symbol, __sisymbol_duplicated);
    if (dup == NULL) {
        struct si *new = (struct si*) malloc (sizeof(struct si));
        new->symbol = (char*)strdup(node->token->symbol);
        new->g_arg_count = 0;
        new->arg_count = 1;
        new->args = (char**)malloc(sizeof(char*) * new->arg_count);
        new->args[0] = (char*)malloc(sizeof(char) * 4);
        memcpy(new->args[0], "(*)", 4);
        new->interpretation = (char*)strdup(node->token->symbol);
        new->syntax_count = 1;
        new->syntax = (enum ptbsyntax*) malloc (sizeof(enum ptbsyntax));
        new->syntax[0] = node->syntax;
        enqueue(silist, (void*)new);
        return new;
    } else {
        return dup;
    }
}

/* 
    20230522 in Portugal
    Words used as meaning like nouns but not tagged as nouns
    For instance, A is true, where true is tagged as adjective(JJ)
    This case, this true has direct semantics as a literal.
    Another point is that, the si must be a symbol from the contextual information
*/
int __is_nonnounsyntax_using_as_noun__(struct astnode *node, struct si *si) {
    if (countastchildren(node) == 1 && si->source == CONTEXTUTAL)
        return 0;
    else 
        return 1;
}

/* only the noun series (NN, NNS, NNP, NNPS) and cardinal number (CD) can provide direct semantics */
int __is_direct_semantics__(enum ptbsyntax syntax) {
    if (syntax == NN || 
            syntax == NNS || 
            syntax == NNP ||
            syntax == NNPS || 
            syntax == CD
            ) 
        return 0;
    else 
        return 1;
}

/* because the event predicate has only one child. and such event requires multiple synthesised predicates to finish the synthesis. therefore, it is a special case to the current practice of si matching, which is designed for usual higher-order logic. */
int __is_event_predicate__(struct astnode *node) {
    if (countastchildren(node) > 1) {
        return 1;
    } else {
        struct astnode *child = getastchild(node, 0);
        if (child->type != Variable || child->token->symbol[0] != 'e') {
            return 1;
        } else {
            return 0;
        }
    }
}

// /*
//     this function is check if the event predicate is useless.
//     the case is that, if the entities relevant to the event are not going to be synthesised, this event predicate is useless because it will not have any synthesised entities to synthesise with.
// */
// int __check_useless_event__(struct astnode *node) {

// }

void sianalysis() {
    struct astnode *node;
    // struct si *si;
    struct queue *si_q;
    #if SIDEBUG
    printf("si analysis: there are %d predicates in the queue.\n", predicates->count);
    #endif
    /* 
        sorting the predicates according to the semantic interpretation found 
        1st priority: all predicates with 1-argument si, and the argument is '*'. these predicates can be synthesised without any affects from other predicates
        2nd priority: all predicates with 1-argument si, and the argument is not '*'
        3rd priority: all other predicates which syntax are not preposition (IN).
        4th priority: predicates which syntax are prepositions. IN predicates require two arguments which are both synthesised, otherwise the code synthesis will be very difficult to compose, such as, Template in synthesis, variable in synthesis, variable in synthesis to template, etc.
    */
    struct queue *tmp = initqueue(), *two_args_preds = initqueue(), *in_preds = initqueue();
    while (!isempty(predicates)) {
        node = (struct astnode*)dequeue(predicates);
        // reset the si pointer for checking a node dequeue from the predicates queue
        si_q = NULL;
        if (selfSI[node->syntax] == 0) {
            // generate runtime SI
            node->si_q = initqueue();
            enqueue(node->si_q, (void*)__generate_runtime_si__(node));
            push(tmp, node);
        } else if (__is_event_predicate__(node) == 0) { 
            si_q = q_searchqueue(silist, node, __eventsimatcher);
            if (si_q == NULL || si_q->count == 0) {
                /* 
                    there can be a possibility that all the relevant entities are not going to be synthesised. in another words, the relevant entities do not have predicates matched with SI. if this is the case, this event is useless.
                */
                fprintf(stderr, "Symbol error(si analysis): Please provide the SI for predicate(%s)\n", node->token->symbol);
            } else {
                node->si_q = si_q;
                enqueue(two_args_preds, node);
            }
        } else {
            si_q = q_searchqueue(silist, node, __simatcher);
            if (si_q == NULL || si_q->count == 0) {
                if (node->isroot) {
                    fprintf(stderr, "Symbol error(si analysis): Please provide the SI for predicate(%s)\n", node->token->symbol);
                    exit(-10);
                }
                __remove_all_children_cst__(node);
                if (node->parent->type != Connective) {
                    fprintf(stderr, "Symbol error(si analysis): Please provide the SI for predicate(%s)\n", node->token->symbol);
                    exit(-10);
                } else if (
                    node->parent->conntype != Op_And &&
                    node->parent->conntype != Op_Or) {
                    fprintf(stderr, "Symbol error(si analysis): Please provide the SI for predicate(%s)\n", node->token->symbol);
                    exit(-11);                
                } else {
                    root = deleteastnodeandedge(node, root);
                }
                continue;
            } else { 
                node->si_q = si_q;
                if (node->syntax == IN) {
                    enqueue(in_preds, node);
                } else if (node->syntax == NN || node->syntax >= Gram_Prog) {
                    if (si_q->count == 1 && strcmp(((struct si*)gqueue(si_q, 0))->args[0], "*") == 0) {
                        push(tmp, node);
                    } else {
                        enqueue(tmp, node);
                    }
                } else {
                    enqueue(two_args_preds, node);
                }
            }
        }
    }
    while (!isempty(two_args_preds)) {
        enqueue(tmp, dequeue(two_args_preds));
    }
    while (!isempty(in_preds)) {
        enqueue(tmp, dequeue(in_preds));
    }
    deallocatequeue(two_args_preds, NULL);
    deallocatequeue(predicates, NULL);
    deallocatequeue(in_preds, NULL);
    predicates = tmp;
}

/* 
    semantic interpretation synthesis 
    this is a process to identify as many si as possible presenting in the meaning representation
    - parameter descriptions
        predicates  : a queue holding pointers of predicates present in an abstract syntax tree parsed from meaning representation
        silist      : a queue holding semantic interpretations parsed from standard semantic interpretation database
        cst         : a queue holding the compile time symbols, aka the identitiers in the meaning representation
*/
void sisynthesis() {
    struct astnode *node;
    struct si *si;
    #if SIDEBUG
    printf("si synthesis: after sorting, there are %d predicates in the queue.\n", predicates->count);
    for (int i = 0; i < predicates->count; ++i) {
        node = (struct astnode*)gqueue(predicates, i);
        printf("%d. %s(%s) %d\n", i + 1, node->token->symbol, ptbsyntax2string(node->syntax), node->si_q->count);
    }
    #endif

    struct queue *si_q;
    int count = predicates->count;
    while (!isempty(predicates)) {    
        node = (struct astnode*)dequeue(predicates);
        #if SIDEBUG
        printf("si synthesis: processing predicate %s(%s) with %d SIs available.\n", node->token->symbol, ptbsyntax2string(node->syntax), node->si_q->count);
        #endif

        si_q = node->si_q;
        si = NULL;
        if (si_q->count == 0) si = NULL;
        else if (si_q->count == 1) si = gqueue(si_q, 0);
        else if (__is_event_predicate__(node) == 0) {
            /* TODO: we have to add a condition specific for the event predicates
                check the children type matching the SI's argument type specification
            */
            si = searchqueue(si_q, node, __event_argtype_simatcher);
        } 
        else {
            // because there are more than one possible si
            // in order to get the most appropriate one, we filter the queue to get the one that has arg_types matching the current child node types                
            si = searchqueue(si_q, node, __argtype_simatcher);                                
        }                 
        if (si_q->count > 0 && countastchildren(node) == 1 && si == NULL) {
            /* This is a case that the SIs in the si_q require non-asterisk argument */
            /* Therefore, we alias all variable nodes related to this predicate's child with the predicate itself */
            si_alias(node, NULL);
        } else if (si == NULL) {
            #if SIDEBUG
            printf("si synthesis: no exact matched si for predicate %s(%s) is found. but there are %d SIs in the queue\n", node->token->symbol, ptbsyntax2string(node->syntax), node->si_q->count);
            #endif
            if (count == 0) {
                fprintf(stderr, "SI is not found after all other direct predicates are tried.\n");
                exit(-100);
            } else {
                --count;
                enqueue(predicates, (void*)node);
            }
        } else {
            /* An SI is matched. Synthesis can be performed. */
            int x = 1;
            if (node->isroot == 1) {
                x = __synthesis_predicate_at_root__(si);
            } else if (node->syntax == IN) {
                x = IN_code_synthesis(node, si);
            } else if (__is_event_predicate__(node) == 0) {
                x = event_synthesis(node, si);
            } else {
                if (__is_direct_semantics__(node->syntax) != 0 &&
                __is_nonnounsyntax_using_as_noun__(node, si) != 0) {
                    for (int i = 0; i < countastchildren(node); ++i) {
                        if (getastchild(node, i)->type != Synthesised) {
                            x = 1;
                            goto CHECK;
                        }
                    }
                }
                // TODO: check keywords (e.g. \sub) here
                if (countastchildren(node) == 2 && si->interpretation[0] != '\\') {
                    subtree_si_synthesis(node, si);
                    x = 0;
                } else if (__is_nonnounsyntax_using_as_noun__(node, si) == 0 &&
                    getastchild(node, 0)->type == Variable &&
                    searchcst(getastchild(node, 0)->token->symbol)->refs->count == 1) {
                    /*
                        20230523 added in Portugal
                        For predicates that having contextual symbols, their syntax can be ignored (such that we allow the NLP errors) if the argument entity variables are ONLY being accepted by these predicates. Such that, a predicate is the only acceptor of an entity variable within the formula.
                    */
                    x = (*code_syntheses[NN])(node, si);
                } else {
                    x = (*code_syntheses[node->syntax])(node, si);
                }
            }

            CHECK:
            if (x != 0) {
                #if SIDEBUG
                printf("si synthesis: predicate %s(%s) code synthesis is not done in this loop.\n", node->token->symbol, ptbsyntax2string(node->syntax));
                #endif
                enqueue(predicates, (void*)node);
            } else {
                count = predicates->count;
            }
        }          
        #if ASTDEBUG
        showast(root, 0);
        showqueue(cst, showcstsymbol);
        fflush(stdout);
        #endif
    }
    #if SIDEBUG
    printf("si synthesis finished\n");
    #endif
}

void opresolution() {
    struct astnode *node, *left, *right;    
    while (operators->count > 0) {
        node = (struct astnode*)dequeue(operators);
        left = getastchild(node, 0);
        right = getastchild(node, 1);
        char *s = NULL;

        if (left->type == Synthesised && right->type == Synthesised) {
            /* there can be one of the synthesised node has unresolved grammar argument */
            struct cstsymbol *cleft = searchsymbolbyref(left), *cright = searchsymbolbyref(right);
            if (cleft->g_arg_count < ((struct si*)cleft->si_ptr)->g_arg_count && cright->g_arg_count < ((struct si*)cright->si_ptr)->g_arg_count) {
                fprintf(stderr, "The predicates' grammar arguments are not resolved\n");
                exit(-15);
            } else if (cleft->g_arg_count == ((struct si*)cleft->si_ptr)->g_arg_count && cright->g_arg_count == ((struct si*)cright->si_ptr)->g_arg_count) {
                /* both ast node semantic interpretations have been synthesised */
                /* therefore, the operator serves as a comparator */
                s = __combine_3_strings__(left->token->symbol, "==", right->token->symbol);
            } else {
                struct astnode *template, *sub;
                struct cstsymbol *ctemplate;
                if (cleft->g_arg_count < ((struct si*)cleft->si_ptr)->g_arg_count) {
                    template = left;
                    sub = right;
                    ctemplate = cleft;
                } else {
                    template = right;
                    sub = left;
                    ctemplate = cright;
                }
                struct si *_si_ptr = ctemplate->si_ptr;
                char *tmp = __combine_3_strings__("(", _si_ptr->g_args[ctemplate->g_arg_count], ")");
                s = strrep(template->token->symbol, tmp, sub->token->symbol);
                free(tmp);
                ctemplate->g_arg_count++;
                ctemplate = updatecstsymbol(s, template);      
                __remove_all_children_cst__(node);
                syncsymbol(ctemplate);                      
            }
        } else {
            /* if node A has node type == Template, the symbol of node A is aliased as the symbol of node B */
            /* such that, semantic interpretation is synthesised from substituting the semantic interpretation of node B into the template in node A */
            if (left->type == Template && right->type == Synthesised) {
                s = __subsititute_synthesised_into_template__(right, left);
            } else if (left->type == Synthesised && right->type == Template) {                
                s = __subsititute_synthesised_into_template__(left, right);
            } else {
                /* missing semantic intrepretations. code synthesis failed */
                return;
            }
        }
        __replace_si_at_parent__(node, Synthesised, s);
        if (s) {
            free(s);
        }
    }    
}

int __sisymbol_duplicated(void *_si, void *_symbol) {
    char *symbol = (char*)_symbol;
    struct si* si = (struct si*)_si;
    if (strcmp(symbol, si->symbol) == 0) return 0;
    else return 1;
}



int __simatcher(void *_si, void *_astnode) {
    struct si* si = (struct si*)_si;
    struct astnode *node = (struct astnode*)_astnode;
    // if (strcmp(node->token->symbol, si->symbol) == 0 &&
    //             search_syntax(si, node->syntax) == 0 && 
    //             si->arg_count == countastchildren(node)) 
    /* 20230522 in portugal. 
        We want to tackle a problem of using a word tagged as not-a-noun at the end of a sentence, or an declarative sentence, where this word should be a symbol.
        Adding a condition that if there is only one child, it is okay to skip the search_syntax. Because this is to say that this symbol must be a symbol in the contextual information. 
        This can be wrong if there is an SI in the SI library conflicting with the contextual information. 
        TODO: we can put a flag in the SI to distinguish between contextual SI and std SI, such that this will not be a problem.
    */
    int n_child = countastchildren(node);
    if (strcmp(node->token->symbol, si->symbol) == 0 &&
                (__is_nonnounsyntax_using_as_noun__(node, si) == 0 || search_syntax(si, node->syntax) == 0) && 
                si->arg_count == n_child) 
        return 0;
    else
        return 1;
}

/* 
    this is an SI matcher that is specifically for the event predicates.
    event predicates have only one child that is the event variable, which is not useful in the SI matching with SIs should have specified the number of arguments required for synthesis.
    however, we have recorded the entities that have grammar relationships with this event variable.
    such that, we can check the number of arguments required for the SI against the number of entities of the event variables.
*/
int __eventsimatcher(void *_si, void *_astnode) {
    struct si* si = (struct si*)_si;
    struct astnode *node = (struct astnode*)_astnode, *child = getastchild(node, 0);
    struct event *event = searchevent(child->token->symbol);
    if (strcmp(si->symbol, node->token->symbol) == 0 &&
        search_syntax(si, node->syntax) == 0 &&
        si->arg_count == event->entities->count
        ) 
    {
        return 0;
    } else {
        return 1;
    }
}

int __event_argtype_simatcher(void *_si, void *_astnode) {
    struct si* si = (struct si*)_si;
    struct astnode *node = (struct astnode*)_astnode;
    struct cstsymbol *c = NULL;
    struct event *event = searchevent(getastchild(node, 0)->token->symbol);
    for (int i = 0; i < event->entities->count; ++i) {
        c = ((struct entity*)gqueue(event->entities, i))->ptr;
        if (si->arg_types[i] != -1 && si->arg_types[i] != c->type) return 1;
        else continue;
    }
    return 0;
}

int __argtype_simatcher(void *_si, void *_astnode) {
    struct si* si = (struct si*)_si;
    struct astnode *node = (struct astnode*)_astnode, *tmp;
    struct cstsymbol *c = NULL;
    for (int i = 0; i < si->arg_count; ++i) {
        tmp = getastchild(node, i);
        c = searchsymbolbyref(tmp);
        if (si->arg_types[i] != -1 && si->arg_types[i] != c->type) return 1;
        else continue;
    }
    return 0;
}

int __preposition_argtype_simatcher(void *_si, void *_datanode) {
    struct si* si = (struct si*)_si;
    struct astnode *node = (struct astnode*)_datanode;
    if (si->arg_count == 1) {
        struct cstsymbol *c = searchsymbolbyref(node);    
        if (si->arg_types[0] == c->type) return 0;
    }
    return 1;
}

int __match_interpretation_and_get_type(void *_si, void *_s) {
    struct si* si = (struct si*)_si;
    char *s = (char*)_s;
    if (strcmp(si->symbol, s) == 0) return 0;
    else return 1;
}

int search_syntax(struct si* si, enum ptbsyntax ptb) {
    for (int i = 0; i < si->syntax_count; ++i) {
        if (si->syntax[i] == ptb) {
            return 0;
        }
    }
    return 1;
}

void showsi(void *_si) {
    struct si *si = (struct si*)_si;
    printf("==========================Semantic interpretations: =========================\n");
    printf("Symbol          Syntactic Category       Arity     Arguments    Interpretation\n");
    printf("Symbol: %s   Syntactic Category: ", si->symbol);        
    for (int j = 0; j < si->syntax_count; ++j) {
        printf("%s ", ptbsyntax2string(si->syntax[j]));
    }
    printf("  Arity: %d   Arguments: ", si->arg_count);
    for (int j = 0; j < si->arg_count; ++j) {
        printf("%s ", si->args[j]);
    }
    printf("   %s(%p)\n", si->interpretation, (void*)si->interpretation);
    printf("   Grammar_arity: %d   Grammar_arguments: ", si->g_arg_count);
    for (int j = 0; j < si->g_arg_count; ++j) {
        printf("%s ", si->g_args[j]);
    }
    printf("============================================================================\n");
}

void deallocatesi(void *tmp) {    
    struct si *si = (struct si*)tmp;
    for (int i = 0; i < si->arg_count; ++i) {
        free(si->args[i]);
    }
    for (int i = 0; i < si->g_arg_count; ++i) {
        if (si->g_args[i])
            free(si->g_args[i]);
    }
    free(si->syntax);
    if (si->interpretation)
        free(si->interpretation);
    free(si->symbol);
    free(si);
}

