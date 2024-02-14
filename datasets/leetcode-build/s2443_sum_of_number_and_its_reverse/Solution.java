package g2401_2500.s2443_sum_of_number_and_its_reverse;

// #Medium #Math #Enumeration #2022_12_13_Time_265_ms_(80.33%)_Space_39.5_MB_(89.70%)

public class Solution {
	//@ requires(*The input `num` must be a non-negative integer.*);
	//@ ensures(*The method should return a boolean value indicating whether `num` can be expressed as the sum of any non-negative integer and its reverse.*);
	//@ ensures(*If `num` can be expressed as the sum of any non-negative integer and its reverse, the method should return `true`.*);
	//@ ensures(*If `num` cannot be expressed as the sum of any non-negative integer and its reverse, the method should return `false`.*);
    public boolean sumOfNumberAndReverse(int num) {
        for (int i = 0; i <= num; ++i) {
            int n = i;
            int r = 0;
            while (n != 0) {
                r = r * 10 + n % 10;
                n = n / 10;
            }
            if (r + i == num) {
                return true;
            }
        }
        return false;
    }
}