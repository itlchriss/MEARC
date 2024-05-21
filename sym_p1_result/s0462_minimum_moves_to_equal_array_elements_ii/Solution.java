package g0401_0500.s0462_minimum_moves_to_equal_array_elements_ii;

// #Medium #Array #Math #Sorting #2022_07_19_Time_7_ms_(31.31%)_Space_46.7_MB_(6.63%)

import java.util.Arrays;

public class Solution {
//@ requires(*In one move, you can increment or decrement an element of the array by `1`.*);
//@ requires(*Test cases are designed so that the answer will fit in a 32-bit integer.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [1,2,3]*);
//@ requires(*Output: 2*);
//@ requires(*Explanation: Only two moves are needed (remember each move increments or decrements one element): [1,2,3] => [2,2,3] => [2,2,2]*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [1,10,2,9]*);
//@ requires(*Output: 16*);
//@ requires(*Constraints:*);
//@ requires(*`n == nums.length`*);
//@ requires(*<code>1 <= nums.length <= 10<sup>5</sup></code>*);
//@ requires(*<code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code>*);
//@ ensures(*Given an integer array param_nums of size `n`, the result is the minimum number of moves required to make all array elements equal.*);
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