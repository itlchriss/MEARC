package g0001_0100.s0069_sqrtx;

// #Easy #Top_Interview_Questions #Math #Binary_Search #Binary_Search_I_Day_4
// #2023_08_11_Time_1_ms_(99.51%)_Space_39.5_MB_(78.13%)

public class Solution {
//@ ensures(*If the non-negative integer parameter `x` is 0, the integer result is 0.*);
//@ ensures(*If the non-negative integer parameter `x` is 1, the integer result is 1.*);
//@ ensures(*If the non-negative integer parameter `x` is greater than 1, the integer result is the truncated square root of the integer parameter `x`.*);
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