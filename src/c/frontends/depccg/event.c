#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <string.h>
#include "event.h"
#include "util.h"

enum gramtype __string2gramtype(char *input);

char *gram_name[] = { "SubjectOf", "AccusationOf", "IntentionalAccusationOf", "ExtentionalAccusationOf", "Dative", "Genitive", "Ablative", "Relative", "Vocative" };

int __eventptrcomparator(void *a, void *b) {
    if (a == b) return 0;
    else return 1;
}

/*
TODO: instead of alias, we should just replace the entity var with the event var. such that the synthesis will use the replaced var's SI
*/
void aliaseventsubject(struct queue *events, struct queue *cst, struct event *ptr) {
    struct event *ev = (struct event *)searchqueue_firstmatch(events, ptr, __eventptrcomparator);
    struct cstsymbol *ev_c = searchcst(cst, ev->var);
    struct entity *en = NULL, *tmp = NULL;
    for (int i = 0; i < ev->entities->count; ++i) {
        if ((en = (struct entity *)gqueue(ev->entities, i))->type == SubjectOf) {
            break;
        }
    }
    char *tmpvar = NULL;
    tmpvar = (char*)strdup(en->var);
    free(en->var);
    en->var = (char*)strdup(ev->var);
    en->ptr = ev_c;
    en->sitype = EXP;
    for (int i = 0; i < events->count; ++i) {
        struct event *ev = (struct event *)gqueue(events, i);
        for (int j = 0; j < ev->entities->count; ++j) {
            tmp = (struct entity *)gqueue(ev->entities, j);
            if (strcmp(tmpvar, tmp->var) == 0) {
                free(tmp->var);
                tmp->var = (char*)strdup(en->var);
                tmp->ptr = en->ptr;
                tmp->sitype = en->sitype;
            }
        }
    }
    free(tmpvar);
}

void connectentitysymbol(struct queue *cst, struct queue *events) {
    for (int i = 0; i < events->count; ++i) {
        struct event *ev = (struct event *)gqueue(events, i);
        for (int j = 0; j < ev->entities->count; ++j) {
            struct entity *en = (struct entity *)gqueue(ev->entities, j);
            if ((en->ptr = searchcst(cst, en->var)) == NULL) {
                fprintf(stderr, "Entity variable %s is not found in the compiler symbol table\n", en->var);
                exit(-15);
            }
        }
    }
}

struct event *searchevent(struct queue *events, char *eventvar) {
    struct event *e;
    for (int i = 0; i < events->count; ++i) {
        e = (struct event*)gqueue(events, i);
        if (strcmp(e->var, eventvar) == 0) return e;
    }
    return NULL;
}

int countevententity(struct queue *events, char *eventvar) {
    return (searchevent(events, eventvar))->entities->count;
}

struct event *newevent(struct queue *events, char *eventvar) {
    struct event *ev;
    if ((ev = searchevent(events, eventvar)) == NULL) {
        // printf("Error: duplicated event declaration for %s\n", eventvar);
        // exit(-12);
        struct event *new = (struct event*)malloc(sizeof(struct event));
        new->var = (char*)strdup(eventvar);
        new->entities = initqueue();
        enqueue(events, (void*)new);
        return new;
    }
    return ev;
}

void addevententity(struct event *event, char *entityvar, char *gramstr) {
    struct entity *new = (struct entity*)malloc(sizeof(struct entity));
    new->var = (char*)strdup(entityvar);
    new->type = __string2gramtype(gramstr);
    new->sitype = -1;
    new->alias = NULL;
    enqueue(event->entities, (void*)new);
}

void syncentitysitype(struct queue *events, char *var, enum argtype type) {
    for (int i = 0; i < events->count; ++i) {
        struct event *ev = (struct event *)gqueue(events, i);
        for (int j = 0; j < ev->entities->count; ++j) {
            struct entity *en = (struct entity *)gqueue(ev->entities, j);
            if (strcmp(en->var, var) == 0) {
                en->sitype = type;
            }
        }
    }
}

// void addentitytoevent(struct queue *events, char *eventvar, char *entityvar, char *gramstr) {
//     struct event *ev = searchevent(events, eventvar);
//     if (ev == NULL) {
//         printf("Error: Event %s not found\n", eventvar);
//         exit(-13);
//     }
//     struct entity *en = (struct event*)malloc(sizeof(struct entity));
//     en->var = (char*)strdup(entityvar);
//     en->type = string2gramtype(gramstr);
//     enqueue(ev->entities, (void*)en);
// }

char *event2template(struct queue *events, char *eventvar) {
    /*
    TODO
    */
    return NULL;
}

void *__popentityfromevent(struct queue *events, char *eventvar) {
    struct event *e = searchevent(events, eventvar);
    if (e == NULL) return NULL;
    return dequeue(e->entities);
}

void showevent(void *_event) {
    struct event *event = (struct event*)_event;
    printf("Event: %s\n", event->var);
    for (int j = 0; j < event->entities->count; ++j) {
        struct entity *entity = (struct entity *)gqueue(event->entities, j);
        printf("    ->Entity: %s(%s) alias(%s) SI(%d)\n ", entity->var, gram_name[entity->type], entity->alias, entity->sitype);
    }
}

void deallocateentity(struct entity *entity) {
    free(entity->var);
    if (entity->alias) {
        free(entity->alias);
    }
    free(entity);
}

void deallocateevent(void *_event) {
    struct event *event = (struct event*)_event;
    free(event->var);
    while (!isempty(event->entities)) {
        deallocateentity((struct entity*)dequeue(event->entities));
    }    
}

enum gramtype __string2gramtype(char *input) {
    if (strcmp(input, "Subj") == 0) return SubjectOf;
    else if (strcmp(input, "Acc") == 0) return AccusationOf;
    else if (strcmp(input, "AccI") == 0) return IntentionalAccusationOf; 
    else if (strcmp(input, "Dat") == 0) return Dative;
    else if (strcmp(input, "Gen") == 0) return Genitive; 
    else if (strcmp(input, "Abl") == 0) return Ablative;
    else if (strcmp(input, "Rel") == 0) return Relative;
    else if (strcmp(input, "Voc") == 0) return Vocative; 
    else if (strcmp(input, "AccE") == 0) return ExtentionalAccusationOf;
    else {
        fprintf(stderr, "Unknown Grammar type %s in the MR\n", input);
        exit(-2);
    }
}