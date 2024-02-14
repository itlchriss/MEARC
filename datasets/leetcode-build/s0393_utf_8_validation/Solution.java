package g0301_0400.s0393_utf_8_validation;

// #Medium #Array #Bit_Manipulation #2022_07_13_Time_1_ms_(100.00%)_Space_43_MB_(87.62%)

public class Solution {
	//@ requires(*The input array `data` is not null.*);
	//@ requires(*The length of the input array `data` is greater than or equal to 1 and less than or equal to - Each element in the input array `data` is an integer between 0 and*);
	//@ ensures(*The method returns a boolean value indicating whether the input `data` is a valid UTF-8 encoding.*);
	//@ ensures(*If the input `data` is a valid UTF-8 encoding, the method returns true.*);
	//@ ensures(*If the input `data` is not a valid UTF-8 encoding, the method returns false.*);
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