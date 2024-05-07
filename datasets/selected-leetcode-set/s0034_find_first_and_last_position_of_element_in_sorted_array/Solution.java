package g0001_0100.s0034_find_first_and_last_position_of_element_in_sorted_array;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Binary_Search
// #Algorithm_II_Day_1_Binary_Search #Binary_Search_I_Day_5 #Big_O_Time_O(log_n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_44.3_MB_(89.57%)

public class Solution {
//@ ensures(*The integer array parameter `nums` is sorted in non-decreasing order.*);
//@ ensures(*The integer parameter `target` is within the range of -10^9 to 10^9.*);
//@ ensures(*The integer array result has a length of 2.*);
//@ ensures(*If the `target` value is found in the `nums` array, the first element of the result array is the starting position of the `target` value in the `nums` array.*);
//@ ensures(*If the `target` value is found in the `nums` array, the second element of the result array is the ending position of the `target` value in the `nums` array.*);
//@ ensures(*If the `target` value is not found in the `nums` array, both elements of the result array are -1.*);
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