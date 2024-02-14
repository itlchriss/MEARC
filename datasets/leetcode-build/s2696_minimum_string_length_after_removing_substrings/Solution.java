package g2601_2700.s2696_minimum_string_length_after_removing_substrings;

// #Easy #String #Stack #Simulation #2023_09_13_Time_44_ms_(97.86%)_Space_44.7_MB_(50.13%)

import java.util.ArrayDeque;

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` consists only of uppercase English letters.*);
	//@ ensures(*The output is an integer representing the minimum possible length of the resulting string after removing substrings.*);
	//@ ensures(*The resulting string is obtained by removing any occurrence of the substrings "AB" or "CD" from the input string `s`.*);
	//@ ensures(*The resulting string may contain new occurrences of the substrings "AB" or "CD" after removing the initial occurrences.*);
	//@ ensures(*The length of the resulting string is minimized, meaning there are no further substrings "AB" or "CD" that can be removed to reduce the length.*);
    public int minLength(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty()
                    && ((c == 'B' && stack.getLast() == 'A')
                            || (c == 'D' && stack.getLast() == 'C'))) {
                stack.removeLast();
            } else {
                stack.addLast(c);
            }
        }
        return stack.size();
    }
}