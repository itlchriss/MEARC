package g0001_0100.s0057_insert_interval;

// #Medium #Array #Level_2_Day_17_Interval #2023_08_11_Time_0_ms_(100.00%)_Space_43.7_MB_(95.60%)

import java.util.Arrays;

public class Solution {
	//@ requires(*1. The `intervals` array is sorted in ascending order by the start value of each interval.*);
	//@ requires(*2. The `intervals` array does not contain any overlapping intervals.*);
	//@ requires(*3. The `newInterval` array contains two elements representing the start and end values of the new interval.*);
	//@ requires(*4. The start value of the new interval is greater than or equal to the start value of the first interval in the `intervals` array.*);
	//@ requires(*5. The end value of the new interval is less than or equal to the end value of the last interval in the `intervals` array.*);
	//@ ensures(*1. The `intervals` array is still sorted in ascending order by the start value of each interval.*);
	//@ ensures(*2. The `intervals` array does not contain any overlapping intervals.*);
	//@ ensures(*3. The `newInterval` has been inserted into the `intervals` array in the correct position.*);
	//@ ensures(*4. If the new interval overlaps with any existing intervals, those intervals have been merged into a single interval.*);
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int l = 0;
        int r = n - 1;
        while (l < n && newInterval[0] > intervals[l][1]) {
            l++;
        }
        while (r >= 0 && newInterval[1] < intervals[r][0]) {
            r--;
        }
        int[][] res = new int[l + n - r][2];
        for (int i = 0; i < l; i++) {
            res[i] = Arrays.copyOf(intervals[i], intervals[i].length);
        }
        res[l][0] = Math.min(newInterval[0], l == n ? newInterval[0] : intervals[l][0]);
        res[l][1] = Math.max(newInterval[1], r == -1 ? newInterval[1] : intervals[r][1]);
        for (int i = l + 1, j = r + 1; j < n; i++, j++) {
            res[i] = intervals[j];
        }
        return res;
    }
}