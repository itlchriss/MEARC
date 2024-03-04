package g2201_2300.s2244_minimum_rounds_to_complete_all_tasks;

// #Medium #Array #Hash_Table #Greedy #Counting
// #2022_06_11_Time_13_ms_(94.26%)_Space_99.8_MB_(26.72%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `tasks` is not null.*);
//@ ensures(*The length of the input array `tasks` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `tasks` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum rounds required to complete all the tasks.*);
//@ ensures(*If it is not possible to complete all the tasks, the method returns -1.*);
    public int minimumRounds(int[] tasks) {
        Arrays.sort(tasks);
        int rounds = 0;
        int c = 1;
        for (int i = 0; i < tasks.length - 1; i++) {
            if (tasks[i] == tasks[i + 1]) {
                c++;
            } else {
                if (c == 1) {
                    return -1;
                }
                if (c % 3 == 0) {
                    rounds += c / 3;
                }
                if (c % 3 >= 1) {
                    rounds += c / 3 + 1;
                }
                c = 1;
            }
        }
        if (c == 1) {
            return -1;
        }
        if (c % 3 == 0) {
            rounds += c / 3;
        }
        if (c % 3 >= 1) {
            rounds += c / 3 + 1;
        }
        return rounds;
    }
}