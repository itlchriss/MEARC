#ifndef DST_H
#define DST_H

#include "util.h"

// dynamic symbol table header

// dst.template format
// sample: arr:int[]:p:sort
// first slot: symbol name. store in symbol
// second slot: data type. store with a combination of ptype + rtype
// third slot: software element type. store in stype
//              p: parameter
//              c: class
//              i: interface
//              f: function
//              d: class field
// forth slot: scope of the symbol. 
//          global is reserved for class and interface
//          all other inputs are treated as class, interface or function name

// Data primitive type
enum dstdataprimtype { Dst_P_Void, Dst_P_Int, Dst_P_Float, Dst_P_Double, Dst_P_Char };

// Data reference type
enum dstdatareftype { Dst_R_Array, Dst_Class, Dst_Interface };

// Symbol type. Its role in the program
enum dstsymtype { Dst_SYM_Field, Dst_SYM_Param, Dst_SYM_Function, Dst_SYM_Class, Dst_SYM_Interface, Dst_SYM_Standard };

struct dstnode {
    char *symbol, *scope, *rtypename;
    char *typestr;
    enum dstdataprimtype ptype;
    enum dstdatareftype rtype;
    enum dstsymtype stype;
    struct dstnode *next;
};

struct queue* getparams(struct dstnode *dst, struct dstnode *fptr);
struct dstnode* getdstsymbol(struct dstnode *dst, char *symbol);
void adddstnode(struct dstnode *dst, struct dstnode *new);
struct dstnode* newdstnode(char *symbol, char *datatype, char *symtype, char *scope);
void deallocatedst(struct dstnode *dst);
void showdst(struct dstnode *dst);
#endif

