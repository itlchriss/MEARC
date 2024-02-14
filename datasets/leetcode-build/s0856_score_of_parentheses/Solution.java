package g0801_0900.s0856_score_of_parentheses;

// #Medium #String #Stack #2022_03_27_Time_1_ms_(67.71%)_Space_42.7_MB_(5.56%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is between 2 and 50.*);
	//@ requires(*The input string `s` consists of only '(' and ')' characters.*);
	//@ requires(*The input string `s` is a balanced parentheses string.*);
	//@ ensures(*The method returns an integer value representing the score of the input string `s`.*);
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
}