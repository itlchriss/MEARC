package g0901_1000.s0908_smallest_range_i;

// #Easy #Array #Math #2022_03_28_Time_2_ms_(88.84%)_Space_41.9_MB_(99.76%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 10000 and is greater than or equal to 0.*);
//@ requires(*The integer parameter `k` is less than or equal to 10000 and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1525\. Number of Good Ways to Split a String*);
//@ requires(*Hard*);
//@ requires(*You are given a string `s`. You can split the string at any position and move each part into a new array.*);
//@ requires(**);
//@ requires(*In one move, you can choose two adjacent parts of the array and merge them into one part. However, you can only merge parts of the array that have the same character.*);
//@ requires(**);
//@ requires(*Return _the number of good ways to split the string_.*);
//@ requires(**);
//@ requires(*A way to split a string is good if you can split the string in such a way that you can change **at most one** part into any other character.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "aacaba "*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** There are 5 good ways of splitting s:*);
//@ requires(*Split at index 3:  "aacaba " ->  "aa " +  "caba "*);
//@ requires(*Split at index 4:  "aacaba " ->  "aaca " +  "ba "*);
//@ requires(*Split at index 5:  "aacaba " ->  "aacab " +  "a "*);
//@ requires(*Split at index 6:  "aacaba " ->  "aacaba " +  ""*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "abcd "*);
//@ requires(***Output:** 1*);
//@ requires(***Explanation:** Splitting  "abcd " into  "a " +  "bcd " is not good because you can split s into  "ab " +  "cd " and change one part to any other character.*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** s =  "aaaaa "*);
//@ requires(***Output:** 4*);
//@ requires(***Explanation:** All good ways of splitting s are:*);
//@ requires(*Split at index 1:  "aaaaa " ->  "a " +  "aaaa "*);
//@ requires(*Split at index 2:  "aaaaa " ->  "aa " +  "aaa "*);
//@ requires(*Split at index 3:  "aaaaa " ->  "aaa " +  "aa "*);
//@ requires(*Split at index 4:  "aaaaa " ->  "aaaa " +  "a "*);
//@ requires(**);
//@ requires(***Example 4:***);
//@ requires(**);
//@ requires(***Input:** s =  "acbadbaada "*);
//@ requires(***Output:** 2*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 100`*);
//@ requires(**   `s` consists of lowercase English letters only.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1] and the integer parameter `k` is equal to 0, the integer result is equal to 0.*);
//@ ensures(*If the integer array parameter `nums` is equal to [0,10] and the integer parameter `k` is equal to 2, the integer result is equal to 6.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,3,6] and the integer parameter `k` is equal to 3, the integer result is equal to 0.*);
    public int smallestRangeI(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min + k >= max - k) {
            return 0;
        }
        return (max - k) - (min + k);
    }
}