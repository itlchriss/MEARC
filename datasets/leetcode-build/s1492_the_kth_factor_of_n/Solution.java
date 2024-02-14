package g1401_1500.s1492_the_kth_factor_of_n;

// #Medium #Math #2022_04_05_Time_1_ms_(83.94%)_Space_41.6_MB_(14.02%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input integers `n` and `k` are positive.*);
	//@ requires(*`n` is greater than or equal to `k`.*);
	//@ ensures(*The method returns an integer.*);
	//@ ensures(*If `n` has less than `k` factors, the method returns -1.*);
	//@ ensures(*If `n` has at least `k` factors, the method returns the kth factor of `n`.*);
    public int kthFactor(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
            }
        }
        return list.size() >= k ? list.get(k - 1) : -1;
    }
}