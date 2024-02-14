package g1901_2000.s1943_describe_the_painting;

// #Medium #Array #Prefix_Sum #2022_05_16_Time_29_ms_(93.92%)_Space_127.4_MB_(75.00%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	//@ requires(*The input `segments` is a 2D integer array.*);
	//@ requires(*Each element `segments[i]` is an array of length 3.*);
	//@ requires(*The start value `start_i` of each segment is less than the end value `end_i`.*);
	//@ requires(*The color value `color_i` of each segment is distinct.*);
	//@ ensures(*The output is a 2D array `painting`.*);
	//@ ensures(*Each element `painting[j]` is an array of length 3.*);
	//@ ensures(*The start value `left_j` of each segment in `painting` is less than the end value `right_j`.*);
	//@ ensures(*The mixed color sum `mix_j` of each segment in `painting` is the sum of the mixed colors in the corresponding segments of the input `segments`.*);
	//@ ensures(*The segments in `painting` are non-overlapping.*);
	//@ ensures(*The segments in `painting` represent the minimum number of non-overlapping half-closed segments of mixed colors.*);
    public List<List<Long>> splitPainting(int[][] segments) {
        List<List<Long>> result = new ArrayList<>();
        int n = 1;
        for (int[] s : segments) {
            n = Math.max(n, s[1]);
        }
        n += 1;
        long[] line = new long[n];
        boolean[] endpoint = new boolean[n];
        for (int[] s : segments) {
            int start = s[0];
            int end = s[1];
            int color = s[2];
            line[start] += color;
            line[end] -= color;
            endpoint[start] = endpoint[end] = true;
        }
        long mixedColor = 0;
        int start = 1;
        for (int end = 1; end < n; end++) {
            if (endpoint[end]) {
                if (mixedColor > 0) {
                    result.add(Arrays.asList((long) start, (long) end, mixedColor));
                }
                start = end;
            }
            mixedColor += line[end];
        }
        return result;
    }
}