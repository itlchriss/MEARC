package g0401_0500.s0462_minimum_moves_to_equal_array_elements_ii;

// #Medium #Array #Math #Sorting #2022_07_19_Time_7_ms_(31.31%)_Space_46.7_MB_(6.63%)

import java.util.Arrays;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All the values in the integer array parameter `nums` are less than or equal to 1000000000 and are greater than or equal to -1000000000.*);
//@ ensures(*The integer result is less than or equal to the maximum value of java integer and is greater than or equal to the minimum value of java integer.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3], the integer result is equal to 2.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,10,2,9], the integer result is equal to 16.*);
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