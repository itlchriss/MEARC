package g0401_0500.s0435_non_overlapping_intervals;

// #Medium #Array #Dynamic_Programming #Sorting #Greedy #Data_Structure_II_Day_4_Array
// #2022_07_16_Time_96_ms_(47.37%)_Space_106.6_MB_(6.15%)

import java.util.Arrays;

public class Solution {
    /*
     * This is sorting my starting time, the key here is that we'll want to update end time when an
     * erasure is needed: we use the smaller end time instead of the bigger one which is more likely
     * to overlap with others.
     */
	//@ requires(*The input array `intervals` is not null.*);
	//@ requires(*The length of the input array `intervals` is greater than or equal to - Each interval in the input array `intervals` is represented by an array of length - The start and end values of each interval in the input array `intervals` are within the range of -5 * 10^4 to 5 * 10^- The start value of each interval in the input array `intervals` is less than the end value.*);
	//@ ensures(*The method returns an integer representing the minimum number of intervals that need to be removed to make the rest of the intervals non-overlapping.*);
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int erasures = 0;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                erasures++;
                end = Math.min(end, intervals[i][1]);
            } else {
                end = intervals[i][1];
            }
        }
        return erasures;
    }
}