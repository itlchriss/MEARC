package g2001_2100.s2099_find_subsequence_of_length_k_with_the_largest_sum;

// #Easy #Array #Hash_Table #Sorting #Heap_Priority_Queue
// #2022_05_26_Time_6_ms_(85.46%)_Space_42.3_MB_(94.75%)

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to `k`.*);
//@ ensures(*The value of `k` is greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `result` is not null.*);
//@ ensures(*The length of the output array `result` is equal to `k`.*);
//@ ensures(*The sum of all elements in the output array `result` is the largest among all possible subsequences of length `k` in the input array `nums`.*);
    public int[] maxSubsequence(int[] nums, int k) {
        // Create proirity queue with min priority queue so that min element will be removed first,
        // with index
        // Add those unique index in a set
        // Loop from 0 to n-1 and add element in result if set contains those index
        // For ex. set has index 3,5,6 Just add those element. Order will be maintained
        // We are defining the min priority queue
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        // Add element with index to priority queue
        for (int i = 0; i < nums.length; i++) {
            q.offer(new int[] {nums[i], i});
            if (q.size() > k) {
                q.poll();
            }
        }
        // Set to keep index
        Set<Integer> index = new HashSet<>();
        // At the index in the set since index are unique
        while (!q.isEmpty()) {
            int[] top = q.poll();
            index.add(top[1]);
        }
        // Final result add here
        int[] result = new int[k];
        // Just add the element in the result for those index present in SET
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (index.contains(i)) {
                result[p] = nums[i];
                ++p;
            }
        }
        return result;
    }
}