package g0001_0100.s0033_search_in_rotated_sorted_array;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Binary_Search
// #Algorithm_II_Day_1_Binary_Search #Binary_Search_I_Day_11 #Level_2_Day_8_Binary_Search
// #Udemy_Binary_Search #Big_O_Time_O(log_n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_40.6_MB_(92.43%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 5000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are unique and are distinct.*);
//@ requires(*The integer array parameter `nums` is sorted in ascending order after being possibly rotated at an unknown pivot index `k` (1 <= k < nums.length).*);
//@ requires(*The integer parameter `target` is less than or equal to 10000 and is greater than or equal to -10000.*);
//@ requires(*The algorithm must have a runtime complexity of O(log n).*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2415\. Maximum Ascending Subarray Sum*);
//@ requires(*Medium*);
//@ requires(*Given an array of positive integers `nums`, return the maximum possible sum of an ascending subarray in it.*);
//@ requires(**);
//@ requires(*A subarray is defined as a contiguous sequence of numbers in an array.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[10,20,30,5,10,50\]*);
//@ requires(***Output:** 65*);
//@ requires(***Explanation:** \[5,10,50\] is the ascending subarray with the maximum sum of 65.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[10,20,30,40,50\]*);
//@ requires(***Output:** 150*);
//@ requires(***Explanation:** \[10,20,30,40,50\] is the ascending subarray with the maximum sum of 150.*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** nums = \[12,17,15,13,10,11,12\]*);
//@ requires(***Output:** 33*);
//@ requires(***Explanation:** \[10,11,12\] is the ascending subarray with the maximum sum of 33.*);
//@ requires(**);
//@ requires(***Example 4:***);
//@ requires(**);
//@ requires(***Input:** nums = \[100,10,1\]*);
//@ requires(***Output:** 100*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 1000`*);
//@ requires(**   `1 <= nums[i] <= 1000`*);
//@ requires(**);
//@ requires(*Method signature: public int maxAscendingSum(int[] nums)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000 and is greater than or equal to 1.*);
//@ ensures(*The resulting array is [nums[k], nums[k+1],..., nums[n-1], nums[0], nums[1],..., nums[k-1]] (0-indexed).*);
//@ ensures(*If the integer parameter `target` is in the integer array parameter `nums`, the integer result is the index of `target` in `nums`.*);
//@ ensures(*If the integer parameter `target` is not in the integer array parameter `nums`, the integer result is -1.*);
//@ ensures(*If the integer array parameter `nums` is equal to [4,5,6,7,0,1,2] and the integer parameter `target` is equal to 0, the integer result is equal to 4.*);
//@ ensures(*If the integer array parameter `nums` is equal to [4,5,6,7,0,1,2] and the integer parameter `target` is equal to 3, the integer result is equal to -1.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1] and the integer parameter `target` is equal to 0, the integer result is equal to -1.*);
//@ ensures(*The integer result is greater than or equal to the sum of the smallest ascending subarray in the integer array parameter `nums`.*);
//@ ensures(*The integer result is less than or equal to the sum of the largest ascending subarray in the integer array parameter `nums`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [10,20,30,5,10,50], the integer result is equal to 65.*);
//@ ensures(*If the integer array parameter `nums` is equal to [10,20,30,40,50], the integer result is equal to 150.*);
//@ ensures(*If the integer array parameter `nums` is equal to [12,17,15,13,10,11,12], the integer result is equal to 33.*);
//@ ensures(*If the integer array parameter `nums` is equal to [100,10,1], the integer result is equal to 100.*);
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