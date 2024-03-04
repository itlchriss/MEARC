package g0601_0700.s0636_exclusive_time_of_functions;

// #Medium #Array #Stack #2022_03_21_Time_17_ms_(76.73%)_Space_52.4_MB_(28.28%)

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Solution {
//@ ensures(*Method behavioural requirements:*);
//@ ensures(*The integer parameter `n` must be between 1 and 100.*);
//@ ensures(*The integer list parameter `logs` must have a length between 1 and 500.*);
//@ ensures(*The integer function ID `function_id` in the logs must be between 0 and n-1.*);
//@ ensures(*The timestamp `timestamp` in the logs must be between 0 and 10^9.*);
//@ ensures(*No two start events can happen at the same timestamp.*);
//@ ensures(*No two end events can happen at the same timestamp.*);
//@ ensures(*Each function ID has an "end" log for each "start" log.*);
//@ ensures(*The integer array result must contain the exclusive time for each function ID.*);
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