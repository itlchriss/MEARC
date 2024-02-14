package g1101_1200.s1155_number_of_dice_rolls_with_target_sum;

// #Medium #Dynamic_Programming #2023_06_02_Time_15_ms_(84.85%)_Space_40.7_MB_(90.40%)

import java.util.Arrays;

public class Solution {
    private int[][] memo;
    private int k;

    private int dp(int diceLeft, int targetLeft) {
        if (diceLeft == 0) {
            if (targetLeft == 0) {
                return 1;
            }
            return 0;
        }
        if (memo[diceLeft][targetLeft] == -1) {
            int res = 0;
            for (int i = 1; i <= Math.min(k, targetLeft); i++) {
                res += dp(diceLeft - 1, targetLeft - i);
                int modulo = 1000000007;
                res %= modulo;
            }
            memo[diceLeft][targetLeft] = res;
        }
        return memo[diceLeft][targetLeft];
    }
	//@ requires(*The values of `n`, `k`, and `target` are positive integers.*);
	//@ requires(*`n` and `k` are not greater than 30.*);
	//@ requires(*`target` is not greater than 1000.*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*The returned value is the number of possible ways to roll the dice so that the sum of the face-up numbers equals `target`.*);
	//@ ensures(*The returned value is calculated modulo 10^9 + 7.*);

    public int numRollsToTarget(int n, int k, int target) {
        this.k = k;
        this.memo = new int[n + 1][target + 1];
        for (int[] i : memo) {
            Arrays.fill(i, -1);
        }
        return dp(n, target);
    }
}