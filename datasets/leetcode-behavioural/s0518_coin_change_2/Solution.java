package g0501_0600.s0518_coin_change_2;

// #Medium #Array #Dynamic_Programming #Dynamic_Programming_I_Day_20
// #2022_07_25_Time_4_ms_(84.67%)_Space_42.2_MB_(60.30%)

public class Solution {
//@ ensures(*If the integer parameter `amount` can be made up by any combination of the integer array parameter `coins`, the integer result is the number of combinations that make up that amount.*);
//@ ensures(*If the integer parameter `amount` cannot be made up by any combination of the integer array parameter `coins`, the integer result is 0.*);
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}