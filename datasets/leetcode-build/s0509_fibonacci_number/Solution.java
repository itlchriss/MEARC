package g0501_0600.s0509_fibonacci_number;

// #Easy #Dynamic_Programming #Math #Recursion #Memoization #Dynamic_Programming_I_Day_1
// #Level_1_Day_10_Dynamic_Programming #Udemy_Dynamic_Programming
// #2022_07_25_Time_0_ms_(100.00%)_Space_41.4_MB_(15.60%)

public class Solution {
    private int[] memo = new int[31];
	//@ requires(*The input `n` must be a non-negative integer.*);
	//@ requires(*The value of `n` must be within the range of 0 to 30.*);
	//@ ensures(*The method should return an integer value representing the Fibonacci number `F(n)`.*);
	//@ ensures(*The returned value should be the sum of the two preceding Fibonacci numbers, `F(n-1)` and `F(n-2)`, if `n` is greater than 1.*);
	//@ ensures(*If `n` is 0, the method should return 0.*);
	//@ ensures(*If `n` is 1, the method should return 1.*);

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = fib(n - 1) + fib(n - 2);
        return memo[n];
    }
}