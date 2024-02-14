package g0001_0100.s0056_merge_intervals;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Sorting
// #Data_Structure_II_Day_2_Array #Level_2_Day_17_Interval #Udemy_2D_Arrays/Matrix
// #Big_O_Time_O(n_log_n)_Space_O(n) #2023_08_11_Time_8_ms_(96.27%)_Space_45.2_MB_(90.13%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	//@ requires(*The input array `intervals` is not null.*);
	//@ requires(*The length of `intervals` is greater than or equal to 1.*);
	//@ requires(*Each interval in `intervals` is an array of length 2.*);
	//@ requires(*The start and end values of each interval are non-negative integers.*);
	//@ requires(*The start value of each interval is less than or equal to the end value.*);
	//@ ensures(*The returned array is not null.*);
	//@ ensures(*The length of the returned array is less than or equal to the length of the input array.*);
	//@ ensures(*Each interval in the returned array is an array of length 2.*);
	//@ ensures(*The start and end values of each interval in the returned array are non-negative integers.*);
	//@ ensures(*The start value of each interval in the returned array is less than or equal to the end value.*);
	//@ ensures(*The returned array contains non-overlapping intervals that cover all the intervals in the input array.*);
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> list = new ArrayList<>();
        int[] current = intervals[0];
        list.add(current);
        for (int[] next : intervals) {
            if (current[1] >= next[0]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                current = next;
                list.add(current);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}