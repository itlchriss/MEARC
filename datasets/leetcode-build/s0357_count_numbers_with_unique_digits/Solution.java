package g0301_0400.s0357_count_numbers_with_unique_digits;

// #Medium #Dynamic_Programming #Math #Backtracking
// #2022_07_11_Time_0_ms_(100.00%)_Space_41.2_MB_(23.67%)

public class Solution {
	//@ requires(*The input `n` must be a non-negative integer.*);
	//@ requires(*The input `n` must be less than or equal to 8.*);
	//@ ensures(*The output must be an integer representing the count of all numbers with unique digits.*);
	//@ ensures(*The output must be greater than or equal to 1.*);
	//@ ensures(*The output must be less than or equal to 10^n.*);
    public int countNumbersWithUniqueDigits(int n) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            int mul = 1;
            for (int j = 1; j < i; j++) {
                mul *= (10 - j);
            }
            ans = ans + 9 * mul;
        }
        return ans;
    }
}