package g0001_0100.s0020_valid_parentheses;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #String #Stack
// #Data_Structure_I_Day_9_Stack_Queue #Udemy_Strings #Big_O_Time_O(n)_Space_O(n)
// #2023_08_09_Time_2_ms_(90.49%)_Space_40.1_MB_(98.14%)

import java.util.Stack;

@SuppressWarnings("java:S1149")
public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists only of the characters '(', ')', '{', '}', '[' and ']'.*);
//@ ensures(*If the boolean result is equal to the true literal, all open brackets in the string parameter `s` are closed by the same type of brackets and in the correct order.*);
//@ ensures(*If the boolean result is equal to the false literal, there exists at least one open bracket in the string parameter `s` that is not closed by the same type of bracket or not in the correct order.*);
//@ ensures(*If the string parameter `s` is equal to "()", the boolean result is equal to the true literal.*);
//@ ensures(*If the string parameter `s` is equal to "()[]{}", the boolean result is equal to the true literal.*);
//@ ensures(*If the string parameter `s` is equal to "(]", the boolean result is equal to the false literal.*);
//@ ensures(*If the string parameter `s` is equal to "([)]", the boolean result is equal to the false literal.*);
//@ ensures(*If the string parameter `s` is equal to "{[]}", the boolean result is equal to the true literal.*);
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