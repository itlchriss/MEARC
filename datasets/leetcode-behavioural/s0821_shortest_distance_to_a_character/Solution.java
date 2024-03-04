package g0801_0900.s0821_shortest_distance_to_a_character;

// #Easy #Array #String #Two_Pointers #2022_03_23_Time_3_ms_(42.73%)_Space_42.6_MB_(67.20%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The character `c` is a lowercase English letter.*);
//@ ensures(*The character `c` occurs at least once in `s`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The length of the output array `answer` is equal to the length of the input string `s`.*);
//@ ensures(*Each element `answer[i]` in the output array represents the distance from index `i` to the closest occurrence of character `c` in `s`.*);
//@ ensures(*The distance between two indices `i` and `j` is calculated using the absolute value function `abs(i - j)`.*);
    public int[] shortestToChar(String s, char c) {
        int[] result = new int[s.length()];
        Arrays.fill(result, Integer.MAX_VALUE);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                result[i] = 0;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (result[i] != 0) {
                int j = i - 1;
                while (j >= 0 && result[j] != 0) {
                    j--;
                }
                if (j >= 0) {
                    result[i] = i - j;
                }
                j = i + 1;
                while (j < s.length() && result[j] != 0) {
                    j++;
                }
                if (j < s.length()) {
                    result[i] = Math.min(result[i], j - i);
                }
            }
        }
        return result;
    }
}