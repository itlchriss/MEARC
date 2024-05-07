package g0001_0100.s0035_search_insert_position;

// #Easy #Top_100_Liked_Questions #Array #Binary_Search #Algorithm_I_Day_1_Binary_Search
// #Binary_Search_I_Day_2 #Big_O_Time_O(log_n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_43.3_MB_(58.21%)

public class Solution {
//@ ensures(*The integer array parameter `nums` contains distinct values sorted in ascending order.*);
//@ ensures(*The integer parameter `target` is equal to the target value to be found or inserted.*);
//@ ensures(*The integer result is the index of the target value in the integer array parameter `nums` if the target value is found.*);
//@ ensures(*If the target value is not found in the integer array parameter `nums`, the integer result is the index where the target value would be inserted in order.*);
//@ ensures(*The runtime complexity of the algorithm must be O(log n).*);
    public int searchInsert(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        //@ maintaining 0 <= lo <= hi < nums.length || lo == hi + 1;
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