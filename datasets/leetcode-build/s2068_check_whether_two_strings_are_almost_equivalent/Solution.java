package g2001_2100.s2068_check_whether_two_strings_are_almost_equivalent;

// #Easy #String #Hash_Table #Counting #2022_05_29_Time_1_ms_(95.21%)_Space_42.6_MB_(35.36%)

public class Solution {
	//@ requires(*The input strings `word1` and `word2` must not be null.*);
	//@ requires(*The lengths of `word1` and `word2` must be equal.*);
	//@ requires(*The lengths of `word1` and `word2` must be between 1 and 100 (inclusive).*);
	//@ requires(*`word1` and `word2` must consist only of lowercase English letters.*);
	//@ ensures(*The method returns `true` if the differences between the frequencies of each letter from `'a'` to `'z'` between `word1` and `word2` is at most 3.*);
	//@ ensures(*The method returns `false` otherwise.*);
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] freq = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            ++freq[word1.charAt(i) - 'a'];
            --freq[word2.charAt(i) - 'a'];
        }
        for (int i : freq) {
            if (Math.abs(i) > 3) {
                return false;
            }
        }
        return true;
    }
}