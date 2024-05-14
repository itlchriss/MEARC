package g0001_0100.s0034_find_first_and_last_position_of_element_in_sorted_array;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Binary_Search
// #Algorithm_II_Day_1_Binary_Search #Binary_Search_I_Day_5 #Big_O_Time_O(log_n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_44.3_MB_(89.57%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 0.*);
//@ requires(*All the values in the integer array parameter `nums` are less than or equal to 1000000000 and are greater than or equal to -1000000000.*);
//@ requires(*The integer parameter `target` is less than or equal to 1000000000 and is greater than or equal to -1000000000.*);
//@ ensures(*If the integer parameter `target` is not found in the integer array parameter `nums`, the integer array result is equal to [-1, -1].*);
//@ ensures(*If the integer parameter `target` is found in the integer array parameter `nums`, the first element of the integer array result is equal to the starting position of the `target` value in the integer array parameter `nums`, and the second element of the integer array result is equal to the ending position of the `target` value in the integer array parameter `nums`.*);
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