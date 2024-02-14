package g0101_0200.s0172_factorial_trailing_zeroes;

// #Medium #Top_Interview_Questions #Math #Udemy_Integers
// #2022_06_26_Time_1_ms_(85.61%)_Space_42.1_MB_(7.61%)

public class Solution {
	//@ requires(*The input `n` must be a non-negative integer.*);
	//@ requires(*The input `n` must be less than or equal to 10^4.*);
	//@ ensures(*The method should return an integer representing the number of trailing zeroes in `n!`.*);
	//@ ensures(*If `n` is 0, the method should return 0.*);
	//@ ensures(*If `n` is greater than 0, the method should return the number of trailing zeroes in `n!`.*);
    public int trailingZeroes(int n) {
        int base = 5;
        int count = 0;
        while (n >= base) {
            count += n / base;
            base = base * 5;
        }
        return count;
    }
}