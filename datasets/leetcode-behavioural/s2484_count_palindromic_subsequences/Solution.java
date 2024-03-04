package g2401_2500.s2484_count_palindromic_subsequences;

// #Hard #String #Dynamic_Programming #2023_01_26_Time_93_ms_(76.16%)_Space_40.2_MB_(99.74%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is greater than or equal to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the number of palindromic subsequences of length 5 in the input string `s`.*);
//@ ensures(*The returned value is modulo 10^9 + 7.*);
    public int countPalindromes(String s) {
        int n = s.length();
        long ans = 0;
        int mod = (int) 1e9 + 7;
        int[] count = new int[10];
        for (int i = 0; i < n; i++) {
            long m = 0;
            for (int j = n - 1; j > i; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    ans += (m * (j - i - 1));
                    ans = ans % mod;
                }
                m += count[s.charAt(j) - '0'];
            }
            count[s.charAt(i) - '0']++;
        }
        return (int) ans;
    }
}