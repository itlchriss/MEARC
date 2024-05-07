package g0001_0100.s0033_search_in_rotated_sorted_array;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Binary_Search
// #Algorithm_II_Day_1_Binary_Search #Binary_Search_I_Day_11 #Level_2_Day_8_Binary_Search
// #Udemy_Binary_Search #Big_O_Time_O(log_n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_40.6_MB_(92.43%)

public class Solution {
//@ ensures(*The integer array parameter `nums` is sorted in ascending order with distinct values.*);
//@ ensures(*The integer array parameter `nums` is possibly rotated at an unknown pivot index `k` such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]`.*);
//@ ensures(*The integer result is the index of the integer parameter `target` if it is in the integer array `nums`.*);
//@ ensures(*If the integer parameter `target` is not in the integer array `nums`, the integer result is -1.*);
//@ ensures(*The algorithm must have a runtime complexity of O(log n).*);
//@ ensures(*The length of the integer array `nums` is greater than or equal to 1 and is less than or equal to 5000.*);
//@ ensures(*All values in the integer array `nums` are unique.*);
//@ ensures(*All values in the integer array `nums` are within the range of -10^4 to 10^4.*);
//@ ensures(*The integer parameter `target` is within the range of -10^4 to 10^4.*);
    public int search(int[] nums, int target) {
        int mid;
        int lo = 0;
        int hi = nums.length - 1;
        // maintaining 0 <= hi < nums.length;
        //@ maintaining 0 <= lo <= hi < nums.length || lo == hi + 1;
        while (lo <= hi) {
            mid = ((hi - lo) >> 1) + lo;            
            if (target == nums[mid]) {
                return mid;
            }
            // if this is true, then the possible rotation can only be in the second half
            if (nums[lo] <= nums[mid]) {
                // the target is in the first half only if it's
                if (nums[lo] <= target && target <= nums[mid]) {
                    // included
                    hi = mid - 1;
                } else {
                    // between nums[lo] and nums[mid]
                    lo = mid + 1;
                }
                // otherwise, the possible rotation can only be in the first half
            } else if (nums[mid] <= target && target <= nums[hi]) {
                // the target is in the second half only if it's included
                lo = mid + 1;
            } else {
                // between nums[hi] and nums[mid]
                hi = mid - 1;
            }
        }
        return -1;
    }
}