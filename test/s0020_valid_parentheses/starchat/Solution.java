package g0001_0100.s0020_valid_parentheses;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #String #Stack
// #Data_Structure_I_Day_9_Stack_Queue #Udemy_Strings #Big_O_Time_O(n)_Space_O(n)
// #2023_08_09_Time_2_ms_(90.49%)_Space_40.1_MB_(98.14%)

import java.util.Stack;

@SuppressWarnings("java:S1149")
public class Solution {
//@ requires(*The string parameter `s` is less than or equal to 10000 characters long.*);
//@ requires(*The string parameter `s` consists only of the characters '()', '[]', '{}'.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2528\. Maximum Number of Non-Overlapping Substrings with a Given Length*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given a string `s` and an integer `k`, return _the maximum number of non-overlapping substrings of length_ `k` _that contain only lowercase English letters_.*);
//@ requires(**);
//@ requires(*A substring is a contiguous sequence of characters within a string.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s = "adefaddaccc", k = 3*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:** The 3 substrings are "aad", "fdd", "ccc".*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s = "abbcccaa", k = 3*);
//@ requires(***Output:** 0*);
//@ requires(***Explanation:** There are no substrings of length 3.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 105`*);
//@ requires(**   `s` consists of only lowercase English letters.*);
//@ requires(**   `1 <= k <= s.length`*);
//@ requires(**);
//@ requires(*Method signature: public int maxNonOverlappingSubstrings(String s, int k)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists only of lowercase English letters.*);
//@ requires(*The integer parameter `k` is less than or equal to the length of the string parameter `s` and is greater than or equal to 1.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2950\. Find the Minimum Number of Flips to Make a Binary String Alternating*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*You are given a binary string `s` of length `n`.*);
//@ requires(**);
//@ requires(*A binary string is **alternating** if no two adjacent characters are equal. For example, the strings `"010"`, `"1010"`, and `"111000"` are alternating, while the string `"000111"` is not.*);
//@ requires(**);
//@ requires(*A **flip** is changing a `'0'` to a `'1'` or vice versa at **exactly one** place in the string.*);
//@ requires(**);
//@ requires(*Return _the **minimum** number of flips to make_ `s` _alternating_.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s = "110010"*);
//@ requires(***Output:** 1*);
//@ requires(***Explanation:** We can flip s[0] to '1' to make s = "111010", which is alternating.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s = "010"*);
//@ requires(***Output:** 0*);
//@ requires(***Explanation:** s is already alternating.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 105`*);
//@ requires(**   `s[i]` is either `'0'` or `'1'`.*);
//@ requires(**);
//@ requires(*Method signature: public int minFlips(String s)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All characters in the string parameter `s` are either '0' or '1'.*);
//@ ensures(*The boolean result is true if the input string is valid according to the rules mentioned in the problem description.*);
//@ ensures(*The boolean result is false if the input string is not valid according to the rules mentioned in the problem description.*);
//@ ensures(*If the string parameter `s` is equal to "()", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "()[]{}", the boolean result is true.*);
//@ ensures(*If the string parameter `s` is equal to "(]", the boolean result is false.*);
//@ ensures(*If the string parameter `s` is equal to "([)]", the boolean result is false.*);
//@ ensures(*If the string parameter `s` is equal to "{[]}", the boolean result is true.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "adefaddaccc" and the integer parameter `k` is equal to 3, the integer result is equal to 3.*);
//@ ensures(*If the string parameter `s` is equal to "abbcccaa" and the integer parameter `k` is equal to 3, the integer result is equal to 0.*);
//@ ensures(*The integer result is greater than or*);
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        //@ loop_invariant 0 <= i <= s.length();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}