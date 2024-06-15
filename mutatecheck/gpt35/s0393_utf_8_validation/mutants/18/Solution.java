package g0301_0400.s0393_utf_8_validation;

// #Medium #Array #Bit_Manipulation #2022_07_13_Time_1_ms_(100.00%)_Space_43_MB_(87.62%)

public class Solution {
// ensures \result == false ==> data does not represent a valid UTF-8 encoding
// requires data != null && data.length >= 1 && data.length <= 2 * 10^4
// requires public boolean validUtf8(int[] data)
// ensures \result == true || \result == false
// requires (\forall int i; 0 <= i && i < data.length; 0 <= data[i] && data[i] <= 255)
// ensures \result == true ==> data represents a valid UTF-8 encoding
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
                } else if (d << 7 == 1) {
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
