#ifndef EVENT_H
#define EVENT_H
#include "cst.h"
#include "si.h"

enum gramtype { SubjectOf, AccusationOf, IntentionalAccusationOf, ExtentionalAccusationOf, Dative, Genitive, Ablative, Relative, Vocative };

struct event {
    /*
    *  The event variable of this event. Such as 'e01'
    */
    // char *var;
    struct cstsymbol *cstptr;

    
    struct queue *entities;
};

struct entity {
    // char *var;
    char *alias;
    struct cstsymbol *cstptr;
    enum gramtype type;
};


int countevententity(char *eventvar);
struct event *searchevent(char *eventvar);
struct event *__searchevent(struct cstsymbol *_cstptr);
struct event *newevent(struct cstsymbol *cstptr);
struct entity *newentity(struct cstsymbol *_cstptr, enum gramtype _type);
void showevent(void *_event);
void deallocateevent(void *_eventnode);

// void syncentitysitype(char *var, enum argtype type);
/*
Connecting the entity variable to the compiler symbol table
This connection is important in resolving the events
*/
void connectentitysymbol(struct queue *cst, struct queue *events);
/*
Aliasing the subject of an event using the event itself
This is an experiment case for possessive pronouns, e.g. A who has B, the event 'has' is a node synthesising the phrase, thus A should be aliased using the synthesised semantics at 'has'
*/
void aliaseventsubject(struct queue *cst, struct event *ptr);
/* 
    loop over all the events to check if the components(related variables) are assigned, then the event is assigned
    an event must be assigned before synthesising
*/
void update_events();
#endif
