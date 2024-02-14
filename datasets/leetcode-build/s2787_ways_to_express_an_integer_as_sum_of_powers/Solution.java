package g2701_2800.s2787_ways_to_express_an_integer_as_sum_of_powers;

// #Medium #Dynamic_Programming #2023_09_15_Time_12_ms_(98.96%)_Space_42.4_MB_(86.68%)

public class Solution {
	//@ requires(*The input integers `n` and `x` must be positive.*);
	//@ requires(*The value of `n` must be between 1 and 300 (inclusive).*);
	//@ requires(*The value of `x` must be between 1 and 5 (inclusive).*);
	//@ ensures(*The method should return an integer representing the number of ways `n` can be expressed as the sum of the `x`th power of unique positive integers.*);
	//@ ensures(*The returned value should be modulo `10^9 + 7`.*);
    public int numberOfWays(int n, int x) {
        int[] dp = new int[301];
        int mod = 1000000007;
        int v;
        dp[0] = 1;
        int a = 1;
        while (Math.pow(a, x) <= n) {
            v = (int) Math.pow(a, x);
            for (int i = n; i >= v; i--) {
                dp[i] = (dp[i] + dp[i - v]) % mod;
            }
            a++;
        }
        return dp[n];
    }
}