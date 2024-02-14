package g1601_1700.s1662_check_if_two_string_arrays_are_equivalent;

// #Easy #Array #String #2022_04_23_Time_0_ms_(100.00%)_Space_40_MB_(94.96%)

public class Solution {
	//@ requires(*The input arrays `word1` and `word2` are not null.*);
	//@ requires(*The lengths of `word1` and `word2` are equal.*);
	//@ ensures(*The method returns `true` if the concatenated strings of `word1` and `word2` are equal, and `false` otherwise.*);
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        for (String word : word1) {
            sb1.append(word);
        }
        StringBuilder sb2 = new StringBuilder();
        for (String word : word2) {
            sb2.append(word);
        }
        return sb1.toString().equals(sb2.toString());
    }
}