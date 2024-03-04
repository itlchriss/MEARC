package g1001_1100.s1049_last_stone_weight_ii;

// #Medium #Array #Dynamic_Programming #2022_02_28_Time_2_ms_(95.98%)_Space_41.8_MB_(31.51%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `stones` is not null.*);
//@ ensures(*The length of the input array `stones` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `stones` is a positive integer.*);
//@ ensures(*The maximum value of each element in the input array `stones` is 100.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer.*);
//@ ensures(*The return value is the smallest possible weight of the left stone after the game is played.*);
//@ ensures(*If there are no stones left after the game is played, the return value is 0.*);
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
}