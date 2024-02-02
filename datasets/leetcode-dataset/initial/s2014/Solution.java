package g2001_2100.s2014_longest_subsequence_repeated_k_times;

// #Hard #String #Greedy #Backtracking #Counting #Enumeration
// #2022_05_24_Time_169_ms_(98.59%)_Space_48.8_MB_(57.75%)

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the **longest subsequence repeated**_ `k` _times in string_ `s`_. If multiple such subsequences are found, return the **lexicographically largest** one. If there is no such subsequence, return an **empty** string_. **Explanation:** There is no subsequence repeated 2 times. Empty string is returned.
Return _the **longest subsequence repeated**_ `k` _times in string_ `s`_. If multiple such subsequences are found, return the **lexicographically largest** one. If there is no such subsequence, return an **empty** string_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
