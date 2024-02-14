package g1801_1900.s1882_process_tasks_using_servers;

// #Medium #Array #Heap_Priority_Queue #2022_05_06_Time_290_ms_(77.45%)_Space_69.1_MB_(95.62%)

import java.util.PriorityQueue;

public class Solution {
	//@ requires(*The `servers` array and `tasks` array must not be null.*);
	//@ requires(*The lengths of the `servers` array and `tasks` array must be equal to `n` and `m` respectively.*);
	//@ requires(*The length of the `servers` array and `tasks` array must be between 1 and 2 * 10^5 (inclusive).*);
	//@ requires(*The values in the `servers` array and `tasks` array must be between 1 and 2 * 10^5 (inclusive).*);
	//@ ensures(*The returned array `ans` must not be null.*);
	//@ ensures(*The length of the returned array `ans` must be equal to `m`.*);
	//@ ensures(*The values in the returned array `ans` must be indices of the servers, indicating which server each task is assigned to.*);
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<Integer> serverq =
                new PriorityQueue<>(
                        (i1, i2) ->
                                servers[i1] != servers[i2] ? servers[i1] - servers[i2] : i1 - i2);
        for (int i = 0; i < servers.length; i++) {
            serverq.offer(i);
        }
        PriorityQueue<int[]> activetaskq = new PriorityQueue<>((i1, i2) -> i1[1] - i2[1]);
        int time = 0;
        int[] res = new int[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            time = Math.max(time, i);
            while (!activetaskq.isEmpty() && activetaskq.peek()[1] <= i) {
                int[] task = activetaskq.poll();
                serverq.offer(task[0]);
            }
            if (serverq.isEmpty()) {
                int[] toptask = activetaskq.peek();
                while (!activetaskq.isEmpty() && activetaskq.peek()[1] == toptask[1]) {
                    int[] task = activetaskq.poll();
                    serverq.offer(task[0]);
                }
                time = toptask[1];
            }
            int server = serverq.poll();
            res[i] = server;
            activetaskq.offer(new int[] {server, time + tasks[i]});
        }
        return res;
    }
}