package g1701_1800.s1754_largest_merge_of_two_strings;

// #Medium #String #Greedy #Two_Pointers #2022_04_30_Time_37_ms_(89.23%)_Space_53.8_MB_(53.08%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input strings `word1` and `word2` are not null.*);
//@ ensures(*The input strings `word1` and `word2` consist only of lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output string `merge` is not null.*);
//@ ensures(*The output string `merge` is the lexicographically largest merge of `word1` and `word2`.*);
//@ ensures(*The length of the output string `merge` is equal to the sum of the lengths of `word1` and `word2`.*);
//@ ensures(*The characters in the output string `merge` are in the same order as they appear in `word1` and `word2`.*);
//@ ensures(*The characters in the output string `merge` are either from `word1` or from `word2`.*);
//@ ensures(*The characters in the output string `merge` are deleted from `word1` and `word2` in the process of constructing the merge.*);
//@ ensures(*After constructing the merge, `word1` and `word2` are empty strings.*);
    public String largestMerge(String word1, String word2) {
        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                boolean first = go(a, i, b, j);
                if (first) {
                    sb.append(a[i]);
                    i++;
                } else {
                    sb.append(b[j]);
                    j++;
                }
            } else {
                if (a[i] > b[j]) {
                    sb.append(a[i]);
                    i++;
                } else {
                    sb.append(b[j]);
                    j++;
                }
            }
        }

        while (i < a.length) {
            sb.append(a[i++]);
        }
        while (j < b.length) {
            sb.append(b[j++]);
        }

        return sb.toString();
    }

    private boolean go(char[] a, int i, char[] b, int j) {
        while (i < a.length && j < b.length && a[i] == b[j]) {
            i++;
            j++;
        }
        if (i == a.length) {
            return false;
        }
        if (j == b.length) {
            return true;
        }
        return a[i] > b[j];
    }
}