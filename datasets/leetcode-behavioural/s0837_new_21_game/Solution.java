package g0801_0900.s0837_new_21_game;

// #Medium #Dynamic_Programming #Math #Sliding_Window #Probability_and_Statistics
// #2022_03_24_Time_5_ms_(77.70%)_Space_43.8_MB_(59.58%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The method takes three integer parameters: `n`, `k`, and `maxPts`.*);
//@ ensures(*`n` represents the maximum number of points Alice can have.*);
//@ ensures(*`k` represents the minimum number of points Alice needs to stop drawing numbers.*);
//@ ensures(*`maxPts` represents the maximum number of points Alice can gain in each draw.*);
//@ ensures(*`0 <= k <= n <= 10^4` and `1 <= maxPts <= 10^4`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a double value representing the probability that Alice has `n` or fewer points.*);
//@ ensures(*The returned probability is within `10^-5` of the actual answer.*);
    public double new21Game(int n, int k, int w) {
        if (n >= k + w || k == 0) {
            return 1.0;
        }
        double[] dp = new double[n + 1];
        dp[0] = 1.0;
        double res = 0.0;
        double runningSum = dp[0];
        for (int i = 1; i <= n; i++) {
            dp[i] = runningSum / w;
            if (i < k) {
                runningSum += dp[i];
            } else {
                res += dp[i];
            }
            if (i - w >= 0) {
                runningSum -= dp[i - w];
            }
        }
        return res;
    }
}