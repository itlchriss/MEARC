package g1801_1900.s1808_maximize_number_of_nice_divisors;

// #Hard #Math #Recursion #2022_05_03_Time_1_ms_(80.77%)_Space_41_MB_(42.31%)

public class Solution {
    private long modPow(long b, int e, int m) {
        if (m == 1) {
            return 0;
        }
        if (e == 0 || b == 1) {
            return 1;
        }
        b %= m;
        long r = 1;
        while (e > 0) {
            if ((e & 1) == 1) {
                r = r * b % m;
            }
            e = e >> 1;
            b = b * b % m;
        }
        return r;
    }
	//@ requires(*The input `primeFactors` is a positive integer.*);
	//@ requires(*The value of `primeFactors` is between 1 and 10^9 (inclusive).*);
	//@ ensures(*The method returns an integer representing the number of nice divisors of `n`.*);
	//@ ensures(*The returned value is the maximum possible number of nice divisors for a positive integer `n` that satisfies the given conditions.*);
	//@ ensures(*The returned value is modulo 10^9 + 7.*);

    public int maxNiceDivisors(int pf) {
        int mod = 1000000007;
        int[] st = new int[] {0, 1, 2, 3, 4, 6};
        return pf < 5 ? pf : (int) (modPow(3, pf / 3 - 1, mod) * st[3 + pf % 3] % mod);
    }
}