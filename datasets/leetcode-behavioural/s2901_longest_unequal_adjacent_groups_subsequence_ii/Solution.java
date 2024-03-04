package g2901_3000.s2901_longest_unequal_adjacent_groups_subsequence_ii;

// #Medium #Array #String #Dynamic_Programming #2023_12_25_Time_40_ms_(92.26%)_Space_45_MB_(41.07%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` is greater than or equal to 1.*);
//@ ensures(*The length of the `words` array is equal to `n`.*);
//@ ensures(*The length of the `groups` array is equal to `n`.*);
//@ ensures(*Each string in the `words` array consists of lowercase English letters.*);
//@ ensures(*Each string in the `words` array has a length between 1 and 10 (inclusive).*);
//@ ensures(*Each element in the `groups` array is between 1 and `n` (inclusive).*);
//@ ensures(*The `words` array consists of distinct strings.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned list contains strings from the `words` array.*);
//@ ensures(*The returned list corresponds to a subsequence of indices from the original array `[0, 1, ..., n - 1]`.*);
//@ ensures(*The returned list satisfies the conditions of having unequal adjacent groups and a hamming distance of 1 between adjacent words.*);
    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        int[] check = new int[groups.length];
        int[] before = new int[groups.length];
        Arrays.fill(check, 1);
        Arrays.fill(before, -1);
        int index = 0;
        int max = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (groups[i] != groups[j] && ham(words[i], words[j]) && check[j] + 1 > check[i]) {
                    check[i] = check[j] + 1;
                    before[i] = j;
                    if (check[i] > max) {
                        max = check[i];
                        index = i;
                    }
                }
            }
        }
        List<String> ans = new ArrayList<>();
        while (index >= 0) {
            ans.add(words[index]);
            index = before[index];
        }
        Collections.reverse(ans);
        return ans;
    }

    private boolean ham(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return count == 1;
    }
}