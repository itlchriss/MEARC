package g1901_2000.s1901_find_a_peak_element_ii;

// #Medium #Array #Binary_Search #Matrix #Divide_and_Conquer #Binary_Search_II_Day_17
// #2022_05_11_Time_0_ms_(100.00%)_Space_115.1_MB_(45.96%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input matrix `mat` is not null.*);
//@ ensures(*2. The input matrix `mat` has at least one row and one column.*);
//@ ensures(*3. The input matrix `mat` has no two adjacent cells that are equal.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The output array `result` is not null.*);
//@ ensures(*2. The output array `result` has a length of 2.*);
//@ ensures(*3. The output array `result` contains valid indices `i` and `j` such that `mat[i][j]` is a peak element.*);
//@ ensures(*4. The output array `result` contains at least one valid peak element index.*);
//@ ensures(*5. The output array `result` contains only valid peak element indices.*);
//@ ensures(*6. The output array `result` contains distinct peak element indices.*);
//@ ensures(*7. The output array `result` contains peak element indices that are strictly greater than all of their adjacent neighbors to the left, right, top, and bottom.*);
//@ ensures(*8. The output array `result` contains peak element indices that are within the bounds of the input matrix `mat`.*);
//@ ensures(*9. The output array `result` contains peak element indices that are not on the outer perimeter of the input matrix `mat`.*);
//@ ensures(*10. The output array `result` contains peak element indices that are not surrounded by adjacent cells with a value of `-1`.*);
    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int l = 0;
        int r = m - 1;
        int mid;
        while (l <= r) {
            mid = (l + r) / 2;
            int mx = mat[0][mid];
            int mxi = 0;
            for (int i = 1; i < n; i++) {
                if (mx < mat[i][mid]) {
                    mx = mat[i][mid];
                    mxi = i;
                }
            }
            int lv = mid > l ? mat[mxi][mid - 1] : -1;
            int rv = mid < r ? mat[mxi][mid + 1] : -1;
            if (mx > lv && mx > rv) {
                return new int[] {mxi, mid};
            } else if (mx > lv) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return new int[] {-1, -1};
    }
}