package g0001_0100.s0069_sqrtx;

// #Easy #Top_Interview_Questions #Math #Binary_Search #Binary_Search_I_Day_4
// #2023_08_11_Time_1_ms_(99.51%)_Space_39.5_MB_(78.13%)

public class Solution {
//@ ensures(*The integer parameter `x` is greater than or equal to 0 and is less than or equal to 2^31 - 1.*);
//@ ensures(*The integer result is the square root of the integer parameter `x`.*);
//@ ensures(*The decimal digits of the square root are truncated, and only the integer part is returned.*);
    public int mySqrt(int x) {
        int start = 1;
        int end = x / 2;
        int sqrt = start + (end - start) / 2;
        if (x == 0) {
            return 0;
        }
        //@ assume start > 0 && end > 0 && sqrt > 0;
        // maintaining start <= end || start > end;
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