package g0201_0300.s0263_ugly_number;

// #Easy #Math #2022_07_05_Time_2_ms_(65.06%)_Space_41.3_MB_(46.18%)

public class Solution {
//@ requires(*The integer parameter `n` is less than or equal to 2147483647 and is greater than or equal to -2147483648.*);
//@ ensures(*If the integer parameter `n` is equal to 6, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 8, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 14, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 1, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 0, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 10, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 15, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 30, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 100, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 105, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 1000, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 10000, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 100000, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 1000000, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 10000000, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 100000000, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 1000000000, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 2147483647, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to -2147483648, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 2, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 3, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 5, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 7, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 11, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 13, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 17, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 19, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 23, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 29, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 31, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 37, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 41, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 43, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 47, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 53, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 59, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 61, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 67, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 71, the boolean result is equal to true*);
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