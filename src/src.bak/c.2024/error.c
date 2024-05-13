#include "error.h"
#include <stdio.h>
#include <stdlib.h>

void sisyntax_error(char *filepath, char *key, char *value) {
    fprintf(stderr, "Syntax error:");
    fprintf(stderr, "Cannot parse SI file %s with key %s and value %s\n", filepath, key ,value);
    exit(-1);
}

void semantic_error(char *s, char *support_info) {
    fprintf(stderr, "Semantic error:");
    fprintf(stderr, s, support_info);
    exit(-1);
}

void syntax_error(char *s, char *support_info) {
    fprintf(stderr, "Syntax error:");
    fprintf(stderr, s, support_info);
    exit(-1);
}

void siconflict_error(char *support_info) {
    fprintf(stderr, "SI conflict error:");
    fprintf(stderr, "The predicate(%s) are more than one SI existing accepts the same number of arguments with exactly the same data types\n", support_info);
    exit(-1);
}

void sinotfound_error(char *support_info) {
    fprintf(stderr, "SI not found error:");
    fprintf(stderr, "The predicate(%s) has no SI found with the encountered argument data types\n", support_info);
    exit(-1);
}

void sideclare_error(char *support_info) {
    fprintf(stderr, "SI declaration error:");
    fprintf(stderr, "The predicate(%s) should have all its SIs starting with '__Rel__'. If you are not intended to declare it as Rel SI, please correct it.\n", support_info);
    exit(-1);
}

void internal_error(char *support_info) {
    fprintf(stderr, "Internal error:");
    fprintf(stderr, "Please check the function %s\n", support_info);
    exit(-1);
}
