package g2501_2600.s2552_count_increasing_quadruplets;

// #Hard #Array #Dynamic_Programming #Prefix_Sum #Enumeration #Binary_Indexed_Tree
// #2023_08_18_Time_48_ms_(97.29%)_Space_43_MB_(93.41%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of `nums` is at least 4.*);
//@ ensures(*All integers in `nums` are unique.*);
//@ ensures(*The integers in `nums` are in the range from 1 to the length of `nums`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a long value representing the number of increasing quadruplets in the input array `nums`.*);
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        long[] dp = new long[n];
        Arrays.fill(dp, 0);
        long ret = 0;
        for (int i = 1; i < n; i++) {
            int choice = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    choice++;
                    ret += dp[j];
                } else if (nums[i] < nums[j]) {
                    dp[j] += choice;
                }
            }
        }
        return ret;
    }
}