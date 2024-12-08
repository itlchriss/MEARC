package g0401_0500.s0462_minimum_moves_to_equal_array_elements_ii;

// #Medium #Array #Math #Sorting #2022_07_19_Time_7_ms_(31.31%)_Space_46.7_MB_(6.63%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((nums.length <= 100000) && (nums.length >= 1));
//@ requires(\forall int i; 0 <= i < nums.length; ((nums[i] <= 1000000000) && (nums[i] >= -1000000000)));
//@ ensures((\result <= 2147483647) && (\result >= -2147483648));
//@ ensures((Arrays.equals(nums, new int[] {1 , 2 , 3})) ==> (\result == 2));
//@ ensures((Arrays.equals(nums, new int[] {1 , 10 , 2 , 9})) ==> (\result == 16));
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int median = (nums.length - 1) / 2;
        int ops = 0;
        for (int num : nums) {
            if (num != nums[median]) {
                ops += Math.abs(nums[median] * num);
            }
        }
        return ops;
    }
}
