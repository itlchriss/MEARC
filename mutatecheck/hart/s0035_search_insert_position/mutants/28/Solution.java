package g0001_0100.s0035_search_insert_position;

// #Easy #Top_100_Liked_Questions #Array #Binary_Search #Algorithm_I_Day_1_Binary_Search
// #Binary_Search_I_Day_2 #Big_O_Time_O(log_n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_43.3_MB_(58.21%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((target <= 10000) && (target >= -10000));
//@ requires((nums.length <= 10000) && (nums.length >= 1));
//@ requires(\forall int i; 0 <= i < nums.length; ((nums[i] <= 10000) && (nums[i] >= -10000)));
//@ ensures((\result <= nums.length) && (\result >= 0));
//@ ensures(((Arrays.equals(nums, new int[] {1 , 3 , 5 , 6})) && (target == 5)) ==> (\result == 2));
//@ ensures(((Arrays.equals(nums, new int[] {1 , 3 , 5 , 6})) && (target == 7)) ==> (\result == 4));
//@ ensures(((Arrays.equals(nums, new int[] {1 , 3 , 5 , 6})) && (target == 2)) ==> (\result == 1));
//@ ensures(((Arrays.equals(nums, new int[] {1})) && (target == 0)) ==> (\result == 0));
//@ ensures(((Arrays.equals(nums, new int[] {1 , 3 , 5 , 6})) && (target == 0)) ==> (\result == 0));
    public int searchInsert(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        //@ maintaining 0 <= lo <= hi < nums.length || lo == hi + 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                hi = mid + 1;
            } else if (target > nums[mid]) {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
