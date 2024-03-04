package g1701_1800.s1770_maximum_score_from_performing_multiplication_operations;

// #Medium #Array #Dynamic_Programming #2022_04_30_Time_31_ms_(92.41%)_Space_53.2_MB_(88.74%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of `nums` and `multipliers` arrays should be greater than or equal to - The length of `nums` array should be greater than or equal to the length of `multipliers` array.*);
//@ ensures(*The length of `multipliers` array should be greater than or equal to - The values in `nums` and `multipliers` arrays should be within the range of -1000 to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer value representing the maximum score after performing `m` operations.*);
//@ ensures(*The `nums` array should be modified after performing the operations.*);
    public int maximumScore(int[] nums, int[] mult) {
        int n = nums.length;
        int m = mult.length;
        int row = m;
        int[] dp = new int[m];
        int[] prev = new int[m + 1];
        while (--row >= 0) {
            for (int i = 0; i <= row; ++i) {
                dp[i] =
                        Math.max(
                                prev[i] + mult[row] * nums[n - row + i - 1],
                                prev[i + 1] + mult[row] * nums[i]);
            }
            prev = dp;
        }
        return dp[0];
    }
}