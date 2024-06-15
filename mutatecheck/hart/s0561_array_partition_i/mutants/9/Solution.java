package g0501_0600.s0561_array_partition_i;

// #Easy #Array #Sorting #Greedy #Counting_Sort
// #2022_08_03_Time_14_ms_(84.99%)_Space_44.2_MB_(95.29%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires(nums.length % 2 == 0);
//@ requires(\forall int i; 0 <= i < nums.length; ((nums[i] <= 10000) && (nums[i] >= -10000)));
//@ requires((nums.length <= 10000) && (nums.length >= 1));
//@ ensures((Arrays.equals(nums, new int[] {1 , 4 , 3 , 2})) ==> (\result == 4));
//@ ensures((Arrays.equals(nums, new int[] {6 , 2 , 6 , 5 , 1 , 2})) ==> (\result == 9));
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i = i - 2) {
            sum += Math.min(nums[i], nums[i + 1]);
        }
        return sum;
    }
}
