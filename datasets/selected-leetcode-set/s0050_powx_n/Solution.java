package g0001_0100.s0050_powx_n;

// #Medium #Top_Interview_Questions #Math #Recursion #Udemy_Integers
// #2023_08_11_Time_0_ms_(100.00%)_Space_41.2_MB_(14.99%)

public class Solution {
//@ ensures(*The double parameter `x` is greater than -100.0 and is less than 100.0.*);
//@ ensures(*The integer parameter `n` is greater than or equal to -2^31 and is less than or equal to 2^31-1.*);
//@ ensures(*The double result is greater than or equal to -10^4 and is less than or equal to 10^4.*);
//@ ensures(*The double result is equal to `x` raised to the power `n`.*);
    public double myPow(double x, int n) {
        //@ assume x > 0;
        long nn = n;
        double res = 1;
        if (n < 0) {
            nn *= -1;
        }
        //@ ghost double k = res;
        //@ loop_invariant nn >= 0;
        //@ maintaining res == k * x ==> nn % 2 == 1;
        //@ decreases nn;
        while (nn > 0) {
            if (nn % 2 == 1) {
                nn--;
                //@ set k = res;
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