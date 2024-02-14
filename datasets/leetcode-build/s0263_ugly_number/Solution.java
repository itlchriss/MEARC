package g0201_0300.s0263_ugly_number;

// #Easy #Math #2022_07_05_Time_2_ms_(65.06%)_Space_41.3_MB_(46.18%)

public class Solution {
	//@ requires(*The input integer `n` is a positive integer.*);
	//@ ensures(*The method returns `true` if `n` is an ugly number.*);
	//@ ensures(*The method returns `false` if `n` is not an ugly number.*);
	//@ ensures(*If `n` is an ugly number, it means that all of its prime factors are limited to 2, 3, and 5.*);
    public boolean isUgly(int n) {
        if (n == 1) {
            return true;
        } else if (n <= 0) {
            return false;
        }
        int[] factors = new int[] {2, 3, 5};
        for (int factor : factors) {
            while (n > 1 && n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }
}