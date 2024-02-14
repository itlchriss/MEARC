package g0801_0900.s0801_minimum_swaps_to_make_sequences_increasing;

// #Hard #Array #Dynamic_Programming #2022_03_23_Time_6_ms_(71.94%)_Space_87.4_MB_(76.67%)

public class Solution {
	//@ requires(*The lengths of `listA` and `listB` are the same.*);
	//@ requires(*The length of `listA` and `listB` is at least 2.*);
	//@ requires(*The elements of `listA` and `listB` are non-negative integers.*);
	//@ requires(*The elements of `listA` and `listB` are within the range of 0 to 2 * 10^5.*);
	//@ ensures(*The returned value is the minimum number of operations needed to make `listA` and `listB` strictly increasing.*);
	//@ ensures(*After the operations, `listA` and `listB` are both strictly increasing.*);
    public int minSwap(int[] listA, int[] listB) {
        int[] dp = new int[2];
        dp[1] = 1;
        for (int i = 1; i < listA.length; i++) {
            int a = Integer.MAX_VALUE;
            int b = Integer.MAX_VALUE;
            if (listA[i] > listA[i - 1] && listB[i] > listB[i - 1]) {
                a = dp[0];
                b = dp[1];
            }
            if (listA[i] > listB[i - 1] && listB[i] > listA[i - 1]) {
                a = Math.min(a, dp[1]);
                b = Math.min(b, dp[0]);
            }
            dp[0] = a;
            dp[1] = b + 1;
        }
        return Math.min(dp[0], dp[1]);
    }
}