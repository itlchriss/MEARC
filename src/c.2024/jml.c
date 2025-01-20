#include "jml.h"
#include "util.h"
#include "stdlib.h"
#include "string.h"
#include "si.h"
#include "sshare.h"
#include "error.h"

extern struct queue *silist;

char * array_equals_primitive_1_var_1_direct(char *var, char *sym);
char * array_equals_2_vars(char *var1, char *var2);

void _itoa(int N, char *str) {
    int i = 0;

    if (N == 0) {
        str[0] = '0';
        str[1] = '\0';
        return;
    }
  
    // Save the copy of the number for sign
    int sign = N;

    // If the number is negative, make it positive
    if (N < 0)
        N = -N;

    // Extract digits from the number and add them to the
    // string
    while (N > 0) {
      
        // Convert integer digit to character and store
      	// it in the str
        str[i++] = N % 10 + '0';
      	N /= 10;
    } 

    // If the number was negative, add a minus sign to the
    // string
    if (sign < 0) {
        str[i++] = '-';
    }

    // Null-terminate the string
    str[i] = '\0';

    // Reverse the string to get the correct order
    for (int j = 0, k = i - 1; j < k; j++, k--) {
        char temp = str[j];
        str[j] = str[k];
        str[k] = temp;
    }
}


// char * conv_operator(operator op) {
//     int idx = (int)op;
//     return j_opers[idx];
// }


char * array_partially_equal(char *d1, char *d2) {
    char *pos, *pos1;
    char *_s = strtok_r(d2, "_", &pos);
    char *startchr = (char *)strdup(_s);
    char *endchr = strdup(strtok_r(NULL, "_", &pos));
    char *seq = strdup(strtok_r(NULL, "_", &pos));

    // int c = 0;
    char *result = NULL;
    char *tmp = "%x[%i] == %s";
    char *_a = strrep(tmp, "%x", d1);
    _s = strtok_r(seq, ",", &pos1);
    int start = (int)(startchr[0] - '0'), end = (int)(endchr[0] - '0');
    for (int i = start; i <= end; ++i) {
        char num[10];
        _itoa(i, num);
        char *_b = strrep(_a, "%i", num);
        char *_target = strrep(_b, "%s", _s);
        free(_b);
        if (result != NULL) {
            result = combine_strings(3, result, " && ", _target);
        } else {
            result = _target;
        }
        _s = strtok_r(NULL, ",", &pos1);
    }
    // while (_t != NULL) {
    //     char num[10];
    //     _itoa(c, num);
    //     char *_b = strrep(_a, "%i", num);
    //     char *_target = strrep(_b, "%s", _t);
    //     free(_b);
    //     if (result != NULL) {
    //         result = combine_strings(3, result, " && ", _target);
    //     } else {
    //         result = _target;
    //     }
    //     c++;
    //     _t = strtok_r(NULL, ",", &pos);
    // }
    return result;
}


char * contain_only_string_chararray(char *d1, char *d2) {
    char *pos;
    char *_t = strtok_r(d2, ",", &pos), *connective = NULL;
    if (strcmp(_t, "or") == 0) {
        connective = " || ";
    } else {
        connective = " && ";
    }
    char *head = "\\forall int i; 0 <= i < %x.length; ";
    char *result = strrep(head, "%x", d1);    
    _t = strtok_r(NULL, ",", &pos);
    char *end = NULL;
    while (_t != NULL) {
        struct si *si = searchqueue(silist, _t, __match_si_with_symbol_only__);
        if (si == NULL) {
            sinotfound_error("Cannot find the SI for the symbol in the contain_only_string_chararray function");
        }
        char *tmp;
        if (si->type == SI_INT_TYPE_JAVA_METHOD) {
            tmp = "%s(%x.get(i))";
        } else {
            tmp = "%x.charAt(i) == %s";
        }
        char *_target = strrep(tmp, "%s", si->interpretation);        
        tmp = (char *)strdup(_target);
        free(_target);
        _target = strrep(tmp, "%x", d1);
        free(tmp);        
        if (end != NULL) {
            end = combine_strings(3, end, connective, _target);
        } else {
            end = _target;
        }
        _t = strtok_r(NULL, ",", &pos);
    }
    result = combine_strings(2, result, end);
    return result;
}

/* 
    the entity that provides d1 must be a list
*/
char * list_2_array_equal(char *d1, char *d2) {
    return list_2_string_array_equal(d1, d2);
}


/* 
    the entity that provides d1 must be a list and d2 must be a string array
*/
char * list_2_string_array_equal(char *d1, char *d2) {
    char *pos;
    char *_t = strtok_r(d2, ",", &pos);
    int c = 0;
    char *result = NULL;
    char *tmp = "%x.get(%i) == %s";
    char *_a = strrep(tmp, "%x", d1);
    while (_t != NULL) {
        char num[10];
        _itoa(c, num);
        char *_b = strrep(_a, "%i", num);
        char *_target = strrep(_b, "%s", _t);
        free(_b);
        if (result != NULL) {
            result = combine_strings(3, result, " && ", _target);
        } else {
            result = _target;
        }
        c++;
        _t = strtok_r(NULL, ",", &pos);
    }
    return result;
}

char * array_equal(char *d1, char *d2) {
    /*
        A direct value for an array means the value is a comma-separated string representing the values of an array
    */
    int _d1_direct_value = ssearch(d1, ",");
    int _d2_direct_value = ssearch(d2, ",");
    if (_d1_direct_value == TRUE && _d2_direct_value == TRUE) {
        // TODO
        /* very rare in specification */
    } else if (_d1_direct_value == TRUE) {
        return array_equals_primitive_1_var_1_direct(d2, d1);
    } else if (_d2_direct_value == TRUE) {
        return array_equals_primitive_1_var_1_direct(d1, d2);
    } else {
        return array_equals_2_vars(d1, d2);
    }
    return NULL;
}

/* 
    returning a JML format string representing the Arrays.Equal comparison
    between a variable with array datatype and a direct symbol with array datatype

    @var the intermediate SI of the variable 
    @sym the intermediate SI of the array direct symbol, should be in the format "a,b,c..."
*/
char * array_equals_primitive_1_var_1_direct(char *var, char *sym) {
    char *pos;
    char *_t = strtok_r(sym, ",", &pos);
    int c = 0;
    char *result = NULL;
    char *tmp = "%x[%i] == %d";
    char *_a = strrep(tmp, "%x", var);
    while (_t != NULL) {
        char num[10];
        _itoa(c, num);
        char *_b = strrep(_a, "%i", num);
        char *_target = strrep(_b, "%d", _t);
        free(_b);
        if (result != NULL) {
            result = combine_strings(3, result, " && ", _target);
        } else {
            result = _target;
        }
        c++;
        _t = strtok_r(NULL, ",", &pos);
    }
    // char num[10];
    // _itoa(c, num);
    // result = combine_strings(5, result, " && ", var, ".length == ", num);
    return result;
}

char * array_equals_2_vars(char *var1, char *var2) {
    char *tmp = "\\forall int i; 0 <= i < %x.length; %x[i] == %y[i]";
    char *a = strrep(tmp, "%x", var1);
    char *result = strrep(a, "%y", var2);
    free(a);
    return result;
}

// char * string_consist_only(char *var1, char *var2) {
//     return result;
// }

// char * string_contain(char *var1, char *var2) {
//     return result;
// }