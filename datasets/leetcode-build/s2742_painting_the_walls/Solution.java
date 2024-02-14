package g2701_2800.s2742_painting_the_walls;

// #Hard #Array #Dynamic_Programming #2023_09_23_Time_8_ms_(98.78%)_Space_43_MB_(100.00%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The length of the `cost` array is equal to the length of the `time` array.*);
	//@ requires(*The length of the `cost` array is greater than or equal to 1.*);
	//@ requires(*The values in the `cost` array are positive integers.*);
	//@ requires(*The values in the `time` array are positive integers.*);
	//@ requires(*The maximum value in the `cost` array is less than or equal to 10^6.*);
	//@ requires(*The maximum value in the `time` array is less than or equal to 500.*);
	//@ ensures(*The method returns an integer representing the minimum amount of money required to paint the walls.*);
	//@ ensures(*The returned value is greater than or equal to 0.*);
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, (int) 1e9);
        dp[0] = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = n; j > 0; --j) {
                dp[j] = Math.min(dp[j], dp[Math.max(j - time[i] - 1, 0)] + cost[i]);
            }
        }

        return dp[n];
    }
}