package g1501_1600.s1536_minimum_swaps_to_arrange_a_binary_grid;

// #Medium #Array #Greedy #Matrix #2022_04_10_Time_1_ms_(100.00%)_Space_45.4_MB_(93.33%)

public class Solution {
	//@ requires(*The input grid is a square matrix with dimensions n x n.*);
	//@ requires(*Each element in the grid is either 0 or 1.*);
	//@ ensures(*The grid is valid, meaning all cells above the main diagonal are zeros.*);
	//@ ensures(*The minimum number of steps needed to make the grid valid is returned.*);
	//@ ensures(*If the grid cannot be made valid, -1 is returned.*);
    public int minSwaps(int[][] grid) {
        int len = grid.length;
        int swap = 0;
        int[] preProcess = new int[len];
        for (int i = 0; i < len; i++) {
            preProcess[i] = countRightZeros(grid[i]);
        }
        for (int i = 0; i < len; i++) {
            int minValueRequired = len - i - 1;
            int j = i;
            while (j < len && preProcess[j] < minValueRequired) {
                j++;
            }
            if (j == len) {
                return -1;
            }
            while (j != i) {
                swap++;
                int temp = preProcess[j];
                preProcess[j] = preProcess[j - 1];
                preProcess[j - 1] = temp;
                j--;
            }
        }
        return swap;
    }

    private int countRightZeros(int[] row) {
        int cnt = 0;
        for (int i = row.length - 1; i >= 0; i--) {
            if (row[i] != 0) {
                break;
            }
            cnt++;
        }
        return cnt;
    }
}