package g2501_2600.s2520_count_the_digits_that_divide_a_number;

// #Easy #Math #2023_04_18_Time_0_ms_(100.00%)_Space_39.3_MB_(62.57%)

public class Solution {
	//@ requires(*The input `num` is an integer.*);
	//@ requires(*The value of `num` is greater than or equal to 1.*);
	//@ requires(*The value of `num` does not contain the digit 0.*);
	//@ ensures(*The output is an integer representing the number of digits in `num` that divide `num`.*);
	//@ ensures(*The output is greater than or equal to 0.*);
	//@ ensures(*The output is less than or equal to the number of digits in `num`.*);
	//@ ensures(*The output is equal to the number of digits in `num` that are divisible by `num`.*);
    public int countDigits(int num) {
        int a = num;
        int count = 0;
        while (a > 0) {
            int r = a % 10;
            if (r != 0 && num % r == 0) {
                count++;
            }
            a /= 10;
        }
        return count;
    }
}