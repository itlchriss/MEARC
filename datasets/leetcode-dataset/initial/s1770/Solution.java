package g1701_1800.s1770_maximum_score_from_performing_multiplication_operations;

// #Medium #Array #Dynamic_Programming #2022_04_30_Time_31_ms_(92.41%)_Space_53.2_MB_(88.74%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the **maximum** score after performing_ `m` _operations._*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
