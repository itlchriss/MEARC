package g0401_0500.s0462_minimum_moves_to_equal_array_elements_ii;

// #Medium #Array #Math #Sorting #2022_07_19_Time_7_ms_(31.31%)_Space_46.7_MB_(6.63%)

import java.util.Arrays;

public class Solution {
//@ requires(*The integer array parameter `nums` must not be null.*);
//@ requires(*The integer array parameter `nums` must have a size greater than or equal to 1 and less than or equal to 100000.*);
//@ requires(*All elements in the integer array parameter `nums` must be greater than or equal to -1000000000 and less than or equal to 1000000000.*);
//@ requires(*Each move can only increment or decrement an element in the array by 1.*);
//@ ensures(*The integer result is the minimum number of moves required to make all elements in the integer array parameter `nums` equal.*);
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