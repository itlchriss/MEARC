package g1501_1600.s1510_stone_game_iv;

// #Hard #Dynamic_Programming #Math #Game_Theory
// #2022_04_09_Time_12_ms_(85.75%)_Space_39.2_MB_(96.63%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer.*);
//@ ensures(*The method `winnerSquareGame` is called with a valid input.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns `true` if Alice wins the game, and `false` otherwise.*);
//@ ensures(*If `n` is 1, the method returns `true`.*);
//@ ensures(*If `n` is 2, the method returns `false`.*);
//@ ensures(*If `n` is a perfect square, the method returns `true`.*);
//@ ensures(*If `n` is not a perfect square, the method returns `false`.*);
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int k = 1; k * k <= i; k++) {
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}