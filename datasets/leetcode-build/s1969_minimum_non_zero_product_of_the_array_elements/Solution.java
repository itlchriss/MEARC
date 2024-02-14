package g1901_2000.s1969_minimum_non_zero_product_of_the_array_elements;

// #Medium #Math #Greedy #Recursion #2022_05_21_Time_0_ms_(100.00%)_Space_40.2_MB_(75.38%)

public class Solution {
	//@ requires(*The input `p` is a positive integer.*);
	//@ requires(*The array `nums` is 1-indexed.*);
	//@ requires(*The elements of `nums` are integers in the inclusive range [1, 2^p - 1].*);
	//@ requires(*The length of `nums` is 2^p - 1.*);
	//@ ensures(*The return value is an integer.*);
	//@ ensures(*The return value is the minimum non-zero product of `nums`.*);
	//@ ensures(*The return value is before the modulo operation is done.*);
	//@ ensures(*The return value is modulo 10^9 + 7.*);
    public int minNonZeroProduct(int p) {
        int m = (int) (1e9 + 7);
        long n = ((1L << p) - 2) % m;
        long ans = n + 1;
        long cnt = (1L << (p - 1)) - 1;
        while (cnt > 0) {
            if ((cnt & 1) == 1) {
                ans = ans * n % m;
            }
            cnt >>= 1;
            n = n * n % m;
        }
        return (int) ans;
    }
}