package g0401_0500.s0462_minimum_moves_to_equal_array_elements_ii;

// #Medium #Array #Math #Sorting #2022_07_19_Time_7_ms_(31.31%)_Space_46.7_MB_(6.63%)

import java.util.Arrays;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000000000 and is greater than or equal to -1000000000.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2528\. Count Substrings That Differ by One Character and Have Same Length*);
//@ requires(*Medium*);
//@ requires(*Given two strings `s` and `t`, return _the number of substrings in_ `s` _that differ from some substring in_ `t` _by exactly one character and have the same length_.*);
//@ requires(**);
//@ requires(*A substring is a contiguous sequence of characters within a string.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "aba ", t =  "abb "*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** The two substrings are  "ba " and  "ab ". *);
//@ requires(*"ba " differs from  "bb " by one character: 'b' / 'b'.*);
//@ requires(*"ab " differs from  "bb " by one character: 'a' / 'b'.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "ece ", t =  "ece "*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:** The 3 substrings are  "ece ",  "ce ", and  "e ". *);
//@ requires(*"ece " is identical to  "ece ".*);
//@ requires(*"ce " differs from  "ce " by one character: 'e' / 'c'.*);
//@ requires(*"e " differs from  "e " by one character: 'e' / 'e'.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length, t.length <= 105`*);
//@ requires(**   `s` and `t` contain only lowercase English letters.*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve this problem in `O(n)` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public int countSubstrings(String s, String t)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*The length of the string parameter `t` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` and `t` contain only lowercase English letters.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2527\. Count Substrings That Differ by One Character*);
//@ requires(*Medium*);
//@ requires(*Given two strings `s` and `t`, return _the number of substrings in_ `s` _that differ from some substring in_ `t` _by exactly one character_.*);
//@ requires(**);
//@ requires(*A substring is a contiguous sequence of characters within a string.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "aba ", t =  "abb "*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** The two substrings are  "ba " and  "ab ". *);
//@ requires(*"ba " differs from  "bb " by one character: 'b' / 'b'.*);
//@ requires(*"ab " differs from  "bb " by one character: 'a' / 'b'.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "ece ", t =  "ece "*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:** The 3 substrings are  "ece ",  "ce ", and  "e ". *);
//@ requires(*"ece " is identical to  "ece ".*);
//@ requires(*"ce " differs from  "ce " by one character: 'e' / 'c'.*);
//@ requires(*"e*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to 1000000000.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3], the integer result is equal to 2.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,10,2,9], the integer result is equal to 16.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to 1000000000.*);
//@ ensures(*If the string parameter `s` is equal to "aba" and the string parameter `t` is equal to "abb", the integer result is equal to 2.*);
//@ ensures(*If the string parameter `s` is equal to "ece" and the string parameter `t` is equal to "ece", the integer result is equal to 3.*);
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int median = (nums.length - 1) / 2;
        int ops = 0;
        for (int num : nums) {
            if (num != nums[median]) {
                ops += Math.abs(nums[median] - num);
            }
        }
        return ops;
    }
}