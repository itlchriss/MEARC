package g0001_0100.s0089_gray_code;

// #Medium #Math #Bit_Manipulation #Backtracking
// #2022_06_20_Time_3_ms_(98.59%)_Space_47.3_MB_(99.65%)

import java.util.Arrays;
import java.util.List;

public class Solution {
//@ ensures(*The integer parameter `n` must be within the inclusive range [1, 16].*);
//@ ensures(*The integer list result must be a valid n-bit gray code sequence.*);
//@ ensures(*The first element of the integer list result must be 0.*);
//@ ensures(*Each integer in the integer list result must be unique.*);
//@ ensures(*The binary representation of every pair of adjacent integers in the integer list result must differ by exactly one bit.*);
//@ ensures(*The binary representation of the first and last integers in the integer list result must differ by exactly one bit.*);
    public List<Integer> grayCode(int n) {
        Integer[] n1 = {0};
        int shift = 1;
        while (n > 0) {
            Integer[] temp = new Integer[n1.length * 2];
            int pos = 0;
            for (Integer integer : n1) {
                temp[pos++] = integer;
            }
            for (int i = n1.length - 1; i >= 0; i--) {
                temp[pos++] = n1[i] | shift;
            }
            n1 = temp;
            shift <<= 1;
            n--;
        }
        return Arrays.asList(n1);
    }
}