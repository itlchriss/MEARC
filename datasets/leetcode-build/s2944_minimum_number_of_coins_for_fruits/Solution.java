package g2901_3000.s2944_minimum_number_of_coins_for_fruits;

// #Medium #Array #Dynamic_Programming #Heap_Priority_Queue #Queue #Monotonic_Queue
// #2024_01_04_Time_2_ms_(99.43%)_Space_43.9_MB_(56.19%)

public class Solution {
	//@ requires(*The input array `prices` is not null.*);
	//@ requires(*The length of the input array `prices` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `prices` are positive integers.*);
	//@ ensures(*The return value is an integer representing the minimum number of coins needed to acquire all the fruits.*);
    public int minimumCoins(int[] prices) {
        int n = prices.length;
        int[] dp = new int[n];
        dp[n - 1] = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            int pos = i + 1;
            int acquired = i + pos;
            if (acquired + 1 < n) {
                int min = Integer.MAX_VALUE;
                for (int j = acquired + 1; j >= i + 1; j--) {
                    min = Math.min(min, dp[j]);
                }
                dp[i] = prices[i] + min;
            } else {
                dp[i] = prices[i];
            }
        }
        return dp[0];
    }
}