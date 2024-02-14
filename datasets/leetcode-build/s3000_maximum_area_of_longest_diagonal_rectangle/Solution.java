package g2901_3000.s3000_maximum_area_of_longest_diagonal_rectangle;

// #Easy #Array #2024_01_17_Time_1_ms_(99.67%)_Space_44.1_MB_(93.21%)

public class Solution {
	//@ requires(*The input array `dimensions` is not null.*);
	//@ requires(*The length of `dimensions` is greater than 0.*);
	//@ requires(*Each element in `dimensions` is an array of length 2.*);
	//@ requires(*The length and width of each rectangle in `dimensions` are positive integers.*);
	//@ ensures(*The method returns an integer representing the area of the rectangle with the longest diagonal.*);
	//@ ensures(*If there are multiple rectangles with the longest diagonal, the method returns the area of the rectangle with the maximum area.*);
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
}