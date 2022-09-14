#ifndef JML_H
#define JML_H

static char j_opers[] = { "<",">", "=", ">=", "<=" };

enum operator {
    Lesser = 0,
    Greater,
    Equal,
    GreaterOrEqual,
    LesserOrEqual
};

enum jml_type {
    JmlOp,
    JmlVar,
    JmlKw
};

struct jml_node {
    jml_type type;

};

char * conv_operator(operator oper);
#endif