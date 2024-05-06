package g0001_0100.s0081_search_in_rotated_sorted_array_ii;

// #Medium #Array #Binary_Search #Binary_Search_II_Day_12
// #2022_06_20_Time_1_ms_(82.83%)_Space_43.7_MB_(51.03%)

public class Solution {
//@ ensures(*The integer array parameter `nums` is sorted in non-decreasing order.*);
//@ ensures(*The integer array parameter `nums` is rotated at an unknown pivot index `k` such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]`.*);
//@ ensures(*The integer array parameter `nums` may contain duplicates.*);
//@ ensures(*The integer result is equal to the true literal if the integer parameter `target` is in the integer array `nums`, otherwise, the result is equal to the false literal.*);
//@ ensures(*The integer parameter `target` is greater than or equal to -10000 and is less than or equal to 10000.*);
//@ ensures(*The length of the integer array parameter `nums` is greater than or equal to 1 and is less than or equal to 5000.*);
    public boolean search(int[] nums, int target) {
        return binary(nums, 0, nums.length - 1, target);
    }

    private boolean binary(int[] a, int i, int j, int t) {
        if (i > j) {
            return false;
        }
        int mid = (i + j) / 2;
        if (a[mid] == t) {
            return true;
        }
        boolean c1 = binary(a, i, mid - 1, t);
        boolean c2 = binary(a, mid + 1, j, t);
        return c1 || c2;
    }
}