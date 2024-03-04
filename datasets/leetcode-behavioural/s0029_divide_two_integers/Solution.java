package g0001_0100.s0029_divide_two_integers;

// #Medium #Top_Interview_Questions #Math #Bit_Manipulation #Udemy_Bit_Manipulation
// #2023_08_09_Time_1_ms_(97.44%)_Space_39.5_MB_(92.14%)

public class Solution {
//@ ensures(*If the integer parameter `divisor` is equal to 0, the method should throw an exception.*);
//@ ensures(*The integer result is the quotient after dividing the integer parameter `dividend` by the integer parameter `divisor`.*);
//@ ensures(*If the quotient is strictly greater than 2^31 - 1, the result should be 2^31 - 1.*);
//@ ensures(*If the quotient is strictly less than -2^31, the result should be -2^31.*);
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