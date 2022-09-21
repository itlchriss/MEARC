#ifndef UTIL_H
#define UTIL_H

struct queue {
    int count;
    struct queuenode *q;
    struct queuenode *last;
};

struct queuenode {
    void *datanode;
    struct queuenode *next;
    struct queuenode *prev;
};



struct queue* initqueue();
void enqueue(struct queue* queue, void *node);
void* dequeue(struct queue* queue);
void* pqueue(struct queue* queue);
void* gqueue(struct queue* queue, int index);
void rqueue(struct queue* queue, int index);
struct queuenode* _gqueue(struct queue* queue, int index);
void* plastqueue(struct queue* queue);
int isempty(struct queue* queue);
void showqueue(struct queue *queue, void (*print)(void*));
void* searchqueue(struct queue *queue, void *data, int (*compare)(void*, void*));
// use queue as a stack
void push(struct queue* stack, void *node);
void* pop(struct queue* stack);
void* peek(struct queue* stack);

void prepend(char *s, const char* t);
void append(char *s, const char* t);

void deallocatequeue(struct queue *queue, void (*deallocate)(void*));


char * getfunctor(char *);
char * getpos(char *);
char * getchunk(char *);

char * conv_cardinal_number(char *);

void popchar(char *);

// string replace
// replacing all instances of str1 in str with str2
// returning a new instances of char array containing the replaced string
char* strrep(char *str, char *str1, char *str2);
unsigned long long sdbm(unsigned char *str);
void trim(char *s);

#endif
