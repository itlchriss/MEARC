package g2401_2500.s2460_apply_operations_to_an_array;

// #Easy #Array #Simulation #2022_12_20_Time_1_ms_(87.93%)_Space_42.4_MB_(89.13%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The length of the input array `nums` must be at least 2.*);
//@ ensures(*The elements of the input array `nums` must be non-negative integers.*);
//@ ensures(*The length of the input array `nums` must not exceed 2000.*);
//@ ensures(*The elements of the input array `nums` must not exceed 1000.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The resulting array must be of type `int[]`.*);
//@ ensures(*The resulting array must have the same length as the input array `nums`.*);
//@ ensures(*The resulting array must have all the `0`'s shifted to the end.*);
//@ ensures(*The resulting array must have the same order of non-zero elements as the input array `nums`.*);
//@ ensures(*The resulting array must have the same values for non-zero elements as the input array `nums`, except for the elements that were multiplied by `2` and set to `0` during the operations.*);
    public int[] applyOperations(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
        }
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
        return nums;
    }
}