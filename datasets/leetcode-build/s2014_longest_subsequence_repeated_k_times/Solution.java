package g2001_2100.s2014_longest_subsequence_repeated_k_times;

// #Hard #String #Greedy #Backtracking #Counting #Enumeration
// #2022_05_24_Time_169_ms_(98.59%)_Space_48.8_MB_(57.75%)

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The input string `s` has a length greater than or equal to 2.*);
	//@ requires(*3. The input integer `k` is greater than or equal to 2.*);
	//@ requires(*4. The input integer `k` is less than or equal to the length of string `s` divided by 8.*);
	//@ ensures(*1. The output string is the longest subsequence repeated `k` times in string `s`.*);
	//@ ensures(*2. If multiple longest subsequences are found, the output string is the lexicographically largest one.*);
	//@ ensures(*3. If there is no subsequence repeated `k` times, the output string is empty.*);
    public String longestSubsequenceRepeatedK(String s, int k) {
        char[] ca = s.toCharArray();
        char[] freq = new char[26];
        for (char value : ca) {
            ++freq[value - 'a'];
        }
        ArrayList<String>[] cand = new ArrayList[8];
        cand[1] = new ArrayList<>();
        String ans = "";
        for (int i = 0; i < 26; i++) {
            if (freq[i] >= k) {
                ans = "" + (char) ('a' + i);
                cand[1].add(ans);
            }
        }
        for (int i = 2; i < 8; i++) {
            cand[i] = new ArrayList<>();
            for (String prev : cand[i - 1]) {
                for (String c : cand[1]) {
                    String next = prev + c;
                    if (isSubsequenceRepeatedK(ca, next, k)) {
                        ans = next;
                        cand[i].add(ans);
                    }
                }
            }
        }
        return ans;
    }

    private boolean isSubsequenceRepeatedK(char[] ca, String t, int k) {
        char[] ta = t.toCharArray();
        int n = ca.length;
        int m = ta.length;
        int i = 0;
        while (k-- > 0) {
            int j = 0;
            while (i < n && j < m) {
                if (ca[i] == ta[j]) {
                    j++;
                }
                i++;
            }
            if (j != m) {
                return false;
            }
        }
        return true;
    }
}