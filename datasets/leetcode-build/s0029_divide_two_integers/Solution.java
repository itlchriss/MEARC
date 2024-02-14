package g0001_0100.s0029_divide_two_integers;

// #Medium #Top_Interview_Questions #Math #Bit_Manipulation #Udemy_Bit_Manipulation
// #2023_08_09_Time_1_ms_(97.44%)_Space_39.5_MB_(92.14%)

public class Solution {
	//@ requires(*The method should take two integer parameters, `dividend` and `divisor`.*);
	//@ requires(*The `divisor` should not be equal to 0.*);
	//@ ensures(*The method should return an integer value representing the quotient of `dividend` divided by `divisor`.*);
	//@ ensures(*The integer division should truncate towards zero, meaning the fractional part should be discarded.*);
	//@ ensures(*If the quotient is strictly greater than the maximum value of a 32-bit signed integer, return the maximum value.*);
	//@ ensures(*If the quotient is strictly less than the minimum value of a 32-bit signed integer, return the minimum value.*);
    public int divide(int dividend, int divisor) {
        boolean isNegative = dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0;
        long ans = 0;
        long divide = Math.abs((long) dividend);
        long divisorAbs = Math.abs((long) divisor);
        while (divide >= divisorAbs) {
            long temp = divisorAbs;
            long cnt = 1;
            while (divide >= temp) {
                divide -= temp;
                ans += cnt;
                cnt <<= 1;
                temp <<= 1;
            }
        }
        if (isNegative) {
            ans = -ans;
        }
        int intMin = -(1 << 31);
        int intMax = (1 << 31) - 1;
        if (ans < intMin || ans > intMax) {
            ans = intMax;
        }
        return (int) ans;
    }
}