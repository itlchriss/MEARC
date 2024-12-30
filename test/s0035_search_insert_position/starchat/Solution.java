package g0001_0100.s0035_search_insert_position;

// #Easy #Top_100_Liked_Questions #Array #Binary_Search #Algorithm_I_Day_1_Binary_Search
// #Binary_Search_I_Day_2 #Big_O_Time_O(log_n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_43.3_MB_(58.21%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 10000 and is greater than or equal to -10000.*);
//@ requires(*The integer array parameter `nums` contains distinct values sorted in ascending order.*);
//@ requires(*The integer parameter `target` is less than or equal to 10000 and is greater than or equal to -10000.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2521\. Find the Peaks*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*You are given a **0-indexed** array `mountain` of length `n` which represents the height of a series of mountains.*);
//@ requires(**);
//@ requires(*You may choose one of the following tools:*);
//@ requires(**);
//@ requires(**   A **corner** pick: Pick the mountain at the **beginning** or the **end** of the array.*);
//@ requires(**   A **random** pick: Pick a random index `i` where `0 <= i < n` and pick the mountain at index `i`.*);
//@ requires(**   A **peak** pick: Pick the **tallest** mountain.*);
//@ requires(**);
//@ requires(*Return _the index of the mountain which you must pick such that you can maximize your chance to get the mountain above some height_. If there are multiple valid answers, return the **smallest** such index.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** mountain = \[1,2,3,2,1\]*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** The mountain at index 2 (3) is the highest peak.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** mountain = \[0,1,0,1,0\]*);
//@ requires(***Output:** 1*);
//@ requires(***Explanation:** The mountain at index 1 (1) is the highest peak.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `n == mountain.length`*);
//@ requires(**   `2 <= n <= 105`*);
//@ requires(**   `0 <= mountain[i] <= 109`*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve this problem in `O(log(n))` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public int findPeak(int[] mountain)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `mountain` is less than or equal to 105 and is greater than or equal to 2.*);
//@ requires(*All values in the integer array parameter `mountain` are less than or equal to 109 and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2951\. Find the Peaks*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*You are given a **0-indexed** array `mountain` of length `n` which represents the height of a series of mountains.*);
//@ requires(**);
//@ requires(*You may choose one of the following tools:*);
//@ requires(**);
//@ requires(**   A **corner** pick: Pick the mountain at the **beginning** or the **end** of the array.*);
//@ requires(**   A **random*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the integer array parameter `nums`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,3,5,6] and the integer parameter `target` is equal to 5, the integer result is equal to 2.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,3,5,6] and the integer parameter `target` is equal to 2, the integer result is equal to 1.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,3,5,6] and the integer parameter `target` is equal to 7, the integer result is equal to 4.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,3,5,6] and the integer parameter `target` is equal to 0, the integer result is equal to 0.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1] and the integer parameter `target` is equal to 0, the integer result is equal to 0.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the integer array parameter `mountain` minus 1.*);
//@ ensures(*If the integer array parameter `mountain` is equal to [1,2,3,2,1], the integer result is equal to 2.*);
//@ ensures(*If the integer array parameter `mountain` is equal to [0,1,0,1,0], the integer result is equal to 1.*);
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