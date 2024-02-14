package g0401_0500.s0483_smallest_good_base;

// #Hard #Math #Binary_Search #2022_07_21_Time_2_ms_(96.00%)_Space_40.6_MB_(93.33%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*1. The input `n` is a string representation of a positive integer.*);
	//@ requires(*2. The input `n` does not contain any leading zeros.*);
	//@ ensures(*1. The output is a string representation of the smallest good base of `n`.*);
	//@ ensures(*2. The output is a positive integer in base `k` where all digits are `1`'s.*);
	//@ ensures(*3. The output is the smallest possible base `k` that satisfies the condition.*);
	//@ ensures(*4. The output is a valid base for `n`, meaning that all digits in `n` can be represented in base `k`.*);
	//@ ensures(*5. The output is a valid base for `n`, meaning that the base `k` is greater than or equal to 2.*);
    public String smallestGoodBase(String n) {
        return sol1(n);
    }

    private String sol1(String n) {
        long x = Long.parseLong(n);
        List<Long> ans = new ArrayList<>();
        ans.add(x - 1);
        long y = x - 1;
        for (int i = 2; i < 63; i++) {
            double dm = Math.pow(y, 1.0 / i);
            long dml = (long) dm;
            for (int j = 0; j > -3 && dml + j > 1; j--) {
                long d = dml + j;
                if (y % d == 0) {
                    long poly = poly(d, i);
                    if (poly == x) {
                        ans.add(d);
                    }
                }
            }
        }
        long end = ans.get(ans.size() - 1);
        return end + "";
    }

    private long poly(long b, int n) {
        long ans = 1;
        long m = 1;
        for (int i = 0; i < n; i++) {
            m *= b;
            ans += m;
        }
        return ans;
    }
}