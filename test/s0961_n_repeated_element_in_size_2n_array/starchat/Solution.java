package g0901_1000.s0961_n_repeated_element_in_size_2n_array;

// #Easy #Array #Hash_Table #2022_03_31_Time_1_ms_(87.33%)_Space_54.1_MB_(66.98%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is equal to 2 times the integer parameter `n`.*);
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 10000.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 10000.*);
//@ requires(*The integer array parameter `nums` contains `n + 1` unique elements.*);
//@ requires(*Exactly one element of the integer array parameter `nums` is repeated `n` times.*);
//@ requires(*The integer parameter `n` is greater than or equal to 2 and is less than or equal to 5000.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1019\. Next Greater Element III*);
//@ requires(*Hard*);
//@ requires(*Given a positive integer `n`, find the smallest integer which has exactly the same digits existing in the integer `n` and is greater in value than `n`. If no such positive integer exists, return `-1`.*);
//@ requires(**);
//@ requires(***Note** that the returned integer should fit in **32-bit** integer, if there is a valid answer but it does not fit in **32-bit** integer, return `-1`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** n = 12*);
//@ requires(***Output:** 21*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** n = 21*);
//@ requires(***Output:** -1*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= n <= 231 - 1`*);
//@ requires(**);
//@ requires(*Method signature: public int nextGreaterElement(int n)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The integer parameter `n` is greater than or equal to 1 and is less than or equal to 231 - 1.*);
//@ requires(*If there is a valid answer but it does not fit in **32-bit** integer, return `-1`.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 1234\. Largest Multiple of Three*);
//@ requires(*Medium*);
//@ requires(*Given an integer array of integers `nums`, return _the largest multiple of three that is present in_ `nums`.*);
//@ requires(**);
//@ requires(*If there is no such multiple of three, return `-1`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[3,6,5,1,8\]*);
//@ requires(***Output:** 18*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[4]*);
//@ requires(***Output:** -1*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** nums = \[1,2,3,4,6,9\]*);
//@ requires(***Output:** 18*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 104`*);
//@ requires(**   `1 <= nums[i] <= 109`*);
//@ requires(**);
//@ requires(*Method signature: public int largestMultipleOfThree(int[] nums)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `nums` is greater than or equal to 1 and is less than or equal to 104.*);
//@ requires(*All values in the integer array parameter `nums` are greater than or equal to 1 and is less than or equal to 109.*);
//@ requires(*If there is a valid answer but it does not fit in **32-bit** integer, return `-1`.*);
//@ requires(*If the integer array parameter `nums` is equal to [*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to 10000.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,3], the integer result is equal to 3.*);
//@ ensures(*If the integer array parameter `nums` is equal to [2,1,2,5,3,2], the integer result is equal to 2.*);
//@ ensures(*If the integer array parameter `nums` is equal to [5,1,5,2,5,3,5,4], the integer result is equal to 5.*);
//@ ensures(*The integer result is greater than or equal to 1 and is less than or equal to 231 - 1.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,3], the integer result is equal to 21.*);
//@ ensures(*If the integer array parameter `nums` is equal to [2,1,2,5,3,2], the integer result is equal to -1.*);
//@ ensures(*If the integer array parameter `nums` is equal to [5,1,5,2,5,3,5,4], the integer result is equal to -1.*);
    public int repeatedNTimes(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return -1;
    }
}