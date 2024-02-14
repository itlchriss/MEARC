package g0101_0200.s0150_evaluate_reverse_polish_notation;

// #Medium #Top_Interview_Questions #Array #Math #Stack #Programming_Skills_II_Day_3
// #2022_06_24_Time_9_ms_(51.23%)_Space_44.1_MB_(56.86%)

import java.util.Stack;

@SuppressWarnings("java:S1149")
public class Solution {
	//@ requires(*1. The input `tokens` is not null.*);
	//@ requires(*2. The length of `tokens` is at least 1 and at most 10^4.*);
	//@ requires(*3. Each element in `tokens` is either an operator ("+", "-", "*", "/") or an integer in the range [-200, 200].*);
	//@ ensures(*1. The method returns an integer value.*);
	//@ ensures(*2. The returned value is the result of evaluating the arithmetic expression in Reverse Polish Notation represented by the input `tokens`.*);
	//@ ensures(*3. The returned value is the correct evaluation of the expression, considering the valid operators and operand types.*);
	//@ ensures(*4. The returned value is calculated by performing the arithmetic operations in the correct order, following the Reverse Polish Notation rules.*);
	//@ ensures(*5. The returned value is the correct result, even when division is performed between two integers (truncating toward zero).*);
	//@ ensures(*6. The returned value is the correct result, even when there are multiple levels of nested expressions.*);
	//@ ensures(*7. The returned value is the correct result, even when there are negative numbers involved in the expression.*);
    public int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        for (String token : tokens) {
            if (!Character.isDigit(token.charAt(token.length() - 1))) {
                st.push(eval(st.pop(), st.pop(), token));
            } else {
                st.push(Integer.parseInt(token));
            }
        }
        return st.pop();
    }

    private int eval(int second, int first, String operator) {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            default:
                return first / second;
        }
    }
}