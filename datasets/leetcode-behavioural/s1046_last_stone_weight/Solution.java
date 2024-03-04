package g1001_1100.s1046_last_stone_weight;

// #Easy #Array #Heap_Priority_Queue #Level_1_Day_15_Heap
// #2022_02_27_Time_2_ms_(73.81%)_Space_42.3_MB_(5.13%)

import java.util.PriorityQueue;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `stones` is not null.*);
//@ ensures(*The length of the input array `stones` is at least - The elements in the input array `stones` are positive integers.*);
//@ ensures(*The elements in the input array `stones` are not null.*);
//@ ensures(*The elements in the input array `stones` are not greater than *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the smallest possible weight of the left stone.*);
//@ ensures(*If there are no stones left, the method returns 0.*);
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            heap.offer(stone);
        }
        while (!heap.isEmpty()) {
            if (heap.size() >= 2) {
                int one = heap.poll();
                int two = heap.poll();
                int diff = one - two;
                heap.offer(diff);
            } else {
                return heap.poll();
            }
        }
        return -1;
    }
}