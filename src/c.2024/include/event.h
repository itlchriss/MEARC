#ifndef EVENT_H
#define EVENT_H
#include "cst.h"
#include "si.h"

enum gramtype { SubjectOf, AccusationOf, IntentionalAccusationOf, ExtentionalAccusationOf, Dative, Genitive, Ablative, Relative, Vocative };

struct event {
    /*
    *  The event variable of this event. Such as 'e01'
    */
    char *var;
    struct queue *entities;
};

struct entity {
    char *var;
    char *alias;
    // enum argtype sitype;
    struct cstsymbol *ptr;
    enum gramtype type;
};

char *gramtype2string(enum gramtype type);
int countevententity(char *eventvar);
struct event *searchevent(char *eventvar);
void removeentityfromevents(char *entityvar);
// struct event *newevent(struct queue *events, char *eventvar);
struct event *newevent(char *eventvar);
void addevententity(struct event *event, char *entityvar, char *gramstr);
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
#endif
