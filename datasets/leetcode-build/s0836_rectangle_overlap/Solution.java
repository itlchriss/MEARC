package g0801_0900.s0836_rectangle_overlap;

// #Easy #Math #Geometry #2022_03_24_Time_0_ms_(100.00%)_Space_41.6_MB_(35.43%)

public class Solution {
	//@ requires(*The input arrays `rec1` and `rec2` must have a length of 4.*);
	//@ requires(*The values in `rec1` and `rec2` must be integers.*);
	//@ requires(*The values in `rec1` and `rec2` must be within the range of -10^9 to 10^9.*);
	//@ requires(*The values in `rec1` and `rec2` must represent valid rectangles with non-zero area.*);
	//@ ensures(*The method should return a boolean value indicating whether the two rectangles overlap or not.*);
	//@ ensures(*If the rectangles overlap, the method should return `true`.*);
	//@ ensures(*If the rectangles do not overlap, the method should return `false`.*);
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return rec1[0] < rec2[2] && rec2[0] < rec1[2] && rec1[1] < rec2[3] && rec2[1] < rec1[3];
    }
}