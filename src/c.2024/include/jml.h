#ifndef JML_H
#define JML_H

/*
    JML specific helper functions
    One can extend these functions to generate other formal specification languages
*/

char * array_equal(char*, char*);

char * list_2_string_array_equal(char*, char*);

/* currently only support for integer array  */
char * list_2_array_equal(char*, char*);

char * array_partially_equal(char*, char*);
#endif
