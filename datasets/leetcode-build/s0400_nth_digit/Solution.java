package g0301_0400.s0400_nth_digit;

// #Medium #Math #Binary_Search #2022_07_15_Time_0_ms_(100.00%)_Space_39_MB_(95.41%)

public class Solution {
    /*
     * 1. find the length of the number where the nth digit is from
     * 2. find the actual number where the nth digit is from
     * 3. find the nth digit and return
     */
	//@ requires(*The input `n` is a positive integer.*);
	//@ requires(*The input `n` is within the range of `1` to `2^31 - 1`.*);
	//@ ensures(*The method returns an integer representing the `n`th digit of the infinite integer sequence.*);
	//@ ensures(*The returned digit is within the range of `0` to `9`.*);
    public int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;
        while (n > len * count) {
            n -= len * count;
            len += 1;
            count *= 10;
            start *= 10;
        }
        start += (n - 1) / len;
        String s = Integer.toString(start);
        return Character.getNumericValue(s.charAt((n - 1) % len));
    }
}