package g0501_0600.s0521_longest_uncommon_subsequence_i;

// #Easy #String #2022_07_28_Time_0_ms_(100.00%)_Space_40.2_MB_(87.89%)

public class Solution {
    /*
     * The gotcha point of this question is:
     * 1. if a and b are identical, then there will be no common subsequence, return -1
     * 2. else if a and b are of equal length, then any one of them will be a subsequence of the other string
     * 3. else if a and b are of different length, then the longer one is a required subsequence because
     * the longer string cannot be a subsequence of the shorter one
     * Or in other words, when a.length() != b.length(), no subsequence of b will be equal to a,
     * so return Math.max(a.length(), b.length())
     */
	//@ requires(*The input strings `a` and `b` are not null.*);
	//@ requires(*The lengths of `a` and `b` are between 1 and 100 (inclusive).*);
	//@ requires(*The strings `a` and `b` consist of lowercase English letters.*);
	//@ ensures(*The method returns an integer representing the length of the longest uncommon subsequence between `a` and `b`.*);
	//@ ensures(*If there is no uncommon subsequence, the method returns -1.*);
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }
}