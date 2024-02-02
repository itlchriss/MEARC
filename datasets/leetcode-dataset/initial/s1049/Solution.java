package g1001_1100.s1049_last_stone_weight_ii;

// #Medium #Array #Dynamic_Programming #2022_02_28_Time_2_ms_(95.98%)_Space_41.8_MB_(31.51%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the smallest possible weight of the left stone_. If there are no stones left, return `0`.
Return _the smallest possible weight of the left stone_. If there are no stones left, return `0`.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int lastStoneWeightII(int[] stones) {
        // dp[i][j] i is the index of stones, j is the current weight
        // goal is to find max closest to half and use it to get the diff
        // 0-1 knapsack problem
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int half = sum / 2;
        int[] dp = new int[half + 1];
        for (int cur : stones) {
            for (int j = half; j >= cur; j--) {
                dp[j] = Math.max(dp[j], dp[j - cur] + cur);
            }
        }
        return sum - dp[half] * 2;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
