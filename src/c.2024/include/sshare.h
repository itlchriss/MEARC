#ifndef SYN_SHARE_H
#define SYN_SHARE_H

#include "util.h"

int __compare_datatype__(struct datatype *, struct datatype *);

struct queue* __get_java_method_interpretations_from_chain__(char *);


/*
    a helper function checking the input cst symbol's SI is starting with '__Rel__'.
    If so, c is said to be a dependent value and the result is true, otherwise, the result is false.
    Besides, the one with '__Rel__' SI should have only one SI and this SI starts with '__Rel__', otherwise, a semantic declaration error is thrown
*/
int __is_Rel_dependent__(struct cstsymbol *);


int __is_Abstract_noun__(struct cstsymbol *);

/*
    Match an SI with the symbol only. this is used in finding REL SI
*/
int __match_si_with_symbol_only__(void *_, void *);


/*
    Match an SI with one argument data type
*/
int __match_si_with_input_arg_datatype__(void *, void *);


int check_need_assigned_entity(struct astnode *);


struct queue *__obtain_si_with_cstptr_(struct cstsymbol *, struct cstsymbol *, struct queue *);

int has_Rel_SI(struct queue *);
int has_Abstract_SI(struct queue *);

int __is_abtract_arg_done__(struct cstsymbol *ptr);
int __has_abstract_ex_arg__(struct cstsymbol *ptr);

int __direct_syntax_synthesis__(struct astnode *);
void __post_operation_si_subtree_synthesis__(struct astnode *);

// void subtree_si_synthesis(struct astnode *, struct si *);

void __combinatorial_subtree_si_synthesis__(struct astnode *, struct queue *);

struct queue *__obtain_si_(struct astnode *, struct astnode *, struct si *);

#endif
