#ifndef _EVENT_H
#define _EVENT_H

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
    enum gramtype type;
};

int countevententity(struct queue *events, char *eventvar);
struct event *searchevent(struct queue *events, char *eventvar);
struct event *newevent(struct queue *events, char *eventvar);
void addevententity(struct event *event, char *entityvar, char *gramstr);
void showevent(void *_event);
void deallocateevent(void *_eventnode);

#endif
