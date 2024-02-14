package g1101_1200.s1140_stone_game_ii;

// #Medium #Array #Dynamic_Programming #Math #Game_Theory
// #2023_06_01_Time_9_ms_(43.08%)_Space_43_MB_(39.09%)

import java.util.Arrays;

public class Solution {
    private int[][] dp = new int[105][105];

    private int help(int i, int m, int[] p) {
        if (i >= p.length) {
            dp[i][m] = 0;
            return 0;
        }
        if (dp[i][m] != -1) {
            return dp[i][m];
        }
        int ans = Integer.MIN_VALUE;
        int total = 0;
        for (int j = 0; j < 2 * m; j++) {
            if (i + j < p.length) {
                total += p[i + j];
                ans = Math.max(ans, total - help(i + j + 1, Math.max(m, j + 1), p));
            }
        }
        dp[i][m] = ans;
        return ans;
    }
	//@ requires(*The input array `piles` is not null.*);
	//@ requires(*The length of the input array `piles` is greater than or equal to 1.*);
	//@ requires(*The length of the input array `piles` is less than or equal to 100.*);
	//@ requires(*Each element in the input array `piles` is a positive integer.*);
	//@ requires(*The value of `M` is initially set to 1.*);
	//@ ensures(*The method returns an integer representing the maximum number of stones Alice can get.*);
	//@ ensures(*The maximum number of stones Alice can get is greater than or equal to 0.*);
	//@ ensures(*The maximum number of stones Alice can get is less than or equal to the sum of all elements in the input array `piles`.*);
	//@ ensures(*The value of `M` is updated to the maximum value of `M` and `X` on each player's turn.*);
	//@ ensures(*The game continues until all the stones have been taken.*);

    public int stoneGameII(int[] piles) {
        int sum = 0;
        for (int[] arr1 : dp) {
            Arrays.fill(arr1, -1);
        }
        for (int z : piles) {
            sum += z;
        }
        return (sum + help(0, 1, piles)) / 2;
    }
}