package g1401_1500.s1425_constrained_subsequence_sum;

// #Hard #Array #Dynamic_Programming #Heap_Priority_Queue #Sliding_Window #Queue #Monotonic_Queue
// #2022_03_28_Time_69_ms_(30.52%)_Space_129.5_MB_(19.86%)

import java.util.LinkedList;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to `k`.*);
//@ ensures(*The value of `k` is between 1 and the length of `nums`, inclusive.*);
//@ ensures(*The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are between -10^4 and 10^4, inclusive.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*The returned integer value is the maximum sum of a non-empty subsequence of the input array `nums` that satisfies the given condition.*);
//@ ensures(*For every two consecutive integers in the subsequence, `nums[i]` and `nums[j]`, where `i < j`, the condition `j - i <= k` is satisfied.*);
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int res = Integer.MIN_VALUE;
        LinkedList<int[]> mono = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int take = nums[i];

            while (!mono.isEmpty() && i - mono.getFirst()[0] > k) {
                mono.removeFirst();
            }
            if (!mono.isEmpty()) {
                int mx = Math.max(0, mono.getFirst()[1]);
                take += mx;
            }
            while (!mono.isEmpty() && take > mono.getLast()[1]) {
                mono.removeLast();
            }

            mono.add(new int[] {i, take});
            res = Math.max(res, take);
        }
        return res;
    }
}