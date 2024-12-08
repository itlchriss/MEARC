package g0001_0100.s0035_search_insert_position;

// #Easy #Top_100_Liked_Questions #Array #Binary_Search #Algorithm_I_Day_1_Binary_Search
// #Binary_Search_I_Day_2 #Big_O_Time_O(log_n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_43.3_MB_(58.21%)

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer array parameter `nums` must contain distinct values sorted in ascending order.*);
//@ ensures(*The integer parameter `target` must be within the range of -10^4 to 10^4.*);
//@ ensures(*The integer result is the index of the integer parameter `target` if it is found in the integer array parameter `nums`.*);
//@ ensures(*If the integer parameter `target` is not found in the integer array parameter `nums`, the result is the index where it would be inserted in order.*);
    public int searchInsert(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                hi = mid - 1;
            } else if (target > nums[mid]) {
                lo = mid + 1;
            }
        }
        return lo;
    }
}