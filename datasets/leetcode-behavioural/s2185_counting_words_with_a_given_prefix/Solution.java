package g2101_2200.s2185_counting_words_with_a_given_prefix;

// #Easy #Array #String #2022_06_08_Time_0_ms_(100.00%)_Space_43.9_MB_(15.40%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `words` is not null.*);
//@ ensures(*The input string `pref` is not null.*);
//@ ensures(*The length of `words` is greater than or equal to 1.*);
//@ ensures(*The length of each string in `words` is greater than or equal to 1.*);
//@ ensures(*The length of `pref` is greater than or equal to 1.*);
//@ ensures(*Each string in `words` and `pref` consists of lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of strings in `words` that contain `pref` as a prefix.*);
//@ ensures(*The method does not modify the input array `words` or the input string `pref`.*);
    public int prefixCount(String[] words, String pref) {
        int count = 0;
        for (String s : words) {
            if (s.startsWith(pref)) {
                count++;
            }
        }
        return count;
    }
}