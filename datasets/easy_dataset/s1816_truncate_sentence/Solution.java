package g1801_1900.s1816_truncate_sentence;

// #Easy #Array #String #2022_05_03_Time_2_ms_(65.71%)_Space_42.9_MB_(30.34%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input sentence `s` is not null.*);
//@ ensures(*The input sentence `s` is not empty.*);
//@ ensures(*The input sentence `s` consists of only uppercase and lowercase English letters and spaces.*);
//@ ensures(*The input sentence `s` does not have leading or trailing spaces.*);
//@ ensures(*The input integer `k` is greater than or equal to 1.*);
//@ ensures(*The input integer `k` is less than or equal to the number of words in `s`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output string `s` is truncated such that it contains only the first `k` words.*);
//@ ensures(*The output string `s` does not have any leading or trailing spaces.*);
    public String truncateSentence(String s, int k) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(words[i]);
            sb.append(" ");
        }
        return sb.substring(0, sb.toString().length() - 1);
    }
}