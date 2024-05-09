package g0001_0100.s0031_next_permutation;

// #Medium #Top_100_Liked_Questions #Array #Two_Pointers #Big_O_Time_O(n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_42_MB_(90.28%)

public class Solution {
    //
//@ requires(*The length of the integer array `nums` is greater than or equal to 1 and is less than or equal to 100.*);
//@ requires(*All values in the integer array `nums` are greater than or equal to 0 and are less than or equal to 100.*);
//@ ensures(*If the integer array parameter `nums` is rearranged into the lexicographically next greater permutation of numbers, the integer array result is equal to the rearranged `nums`.*);
//@ ensures(*If the integer array parameter `nums` cannot be rearranged into the lexicographically next greater permutation of numbers, the integer array result is equal to the sorted `nums` in ascending order.*); public void nextPermutation(int[] nums) {
    //     if (nums == null || nums.length <= 1) {
    //         return;
    //     }
    //     int i = nums.length - 2;
    //     while (i >= 0 && nums[i] >= nums[i + 1]) {
    //         i--;
    //     }
    //     if (i >= 0) {
    //         int j = nums.length - 1;
    //         while (nums[j] <= nums[i]) {
    //             j--;
    //         }
    //         swap(nums, i, j);
    //     }
    //     reverse(nums, i + 1, nums.length - 1);
    // }

    //@ requires 0 <= i <= j < nums.length;
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //@ requires 0 <= i <= j < nums.length;
    private void reverse(int[] nums, int i, int j) {
        //@ maintaining i <= j || j == i - 1;               
        while (i < j) {
            swap(nums, i++, j--);
        }
    }
}