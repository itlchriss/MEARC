package g0001_0100.s0089_gray_code;

// #Medium #Math #Bit_Manipulation #Backtracking
// #2022_06_20_Time_3_ms_(98.59%)_Space_47.3_MB_(99.65%)

import java.util.Arrays;
import java.util.List;

public class Solution {
	//@ requires(*The input `n` is a positive integer.*);
	//@ requires(*`n` is less than or equal to 16.*);
	//@ ensures(*The output is a list of integers.*);
	//@ ensures(*The length of the output list is equal to `2^n`.*);
	//@ ensures(*The first element of the output list is 0.*);
	//@ ensures(*Each integer in the output list is in the inclusive range `[0, 2^n - 1]`.*);
	//@ ensures(*Each integer in the output list appears no more than once.*);
	//@ ensures(*The binary representation of every pair of adjacent integers in the output list differs by exactly one bit.*);
	//@ ensures(*The binary representation of the first and last integers in the output list differs by exactly one bit.*);
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