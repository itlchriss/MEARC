package g0301_0400.s0393_utf_8_validation;

// #Medium #Array #Bit_Manipulation #2022_07_13_Time_1_ms_(100.00%)_Space_43_MB_(87.62%)

public class Solution {
//@ requires(*A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:*);
//@ requires(*1.*);
//@ requires(*For a 1-byte character, the first bit is a `0`, followed by its Unicode code.*);
//@ requires(*2.*);
//@ requires(*For an n-bytes character, the first `n` bits are all one's, the `n + 1` bit is `0`, followed by `n - 1` bytes with the most significant `2` bits being `10`.*);
//@ requires(*This is how the UTF-8 encoding would work:*);
//@ requires(*Char.*);
//@ requires(*number range  |        UTF-8 octet sequence*);
//@ requires(*(hexadecimal)    |              (binary)*);
//@ requires(*--------------------+---------------------------------------------*);
//@ requires(*0000 0000-0000 007F | 0xxxxxxx*);
//@ requires(*0000 0080-0000 07FF | 110xxxxx 10xxxxxx*);
//@ requires(*0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx*);
//@ requires(*0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx*);
//@ requires(*Note: The input is an array of integers.*);
//@ requires(*Only the least significant 8 bits of each integer is used to store the data.*);
//@ requires(*This means each integer represents only 1 byte of data.*);
//@ requires(*Example 1:*);
//@ requires(*Input: data = [197,130,1]*);
//@ requires(*Output: true*);
//@ requires(*Explanation: data represents the octet sequence: 11000101 10000010 00000001.*);
//@ requires(*It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.*);
//@ requires(*Example 2:*);
//@ requires(*Input: data = [235,140,4]*);
//@ requires(*Output: false*);
//@ requires(*Explanation: data represented the octet sequence: 11101011 10001100 00000100.*);
//@ requires(*The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.*);
//@ requires(*The next byte is a continuation byte which starts with 10 and that's correct.*);
//@ requires(*But the second continuation byte does not start with 10, so it is invalid.*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= data.length <= 2  10<sup>4</sup></code>*);
//@ requires(*`0 <= data[i] <= 255`*);
//@ ensures(*Given an integer array param_data representing the data, the result is whether it is a valid UTF-8 encoding.*);
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