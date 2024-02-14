package g2401_2500.s2466_count_ways_to_build_good_strings;

// #Medium #Dynamic_Programming #2023_01_11_Time_8_ms_(99.59%)_Space_41_MB_(98.19%)

public class Solution {
	//@ requires(*The values of `low` and `high` must be integers greater than or equal to 1 and less than or equal to 10^- The values of `zero` and `one` must be integers greater than or equal to 1 and less than or equal to the value of `low`.*);
	//@ ensures(*The method should return an integer representing the number of different good strings that can be constructed satisfying the given properties.*);
	//@ ensures(*The returned value should be modulo 10^9 + 7.*);
    public int countGoodStrings(int low, int high, int zero, int one) {
        int[] dp = new int[high + 1];
        dp[zero]++;
        dp[one]++;
        int ans = 0;
        for (int i = 0; i < high + 1; i++) {
            if (dp[i] != 0) {
                if (i + zero <= high) {
                    dp[i + zero] += dp[i];
                    dp[i + zero] = dp[i + zero] % 1000000007;
                }
                if (i + one <= high) {
                    dp[i + one] += dp[i];
                    dp[i + one] = dp[i + one] % 1000000007;
                }
                if (i >= low) {
                    ans += dp[i];
                    ans = ans % 1000000007;
                }
            }
        }
        return ans;
    }
}