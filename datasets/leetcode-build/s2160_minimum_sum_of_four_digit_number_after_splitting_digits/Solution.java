package g2101_2200.s2160_minimum_sum_of_four_digit_number_after_splitting_digits;

// #Easy #Math #Sorting #Greedy #2022_06_03_Time_1_ms_(78.31%)_Space_40.7_MB_(62.78%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input `num` must be a positive integer.*);
	//@ requires(*The input `num` must consist of exactly four digits.*);
	//@ ensures(*The output must be an integer representing the minimum possible sum of `new1` and `new2`.*);
	//@ ensures(*The digits in `num` must be split into two new integers `new1` and `new2`.*);
	//@ ensures(*Leading zeros are allowed in `new1` and `new2`.*);
	//@ ensures(*All the digits found in `num` must be used in the split.*);
	//@ ensures(*The sum of `new1` and `new2` must be minimized.*);
    public int minimumSum(int num) {
        int[] digit = new int[4];
        int cur = 0;
        while (num > 0) {
            digit[cur++] = num % 10;
            num /= 10;
        }
        Arrays.sort(digit);
        int num1 = digit[0] * 10 + digit[2];
        int num2 = digit[1] * 10 + digit[3];
        return num1 + num2;
    }
}