package g0001_0100.s0057_insert_interval;

// #Medium #Array #Level_2_Day_17_Interval #2023_08_11_Time_0_ms_(100.00%)_Space_43.7_MB_(95.60%)

import java.util.Arrays;

public class Solution {
//@ ensures(*The integer 2D array parameter `intervals` must not be null.*);
//@ ensures(*The integer array parameter `intervals` must be sorted in ascending order by the first element of each interval.*);
//@ ensures(*The integer array parameter `newInterval` must not be null.*);
//@ ensures(*The integer array parameter `newInterval` must represent a valid interval where the first element is less than or equal to the second element.*);
//@ ensures(*The integer 2D array result `intervals` after the insertion must be sorted in ascending order by the first element of each interval.*);
//@ ensures(*The integer 2D array result `intervals` after the insertion must not have any overlapping intervals.*);
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