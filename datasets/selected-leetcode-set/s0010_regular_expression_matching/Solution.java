package g0001_0100.s0010_regular_expression_matching;

// #Hard #Top_100_Liked_Questions #Top_Interview_Questions #String #Dynamic_Programming #Recursion
// #Udemy_Dynamic_Programming #Big_O_Time_O(m*n)_Space_O(m*n)
// #2024_01_04_Time_1_ms_(100.00%)_Space_42.1_MB_(29.26%)

public class Solution {
    private Boolean[][] cache;
    

//@ ensures(*The string parameter `s` must not be null.*);
//@ ensures(*The string parameter `p` must not be null.*);
//@ ensures(*The boolean result is equal to the true literal if the input string `s` matches the pattern `p` according to the regular expression rules specified.*);
//@ ensures(*The boolean result is equal to the false literal if the input string `s` does not match the pattern `p` according to the regular expression rules specified.*);
//@ ensures(*The length of the input string `s` is greater than or equal to 1 and is less than or equal to 20.*);
//@ ensures(*The length of the pattern `p` is greater than or equal to 1 and is less than or equal to 30.*);
//@ ensures(*The input string `s` contains only lowercase English letters.*);
//@ ensures(*The pattern `p` contains only lowercase English letters, '.', and '*'.*);
//@ ensures(*For each appearance of the character '*', there will be a previous valid character to match.*);

    public boolean isMatch(String s, String p) {
        //@ assume Integer.MIN_VALUE + 1 <= s.length() <= Integer.MAX_VALUE - 1;
        //@ assume Integer.MIN_VALUE + 1 <= p.length() <= Integer.MAX_VALUE - 1;
        cache = new Boolean[s.length() + 1][p.length() + 1];
        return isMatch(s, p, 0, 0);
    }

    //@ requires s != null && p != null;
    //@ requires cache != null;
    //@ requires cache.length == s.length() + 1 && (\forall int m; 0 <= m < s.length(); cache[m].length == p.length() + 1);
    // assigns cache;
    private boolean isMatch(String s, String p, int i, int j) {
        if (j == p.length()) {
            return i == s.length();
        }
        boolean result;
        //@ assume 0 <= i < cache.length;
        //@ assume cache[i] != null;       
        //@ assume 0 <= j < cache[i].length; 
        if (cache[i][j] != null) {
            return cache[i][j];
        }
        boolean firstMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        if ((j + 1) < p.length() && p.charAt(j + 1) == '*') {
            result = (firstMatch && isMatch(s, p, i + 1, j)) || isMatch(s, p, i, j + 2);
        } else {
            result = firstMatch && isMatch(s, p, i + 1, j + 1);
        }
        //@ assume 0 <= i < cache.length;
        //@ assume cache[i] != null;       
        //@ assume 0 <= j < cache[i].length; 
        cache[i][j] = result;
        return result;
    }
}