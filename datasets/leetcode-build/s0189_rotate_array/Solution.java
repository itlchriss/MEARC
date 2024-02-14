package g0101_0200.s0189_rotate_array;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Math #Two_Pointers
// #Algorithm_I_Day_2_Two_Pointers #Udemy_Arrays #Big_O_Time_O(n)_Space_O(1)
// #2022_06_27_Time_0_ms_(100.00%)_Space_58_MB_(96.22%)

public class Solution {
    private void reverse(int[] nums, int l, int r) {
        while (l <= r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The value of `k` is non-negative.*);
	//@ ensures(*The elements of the input array `nums` are rotated to the right by `k` steps.*);
	//@ ensures(*The order of the elements in the rotated array is correct.*);
	//@ ensures(*The rotated array is stored in the same input array `nums`.*);
	//@ ensures(*The length of the rotated array is the same as the length of the input array `nums`.*);
	//@ ensures(*The elements of the rotated array are the same as the elements of the input array `nums`, but in a different order.*);

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int t = n - (k % n);
        reverse(nums, 0, t - 1);
        reverse(nums, t, n - 1);
        reverse(nums, 0, n - 1);
    }
}