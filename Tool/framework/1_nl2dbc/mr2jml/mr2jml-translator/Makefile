CC		=	cc
FCC		=	flex
BCC		=	bison
SRC		=	./src/compiler
BUILD	=	./build
BIN		=   ./bin
INCL	=	$(SRC)/include
CFLAGS	= 	-g -Wall -ansi -pedantic -I$(INCL) -std=c99 -D_POSIX_C_SOURCE=200809L
OBJS	=	parser.o lex.o sst.o ast.o cst.o util.o cg.o dst.o sem.o h2jc.o
DEBUG   ?=      1

ifeq ($(DEBUG), 1)
#   CFLAGS += -DDEBUG -fsanitize=address
   CFLAGS += -DDEBUG
endif

.PHONY: directories

all: directories h2jc

directories: ${BUILD} ${BIN}

${BUILD}:
	mkdir -p $(BUILD)

${BIN}:
	mkdir -p $(BIN)

h2jc: $(OBJS)
	$(CC) $(CFLAGS) $(addprefix $(BUILD)/, $(OBJS)) -o $(BIN)/h2jc -ll

lex-only: lex
	$(CC) $(BUILD)/lex.yy.c -ll -o $(BIN)/lex.o

lex: 
	$(FCC) -o $(BUILD)/lex.yy.c $(SRC)/lex.l

lex.o:	lex
	$(CC) $(CFLAGS) -c $(BUILD)/lex.yy.c -o $(BUILD)/lex.o

h2jc.o: $(SRC)/h2jc.c
	$(CC) $(CFLAGS) -c -o $(BUILD)/h2jc.o $<

parser.o:	parser.c
		$(CC) $(CFLAGS) -c $(BUILD)/parser.c -o $(BUILD)/parser.o

parser.c:	
		$(BCC) -d -v $(SRC)/parser.y
		mv parser.tab.c $(BUILD)/parser.c
		mv parser.tab.h $(BUILD)/tok.h
	
ast.o : $(SRC)/ast.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/ast.o $<

util.o : $(SRC)/util.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/util.o $<

sst.o  : $(SRC)/sst.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/sst.o $<

cst.o  : $(SRC)/cst.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/cst.o $<

opt.o  : $(SRC)/opt.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/opt.o $<

cg.o  : $(SRC)/cg.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/cg.o $<		

dst.o  : $(SRC)/dst.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/dst.o $<		

sem.o  : $(SRC)/sem.c
		$(CC) $(CFLAGS) -c -o $(BUILD)/sem.o $<		

lex.o parser.o sym_table.o		:	$(INCL)/h2jc.h
parser.only						:	$(INCL)/ast.h
lex.o							: 	$(BUILD)/tok.h
ast.o							:   $(INCL)/ast.h $(INCL)/cst.h $(INCL)/sst.h
util.o							:   $(INCL)/util.h
sst.o							:   $(INCL)/util.h $(INCL)/sst.h
cst.o							:   $(INCL)/util.h $(INCL)/ast.h
cg.o							:   $(INCL)/util.h $(INCL)/cg.h $(INCL)/dst.h
sem.o							:   $(INCL)/ast.h $(INCL)/sem.h 
clean:
	rm $(BUILD)/*
	rm ./parser.output
	rm ./bin/*
	
