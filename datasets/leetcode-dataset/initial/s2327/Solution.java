package g2301_2400.s2327_number_of_people_aware_of_a_secret;

// #Medium #Dynamic_Programming #Simulation #Queue
// #2022_07_04_Time_7_ms_(80.00%)_Space_44_MB_(10.00%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given an integer `n`, return _the number of people who know the secret at the end of day_ `n`. Since the answer may be very large, return it **modulo** <code>10<sup>9</sup> + 7</code>.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long[][] dp = new long[n + forget][3];
        // 0: people who currently know the secret (includes [1] below)
        // 1: people who start sharing the secret on this day
        // 2: people who forget on this day
        long mod = (long) 1e9 + 7;
        dp[0][0] = dp[delay][1] = dp[forget][2] = 1;
        for (int i = 1; i < n; i++) {
            // dp[i][1] was originally just the i - delay newcomers
            dp[i][1] = (dp[i][1] + dp[i - 1][1] - dp[i][2] + mod) % mod;
            // these people forget on i + forget day
            dp[i + forget][2] = dp[i][1];
            // these people start sharing on i + delay day
            dp[i + delay][1] = dp[i][1];
            // today's total people who know the secret
            dp[i][0] = (dp[i - 1][0] + dp[i][1] - dp[i][2] + mod) % mod;
        }
        return (int) dp[n - 1][0];
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
