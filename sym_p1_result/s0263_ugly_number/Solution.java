package g0201_0300.s0263_ugly_number;

// #Easy #Math #2022_07_05_Time_2_ms_(65.06%)_Space_41.3_MB_(46.18%)

public class Solution {
//@ requires(*The integer parameter `n` is greater than or equal to -2147483648 and is less than or equal to 2147483647.*);
//@ requires(*An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.*);
//@ ensures(*If the boolean result is equal to true, the integer parameter `n` is an ugly number.*);
//@ ensures(*If the boolean result is equal to false, the integer parameter `n` is not an ugly number.*);
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