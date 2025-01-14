#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <string.h>
#include "event.h"
#include "util.h"
#include "error.h"
#include "alias.h"

extern struct queue *events;

void deallocateentity(struct entity *entity);

enum gramtype __string2gramtype(char *input);


int __eventptrcomparator(void *a, void *b) {
    if (a == b) return 0;
    else return 1;
}



/* 
    loop over all the events to check if the components(related variables) are assigned, then the event is assigned
    an event must be assigned before synthesising
*/
void update_events() {
    struct event *e = NULL;
    for (int i = 0; i < events->count; ++i) {
        e = (struct event *)gqueue(events, i);
        int all_assigned = TRUE;
        for (int j = 0; j < e->entities->count; ++j) {
            struct entity *en = (struct entity*)gqueue(e->entities, j);
            if (en->cstptr->ref_count == 1 && en->cstptr->status != Assigned) {
                struct cstsymbol *c = searchalias(en->cstptr);
                if (c == NULL) {
                    semantic_error("A variable(%s) has no alias and does not have a semantics. This is checked in the event. Please check the meaning representation, and ensure that all predicates have proper semantic interpretations.", en->cstptr->symbol);
                } else if (c->status != Assigned) {
                    semantic_error("A variable(%s) has an aliased variable, however, the aliased variable does not have a synthesised semantic interpretation.", en->cstptr->symbol);
                } else {
                    en->cstptr = c;
                }
            } else if (en->cstptr->status != Assigned) all_assigned = FALSE;
        }
        if (all_assigned) e->cstptr->status = Assigned;
    }
}

struct event *searchevent(char *eventvar) {
    struct event *e;
    for (int i = 0; i < events->count; ++i) {
        e = (struct event*)gqueue(events, i);
        if (strcmp(e->cstptr->symbol, eventvar) == 0) return e;
    }
    return NULL;
}

struct event *__searchevent(struct cstsymbol *_cstptr) {
    struct event *e;
    for (int i = 0; i < events->count; ++i) {
        e = (struct event*)gqueue(events, i);
        if (e->cstptr == _cstptr) return e;
    }
    return NULL;
}

int countevententity(char *eventvar) {
    return (searchevent(eventvar))->entities->count;
}

struct event *newevent(struct cstsymbol *_cstptr) {
    struct event *new = NULL, *tmp = NULL;
    for (int i = 0; i < events->count; ++i) {
        tmp = (struct event *)gqueue(events, i);
        if (tmp->cstptr == _cstptr) {
            new = tmp;
            break;
        }
    }
    if (new == NULL) {
        new = (struct event*)malloc(sizeof(struct event));
        new->cstptr = _cstptr;
        new->entities = initqueue();
        enqueue(events, (void*)new);
    }            
    return new;
}

struct entity *newentity(struct cstsymbol *_cstptr, enum gramtype _type) {
    struct entity *new = (struct entity*)malloc(sizeof(struct entity));
    new->cstptr = _cstptr;
    new->type = _type;
    return new;
}


void *__popentityfromevent(char *eventvar) {
    struct event *e = searchevent(eventvar);
    if (e == NULL) return NULL;
    return dequeue(e->entities);
}

void showevent(void *_event) {
    struct event *event = (struct event*)_event;
    printf("Event: %s\n", event->cstptr->symbol);
    for (int j = 0; j < event->entities->count; ++j) {
        struct entity *entity = (struct entity *)gqueue(event->entities, j);
        printf("=============================Event symbol===================================\n");
        printf("    ->Entity: %s(%d) cst reference count: %d\n ", entity->cstptr->symbol, entity->type, entity->cstptr->ref_count);
        printf("===================================================================================\n");
    }
}

void deallocateentity(struct entity *entity) {
    free(entity);
}

void deallocateevent(void *_event) {
    struct event *event = (struct event*)_event;
    while (!isempty(event->entities)) {
        deallocateentity((struct entity*)dequeue(event->entities));
    }    
}


