package g1401_1500.s1434_number_of_ways_to_wear_different_hats_to_each_other;

// #Hard #Array #Dynamic_Programming #Bit_Manipulation #Bitmask
// #2022_03_28_Time_31_ms_(77.92%)_Space_41.9_MB_(95.45%)

import java.util.List;

public class Solution {
	//@ requires(*The input `hats` is a 2D integer array.*);
	//@ requires(*The length of `hats` is equal to `n`.*);
	//@ requires(*Each element in `hats` is a list of unique integers.*);
	//@ requires(*The length of each list in `hats` is between 1 and - Each integer in `hats` is between 1 and*);
	//@ ensures(*The method returns an integer.*);
	//@ ensures(*The returned integer represents the number of ways that the `n` people can wear different hats to each other.*);
	//@ ensures(*The returned integer is modulo 10^9 + 7.*);
    public int numberWays(List<List<Integer>> hats) {
        long mod = 1000000007L;

        int size = hats.size();
        boolean[][] possible = new boolean[size][41];

        for (int i = 0; i < size; i++) {
            for (int j : hats.get(i)) {
                possible[i][j] = true;
            }
        }
        long[] dp = new long[1 << size];
        dp[0] = 1;
        for (int i = 1; i <= 40; i++) {
            for (int j = dp.length - 1; j > 0; j--) {
                for (int k = 0; k < size; k++) {
                    if (((j >> k) & 1) == 1 && possible[k][i]) {
                        dp[j] += dp[j ^ (1 << k)];
                    }
                }
                dp[j] %= mod;
            }
        }
        return (int) dp[(1 << size) - 1];
    }
}