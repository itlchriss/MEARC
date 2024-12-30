package g0201_0300.s0263_ugly_number;

// #Easy #Math #2022_07_05_Time_2_ms_(65.06%)_Space_41.3_MB_(46.18%)

public class Solution {
//@ requires(*Method behavioural specifications for the given context:*);
//@ requires(**);
//@ requires(*The integer parameter `n` is less than or equal to 2147483647 and is greater than or equal to -2147483648.*);
//@ ensures(*If the integer parameter `n` is less than or equal to 0, the boolean result is equal to the false literal.*);
//@ ensures(*If the integer parameter `n` is equal to 1, the boolean result is equal to the true literal.*);
//@ ensures(*If the boolean result is equal to the true literal, all prime factors of the integer parameter `n` are limited to 2, 3, and 5.*);
//@ ensures(*If the boolean result is equal to the false literal, the integer parameter `n` has at least one prime factor that is not 2, 3, or 5.*);
//@ ensures(*If the integer parameter `n` is equal to 6, the boolean result is equal to the true literal.*);
//@ ensures(*If the integer parameter `n` is equal to 8, the boolean result is equal to the true literal.*);
//@ ensures(*If the integer parameter `n` is equal to 14, the boolean result is equal to the false literal.*);
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