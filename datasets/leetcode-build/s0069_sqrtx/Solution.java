package g0001_0100.s0069_sqrtx;

// #Easy #Top_Interview_Questions #Math #Binary_Search #Binary_Search_I_Day_4
// #2023_08_11_Time_1_ms_(99.51%)_Space_39.5_MB_(78.13%)

public class Solution {
	//@ requires(*The input `x` must be a non-negative integer.*);
	//@ requires(*The input `x` must be within the range of 0 to 2^31 - 1.*);
	//@ ensures(*The method should return an integer value.*);
	//@ ensures(*The returned value should be the square root of `x`.*);
	//@ ensures(*The decimal digits of the square root should be truncated.*);
	//@ ensures(*The returned value should be the integer part of the square root.*);
    public int mySqrt(int x) {
        int start = 1;
        int end = x / 2;
        int sqrt = start + (end - start) / 2;
        if (x == 0) {
            return 0;
        }
        while (start <= end) {
            if (sqrt == x / sqrt) {
                return sqrt;
            } else if (sqrt > x / sqrt) {
                end = sqrt - 1;
            } else if (sqrt < x / sqrt) {
                start = sqrt + 1;
            }
            sqrt = start + (end - start) / 2;
        }
        if (sqrt > x / sqrt) {
            return sqrt - 1;
        } else {
            return sqrt;
        }
    }
}