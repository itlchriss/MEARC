package g0801_0900.s0856_score_of_parentheses;

// #Medium #String #Stack #2022_03_27_Time_1_ms_(67.71%)_Space_42.7_MB_(5.56%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given a balanced parentheses string `s`, return _the **score** of the string_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int scoreOfParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(-1);
            } else {
                int curr = 0;
                while (stack.peek() != -1) {
                    curr += stack.pop();
                }
                stack.pop();
                stack.push(curr == 0 ? 1 : curr * 2);
            }
        }
        int score = 0;
        while (!stack.isEmpty()) {
            score += stack.pop();
        }
        return score;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
