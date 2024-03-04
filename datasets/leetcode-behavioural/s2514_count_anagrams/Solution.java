package g2501_2600.s2514_count_anagrams;

// #Hard #String #Hash_Table #Math #Counting #Combinatorics
// #2023_03_21_Time_22_ms_(100.00%)_Space_43_MB_(98.15%)

import java.util.Arrays;

public class Solution {
    private static final int MOD = (int) 1e9 + 7;
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of `s` is between 1 and 10^- `s` consists of lowercase English letters and spaces.*);
//@ ensures(*There is a single space between consecutive words.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of distinct anagrams of `s`.*);
//@ ensures(*The returned value is modulo 10^9 + 7.*);

    public int countAnagrams(String s) {
        var charArray = s.toCharArray();
        long ans = 1L;
        long mul = 1L;
        var cnt = new int[26];
        int j = 0;
        for (char c : charArray) {
            if (c == ' ') {
                Arrays.fill(cnt, 0);
                j = 0;
            } else {
                mul = mul * ++cnt[c - 'a'] % MOD;
                ans = ans * ++j % MOD;
            }
        }
        return (int) (ans * pow(mul, MOD - 2) % MOD);
    }

    private long pow(long x, int n) {
        var res = 1L;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }
}