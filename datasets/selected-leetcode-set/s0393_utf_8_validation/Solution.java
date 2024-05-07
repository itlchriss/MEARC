package g0301_0400.s0393_utf_8_validation;

// #Medium #Array #Bit_Manipulation #2022_07_13_Time_1_ms_(100.00%)_Space_43_MB_(87.62%)

public class Solution {
//@ ensures(*Method behavioural requirements:*);
//@ ensures(*The integer array parameter `data` must not be null.*);
//@ ensures(*The integer array parameter `data` must have a length between 1 and 2 * 10^4.*);
//@ ensures(*Each element in the integer array parameter `data` must be between 0 and 255.*);
//@ ensures(*The boolean result is true if the integer array parameter `data` represents a valid UTF-8 encoding according to the specified rules.*);
//@ ensures(*The boolean result is false if the integer array parameter `data` does not represent a valid UTF-8 encoding according to the specified rules.*);
    public boolean validUtf8(int[] data) {
        int count = 0;
        for (int d : data) {
            if (count == 0) {
                if (d >> 5 == 0b110) {
                    count = 1;
                } else if (d >> 4 == 0b1110) {
                    count = 2;
                } else if (d >> 3 == 0b11110) {
                    count = 3;
                } else if (d >> 7 == 1) {
                    return false;
                }
            } else {
                if (d >> 6 != 0b10) {
                    return false;
                } else {
                    count--;
                }
            }
        }
        return count == 0;
    }
}