package g0501_0600.s0518_coin_change_2;

// #Medium #Array #Dynamic_Programming #Dynamic_Programming_I_Day_20
// #2022_07_25_Time_4_ms_(84.67%)_Space_42.2_MB_(60.30%)

public class Solution {
	//@ requires(*The `amount` parameter must be a non-negative integer.*);
	//@ requires(*The `coins` array must not be null.*);
	//@ requires(*The `coins` array must contain at least one element.*);
	//@ requires(*All elements in the `coins` array must be positive integers.*);
	//@ requires(*The `coins` array must not contain any duplicate values.*);
	//@ ensures(*The method returns an integer representing the number of combinations that make up the given `amount`.*);
	//@ ensures(*If the given `amount` cannot be made up by any combination of the coins, the method returns 0.*);
	//@ ensures(*The returned value is guaranteed to fit into a signed 32-bit integer.*);
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