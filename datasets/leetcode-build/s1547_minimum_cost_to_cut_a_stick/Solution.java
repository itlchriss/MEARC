package g1501_1600.s1547_minimum_cost_to_cut_a_stick;

// #Hard #Array #Dynamic_Programming #2022_04_11_Time_6_ms_(100.00%)_Space_41.9_MB_(90.56%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input `n` must be an integer greater than or equal to - The input `cuts` must be an array of integers with a length greater than or equal to - Each element in the `cuts` array must be an integer between 1 and `n-1`.*);
	//@ requires(*All elements in the `cuts` array must be distinct.*);
	//@ ensures(*The method should return an integer representing the minimum total cost of the cuts.*);
	//@ ensures(*The order of the cuts can be changed.*);
	//@ ensures(*The cost of one cut is the length of the stick to be cut.*);
	//@ ensures(*When a stick is cut, it will be split into two smaller sticks, and the sum of their lengths will be equal to the length of the stick before the cut.*);
    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        int m = cuts.length;
        int[][] dp = new int[m + 1][m + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= m - i; j++) {
                int k = j + i;
                int min = Integer.MAX_VALUE;
                for (int p = j; p < k; p++) {
                    min = Math.min(min, dp[j][p] + dp[p + 1][k]);
                }
                int len = (k == m ? n : cuts[k]) - (j == 0 ? 0 : cuts[j - 1]);
                dp[j][k] = min + len;
            }
        }
        return dp[0][m];
    }
}