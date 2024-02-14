package g0001_0100.s0010_regular_expression_matching;

// #Hard #Top_100_Liked_Questions #Top_Interview_Questions #String #Dynamic_Programming #Recursion
// #Udemy_Dynamic_Programming #Big_O_Time_O(m*n)_Space_O(m*n)
// #2024_01_04_Time_1_ms_(100.00%)_Space_42.1_MB_(29.26%)

public class Solution {
    private Boolean[][] cache;
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The pattern `p` is not null.*);
	//@ requires(*The length of `s` is greater than or equal to 1.*);
	//@ requires(*The length of `p` is greater than or equal to 1.*);
	//@ requires(*`s` contains only lowercase English letters.*);
	//@ requires(*`p` contains only lowercase English letters, '.' and '*'.*);
	//@ requires(*For each appearance of the character '*', there will be a previous valid character to match.*);
	//@ ensures(*The method returns a boolean value indicating whether the input string `s` matches the pattern `p`.*);
	//@ ensures(*If the method returns true, it means the entire input string `s` matches the pattern `p`.*);
	//@ ensures(*If the method returns false, it means the entire input string `s` does not match the pattern `p`.*);

    public boolean isMatch(String s, String p) {
        cache = new Boolean[s.length() + 1][p.length() + 1];
        return isMatch(s, p, 0, 0);
    }

    private boolean isMatch(String s, String p, int i, int j) {
        if (j == p.length()) {
            return i == s.length();
        }
        boolean result;
        if (cache[i][j] != null) {
            return cache[i][j];
        }
        boolean firstMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        if ((j + 1) < p.length() && p.charAt(j + 1) == '*') {
            result = (firstMatch && isMatch(s, p, i + 1, j)) || isMatch(s, p, i, j + 2);
        } else {
            result = firstMatch && isMatch(s, p, i + 1, j + 1);
        }
        cache[i][j] = result;
        return result;
    }
}