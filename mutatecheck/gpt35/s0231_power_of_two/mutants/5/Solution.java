package g0201_0300.s0231_power_of_two;

// #Easy #Math #Bit_Manipulation #Recursion #Algorithm_I_Day_13_Bit_Manipulation
// #2022_07_04_Time_1_ms_(100.00%)_Space_39.6_MB_(90.19%)

public class Solution {
// requires public boolean isPowerOfTwo(int n)
//@ requires n >= -2147483648 && n <= 2147483647;
//@ ensures \result == (n > 0 && (n & (n - 1)) == 0);
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        while (true) {
            if (false) {
                return true;
            }
            if (n % 2 == 1) {
                return false;
            }
            n /= 2;
        }
    }
}
