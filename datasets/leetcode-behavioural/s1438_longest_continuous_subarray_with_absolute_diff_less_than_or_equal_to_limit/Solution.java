package g1401_1500.s1438_longest_continuous_subarray_with_absolute_diff_less_than_or_equal_to_limit;

// #Medium #Array #Heap_Priority_Queue #Sliding_Window #Ordered_Set #Queue #Monotonic_Queue
// #2022_03_28_Time_38_ms_(81.86%)_Space_77.7_MB_(28.67%)

import java.util.ArrayDeque;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The input array `nums` must have at least one element.*);
//@ ensures(*The input array `nums` must contain only integers.*);
//@ ensures(*The input integer `limit` must be non-negative.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output integer must be the size of the longest non-empty subarray.*);
//@ ensures(*The output integer must be greater than or equal to 1.*);
//@ ensures(*The output integer must be less than or equal to the length of the input array `nums`.*);
    public int longestSubarray(int[] nums, int limit) {
        ArrayDeque<Integer> maxQ = new ArrayDeque<>();
        ArrayDeque<Integer> minQ = new ArrayDeque<>();
        int best = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            while (!maxQ.isEmpty() && nums[right] > nums[maxQ.peekLast()]) {
                maxQ.removeLast();
            }
            maxQ.offerLast(right);
            while (!minQ.isEmpty() && nums[right] < nums[minQ.peekLast()]) {
                minQ.removeLast();
            }
            minQ.offerLast(right);
            while (nums[maxQ.peekFirst()] - nums[minQ.peekFirst()] > limit) {
                if (maxQ.peekFirst() == left) {
                    maxQ.removeFirst();
                }
                if (minQ.peekFirst() == left) {
                    minQ.removeFirst();
                }
                left++;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}