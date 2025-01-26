#ifndef JML_H
#define JML_H

#include "cst.h"

/*
    JML specific helper functions
    One can extend these functions to generate other formal specification languages
*/

char * contain_construct(char *, char *, struct datatype*, struct datatype*);

char * array_equal(char*, char*);

char * list_2_string_array_equal(char*, char*);

char * contain_only_template(char*, char*, struct datatype*, struct datatype*);

char * string_charArray_equal(char *, char *);

/* currently only support for integer array  */
char * list_2_array_equal(char*, char*);

char * array_partially_equal(char*, char*);

char * contain_only_string_chararray(char *, char *);
#endif
