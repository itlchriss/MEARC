package g0001_0100.s0034_find_first_and_last_position_of_element_in_sorted_array;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Binary_Search
// #Algorithm_II_Day_1_Binary_Search #Binary_Search_I_Day_5 #Big_O_Time_O(log_n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_44.3_MB_(89.57%)

public class Solution {
//@ requires(Integer.MIN_VALUE <= target && target <= Integer.MAX_VALUE);
//@ requires(nums != null && nums.length >= 0);
//@ requires((\forall int i; 0 <= i && i < nums.length - 1; nums[i] <= nums[i + 1]));
//@ ensures((\result[0] == -1 && \result[1] == -1) ==> (\forall int i; 0 <= i && i < nums.length; nums[i] != target));
//@ ensures((\result[0] != -1 && \result[1] != -1) ==> (\exists int i; 0 <= i && i < nums.length; nums[i] == target && \result[0] <= i && i <= \result[1]));
//@ ensures((\result != null && \result.length == 2));
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
        while (true) {
            int mid = l + (r - l) / 2;
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
