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
<<<<<<< HEAD
=======
//@ ensures(*Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get. **Explanation:** If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 piles in total. If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's larger.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03

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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
