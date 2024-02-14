package g2501_2600.s2575_find_the_divisibility_array_of_a_string;

// #Medium #Array #String #Math #2023_08_22_Time_6_ms_(100.00%)_Space_56.4_MB_(49.79%)

public class Solution {
	//@ requires(*The input string `word` is not null.*);
	//@ requires(*The length of `word` is greater than 0.*);
	//@ requires(*The input integer `m` is a positive integer.*);
	//@ ensures(*The output array `div` is not null.*);
	//@ ensures(*The length of `div` is equal to the length of `word`.*);
	//@ ensures(*Each element in `div` is either 0 or 1.*);
	//@ ensures(*The numeric value of `word[0,...,i]` is divisible by `m` if and only if `div[i]` is 1.*);
    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        long modulo = 0;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int numericValue = word.charAt(i) - '0';
            modulo = (modulo * 10 + numericValue) % m;
            if (modulo == 0) {
                result[i] = 1;
            }
        }
        return result;
    }
}