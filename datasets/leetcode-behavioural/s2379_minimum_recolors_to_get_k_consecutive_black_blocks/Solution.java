package g2301_2400.s2379_minimum_recolors_to_get_k_consecutive_black_blocks;

// #Easy #String #Sliding_Window #2022_08_23_Time_1_ms_(80.00%)_Space_42.2_MB_(20.00%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `blocks` must not be null.*);
//@ ensures(*The length of the input string `blocks` must be greater than or equal to `k`.*);
//@ ensures(*The input integer `k` must be greater than or equal to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of operations needed to achieve at least one occurrence of `k` consecutive black blocks.*);
    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length();
        int ans;
        int i;
        int cur = 0;
        for (i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'W') {
                cur++;
            }
        }
        ans = cur;
        for (i = k; i < n; i++) {
            if (blocks.charAt(i) == 'W') {
                cur++;
            }
            if (blocks.charAt(i - k) == 'W') {
                cur--;
            }
            ans = Math.min(ans, cur);
        }
        return ans;
    }
}