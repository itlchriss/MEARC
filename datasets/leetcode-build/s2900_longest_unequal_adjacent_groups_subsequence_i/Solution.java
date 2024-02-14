package g2801_2900.s2900_longest_unequal_adjacent_groups_subsequence_i;

// #Medium #Array #String #Dynamic_Programming #Greedy
// #2023_12_20_Time_1_ms_(100.00%)_Space_45_MB_(7.16%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S1172")
public class Solution {
	//@ requires(*The input integer `n` must be greater than or equal to 1.*);
	//@ requires(*The length of the `words` array must be equal to `n`.*);
	//@ requires(*The length of the `groups` array must be equal to `n`.*);
	//@ requires(*Each element in the `groups` array must be either 0 or 1.*);
	//@ requires(*The `words` array must consist of distinct strings.*);
	//@ requires(*Each string in the `words` array must consist of lowercase English letters.*);
	//@ ensures(*The method should return a `List<String>` containing the words corresponding to the indices in the selected subsequence.*);
	//@ ensures(*The length of the returned list should be equal to the length of the longest subsequence of indices that satisfies the condition.*);
	//@ ensures(*If there are multiple valid subsequences, the method can return any of them.*);
    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        List<String> ans = new ArrayList<>();
        ans.add(words[0]);
        int prev = groups[0];
        for (int i = 1; i < groups.length; i++) {
            if (prev != groups[i]) {
                ans.add(words[i]);
                prev = groups[i];
            }
        }
        return ans;
    }
}