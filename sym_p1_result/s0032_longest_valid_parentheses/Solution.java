package g0001_0100.s0032_longest_valid_parentheses;

// #Hard #Top_100_Liked_Questions #String #Dynamic_Programming #Stack #Big_O_Time_O(n)_Space_O(1)
// #2023_08_09_Time_1_ms_(100.00%)_Space_41.4_MB_(85.22%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 30000 and is greater than or equal to 0.*);
//@ requires(*The string parameter `s` only contains the characters '(' and ')'.*);
//@ ensures(*The integer result is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "(()", the integer result is equal to 2.*);
//@ ensures(*If the string parameter `s` is equal to ")()())", the integer result is equal to 4.*);
//@ ensures(*If the string parameter `s` is an empty string, the integer result is equal to 0.*);
    public int longestValidParentheses(String s) {
        int max = 0;
        int left = 0;
        int right = 0;
        int n = s.length();
        char ch;
        for (int i = 0; i < n; i++) {
            ch = s.charAt(i);
            if (ch == '(') {
                left++;
            } else {
                right++;
            }
            if (right > left) {
                left = 0;
                right = 0;
            }
            if (left == right) {
                max = Math.max(max, left + right);
            }
        }
        left = 0;
        right = 0;
        for (int i = n - 1; i >= 0; i--) {
            ch = s.charAt(i);
            if (ch == '(') {
                left++;
            } else {
                right++;
            }
            if (left > right) {
                left = 0;
                right = 0;
            }
            if (left == right) {
                max = Math.max(max, left + right);
            }
        }
        return max;
    }
}