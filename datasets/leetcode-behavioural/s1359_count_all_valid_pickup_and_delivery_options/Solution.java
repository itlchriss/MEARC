package g1301_1400.s1359_count_all_valid_pickup_and_delivery_options;

// #Hard #Dynamic_Programming #Math #Combinatorics
// #2022_03_21_Time_0_ms_(100.00%)_Space_40.9_MB_(58.06%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is an integer greater than or equal to 1 and less than or equal to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the count of all valid pickup/delivery sequences.*);
//@ ensures(*The returned count is modulo 10^9 + 7.*);
    public int countOrders(int n) {
        long[] dp = new long[n + 1];
        dp[1] = 1;
        long mod = (long) 1e9 + 7;
        for (int i = 2; i <= n; i++) {
            long gaps = (i - 1) * 2L + 1;
            dp[i] = gaps * (gaps + 1) / 2 * dp[i - 1] % mod;
        }
        return (int) dp[n];
    }
}