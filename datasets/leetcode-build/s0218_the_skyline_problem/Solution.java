package g0201_0300.s0218_the_skyline_problem;

// #Hard #Top_Interview_Questions #Array #Heap_Priority_Queue #Ordered_Set #Divide_and_Conquer
// #Segment_Tree #Binary_Indexed_Tree #Line_Sweep
// #2022_07_02_Time_22_ms_(76.93%)_Space_52.3_MB_(45.14%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Solution {
	//@ requires(*The input `buildings` is a non-empty array of arrays.*);
	//@ requires(*Each subarray in `buildings` contains three elements: `left`, `right`, and `height`.*);
	//@ requires(*The values of `left`, `right`, and `height` are integers.*);
	//@ requires(*The values of `left` and `right` are non-negative.*);
	//@ requires(*The value of `left` is less than the value of `right`.*);
	//@ requires(*The value of `height` is positive.*);
	//@ requires(*The `buildings` array is sorted by the value of `left` in non-decreasing order.*);
	//@ ensures(*The output is a list of lists representing the skyline.*);
	//@ ensures(*Each subarray in the output contains two elements: `x` and `y`.*);
	//@ ensures(*The values of `x` and `y` are integers.*);
	//@ ensures(*The output list is sorted by the value of `x` in non-decreasing order.*);
	//@ ensures(*The last subarray in the output has a `y` value of - There are no consecutive subarrays in the output with the same `y` value.*);
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> list = new ArrayList<>();
        List<int[]> lines = new ArrayList<>();
        for (int[] building : buildings) {
            lines.add(new int[] {building[0], building[2]});
            lines.add(new int[] {building[1], -building[2]});
        }
        lines.sort((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);
        int prev = 0;
        for (int[] line : lines) {
            if (line[1] > 0) {
                map.put(line[1], map.getOrDefault(line[1], 0) + 1);
            } else {
                int f = map.get(-line[1]);
                if (f == 1) {
                    map.remove(-line[1]);
                } else {
                    map.put(-line[1], f - 1);
                }
            }
            int curr = map.lastKey();
            if (curr != prev) {
                list.add(Arrays.asList(line[0], curr));
                prev = curr;
            }
        }
        return list;
    }
}