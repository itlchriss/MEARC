#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "util.h"
#include "dst.h"

// Data primitive type
char* dstdataprimtype_name[] = { "Dst_P_Void", "Dst_P_Int", "Dst_P_Float", "Dst_P_Double", "Dst_P_Char" };

// Data reference type
char* dstdatareftype_name[] = { "Dst_R_Array", "Dst_Class", "Dst_Interface" };

// Symbol type. Its role in the program
char* dstsymtype_name[] = { "Dst_SYM_Field", "Dst_SYM_Param", "Dst_SYM_Function", "Dst_SYM_Class", "Dst_SYM_Interface", "Dst_SYM_Standard" };


struct dstnode* newdstnode(char *symbol, char *datatype, char *symtype, char *scope) {
    struct dstnode *new = (struct dstnode*) malloc (sizeof(struct dstnode));
    new->symbol = (char*)strdup(symbol);
    new->scope = (char*)strdup(scope);
    new->next = NULL;
    new->rtypename = NULL;
    new->rtype = -1;
    new->stype = -1;
    new->ptype = -1;
    // convert symtype to stype
    if (strcmp(symtype, "c") == 0) {
        new->stype = Dst_SYM_Class;
    } else if (strcmp(symtype, "p") == 0) {
        new->stype = Dst_SYM_Param;
    } else if (strcmp(symtype, "i") == 0) {
        new->stype = Dst_SYM_Interface;
    } else if (strcmp(symtype, "f") == 0) {
        new->stype = Dst_SYM_Function;
    } else if (strcmp(symtype, "d") == 0) {
        new->stype = Dst_SYM_Field;
    } else if (strcmp(symtype, "s") == 0) {
        new->stype = Dst_SYM_Standard;
    } else { 
        new->stype = -1;
        printf("Invalid symbol type has been specified in dst template: %s\n", symtype);
    }

    new->typestr = (char*)strdup(datatype);
    
    // convert datatype to ptype and rtype
    int i = 0, bc = 0, bo = 0, c = 0;
    for (i = 0; i < strlen(datatype); ++i) {
        if (datatype[i] == '[') { bo = i; ++c; }
        else if (datatype[i] == ']') { bc = i; ++c; }
    }
    if (c == 2) {
        new->rtype = Dst_R_Array;
        datatype[bo] = '\0';
    } 
    if ((bo > 0 || bc > 0) && new->rtype == -1) {
        printf("Please check with the type in dst: %s\n", datatype);
    }
    if (strcmp(datatype, "void") == 0) {
        new->ptype = Dst_P_Void;
    } else if (strcmp(datatype, "int") == 0) {
        new->ptype = Dst_P_Int;
    } else if (strcmp(datatype, "double") == 0) {
        new->ptype = Dst_P_Double;
    } else if (strcmp(datatype, "float") == 0) {
        new->ptype = Dst_P_Float;
    } else if (strcmp(datatype, "char") == 0) {
        new->ptype = Dst_P_Char;
    } else {
        // a custom class object type
        new->rtype = Dst_Class;
        new->rtypename = (char*)strdup(datatype);
    }
    return new;
}

void adddstnode(struct dstnode *dst, struct dstnode *new) {
    struct dstnode *tmp = dst;
    while (tmp->next != NULL) {
        tmp = tmp->next;
    }
    tmp->next = new;
}

struct dstnode* getdstsymbol(struct dstnode *dst, char *symbol) {
    struct dstnode *tmp = dst;
    while ((tmp) != NULL) {
        if (strcmp(tmp->symbol, symbol) == 0) return tmp;
        else tmp = tmp->next;
    }
    return NULL;
}

struct queue* getparams(struct dstnode *dst, struct dstnode *fptr) {
    struct queue *params = initqueue();
    struct dstnode *tmp = dst;
    while (tmp != NULL) {
        if (strcmp(tmp->scope, fptr->symbol) == 0 && tmp->stype == Dst_SYM_Param) enqueue(params, (void*)tmp);
        tmp = tmp->next;
    }
    return params;
}

void showdst(struct dstnode *dst) {
    struct dstnode *tmp = dst;
    if (tmp == NULL) printf("DST head is NULL\n");
    while (tmp != NULL) {
        printf("Symbol: %s ", tmp->symbol);
        printf("Type: %s ", tmp->typestr);
        if (tmp->stype >= 0 && tmp->stype < 6)
            printf("Symbol type: %s ", dstsymtype_name[tmp->stype]);
        else 
            printf("Symbol type: Unknown ");
        printf("Scope: %s\n", tmp->scope);
        tmp = tmp->next;
    }
}

void deallocatedst(struct dstnode *dst) {
    struct dstnode *tmp;
    while (dst != NULL) {
        tmp = dst;
        if (dst->symbol)
            free(dst->symbol);
        if (dst->scope)
            free(dst->scope);
        if (dst->rtypename)
            free(dst->rtypename);    
        if (dst->typestr)    
            free(dst->typestr);
        dst = dst->next;
        free(tmp);
    }
    if (dst)
        free(dst);
}


