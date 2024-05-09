package g0001_0100.s0020_valid_parentheses;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #String #Stack
// #Data_Structure_I_Day_9_Stack_Queue #Udemy_Strings #Big_O_Time_O(n)_Space_O(n)
// #2023_08_09_Time_2_ms_(90.49%)_Space_40.1_MB_(98.14%)

import java.util.Stack;

@SuppressWarnings("java:S1149")
public class Solution {
//@ requires(*If the input string `s` is valid according to the following conditions:*);
//@ requires(*    - Open brackets must be closed by the same type of brackets.*);
//@ requires(*    - Open brackets must be closed in the correct order.*);
//@ requires(*The length of the input string `s` is greater than or equal to 1 and is less than or equal to 10000.*);
//@ ensures(*If the input string `s` contains only the characters `'('`, `')'`, `'{'`, `'}'`, `'['`, and `']'`, the boolean result is equal to the true literal.*);
//@ ensures(*  the boolean result is equal to the true literal.*);
//@ ensures(*If the input string `s` is not valid according to the above conditions, the boolean result is equal to the false literal.*);
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        //@ loop_invariant 0 <= i <= s.length();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}