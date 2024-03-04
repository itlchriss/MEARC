package g1001_1100.s1030_matrix_cells_in_distance_order;

// #Easy #Array #Math #Sorting #Matrix #Geometry
// #2022_02_27_Time_15_ms_(69.74%)_Space_72.2_MB_(52.05%)

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integers `rows` and `cols` must be greater than or equal to 1.*);
//@ ensures(*The input integers `rCenter` and `cCenter` must be within the range of the matrix dimensions (`rows` and `cols` respectively).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a 2D array of integers representing the coordinates of all cells in the matrix.*);
//@ ensures(*The output array is sorted by the distance of each cell from the center cell `(rCenter, cCenter)`.*);
//@ ensures(*The distance between two cells `(r1, c1)` and `(r2, c2)` is calculated as `|r1 - r2| + |c1 - c2|`.*);
//@ ensures(*The output array contains all cells in the matrix.*);
//@ ensures(*The output array may be in any order as long as it satisfies the condition of being sorted by distance from the center cell.*);
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        Map<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map.computeIfAbsent(
                                Math.abs(i - rCenter) + Math.abs(j - cCenter),
                                k -> new ArrayList<>())
                        .add(new int[] {i, j});
            }
        }
        int[][] res = new int[rows * cols][];
        int i = 0;
        for (List<int[]> list : map.values()) {
            for (int[] each : list) {
                res[i++] = each;
            }
        }
        return res;
    }
}