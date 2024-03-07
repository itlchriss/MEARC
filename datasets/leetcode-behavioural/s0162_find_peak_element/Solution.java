package g0101_0200.s0162_find_peak_element;

// #Medium #Top_Interview_Questions #Array #Binary_Search #Algorithm_II_Day_2_Binary_Search
// #Binary_Search_II_Day_12 #2022_06_25_Time_0_ms_(100.00%)_Space_43.5_MB_(12.83%)

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer result is the index of a peak element in the integer array parameter `nums`.*);
//@ ensures(*The algorithm must run in O(log n) time.*);
    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            // This is done because start and end might be big numbers, so it might exceed the
            // integer limit.
            int mid = start + ((end - start) / 2);
            if (nums[mid + 1] > nums[mid]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}