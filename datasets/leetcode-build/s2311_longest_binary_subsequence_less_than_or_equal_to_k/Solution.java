package g2301_2400.s2311_longest_binary_subsequence_less_than_or_equal_to_k;

// #Medium #String #Dynamic_Programming #Greedy #Memoization
// #2022_06_20_Time_1_ms_(100.00%)_Space_42.3_MB_(50.00%)

public class Solution {
	//@ requires(*The input string `s` is a binary string.*);
	//@ requires(*The input integer `k` is a positive integer.*);
	//@ requires(*The length of `s` is between 1 and - Each character in `s` is either '0' or '1'.*);
	//@ requires(*The value of `k` is between 1 and 10^*);
	//@ ensures(*The method returns an integer representing the length of the longest subsequence of `s` that makes up a binary number less than or equal to `k`.*);
	//@ ensures(*The subsequence can contain leading zeroes.*);
	//@ ensures(*The empty string is considered to be equal to - The subsequence is a string that can be derived from `s` by deleting some or no characters without changing the order of the remaining characters.*);
    public int longestSubsequence(String s, int k) {
        int res = 0;
        int cost = 1;
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0' || cost <= k) {
                k -= cost * (s.charAt(i) - '0');
                ++res;
            }
            if (cost <= k) {
                cost *= 2;
            }
        }
        return res;
    }
}