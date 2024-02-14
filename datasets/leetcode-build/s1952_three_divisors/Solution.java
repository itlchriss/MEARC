package g1901_2000.s1952_three_divisors;

// #Easy #Math #2022_05_18_Time_1_ms_(84.91%)_Space_39.4_MB_(79.95%)

public class Solution {
	//@ requires(*The input integer `n` must be greater than or equal to 1.*);
	//@ requires(*The input integer `n` must be less than or equal to 10^4.*);
	//@ ensures(*The method should return `true` if `n` has exactly three positive divisors.*);
	//@ ensures(*The method should return `false` if `n` does not have exactly three positive divisors.*);
    public boolean isThree(int n) {
        int divisors = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                divisors++;
            }
        }
        return divisors == 3;
    }
}