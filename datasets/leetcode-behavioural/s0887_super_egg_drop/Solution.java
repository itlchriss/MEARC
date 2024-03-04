package g0801_0900.s0887_super_egg_drop;

// #Hard #Dynamic_Programming #Math #Binary_Search
// #2022_03_28_Time_0_ms_(100.00%)_Space_38.7_MB_(99.74%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The value of `k` must be greater than or equal to 1.*);
//@ ensures(*The value of `n` must be greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of moves needed to determine the value of `f` with certainty.*);
    public int superEggDrop(int k, int n) {
        int e = k;
        int f = n;
        int[] dp = new int[e + 1];
        int counter = 1;
        while (true) {
            int temp = dp[0];
            for (int i = 1; i < dp.length; i++) {
                int val = dp[i] + temp + 1;
                temp = dp[i];
                dp[i] = val;
                if (val >= f) {
                    return counter;
                }
            }
            counter = counter + 1;
        }
    }
}