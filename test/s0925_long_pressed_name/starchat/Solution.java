package g0901_1000.s0925_long_pressed_name;

// #Easy #String #Two_Pointers #2022_03_29_Time_1_ms_(84.87%)_Space_40.1_MB_(93.12%)

public class Solution {
//@ requires(*The length of the string parameter `name` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*The length of the string parameter `typed` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `name` consists of only lowercase English letters.*);
//@ requires(*The string parameter `typed` consists of only lowercase English letters.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1371\. Find the Longest Substring Containing All Repeated Characters with at Least Two Distinct Letters*);
//@ requires(**);
//@ requires(*Hard*);
//@ requires(**);
//@ requires(*Given a string `s`, return _the length of the longest substring that contains at least two distinct characters and each of those characters should appear **at least twice**_. If there is no such substring, return `0`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "aaabbcc "*);
//@ requires(***Output:** 5*);
//@ requires(***Explanation:** The longest substring that contains at least two distinct characters is  "aaabb ". Note that  "aaabbcc " is also a valid answer, but  "aaabbcc " is the longest.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "ababbc "*);
//@ requires(***Output:** 5*);
//@ requires(***Explanation:** The longest substring that contains at least two distinct characters is  "ababb ".*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 104`*);
//@ requires(**   `s` consists of only lowercase English letters.*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve this problem in `O(n)` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public int findLongestSubstring(String s)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(*The length of the string parameter `s` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only lowercase English letters.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 1658\. Minimum Operations to Make a Subsequence*);
//@ requires(**);
//@ requires(*Hard*);
//@ requires(**);
//@ requires(*You are given two arrays `target` and `arr`.*);
//@ requires(**);
//@ requires(*`target` is an array of distinct integers and `arr` is an array of integers that might contain duplicates.*);
//@ requires(**);
//@ requires(*In one operation, you can insert any integer from `arr` into `target` without changing the order of the elements in `target`. However, you can perform this operation **at most once**.*);
//@ requires(**);
//@ requires(*Return _the minimum number of operations needed to make_ `target` _a subsequence of_ `arr`.*);
//@ requires(**);
//@ requires(*A **subsequence** of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements' relative order. For example, `[2,7,10]` is a subsequence of `[1,2,3,4,7,10]`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** target = \[5,1,3\], arr = \[9,4,2,3,4,1\]*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** You can add 5 and 3 from arr to target.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** target = \[6,4,8,1,3,2\], arr = \[4,7,6,2,3,8,6,1]*);
//@ requires(***Output:** 3*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= target.length, arr.length <= 105`*);
//@ requires(**   `1 <= target[i], arr[i] <=*);
//@ ensures(*The boolean result is `true` if it is possible that it was the friends name, with some characters (possibly none) being long pressed.*);
//@ ensures(*The boolean result is `false` if it is not possible that it was the friends name, with some characters (possibly none) being long pressed.*);
//@ ensures(*If the string parameter `name` is equal to "alex" and the string parameter `typed` is equal to "aaleex", the boolean result is `true`.*);
//@ ensures(*If the string parameter `name` is equal to "saeed" and the string parameter `typed` is equal to "ssaaedd", the boolean result is `false`.*);
//@ ensures(*The integer result is the length of the longest substring that contains at least two distinct characters and each of those characters should appear at least twice.*);
//@ ensures(*If there is no such substring, the integer result is 0.*);
//@ ensures(*If the string parameter `s` is equal to "aaabbcc", the integer result is 5.*);
//@ ensures(*If the string parameter `s` is equal to "ababbc", the integer result is 5.*);
    public boolean isLongPressedName(String name, String typed) {
        int i = 0;
        int j = 0;
        char prev = '$';
        if (typed.length() < name.length()) {
            return false;
        }
        while (i < name.length() && j < typed.length()) {
            while (j < typed.length() && typed.charAt(j) != name.charAt(i)) {
                if (typed.charAt(j) != prev) {
                    return false;
                }
                if (j == typed.length() - 1) {
                    return false;
                }
                j++;
            }
            prev = name.charAt(i);
            i++;
            j++;
        }
        if (i < name.length()) {
            return false;
        }
        for (; j < typed.length(); j++) {
            if (typed.charAt(j) != prev) {
                return false;
            }
        }
        return true;
    }
}