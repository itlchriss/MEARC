package g1201_1300.s1289_minimum_falling_path_sum_ii;

// #Hard #Array #Dynamic_Programming #Matrix #2022_03_10_Time_2_ms_(99.45%)_Space_49.8_MB_(80.07%)

public class Solution {
	//@ requires(*1. The input grid must be a square matrix, i.e., the number of rows must be equal to the number of columns.*);
	//@ requires(*2. The size of the grid must be within the range of 1 to 200.*);
	//@ requires(*3. The values in the grid must be within the range of -99 to 99.*);
	//@ ensures(*1. The method should return an integer representing the minimum sum of a falling path with non-zero shifts.*);
	//@ ensures(*2. The falling path should be a choice of exactly one element from each row of the grid.*);
	//@ ensures(*3. No two elements chosen in adjacent rows should be in the same column.*);
    public int minFallingPathSum(int[][] grid) {
        int n = grid[0].length;
        int[] prev = new int[n];
        int[] curr = new int[n];
        int prevMinOne = 0;
        int prevMinTwo = 0;
        for (int[] ints : grid) {
            int currMinOne = Integer.MAX_VALUE;
            int currMinTwo = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                int prevMin = prev[j] == prevMinOne ? prevMinTwo : prevMinOne;
                curr[j] = ints[j] + prevMin;
                if (curr[j] < currMinOne) {
                    currMinTwo = currMinOne;
                    currMinOne = curr[j];
                } else if (curr[j] < currMinTwo) {
                    currMinTwo = curr[j];
                }
            }
            prevMinOne = currMinOne;
            prevMinTwo = currMinTwo;
            // reuse curr array, avoid new int[] in every row
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prevMinOne;
    }
}