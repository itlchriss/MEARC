package g0001_0100.s0029_divide_two_integers;

// #Medium #Top_Interview_Questions #Math #Bit_Manipulation #Udemy_Bit_Manipulation
// #2023_08_09_Time_1_ms_(97.44%)_Space_39.5_MB_(92.14%)

public class Solution {
//@ requires(dividend >= Integer.MIN_VALUE && dividend <= Integer.MAX_VALUE);
//@ ensures(\result == dividend / divisor);
//@ requires(divisor >= Integer.MIN_VALUE && divisor <= Integer.MAX_VALUE);
//@ requires(divisor != 0);
//@ ensures((\result > Integer.MAX_VALUE) ==> (\result == Integer.MAX_VALUE));
//@ ensures((\result < Integer.MIN_VALUE) ==> (\result == Integer.MIN_VALUE));
    public int divide(int dividend, int divisor) {
        boolean isNegative = divisor < 0 || dividend < 0 && divisor > 0;
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
