#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdarg.h>
#include "util.h"

void throw_error(char *, char *);

char * combine_strings(int count, ...) {
    va_list valist;
    char *result = NULL;
    va_start(valist, count); 
    for (int i = 0; i < count; ++i) {
        char *s = va_arg(valist, void *);
        if (!s || strlen(s) == 0) continue;
        else {
            int size = 0;
            if (!result) size = strlen(s) + 1;
            else size = strlen(s) + strlen(result) + 1;
            char *new = (char *) malloc(sizeof(char) * size);
            new[0] = '\0';
            if (result) {
                append(new, result);
                free(result);
            }
            append(new, s);
            result = new;
        }
    }
    va_end(valist);
    return result;
}

char * conv_cardinal_number(char *input) {
    if (strcmp(input, "zero")) {
        return "0";
    } else if (strcmp(input , "one")) {
        return "1";
    } else if (strcmp(input , "two")) {
        return "2";
    } else if (strcmp(input , "three")) {
        return "3";
    } else if (strcmp(input , "four")) {
        return "4";
    } else if (strcmp(input , "five")) {
        return "5";
    } else if (strcmp(input , "six")) {
        return "6";
    } else if (strcmp(input , "seven")) {
        return "7";
    } else if (strcmp(input , "eight")) {
        return "8";
    } else if (strcmp(input , "nine")) {
        return "9";
    } else {
        printf("Unknown cardinal number encountered : %s\n", input);
        exit(-2);
    }
}

char* getfunctor(char *input) {
    char *pos;
    char *tmp = strdup(input);
    char *token = strtok_r(tmp, "@", &pos);
    if (token == NULL) throw_error("getfunctor", input);
    char *result = (char *)strdup(token + 1);
    free(tmp);
    printf("%s %s\n", input, result);
    return result;
}

char* getpos(char *input) {
    char *pos;
    char *tmp = strdup(input);
    char *token = strtok_r(tmp, "@", &pos);
    if (token == NULL) throw_error("getpos", input);
    token = strtok_r(NULL, "@", &pos);
    token = (char *)strdup(token) + 1;
    token = strtok_r(token, ",", &pos);
    char *result = (char *)strdup(token);
    free(tmp);
    return result;
}

char* getchunk(char *input) {
    char *tmp = strdup(input);
    char *token = strtok(tmp, "@");
    if (token == NULL) throw_error("getchunk", input);
    token = strtok(NULL, "@");
    token = (char *)strdup(token + 1);
    token = strtok(token, ",");    
    token = strtok(NULL, ",");
    token = (char *)strdup(token);    
    token[strlen(token) - 1] = '\0';
    free(tmp);
    return token;
}

void throw_error(char *func_name, char *data) {
    printf("The annotated functor is invalid in %s: %s", func_name, data);
    exit(-1);
}

void prepend(char *s, const char *t) {
    memmove(s + strlen(t), s, strlen(s) + 1);
    memcpy(s, t, strlen(t));
}

void append(char *s, const char *t) {
    if (strlen(s) == 0) {
        memcpy(s, t, strlen(t) + 1);
    } else {
        strncat(s, t, strlen(t));
    }
}

// sdbm from http://www.cse.yorku.ca/~oz/hash.html
// static unsigned long sdbm(unsigned char *str)
unsigned long long sdbm(unsigned char *str)
{
    unsigned long long hash = 0;
    int c;

    while ((c = *str++))
        hash = c + (hash << 6) + (hash << 16) - hash;

    return hash;
}

void trim(char *s) {
    if (s && *s) {
        int i = 0;
        while(isspace(*s)) s++;
        for (i = strlen(s) - 1; isspace(s[i]); i--);
        s[i + 1] = '\0';
    }
}



struct queue* initqueue() {
    struct queue *new = (struct queue*) malloc (sizeof(struct queue));
    new->q = (struct queuenode*) malloc (sizeof(struct queuenode));
    new->q->next = NULL;
    new->count = 0;
    return new;
}

void enqueue(struct queue* queue, void *node) {
    struct queuenode* tmp = queue->q;
    while(tmp->next != NULL) {
        tmp = tmp->next;
    }
    tmp->next = (struct queuenode*) malloc (sizeof(struct queuenode));
    tmp->next->datanode = node;
    tmp->next->prev = tmp;
    tmp->next->next = NULL;
    queue->last = tmp->next;
    ++queue->count;
}

void* dequeue(struct queue *queue) {
    if (queue->count == 0) return NULL;
    void *ptr = queue->q->next->datanode;
    queue->q->next = queue->q->next->next;
    // free(queue->q->next);
    --queue->count;
    return ptr;
}

void rqueue(struct queue *queue, int index) {
    struct queuenode *node = _gqueue(queue, index);
    if (node != NULL) {
        node->prev->next = node->next;
        if (node->next != NULL) {
            node->next->prev = node->prev;
        }
        queue->count--;
    }
}

void* gqueue(struct queue* queue, int index) {
    struct queuenode *node = _gqueue(queue, index);
    if (node != NULL) {
        return node->datanode;
    } else {
        return NULL;
    }
}

struct queuenode* _gqueue(struct queue* queue, int index) {
    if (queue->count == 0 || index >= queue->count) return NULL;
    struct queuenode *ptr = queue->q->next;
    int i = 0;
    while (i != index && ptr != NULL) {
        ptr = ptr->next;
        ++i;
    }
    return ptr;
}

void* pqueue(struct queue *queue) {
    if (queue->count == 0) return NULL;
    void *ptr = queue->q->next->datanode;
    return ptr;
}

void* plastqueue(struct queue* queue) {
    if (queue->count == 0) return NULL;
    struct queuenode* tmp = queue->q;
    while(tmp->next != NULL) {
        tmp = tmp->next;
    }
    void *ptr = tmp->datanode;
    return ptr;
}

int isempty(struct queue* queue) {
    return queue->count == 0;
}



void push(struct queue* queue, void *node) {
    // struct queuenode* tmp = stack->q;
    // while(tmp->next != NULL) {
    //     tmp = tmp->next;
    // }    
    // tmp->next = (struct queuenode*) malloc (sizeof(struct queuenode));
    // tmp->next->prev = tmp;
    // tmp->next->datanode = node;
    // tmp->next->next = NULL;
    // stack->last = tmp->next;
    // ++stack->count;
    if (queue->count == 0) {
        enqueue(queue, node);
    } else {
        struct queuenode *head = queue->q, *top = head->next;
        struct queuenode *new = (struct queuenode*) malloc (sizeof(struct queuenode));
        head->next = new;
        new->next = top;
        top->prev = new;
        new->prev = head;
        new->datanode = node;
        queue->count++;        
    }
}

void* pop(struct queue* stack) {
    if (stack->count == 0) return NULL;
    void *ptr = stack->last->datanode;
    stack->last = stack->last->prev;    
    --stack->count;
    return ptr;
}

void* peek(struct queue* stack) {
    if (stack->count == 0) return NULL;
    struct queuenode* tmp = stack->q;
    while(tmp->next != NULL) {
        tmp = tmp->next;
    }
    void *ptr = tmp->datanode;
    return ptr;
}

struct queue* copyqueue(struct queue *input) {
    struct queue *new = initqueue();
    for (int i = 0; i < input->count; ++i) {
        enqueue(new, gqueue(input, i));
    }
    return new;
}


void deallocatequeue(struct queue *queue, void (*deallocate)(void*)) {
    struct queuenode *tmp = queue->q->next;
    while (queue->count > 0) {
        tmp = queue->q->next;
        if (queue->q->next == NULL) break;
        queue->q->next = queue->q->next->next;
        if (deallocate != NULL) {
            deallocate(tmp->datanode);
        }
        --queue->count;
    }
    free(queue->q);
    free(queue);
}

int strsearch(char *str, char *str1, int *occur) {
    int t[256], i, skip = 0, k = 0;
    // first, mark all the instances of str1 in str
    // using Boyer-Moore-Horspool
    for (i = 0; i < 256; ++i) {
        t[i] = strlen(str1);
    }
    for (i = 0; i < strlen(str1) - 1; ++i) {
        t[(int)str1[i]] = strlen(str1) - 1 - i;
    }
    
    while (strlen(str) - skip >= strlen(str1)) {
        i = strlen(str1) - 1;
        while (str[skip + i] == str1[i]) {
            if (i == 0) { 
                if (occur != NULL) {
                    occur[k++] = skip; 
                } else {
                    ++k;
                }
                break; 
            }
            --i;
        }
        skip += t[(int)str[skip + strlen(str1) - 1]];
    }
    return k;
}

int isInt(char *string){
    char *endp;
    long n;
    n = strtol(string, &endp, 0);
    if(!*endp && -2147483648  <= n && n <= 2147483647) {
        return TRUE;
    }
    return FALSE;
}

int isChar(char *string) {
    if (strlen(string) == 3 && string[0] == '\'' && string[2] == '\'') {
        return TRUE;
    } else {
        return FALSE;
    }
}

int isString(char *string) {
    if (strlen(string) > 2 && string[0] == '"' && string[strlen(string) - 1] == '"') {
        return TRUE;
    } else {
        return FALSE;
    }
}

int ssearch(char *str, char* pattern) {
    if (str == NULL || pattern == NULL) return FALSE;
    int occur[strlen(str)/strlen(pattern) + 1], k = 0;
    k = strsearch(str, pattern, occur);
    if (k == 0) return FALSE;
    else return TRUE;
}

char* strrep(char *str, char *str1, char *str2) {
    int i = 0, occur[strlen(str)/strlen(str1) + 1], k = 0;
    k = strsearch(str, str1, occur);

    // no occurrences of str1 in str
    if (k == 0) return NULL;
    int size = strlen(str) - (k * (strlen(str1) - strlen(str2))) + 1;
    char *new = (char*) malloc (sizeof(char) * size);
    int j, m, kc;
    for (i = 0, kc = 0, m = 0; i < strlen(str) && m < size - 1;) {
        if (occur[kc] == i) {
            for (j = 0; j < strlen(str2); ++j) {
                new[m++] = str2[j];
            }
            ++kc;
            i += strlen(str1);
            if (k == kc) break;
        } else {
            new[m++] = str[i++];
        }
    }
    if (i < strlen(str)) {
        for (; i < strlen(str); ++i, ++m) {
            new[m] = str[i];
        }
    }
    new[size - 1] = '\0';
    return new;    
}

void popchar(char *s) {
    memmove(s, s+1, strlen(s));
}

char* __combine_3_strings__(char *a, char *b, char *c) {
    int _size = strlen(a) + strlen(b) + strlen(c) + 1;
    char *new = (char*)malloc(sizeof(char) * _size);
    memcpy(new, a, strlen(a) + 1);
    append(new, b);
    append(new, c);
    new[_size - 1] = '\0';
    return new;
}

void showqueue(struct queue *queue, void (*print)(void*)) {
    if (queue == NULL) return;
    for (int i = 0; i < queue->count; ++i) {
        print(gqueue(queue, i));
    }
}

void* searchqueue(struct queue *queue, void *data, int (*compare)(void*, void*)) {
    if (queue == NULL || queue->count <= 0) return NULL;
    void *tmp = NULL;
    for (int i = 0; i < queue->count; ++i) {
        tmp = gqueue(queue, i);
        if (compare(tmp, data)) return tmp;
    }
    return NULL;
}

struct queue* q_searchqueue(struct queue *queue, void *data, int (*compare)(void*, void*)) {
    if (queue == NULL || queue->count <= 0) return NULL;
    struct queue *new = initqueue();
    void *tmp = NULL;
    for (int i = 0; i < queue->count; ++i) {
        tmp = gqueue(queue, i);
        if (compare(tmp, data)) {
            enqueue(new, tmp);
        }
    }
    return new;
}

void applyqueue(struct queue *queue, void *data, void (*func)(void*, void*)) {
    if (queue == NULL || queue->count <= 0) return;
    for (int i = 0; i < queue->count; ++i) {
        func(gqueue(queue, i), data);
    }
}



