# -*- coding: utf-8 -*-

from nltk.sem.logic import *
import unicodedata

from nltk.internals import Counter
# from .logic_parser import lexpr
from logic_parser import lexpr
import re

_counter = Counter()

# Term t ::=
#   x,y,z         <IndividualVariableExpression>
#   e             <EventVariableExpression>
#   f(t)          <ApplicationExpression> where f in {Subj,Obj,Dat,...}
#   john          <ConstantExpression>
#   \x.student(x) <LambdaExpression>

# Formula A ::=
#   F(t)         <ApplicationExpression>
#   F(t,u)       <ApplicationExpression>
#   t = u        <EqualityExpression>
#   A & B        <AndExpression>
#   A | B        <OrExpression>
#   A -> B       <ImpExpression>
#   -A           <NegatedExpression>
#   exists x.A   <ExistsExpression>
#   forall x.A   <AllExpression>
#   True         <ConstantExpression>

def get_atomic_formulas(expression):
    if isinstance(expression, ApplicationExpression):
        return set([expression])
    elif isinstance(expression, EqualityExpression):
        return set([expression])
    elif isinstance(expression, IndividualVariableExpression):
        return set([expression])
    elif isinstance(expression, EventVariableExpression):
        return set([expression])
    elif isinstance(expression, FunctionVariableExpression):
        return set([expression])
    elif isinstance(expression, ConstantExpression):
        return set([expression])
    else:
        return expression.visit(get_atomic_formulas,
                       lambda parts: reduce(operator.or_, parts, set()))

def new_variable(var):
    var = VariableExpression(var)
    # isinstance(var,EventVariableExpression) must come first
    if isinstance(var, EventVariableExpression):
        prefix = 'e0'
    elif isinstance(var, IndividualVariableExpression):
        prefix = 'x0'
    elif isinstance(var, FunctionVariableExpression):
        prefix = 'F0'
    else:
        prefix = 'z0'
    v = Variable("%s%s" % (prefix, _counter.get()))
    return v

true_preds = ['True', 'TrueP']
true_q = "(exists|all)\s+e\d*\.(True|TrueP)"

def is_true_pred(expression):
    return expression in true_preds or re.search(true_q, expression)

def remove_true(expression):
    # Remove True and TrueP
    if isinstance(expression, ApplicationExpression):
        function = remove_true(expression.function)
        argument = remove_true(expression.argument)
        expr = ApplicationExpression(function, argument)
    elif isinstance(expression, EqualityExpression):
        left = remove_true(expression.first)
        right = remove_true(expression.second)
        expr = EqualityExpression(left, right)
    elif isinstance(expression, AndExpression):
        # True & A <=> A & True <=> A
        left = expression.first
        right = expression.second
        left_str = str(left)
        right_str = str(right)
        if left_str in true_preds:
            expr = remove_true(right)
        elif right_str in true_preds:
            expr = remove_true(left)
        else:
            left = remove_true(left)
            right = remove_true(right)
            expr = AndExpression(left, right)
    elif isinstance(expression, OrExpression):
        # True or A <=> A or True <=> True
        left = expression.first
        right = expression.second
        left_str = str(left)
        right_str = str(right)
        if left_str in true_preds:
            expr = remove_true(left)
        elif right_str in true_preds:
            expr = remove_true(right)
        else:
            left = remove_true(left)
            right = remove_true(right)
            expr = OrExpression(left, right)
    elif isinstance(expression, ImpExpression):
        # True -> A <=> A
        # A -> True <=> A
        left = expression.first
        right = expression.second
        left_str = str(left)
        right_str = str(right).strip()  
        if left_str in true_preds:
            expr = remove_true(right)
        elif is_true_pred(right_str):
            expr = remove_true(left)
        else:
            left = remove_true(expression.first)
            right = remove_true(expression.second)
            expr = ImpExpression(left, right)
    elif isinstance(expression, NegatedExpression):
        term = remove_true(expression.term)
        expr = NegatedExpression(term)
    elif isinstance(expression, ExistsExpression):
        variable = expression.variable
        term = expression.term
        newvar = new_variable(variable)
        newvar_expr = VariableExpression(newvar)
        term = term.replace(variable, newvar_expr)
        term = remove_true(term)
        expr = ExistsExpression(newvar, term)
    elif isinstance(expression, AllExpression):
        variable = expression.variable
        term = expression.term
        newvar = new_variable(variable)
        newvar_expr = VariableExpression(newvar)
        term = term.replace(variable, newvar_expr)
        term = remove_true(term)
        expr = AllExpression(newvar, term)
    elif isinstance(expression, LambdaExpression):
        variable = expression.variable
        term = expression.term
        newvar = new_variable(variable)
        newvar_expr = VariableExpression(newvar)
        term = term.replace(variable, newvar_expr)
        term = remove_true(term)
        expr = LambdaExpression(newvar, term)
    # elif isinstance(expression, IndividualVariableExpression):
    #     expr = expression
    # elif isinstance(expression, EventVariableExpression):
    #     expr = expression
    # elif isinstance(expression, FunctionVariableExpression):
    #     expr = expression
    # elif isinstance(expression, ConstantExpression):
    #     expr = expression
    else:
        expr = expression

    return expr


def rename_variable(expression):
    # Rename bound variables so that no variable with the same name is bound
    # by two different quantifiers in different parts of a formula
    if isinstance(expression, ApplicationExpression):
        function = rename_variable(expression.function)
        argument = rename_variable(expression.argument)
        expr = ApplicationExpression(function, argument)
    elif isinstance(expression, EqualityExpression):
        left = rename_variable(expression.first)
        right = rename_variable(expression.second)
        expr = EqualityExpression(left, right)
    elif isinstance(expression, AndExpression):
        left = rename_variable(expression.first)
        right = rename_variable(expression.second)
        expr = AndExpression(left, right)
    elif isinstance(expression, OrExpression):
        left = rename_variable(expression.first)
        right = rename_variable(expression.second)
        expr = OrExpression(left, right)
    elif isinstance(expression, ImpExpression):
        left = rename_variable(expression.first)
        right = rename_variable(expression.second)
        expr = ImpExpression(left, right)
    elif isinstance(expression, NegatedExpression):
        term = rename_variable(expression.term)
        expr = NegatedExpression(term)
    elif isinstance(expression, ExistsExpression):
        variable = expression.variable
        term = expression.term
        newvar = new_variable(variable)
        newvar_expr = VariableExpression(newvar)
        term = term.replace(variable, newvar_expr)
        term = rename_variable(term)
        expr = ExistsExpression(newvar, term)
    elif isinstance(expression, AllExpression):
        variable = expression.variable
        term = expression.term
        newvar = new_variable(variable)
        newvar_expr = VariableExpression(newvar)
        term = term.replace(variable, newvar_expr)
        term = rename_variable(term)
        expr = AllExpression(newvar, term)
    elif isinstance(expression, LambdaExpression):
        variable = expression.variable
        term = expression.term
        newvar = new_variable(variable)
        newvar_expr = VariableExpression(newvar)
        term = term.replace(variable, newvar_expr)
        term = rename_variable(term)
        expr = LambdaExpression(newvar, term)
    # elif isinstance(expression, IndividualVariableExpression):
    #     expr = expression
    # elif isinstance(expression, EventVariableExpression):
    #     expr = expression
    # elif isinstance(expression, FunctionVariableExpression):
    #     expr = expression
    # elif isinstance(expression, ConstantExpression):
    #     expr = expression
    else:
        expr = expression
    return expr

def convert_to_prenex(expression):
    # Convert a formula to one where all existential quantifers come first.
    expression = remove_true(expression)
    expression = rename_variable(expression)
    prenex_form = prenex_expr(expression)
    return prenex_form

def prenex_expr(expression):
    if isinstance(expression, ApplicationExpression):
        expr = prenex_application_expr(expression)
    elif isinstance(expression, EqualityExpression):
        expr = prenex_equality_expr(expression)
    elif isinstance(expression, AndExpression):
        expr = prenex_and_expr(expression)
    elif isinstance(expression, OrExpression):
        expr = prenex_or_expr(expression)
    elif isinstance(expression, ImpExpression):
        expr = prenex_imp_expr(expression)
    elif isinstance(expression, NegatedExpression):
        expr = prenex_not_expr(expression)
    elif isinstance(expression, ExistsExpression):
        expr = prenex_exists_expr(expression)
    elif isinstance(expression, AllExpression):
        expr = prenex_all_expr(expression)
    elif isinstance(expression, LambdaExpression):
        expr = prenex_lambda_expr(expression)
    elif isinstance(expression, IndividualVariableExpression):
        expr = expression
    elif isinstance(expression, EventVariableExpression):
        expr = expression
    elif isinstance(expression, ConstantExpression):
        lexstr = normalize_symbols('%s' % expression)
        expr = ConstantExpression(Variable(lexstr))
    # elif isinstance(expression, Variable):
    #     expr = expression
    else:
        expr = expression
    return expr

def prenex_application_expr(expression):
    function = prenex_expr(expression.function)
    argument = prenex_expr(expression.argument)
    expr = ApplicationExpression(function, argument)
    return expr

def prenex_equality_expr(expression):
    left = prenex_expr(expression.first)
    right = prenex_expr(expression.second)
    expr = EqualityExpression(left, right)
    return expr

def prenex_and_expr(expression):
    left = prenex_expr(expression.first)
    right = prenex_expr(expression.second)
    # [(exists x. L) & R] = exists x. [L & R]
    if isinstance(left, ExistsExpression):
        left_variable = left.variable
        left_term = left.term
        if not left_variable in right.free():
            body = prenex_expr(AndExpression(left_term, right))
            expr = ExistsExpression(left_variable, body)
        else:
            newvar = unique_variable()
            var = VariableExpression(newvar)
            left_term = left_term.replace(left_variable, var)
            body = prenex_expr(AndExpression(left_term, right))
            expr = ExistsExpression(newvar, body)
    # [L & (exists x. R)] = exists x. [L & R]
    elif isinstance(right, ExistsExpression):
        right_variable = right.variable
        right_term = right.term
        if not right_variable in left.free():
            body = prenex_expr(AndExpression(left, right_term))
            expr = ExistsExpression(right_variable, body)
        else:
            newvar = unique_variable()
            var = VariableExpression(newvar)
            right_term = right_term.replace(right_variable, var)
            body = prenex_expr(AndExpression(left, right_term))
            expr = ExistsExpression(newvar, body)
    else:
        expr = AndExpression(left, right)
    return expr

def prenex_or_expr(expression):
    left = prenex_expr(expression.first)
    right = prenex_expr(expression.second)
    expr = OrExpression(left, right)
    return expr

def prenex_imp_expr(expression):
    left = prenex_expr(expression.first)
    right = prenex_expr(expression.second)
    expr = ImpExpression(left, right)
    return expr

def prenex_not_expr(expression):
    term = prenex_expr(expression.term)
    expr = NegatedExpression(term)
    return expr

def prenex_exists_expr(expression):
    variable = expression.variable
    term = prenex_expr(expression.term)
    expr = ExistsExpression(variable, term)
    return expr

def prenex_all_expr(expression):
    variable = expression.variable
    term = prenex_expr(expression.term)
    expr = AllExpression(variable, term)
    return expr

def prenex_lambda_expr(expression):
    variable = expression.variable
    term = prenex_expr(expression.term)
    expr = LambdaExpression(variable, term)
    return expr

def normalize_symbols(expression):
  expression = expression.replace("’","").\
               replace("_","_").\
               replace("（","BracketLeft").\
               replace("）","BracketRight")
  expression = unicodedata.normalize('NFKC', expression)
  return expression


def demo(function):
    for formula in test:
        answer = function(formula)
        print('Formula: {0}'.format(formula))
        print('Prediction: {0}\n'.format(answer))
        
def normalisation(rawmr):
    # return removeBrackets(str(remove_true(lexpr(rawmr))))
    mr = removeParentheses(str(remove_true(lexpr(rawmr))))
    return mr

def __get_parentheses_matches__(text):
    istart = []  # stack of indices of opening parentheses
    d = {}

    for i, c in enumerate(text):
        if c == '(':
            istart.append(i)
        if c == ')':
            try:
                d[istart.pop()] = i
            except IndexError:
                print('Too many closing parentheses')
    if istart:  # check if stack is empty afterwards
        print('Too many opening parentheses')
    return d

 
def removeParentheses(Exp):
    okay = False        
    
    while not okay:
        # print(d)        
        d = __get_parentheses_matches__(Exp)
        if 0 in d.keys() and d[0] == len(Exp) - 1:
            Exp = Exp[1:-1]
        else:
            okay = True
    return Exp

if __name__ == "__main__":   
    import sys
    with open(sys.argv[1], 'r') as fp:
        data = fp.read()
    data = normalisation(data.strip())
    with open(sys.argv[1], 'w') as fp:
        fp.write(data)
    