package g1501_1600.s1520_maximum_number_of_non_overlapping_substrings;

// #Hard #String #Greedy #2022_04_09_Time_15_ms_(92.93%)_Space_43.4_MB_(91.92%)

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` is not empty.*);
	//@ requires(*The input string `s` contains only lowercase English letters.*);
	//@ ensures(*The output is a list of strings.*);
	//@ ensures(*The output list is not null.*);
	//@ ensures(*The output list is not empty.*);
	//@ ensures(*The output list contains only non-empty substrings of `s`.*);
	//@ ensures(*The substrings in the output list do not overlap.*);
	//@ ensures(*For any two substrings `s[i..j]` and `s[k..l]` in the output list, either `j < k` or `i > l` is true.*);
	//@ ensures(*For any substring `s[i..j]` in the output list, if it contains a certain character `c`, it must also contain all occurrences of `c`.*);
	//@ ensures(*The output list contains the maximum number of substrings that meet the above conditions.*);
	//@ ensures(*If there are multiple solutions with the same number of substrings, the output list is the one with minimum total length.*);
	//@ ensures(*The output list is in any order.*);
    public List<String> maxNumOfSubstrings(String s) {
        int[] lefts = new int[26];
        int[] rights = new int[26];
        Arrays.fill(lefts, -1);
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (lefts[idx] == -1) {
                lefts[idx] = i;
            }
            rights[idx] = i;
        }
        List<String> result = new ArrayList<>();
        Deque<int[]> stack = new ArrayDeque<>();
        int[] top = null;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (i == lefts[idx]) {
                if (top == null || rights[idx] < top[1]) {
                    top = new int[] {i, rights[idx]};
                    stack.offerFirst(top);
                } else if (rights[idx] > top[1]) {
                    top[1] = rights[idx];
                }
            } else if (top != null && lefts[idx] < top[0]) {
                int newEnd = rights[idx];
                while (top != null && top[0] > lefts[idx]) {
                    newEnd = Math.max(newEnd, top[1]);
                    stack.pollFirst();
                    top = stack.peekFirst();
                }
                if (top != null) {
                    top[1] = Math.max(newEnd, top[1]);
                }
            }
            if (top != null && i >= top[1]) {
                result.add(s.substring(top[0], top[1] + 1));
                stack.clear();
                top = null;
            }
        }
        return result;
    }
}