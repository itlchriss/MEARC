package g0301_0400.s0338_counting_bits;

// #Easy #Top_100_Liked_Questions #Dynamic_Programming #Bit_Manipulation #Udemy_Bit_Manipulation
// #Big_O_Time_O(num)_Space_O(num) #2022_07_10_Time_2_ms_(86.73%)_Space_48.3_MB_(31.59%)

public class Solution {
	//@ requires(*The input `num` is a non-negative integer.*);
	//@ ensures(*The output is an array `ans` of length `num + 1`.*);
	//@ ensures(*For each index `i` in the range from 0 to `num`, `ans[i]` is the number of 1's in the binary representation of `i`.*);
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        int borderPos = 1;
        int incrPos = 1;
        for (int i = 1; i < result.length; i++) {
            // when we reach pow of 2 ,  reset borderPos and incrPos
            if (incrPos == borderPos) {
                result[i] = 1;
                incrPos = 1;
                borderPos = i;
            } else {
                result[i] = 1 + result[incrPos++];
            }
        }
        return result;
    }
}