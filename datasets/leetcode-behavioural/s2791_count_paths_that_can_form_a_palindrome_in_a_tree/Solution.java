package g2701_2800.s2791_count_paths_that_can_form_a_palindrome_in_a_tree;

// #Hard #Dynamic_Programming #Depth_First_Search #Tree #Bit_Manipulation #Bitmask
// #2023_09_14_Time_97_ms_(99.78%)_Space_59_MB_(81.43%)

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private int getMap(List<Integer> parent, String s, int[] dp, int idx) {
        if (dp[idx] < 0) {
            dp[idx] = 0;
            dp[idx] = getMap(parent, s, dp, parent.get(idx)) ^ (1 << (s.charAt(idx) - 'a'));
        }
        return dp[idx];
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input list `parent` is not null.*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of `parent` is equal to the length of `s`.*);
//@ ensures(*The length of `parent` is greater than or equal to 1.*);
//@ ensures(*The first element of `parent` is -1.*);
//@ ensures(*All elements of `parent` are integers between 0 and n-1, inclusive.*);
//@ ensures(*The input string `s` consists of only lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a long value representing the number of pairs of nodes (u, v) such that u < v and the characters assigned to edges on the path from u to v can be rearranged to form a palindrome.*);

    public long countPalindromePaths(List<Integer> parent, String s) {
        int n = parent.size();
        int[] dp = new int[n];
        long ans = 0;
        Map<Integer, Integer> mapCount = new HashMap<>();
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int currMap = getMap(parent, s, dp, i);
            int evenCount = mapCount.getOrDefault(currMap, 0);
            mapCount.put(currMap, evenCount + 1);
        }
        for (Map.Entry<Integer, Integer> entry : mapCount.entrySet()) {
            int value = entry.getValue();
            ans += (long) value * (value - 1) / 2;
            for (int i = 0; i <= 25; i++) {
                int base = 1 << i;
                if ((entry.getKey() & base) > 0 && mapCount.containsKey(entry.getKey() ^ base)) {
                    ans += (long) value * mapCount.get(entry.getKey() ^ base);
                }
            }
        }
        return ans;
    }
}