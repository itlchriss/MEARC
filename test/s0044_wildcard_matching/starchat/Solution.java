package g0001_0100.s0044_wildcard_matching;

// #Hard #Top_Interview_Questions #String #Dynamic_Programming #Greedy #Recursion
// #Udemy_Dynamic_Programming #2023_08_11_Time_2_ms_(99.87%)_Space_43.2_MB_(99.49%)

public class Solution {
//@ requires(*The length of the string parameter `inputString` is less than or equal to 2000 and is greater than or equal to 0.*);
//@ requires(*The string parameter `inputString` consists of only lowercase English letters.*);
//@ requires(*The length of the string parameter `pattern` is less than or equal to 2000 and is greater than or equal to 0.*);
//@ requires(*The string parameter `pattern` consists of only lowercase English letters, '?', or '*'.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1035\. Uncrossed Lines*);
//@ requires(*Medium*);
//@ requires(*We write the integers of `A` and `B` (in the order they are given) on two separate horizontal lines.*);
//@ requires(**);
//@ requires(*Now, we may draw connecting lines: a straight line connecting two numbers `A[i]` and `B[j]` such that:*);
//@ requires(**);
//@ requires(**   `A[i] == B[j]`;*);
//@ requires(**   The line we draw does not intersect any other connecting (non-horizontal) line.*);
//@ requires(**);
//@ requires(*Note that connecting lines cannot intersect even at the endpoints (i.e., each number can only belong to one connecting line).*);
//@ requires(**);
//@ requires(*Return the maximum number of connecting lines we can draw in this way.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** A = \[1,4,2\], B = \[1,2,4\]*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** We can draw 2 uncrossed lines as in the diagram.*);
//@ requires(**);
//@ requires(*We cannot draw 3 uncrossed lines, because the line from A\[1\] = 1 to B\[2\] = 4 will intersect the line from A\[2\] = 2 to B\[1\] = 2.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** A = \[2,5,1,2,5\], B = \[10,5,2,1,5,2\]*);
//@ requires(***Output:** 3*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** A = \[1,3,7,1,7,5\], B = \[1,9,2,5,1\]*);
//@ requires(***Output:** 2*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= A.length <= 500`*);
//@ requires(**   `1 <= B.length <= 500`*);
//@ requires(**   `1 <= A[i], B[i] <= 2000`*);
//@ requires(**);
//@ requires(***Follow-up:** Consider the number of operations. Is there a less time/space complexity way to solve this problem?*);
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