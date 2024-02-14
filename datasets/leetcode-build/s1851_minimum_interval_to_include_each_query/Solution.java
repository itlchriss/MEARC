package g1801_1900.s1851_minimum_interval_to_include_each_query;

// #Hard #Array #Sorting #Binary_Search #Heap_Priority_Queue #Line_Sweep
// #2022_05_08_Time_140_ms_(84.24%)_Space_90.2_MB_(82.06%)

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
	//@ requires(*The input array `intervals` is not null.*);
	//@ requires(*The input array `queries` is not null.*);
	//@ requires(*The length of `intervals` is greater than or equal to 1.*);
	//@ requires(*The length of `queries` is greater than or equal to 1.*);
	//@ requires(*Each interval in `intervals` is a 2-element array.*);
	//@ requires(*The first element of each interval in `intervals` is less than or equal to the second element.*);
	//@ requires(*The elements in `intervals` are within the range of 1 to 10^7.*);
	//@ requires(*The elements in `queries` are within the range of 1 to 10^7.*);
	//@ ensures(*The returned array is not null.*);
	//@ ensures(*The length of the returned array is equal to the length of `queries`.*);
	//@ ensures(*Each element in the returned array is either a positive integer or -1.*);
    public int[] minInterval(int[][] intervals, int[] queries) {
        int numQuery = queries.length;

        int[][] queriesWithIndex = new int[numQuery][2];
        for (int i = 0; i < numQuery; i++) {
            queriesWithIndex[i] = new int[] {queries[i], i};
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(queriesWithIndex, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<int[]> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(a -> (a[1] - a[0])));

        int[] result = new int[numQuery];

        int j = 0;
        for (int i = 0; i < queries.length; i++) {
            int queryVal = queriesWithIndex[i][0];
            int queryIndex = queriesWithIndex[i][1];

            while (j < intervals.length && intervals[j][0] <= queryVal) {
                minHeap.add(intervals[j]);
                j++;
            }

            while (!minHeap.isEmpty() && minHeap.peek()[1] < queryVal) {
                minHeap.remove();
            }
            result[queryIndex] =
                    minHeap.isEmpty() ? -1 : (minHeap.peek()[1] - minHeap.peek()[0] + 1);
        }

        return result;
    }
}