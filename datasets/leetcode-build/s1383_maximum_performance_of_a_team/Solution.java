package g1301_1400.s1383_maximum_performance_of_a_team;

// #Hard #Array #Sorting #Greedy #Heap_Priority_Queue
// #2022_03_21_Time_66_ms_(66.03%)_Space_69.1_MB_(40.82%)

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	//@ requires(*The input arrays `speed` and `efficiency` must have the same length as `n`.*);
	//@ requires(*The length of the input arrays `speed` and `efficiency` must be greater than or equal to `k`.*);
	//@ requires(*The values of `k` and `n` must be within the specified constraints.*);
	//@ requires(*The values in the input arrays `speed` and `efficiency` must be within the specified constraints.*);
	//@ ensures(*The method should return an integer value representing the maximum performance of the team.*);
	//@ ensures(*The returned value should be the sum of the speeds of the selected engineers multiplied by the minimum efficiency among them.*);
	//@ ensures(*The returned value should be modulo `10^9 + 7`.*);
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] engineers = new int[n][2];
        for (int i = 0; i < n; i++) {
            engineers[i][0] = speed[i];
            engineers[i][1] = efficiency[i];
        }
        Arrays.sort(engineers, (engineer1, engineer2) -> engineer2[1] - engineer1[1]);
        long speedSum = 0;
        long maximumPerformance = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int[] engineer : engineers) {
            if (minHeap.size() == k) {
                speedSum -= minHeap.poll();
            }
            speedSum += engineer[0];
            minHeap.offer(engineer[0]);
            maximumPerformance = Math.max(maximumPerformance, speedSum * engineer[1]);
        }
        return (int) (maximumPerformance % 1000_000_007);
    }
}