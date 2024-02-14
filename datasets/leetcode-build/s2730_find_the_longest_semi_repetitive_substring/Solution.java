package g2701_2800.s2730_find_the_longest_semi_repetitive_substring;

// #Medium #String #Sliding_Window #2023_09_22_Time_5_ms_(100.00%)_Space_44.5_MB_(13.68%)

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The length of the input string `s` is at least 1 and at most 50.*);
	//@ requires(*3. Each character in the input string `s` is a digit from '0' to '9'.*);
	//@ ensures(*1. The method returns an integer representing the length of the longest semi-repetitive substring inside the input string `s`.*);
	//@ ensures(*2. The returned length is greater than or equal to 0.*);
	//@ ensures(*3. The returned length is less than or equal to the length of the input string `s`.*);
	//@ ensures(*4. The returned length is the length of a valid semi-repetitive substring in the input string `s`.*);
	//@ ensures(*5. If there are multiple valid semi-repetitive substrings with the same maximum length, the method returns the length of the first one encountered in the input string `s`.*);
	//@ ensures(*6. If there are no valid semi-repetitive substrings in the input string `s`, the method returns 0.*);
    public int longestSemiRepetitiveSubstring(String s) {
        int i = 0;
        int cur = 0;
        int n = s.length();
        for (int j = 1; j < n; j++) {
            cur += (s.charAt(j) == s.charAt(j - 1)) ? 1 : 0;
            if (cur > 1) {
                cur -= (s.charAt(++i) == s.charAt(i - 1)) ? 1 : 0;
            }
        }
        return n - i;
    }
}