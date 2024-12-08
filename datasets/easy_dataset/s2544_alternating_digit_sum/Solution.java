package g2501_2600.s2544_alternating_digit_sum;

// #Easy #Math #2023_05_11_Time_0_ms_(100.00%)_Space_39.4_MB_(79.49%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input integer `n` must be a positive integer.*);
//@ ensures(*2. The input integer `n` must be less than or equal to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method should return an integer value.*);
//@ ensures(*2. The returned integer value should be the sum of all digits of `n` with their corresponding sign.*);
    public int alternateDigitSum(int n) {
        String s = Integer.toString(n);
        char[] arr = s.toCharArray();
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res += (int) Math.pow(-1, i) * (arr[i] - '0');
        }
        return res;
    }
}