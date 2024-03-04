package g2501_2600.s2558_take_gifts_from_the_richest_pile;

// #Easy #Array #Heap_Priority_Queue #Simulation
// #2023_08_19_Time_5_ms_(97.80%)_Space_41.8_MB_(25.67%)

import java.util.PriorityQueue;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `gifts` is not null.*);
//@ ensures(*The length of the input array `gifts` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `gifts` are positive integers.*);
//@ ensures(*The input integer `k` is greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a non-negative integer.*);
//@ ensures(*The output is less than or equal to the sum of all elements in the input array `gifts`.*);
//@ ensures(*The output is equal to the sum of the remaining gifts after `k` seconds.*);
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        long res = 0;
        for (int gift : gifts) {
            pq.add(gift);
        }
        while (!pq.isEmpty() && k > 0) {
            long val = pq.poll();
            int newVal = (int) Math.sqrt(val);
            pq.add(newVal);
            k--;
        }
        while (!pq.isEmpty()) {
            res += pq.poll();
        }
        return res;
    }
}