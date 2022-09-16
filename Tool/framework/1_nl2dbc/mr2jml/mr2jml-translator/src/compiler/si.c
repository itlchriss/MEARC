#include <string.h>
#include <stdlib.h>
#include "si.h"

struct si* newsi(char *data) {
    struct si *new = (struct si*)malloc(sizeof(struct si));
    return new;
}