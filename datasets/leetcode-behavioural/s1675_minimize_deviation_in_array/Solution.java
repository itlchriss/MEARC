package g1601_1700.s1675_minimize_deviation_in_array;

// #Hard #Array #Greedy #Heap_Priority_Queue #Ordered_Set
// #2022_04_19_Time_104_ms_(88.83%)_Space_58.3_MB_(91.85%)

import java.util.PriorityQueue;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 2.*);
//@ ensures(*The elements in the input array `nums` are positive integers.*);
//@ ensures(*The maximum value of the elements in the input array `nums` is less than or equal to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum deviation of the array.*);
//@ ensures(*The minimum deviation is the maximum difference between any two elements in the array after performing some number of operations.*);
//@ ensures(*The array after performing the operations has the minimum deviation.*);
//@ ensures(*The array after performing the operations satisfies the conditions of the problem statement.*);
//@ ensures(*The array after performing the operations is a valid array with positive integers.*);
//@ ensures(*The array after performing the operations has the same length as the input array `nums`.*);
    public int minimumDeviation(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num % 2 == 1) {
                num *= 2;
            }
            min = Math.min(min, num);
            pq.offer(num);
        }
        int diff = Integer.MAX_VALUE;
        while (pq.peek() % 2 == 0) {
            int max = pq.poll();
            diff = Math.min(diff, max - min);
            min = Math.min(max / 2, min);
            pq.offer(max / 2);
        }
        return Math.min(diff, pq.peek() - min);
    }
}