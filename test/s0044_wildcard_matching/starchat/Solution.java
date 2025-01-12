package g0001_0100.s0044_wildcard_matching;

// #Hard #Top_Interview_Questions #String #Dynamic_Programming #Greedy #Recursion
// #Udemy_Dynamic_Programming #2023_08_11_Time_2_ms_(99.87%)_Space_43.2_MB_(99.49%)

public class Solution {
//@ requires(*The length of the string parameter `inputString` is less than or equal to 2000 and is greater than or equal to 0.*);
//@ requires(*The string parameter `inputString` consists of only lowercase English letters.*);
//@ requires(*The length of the string parameter `pattern` is less than or equal to 2000 and is greater than or equal to 0.*);
//@ requires(*The string parameter `pattern` consists of only lowercase English letters, '?', or '*'.*);
//@ ensures(*The boolean result is true if the entire string `inputString` matches the entire pattern `pattern`, and false otherwise.*);
//@ ensures(*If the string parameter `inputString` is equal to "aa" and the string parameter `pattern` is equal to "a", the boolean result is equal to false.*);
//@ ensures(*If the string parameter `inputString` is equal to "aa" and the string parameter `pattern` is equal to "*", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `inputString` is equal to "cb" and the string parameter `pattern` is equal to "?a", the boolean result is equal to false.*);
//@ ensures(*If the string parameter `inputString` is equal to "adceb" and the string parameter `pattern` is equal to "*a*b", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `inputString` is equal to "acdcb" and the string parameter `pattern` is equal to "a*c?b", the boolean result is equal to false.*);
    public boolean isMatch(String inputString, String pattern) {
        int i = 0;
        int j = 0;
        int starIdx = -1;
        int lastMatch = -1;
        while (i < inputString.length()) {
            if (j < pattern.length()
                    && (inputString.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < pattern.length() && pattern.charAt(j) == '*') {
                starIdx = j;
                lastMatch = i;
                j++;
            } else if (starIdx != -1) {
                // there is a no match and there was a previous star, we will reset the j to indx
                // after star_index
                // lastMatch will tell from which index we start comparing the string if we
                // encounter * in pattern
                j = starIdx + 1;
                // we are saying we included more characters in * so we incremented the
                lastMatch++;
                // index
                i = lastMatch;
            } else {
                return false;
            }
        }
        boolean isMatch = true;
        while (j < pattern.length() && pattern.charAt(j) == '*') {
            j++;
        }
        if (i != inputString.length() || j != pattern.length()) {
            isMatch = false;
        }
        return isMatch;
    }
}