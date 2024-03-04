package g1901_2000.s1962_remove_stones_to_minimize_the_total;

// #Medium #Array #Heap_Priority_Queue #2022_05_20_Time_761_ms_(48.67%)_Space_124.8_MB_(24.78%)

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `piles` must not be null.*);
//@ ensures(*The length of the input array `piles` must be greater than or equal to 1.*);
//@ ensures(*The elements in the input array `piles` must be positive integers.*);
//@ ensures(*The input integer `k` must be greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value must be an integer.*);
//@ ensures(*The return value must be the minimum possible total number of stones remaining after applying the `k` operations.*);
//@ ensures(*The return value must be greater than or equal to 0.*);
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> descendingQueue = new PriorityQueue<>(Collections.reverseOrder());
        int sum = 0;
        int newValue;
        int currentValue;
        int half;
        for (int stones : piles) {
            sum += stones;
            descendingQueue.offer(stones);
        }
        while (k > 0) {
            currentValue = descendingQueue.poll();
            half = currentValue / 2;
            newValue = currentValue - half;
            descendingQueue.offer(newValue);
            sum -= half;
            k--;
        }
        return sum;
    }
}