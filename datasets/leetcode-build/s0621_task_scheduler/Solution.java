package g0601_0700.s0621_task_scheduler;

// #Medium #Array #Hash_Table #Sorting #Greedy #Heap_Priority_Queue #Counting #Level_2_Day_5_Greedy
// #2022_03_21_Time_3_ms_(84.32%)_Space_59.6_MB_(58.24%)

public class Solution {
	//@ requires(*The input `tasks` is not null.*);
	//@ requires(*The length of `tasks` is greater than or equal to 1 and less than or equal to 10^4.*);
	//@ requires(*Each element in `tasks` is an upper-case English letter.*);
	//@ requires(*The integer `n` is non-negative and is in the range [0, 100].*);
	//@ ensures(*The method returns an integer representing the least number of units of time that the CPU will take to finish all the given tasks.*);
    public int leastInterval(char[] tasks, int n) {
        if (n <= 0) {
            return tasks.length;
        }
        int[] counters = new int[26];
        int maxCount = 0;
        for (char task : tasks) {
            int idx = task - 'A';
            counters[idx]++;
            maxCount = Math.max(maxCount, counters[idx]);
        }
        int maxNum = 0;
        for (int counter : counters) {
            if (counter == maxCount) {
                maxNum++;
            }
        }
        return Math.max(tasks.length, (maxCount - 1) * (n + 1) + maxNum);
    }
}