package g2801_2900.s2845_count_of_interesting_subarrays;

// #Medium #Array #Hash_Table #Prefix_Sum #2023_12_13_Time_27_ms_(97.76%)_Space_62.7_MB_(26.12%)

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input list `nums` is not null.*);
//@ ensures(*The input list `nums` is not empty.*);
//@ ensures(*The input integer `modulo` is greater than 0.*);
//@ ensures(*The input integer `k` is greater than or equal to 0.*);
//@ ensures(*The input integer `k` is less than `modulo`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer denoting the count of interesting subarrays.*);
//@ ensures(*The returned count is greater than or equal to 0.*);
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int prefixCnt = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);
        long interestingSubarrays = 0;
        for (Integer num : nums) {
            if (num % modulo == k) {
                prefixCnt++;
            }
            int expectedPrefix = (prefixCnt - k + modulo) % modulo;
            interestingSubarrays += freq.getOrDefault(expectedPrefix, 0);
            freq.put(prefixCnt % modulo, freq.getOrDefault(prefixCnt % modulo, 0) + 1);
        }
        return interestingSubarrays;
    }
}