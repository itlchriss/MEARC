package g0101_0200.s0164_maximum_gap;

// #Hard #Array #Sorting #Bucket_Sort #Radix_Sort
// #2022_06_25_Time_48_ms_(53.59%)_Space_84.1_MB_(20.66%)

import java.util.Arrays;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000000000 and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2527\. Maximum Count of Positive Integer and Negative Integer*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*Given an integer array `nums` sorted in **non-decreasing order**, return _the maximum between the number of positive integers and the number of negative integers_.*);
//@ requires(**);
//@ requires(**   In other words, if the number of positive integers in `nums` is `pos` and the number of negative integers is `neg`, then return the maximum of `pos` and `neg`.*);
//@ requires(**);
//@ requires(***Note** that `0` is neither positive nor negative.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[-2,-1,-1,0,1,2\]*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:** There are 3 positive integers and 3 negative integers.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[0\]*);
//@ requires(***Output:** 0*);
//@ requires(***Explanation:** There are no positive integers and no negative integers.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 1000`*);
//@ requires(**   `-1000 <= nums[i] <= 1000`*);
//@ requires(**   `nums` is sorted in **non-decreasing order**.*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve this problem in `O(1)` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public int maximumCount(int[] nums)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000 and is greater than or equal to -1000.*);
//@ requires(*The integer array parameter `nums` is sorted in non-decreasing order.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2951\. Find the Peaks*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*You are given a **0-indexed** array `mountain`. Your task is to find all the **peaks** in the `mountain` array.*);
//@ requires(**);
//@ requires(*Return _an array that consists of the indices of **peaks** in the given array in **any order**._*);
//@ requires(**);
//@ requires(***Notes:***);
//@ requires(**);
//@ requires(**   A **peak** is defined as an element that is strictly greater than its neighboring elements.*);
//@ requires(**   The first and last elements of the array are **not** considered peaks.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** mountain = \[2,4,1,3,5\]*);
//@ requires(***Output:** \[3,4\]*);
//@ requires(***Explanation:** There are two peaks: 3 and 4.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** mountain = \[1,2,3,4,5\]*);
//@ requires(***Output:** \[4\]*);
//@ requires(***Explanation:** There is only one peak: 4.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `3 <= mountain.length <= 100`*);
//@ requires(**   `1 <= mountain[i] <= 1000`*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve this problem in `O(log(n))` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public List<Integer> findPeaks(int[] mountain)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `mountain` is less than or equal to*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to 1000000000.*);
//@ ensures(*If the integer array parameter `nums` is equal to [3,6,9,1], the integer result is equal to 3.*);
//@ ensures(*If the integer array parameter `nums` is equal to [10], the integer result is equal to 0.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to 1000.*);
//@ ensures(*If the integer array parameter `nums` is equal to \[-2,-1,-1,0,1,2\], the integer result is equal to 3.*);
//@ ensures(*If the integer array parameter `nums` is equal to \[0\], the integer result is equal to 0.*);
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int ret = Integer.MIN_VALUE;
        Arrays.sort(nums);
        //@ maintaining 0 <= i <= nums.length;
        for (int i = 0; i < nums.length - 1; i++) {
            if ((nums[i + 1] - nums[i]) > ret) {
                ret = (nums[i + 1] - nums[i]);
            }
        }
        return ret;
    }
}