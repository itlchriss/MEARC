package g0501_0600.s0516_longest_palindromic_subsequence;

// #Medium #String #Dynamic_Programming #Dynamic_Programming_I_Day_17
// #2022_07_25_Time_88_ms_(58.87%)_Space_68.4_MB_(64.16%)

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The input string `s` is not empty.*);
	//@ requires(*3. The length of the input string `s` is between 1 and 1000.*);
	//@ requires(*4. The input string `s` consists only of lowercase English letters.*);
	//@ ensures(*1. The method returns an integer representing the length of the longest palindromic subsequence in the input string `s`.*);
	//@ ensures(*2. The method does not modify the input string `s`.*);
	//@ ensures(*3. The method returns 0 if there is no palindromic subsequence in the input string `s`.*);
	//@ ensures(*4. The method returns 1 if the input string `s` is a palindrome.*);
	//@ ensures(*5. The method returns the length of the input string `s` if there are no repeated characters in the input string `s`.*);
	//@ ensures(*6. The method returns the length of the input string `s` minus the number of repeated characters in the input string `s` if the input string `s` is not a palindrome.*);
    public int longestPalindromeSubseq(String s) {
        char[] x = s.toCharArray();
        char[] y = new StringBuilder(s).reverse().toString().toCharArray();
        int m = x.length;
        int n = y.length;
        int[][] l = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    l[i][j] = 0;
                } else if (x[i - 1] == y[j - 1]) {
                    l[i][j] = l[i - 1][j - 1] + 1;

                } else {
                    l[i][j] = Math.max(l[i - 1][j], l[i][j - 1]);
                }
            }
        }
        return l[m][n];
    }
}