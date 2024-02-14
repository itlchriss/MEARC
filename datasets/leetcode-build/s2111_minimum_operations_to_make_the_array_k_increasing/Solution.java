package g2101_2200.s2111_minimum_operations_to_make_the_array_k_increasing;

// #Hard #Array #Binary_Search #2022_05_31_Time_97_ms_(22.90%)_Space_123.6_MB_(54.96%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input array `arr` is not null.*);
	//@ requires(*The length of the input array `arr` is greater than or equal to 1.*);
	//@ requires(*The value of `k` is greater than or equal to 1.*);
	//@ requires(*The value of `k` is less than or equal to the length of the input array `arr`.*);
	//@ ensures(*The output is an integer representing the minimum number of operations required to make the array K-increasing for the given `k`.*);
    public int kIncreasing(int[] a, int k) {
        int n = a.length;
        int res = 0;
        for (int s = 0; s < k; s++) {
            List<Integer> dp = new ArrayList<>();
            for (int i = s; i < n; i += k) {
                if (!bsearch(dp, a[i])) {
                    dp.add(a[i]);
                }
            }
            res += dp.size();
        }
        return n - res;
    }

    private boolean bsearch(List<Integer> dp, int target) {
        if (dp.isEmpty()) {
            return false;
        }
        int lo = 0;
        int hi = dp.size() - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (dp.get(mid) <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        if (dp.get(lo) > target) {
            dp.set(lo, target);
            return true;
        }
        return false;
    }
}