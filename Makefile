UNAME_S := $(shell uname -s)
CC		=	cc
FCC		=	flex
BCC		=	bison
# SRC		=	./src/c
SRC		=   ./src/c.2024
BUILD	=	./build
BIN		=   ./bin
INCL	=	$(SRC)/include
CFLAGS	= 	-g -Wall -ansi -pedantic -I$(INCL) -std=c99 -D_POSIX_C_SOURCE=200809L $(LOCINCL)
OBJS	=	parser.o lex.o ast.o si.o cst.o util.o cg.o  main.o event.o alias.o error.o
DEBUG   ?=      1
LEXDEBUG ?=     0
DSTDEBUG ?=		0
LDFLAGS = 
LOCINCL =   -I/usr/local/include 
LOCLINK =   -L/usr/local/lib
ifeq ($(UNAME_S),Darwin)
	LOCINCL = -I/opt/homebrew/Cellar/libyaml/0.2.5/include
	LOCLINK = -L/opt/homebrew/Cellar/libyaml/0.2.5/lib
endif

ifeq ($(ANALYSIS), 1)
	CFLAGS += -fprofile-arcs -ftest-coverage
	LDFLAGS += -p -g -fprofile-arcs -ftest-coverage
endif

ifeq ($(DEBUG), 0)
   CFLAGS = -g -Wall -ansi -pedantic -I$(INCL) -std=c99 -D_POSIX_C_SOURCE=200809L $(LOCINCL)
else
# ifeq ($(DEBUG), 1)
#   CFLAGS += -DDEBUG -fsanitize=address
   CFLAGS += -DDEBUG
# endif

	ifeq ($(LEXDEBUG), 1)
	CFLAGS += -DLEXDEBUG
	endif

	ifeq ($(PARDEBUG), 1)
		CFLAGS += -DPARDEBUG
	endif

	ifeq ($(SIDEBUG), 1)
		CFLAGS += -DSIDEBUG
	endif

	ifeq ($(ASTDEBUG), 1)
		CFLAGS += -DASTDEBUG
	endif

	ifeq ($(CSTDEBUG), 1)
		CFLAGS += -DCSTDEBUG
	endif

	ifeq ($(INFO), 1)
		CFLAGS += -DINFO
	endif

	ifeq ($(MEMDEBUG), 1)
		CFLAGS += -DMEMDEBUG
	endif
endif


.PHONY: directories

all: directories main

directories: ${BUILD} ${BIN}

${BUILD}:
	mkdir -p $(BUILD)

${BIN}:
	mkdir -p $(BIN)

main: $(OBJS) event.o
	$(CC) $(CFLAGS) $(addprefix $(BUILD)/, $(OBJS)) -o $(BIN)/main -ll $(LOCLINK) -lyaml

lex-only: lex
	$(CC) $(BUILD)/lex.yy.c -ll -o $(BIN)/lex.o

lex: 
	$(FCC) -o $(BUILD)/lex.yy.c $(SRC)/lex.l

lex.o:	lex
	$(CC) $(CFLAGS) -c $(BUILD)/lex.yy.c -o $(BUILD)/lex.o

main.o: $(SRC)/main.c
	$(CC) $(CFLAGS) -c -o $(BUILD)/main.o $<

parser.o:	parser.c
		$(CC) $(CFLAGS) -c $(BUILD)/parser.c -o $(BUILD)/parser.o

parser.c:	
		$(BCC) -d -v $(SRC)/parser.y
		cp parser.tab.c $(BUILD)/parser.c
		cp parser.tab.h $(BUILD)/tok.h
		mv parser.tab.h $(BUILD)
	
ast.o : $(SRC)/ast.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/ast.o $<

util.o : $(SRC)/util.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/util.o $<

sst.o  : $(SRC)/sst.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/sst.o $<

cst.o  : $(SRC)/cst.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/cst.o $<

event.o  : $(SRC)/event.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/event.o $<

alias.o  : $(SRC)/alias.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/alias.o $<

opt.o  : $(SRC)/opt.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/opt.o $<

cg.o  : $(SRC)/cg.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/cg.o $<		

dst.o  : $(SRC)/dst.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/dst.o $<		

si.o	: $(SRC)/si.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/si.o $<		

error.o	: $(SRC)/error.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/error.o $<		

lex.o parser.o sym_table.o		:	$(INCL)/core.h
parser.only						:	$(INCL)/ast.h
parser.o						:       $(BUILD)/tok.h $(INCL)/event.h
lex.o							: 	$(BUILD)/tok.h
ast.o							:   $(INCL)/ast.h $(INCL)/cst.h
main.o							:   $(INCL)/si.h $(INCL)/event.h $(INCL)/alias.h
util.o							:   $(INCL)/util.h
cst.o							:   $(INCL)/util.h 
cg.o							:   $(INCL)/util.h $(INCL)/cg.h
alias.o							:	$(INCL)/alias.h
si.o							:   $(INCL)/si.h 
event.o							: 	$(INCL)/event.h
clean:
	rm -rf $(BUILD)/*
	rm ./parser.output
	rm ./bin/*
	rm ./parser.tab.c
	
