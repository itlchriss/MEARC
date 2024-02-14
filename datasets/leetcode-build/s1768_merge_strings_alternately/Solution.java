package g1701_1800.s1768_merge_strings_alternately;

// #Easy #String #Two_Pointers #Programming_Skills_I_Day_8_String
// #2022_04_27_Time_1_ms_(86.26%)_Space_41.7_MB_(79.68%)

public class Solution {
	//@ requires(*The input strings `word1` and `word2` are not null.*);
	//@ requires(*The lengths of `word1` and `word2` are between 1 and 100 (inclusive).*);
	//@ requires(*`word1` and `word2` consist only of lowercase English letters.*);
	//@ ensures(*The returned string is the merged string of `word1` and `word2`.*);
	//@ ensures(*The merged string is obtained by adding letters from `word1` and `word2` in alternating order, starting with `word1`.*);
	//@ ensures(*If one string is longer than the other, the additional letters are appended to the end of the merged string.*);
    public String mergeAlternately(String word1, String word2) {
        int size1 = word1.length();
        int size2 = word2.length();
        int min = Math.min(size1, size2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < min; i++) {
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(i));
        }
        if (min == size1) {
            sb.append(word2, size1, size2);
        }
        if (min == size2) {
            sb.append(word1, size2, size1);
        }
        return sb.toString();
    }
}