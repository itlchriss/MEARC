package g2901_3000.s3000_maximum_area_of_longest_diagonal_rectangle;

// #Easy #Array #2024_01_17_Time_1_ms_(99.67%)_Space_44.1_MB_(93.21%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the **area** of the rectangle having the **longest** diagonal. If there are multiple rectangles with the longest diagonal, return the area of the rectangle having the **maximum** area._ So, the rectangle at index 1 has a greater diagonal length therefore we return area = 8 \* 6 = 48.
Return _the **area** of the rectangle having the **longest** diagonal. If there are multiple rectangles with the longest diagonal, return the area of the rectangle having the **maximum** area._*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int mx = 0;
        for (int[] t : dimensions) {
            if (t[0] * t[0] + t[1] * t[1] > mx) {
                mx = t[0] * t[0] + t[1] * t[1];
            }
        }
        int area = 0;
        for (int[] t : dimensions) {
            if (t[0] * t[0] + t[1] * t[1] == mx && t[0] * t[1] > area) {
                area = t[0] * t[1];
            }
        }
        return area;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
