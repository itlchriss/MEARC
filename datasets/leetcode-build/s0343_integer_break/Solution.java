package g0301_0400.s0343_integer_break;

// #Medium #Dynamic_Programming #Math #Algorithm_II_Day_18_Dynamic_Programming
// #Dynamic_Programming_I_Day_21 #2022_07_10_Time_0_ms_(100.00%)_Space_40.7_MB_(67.02%)

public class Solution {
    private int[] arr;
	//@ requires(*The input `n` must be an integer greater than or equal to 2 and less than or equal to*);
	//@ ensures(*The method should return an integer representing the maximum product that can be obtained by breaking `n` into the sum of `k` positive integers.*);
	//@ ensures(*The sum of the positive integers should be equal to `n`.*);
	//@ ensures(*The number of positive integers `k` should be greater than or equal to 2.*);

    public int integerBreak(int n) {
        arr = new int[n + 1];
        arr[2] = 1;
        // only case involve with 1 other than 2 is 3
        return n == 3 ? 2 : dp(n);
    }

    private int dp(int n) {
        if (n <= 2) {
            return arr[2];
        } else if (arr[n] != 0) {
            return arr[n];
        } else {
            int prod = 1;
            for (int i = 2; i <= n; i++) {
                prod = Math.max(prod, i * dp(n - i));
            }
            arr[n] = prod;
        }
        return arr[n];
    }
}