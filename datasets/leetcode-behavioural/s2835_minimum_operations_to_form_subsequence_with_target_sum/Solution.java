package g2801_2900.s2835_minimum_operations_to_form_subsequence_with_target_sum;

// #Hard #Array #Greedy #Bit_Manipulation #2023_12_11_Time_2_ms_(94.66%)_Space_43.3_MB_(39.69%)

import java.util.List;
import java.util.PriorityQueue;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input list `nums` is not null.*);
//@ ensures(*The input list `nums` is not empty.*);
//@ ensures(*The elements in the input list `nums` are non-negative powers of - The input integer `target` is greater than or equal to - The input integer `target` is less than 2^*);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of operations needed to form a subsequence in `nums` whose elements sum to `target`.*);
//@ ensures(*If it is impossible to obtain such a subsequence, the method returns -1.*);
    public int minOperations(List<Integer> nums, int target) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        long sum = 0;
        long count = 0;
        for (int x : nums) {
            pq.offer(x);
            sum += x;
        }
        if (sum < target) {
            return -1;
        }
        while (!pq.isEmpty()) {
            int val = pq.poll();
            sum -= val;
            if (val <= target) {
                target -= val;
            } else if (sum < target) {
                count++;
                pq.offer(val / 2);
                pq.offer(val / 2);
                sum += val;
            }
        }
        return (int) count;
    }
}