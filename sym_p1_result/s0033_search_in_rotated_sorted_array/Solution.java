package g0001_0100.s0033_search_in_rotated_sorted_array;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Binary_Search
// #Algorithm_II_Day_1_Binary_Search #Binary_Search_I_Day_11 #Level_2_Day_8_Binary_Search
// #Udemy_Binary_Search #Big_O_Time_O(log_n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_40.6_MB_(92.43%)

public class Solution {
//@ requires(*There is an integer array param_nums sorted in ascending order (with distinct values).*);
//@ requires(*For example, `[0,1,2,4,5,6,7]` might be rotated at pivot index `3` and become `[4,5,6,7,0,1,2]`.*);
//@ requires(*You must write an algorithm with `O(log n)` runtime complexity.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [4,5,6,7,0,1,2], target = 0*);
//@ requires(*Output: 4*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [4,5,6,7,0,1,2], target = 3*);
//@ requires(*Output: -1*);
//@ requires(*Example 3:*);
//@ requires(*Input: nums = [1], target = 0*);
//@ requires(*Output: -1*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= nums.length <= 5000`*);
//@ requires(*<code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code>*);
//@ requires(*All values of param_nums are unique.*);
//@ requires(*param_nums is an ascending array that is possibly rotated.*);
//@ requires(*<code>-10<sup>4</sup> <= target <= 10<sup>4</sup></code>*);
//@ ensures(*Prior to being passed to your function, param_nums is possibly rotated at an unknown pivot index `k` (`1 <= k < nums.length`) such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]` (0-indexed).*);
//@ ensures(*Given the array param_nums after the possible rotation and an integer param_target, the result is the index of param_target if it is in param_nums, or `-1` if it is not in param_nums.*);
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