package g0201_0300.s0219_contains_duplicate_ii;

// #Easy #Array #Hash_Table #Sliding_Window #2022_07_02_Time_15_ms_(99.09%)_Space_56_MB_(82.82%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*The integer array parameter `nums` consists of integers.*);
//@ requires(*The integer parameter `k` is less than or equal to 100000 and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2528\. Count Substrings That Differ by One Character*);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*In other words, find the number of substrings in `s` such that replacing exactly one character in `s` with a different character yields a substring that is a substring of `t`.*);
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
//@ requires(*Note that some of these substrings repeat and are counted multiple times.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ ensures(*If the boolean result is true, there are at least two distinct indices `i` and `j` in the array such that `nums[i] == nums[j]` and `abs(i - j) <= k`.*);
//@ ensures(*If the boolean result is false, there are no two distinct indices `i` and `j` in the array such that `nums[i] == nums[j]` and `abs(i - j) <= k`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,1] and the integer parameter `k` is equal to 3, the boolean result is equal to true.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,0,1,1] and the integer parameter `k` is equal to 1, the boolean result is equal to true.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,1,2,3] and the integer parameter `k` is equal to 2, the boolean result is equal to false.*);
//@ ensures(*Given two strings `s` and `t`, return _the number of ways you can choose a non-empty substring of_ `s` _and replace a single character by a different character such that the resulting substring is a substring of_ `t`_.*);
//@ ensures(***Explanation:** The two substrings are  "aba " and  "bab ". We can replace the first 'a' with 'b', resulting in  "bba " which is a substring of  "abb ".*);
//@ ensures(***Explanation:** We can only replace the first character 'a' with 'b', resulting in  "bb " which is not a substring of  "bb ".*);
//@ ensures(*If the integer result is equal to the number of substrings in `s` such that replacing exactly one character in `s` with a different character yields a substring that is a substring of `t`, the integer result is equal to the method return value.*);
//@ ensures(*If the string parameter `s` is equal to "aba" and the string parameter `t` is equal to "abb", the integer result is equal to 2.*);
//@ ensures(*If the string parameter `s` is equal to "ab" and the string parameter `t` is equal to "bb", the integer result is equal to 0.*);
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        //@ maintaining 0 <= i <= nums.length;
        for (int i = 0; i < len; i++) {
            Integer index = map.put(nums[i], i);
            if (index != null && Math.abs(index - i) <= k) {
                return true;
            }
        }
        return false;
    }
}