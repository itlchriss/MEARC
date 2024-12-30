package g0401_0500.s0454_4sum_ii;

// #Medium #Top_Interview_Questions #Array #Hash_Table
// #2022_07_18_Time_133_ms_(95.19%)_Space_42.4_MB_(88.53%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*The length of the integer array parameters `nums1`, `nums2`, `nums3`, and `nums4` is equal to `n`.*);
//@ requires(*The length of the integer array parameters `nums1`, `nums2`, `nums3`, and `nums4` is less than or equal to 200 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameters `nums1`, `nums2`, `nums3`, and `nums4` are less than or equal to 2^28 and is greater than or equal to -2^28.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2527\. Maximum Count of Positive Integer and Negative Integer*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given an integer array `nums` sorted in **non-decreasing** order, return _the maximum between the number of positive integers and the number of negative integers_.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[1,2,3,4\]*);
//@ requires(***Output:** 4*);
//@ requires(***Explanation:** There are 1 positive integer and 3 negative integers. The maximum count is 4.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[0,1\]*);
//@ requires(***Output:** 1*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 100`*);
//@ requires(**   `-100 <= nums[i] <= 100`*);
//@ requires(**   `nums` is sorted in **non-decreasing** order.*);
//@ requires(**);
//@ requires(***Follow up:** Could you solve it in `O(n)` time and/or in `O(1)` space?*);
//@ requires(**);
//@ requires(*Method signature: public int maximumCount(int[] nums)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 100 and is greater than or equal to -100.*);
//@ requires(*The integer array parameter `nums` is sorted in non-decreasing order.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2414\. Length of the Longest Alphabetical Continuous Substring*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*An **alphabetical continuous string** is a string consisting of consecutive letters in the alphabet. In other words, it is any substring of the string `"abcdefghijklmnopqrstuvwxyz"`.*);
//@ requires(**);
//@ requires(**   For example, `"abc"` is an alphabetical continuous string, while `"acb"` and `"za"` are not.*);
//@ requires(**);
//@ requires(*Given a string `inputstring` consisting of lowercase letters only, return _the number of digits in `inputstring` that divide_ `inputstring`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** inputstring =  "abacaba"*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** The longest alphabetical continuous substring is "abacaba", which contains 2 digits (a and b).*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** inputstring =*);
//@ ensures(*The integer result is less than or equal to the product of the length of the integer array parameters `nums1`, `nums2`, `nums3`, and `nums4`.*);
//@ ensures(*The integer result is greater than or equal to 1.*);
//@ ensures(*If the integer array parameters `nums1`, `nums2`, `nums3`, and `nums4` are equal to [1,2], [-2,-1], [-1,2], and [0,2], the integer result is equal to 2.*);
//@ ensures(*If the integer array parameters `nums1`, `nums2`, `nums3`, and `nums4` are equal to [0], [0], [0], and [0], the integer result is equal to 1.*);
//@ ensures(*If the integer result is less than the length of the integer array parameter `nums`, not all the values in the integer array parameter `nums` are positive or negative.*);
//@ ensures(*If the integer result is equal to the length of the integer array parameter `nums` and the first value of the integer array parameter `nums` is positive, all the values in the integer array parameter `nums` are positive.*);
//@ ensures(*If the integer result is equal to the length of the integer array parameter `nums` and the first value of the integer array parameter `nums` is negative, all the values in the integer array parameter `nums` are negative.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,4], the integer result is equal to 4.*);
//@ ensures(*If the integer array parameter `nums` is equal to [0,1], the integer result is equal to 1.*);
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int k : nums3) {
            for (int i : nums4) {
                int sum = k + i;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        for (int k : nums1) {
            for (int i : nums2) {
                int m = -(k + i);
                count += map.getOrDefault(m, 0);
            }
        }
        return count;
    }
}