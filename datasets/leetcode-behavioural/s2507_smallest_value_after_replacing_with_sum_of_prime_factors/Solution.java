package g2501_2600.s2507_smallest_value_after_replacing_with_sum_of_prime_factors;

// #Medium #Math #Number_Theory #2023_03_20_Time_1_ms_(84.27%)_Space_40_MB_(31.46%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private final Map<Integer, Integer> memo = new HashMap<>();
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` is a positive integer.*);
//@ ensures(*`n` is greater than or equal to 2 and less than or equal to 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the smallest value `n` will take on.*);
//@ ensures(*The value of `n` is continuously replaced with the sum of its prime factors until it cannot be further reduced.*);
//@ ensures(*If `n` is already a prime number, the method returns `n` itself.*);
//@ ensures(*The method terminates and returns the smallest value `n` will take on.*);

    public int smallestValue(int n) {
        while (get(n) != n) {
            n = get(n);
        }
        return n;
    }

    private int get(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        double r = Math.pow(n, 0.5);
        int r1 = (int) r;
        if (r - r1 == 0) {
            return 2 * get(r1);
        }
        int res = 0;
        for (int i = r1; i >= 2; i--) {
            if (n % i == 0) {
                res = get(i) + get(n / i);
            }
        }
        res = res == 0 ? n : res;
        memo.put(n, res);
        return res;
    }
}