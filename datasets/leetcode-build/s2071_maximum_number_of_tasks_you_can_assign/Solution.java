package g2001_2100.s2071_maximum_number_of_tasks_you_can_assign;

// #Hard #Array #Sorting #Greedy #Binary_Search #Queue #Monotonic_Queue
// #2022_05_30_Time_156_ms_(40.49%)_Space_117.1_MB_(61.46%)

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
	//@ requires(*The length of the `tasks` array must be equal to `n`.*);
	//@ requires(*The length of the `workers` array must be equal to `m`.*);
	//@ requires(*The value of `pills` must be between 0 and `m`, inclusive.*);
	//@ requires(*The value of `strength` must be between 0 and 10^9, inclusive.*);
	//@ ensures(*The method should return an integer representing the maximum number of tasks that can be completed.*);
	//@ ensures(*Other requirements:*);
	//@ ensures(*Each worker can only be assigned to a single task.*);
	//@ ensures(*Each worker must have a strength greater than or equal to the task's strength requirement.*);
	//@ ensures(*Each worker can receive at most one magical pill.*);
	//@ ensures(*The magical pill will increase a worker's strength by the value of `strength`.*);
	//@ ensures(*The method should assign the magical pills and tasks in a way that maximizes the number of completed tasks.*);
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        int left = 0;
        int right = Math.min(tasks.length, workers.length);
        Arrays.sort(tasks);
        Arrays.sort(workers);
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (canAssign(mid, tasks, workers, pills, strength)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (canAssign(right, tasks, workers, pills, strength)) {
            return right;
        } else {
            return left;
        }
    }

    private boolean canAssign(int count, int[] tasks, int[] workers, int pills, int strength) {
        Deque<Integer> dq = new ArrayDeque<>();
        int ind = workers.length - 1;
        for (int i = count - 1; i >= 0; i--) {
            while (ind >= workers.length - count && workers[ind] + strength >= tasks[i]) {
                dq.offerLast(workers[ind]);
                ind--;
            }
            if (dq.isEmpty()) {
                return false;
            }
            if (dq.peekFirst() >= tasks[i]) {
                dq.pollFirst();
            } else {
                dq.pollLast();
                pills--;
                if (pills < 0) {
                    return false;
                }
            }
        }
        return true;
    }
}