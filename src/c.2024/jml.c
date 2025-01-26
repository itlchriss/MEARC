#include "jml.h"
#include "util.h"
#include "stdlib.h"
#include "string.h"
#include "si.h"
#include "sshare.h"
#include "error.h"
#include <stdio.h>

extern struct queue *silist;

char * array_equals_primitive_1_var_1_direct(char *var, char *sym);
char * array_equals_2_vars(char *var1, char *var2);

char * __get_length_representation(struct datatype *dt) {
    if (dt->r == String) {
        return "length()";
    } else if (dt->r == Array || dt->r == String_Array) {
        return "length";
    } else if (dt->r == List) {
        return "size()";
    } else {
        return NULL;
    }
}

/*
    mode == 0, no template index
    mode == 1, template index
*/
char * __get_access_representation(struct datatype *dt, int mode) {
    if (dt->r == String) {
        if (mode == 0) {
            return "%x.charAt(i)";
        } else {
            return "%x.charAt(%i)";
        }
    } else if (dt->r == Array || dt->r == String_Array) {
        if (mode == 0) {
            return "%x[i]";
        } else {
            return "%x[%i]";
        }
    } else if (dt->r == List) {
        if (mode == 0) {
            return "%x.get(i)";
        } else {
            return "%x.get(%i)";
        }
    } else {
        return NULL;
    }
}

int __match_si_with_template_datatype_only__(void *_si, void *_datatype) {
    struct si *si = (struct si *)_si;
    struct datatype *dt = (struct datatype *)_datatype;
    // if (strcmp(si->symbol, symbol) == 0) return TRUE;
    // else return FALSE;
    struct si_arg *arg = (struct si_arg *)gqueue(si->args, 0);
    if (__compare_datatype__(arg->datatype, dt) == TRUE) {
        return TRUE;
    } else {
        return FALSE;
    }
}

int __match_si_with_datatype_char_only__(void *_si, void *_datatype) {
    struct si *si = (struct si *)_si;
    // struct datatype *dt = (struct datatype *)_datatype;
    // if (strcmp(si->symbol, symbol) == 0) return TRUE;
    // else return FALSE;
    struct si_arg *arg = (struct si_arg *)gqueue(si->args, 0);
    if (search_syntax(si, NN) || search_syntax(si, NNS)) {
        return TRUE;
    } else {
        return FALSE;
    }
}

int is_primitive(char *s) {
    if (ssearch(s, ",") == FALSE && 
        (isInt(s) == TRUE || isChar(s) == TRUE) || isString(s) == TRUE) {
            return TRUE;
        }
    else {
        return FALSE;
    }
}

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

char * process_csv_checking(char *d1, char *data, char *length_repr, char *access_expr) {
    char *pos;
    char *_t = strtok_r(data, ",", &pos), *connective = NULL;
    if (strcmp(_t, "or") == 0) {
        connective = " || ";
    } else {
        connective = " && ";
    }
    char *head = "\\forall int i; 0 <= i < %x.%l; %e";
    char *result = strrep(head, "%x", d1);
    // free(head);
    head = strrep(result, "%l", length_repr);
    free(result);
    result = head;
    char *expr = NULL;
    _t = strtok_r(NULL, ",", &pos);
    while (_t != NULL) {
        if (strcmp(_t, "range") == 0) {
            char *start = strtok_r(NULL, ",", &pos);
            char *end = strtok_r(NULL, ",", &pos);
            char *tmp = strdup("%s <= (int)%a && (int)%a <= %e"); 
            char *_target = strrep(tmp, "%s", start);
            tmp = (char *)strdup(_target);
            free(_target);
            _target = strrep(tmp, "%e", end);
            free(tmp);
            tmp = (char *)strdup(_target);
            free(_target);
            _target = strrep(tmp, "%a", access_expr);
            free(tmp);
            tmp = strrep(_target, "%x", d1);
            free(_target);
            _target = tmp;
            if (expr == NULL) {
                expr = _target;
            } else {
                expr = combine_strings(3, expr, connective, _target);
            }
        } else if (strcmp(_t, "equal") == 0) {
            char *c = strtok_r(NULL, ",", &pos);
            char *tmp = "%a == %s";
            char *_target = strrep(tmp, "%s", c);
            tmp = (char *)strdup(_target);
            free(_target);
            _target = strrep(tmp, "%a", access_expr);
            free(tmp);            
            tmp = strrep(_target, "%x", d1);
            free(_target);
            _target = tmp;
            if (expr == NULL) {
                expr = _target;
            } else {
                expr = combine_strings(3, expr, connective, _target);
            }
        }
        _t = strtok_r(NULL, ",", &pos);
    }
    result = strrep(head, "%e", expr);
    return result;
}

char * contain_construct(char *d1, char *d2, struct datatype *dt1, struct datatype *dt2) {
    struct si * si = searchqueue(silist, d2, __match_si_with_symbol_only__);
    if (si == NULL) {
        printf("contain_construct SI not found\n");
        sinotfound_error(d2);
    }
    char *cdata = (char *)strdup(si->interpretation);
    char *length_repr = __get_length_representation(dt1);
    if (strcmp(d2, "csvdata") == 0 && length_repr != NULL) {
        return process_csv_checking(d1, cdata, length_repr, __get_access_representation(dt1, 0));
    } else {
        if (length_repr == NULL) {
            printf("Construct: %s and length_repr is NULL\n", d2);
            internal_error("The construct provided is not supported.");
        } else {
            printf("Construct: %s %s\n", d2, length_repr);
        }
        internal_error("The construct provided is not supported.");
    }
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
        char *interpretation = NULL, *tmp;
        if (_t[0] == '\'' && _t[strlen(_t) - 1] == '\'') {
            interpretation = _t;
            tmp = "%x.charAt(i) == %s";
        } else {
            struct queue *siq = q_searchqueue(silist, _t, __match_si_with_symbol_only__);
            if (siq == NULL || siq->count == 0) {
                printf("jml.c contain_only_string_chararray\n");
                sinotfound_error(_t);
            }
            struct si *si = searchqueue(siq, _t, __match_si_with_datatype_char_only__);
            if (si == NULL) {
                printf("jml.c contain_only_string_chararray no noun type TSI template\n");
                sinotfound_error(_t);
            }
            interpretation = si->interpretation;
            if (si->type == SI_INT_TYPE_JAVA_METHOD) {
                tmp = "%s(%x.charAt(i))";
            } else {
                tmp = "%x.charAt(i) == %s";
            }
        }        
        // char *_target = strrep(tmp, "%s", si->interpretation);   
        char *_target = strrep(tmp, "%s", interpretation);        
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
    } else if (_d1_direct_value == TRUE || is_primitive(d1) == TRUE) {
        return array_equals_primitive_1_var_1_direct(d2, d1);
    } else if (_d2_direct_value == TRUE || is_primitive(d2) == TRUE) {
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
    char num[10];
    _itoa(c, num);
    result = combine_strings(5, result, " && ", var, ".length == ", num);
    return result;
}

char * array_equals_2_vars(char *var1, char *var2) {
    char *tmp = "\\forall int i; 0 <= i < %x.length; %x[i] == %y[i]";
    char *a = strrep(tmp, "%x", var1);
    char *result = strrep(a, "%y", var2);
    free(a);
    return result;
}

char * string_charArray_equal(char *d1, char *d2) {
    int _d1_direct_value = ssearch(d1, ",");
    int _d2_direct_value = ssearch(d2, ",");
    
    char *string, *charArray;
    if (!_d1_direct_value) {
        string = d1;
        charArray = d2;
    } else {
        charArray = d1;
        string = d2;
    }

    char *pos;
    char *_t = strtok_r(charArray, ",", &pos), *connective = NULL;
    if (strcmp(_t, "or") == 0) {
        connective = " || ";
    } else {
        connective = " && ";
    }
    char *head = "\\forall int i; 0 <= i < %x.length(); ";
    char *result = strrep(head, "%x", string);    
    _t = strtok_r(NULL, ",", &pos);
    char *end = NULL;
    while (_t != NULL) {
        char *tmp = "%x.charAt(i) == %s";
        char *_target = strrep(tmp, "%s", _t);        
        tmp = (char *)strdup(_target);
        free(_target);
        _target = strrep(tmp, "%x", string);
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

char * contain_only_template(char *d1, char *d2, struct datatype *dt1, struct datatype *dt2) {
    int _d1_direct_value = ssearch(d1, "__TEMPLATE__");
    int _d2_direct_value = ssearch(d2, "__TEMPLATE__");
    struct datatype *dt_direct, *dt_template;
    
    char *direct, *template;
    if (!_d1_direct_value) {
        direct = d1;
        template = d2;
        dt_direct = dt1;
        dt_template = dt2;
    } else {
        template = d1;
        direct = d2;
        dt_direct = dt2;
        dt_template = dt1;
    }

    // struct si *si = searchqueue(silist, template, __match_si_with_symbol_only__);
    struct queue *siq = q_searchqueue(silist, template, __match_si_with_symbol_only__);
    if (siq == NULL) {
        sinotfound_error(template);
    }
    struct si *si = searchqueue(siq, dt_direct, __match_si_with_template_datatype_only__);
    if (si == NULL) {
        sinotfound_error(template);
    }
    char *tmp = combine_strings(3, "(", ((struct si_arg *)gqueue(si->args, 0))->symbol, ")");
    char *result = strrep(si->interpretation, tmp, direct);
    return result;
}

// char * string_consist_only(char *var1, char *var2) {
//     return result;
// }

// char * string_contain(char *var1, char *var2) {
//     return result;
// }