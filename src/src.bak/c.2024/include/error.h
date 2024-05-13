#ifndef _ERROR_H
#define _ERROR_H

void semantic_error(char *, char *);
void syntax_error(char *, char *);
void siconflict_error(char *);
void sinotfound_error(char *);
void sideclare_error(char *);
void sisyntax_error(char *, char *, char *);
void internal_error(char *);
#endif
