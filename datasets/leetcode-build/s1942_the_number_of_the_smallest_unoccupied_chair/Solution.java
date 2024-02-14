package g1901_2000.s1942_the_number_of_the_smallest_unoccupied_chair;

// #Medium #Array #Heap_Priority_Queue #Ordered_Set
// #2022_05_16_Time_73_ms_(49.69%)_Space_73.1_MB_(38.04%)

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	//@ requires(*The input `times` is a 2D integer array where each element `times[i]` represents the arrival and leaving times of the `i`th friend.*);
	//@ requires(*The input `targetFriend` is an integer representing the friend number for whom we need to find the chair number.*);
	//@ requires(*All arrival times in `times` are distinct.*);
	//@ ensures(*The method returns an integer representing the chair number that the `targetFriend` will sit on.*);
	//@ ensures(*Additional behavioral requirements:*);
	//@ ensures(*The method should handle the case when the `times` array is empty and return -1 or throw an exception indicating an invalid input.*);
	//@ ensures(*The method should handle the case when the `targetFriend` is out of the range of valid friend numbers (0 to n-1) and return -1 or throw an exception indicating an invalid input.*);
	//@ ensures(*The method should handle the case when there are no unoccupied chairs available and return -1 or throw an exception indicating that all chairs are occupied.*);
	//@ ensures(*The method should handle the case when the `times` array contains overlapping time intervals and return the correct chair number based on the arrival and leaving times.*);
	//@ ensures(*The method should handle the case when the `times` array contains negative arrival or leaving times and return -1 or throw an exception indicating an invalid input.*);
	//@ ensures(*The method should handle the case when the `times` array contains arrival or leaving times that are greater than 10^5 and return -1 or throw an exception indicating an invalid input.*);
    public int smallestChair(int[][] times, int targetFriend) {
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        minheap.offer(0);
        Person[] all = new Person[times.length * 2];
        for (int i = 0; i < times.length; i++) {
            all[2 * i] = new Person(i, times[i][0], false, true);
            all[2 * i + 1] = new Person(i, times[i][1], true, false);
        }
        Arrays.sort(
                all,
                (a, b) -> {
                    int i = a.leave ? -1 : 1;
                    int j = b.leave ? -1 : 1;
                    return a.time == b.time ? i - j : a.time - b.time;
                });

        int[] seat = new int[times.length];
        for (int i = 0; true; i++) {
            if (all[i].arrive) {
                if (targetFriend == all[i].idx) {
                    return minheap.peek();
                }
                seat[all[i].idx] = minheap.poll();
                if (minheap.isEmpty()) {
                    minheap.offer(seat[all[i].idx] + 1);
                }
            } else {
                minheap.offer(seat[all[i].idx]);
            }
        }
    }

    private static class Person {
        boolean leave;
        boolean arrive;
        int time;
        int idx;

        Person(int idx, int time, boolean leave, boolean arrive) {
            this.time = time;
            this.leave = leave;
            this.arrive = arrive;
            this.idx = idx;
        }
    }
}