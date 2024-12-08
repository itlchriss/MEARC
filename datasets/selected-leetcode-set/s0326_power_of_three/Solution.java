package g0301_0400.s0326_power_of_three;

// #Easy #Top_Interview_Questions #Math #Recursion
// #2022_07_09_Time_18_ms_(85.35%)_Space_47.9_MB_(14.68%)

public class Solution {
    // regular method that has a loop
//@ requires(*The integer parameter `n` is greater than or equal to -2147483648 and is less than or equal to 2147483647.*);
//@ ensures(*The boolean result is equal to true if the integer parameter `n` is a power of three.*);
//@ ensures(*The boolean result is equal to false if the integer parameter `n` is not a power of three.*);
//@ ensures(*If the integer parameter `n` is equal to 27, the boolean result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 0, the boolean result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 9, the boolean result is equal to true.*);
    public boolean isPowerOfThree(int n) {
        if (n < 3 && n != 1) {
            return false;
        }
        while (n != 1) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}