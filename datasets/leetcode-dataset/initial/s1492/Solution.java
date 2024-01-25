package g1401_1500.s1492_the_kth_factor_of_n;

// #Medium #Math #2022_04_05_Time_1_ms_(83.94%)_Space_41.6_MB_(14.02%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Consider a list of all factors of `n` sorted in **ascending order**, return _the_ <code>k<sup>th</sup></code> _factor_ in this list or return `-1` if `n` has less than `k` factors. **Explanation:** Factors list is [1, 2, 4], there is only 3 factors. We should return -1.*);

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