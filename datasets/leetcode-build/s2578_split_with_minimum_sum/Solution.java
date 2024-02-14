package g2501_2600.s2578_split_with_minimum_sum;

// #Easy #Math #Sorting #Greedy #2023_08_22_Time_0_ms_(100.00%)_Space_39.3_MB_(76.63%)

public class Solution {
	//@ requires(*The input number `num` is a positive integer.*);
	//@ requires(*The input number `num` does not contain any leading zeros.*);
	//@ ensures(*The method returns an integer representing the minimum possible sum of `num1` and `num2`.*);
	//@ ensures(*The concatenation of `num1` and `num2` is a permutation of `num`.*);
	//@ ensures(*The sum of the number of occurrences of each digit in `num1` and `num2` is equal to the number of occurrences of that digit in `num`.*);
	//@ ensures(*`num1` and `num2` can contain leading zeros.*);
    public int splitNum(int number) {
        int num1 = 0;
        int num2 = 0;
        boolean addToOne = true;
        for (int i = 0; i <= 9; i++) {
            int tmpNumber = number;
            while (tmpNumber > 0) {
                int digit = tmpNumber % 10;
                if (digit == i) {
                    if (addToOne) {
                        num1 *= 10;
                        num1 += digit;
                        addToOne = false;
                    } else {
                        num2 *= 10;
                        num2 += digit;
                        addToOne = true;
                    }
                }
                tmpNumber /= 10;
            }
        }
        return num1 + num2;
    }
}