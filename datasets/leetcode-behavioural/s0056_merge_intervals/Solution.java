package g0001_0100.s0056_merge_intervals;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Sorting
// #Data_Structure_II_Day_2_Array #Level_2_Day_17_Interval #Udemy_2D_Arrays/Matrix
// #Big_O_Time_O(n_log_n)_Space_O(n) #2023_08_11_Time_8_ms_(96.27%)_Space_45.2_MB_(90.13%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
//@ ensures(*The integer 2D array parameter `intervals` must not be null.*);
//@ ensures(*The integer 2D array parameter `intervals` must have a length greater than or equal to 1.*);
//@ ensures(*Each sub-array within the integer 2D array parameter `intervals` must have a length of 2.*);
//@ ensures(*The integer result is a 2D array of non-overlapping intervals that cover all intervals in the input.*);
//@ ensures(*Overlapping intervals in the input must be merged into a single interval in the output.*);
//@ ensures(*The start value of each interval in the output must be less than or equal to the end value.*);
//@ ensures(*The output intervals must cover all intervals in the input.*);
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