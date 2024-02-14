package g0001_0100.s0031_next_permutation;

// #Medium #Top_100_Liked_Questions #Array #Two_Pointers #Big_O_Time_O(n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_42_MB_(90.28%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of 0 to 100.*);
	//@ ensures(*The input array `nums` is modified to represent the lexicographically next greater permutation of numbers.*);
	//@ ensures(*If such an arrangement is not possible, the input array `nums` is modified to be sorted in ascending order.*);
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }
}