package g0001_0100.s0020_valid_parentheses;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #String #Stack
// #Data_Structure_I_Day_9_Stack_Queue #Udemy_Strings #Big_O_Time_O(n)_Space_O(n)
// #2023_08_09_Time_2_ms_(90.49%)_Space_40.1_MB_(98.14%)

import java.util.Stack;

@SuppressWarnings("java:S1149")
public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
