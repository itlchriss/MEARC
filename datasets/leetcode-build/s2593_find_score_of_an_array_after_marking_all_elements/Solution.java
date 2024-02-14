package g2501_2600.s2593_find_score_of_an_array_after_marking_all_elements;

// #Medium #Array #Sorting #Heap_Priority_Queue #Simulation
// #2023_08_23_Time_159_ms_(96.76%)_Space_56.2_MB_(68.11%)

import java.util.PriorityQueue;

public class Solution {
    private static class Point {
        int idx;
        int val;

        Point(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` is not empty.*);
	//@ requires(*The elements in the input array `nums` are positive integers.*);
	//@ requires(*The length of the input array `nums` is less than or equal to 10^5.*);
	//@ requires(*The values of the elements in the input array `nums` are less than or equal to 10^6.*);
	//@ ensures(*The method returns a long value representing the score obtained after applying the algorithm.*);
	//@ ensures(*The score is the sum of the chosen integers from the input array `nums`.*);
	//@ ensures(*All elements in the input array `nums` are marked.*);
	//@ ensures(*The marked elements and their two adjacent elements (if they exist) are marked.*);
	//@ ensures(*The order of marking the elements is based on the smallest unmarked integer and the smallest index in case of a tie.*);

    public long findScore(int[] arr) {
        PriorityQueue<Point> pq =
                new PriorityQueue<>((a, b) -> b.val == a.val ? a.idx - b.idx : a.val - b.val);
        int n = arr.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            pq.add(new Point(i, arr[i]));
        }
        long ans = 0;
        while (!pq.isEmpty()) {
            Point p = pq.remove();
            int idx = p.idx;
            if (!visited[idx]) {
                ans += p.val;
                visited[idx] = true;
                if (idx - 1 >= 0 && !visited[idx - 1]) {
                    visited[idx - 1] = true;
                }
                if (idx + 1 < n && !visited[idx + 1]) {
                    visited[idx + 1] = true;
                }
            }
        }
        return ans;
    }
}