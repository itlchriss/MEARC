package g0201_0300.s0263_ugly_number;

// #Easy #Math #2022_07_05_Time_2_ms_(65.06%)_Space_41.3_MB_(46.18%)

public class Solution {
//@ requires(*An ugly number is a positive integer whose prime factors are limited to `2`, `3`, and `5`.*);
//@ requires(*Example 1:*);
//@ requires(*Input: n = 6*);
//@ requires(*Output: true*);
//@ requires(*Explanation: 6 = 2 × 3*);
//@ requires(*Example 2:*);
//@ requires(*Input: n = 8*);
//@ requires(*Output: true*);
//@ requires(*Explanation: 8 = 2 × 2 × 2*);
//@ requires(*Example 3:*);
//@ requires(*Input: n = 14*);
//@ requires(*Output: false*);
//@ requires(*Explanation: 14 is not ugly since it includes the prime factor 7.*);
//@ requires(*Example 4:*);
//@ requires(*Input: n = 1*);
//@ requires(*Output: true*);
//@ requires(*Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.*);
//@ requires(*Constraints:*);
//@ requires(*<code>-2<sup>31</sup> <= n <= 2<sup>31</sup> - 1</code>*);
//@ ensures(*Given an integer param_n, the result is `true` if param_n is an ugly number.*);
    public boolean isUgly(int n) {
        if (n == 1) {
            return true;
        } else if (n <= 0) {
            return false;
        }
        int[] factors = new int[] {2, 3, 5};
        for (int factor : factors) {
            while (n > 1 && n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }
}