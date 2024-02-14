package g2801_2900.s2842_count_k_subsequences_of_a_string_with_maximum_beauty;

// #Hard #String #Hash_Table #Math #Greedy #Combinatorics
// #2023_12_12_Time_5_ms_(80.91%)_Space_44.9_MB_(55.45%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` is not empty.*);
	//@ requires(*The input integer `k` is greater than or equal to 1.*);
	//@ requires(*The input integer `k` is less than or equal to the length of `s`.*);
	//@ ensures(*The method returns an integer.*);
	//@ ensures(*The returned integer is the number of k-subsequences whose beauty is the maximum among all k-subsequences.*);
	//@ ensures(*The returned integer is modulo 10^9 + 7.*);
    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; ++i) {
            count[s.charAt(i) - 'a']++;
        }
        Arrays.sort(count);
        if (k > 26 || count[26 - k] == 0) {
            return 0;
        }
        long res = 1;
        long comb = 1;
        long mod = (long) 1e9 + 7;
        long bar = count[26 - k];
        long pend = 0;
        for (int freq : count) {
            if (freq > bar) {
                k--;
                res = res * freq % mod;
            }
            if (freq == bar) {
                pend++;
            }
        }
        for (int i = 0; i < k; ++i) {
            comb = comb * (pend - i) / (i + 1);
            res = res * bar % mod;
        }
        return (int) (res * comb % mod);
    }
}