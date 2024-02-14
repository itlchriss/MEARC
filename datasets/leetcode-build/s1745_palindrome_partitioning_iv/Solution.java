package g1701_1800.s1745_palindrome_partitioning_iv;

// #Hard #String #Dynamic_Programming #2022_04_29_Time_10_ms_(100.00%)_Space_44_MB_(95.27%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is at least 3 and at most 2000.*);
	//@ requires(*The input string `s` consists only of lowercase English letters.*);
	//@ ensures(*The method returns `true` if it is possible to split the string `s` into three non-empty palindromic substrings.*);
	//@ ensures(*The method returns `false` if it is not possible to split the string `s` into three non-empty palindromic substrings.*);
    public boolean checkPartitioning(String s) {
        final int len = s.length();
        char[] ch = s.toCharArray();
        int[] dp = new int[len + 1];
        dp[0] = 0x01;
        for (int i = 0; i < len; i++) {
            for (int l : new int[] {i - 1, i}) {
                int r = i;
                while (l >= 0 && r < len && ch[l] == ch[r]) {
                    dp[r + 1] |= dp[l] << 1;
                    l--;
                    r++;
                }
            }
        }
        return (dp[len] & 0x08) == 0x08;
    }
}