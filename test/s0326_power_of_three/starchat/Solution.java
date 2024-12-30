package g0301_0400.s0326_power_of_three;

// #Easy #Top_Interview_Questions #Math #Recursion
// #2022_07_09_Time_18_ms_(85.35%)_Space_47.9_MB_(14.68%)

public class Solution {
    // regular method that has a loop
//@ requires(*The integer parameter `n` is less than or equal to 231 - 1 and is greater than or equal to -231.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2953\. Maximum Score From Removing Substrings*);
//@ requires(*Hard*);
//@ requires(*You are given a string `s` of lowercase English letters and an array of pairs of integers `pairs` where `pairs[i] = [lefti, righti]` indicates the `ith` pair.*);
//@ requires(**);
//@ requires(*You can choose any pair of indices and remove the substring `s[lefti], s[lefti + 1],..., s[righti]` from `s` of any number of times. Note that the indices of the string are **0-indexed**.*);
//@ requires(**);
//@ requires(*Return _the maximum score you can obtain after removing any number of substrings_.*);
//@ requires(**);
//@ requires(*The **score** of a string is the number of **distinct** characters in it.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "adefaddaccc ", pairs = \[\[3,4\],\[6,9\],\[1,8\]\]*);
//@ requires(***Output:** 9*);
//@ requires(***Explanation:** The substring s\[3,4\] =  "defa" has 3 distinct characters.*);
//@ requires(*The substring s\[6,9\] =  "addac" has 4 distinct characters.*);
//@ requires(*The substring s\[1,8\] =  "adefadda" has 7 distinct characters.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "abbaccd ", pairs = \[\[0,2\],\[3,4\],\[5,6\]\]*);
//@ requires(***Output:** 8*);
//@ requires(***Explanation:** The substring s\[0,2\] =  "abb" has 3 distinct characters.*);
//@ requires(*The substring s\[3,4\] =  "ac" has 2 distinct characters.*);
//@ requires(*The substring s\[5,6\] =  "cd" has 2 distinct characters.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 105`*);
//@ requires(**   `s` consists of lowercase English letters.*);
//@ requires(**   `0 <= pairs.length <= 105`*);
//@ requires(**   `0 <= lefti <= righti < s.length`*);
//@ requires(**   At most `5000` calls will be made to `removeSubstrings`.*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve this problem in `O(n^2)` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public int maximumScore(String s, int[][] pairs)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 105 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of lowercase English letters.*);
//@ requires(*The length of the integer array parameter `pairs` is less than or equal to 105 and is greater than or equal to 0.*);
//@ requires(*Each element of the integer array parameter `pairs` is an array of two integers.*);
//@ requires(*The first integer of each element of the integer array parameter `pairs` is less than or equal to the second integer and is greater than or equal to 0.*);
//@ requires(*The first integer of each element of the integer array parameter `pairs` is less than or equal to the length of the string parameter `s` minus 1.*);
//@ requires(*The second integer of each element of the integer array parameter `pairs` is less than or equal to the length of the string parameter `s` minus 1.*);
//@ requires(*If the integer array parameter `pairs` is equal to \[\[0,2\],\[3,4\],\[5,6\]\] and the*);
//@ ensures(*If the integer parameter `n` is a power of three, the boolean result is true.*);
//@ ensures(*If the integer parameter `n` is not a power of three, the boolean result is false.*);
//@ ensures(*The integer result is either true or false.*);
//@ ensures(*If the integer parameter `n` is equal to 27, the boolean result is true.*);
//@ ensures(*If the integer parameter `n` is equal to 0, the boolean result is false.*);
//@ ensures(*If the integer parameter `n` is equal to 9, the boolean result is true.*);
//@ ensures(*The integer result is less than or equal to the length of the string parameter `s` and is greater than or equal to 0.*);
//@ ensures(*The integer result is the maximum score that can be obtained after removing any number of substrings.*);
//@ ensures(*If the integer array parameter `pairs` is equal to \[\[3,4\],\[6,9\],\[1,8\]\] and the string parameter `s` is equal to  "adefaddaccc ", the integer result is equal to 9.*);
    public boolean isPowerOfThree(int n) {
        if (n < 3 && n != 1) {
            return false;
        }
        while (n != 1) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}