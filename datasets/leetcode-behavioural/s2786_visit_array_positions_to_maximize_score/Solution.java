package g2701_2800.s2786_visit_array_positions_to_maximize_score;

// #Medium #Array #Dynamic_Programming #2023_09_15_Time_5_ms_(100.00%)_Space_54.5_MB_(99.95%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is at least 2.*);
//@ ensures(*The input integer `x` is positive.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is a long integer representing the maximum total score.*);
//@ ensures(*The return value is greater than or equal to the initial score `nums[0]`.*);
//@ ensures(*The return value is the sum of the scores of the visited positions minus the scores lost due to differing parities.*);
//@ ensures(*The return value is the maximum possible total score that can be obtained by visiting positions in the array according to the given rules.*);
    public long maxScore(int[] nums, int x) {
        long[] dp = {-x, -x};
        dp[nums[0] & 1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            long toggle = dp[nums[i] & 1 ^ 1] - x;
            long nottoggle = dp[nums[i] & 1];
            dp[nums[i] & 1] = Math.max(toggle, nottoggle) + nums[i];
        }
        return Math.max(dp[0], dp[1]);
    }
}