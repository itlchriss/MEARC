package g0001_0100.s0067_add_binary;

// #Easy #String #Math #Bit_Manipulation #Simulation #Programming_Skills_II_Day_5
// #2023_08_11_Time_1_ms_(100.00%)_Space_41.6_MB_(36.86%)

public class Solution {
//@ requires(*The length of the string parameter `a` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The length of the string parameter `b` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `a` and `b` consist only of '0' or '1' characters.*);
//@ requires(*The string parameter `a` and `b` do not contain leading zeros except for the zero itself.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2528\. Count Substrings That Differ by One Character*);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "aba ", t =  "abb "*);
//@ requires(***Output:** 2*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "ab ", t =  "bb "*);
//@ requires(***Output:** 0*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length, t.length <= 100`*);
//@ requires(**   `s` and `t` consist of lowercase English letters.*);
//@ requires(**);
//@ requires(***Follow up:** Consider the performance of your solution. Could you solve it in `O(n^2)` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public int countSubstrings(String s, String t)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The length of the string parameter `t` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` and `t` consist of lowercase English letters.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2527\. Count Substrings with Only One Distinct Letter*);
//@ requires(*Medium*);
//@ requires(*Given a string `s`, return _the number of non-empty substrings of_ `s` _that have only one distinct letter_.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "aaaba "*);
//@ requires(***Output:** 8*);
//@ requires(***Explanation:** The substrings with one distinct letter are  "aaa ",  "aa ",  "a ",  "b ",  "ab ",  "ba ",  "aaba ",  "aaaba ".*);
//@ requires(*Note that some of these substrings repeat and are counted the number of times they occur.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "aaaaaaaaaa "*);
//@ requires(***Output:** 55*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 1000`*);
//@ requires(**   `s[i]` consists of only lowercase English letters.*);
//@ requires(**);
//@ requires(*Method signature: public int countSubstrings(String s)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of lowercase English letters.*);
//@ ensures(*The string result is a binary string and does not contain leading zeros except for the zero itself.*);
//@ ensures(*If the string parameters `a` and `b` are equal to "11" and "1", the string result is equal to "100".*);
//@ ensures(*If the string parameters `a` and `b` are equal to "1010" and "1011", the string result is equal to "10101".*);
//@ ensures(*Given two strings `s` and `t`, return _the number of ways you can choose a non-empty substring of_ `s` _and replace a single character by a different character such that the resulting substring is a substring of_ `t`.*);
//@ ensures(*In other words, find the number of substrings in `s` such that replacing exactly one character in it with a different character results in a substring that is a substring of `t`.*);
//@ ensures(***Explanation:** The two substrings are  "aba " and  "bab ". We can replace the first 'a' with 'b', resulting in  "bba " which is a substring of  "abb ".*);
//@ ensures(***Explanation:** We can only replace the first character 'a' with 'b', resulting in  "bb " which is not a substring of  "bb ".*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameters `s` and `t` are equal to "aba" and "abb", the integer result is equal to 2.*);
//@ ensures(*If the string parameters `s` and `t` are equal to "ab" and "bb", the integer result is equal to 0.*);
//@ ensures(*The integer result is greater than or*);
    public String addBinary(String a, String b) {
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = aArray.length - 1;
        int j = bArray.length - 1;
        int carry = 0;
        //@ maintaining -1 <= i <= aArray.length - 1 && bArray.length - 1 >= j >= -1;
        while (i >= 0 || j >= 0) {
            int sum = (i >= 0 ? aArray[i] - '0' : 0) + (j >= 0 ? bArray[j] - '0' : 0) + carry;
            sb.append(sum % 2);
            carry = sum / 2;
            if (i >= 0) {
                i--;
            }
            if (j >= 0) {
                j--;
            }
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}