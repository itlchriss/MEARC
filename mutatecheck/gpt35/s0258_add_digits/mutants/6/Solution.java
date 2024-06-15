package g0201_0300.s0258_add_digits;

// #Easy #Math #Simulation #Number_Theory #2022_07_05_Time_1_ms_(100.00%)_Space_39.3_MB_(98.44%)

public class Solution {
//@ ensures \result >= 0 && \result <= 9;
// requires public int addDigits(int num)
//@ requires num >= 0 && num <= 2147483647;
//@ ensures the result is a single digit after repeatedly adding all its digits;
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }
        if (num / 9 == 0) {
            return 9;
        }
        return num % 9;
    }
}
