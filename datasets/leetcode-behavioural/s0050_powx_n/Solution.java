package g0001_0100.s0050_powx_n;

// #Medium #Top_Interview_Questions #Math #Recursion #Udemy_Integers
// #2023_08_11_Time_0_ms_(100.00%)_Space_41.2_MB_(14.99%)

public class Solution {
//@ ensures(*If the double parameter `x` is between -100.0 and 100.0, and the integer parameter `n` is between -2^31 and 2^31-1, the double result is equal to `x` raised to the power of `n`.*);
//@ ensures(*If the double parameter `x` is outside the range of -100.0 to 100.0, or the integer parameter `n` is outside the range of -2^31 to 2^31-1, the result is undefined.*);
    public double myPow(double x, int n) {
        long nn = n;
        double res = 1;
        if (n < 0) {
            nn *= -1;
        }
        while (nn > 0) {
            if (nn % 2 == 1) {
                nn--;
                res *= x;
            } else {
                x *= x;
                nn /= 2;
            }
        }
        if (n < 0) {
            return 1.0 / res;
        }
        return res;
    }
}