package g0001_0100.s0034_find_first_and_last_position_of_element_in_sorted_array;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Binary_Search
// #Algorithm_II_Day_1_Binary_Search #Binary_Search_I_Day_5 #Big_O_Time_O(log_n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_44.3_MB_(89.57%)

public class Solution {
//@ requires(*Given an array of integers param_nums sorted in non-decreasing order, find the starting and ending position of a given param_target value.*);
//@ requires(*You must write an algorithm with `O(log n)` runtime complexity.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [5,7,7,8,8,10], target = 8*);
//@ requires(*Output: [3,4]*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [5,7,7,8,8,10], target = 6*);
//@ requires(*Output: [-1,-1]*);
//@ requires(*Example 3:*);
//@ requires(*Input: nums = [], target = 0*);
//@ requires(*Output: [-1,-1]*);
//@ requires(*Constraints:*);
//@ requires(*<code>0 <= nums.length <= 10<sup>5</sup></code>*);
//@ requires(*<code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code>*);
//@ requires(*param_nums is a non-decreasing array.*);
//@ requires(*<code>-10<sup>9</sup> <= target <= 10<sup>9</sup></code>*);
//@ ensures(*If param_target is not found in the array, the result is `[-1, -1]`.*);
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        ans[0] = helper(nums, target, false);
        ans[1] = helper(nums, target, true);
        return ans;
    }

    private int helper(int[] nums, int target, boolean equals) {
        int l = 0;
        int r = nums.length - 1;
        int result = -1;
        //@ maintaining 0 <= l <= r < nums.length || l == r + 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // assume 0 <= mid < nums.length;
            if (nums[mid] == target) {
                result = mid;
            }
            if (nums[mid] < target || (nums[mid] == target && equals)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return result;
    }
}