package g0301_0400.s0313_super_ugly_number;

// #Medium #Array #Dynamic_Programming #Math #2022_07_10_Time_16_ms_(96.57%)_Space_42.3_MB_(64.43%)

@SuppressWarnings("java:S3012")
public class Solution {
//@ ensures(*The integer parameter `n` must be greater than or equal to 1.*);
//@ ensures(*The integer array parameter `primes` must not be null.*);
//@ ensures(*The integer array parameter `primes` must have a length greater than or equal to 1 and less than or equal to 100.*);
//@ ensures(*Each element in the integer array parameter `primes` must be greater than or equal to 2 and less than or equal to 1000.*);
//@ ensures(*Each element in the integer array parameter `primes` must be a prime number.*);
//@ ensures(*The integer array parameter `primes` must be sorted in ascending order.*);
//@ ensures(*The integer result is the `n`th super ugly number.*);
    public int nthSuperUglyNumber(int n, int[] primes) {
        long[] primes1 = new long[primes.length];
        for (int i = 0; i < primes.length; i++) {
            primes1[i] = primes[i];
        }
        int[] index = new int[primes.length];
        long[] n1 = new long[n];
        n1[0] = 1L;
        for (int i = 1; i < n; i++) {
            long min = Long.MAX_VALUE;
            for (long l : primes1) {
                min = Math.min(min, l);
            }
            n1[i] = min;
            for (int j = 0; j < primes1.length; j++) {
                if (min == primes1[j]) {
                    primes1[j] = primes[j] * (n1[++index[j]]);
                }
            }
        }
        return (int) n1[n - 1];
    }
}