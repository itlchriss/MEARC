package g1601_1700.s1690_stone_game_vii;

// #Medium #Array #Dynamic_Programming #Math #Game_Theory
// #2022_04_15_Time_18_ms_(96.71%)_Space_42.1_MB_(96.71%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `stones` is not null.*);
//@ ensures(*The length of the input array `stones` is at least 2.*);
//@ ensures(*The values in the input array `stones` are positive integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the difference in Alice and Bob's score.*);
//@ ensures(*The difference in Alice and Bob's score is maximized if both players play optimally.*);
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int j = i + 1;
            int sum = stones[i];
            while (j < n) {
                sum += stones[j];
                dp[j] = Math.max(sum - stones[i] - dp[j], sum - stones[j] - dp[j - 1]);
                j++;
            }
        }
        return dp[n - 1];
    }
}