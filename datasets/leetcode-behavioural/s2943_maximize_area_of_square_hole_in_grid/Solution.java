package g2901_3000.s2943_maximize_area_of_square_hole_in_grid;

// #Medium #Array #Sorting #2024_01_04_Time_2_ms_(100.00%)_Space_43.2_MB_(70.48%)

import java.util.Arrays;

@SuppressWarnings("java:S1172")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input values `n` and `m` must be positive integers.*);
//@ ensures(*The length of the array `hBars` must be greater than or equal to 1.*);
//@ ensures(*The length of the array `vBars` must be greater than or equal to 1.*);
//@ ensures(*All values in the array `hBars` must be distinct and within the range `[2, n + 1]`.*);
//@ ensures(*All values in the array `vBars` must be distinct and within the range `[2, m + 1]`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer denoting the maximum area of a square-shaped hole in the grid after removing some bars.*);
//@ ensures(*The maximum area of the square-shaped hole should be greater than or equal to 0.*);
//@ ensures(*The maximum area of the square-shaped hole should be less than or equal to the product of `n` and `m`.*);
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int x = find(hBars);
        int y = find(vBars);
        int res = Math.min(x, y) + 1;
        return res * res;
    }

    private int find(int[] arr) {
        Arrays.sort(arr);
        int res = 1;
        int i = 0;
        int n = arr.length;
        while (i < n) {
            int count = 1;
            while (i + 1 < n && arr[i] + 1 == arr[i + 1]) {
                i++;
                count++;
            }
            i++;
            res = Math.max(res, count);
        }
        return res;
    }
}