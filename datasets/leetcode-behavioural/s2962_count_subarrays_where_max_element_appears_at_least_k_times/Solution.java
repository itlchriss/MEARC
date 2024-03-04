package g2901_3000.s2962_count_subarrays_where_max_element_appears_at_least_k_times;

// #Medium #Array #Sliding_Window #2024_01_16_Time_3_ms_(100.00%)_Space_61.6_MB_(12.43%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to - The input integer `k` is a positive integer.*);
//@ ensures(*The maximum element in the input array `nums` is less than or equal to 10^*);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a long value representing the number of subarrays where the maximum element appears at least `k` times.*);
//@ ensures(*The method does not modify the input array `nums` or its elements.*);
    public long countSubarrays(int[] n, int k) {
        int[] st = new int[n.length + 1];
        int si = 0;
        int m = 0;
        for (int i = 0; i < n.length; i++) {
            if (m < n[i]) {
                m = n[i];
                si = 0;
            }
            if (m == n[i]) {
                st[si++] = i;
            }
        }
        if (si < k) {
            return 0;
        }
        long r = 0;
        st[si] = n.length;
        for (int i = k; i <= si; i++) {
            r += (long) (st[i - k] + 1) * (st[i] - st[i - 1]);
        }
        return r;
    }
}