package g0301_0400.s0393_utf_8_validation;

// #Medium #Array #Bit_Manipulation #2022_07_13_Time_1_ms_(100.00%)_Space_43_MB_(87.62%)

public class Solution {
//@ requires(*The integer array parameter `data` represents a valid UTF-8 encoding if it follows the specified rules for UTF-8 characters.*);
//@ requires(*For a 1-byte character, the first bit is 0, followed by its Unicode code.*);
//@ requires(*For an n-bytes character, the first n bits are all ones, the n + 1 bit is 0, followed by n - 1 bytes with the most significant 2 bits being 10.*);
//@ requires(*The input array `data` contains integers where only the least significant 8 bits of each integer are used to store the data.*);
//@ requires(*The method should return true if the input array `data` represents a valid UTF-8 encoding based on the specified rules.*);
//@ requires(*The method should return false if the input array `data` does not represent a valid UTF-8 encoding based on the specified rules.*);
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