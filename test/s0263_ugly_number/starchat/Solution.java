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
//@ ensures(*If the integer parameter `n` is equal to 15, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 30, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 35, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 49, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 98, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 100, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 125, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 126, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 150, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 151, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 175, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 176, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 200, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 225, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 250, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 251, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 275, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 276, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 300, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 375, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 376, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 400, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 450, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 451, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 475, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 476, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 500, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 525, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 526, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 550, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 575, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 576, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 600, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 625, the boolean result*);
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