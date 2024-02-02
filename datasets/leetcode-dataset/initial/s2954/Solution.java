package g2901_3000.s2954_count_the_number_of_infection_sequences;

// #Hard #Array #Math #Combinatorics #2024_01_15_Time_11_ms_(99.61%)_Space_46.3_MB_(29.46%)

public class Solution {
    private static final int M = 100000;
    private final long[] fact = new long[M + 1];
    private final long[] invFact = new long[M + 1];
    private static final int MOD = 1000000007;
    private long init = 0;

    private int modPow(int x, int y, int mod) {
        if (y == 0) {
            return 1;
        }
        long p = modPow(x, y / 2, mod) % mod;
        p = (p * p) % mod;
        return y % 2 == 1 ? (int) (p * x % mod) : (int) p;
    }

    private long binomCoeff(int n, int k) {
        return Math.max(1L, fact[n] * invFact[k] % MOD * invFact[n - k] % MOD);
    }
<<<<<<< HEAD
=======
//@ ensures(*Since the answer may be large, return it modulo <code>10<sup>9</sup> + 7</code>.
It can be shown that after a finite number of seconds, all the children in the queue will get infected with the disease. An **infection sequence** is the sequential order of positions in which **all** of the non-infected children get infected with the disease. Return _the total number of possible infection sequences_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03

    public int numberOfSequence(int n, int[] sick) {
        if (init == 0) {
            init = 1;
            fact[0] = 1;
            for (int i = 1; i <= M; ++i) {
                fact[i] = fact[i - 1] * i % MOD;
            }
            invFact[M] = modPow((int) fact[M], MOD - 2, MOD);
            for (int i = M - 1; i > 0; --i) {
                invFact[i] = invFact[i + 1] * (i + 1) % MOD;
            }
        }
        long res = 1;
        for (int i = 1; i < sick.length; ++i) {
            int group = sick[i] - sick[i - 1] - 1;
            res = res * modPow(2, Math.max(0, group - 1), MOD) % MOD;
            res = res * binomCoeff(sick[i] - i, group) % MOD;
        }
        return (int) (res * binomCoeff(n - sick.length, n - sick[sick.length - 1] - 1) % MOD);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
