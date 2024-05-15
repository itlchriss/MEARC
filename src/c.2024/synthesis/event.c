#include <stdarg.h>
#include <string.h>
#include <stdlib.h>

#include "util.h"
#include "ast.h"
#include "event.h"
#include "error.h"
#include "sshare.h"
#include "synthesis.h"


extern struct astnode *root;
extern struct queue *predicates, *operators, *silist, *events, *alias;
extern struct astnode *root;


char *gram_string[] = { "Subj", "Acc", "AccI", "AccE", "Dat", "Gen", "Abl", "Rel", "Voc" };
char *gram_name[] = { "SubjectOf", "AccusationOf", "IntentionalAccusationOf", "ExtentionalAccusationOf", "Dative", "Genitive", "Ablative", "Relative", "Vocative" };

char *gramtype2string(enum gramtype type) {
    return gram_string[type];
}

char *__do_lazy_resolve__(char *s, struct entity *en) {
    int occur[strlen(s)/7 + 1];
    if (strsearch(s, "__REF__type", occur) != 0) {
        char *tmp = strrep(s, "(__REF__type)", (char *)gqueue(en->cstptr->datatype->types, 0));
        free(s);
        return tmp;
    } else {
        return s;
    }
}

struct queue *__match_event_si__(struct queue *siq, int count, ...) {
    va_list valist;

    struct queue *result = initqueue();    
    for (int i = 0; i < siq->count; ++i) {
        struct si *si = (struct si *)gqueue(siq, i);        
        va_start(valist, count);    
        int match = TRUE;
        for (int j = 0; j < count; ++j) {
            struct si_arg *arg = (struct si_arg *)gqueue(si->args, j);
            struct datatype *datatype = (struct datatype *)va_arg(valist, void *);
            if (!__compare_datatype__(arg->datatype, datatype)) { 
                match = FALSE; 
                break; 
            }
        }        
        va_end(valist);
        if (match) enqueue(result, (void *)si);
    }    
    return result;
}

/*
    combinatorially forming all possible SI synthesis for 2 event entities
    the reason why it is a combinatorial problem is that,
    1. there are 2 event entities, and each of them may have multiple SIs
        i. let x and y be the number of SIs for the 2 event entities
    2. there can be multiple SIs, let the number of SIs be n
    3. therefore, at most we have  x * y * n SIs
*/
struct queue *__2_event_entities_combinatorial_subtree_si_synthesis__(struct event *event, struct queue *siq) {
    struct queue *result = initqueue();
    struct entity *en1 = (struct entity *)gqueue(event->entities, 0), *en2 = (struct entity *)gqueue(event->entities, 1);
    char *t1 = __combine_3_strings__("(", gramtype2string(en1->type), ")"), *t2 = __combine_3_strings__("(", gramtype2string(en2->type), ")");
    for (int i = 0; i < siq->count; ++i) {
        struct si *si = (struct si *)gqueue(siq, i);        
        if (en1->cstptr->datatype->i == INT_SI_TYPE_MULTIPLE_SI || en2->cstptr->datatype->i == INT_SI_TYPE_MULTIPLE_SI) {
            // 1. if both entities are multiple SI... then we don't support it in the current version:)
            // 2. synthesise the one that is not multiple SI first
            // 3. do the multiple SI one by one by checking the datatype to decide the operator
            //  and in the current version, we do not support the combinatorial synthesis here
            char *tmp = NULL, *d = NULL, *s = (char *)strdup(si->interpretation), *targettag = NULL;
            struct cstsymbol *msptr = NULL;
            if (en1->cstptr->datatype->i != INT_SI_TYPE_MULTIPLE_SI) {
                d = (char *)gqueue(en1->cstptr->datalist, 0);
                tmp = strrep(s, t1, d);
                msptr = en2->cstptr;
                targettag = t2;
            } else {
                d = (char *)gqueue(en2->cstptr->datalist, 0);
                tmp = strrep(s, t2, d);
                msptr = en1->cstptr;
                targettag = t1;
            }
            free(s);
            s = tmp;

            char *_s = (char *)strdup(tmp);
            char *token, *last, *pos;
            last = token = strtok_r(_s, ";", &pos);
            for (;(token = strtok_r(NULL, ";", &pos)) != NULL; last = token);
            free(_s);
            token = strdup(last);            
            tmp[strlen(tmp) - strlen(token)] = '\0';
            // free(last);
            last = token;

            char *complex = NULL;
            for (int j = 0; j < msptr->datalist->count; ++j) {
                d = (char *)gqueue(msptr->datalist, j);
                // TODO: we support primitive type using == and interpretation type as java_method using directly substitution here
                //          we can support reference type in the future
                struct datatype *dt = (struct datatype *)gqueue(msptr->datatype->multiple_datatypes, j);
                if (dt->i == INT_SI_TYPE_JAVA_METHOD) {
                    _s = strrep(last, targettag, d);
                } else if (dt->p != UNDEFINED) {
                    // TODO: notice that we have not implmented the type checking here. it is to be implemented
                    char *_d = (char *)strdup(d);
                    append(_d, (char *)strdup(" == "));
                    _s = strrep(last, targettag, _d);
                    free(_d);
                } else {
                    // TODO: the space for reference type
                    internal_error("Current version does not support multiple SI with reference type");
                }

                if (!complex) {
                    d = combine_strings(3, _s, " ", (char *)gqueue(msptr->conjunction_operators, j));
                } else if (j != msptr->datalist->count - 1) {
                    d = combine_strings(5, complex, " ", _s, " ", (char *)gqueue(msptr->conjunction_operators, j));
                } else {
                    d = combine_strings(3, complex, " ", _s);
                }
                complex = d;
                free(_s);
            }
            d = combine_strings(2, tmp, complex);
            free(complex);
            free(tmp);
            tmp = d;
            enqueue(result, (void *)tmp);
            ///////////////////////////////////////////////////////////////////////////////////////////////////////            
        } else {
            // single SI combinatorial synthesis
            for (int j = 0; j < en1->cstptr->datalist->count; ++j) {
                char *d1 = (char *)gqueue(en1->cstptr->datalist, j);
                for (int k = 0; k < en2->cstptr->datalist->count; ++k) {
                    char *d2 = (char *)gqueue(en2->cstptr->datalist, k), *s = (char *)strdup(si->interpretation);                    
                    char *tmp = strrep(s, t1, d1);
                    free(s);
                    s = tmp;
                    if (en2->cstptr->datatype->i >= 50 && si->spec_init_type != AnyPrimitiveType) {
                        char *_d2 = __combine_3_strings__(d2, " ==", " ");
                        tmp = strrep(s, t2, _d2);
                        free(_d2);
                    } else if (en2->cstptr->datatype->i == INT_SI_TYPE_JAVA_METHOD_CHAIN) {
                        /* 
                            get the last part of the interpretation
                            Currently support:
                                1. quantify expression
                                2. simple expression with only one substitution
                        */
                        char *_s = (char *)strdup(tmp);
                        char *token, *last, *pos;
                        last = token = strtok_r(_s, ";", &pos);
                        for (;(token = strtok_r(NULL, ";", &pos)) != NULL; last = token);
                        free(_s);
                        token = strdup(last);
                        tmp[strlen(tmp) - strlen(token)] = '\0';
                        struct queue *inter_list = __get_java_method_interpretations_from_chain__(d2);
                        // TODO: we need to support a full pre order expression
                        char *inter_tmp = __combine_3_strings__(                            
                                strrep(token, t2, gqueue(inter_list, 1)),                            
                                gqueue(inter_list, 0),
                                strrep(token, t2, gqueue(inter_list, 2))
                            );                    
                        char *buf = __combine_3_strings__(tmp, inter_tmp, " ");
                        deallocatequeue(inter_list, deallocatedata);
                        tmp = buf;
                    } else {
                        tmp = strrep(s, t2, d2);
                    }
                    free(s);
                    enqueue(result, (void *)tmp);
                }            
            }
        } // single SI combinatorial synthesis
    }
    free(t1);
    free(t2);
    return result;
}

/*
    combinatorially forming all possible SI synthesis for 1 event entity
    the reason why it is a combinatorial problem is that,
    1. there are 1 event entity, and it may have multiple SIs
        i. let x be the number of SIs for the 2 event entities
    2. there can be multiple SIs, let the number of SIs be n
    3. therefore, at most we have  x * n SIs
*/
struct queue *__1_event_entities_combinatorial_subtree_si_synthesis__(struct event *event, struct queue *siq) {
    struct queue *result = initqueue();
    struct entity *en1 = (struct entity *)gqueue(event->entities, 0);
    for (int i = 0; i < siq->count; ++i) {
        struct si *si = (struct si *)gqueue(siq, i);   
        if (strcmp(((struct si_arg *)gqueue(si->args, 0))->symbol, "*") == 0) {
            deallocatequeue(en1->cstptr->datalist, deallocatedata);
            en1->cstptr->datalist = initqueue();
            char *tmp = strdup(si->interpretation);
            enqueue(en1->cstptr->datalist, (void *)tmp);
            result = NULL;
        } else {
            char *t1 = __combine_3_strings__(
                "(", 
                ((struct si_arg *)gqueue(si->args, 0))->symbol, 
                ")");      
        
            for (int j = 0; j < en1->cstptr->datalist->count; ++j) {
                char *d1 = (char *)gqueue(en1->cstptr->datalist, j), *s = (char *)strdup(si->interpretation);         
                char *tmp;
                tmp = strrep(s, t1, d1);
                free(s);
                tmp = __do_lazy_resolve__(tmp, en1);
                enqueue(result, (void *)tmp);       
            }
            free(t1);
        }
    }
    return result;
}


int event_synthesis(struct astnode *node) {
    struct event *e = __searchevent(getastchild(node, 0)->cstptr);
    struct queue *siq = NULL;
    struct queue * (*funcptr)(struct event *, struct queue *);
    e->cstptr->astptr = node;
    struct entity *en1 = NULL, *en2 = NULL;
    
    if (e->entities->count == 1) {
        /* cases that predicates only have Subj */
        en1 = (struct entity *)gqueue(e->entities, 0);
        if (en1->cstptr->datatype->relative_datatype != NULL) {
            siq = __match_event_si__(node->si_q, 1, en1->cstptr->datatype->relative_datatype);
        } else {
            siq = __match_event_si__(node->si_q, 1, en1->cstptr->datatype);
        }

        funcptr = &__1_event_entities_combinatorial_subtree_si_synthesis__;
    } else {
        /* cases that predicates have two components */        
        en1 = (struct entity *)gqueue(e->entities, 0), en2 = (struct entity *)gqueue(e->entities, 1);
        // // NOTE: to be removed later. we should make use of the interpretation_type to do this checking
        // if (en1->cstptr->datatype->relative_datatype != NULL) {
        //     siq = __match_event_si__(node->si_q, 2, en1->cstptr->datatype->relative_datatype, en2->cstptr->datatype);
        // } else if (en1->cstptr->datatype->relative_datatype != NULL) { 
        //     siq = __match_event_si__(node->si_q, 2, en1->cstptr->datatype, en2->cstptr->datatype->relative_datatype);
        // } else {
        //     siq = __match_event_si__(node->si_q, 2, en1->cstptr->datatype, en2->cstptr->datatype);
        // }
        siq = __match_event_si__(node->si_q, 2, en1->cstptr->datatype, en2->cstptr->datatype);
        /* combinatorially forming all possible SI synthesis from 2 entities */
        funcptr = &__2_event_entities_combinatorial_subtree_si_synthesis__;
    }    
    if (siq->count == 0) sinotfound_error(node->token->symbol);
    node->si_q = (*funcptr)(e, siq);
    if (datatype_is_specific(((struct si*)gqueue(siq, 0))->synthesised_datatype)) {
        // e->cstptr->datatype->p = en1->cstptr->datatype->p = ((struct si*)gqueue(siq, 0))->synthesised_datatype->p;
        // e->cstptr->datatype->r = en1->cstptr->datatype->r = ((struct si*)gqueue(siq, 0))->synthesised_datatype->r;
        e->cstptr->datatype->p = ((struct si*)gqueue(siq, 0))->synthesised_datatype->p;
        e->cstptr->datatype->r = ((struct si*)gqueue(siq, 0))->synthesised_datatype->r;             
    }

    for (int i = 0; i < e->entities->count; ++i) ((struct entity *)gqueue(e->entities, i))->cstptr->ref_count--;
    e->cstptr->datalist = initqueue();
    /* storing the synthesised intermediate SI into the event, such that the INT SI can be referenced in the later events if such event is accepted as argument */
    if (node->si_q != NULL) {
        for (int i = 0; i < node->si_q->count; ++i) enqueue(e->cstptr->datalist, gqueue(node->si_q, i));
    }
    // NOTE: to be removed later. we should make use of the interpretation_type to do this checking
    e->cstptr->interpretation_type = INT_SI_TYPE_EXPR;
    e->cstptr->ref_count--; 
    // NOTE: to be removed later. we should make use of the interpretation_type to do this checking
    if (en1->cstptr->datatype->relative_datatype) {
        free(en1->cstptr->datatype->relative_datatype);
        en1->cstptr->datatype->relative_datatype = NULL;
    }
    if (en2 && en2->cstptr->datatype->relative_datatype) {
        free(en2->cstptr->datatype->relative_datatype);
        en2->cstptr->datatype->relative_datatype = NULL;
    }
    /* the resulting operations */
    switch (node->syntax) {
        case JJ:
            JJ_event_synthesis_post_operation(node, siq);
            break;
        default:
            __post_operation_si_subtree_synthesis__(node);
            if (((struct si *)gqueue(siq, 0))->type == SI_INT_TYPE_MODIFIER) {
                struct entity *en1 = (struct entity *)gqueue(e->entities, 0);
                en1->cstptr->datalist = initqueue();
                for (int i = 0; i < node->si_q->count; ++i) {
                    enqueue(en1->cstptr->datalist, strdup(gqueue(node->si_q, i)));
                }
                root = deleteastnodeandedge(node, root);        
            } else if (node->si_q == NULL) {
                root = deleteastnodeandedge(node, root);
            } else {
                struct entity *en1 = (struct entity *)gqueue(e->entities, 0);
                en1->cstptr->interpretation_type = SI_INT_TYPE_EXPR;
            }
            /* since the SIs are only for this synthesis, it should have no effect on the overall SI list. we should deallocate ASAP */
            deallocatequeue(siq, NULL);
            break;
    }        
    return 0;
}