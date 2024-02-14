package g0901_1000.s0921_minimum_add_to_make_parentheses_valid;

// #Medium #String #Greedy #Stack #2022_03_29_Time_2_ms_(33.69%)_Space_42.2_MB_(39.63%)

import java.util.ArrayDeque;
import java.util.Deque;

@SuppressWarnings("java:S1149")
public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is between 1 and - Each character in the input string `s` is either '(' or ')'.*);
	//@ ensures(*The output is an integer representing the minimum number of moves required to make the input string `s` valid.*);
    public int minAddToMakeValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            } else {
                stack.push(c);
            }
        }
        return stack.size();
    }
}