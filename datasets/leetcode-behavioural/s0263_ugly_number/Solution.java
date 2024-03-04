package g0201_0300.s0263_ugly_number;

// #Easy #Math #2022_07_05_Time_2_ms_(65.06%)_Space_41.3_MB_(46.18%)

public class Solution {
//@ ensures(*If the integer parameter `n` is an ugly number (a positive integer whose prime factors are limited to 2, 3, and 5), the boolean result is true.*);
//@ ensures(*If the integer parameter `n` is not an ugly number (contains prime factors other than 2, 3, and 5), the boolean result is false.*);
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