package g1801_1900.s1872_stone_game_viii;

// #Hard #Array #Dynamic_Programming #Math #Prefix_Sum #Game_Theory
// #2022_05_11_Time_3_ms_(98.18%)_Space_50.9_MB_(98.18%)

public class Solution {
	//@ requires(*The input array `stones` must not be null.*);
	//@ requires(*The length of the input array `stones` must be greater than or equal to 2.*);
	//@ requires(*The values in the input array `stones` must be within the range of -10^4 to 10^4.*);
	//@ ensures(*The method returns an integer representing the score difference between Alice and Bob.*);
	//@ ensures(*The score difference is calculated by subtracting Bob's score from Alice's score.*);
	//@ ensures(*Alice's score is the sum of the values of the stones she removed.*);
	//@ ensures(*Bob's score is the sum of the values of the stones he removed.*);
	//@ ensures(*The game stops when only one stone is left in the row.*);
    public int stoneGameVIII(int[] stones) {
        if (stones == null || stones.length <= 1) {
            return 0;
        }
        int n = stones.length;
        for (int i = 1; i < n; i++) {
            stones[i] = stones[i - 1] + stones[i];
        }
        // presum stones[] is ready;
        // dp[n-2]
        int dp = stones[n - 1];
        // The game stops when only one stone is left in the row.
        for (int i = n - 3; i >= 0; i--) {
            dp = Math.max(stones[i + 1] - dp, dp);
        }
        return dp;
    }
}