package g0001_0100.s0035_search_insert_position;

// #Easy #Top_100_Liked_Questions #Array #Binary_Search #Algorithm_I_Day_1_Binary_Search
// #Binary_Search_I_Day_2 #Big_O_Time_O(log_n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_43.3_MB_(58.21%)

public class Solution {
//@ requires(*You must write an algorithm with `O(log n)` runtime complexity.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [1,3,5,6], target = 5*);
//@ requires(*Output: 2*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [1,3,5,6], target = 2*);
//@ requires(*Output: 1*);
//@ requires(*Example 3:*);
//@ requires(*Input: nums = [1,3,5,6], target = 7*);
//@ requires(*Output: 4*);
//@ requires(*Example 4:*);
//@ requires(*Input: nums = [1,3,5,6], target = 0*);
//@ requires(*Output: 0*);
//@ requires(*Example 5:*);
//@ requires(*Input: nums = [1], target = 0*);
//@ requires(*Output: 0*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= nums.length <= 10<sup>4</sup></code>*);
//@ requires(*<code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code>*);
//@ requires(*param_nums contains distinct values sorted in ascending order.*);
//@ requires(*<code>-10<sup>4</sup> <= target <= 10<sup>4</sup></code>*);
//@ ensures(*Given a sorted array of distinct integers and a target value, the result is the index if the target is found.*);
//@ ensures(*If not, the result is the index where it would be if it were inserted in order.*);
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