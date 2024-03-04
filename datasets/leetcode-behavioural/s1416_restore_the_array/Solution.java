package g1401_1500.s1416_restore_the_array;

// #Hard #String #Dynamic_Programming #2022_03_26_Time_34_ms_(100.00%)_Space_42_MB_(100.00%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` must have a length greater than or equal to 1 and less than or equal to 10^5.*);
//@ ensures(*The input string `s` must consist of only digits and should not contain any leading zeros.*);
//@ ensures(*The input integer `k` must be greater than or equal to 1 and less than or equal to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output of the method should be an integer representing the number of possible arrays that can be printed as `s` using the mentioned program.*);
//@ ensures(*The output should be returned modulo 10^9 + 7.*);
    public int numberOfArrays(String s, int k) {
        int kMod = 1000000007;
        int n = s.length();
        int[] dp = new int[n];
        if (s.charAt(n - 1) != '0') {
            dp[n - 1] = 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                continue;
            }
            long temp = 0;
            int j = i;
            while (j < n && temp * 10 + s.charAt(j) - '0' <= k) {
                temp = temp * 10 + s.charAt(j) - '0';
                if (j == n - 1) {
                    dp[i] += 1;
                } else {
                    dp[i] += dp[j + 1];
                }
                dp[i] %= kMod;
                j++;
            }
        }
        return dp[0];
    }
}