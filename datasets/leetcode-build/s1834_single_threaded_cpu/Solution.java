package g1801_1900.s1834_single_threaded_cpu;

// #Medium #Array #Sorting #Heap_Priority_Queue
// #2022_05_07_Time_134_ms_(83.22%)_Space_100.6_MB_(75.23%)

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
	//@ requires(*The input `tasks` is a 2D integer array.*);
	//@ requires(*Each element in `tasks` is an array of size 2, representing the enqueue time and processing time of a task.*);
	//@ requires(*The enqueue time and processing time of each task are positive integers.*);
	//@ requires(*The length of `tasks` is equal to `n`.*);
	//@ requires(*The value of `n` is a positive integer.*);
	//@ requires(*The value of `n` is less than or equal to 10^5.*);
	//@ requires(*The enqueue time and processing time of each task are less than or equal to 10^9.*);
	//@ ensures(*The output is an integer array representing the order in which the CPU will process the tasks.*);
	//@ ensures(*The length of the output array is equal to `n`.*);
	//@ ensures(*The elements in the output array are indices of the tasks, ranging from 0 to `n-1`.*);
	//@ ensures(*The CPU processes the tasks in the order specified by the output array.*);
	//@ ensures(*If the CPU is idle and there are no available tasks to process, the CPU remains idle.*);
	//@ ensures(*If the CPU is idle and there are available tasks, the CPU chooses the task with the shortest processing time. If multiple tasks have the same shortest processing time, it chooses the task with the smallest index.*);
	//@ ensures(*Once a task is started, the CPU processes the entire task without stopping.*);
	//@ ensures(*The CPU can finish a task and start a new one instantly.*);
    public int[] getOrder(int[][] tasks1) {
        int n = tasks1.length;
        int[][] tasks = new int[n][3];
        for (int i = 0; i < n; i++) {
            tasks[i] = new int[] {tasks1[i][0], tasks1[i][1], i};
        }
        Arrays.sort(tasks, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> minHeap =
                new PriorityQueue<>(
                        (a, b) -> {
                            if (a[1] == b[1]) {
                                return a[2] - b[2];
                            } else {
                                return a[1] - b[1];
                            }
                        });
        int time = tasks[0][0];
        int[] taskOrderResult = new int[n];
        int i = 0;
        int index = 0;
        while (!minHeap.isEmpty() || i < n) {
            while (i < n && time >= tasks[i][0]) {
                minHeap.add(tasks[i++]);
            }
            if (!minHeap.isEmpty()) {
                int[] task = minHeap.remove();
                taskOrderResult[index++] = task[2];
                time += task[1];
            } else {
                time = tasks[i][0];
            }
        }
        return taskOrderResult;
    }
}