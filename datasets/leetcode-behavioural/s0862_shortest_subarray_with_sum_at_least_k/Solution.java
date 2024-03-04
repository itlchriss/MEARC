package g0801_0900.s0862_shortest_subarray_with_sum_at_least_k;

// #Hard #Array #Binary_Search #Heap_Priority_Queue #Prefix_Sum #Sliding_Window #Queue
// #Monotonic_Queue #2022_03_27_Time_53_ms_(74.73%)_Space_125.4_MB_(52.01%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The length of the input array `nums` must be greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` must be within the range of -10^5 to 10^5.*);
//@ ensures(*The input integer `k` must be within the range of 1 to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer representing the length of the shortest non-empty subarray of `nums` with a sum of at least `k`.*);
//@ ensures(*If there is no such subarray, the method should return -1.*);
    public int shortestSubarray(int[] nums, int k) {
        Deque<long[]> dq = new ArrayDeque<>();
        dq.offer(new long[] {-1, 0});
        int i = 0;
        long curSum = 0;
        int res = Integer.MAX_VALUE;
        while (i < nums.length) {
            curSum += nums[i];
            while (!dq.isEmpty() && dq.peekFirst()[1] <= curSum - k) {
                res = Math.min(res, (int) (i - dq.pollFirst()[0]));
            }
            while (!dq.isEmpty() && dq.peekLast()[1] >= curSum) {
                dq.pollLast();
            }
            dq.offerLast(new long[] {i, curSum});
            i++;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}