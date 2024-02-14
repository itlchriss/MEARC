package g0801_0900.s0850_rectangle_area_ii;

// #Hard #Array #Ordered_Set #Segment_Tree #Line_Sweep
// #2022_03_24_Time_4_ms_(97.00%)_Space_43.2_MB_(41.20%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input `rectangles` is a 2D array of axis-aligned rectangles.*);
	//@ requires(*Each rectangle `rectangle[i]` is represented by an array of four integers `[x1, y1, x2, y2]`, where `(x1, y1)` are the coordinates of the bottom-left corner and `(x2, y2)` are the coordinates of the top-right corner.*);
	//@ requires(*The coordinates of the rectangles are within the range `0 <= xi1, yi1, xi2, yi2 <= 10^9`.*);
	//@ requires(*The length of the `rectangles` array is between 1 and*);
	//@ ensures(*The method returns an integer representing the total area covered by all rectangles.*);
	//@ ensures(*The returned area is modulo `10^9 + 7`.*);
	//@ ensures(*Any area covered by two or more rectangles is only counted once.*);
    public int rectangleArea(int[][] rectangles) {
        List<int[]> memo = new ArrayList<>();
        for (int[] rectangle : rectangles) {
            helper(0, rectangle, memo);
        }

        long res = 0;
        final int mod = (int) (1e9 + 7);
        for (int[] m : memo) {
            res = (res + (long) (m[2] - m[0]) * (long) (m[3] - m[1])) % mod;
        }

        return (int) res;
    }

    private void helper(int id, int[] rectangle, List<int[]> memo) {
        if (id >= memo.size()) {
            memo.add(rectangle);
            return;
        }

        int[] cur = memo.get(id);

        if (rectangle[2] <= cur[0]
                || rectangle[0] >= cur[2]
                || rectangle[1] >= cur[3]
                || rectangle[3] <= cur[1]) {
            helper(id + 1, rectangle, memo);
            return;
        }

        if (rectangle[0] < cur[0]) {
            helper(id + 1, new int[] {rectangle[0], rectangle[1], cur[0], rectangle[3]}, memo);
        }

        if (rectangle[2] > cur[2]) {
            helper(id + 1, new int[] {cur[2], rectangle[1], rectangle[2], rectangle[3]}, memo);
        }

        if (rectangle[1] < cur[1]) {
            helper(
                    id + 1,
                    new int[] {
                        Math.max(rectangle[0], cur[0]),
                        rectangle[1],
                        Math.min(rectangle[2], cur[2]),
                        cur[1]
                    },
                    memo);
        }

        if (rectangle[3] > cur[3]) {
            helper(
                    id + 1,
                    new int[] {
                        Math.max(rectangle[0], cur[0]),
                        cur[3],
                        Math.min(rectangle[2], cur[2]),
                        rectangle[3]
                    },
                    memo);
        }
    }
}