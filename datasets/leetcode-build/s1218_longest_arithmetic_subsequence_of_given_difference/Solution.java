package g1201_1300.s1218_longest_arithmetic_subsequence_of_given_difference;

// #Medium #Array #Hash_Table #Dynamic_Programming
// #2022_03_11_Time_11_ms_(98.23%)_Space_63.9_MB_(78.62%)

public class Solution {
	//@ requires(*The input array `arr` is not null.*);
	//@ requires(*The length of the input array `arr` is greater than or equal to 1.*);
	//@ requires(*The difference value `difference` is within the range of -10^4 to 10^4.*);
	//@ ensures(*The returned value is an integer representing the length of the longest subsequence in `arr` that is an arithmetic sequence with a difference of `difference`.*);
	//@ ensures(*The returned value is greater than or equal to 0.*);
	//@ ensures(*The returned value is less than or equal to the length of the input array `arr`.*);
    public int longestSubsequence(int[] arr, int difference) {
        int res = 0;
        int[] dp = new int[20001];
        for (int j : arr) {
            int cur = j + 10000;
            int last = j - difference + 10000;
            if (last < 0 || last > 20000) {
                dp[cur] = Math.max(dp[cur], 1);
            } else {
                dp[cur] = Math.max(dp[cur], dp[last] + 1);
            }
            res = Math.max(res, dp[cur]);
        }
        return res;
    }
}