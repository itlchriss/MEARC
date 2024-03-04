package g2301_2400.s2365_task_scheduler_ii;

// #Medium #Array #Hash_Table #Simulation #2022_08_14_Time_70_ms_(55.56%)_Space_123.3_MB_(11.11%)

import java.util.HashMap;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `tasks` is not null.*);
//@ ensures(*The input array `tasks` is not empty.*);
//@ ensures(*The input array `tasks` contains only positive integers.*);
//@ ensures(*The input integer `space` is positive.*);
//@ ensures(*The input integer `space` is less than or equal to the length of the `tasks` array.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a positive integer representing the minimum number of days needed to complete all tasks.*);
//@ ensures(*The output is greater than or equal to the length of the `tasks` array.*);
//@ ensures(*The output is the minimum number of days needed to complete all tasks, considering the space constraint.*);
    public long taskSchedulerII(int[] tasks, int space) {
        long days = 0;
        space++;
        HashMap<Integer, Long> lastOccurence = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            if (lastOccurence.containsKey(tasks[i])) {
                long lastTimeOccurred = lastOccurence.get(tasks[i]);
                long daysDifference = days - lastTimeOccurred;
                if (daysDifference < space) {
                    days += (space - daysDifference);
                }
            }
            lastOccurence.put(tasks[i], days);
            days++;
        }
        return days;
    }
}