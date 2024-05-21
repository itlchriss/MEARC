package g0101_0200.s0164_maximum_gap;

// #Hard #Array #Sorting #Bucket_Sort #Radix_Sort
// #2022_06_25_Time_48_ms_(53.59%)_Space_84.1_MB_(20.66%)

import java.util.Arrays;

public class Solution {
//@ requires(*You must write an algorithm that runs in linear time and uses linear extra space.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [3,6,9,1]*);
//@ requires(*Output: 3*);
//@ requires(*Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [10]*);
//@ requires(*Output: 0*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= nums.length <= 10<sup>5</sup></code>*);
//@ requires(*<code>0 <= nums[i] <= 10<sup>9</sup></code>*);
//@ ensures(*Given an integer array param_nums, the result is the maximum difference between two successive elements in its sorted form.*);
//@ ensures(*If the array contains less than two elements, the result is `0`.*);
//@ ensures(*Explanation: The array contains less than 2 elements, therefore the result is 0.*);
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