package g0001_0100.s0044_wildcard_matching;

// #Hard #Top_Interview_Questions #String #Dynamic_Programming #Greedy #Recursion
// #Udemy_Dynamic_Programming #2023_08_11_Time_2_ms_(99.87%)_Space_43.2_MB_(99.49%)

public class Solution {
//@ requires(*Given an input string (`s`) and a pattern (`p`), implement wildcard pattern matching with support for `'?'*);
//@ requires(*` and `''` where:*);
//@ requires(*`'?'*);
//@ requires(*` Matches any single character.*);
//@ requires(*`''` Matches any sequence of characters (including the empty sequence).*);
//@ requires(*The matching should cover the entire input string (not partial).*);
//@ requires(*Example 1:*);
//@ requires(*Input: s = "aa", p = "a"*);
//@ requires(*Output: false*);
//@ requires(*Explanation: "a" does not match the entire string "aa".*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = "aa", p = "\"*);
//@ requires(*Output: true*);
//@ requires(*Explanation: '\' matches any sequence.*);
//@ requires(*Example 3:*);
//@ requires(*Input: s = "cb", p = "?*);
//@ requires(*a"*);
//@ requires(*Output: false*);
//@ requires(*Explanation: '?'*);
//@ requires(*matches 'c', but the second letter is 'a', which does not match 'b'.*);
//@ requires(*Example 4:*);
//@ requires(*Input: s = "adceb", p = "\a\b"*);
//@ requires(*Output: true*);
//@ requires(*Explanation: The first '\' matches the empty sequence, while the second '\' matches the substring "dce".*);
//@ requires(*Example 5:*);
//@ requires(*Input: s = "acdcb", p = "a\c?b"*);
//@ requires(*Output: false*);
//@ requires(*Constraints:*);
//@ requires(*`0 <= s.length, p.length <= 2000`*);
//@ requires(*`s` contains only lowercase English letters.*);
//@ requires(*`p` contains only lowercase English letters, `'?'*);
//@ requires(*` or `''`.*);
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