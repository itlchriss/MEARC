package g2001_2100.s2100_find_good_days_to_rob_the_bank;

// #Medium #Array #Dynamic_Programming #Prefix_Sum
// #2022_05_17_Time_13_ms_(46.46%)_Space_99.4_MB_(51.27%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input array `security` is not null.*);
	//@ requires(*The length of the input array `security` is greater than or equal to 1.*);
	//@ requires(*The input integer `time` is greater than or equal to 0.*);
	//@ ensures(*The returned list contains only valid indices from the input array `security`.*);
	//@ ensures(*The returned list contains all the indices of the good days to rob the bank.*);
	//@ ensures(*The order of the indices in the returned list does not matter.*);
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;
        // dec: # of non-increasing elements before [i]
        // inc: # of non-decreasing elements after [i]
        int[] dec = new int[n];
        int[] inc = new int[n];
        for (int i = 1; i < n; i++) {
            if (security[i] <= security[i - 1]) {
                dec[i] = dec[i - 1] + 1;
            }
            // no need for else, because array elements are inited as 0
        }
        for (int i = n - 2; i >= 0; i--) {
            if (security[i] <= security[i + 1]) {
                inc[i] = inc[i + 1] + 1;
            }
            // no need for else, because array elements are inited as 0
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dec[i] >= time && inc[i] >= time) {
                res.add(i);
            }
        }
        return res;
    }
}