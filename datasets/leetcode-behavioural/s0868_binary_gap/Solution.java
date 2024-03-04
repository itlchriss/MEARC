package g0801_0900.s0868_binary_gap;

// #Easy #Bit_Manipulation #2022_05_16_Time_1_ms_(82.94%)_Space_41.4_MB_(28.50%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the longest distance between any two adjacent `1`'s in the binary representation of `n`.*);
//@ ensures(*If there are no two adjacent `1`'s, the method returns `0`.*);
    public int binaryGap(int n) {
        int max = 0;
        int pos = 0;
        int lastPos = -1;
        while (n != 0) {
            pos++;
            if ((n & 1) == 1) {
                if (lastPos != -1) {
                    max = Math.max(max, pos - lastPos);
                }
                lastPos = pos;
            }
            n >>= 1;
        }
        return max;
    }
}