package g2801_2900.s2834_find_the_minimum_possible_sum_of_a_beautiful_array;

// #Medium #Math #Greedy #2023_12_11_Time_0_ms_(100.00%)_Space_39.6_MB_(48.53%)

public class Solution {
	//@ requires(*The input `n` is a positive integer.*);
	//@ requires(*The input `target` is a positive integer.*);
	//@ requires(*The input `n` is less than or equal to 10^9.*);
	//@ requires(*The input `target` is less than or equal to 10^9.*);
	//@ ensures(*The output is an integer.*);
	//@ ensures(*The output is the minimum possible sum of a beautiful array modulo 10^9 + 7.*);
    public int minimumPossibleSum(int n, int k) {
        long mod = (long) 1e9 + 7;
        if (k > (n + n - 1)) {
            return (int) ((long) n * (n + 1) % mod / 2);
        }
        long toChange = n - (long) (k / 2);
        long sum = ((n * ((long) n + 1)) / 2) % mod;
        long remain = (long) k - ((k / 2) + 1);
        return (int) ((sum + (toChange * remain) % mod) % mod);
    }
}