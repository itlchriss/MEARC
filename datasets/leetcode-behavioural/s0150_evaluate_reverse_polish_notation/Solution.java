package g0101_0200.s0150_evaluate_reverse_polish_notation;

// #Medium #Top_Interview_Questions #Array #Math #Stack #Programming_Skills_II_Day_3
// #2022_06_24_Time_9_ms_(51.23%)_Space_44.1_MB_(56.86%)

import java.util.Stack;

@SuppressWarnings("java:S1149")
public class Solution {
//@ ensures(*The string array parameter `tokens` must not be null.*);
//@ ensures(*The integer result is the evaluation of the arithmetic expression in Reverse Polish Notation represented by the string array parameter `tokens`.*);
//@ ensures(*The division operation between two integers should truncate toward zero.*);
//@ ensures(*The arithmetic expression in Reverse Polish Notation represented by the string array parameter `tokens` is always valid and will not result in division by zero.*);
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