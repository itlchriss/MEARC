package g0601_0700.s0636_exclusive_time_of_functions;

// #Medium #Array #Stack #2022_03_21_Time_17_ms_(76.73%)_Space_52.4_MB_(28.28%)

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Solution {
	//@ requires(*The input `n` is a positive integer representing the number of functions.*);
	//@ requires(*The input `logs` is a list of strings representing the log messages.*);
	//@ requires(*Each log message in `logs` is formatted as "{function_id}:{start|end}:{timestamp}".*);
	//@ requires(*The function IDs in `logs` range from 0 to n-- The timestamps in `logs` range from 0 to 10^- There are no two start events happening at the same timestamp.*);
	//@ requires(*There are no two end events happening at the same timestamp.*);
	//@ requires(*Each function has an "end" log for each "start" log.*);
	//@ ensures(*The method returns an array of integers representing the exclusive time of each function.*);
	//@ ensures(*The length of the returned array is equal to n.*);
	//@ ensures(*The value at the i-th index of the returned array represents the exclusive time for the function with ID i.*);
    public int[] exclusiveTime(int n, List<String> logs) {
        Deque<Log> stack = new ArrayDeque<>();
        int[] result = new int[n];
        for (String content : logs) {
            Log log = new Log(content);
            if (log.isStart) {
                stack.push(log);
            } else {
                Log top = stack.pop();
                int executionTime = log.time - top.time + 1;
                result[top.id] += executionTime - top.waitingTime;
                if (!stack.isEmpty()) {
                    stack.peek().waitingTime += executionTime;
                }
            }
        }
        return result;
    }

    private static class Log {
        int id;
        boolean isStart;
        int time;
        int waitingTime;

        Log(String content) {

            String[] tokens = content.split(":");

            id = Integer.parseInt(tokens[0]);
            isStart = tokens[1].equals("start");
            time = Integer.parseInt(tokens[2]);

            waitingTime = 0;
        }
    }
}